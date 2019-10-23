/**
 * This class stores stats about the simulation.
 * In particular, the average waiting time, the number of customers
 * who left, and the number of customers who were served, are stored.
 *
 * @author Ooi Wei Tsang
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
class Statistics {
  /** Sum of time spent waiting for all customers. */
  private final double totalWaitingTime;

  /** Total number of customers who were served. */
  private final int totalNumOfServedCustomers;

  /** Total number of customers who left without being served. */
  private final int totalNumOfLostCustomers;
  private final int custs;
  /**
   * Construct an Statistics object with 0 waiting time, 0
   * served customer, and 0 lost customer.
   * @return A new Statistics object 
   */
  public Statistics() {
    this.totalWaitingTime = 0;
    this.totalNumOfServedCustomers = 0;
    this.totalNumOfLostCustomers = 0;
    this.custs = 0;
  }
  private Statistics(double w,int ns,int nl, int t){
    this.totalWaitingTime = w;
    this.totalNumOfServedCustomers = ns;
    this.totalNumOfLostCustomers = nl;
    this.custs = t;
  }
  public Statistics makeChange(double w,int s,int l,int t){
      return new Statistics(w,s,l,t);
  }
  public Statistics clone(){
    return new Statistics(totalWaitingTime,totalNumOfServedCustomers,totalNumOfLostCustomers,custs);
  }
  /**
   * Mark that a customer is served.
   * @return A new Statistics object with updated stats
   */
  public Statistics newCust(){
    return makeChange(totalWaitingTime,totalNumOfServedCustomers,totalNumOfLostCustomers,custs+1);
  }
  public Statistics serveOneCustomer() {
    return makeChange(totalWaitingTime,totalNumOfServedCustomers+1,totalNumOfLostCustomers,custs);
  }

  /**
   * Mark that a customer is lost.
   * @return A new Statistics object with updated stats
   */
  public Statistics looseOneCustomer() {
    return makeChange(totalWaitingTime,totalNumOfServedCustomers,totalNumOfLostCustomers+1,custs);
  }

  /**
   * Accumulate the waiting time of a customer.
   * @param time The time a customer waited.
   * @return A new Statistics object with updated stats
   */
  public Statistics recordWaitingTime(double time) {
    return makeChange(totalWaitingTime+time,totalNumOfServedCustomers,totalNumOfLostCustomers,custs);
  }
  public int getID(){
    return this.custs;
  }
  /**
   * Return a string representation of the staistics collected.
   * @return A string containing three numbers: the average
   *     waiting time, followed by the number of served customer,
   *     followed by the number of lost customer.
   */
  public String toString() {
    return String.format("[%.3f %d %d]",
        totalWaitingTime / totalNumOfServedCustomers,
        totalNumOfServedCustomers, totalNumOfLostCustomers);
  }
}
