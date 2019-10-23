package cs2030.mystream;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.*;
class MySupplier<T>{
    private Supplier<T> sup;
    private T cache;
    private boolean cached;
    MySupplier(Supplier<T> s){
        this.sup = s;
        this.cached = false;
    }
    public T get(){
        if (!cached){
            cache = sup.get();
            cached = true;
        }
        return cache;
    }
}
