import java.util.ArrayList;
import java.util.List;

public class NormalCab extends Vehicle{

    public String type;
    public NormalCab(String identity, int t){
        super(identity, t);
        type = "NormalCab";
        s.add(new JustRide());
        s.add(new TakeACab());
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

