import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Lazy<T>{
    Optional<T> value;
    Supplier<Optional<T>> supplier;
    public Lazy( T val){
        value = Optional.of(val);
    }
    public Lazy(Supplier<T> s){
        value = Optional.empty();
        supplier = ()->Optional.of(s.get());
    }
    public static <T>Lazy<T> of(T val){
        return new Lazy<T>(val);
    }
    public static <T>Lazy<T> of(Supplier<T> sup){
        return new Lazy<T>(sup);
    }
    public T get(){
        if (value.equals(Optional.empty())){
            value = supplier.get();
        }
        return value.orElseThrow();
    }
    
    @Override
    public String toString(){
        return value.map(x->x.toString()).orElseGet(()->"?");
    }
}
