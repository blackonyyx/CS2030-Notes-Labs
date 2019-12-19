package cs2030.simulator;

import java.util.Optional;
import java.util.LinkedList;
/**
 * The HumanServer class extends from Server and
 * keeps track of who is the customer being served (if any)
 * who is the customer waiting to be served (if any) and manages a 
 * subsequent queue of waiting customers to be served.
 *
 * @author Stephen Tan
 */

class HumanServer extends Server {
    /** The state of the Server if it is resting.*/
    private boolean resting;

    /** The queue of customers currently waiting, if any. */
    private LinkedList<Customer> waitingCustomers; 

    /**
     * Creates a server and initalizes it with a unique id.
     */
    public HumanServer(int id,int capacity) {
        super(id,capacity);
        this.waitingCustomers = new LinkedList<>();
        this.resting = false;
    }

    /**
     * Private constructor for a server.
     */
    private HumanServer(int id, Optional<Customer> currentCustomer,
            LinkedList<Customer> waitingCustomer, int capacity,boolean rest) {
        super(id,currentCustomer,capacity);
        this.waitingCustomers = waitingCustomer;
        this.resting = rest;
    }

    /**
     * Change this server's state to idle by removing its current customer.
     * @return A new server with the current customer removed.
     */
    public HumanServer makeIdle() {
        return new HumanServer(id, Optional.empty(),waitingCustomers,capacity,resting);
    }
    /**
     * Checks if the current server is idle.
     * @return true if the server is idle (no current customer); false otherwise.
     */

    public boolean isIdle() {
        return !this.currentCustomer.isPresent();
    }

    /**
     * Boolean check for any waiting customer in the queue.
     * @return if the server has any waiting customer.
     */
    public boolean hasWaitingCustomer() {
        return Optional.ofNullable(waitingCustomers.peek()).isPresent();
    }

    /**
     * Checks if the queue of customers waiting for server is full.
     * @return true if a customer is waiting for given server; false otherwise.
     */
    public boolean queueFull() {
        return this.waitingCustomers.size() >= capacity;
    }

    /**
     * Changes the state of this server to resting.
     * @return new HumanServer equivelent to this one, with rest state changed to true.
     */
    public HumanServer makeRest() {
        return new HumanServer(id,this.currentCustomer,waitingCustomers,capacity,true);
    }

    /**
     * checks if server is currently resting.
     * @return boolean true if resting else false.
     */
    public boolean isResting() {
        return resting;
    }

    /**
     * Changes the state of the HumanServer to end the resting state.
     * @return new HumanServer equivelent to this one, with rest state changed to false.
     */
    public HumanServer endRest() {
        return new HumanServer(id,this.currentCustomer,waitingCustomers,capacity,false);
    }

    /**
     * Returns waiting customer for given server if any.
     * @return customer waiting for given server else retuns a Optional.empty().
     */
    public Optional<Customer> getWaitingCustomer() {
        return Optional.ofNullable(waitingCustomers.peek());
    }

    /**
     * Serve a customer.
     * @param customer The customer to be served.
     * @return The new server serving this customer.
     */
    public HumanServer serve(Customer customer) {
        Optional<Customer> nextCust = Optional.ofNullable(waitingCustomers.poll());
        if (nextCust.filter(c -> c.equals(customer)).isEmpty()) {
            return new HumanServer(this.id, Optional.of(customer),
                    waitingCustomers,capacity,resting);
        } else {
            return new HumanServer(this.id,nextCust,waitingCustomers,capacity,resting);
        }
    }

    /**
     * Make a customer wait for this server.
     * @param customer The customer who will wait for this server.
     * @return The new server with a waiting customer.
     */
    public HumanServer askToWait(Customer customer) {
        waitingCustomers.add(customer);
        return new HumanServer(id,currentCustomer,waitingCustomers,capacity,resting);
    }

    /**
     * Return a string representation of this server.
     * @return A string server followed by the ID of the server.
     */
    public String toString() {
        return "server " + Integer.toString(this.id);
    }

    /**
     * Checks the queue size of the server.
     * @return queuesize of the server.
     */
    public int queueSize() {
        return waitingCustomers.size();
    }

}
