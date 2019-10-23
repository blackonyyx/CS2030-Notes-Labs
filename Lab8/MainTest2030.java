import cs2030.mystream.InfiniteList;
import java.util.*;
import java.util.function.*;

class MainTest2030 {
    
    private static class IncrementalGenerator<T> implements Supplier<T> {
        private T curr;
        private UnaryOperator<T> next;
        public IncrementalGenerator(T seed, UnaryOperator<T> next) {
            this.curr = seed;
            this.next = next;
        }
        public T get() {
            return curr = next.apply(curr);
        }
    }
    
    private static class RandomGenerator implements Supplier<Integer> {
        private Random rng;
        public RandomGenerator(long seed) {
            this.rng = new Random(seed);
        }
        public Integer get() {
            return rng.nextInt(1000);
        }
    }
    
    private static class InfiniteListGrader<T> {
        private static int id = 0;
        private InfiniteList<T> list;
        
        public InfiniteListGrader(InfiniteList<T> list) {
            this.list = list;
        }
        
        public static <T> InfiniteListGrader<T> generate(Supplier<T> s) {
            System.out.println("Test " + ++id);
            return new InfiniteListGrader<T>(InfiniteList.generate(() -> {
                T tmp = s.get();
                System.out.println("generate: " + tmp);
                return tmp;
            }));
        }

        public static <T> InfiniteListGrader<T> iterate(T seed, UnaryOperator<T> next) {
            System.out.println("Test " + ++id);
            return new InfiniteListGrader<T>(InfiniteList.iterate(seed, old -> {
                T tmp = next.apply(old);
                System.out.println("iterate: " + old + " -> " + tmp);
                return tmp;
            }));
        }
        public <R> InfiniteListGrader<R> map(Function<? super T, ? extends R> mapper) {
            return new InfiniteListGrader<R>(list.map(x -> {
                R tmp = mapper.apply(x);
                System.out.println("map: " + x + " -> " + tmp);
                return tmp;
            }));
        }
        public InfiniteListGrader<T> filter(Predicate<T> predicate) {
            return new InfiniteListGrader<T>(list.filter(x -> {
                boolean tmp = predicate.test(x);
                System.out.println("filter: " + x + " -> " + tmp);
                return tmp;
            }));
        }
        public InfiniteListGrader<T> takeWhile(Predicate<T> predicate) {
            return new InfiniteListGrader<T>(list.takeWhile(x -> {
                boolean tmp = predicate.test(x);
                System.out.println("takeWhile: " + x + " -> " + tmp);
                return tmp;
            }));
        }
        public InfiniteListGrader<T> limit(int n) {
            return new InfiniteListGrader<T>(list.limit(n));
        }
        public long count() {
            return list.count();
        }
        public void forEach() {
            list.forEach(x -> {
                System.out.println("forEach: " + x);
            });
        }
        public T reduce(T identity, BinaryOperator<T> accumulator) {
            return list.reduce(identity, accumulator);
        }
        public Optional<T> reduce(BinaryOperator<T> accumulator) {
            return list.reduce(accumulator);
        }
        public Object[] toArray() {
            return list.toArray();
        }
    }

