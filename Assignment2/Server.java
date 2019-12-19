package cs2030.simulator;

import java.util.Optional;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The Server class keeps track of who is the customer being served (if any)
 * and who is the customer waiting to be served (if any).
 *
 * @author Stephen Tan 
 */
abstract class Server {
    /** The unique ID of this server. */
    protected final int id;
    /**
     * The capacity of the queue of customers to wait.
     */
    protected final int capacity;
    /** The Customer currently being served, if any. */
    protected Optional<Customer> currentCustomer;

    /**
     * Creates a server and initalizes it with a unique id.
     */
    public Server(int id,int capacity) {
        this.currentCustomer = Optional.empty();
        this.capacity = capacity;
        this.id = id;
    }

    /**
     * Private constructor for a server.
     */
    protected Server(int id, Optional<Customer> currentCustomer,int capacity) {
        this.id = id;
        this.currentCustomer = currentCustomer;
        this.capacity = capacity;
    }

    /**
     * Checks if the current server is idle.
     * @return true if the server is idle (no current customer); false otherwise.
     */
    public boolean isIdle() {
        return !this.currentCustomer.isPresent();
    }

    /**
     * Abstract method for inheriting classes to check if it has waiting customers.
     * @return if the server has any waiting customer.
     */
    public abstract boolean hasWaitingCustomer();
    /**
     * Abstract method for inheriting classes to check if customer queue has reached full capacity.
     * @return if the server queue has reached full capacity.
     */

    public abstract boolean queueFull();

    /**
     * Returns waiting customer for given server.
     * @return customer waiting for given server.
     */
    public abstract Optional<Customer> getWaitingCustomer(); 

    /**
     * Serve a customer.
     * @param customer The customer to be served.
     * @return The new server serving this customer.
     */
    public abstract Server serve(Customer customer); 

    /**
     * Make a customer wait for this server.
     * @param customer The customer who will wait for this server.
     * @return The new server with a waiting customer.
     */
    public abstract Server askToWait(Customer customer);
    /**
     * Abstract methd to check the current size of the queue of waiting customers.
     * @return queuesize of the server.
     */

    public abstract int queueSize();

    /**
     * Return a string representation of this server.
     * @return A string S followed by the ID of the server, followed by the
     *     waiting customer.
     */
    public String toString() {
        return Integer.toString(this.id);
        // return "" + this.id + " (Q: " + waitingCustomer.map(c -> c.toString()).orElse("-") + ")";
    }

    /**
     * Checks if two servers have the same id.
     * @param  obj Another objects to compared against.
     * @return  true if obj is a server with the same id; false otherwise.
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Server)) {
            return false;
        }
        return (this.id == ((Server)obj).id);
    }

    /**
     * Return the hashcode for this server.
     * @return the ID of this server as its hashcode.
     */
    public int hashCode() {
        return this.id;
    }
}
