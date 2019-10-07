import java.util.ArrayList;
import java.util.List;
/**
 * ServedEvent class representing the customer being served by server.
 */
public class ServedEvent extends Event{
  /**
   * Server associated with the Event.
   */
  private Server server;
  /**
   * Constructor for a ServedEvent
   * @param s Server associated with the Event.
   */
  public ServedEvent(Customer cust, double t,Server s){
    super(cust,t);
    server = s;
  }
  /**
     * Creates the Done event from either a WaitEvent or a ArrivalEvent
     * @param s for polymorhism in Simulation.
     * @return  DoneEvent taking the done time of service by the server as the time of event.
     */
  public DoneEvent getNextEvent(List<Server> serve){
    DoneEvent newEvent = new DoneEvent(this.getCustomer(),this.server.getDoneTime(),this.server);
    this.server.setService(newEvent);
    return newEvent;
  }
  /**
   * Updating statistics of served customer. 
   * through comparing the arrival time of customer to the time of service, ie this event's time.
   */
  public void upStats(Statistics s){
    s.servedCustomer();
    s.waited(this.getTime()-this.getCustomer().getArrival());
  }
  /**
   * toString method for printing the string representation of the event.
   * @return String representation of event
   */
  @Override
  public String toString(){
    return String.format("%.3f",getTime()) +" "+ this.getID()+ " served by "+server.getID();
  }
}
