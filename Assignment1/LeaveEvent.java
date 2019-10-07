import java.util.ArrayList;
import java.util.List;
/**
 * LeaveEvent class representing the customer not being served.
 */

public class LeaveEvent extends Event{
  /**
   * Constructor for a LeaveEvent
   */
  public LeaveEvent(Customer cust, double t){
    super(cust,t);
  }
  /**
   * returns no event as there is no event after a leave event.
   * @param s ArrayList for polymorphism
   * @return null as there is no event after this.
   */
  public Event getNextEvent(List<Server> serve){
      return null;
  }
  /**
   * Method not in use but is implemented for polymorhism.
   */
  public void upStats(Statistics s){
    s.leftCustomer();
    return;
  }
  /**
   * toString method for printing the string representation of the event.
   * @return String representation of event
   */
  @Override
  public String toString(){
    return String.format("%.3f",getTime()) +" "+ this.getID()+ " leaves";
  }
}
