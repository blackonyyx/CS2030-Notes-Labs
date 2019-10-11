import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.function.Function;
class Trace<T> {
    protected T current;
    protected final List<T> History; 
    protected Trace(T s, List<T> back){
       History = back;
       current = s;
    }
    protected Trace (T s){
        History = new ArrayList<T>();
        current = s;
    }
    @SafeVarargs
    public static <T> Trace<T> of (T seed, T... history){
        if (history == null){
            return new Trace<T>(seed);
        }else{
            return new Trace<T>(seed,new ArrayList<T>(Arrays.asList(history)));
        }
    }
    public T get(){
        return current;
    }
    public List<T> history(){
        List<T> x = deepCopy().subList(0,History.size());
        x.add(current);
        return x;
    }
    public Trace<T> back(int n){
        if (n>History.size()){
          T origin = History.get(0);
          return new Trace<T>(origin);
        }
        List<T> wayback = deepCopy().subList(0,History.size()-n);
        return new Trace<T>(History.get(History.size()-n),wayback);
    }
    public List<T> deepCopy(){
      List<T> x = new ArrayList<T>();
      for (T t: History){
        x.add(t);
      }
      return x;
    }
    @Override
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }else if(obj instanceof Trace){
            @SuppressWarnings("unchecked")
            Trace<T> t = (Trace<T>)obj;
            if (t.history().equals(this.history())){
                return true;
            }else {
                return false;
            }
        }else{

            return true;
        }
    }
  @Override
  public String toString(){
    return this.current+ " history "+ this.History;
  }


    public Trace<T> map(Function<? super T,? extends T> mapper){
      return new Trace<T>(mapper.apply(this.get()),this.history());
    }
    public Trace<T> flatMap(Function<? super T,? extends Trace<? extends T>> mapper){
      @SuppressWarnings("unchecked")
      // Trace<T> -> Trace T
      Trace<T> wrapped = (Trace<T>)mapper.apply(this.get());
      /*
      System.out.println("\ncurrent " +this);//(Trace<T>)
      System.out.println("next "+ wrapped);
      */
      List<T> lst = new ArrayList<T>();
      lst.addAll(this.history().subList(0,this.history().size()-1));
      lst.addAll(wrapped.history().subList(0,wrapped.history().size()-1));
      return new Trace<T>(wrapped.get(),lst);
    }
}
