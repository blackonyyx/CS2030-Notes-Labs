import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Lazy<T>{
    Optional<T> value;
    Supplier<Optional<T>> supplier;
    T t;
    public Lazy( T val){
        value = Optional.of(val);
    }
    public Lazy(Supplier<T> s){
        value = Optional.empty();
        supplier = ()->Optional.ofNullable(s.get());
    }
    public static <T> Lazy<T> of(T val){
        return new Lazy<T>(val);
    }
    public static <T> Lazy<T> of(Supplier<T> sup){
        return new Lazy<T>(sup);
    }
    public T get(){
        if (value.equals(Optional.empty())){
            value = supplier.get();
        }
        return value.orElse(t);
    }

    public <U>Lazy<U> map(Function<? super T,? extends U> fn){
        return new Lazy<U>(()-> fn.apply(this.get()));
    }
    public <U> Lazy<U> flatMap(Function<? super T,? extends Lazy<? extends U>> mapper ){
        return new Lazy<U>(()-> mapper.apply(this.get()).get());
    }
    @Override
    public String toString(){
        return value.map(x->x.toString()).orElseGet(()->"?");
    }
}
