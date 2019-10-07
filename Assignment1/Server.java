import java.util.List;
import java.util.ArrayList;
/**
 * Server class that changes the state of events based on the slots they have for waiting and serving.
 * Customers will either wait, be served, or leave based on the server's availibility.
 */
class Server{
  /**
   * Event representing a server serving a customer.
   */
  private Event serving;
  /**
   * Event representing a customer waiting for service from a busy server.
   */
  private Event waiting;
  /**
   * ID tracker to give each Server a unique id to track.
   */
  private static int TOTALNUMID = 0;
  /**
   * Unique ID of the single server, for identification.
   */
  private final int id;
  /**
   * Constant variable for the time taken for service of a single customer.
   */
  private static final double SERVICE = 1;
  /**
   * Creates an idle Server class with open slots for any Event.
   */
  Server(){
    serving = null;
    waiting = null;
    Server.TOTALNUMID++;
    id = TOTALNUMID;
  }
  /**
   * Sets an event of a customer being served by this server
   * Could also be updating of service status of customer in the first slot waiting-> served
   * @param e The service state of the customer being served.
   */
  public void setService(Event e){
    serving = e;
  }
   /**
   * Sets an event of a customer waiting for service by this server
   * @param e The service state of the customer waiting.
   */ 
  public void setWaiting(Event e){
    waiting = e;
  }
  /**
   * Getter for unique id of server
   * @return id of server
   */
  public int getID(){
    return this.id;
  }
  /**
   * Getter for the Service time of the server.
   * @return static constant for service time of server
   */
  public double getServiceTime(){
    return Server.SERVICE;
  }
  /**
   * Boolean to check if the server is free and able to serve a new customer.
   * (only applicable for first service or if no customer comes in the time between this service and done event)
   * @return boolean
   */
  public boolean canServe(){
    return (serving == null&& waiting == null);
  }
  /**
   * Boolean to check if server is not free, but has a empty waiting slot.
   * @return boolean
   */
  public boolean canWait(){
    return (serving != null&&waiting == null);
  }
  /**
   * Getter that accesses the service time of currently served customer (done event)
   * @return double representing ending time of service of served customer
   */
  public double getWaitTime(){
    return this.serving.getTime();
  }
  /**
   * Moves the waiting Customer Event to the serving customer slot.
   * used by a done event, to initiate the service of the next customer
   */
  public void serveWaiting(){
    if (serving!=null){
      serving =null;
    }
    if (waiting!= null){
      serving = waiting;
      waiting = null;
    }
  }
  /**
   * Calculates the time taken for service to be complete.
   * @return double representing the time at which service is completed.
   */
  public double getDoneTime(){
    return getServiceTime() + serving.getTime();
  }
}
