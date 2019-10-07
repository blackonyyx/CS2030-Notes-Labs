import java.util.ArrayList;
import java.util.List;
/**
 * ServedEvent class representing the customer being served by server.
 */
public class WaitEvent extends Event{
  /**
   * Server associated with the Event.
   */
  private Server server;
  /**
   * Constructor for a WaitEvent
   * @param s Server associated with the Event.
   */
  public WaitEvent(Customer cust, double t,Server s){
    super(cust,t);
    this.server = s;
  }
  /**
     * Creates the ServedEvent based on the availability of the server in the class.
     * Sets the ServedEvent inside the Server
     * @param s for polymorphism in Simulation. 
     * @return ServedEvent taking the time of the DoneEvent inside Server
     */
  public ServedEvent getNextEvent(List<Server> serve){
    if (!this.server.canWait()){
      // wait event transfered over to serving slot in server
      ServedEvent newEvent = new ServedEvent(this.getCustomer(),server.getWaitTime(),server);
      this.server.setWaiting(newEvent);
      return newEvent;
    }else{
      return null;
    }
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
    return String.format("%.3f",getTime()) +" "+ this.getID()+ " waits to be served by "+server.getID();
  }
}
