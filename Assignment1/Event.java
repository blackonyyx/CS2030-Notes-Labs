import java.util.ArrayList;
import java.util.List;
/**
 * Abstract Event to have polymorphism amongst events.
 * Solid classes inheriting from Events can create new events and update statistics
 */
public abstract class Event{
  /** 
   * All events involve a customer
   */
  protected final Customer c;
  /**
   * Instance of time at which event is occuring.
   * Could be due to either waiting for service or service duration
   */
  protected final double time;
  /**
   * Constructor for Event class
   * @param Customer customer involved in event.
   * @param time of occurance of event
   */
  public Event(Customer cust, double t){
    this.c = cust;
    this.time = t;
  }
  /**
   * Abstract class that denotes the updating of statistics to be tracked at certain events
   */
  public abstract void upStats(Statistics s);
  /**
   * Abstract clas denoting the sequence and chaining of events
   */
  public abstract Event getNextEvent(List<Server> s);
  /**
   * Getter for customer object associated with the event.
   * @return customer
   */
  public Customer getCustomer(){
    return this.c;
  }
  /**
   * getter for the id of customer pertaining to the event.
   * @return customer id
   */
  public int getID(){
    return this.c.getID();
  }
  /**
   * Getter for time of the event.
   * @return the time at which the event is taking place
   */
  public double getTime(){
    return this.time;
  }
}
