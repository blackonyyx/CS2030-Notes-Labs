package cs2030.simulator;

/**
 * The Simulation class encapsulates information pertaining to our
 * Simulation.
 *
 * @author atharvjoshi
 * @author weitsang
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
public class Simulation {

    /** The time a server takes to serve a customer. */
    private final RandomGenerator rg;

    /** The probability of a human server resting.*/
    private final double probabilityRest;

    /** The probabability of a greedy customer.*/
    private final double greedyProb;

    /**
     * Constructor class for a simulation containing all 
     * the generative information for the simulation.*/
    public Simulation(int seedvalue, double arrivalrate, double servicerate,
            double restingrate,double probilityRest,double greedy) {
        rg = new RandomGenerator(seedvalue,arrivalrate,servicerate,restingrate);
        probabilityRest = probilityRest;
        greedyProb = greedy;
    }

    /**
     * Generates a customer type, 1 as greedy, 0 if not greedy.
     * @return integer 1 if greedy 0 if not greedy
     */
    public int genCustomerType() {
        return (rg.genCustomerType() < greedyProb) ? 1 : 0;
    }

    /**
     * Generates the arrival timings of each customer.
     * @return double representing the time of arrival of a customer.
     */
    public double genInterArrivalTime() {
        return this.rg.genInterArrivalTime();
    }

    /**
     * Generates a random rest.
     * @return a int, 1 if rg class object returns a number less than 
     *            probability of rest meaning that server rests else 0 and
     *            server continues to serve customers.
     */ 
    public int genRandomRest() {
        return (rg.genRandomRest() < probabilityRest) ? 1 : 0;
    }

    /**
     * Generates a random Rest period.
     * @return double representing the time that the server rests.
     */
    public double genRestPeriod() {
        return rg.genRestPeriod();
    }

    /**
     * generates a random Service period.
     * @return double representing the time taken by a server to serve customer.
     */
    public double genServiceTime() {
        return rg.genServiceTime();
    }
}
