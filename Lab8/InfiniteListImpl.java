package cs2030.mystream;

import java.util.function.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;
/**
 * Implementation Class for IFL interface to function.
 */
public class InfiniteListImpl<T> implements InfiniteList<T>{
  /**
   * Wrapper MySupplier to allow for caching.
   * head -> a supplier for lazy evaluation that returns the current value.
   * tail -> the continuation of the stream using tail recursion.
   */
  private final MySupplier<Optional<T>> head;
  private final MySupplier<InfiniteListImpl<T>> tail;
  protected InfiniteListImpl( Supplier <Optional<T>> head, Supplier<InfiniteListImpl<T>> tail){
    this.head = new MySupplier<Optional<T>>(head);
    this.tail = new MySupplier<InfiniteListImpl<T>>(tail);
  }
  protected InfiniteListImpl(Optional<T> head,Supplier<InfiniteListImpl<T>> tail){ 
       this.head = new MySupplier<Optional<T>>(() -> head);
       this.tail = new MySupplier<InfiniteListImpl<T>>(tail);
  }
  
  public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> sup){
    return new InfiniteListImpl<T>(()->Optional.of(sup.get()),
                          ()-> InfiniteListImpl.generate(sup));
  }
  public static <T> InfiniteListImpl<T> iterate(T seed, Function<? super T,? extends T> next){
    return new InfiniteListImpl<T>(Optional.of(seed), 
                                   ()-> InfiniteListImpl.iterate(next.apply(seed),next));
  }
  public InfiniteListImpl<T> filter(Predicate<? super T> pred){
    return new InfiniteListImpl<T>(()->head.get().filter(pred),
                                   ()-> tail.get().filter(pred));
  }
  public <R> InfiniteListImpl<R> map(Function<? super T,? extends R> mapper){
      return new InfiniteListImpl<R>(()->head.get().map(mapper),
                                     ()->tail.get().map(mapper));
  }
  public void forEach(Consumer <? super T> c){
      InfiniteListImpl<T> curr = this;
      while(!curr.isEmptyList()){
          curr.head.get().ifPresent(c);
          curr = curr.tail.get();
      }
  }
  public long count(){
    return this.toArray().length;
  }
  public Object[] toArray(){
      InfiniteListImpl<T> curr = this;
      List<Object> l = new ArrayList<>();
      while(!curr.isEmptyList()){
          if (curr.head.get().isPresent()){
            l.add(curr.head.get().get());
          }
          curr = curr.tail.get();
      }
      return l.toArray();
  } 
  public InfiniteListImpl<T> limit(long n){
    return new InfiniteListImpl<T>(()-> {
                    if (n <= 0){
                        return Optional.empty();
                    }else {
                        return this.head.get();
                    }}, ()-> {
                      if (n<=0||(n==1&&this.head.get().isPresent())){
                        return new EmptyList<T>();
                      }else{
                          InfiniteListImpl<T> myTail = this.tail.get();
                          if(myTail.isEmptyList()){
                              return myTail;
                          }else{
                              return myTail.limit(n-(this.head.get().isPresent()? 1 : 0));}
                      }});
  }
  public InfiniteListImpl<T> takeWhile(Predicate<? super T> pred){
      MySupplier<Optional<T>> tester  = new MySupplier<Optional<T>>(()->head.get().filter(pred));
      return new InfiniteListImpl<T>(()-> {
                                          if (tester.get().isPresent()){
                                               return head.get();
                                          }else {
                                            return Optional.empty();
                                          }}, ()-> {
                                            if (this.head.get().isPresent()){
                                              if(tester.get().isEmpty()){
                                                return new EmptyList<T>();
                                              }
                                            }
                                          InfiniteListImpl<T> myTail = tail.get();
                                            if(myTail.isEmptyList()){
                                              return myTail;
                                            }else{
                                              return myTail.takeWhile(pred);}});
      }
  public InfiniteListImpl<T> get(){
      if (this.head.get().isPresent()){
          System.out.println(this.head.get().get());
      }
      return this.tail.get();
  }
  

    public Optional<T> reduce (BinaryOperator<T> accumulator){
      InfiniteListImpl<T> curr = this;
      Optional<T> reducer = head.get();
      while (reducer.isEmpty()&& !curr.isEmptyList()){
        curr = curr.tail.get();
        reducer = curr.head.get();
      }
      while(!curr.isEmptyList()&&reducer.isPresent()){
        curr = curr.tail.get();
        if (curr.head.get().isPresent()){
          reducer= Optional.ofNullable(accumulator.apply(reducer.get(),curr.head.get().get()));
        }
      }
      return reducer;
  }

  public <U> U reduce ( U identity, BiFunction<U,? super T, U> accumulator){
      InfiniteListImpl<T> curr = this;
      U reduction = identity;
      while(!curr.isEmptyList()){
        if (curr.head.get().isPresent()){
          reduction= accumulator.apply(reduction,curr.head.get().get());
        }
          curr = curr.tail.get();
      }
      return reduction;
  }
   public boolean isEmptyList(){
       return this instanceof EmptyList;
   }
}
