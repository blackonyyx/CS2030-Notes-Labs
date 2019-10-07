/**
 * Customer class holding customer information
 */
class Customer {
  /**
   * Counter to set id of Customers.
   */
  private static int CUSTOMERS = 0;
  /**
   * Time of arrival of the customer.
   */
  public final double arrive;
  /**
   * Unique immutable id of the customer.
   */
  public final int id;
  /**
   * Takes in a double as a start time and constructs a customer with a unique id.
   * @param arrival the time that customer arrives at.
   */
  public Customer(double arrival){
    Customer.CUSTOMERS++;
    this.arrive = arrival;
    this.id = CUSTOMERS;
  }
  /**
   * Getter for arrival time.
   */
  public double getArrival(){
    return arrive;
  }
  /**
   * Getter for unique id of customer.
   */
  public int getID(){
    return id;
  }
}

