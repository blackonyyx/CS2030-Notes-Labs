/**
 * Statistics class to keep track of whats happening in the simulation.
 * Currently tracking:
 * the average waiting time for customers who have been served
 * the number of customers served
 * the number of customers who left without being served
 */

class Statistics{
  private double waitTime = 0;
  private int left = 0;
  private int served = 0;
  /**
   * All statistics start with 0
   */
  public Statistics(){}
  /**
   * Increases the number of served customers
   */
  public void servedCustomer(){
    served++;
  }
  /**
   * Increases the number of customers who left
   */
  public void leftCustomer(){
    left++;
  }
  /**
   * Increases the total amount of time waited by customers
   */
  public void waited(double t){
    waitTime += t;
  }
  /**
   * Format the printout of the Statistics.
   */
  public String toString(){
    double avgWait = waitTime/served;
    return String.format("[%.3f %d %d]",avgWait,served,left);
  }
}
