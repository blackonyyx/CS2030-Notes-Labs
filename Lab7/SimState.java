import java.util.stream.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
public class SimState {
  private final PriorityQueue<Event> events;
  private final String logs;
  private final Statistics stats;
  private final Shop shop;
  public SimState(int numOfServers) {
    this.shop = new Shop(numOfServers);
    this.stats = new Statistics();
    this.events = new PriorityQueue<Event>();
    this.logs = "";
  }
  public SimState(Shop s, Statistics stat, PriorityQueue<Event> e,String log){
    this.shop =s;
    this.stats = stat;
    this.events =e;
    this.logs = log;
  }
  class Event implements Comparable<Event> {
    private double time;
    private Function<SimState,SimState> fn;
    Event(double time,Function<SimState,SimState> fn) {
      this.time = time;
      this.fn = fn;
    }  
    public int compareTo(Event other) {
      return (int)Math.signum(this.time - other.time);
    }
    public SimState simulate(SimState sim){
      return this.fn.apply(sim);
    }
  }
  public SimState addEvent(double time,Function<SimState,SimState> f){
    PriorityQueue<Event> ev = this.events.add(new Event(time,f));
    return new SimState(shop,stats,ev,logs);
  }
  public SimState copy(){
    return new SimState(shop.copy(),stats.clone(),events.clone(events.copy()),logs);
  }
  private Pair<Optional<Event>, SimState> nextEvent() {
    Pair<Optional<Event>, PriorityQueue<Event>> result = events.poll();
    return Pair.of(result.first,new SimState(shop,stats,result.second,logs));
  }
  public SimState noteArrival(double time, Customer c) {
    String s = String.format("%.3f %s arrives\n", time, c);
    return new SimState(shop.copy(),stats.clone().newCust(),events.clone(events.copy()),logs+s);
  }
  public SimState noteWait(double time, Server s, Customer c) {
    String st = String.format("%.3f %s waits to be served by %s\n", time, c, s);
    return new SimState(shop.copy(),stats.clone(),events.clone(events.copy()),logs+st);
  }
  public SimState noteServed(double time, Server s, Customer c) {
    String st = String.format("%.3f %s served by %s\n", time, c, s);
    Statistics sta= stats.serveOneCustomer().recordWaitingTime(c.timeWaited(time));
    return new SimState(shop.copy(),sta,events.clone(events.copy()),logs+st);
  }
  public SimState noteDone(double time, Server s, Customer c) {
    String st = String.format("%.3f %s done serving by %s\n", time, c, s);
    return new SimState(shop.copy(),stats.clone(),events.clone(events.copy()),logs+st);
  }
  public SimState noteLeave(double time, Customer customer) {
    String st = String.format("%.3f %s leaves\n", time, customer);
    return new SimState(shop.copy(),stats.looseOneCustomer(),events.clone(events.copy()),logs+st);
  }
  public SimState simulateArrival(double time) {
    SimState x = copy();
    Customer customer = new Customer(time,this.stats.getID()+1);
    return new SimState(x.shop,x.stats,x.events,x.logs)
                .noteArrival(time, customer)
                .processArrival(time, customer);
  }
  private SimState processArrival(double time, Customer customer) {
    Optional<Server> serv = shop.copy().findIdleServer();
    SimState x = copy();
    if (serv.isPresent()) {
      return serveCustomer(time,serv.get(),customer);
    }
    serv = shop.copy().findServerWithNoWaitingCustomer();
    if (serv.isPresent()) {
      Server s = serv.get().askToWait(customer);
      Shop shopee = shop.replace(serv.get().askToWait(customer));
      return new SimState(shopee.copy(),x.stats,x.events,x.logs).noteWait(time,s,customer);
    }
    return new SimState(shop,stats,events,logs).noteLeave(time,customer);
  }
  public SimState simulateDone(double time, Server server, Customer customer) {
    Optional<Server> real = this.shop.find(ser->server.equals(ser) );
    if (real.isPresent()){
      SimState x = copy();
      x = x.noteDone(time, real.get(), customer);
      Optional<Customer> c = real.get().getWaitingCustomer();
      if (c.isPresent()) {
        return new SimState(x.shop,x.stats,x.events,x.logs).serveCustomer(time, real.get(), c.get());
      }
      Server s = real.get().makeIdle();
      Shop shopee = shop.replace(s);
      return new SimState(shopee,x.stats,x.events,x.logs);
    }
    return this;
  }
  private SimState serveCustomer(double time, Server server, Customer customer) {
    double doneTime = time + Simulation.SERVICE_TIME;
    Optional<Server> real = this.shop.find(ser->server.equals(ser));
    SimState x = copy();
    Server s = real.get().serve(customer);
    Shop sho = shop.copy().replace(s);
    return new SimState(sho,x.stats,x.events,x.logs).noteServed(time,s,customer).addEvent(doneTime,t->t.simulateDone(doneTime,s,customer));
  }
  public SimState run() {
    Pair<Optional<Event>, SimState> p = nextEvent();
    return Stream.iterate(p, x->x.first.get().simulate(x.second).nextEvent()).filter(y->y.first.isEmpty()).findFirst().get().second;
  }

  /**
   * Return a string representation of the simulation state, which
   * consists of all the logs and the stats.
   * @return A string representation of the simulation.
   */
  public String toString() {
    return logs+stats.toString();
  }
}
