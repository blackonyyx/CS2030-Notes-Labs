import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * Simulation class that handles and manages the sequence of discrete events.
 */
class Simulation{
  /**
   * ArrayList of servers starting with the first one produced.
   * Determines how arrivals are processed
   */
  private List<Server> s = new ArrayList<Server>();
  /**
   * Arrival times of new customers.
   * To determine arrival events for the simulation.
   */
  private List<Double> arrivals;
  /**
   * Statistics Class to keep track of the required statistics.
   * ie Average wait time, number served, number leaving without service.
   */
  private Statistics stats= new Statistics();
  /**
   * PriorityQueue of events to clear before end of simulation.
   */
  private PriorityQueue<Event> events;
  /**
   * constructs the simulation that creates the arrival events according to values passed to it from main.
   * Loads the arrival events into events.
   * @param i number of servers created.
   * @param c double values at which arrival events are created and loaded onto the priorityqueue
   */
  public Simulation(int i, List<Double> c){
    for (int n = 0; n<i; n++){
      s.add(new Server());
    }
    this.arrivals = c;
    events = new PriorityQueue<>(new EventComparator());
    for (Double d : c){
      double val = d.doubleValue();
      Customer cust = new Customer(val);
      ArrivalEvent temp = new ArrivalEvent(cust,val);
      events.add(temp);
    }
  }
  /**
   * Processes the sequence of Events to calculate and print out the statistice.
   * 1) polls the event queue and gets the first event from PriorityQueue
   * 2) Prints it out to the console to signify initialisation and processing
   * 3) Event gets processed by the servers to create the next event.
   * 4) Statistics are recorded after the creation of a new event, if any.
   * 5) New event is added onto the queue
   * 6) After the queue is cleared, statistics are then printed onto the console
   */
  public void processEvents(){
    while (events.size() >0){
      Event first = events.poll();
      System.out.println(first);
      Event cont = first.getNextEvent(this.s);
      if (cont != null){
        cont.upStats(stats);
        events.add(cont);
      }
    }
    System.out.println(stats);
  }
}
