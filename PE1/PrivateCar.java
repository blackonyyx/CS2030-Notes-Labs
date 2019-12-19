import java.util.ArrayList;
import java.util.List;
public class PrivateCar extends Vehicle{
    public String type;
    public PrivateCar(String identity, int t){
        super(identity, t);
        type = "PrivateCar";
//        s.add(new JustRide());
//        s.add(new ShareARide());
    }
    public String getType(){
        return type;
    }
    @Override
    public String toString(){
        String s = super.toString();
        return s+ " " + getType();
    }
}

