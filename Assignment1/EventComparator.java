import java.util.Comparator;
/**
 * Creates a basis of comparison for Events in the priorityqueue.
 */
class EventComparator implements Comparator<Event>{
     /**
     * Compares 2 Events and decides which is smaller, equal or greater.
     * The first priority is to check for earlier time.
     * tie breaker, customer ID is checked instead, 
     * which also hints on the priority of different type of events.
     * @param e1 left event
     * @param e2 right event
     * @return -1 if left event is prioritised over right event. 
     * 0 if there isn't a priority, which will not happen in this case.
     * 1 if right event is prioritised over left event.
     */
  @Override 
  public int compare(Event e1, Event e2){
    if(e1.getTime() < e2.getTime()){
      return -1;
    }else if (e1.getTime() > e2.getTime()){
      return 1;
    }else if(e1.getID() <e2.getID()){
      return -1;
    }else if(e1.getID()>e2.getID()){
      return 1;
    }else{
      return 0;
    }
  }
}

