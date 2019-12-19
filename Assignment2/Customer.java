package cs2030.simulator;

/**
 * The Customer class encapsulates information and methods pertaining to a
 * Customer in a simulation.  
 *
 * @author Stephen Tan
 */
class Customer {
    /** The unique ID of this customer. */
    private final int id;

    /** The time this customer arrives. */
    private double timeArrived;
    /** Boolean to check id the customer is greedy.*/
    private final boolean greedy;
    /**
     * Create and initalize a new customer.
     * The {@code id} of the customer is set.
     *
     * @param timeArrived The time this customer arrived in the simulation.
     */

    public Customer(double timeArrived, int id, int greed) {
        this.timeArrived = timeArrived;
        this.id = id;
        if (greed == 1) {
            this.greedy = true;
        } else {
            this.greedy = false;
        }
    } 
    /**
     * Checks if the customer is a greedy customer.
     * @return true if greedy or else false.
     */

    public boolean isGreedy() {
        return greedy;
    }
    /**
     * Return the waiting time of this customer.
     * @return The waiting time of this customer.
     */

    public double timeWaited(double t) {
        return t - timeArrived;
    }

    /**
     * Return a string representation of this customer.
     * @return The id of the customer prefixed with "C"
     */
    public String toString() {
        if (greedy == true) {
            return Integer.toString(id) + "(greedy)";
        } else {
            return Integer.toString(this.id);
        }
    }

    /**
     * Checks the object and compares it to the customer.
     * @param obj an Object class.
     * @return boolean check if the object compared to this is the same customer 
     */

    @Override

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } 
        if (obj instanceof Customer) {
            Customer c = (Customer) obj;
            return c.id == this.id;
        }
        return false;
    }
    /**
     * Overrides the hashcode function to use unique customer id as the hashcode.
     */

    @Override

    public int hashCode() {
        return id;
    }
}
