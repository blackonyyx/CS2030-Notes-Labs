import java.util.ArrayList;
import java.util.List;
/**
 * DoneEvent class representing the customer finishing service by server.
 */

public class DoneEvent extends Event{
  /**
   * Server associated with the Event.
   */ 
  private Server server;
  /**
   * Constructor for a DoneEvent
   * @param s Server associated with the Event.
   */
  public DoneEvent(Customer cust, double t,Server s){
    super(cust,t);
    this.server = s;
  }
  /**
   * returns no event as there is no event after a done event.
   * sets up server slots for the next event in line.
   * @param s ArrayList for polymorphism
   * @return null as there is no event after this.
   */
  public Event getNextEvent(List<Server> serve){
    this.server.serveWaiting();
    return null;
  }
  /**
   * Method not in use but is implemented for polymorhism.
   */
  public void upStats(Statistics s){
    return;
  }
  /**
   * toString method for printing the string representation of the event.
   * @return String representation of event
   */
  @Override
  public String toString(){
    return String.format("%.3f",getTime()) +" "+ this.getID()+ " done serving by "+ this.server.getID();
  }
}
