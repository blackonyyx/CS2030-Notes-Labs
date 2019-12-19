import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LazyList<T extends Comparable<T>> {
    List<Lazy<T>> list;
    private LazyList(List<Lazy<T>> list,int len) {
        this.list = list;
    }

    static <T extends Comparable<T>> LazyList<T> generate(int n, T seed, UnaryOperator<T> f) {
        List<Lazy<T>> l= Stream.iterate(new Lazy<T>(seed), x -> x.map(f))
                .limit(n)
                .collect(Collectors.toList());
        return new LazyList<T>(l,n);

    }

    public T get(int i) {
        return this.list.get(i).get();
    }

    public int indexOf(T v) {
        return this.list.indexOf(Lazy.of(v));
    }
}
