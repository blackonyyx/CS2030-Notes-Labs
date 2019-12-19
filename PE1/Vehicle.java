import java.util.ArrayList;
import java.util.List;
public abstract class Vehicle{
    public String id;
    public int time;
    public List<Service> s = new ArrayList<>();
    public Vehicle(String identity, int t){
        this.id = identity;
        this.time = t;
    }
    public String getID(){
        return id;
    }
    public int getTime(){
        return time;
    }
    //public abstract boolean canService(Sercice s);
    public abstract String getType();
    @Override
    public String toString(){
        return getID() + " (" + getTime()+ " mins away)";
    }
}
