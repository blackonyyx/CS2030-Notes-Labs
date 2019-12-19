package cs2030.simulator;

import java.util.LinkedList;
import java.util.Optional;

/**
 * The SelfCheckout class extends from Server and
 * keeps track of who is the customer being served (if any)
 * who is the customer waiting to be served (if any) and manages a
 * subsequent queue of waiting customers to be served.
 * Package Private class.
 * @author Stephen Tan
 */
class SelfCheckout extends Server {
    /** The queue of customers currently waiting, if any. 
     * SelfCheckouts share a single queue hence, the variable used is a class variable
     * shared amongst the SelfCheckouts*/
    private static LinkedList<Customer> waitingQueue = new LinkedList<>();

    /**
     * Creates a SelfCheckout and initalizes it with a unique id.
     */
    public SelfCheckout(int id,int capacity) {
        super(id,capacity);
    }

    /**
     * Private constructor for a SelfCheckout.
     */
    private SelfCheckout(int id,Optional<Customer> serving,int capacity) {
        super(id,serving,capacity);
    }

    /**
     * check if SelfCheckout queue  has waiting customers.
     * @return if the server has any waiting customer.
     */
    public boolean hasWaitingCustomer() {
        return Optional.ofNullable(SelfCheckout.waitingQueue.peek()).isPresent();
    }
    
    /**
     * Change this server's state to idle by removing its current customer.
     * @return A new server with the current customer removed.
     */
    public SelfCheckout makeIdle() {
        return new SelfCheckout(id,Optional.empty(),capacity);
    }

    /**
     * Checks if the queue of customers waiting for server is full.
     * @return true if a customer is waiting for given server; false otherwise.
     */
    public boolean queueFull() {
        return SelfCheckout.waitingQueue.size() >= capacity;
    }

    /**
     * Make a customer wait for this server.
     * @param customer The customer who will wait for a SelfCheckout counter.
     * @return The same SelfCheckout, for polymorphism.
     */
    public SelfCheckout askToWait(Customer customer) {
        SelfCheckout.waitingQueue.add(customer);
        return this;
    }

    /**
     * Serve a customer.
     * @param customer The customer to be served.
     * @return The new server serving this customer.
     */
    public SelfCheckout serve(Customer c) {
        Optional<Customer> nextCust = Optional.ofNullable(SelfCheckout.waitingQueue.poll());
        if (nextCust.filter(x -> x.equals(c)).isEmpty()) {
            return new SelfCheckout(this.id,Optional.of(c),capacity);
        } else {
            return new SelfCheckout(this.id,nextCust,capacity);
        }
    }

    /**
     * Returns waiting customer for given server.
     * @return customer waiting for given server.
     */
    public Optional<Customer> getWaitingCustomer() {
        return Optional.ofNullable(SelfCheckout.waitingQueue.peek());
    }

    /**
     * Checks the static variable waitingQueue of the class.
     * @return queuesize of the SelfCheckout Queue.
     */
    public int queueSize() {
        return SelfCheckout.waitingQueue.size();
    }

    /**
     * Return a string representation of this server.
     * @return A string self-check followed by the ID of the selfcheckout.
     */

    public String toString() {
        return "self-check " + Integer.toString(this.id);
    }

}

