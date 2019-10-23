package cs2030.mystream;

import java.util.Optional;
import java.util.function.*;
class EmptyList<T> extends InfiniteListImpl<T>{
    EmptyList(){
        super(()->Optional.empty(),()->new EmptyList<T>());
    }
    public <R> EmptyList<R> map(Function<? super T,? extends R> mapper){
      return new EmptyList<R>();
    }
    public EmptyList<T> filter(Predicate<? super T> pred){
      return new EmptyList<T>();
    }
    public EmptyList<T> limit(long n){
      return new EmptyList<T>();
    }

}
