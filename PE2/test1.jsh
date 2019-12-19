Lazy<Integer> eight = Lazy.of(8)
eight
eight.get()
Supplier<String> s = () -> "hello"
Lazy<String> hello = Lazy.of(s)
hello
hello.get()
s = () -> { System.out.println("world!"); return "hello"; }
Lazy<String> hello = Lazy.of(s)
hello
hello.get()
// check that "world!" should not be printed again.
hello.get()
Random rng = new Random(1)
Supplier<Integer> r = () -> rng.nextInt()
Lazy<Integer> random = Lazy.of(r)
// check that random value should not be available
random
// check that random value is obtained only once
random.get().equals(random.get())
/exit
