package cs2030.simulator;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.function.Function;

/**
 * This class encapsulates all the simulation states.  There are four main
 * components: (i) the event queue, (ii) the statistics, (iii) the shop
 * (the servers) and (iv) the event logs.
 *
 * @author atharvjoshi
 * @author weitsang
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
public class SimState {

    /**
     * The Event class encapsulates information and methods pertaining to a
     * Simulator event. Stores a lambda that denotes type of event - arrival or
     * done.
     */
    private class Event implements Comparable<Event> {
        /** The time this event occurs at. */
        private double time;

        /** A function that this event will execute. */
        private Function<SimState, SimState> lambda;

        /**
         * Creates an event and initializes it.
         * @param time The time of occurrence.
         * @param f The function that this event will execute.
         */
        public Event(double time, Function<SimState, SimState> f) {
            this.time = time;
            this.lambda = f;
        }

        /**
         * Defines natural ordering of events by their time.
         * Events ordered in ascending order of their timestamps.
         *
         * @param other Another event to compare against.
         * @return 0 if two events occur at same time, a positive number if
         *     this event has later than other event, a negative number otherwise.
         */
        public int compareTo(Event other) {
            return (int)Math.signum(this.time - other.time);
        }

        /**
         * Smulates this event by applying the lambda.
         *
         * @param state The current simulation state.
         * @return A new state of simulation.
         */
        public SimState simulate(SimState state) {
            return this.lambda.apply(state);
        }
    }

    /** The priority queue of events. */
    private PriorityQueue<Event> events;

    /** The statistics maintained. */
    private final Statistics stats;

    /** The shop of servers. */
    private final Shop shop;

    /** The event logs. */
    private final String log;

    /** The customer id. */
    private final int lastCustomerId;

    /** The Simulation class encapsulates all the parameters that cause changes in the SimState.*/
    private final Simulation rNG;
    /**
     * A private constructor of internal states.
     * @param  shop The list of servers.
     * @param  stats The statistics being kept.
     * @param  events A priority queue of events.
     * @param  log A log of what's happened so far.
     * @param lastCustomerId the last customer that the SimState pertains to.
     * @param Simulation the class that determines the set of events that come to
     *          pass within the SimState.
     */

    private SimState(Shop shop, Statistics stats, PriorityQueue<Event> events,
            String log, int lastCustomerId,Simulation r) {
        this.shop = shop;
        this.stats = stats;
        this.events = events;
        this.log = log;
        this.lastCustomerId = lastCustomerId;
        this.rNG = r;
    }

    /**
     * Constructor for creating the simulation state from scratch.
     * @param numOfServers The number of servers.
     */
    public SimState(int numOfServers,int numOfSelfChecks,int capacity,Simulation r) {
        this(new Shop(numOfServers,capacity,numOfSelfChecks),
                new Statistics(),
                new PriorityQueue<Event>(),
                "", 1,r);
    }

    /**
     * Initialises the simulation of all events.
     * @param total total number of arrival events in the simulation.
     * @return SimState after all events have been processed and added to the log in the simulation.
     */
    public SimState simulation(int total) {
        SimState simulated = this;
        double time = 0;
        while (total > 0) {
            total--;
            final double t = time;
            simulated = simulated.addEvent(t,state  ->  state.simulateArrival(t));
            time += rNG.genInterArrivalTime();

        }
        return simulated;
    }

    /**
     * Update the statistics of this simulation.
     * @param  stats The updated statistics to replace the existing one.
     * @return The new simulation state.
     */
    private SimState stats(Statistics stats) {
        return new SimState(this.shop, stats, this.events, this.log, this.lastCustomerId,this.rNG);
    }

    /**
     * Update a server of this simulations.
     * @param  s The updated server to replace the existing one.
     * @return The new simulation state.
     */
    private SimState server(Server s) {
        return new SimState(shop.replace(s), this.stats, this.events, this.log, 
                this.lastCustomerId,this.rNG);
    }

    /**
     * Update the event queue of this simulations.
     * @param  pq The priority queue to replace the existing one.
     * @return The new simulation state.
     */
    private SimState events(PriorityQueue<Event> pq) {
        return new SimState(this.shop, this.stats, pq, this.log, this.lastCustomerId,this.rNG);
    }

    /**
     * Update the event log of this simulations.
     * @param  s The log string to append to this event log.
     * @return The new simulation state.
     */
    private SimState log(String s) {
        return new SimState(this.shop,this.stats,this.events,this.log + s,
                this.lastCustomerId,this.rNG);
    }

    /**
     * Update the event log of this simulations.
     * @param  id The server id
     * @return The new simulation state.
     */
    private SimState id(int id) {
        return new SimState(this.shop, this.stats, this.events, this.log, id,this.rNG);
    }

    /**
     * Add an event to the simulation's event queue.
     * @param  time The time the event to be added occur.
     * @param  lambda How the state to be updated upon execution of this event.
     * @return The new simulation state.
     */
    public SimState addEvent(double time, Function<SimState, SimState> lambda) {
        return events(events.add(new Event(time, lambda)));
    }

    /**
     * Retrieve the next event with earliest time stamp from the
     * priority queue, and a new state.  If there is no more event, an
     * Optional.empty will be returned.
     * @return A pair object with an (optional) event and the new simulation
     *     state.
     */
    private Pair<Optional<Event>, SimState> nextEvent() {
        Pair<Optional<Event>, PriorityQueue<Event>> result = this.events.poll();
        return Pair.of(result.first, events(result.second));
    }

    /**
     * Called when a customer arrived in the simulation.
     * @param time The time the customer arrives.
     * @param c The customer that arrrives.
     * @return A new state of the simulation after the customer arrives.
     */
    public SimState noteArrival(double time, Customer c) {
        return log(String.format("%.3f %s arrives\n", time, c));
    }

    /**
     * Called when a customer arrived in the simulation.  This methods update
     * the logs of simulation.
     * @param time The time the customer arrives.
     * @param c The customer that arrrives.
     * @return A new state of the simulation after the customer arrives.
     */
    public SimState noteWait(double time, Server s, Customer c) {
        return log(String.format("%.3f %s waits to be served by %s\n", time, c, s));
    }

    /**
     * Called when a customer is served in the simulation.  This methods
     * update the logs and the statistics of the simulation.
     * @param time The time the customer arrives.
     * @param s The server that serves the customer.
     * @param c The customer that is served.
     * @return A new state of the simulation after the customer is served.
     */
    public SimState noteServed(double time, Server s, Customer c) {
        return log(String.format("%.3f %s served by %s\n", time, c, s))
            .stats(stats
                    .serveOneCustomer()
                    .recordWaitingTime(c.timeWaited(time)));
    }

    /**
     * Called when a customer is done being served in the simulation.
     * This methods update the logs of the simulation.
     * @param time The time the customer arrives.
     * @param s The server that serves the customer.
     * @param c The customer that is served.
     * @return A new state of the simulation after the customer is done being
     *     served.
     */
    public SimState noteDone(double time, Server s, Customer c) {
        return log(String.format("%.3f %s done serving by %s\n", time, c, s));
    }

    /**
     * Called when a customer leaves the shops without service.
     * Update the log and statistics.
     * @param  time  The time this customer leaves.
     * @param  customer The customer who leaves.
     * @return A new state of the simulation.
     */
    public SimState noteLeave(double time, Customer customer) {
        return log(String.format("%.3f %s leaves\n", time, customer))
            .stats(stats.looseOneCustomer());
    }

    /**
     * Simulates the logic of what happened when a customer arrives.
     * Determines if customer is greedy or not, then 
     * the customer is either served, waiting to be served, or leaves.
     * @param time The time the customer arrives.
     * @return A new state of the simulation.
     */
    public SimState simulateArrival(double time) {
        int type = rNG.genCustomerType();
        Customer customer = new Customer(time, this.lastCustomerId,type);
        return noteArrival(time, customer)
            .id(this.lastCustomerId + 1)
            .processArrival(time, customer);
    }

    /**
     * Handle the logic of finding idle servers to serve the customer, 
     * or a server that the customer can wait for, or leave.  Called
     * from simulateArrival.
     * @param time The time the customer arrives.
     * @param customer The customer to be served.
     * @return A new state of the simulation.
     */
    private SimState processArrival(double time, Customer customer) {
        if (!customer.isGreedy()) {
            return shop.find(server  ->  server.isIdle() && !server.isResting())
                .map(server  ->  serveCustomer(time, server, customer))
                .or(() -> shop.findself(s -> s.isIdle())
                        .map(s -> serveCustomer(time,s,customer)))
                .or(()  ->  shop
                        .find(server  ->  !server.queueFull())
                        .map(server  ->  noteWait(time, server, customer)
                            .server(server.askToWait(customer))))
                .or(() ->  shop.findself(s -> !s.queueFull())
                        .map(y ->  server(y.askToWait(customer)).noteWait(time,y,customer)))
                .orElseGet(()  ->  noteLeave(time, customer));
        } else {
            return shop.find(server  ->  server.isIdle() && !server.isResting())
                .map(server  ->  serveCustomer(time, server, customer))
                .or(() -> shop.findself(s -> s.isIdle())
                        .map(s -> serveCustomer(time,s,customer)))
                .or(() -> shop.findShortest().filter(x -> x instanceof HumanServer)
                        .map(x -> (HumanServer)x)
                            .map(server -> noteWait(time,server,customer)
                                .server(server.askToWait(customer))))
                .or(() -> shop.findShortest().filter(x -> x instanceof SelfCheckout)
                        .map(y -> (SelfCheckout)y)
                            .map(self -> noteWait(time,self,customer)
                                .server(self.askToWait(customer))))
                .orElseGet(() ->  noteLeave(time,customer));
        }
    }

    /**
     * Simulates the logic of what happened when a customer is done being
     * served by a Server. 
     * If it is a HumanServer, The server either takes a rest, serves the next customer 
     * or becomes idle.
     * If served by a SelfCheckout, the server either serves the next customer or becomes idle.
     * @param time The time the service is done.
     * @param server The server serving the customer.
     * @param customer The customer being served.
     * @return A new state of the simulation.
     */
    public SimState simulateDone(double time, Server server, Customer customer) {
        if (server instanceof HumanServer) {
            HumanServer ser = (HumanServer)server;
            return (rNG.genRandomRest() == 1) ? noteDone(time,ser,customer).makeRest(time,ser)
                : shop.find(s  ->  s.equals(ser))
                .flatMap(a  ->  a.getWaitingCustomer())//Optional customer
                .map(c  ->  noteDone(time, ser, customer).serveCustomer(time, ser, c))
                .orElseGet(()  -> noteDone(time,ser,customer).server(ser.makeIdle()));
        } else {
            SelfCheckout ser = (SelfCheckout)server;
            return shop.findself(s  ->  s.equals(ser))
                .flatMap(a  ->  a.getWaitingCustomer())
                .map(c  ->  noteDone(time, ser, customer).serveCustomer(time, ser, c))
                .orElseGet(()  -> noteDone(time,ser,customer).server(ser.makeIdle()));
        }
    }

    /**
     * Handle the logic of server serving customer.  A new done event
     * is generated and scheduled.
     * @param  time  The time this customer is served.
     * @param  server The server serving this customer.
     * @param  customer The customer being served.
     * @return A new state of the simulation.
     */
    public SimState serveCustomer(double time, Server server, Customer customer) {
        double doneTime = time + rNG.genServiceTime();
        return server(server.serve(customer))
            .noteServed(time, server, customer)
            .addEvent(doneTime, state  ->  state.simulateDone(doneTime, server, customer));
    }

    /**
     * Handles the logic of HumanServer taking a rest.
     * @param time the time the previous service was done at
     * @param s the server taking a break
     */
    public SimState makeRest(double time,HumanServer s) {
        double timeResting = time + rNG.genRestPeriod();
        HumanServer serv = s.makeRest().makeIdle();
        return shop.find(servant  ->  servant.equals(serv))
            .map(x ->  server(serv)).get()
            .addEvent(timeResting,state ->  state.endRest(timeResting,serv));
    }

    /**
     * Handles the logic of HumanServer ending rest.
     * @param time the time that the break ends at.
     * @param serv the server taking a break
     */
    public SimState endRest(double time,HumanServer serv) {
        return shop.find(s -> s.equals(serv))
            .flatMap(a  ->  a.getWaitingCustomer())//Optional customer
            .map(c  ->  server(serv.endRest()).serveCustomer(time,serv,c))
            .orElseGet(()  -> server(serv.endRest()));
    }

    /**
     * The main simulation loop.  Repeatedly get events from the event
     * queue, simulate and update the event.  Return the final simulation
     * state.
     * @return The final state of the simulation.
     * @throws Null Pointer Exception, if there is nothing in the previous event.
     */
    public SimState run() {
        Pair<Optional<Event>,SimState> s = Stream.iterate(this.nextEvent(),
            p  ->  p.first.isPresent(),
            p  ->  p.first.get().simulate(p.second).nextEvent())
            .reduce((p, q)  ->  q)
            .orElseThrow();
        return s.first.get().simulate(s.second);
    }

    /**
     * Return a string representation of the simulation state, which
     * consists of all the logs and the stats.
     * @return A string representation of the simulation.
     */
    public String toString() {
        return log + stats.toString();
    }
}
