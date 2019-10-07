import java.util.ArrayList;
import java.util.List;
/**
 * Arrival event created at initialisation of simulation. 
 * inherits from event class
 */
public class ArrivalEvent extends Event{
  /**
   * Constructor for Event class
   * @param cust Customer involved in event.
   * @param t time of occurance of event
   */
  public ArrivalEvent(Customer cust, double t){
    super(cust,t);
  }
  /**
     * Creates the next event based on the availability of servers.
     * The available server will be updated to hold a field of the event
     * and be involved in the creation of new event.
     * @param s ArrayList of servers to be checked
     * @return parent class Event, which could be in the form of
     * LeaveEvent, if there are no servers.
     * ServedEvent, if there exists an server that is completely free.
     * WaitEvent, if there exists server and there are no empty servers.
     */
  public Event getNextEvent(List<Server> serve){
    Server freeServer = getAvailableServer(serve);
    if (freeServer == null){
      return createLeave();
    }else if(freeServer.canServe()){
      Event newEvent = createServe(freeServer);
      freeServer.setService(newEvent);
      return newEvent;
    }else if (freeServer.canWait()){
      Event newEvent = createWait(freeServer);
      freeServer.setWaiting(newEvent);
      return newEvent;
    }else{
      return null;
    }
  }
  /**
   * Method to create a ServedEvent class associated with a idle server
   * @param s an empty server able to serve the customer.
   * @return ServedEvent representing the change in the state of the customer.
   */
  public ServedEvent createServe(Server s){
    return new ServedEvent(this.getCustomer(), this.getTime(),s);
  }
   /**
   * Method to create a WaitEvent class associated with a serving server
   * @param s an server with no waiting customer.
   * @return WaitEvent representing the change in the state of the customer to wait
   */
  public WaitEvent createWait(Server s) {
    return new WaitEvent(this.getCustomer(),this.getTime(),s);
  }
   /**
   * Method to create a LeaveEvent class 
   * @return LeaveEvent representing the change in the state of the customer to leave.
   */
  public LeaveEvent createLeave(){
    return new LeaveEvent(this.getCustomer(), this.getTime());
  }
  /**
   * Iterative step to find an empty server in the list of servers
   * @param serve a list of servers from Simulation class
   * @return s a server that is free or returns null if no such server is found.
   */
  public Server getAvailableServer(List<Server> serve){
    Server serving = null;
    for (Server s: serve){
      if (s.canServe()){
        return s;
      }
    }
    for (Server s: serve){
      if (s.canWait()){
        return s;
      }
    }
    return serving;
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
    return String.format("%.3f",getTime()) +" "+ this.getID()+ " arrives";
  }
}