    public static void main(String[] args) {
/*
        // generate() test (test #1)
        //InfiniteListGrader.generate(() -> 1);
        
        // iterate() test (test #2)
        //InfiniteListGrader.iterate(0, x -> x + 1);
        
        // limit() test (test #3)
        InfiniteListGrader.iterate(0, x -> x + 1).limit(10);
        InfiniteListGrader.iterate(0, x -> x + 1).limit(0);*/
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).limit(10);
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).limit(0);
        /*
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).limit(1).count());
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).limit(4).count());
        InfiniteListGrader.iterate(0, x -> x + 1).limit(4).forEach();
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).limit(2).limit(4).count());
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).limit(0).count());
        InfiniteListGrader.iterate(0, x -> x + 1).limit(2).limit(4).forEach();
        InfiniteListGrader.iterate(0, x -> x + 123).limit(2).limit(4).forEach();
        InfiniteListGrader.iterate(0, x -> x + 1).limit(2).limit(0).forEach();
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).limit(2).limit(0).forEach();
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).limit(2).limit(4).forEach();
        InfiniteListGrader.generate(new RandomGenerator(12345)).limit(100).forEach();
        System.out.println(Arrays.asList(InfiniteListGrader.iterate(0, x -> x + 1).limit(5).toArray()));
        
        // takeWhile() test (test #19)
        InfiniteListGrader.iterate(0, x -> x + 1).takeWhile(x -> x < 2);
        InfiniteListGrader.iterate(0, x -> x + 1).takeWhile(x -> x < 1);
        InfiniteListGrader.iterate(0, x -> x + 1).takeWhile(x -> x < 0);
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).takeWhile(x -> x < 2);
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).takeWhile(x -> x < 0);
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).takeWhile(x -> x < 2).count());
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).takeWhile(x -> x < 1).count());
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).takeWhile(x -> x < 0).count());
        System.out.println(Arrays.asList(InfiniteListGrader.iterate(0, x -> x + 1).map(x -> x).takeWhile(x -> x < 4).limit(1).toArray()));
        System.out.println(Arrays.asList(InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).map(x -> x).takeWhile(x -> x < 4).limit(1).toArray()));
        System.out.println(Arrays.asList(InfiniteListGrader.generate(new RandomGenerator(12345)).map(x -> x).takeWhile(x -> x < 4).limit(1).toArray()));
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).limit(4).takeWhile(x -> x < 2).count());
        System.out.println(Arrays.asList(InfiniteListGrader.iterate(0, x -> x + 1).limit(4).takeWhile(x -> x < 2).toArray()));
        
        // filter() test (test #32)
        InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x < 10);
        InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x < 10).limit(4);
        InfiniteListGrader.iterate(0, x -> x + 1).limit(4).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).filter(x -> x % 100 == 0);
        InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).filter(x -> x % 100 == 0).limit(2);
        InfiniteListGrader.iterate(0, x -> x + 1).takeWhile(x -> true).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).filter(x -> x % 100 == 0);
        InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).filter(x -> x % 100 == 0).takeWhile(x -> true);
        InfiniteListGrader.iterate(0, x -> x + 1).takeWhile(x -> false).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).filter(x -> x % 100 == 0);
        InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).filter(x -> x % 100 == 0).takeWhile(x -> false);
        InfiniteListGrader.iterate(0, x -> x + 1).takeWhile(x -> x < 100).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).filter(x -> x % 100 == 0);
        InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).filter(x -> x % 100 == 0).takeWhile(x -> x < 100);
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).filter(x -> x % 100 == 0);
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).filter(x -> x % 100 == 0).limit(2);
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x < 10).limit(4).count());
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x > 10).limit(4).count());
        System.out.println(Arrays.asList(InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x > 10).limit(4).toArray()));
        System.out.println(Arrays.asList(InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x % 10 == 1).limit(4).toArray()));
        InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x < 10).limit(4).forEach();
        InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x > 10).limit(4).forEach();
        InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).limit(4).forEach();
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 10)).filter(x -> x % 5 == 0).filter(x -> x % 10 == 0).limit(1).forEach();
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 10)).filter(x -> x % 2 == 0).filter(x -> x % 3 == 0).limit(4).forEach();
        InfiniteListGrader.generate(new RandomGenerator(23456)).filter(x -> x % 2 == 0).filter(x -> x % 4 == 0).limit(4).forEach();
        System.out.println(Arrays.asList(InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x > 6).limit(2).toArray()));
        System.out.println(Arrays.asList(InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x > 10).filter(x -> x < 20).limit(1).toArray()));
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).limit(4).filter(x -> x < 2).count());
        
        // map() test (test #57)
        InfiniteListGrader.iterate(0, x -> x + 1).map(x -> 2 * x);
        InfiniteListGrader.iterate(0, x -> x + 1).map(x -> 2 * x).limit(10);
        InfiniteListGrader.iterate(0, x -> x + 1).map(x -> 2 * x).map(x -> x / 2).map(x -> x * x).limit(10);
        InfiniteListGrader.iterate(0, x -> x + 1).filter(x -> x % 2 == 0).map(x -> 2 * x).map(x -> x / 2).map(x -> x * x).limit(10);
        InfiniteListGrader.iterate(0, x -> x + 1).map(x -> 2 * x).map(x -> x / 2).filter(x -> x % 3 == 0).map(x -> x * x).limit(10);
        InfiniteListGrader.iterate(0, x -> x + 1).map(x -> 2 * x).map(x -> x / 2).filter(x -> x % 3 == 0).limit(10);
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).map(x -> 2 * x).map(x -> x / 2).map(x -> x * x).limit(10);
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).map(x -> 2 * x).map(x -> x / 2).filter(x -> x % 3 == 0).map(x -> x * x).limit(10);
        System.out.println(Arrays.asList(InfiniteListGrader.iterate(0, x -> x + 1).map(x -> x * x).filter(x -> x % 2 == 1).limit(3).toArray()));
        System.out.println(Arrays.asList(InfiniteListGrader.iterate(0, x -> x + 1).map(x -> x * x).limit(5).toArray()));
        System.out.println(Arrays.asList(InfiniteListGrader.iterate(0, x -> x + 1).takeWhile(x -> x < 5).toArray()));
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).map(x -> 2 * x).map(x -> x / 2).filter(x -> x % 3 == 0).map(x -> x * x).limit(10).forEach();
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).limit(9).map(x -> 2 * x).map(x -> x / 2).filter(x -> x % 3 == 0).map(x -> x * x).forEach();
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).map(x -> 2 * x).map(x -> x / 2).limit(8).filter(x -> x % 3 == 0).map(x -> x * x).forEach();
        InfiniteListGrader.generate(new IncrementalGenerator<>(0, x -> x + 1)).map(x -> 2 * x).map(x -> x / 2).filter(x -> x % 3 == 0).limit(8).map(x -> x * x).forEach();
        InfiniteListGrader.generate(new RandomGenerator(34567)).map(x -> 2 * x).map(x -> x / 2).filter(x -> x % 3 == 0).limit(8).map(x -> x * x).forEach();
        InfiniteListGrader.iterate(32, x -> x + 2).map(x -> 2 * x).map(x -> x / 2).filter(x -> x % 3 == 0).takeWhile(x -> x < 100).limit(7).map(x -> x * x).forEach();
        InfiniteListGrader.iterate(32, x -> x + 2).map(x -> 2 * x).map(x -> x / 2).takeWhile(x -> x < 100).filter(x -> x % 3 == 0).limit(8).map(x -> x * x).forEach();
        
        // reduce() test (test #75)
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).map(x -> x * x).limit(5).reduce(0, (x, y) -> x + y));
        System.out.println(InfiniteListGrader.iterate(1, x -> x + 1).limit(5).reduce(0, (x, y) -> x * y));
        System.out.println(InfiniteListGrader.iterate(1, x -> x + 1).limit(0).reduce(0, (x, y) -> x * y));
        System.out.println(InfiniteListGrader.iterate(0, x -> x + 1).limit(0).reduce(1000, (x, y) -> x + y));
        System.out.println(InfiniteListGrader.iterate(1, x -> x + 1).limit(5).reduce((x, y) -> x * y));
        System.out.println(InfiniteListGrader.iterate(1, x -> x + 1).limit(5).reduce((x, y) -> x + 1));
        System.out.println(InfiniteListGrader.iterate(1, x -> x + 1).limit(0).reduce((x, y) -> x * y));
        
        // count() test (test #82)
        System.out.println(InfiniteListGrader.iterate(1, x -> x + 1).limit(0).count());
        System.out.println(InfiniteListGrader.iterate(1, x -> x + 1).limit(10).count());
        System.out.println(InfiniteListGrader.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).limit(2).count());
        System.out.println(InfiniteListGrader.iterate(1, x -> x + 1).takeWhile(x -> x <= 2).limit(2).count());
        
        // forEach() and toArray() are already heavily tested in the intermediate operations tests, so no need specific tests for them.*/
    }
    
}
