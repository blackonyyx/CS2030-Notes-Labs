import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.function.Function;

class ChildTrace<T> extends Trace<T>{

  private ChildTrace(T s, List<T> back){
    super(s,back);
  }
  private ChildTrace(T s){
    super(s);
  }
  @SafeVarargs
  public static <T> ChildTrace<T> of (T seed, T... history){
        if (history == null){
            return new ChildTrace<T>(seed);
        }else{
            return new ChildTrace<T>(seed, new ArrayList<T>(Arrays.asList(history)));
        }
    }
}
