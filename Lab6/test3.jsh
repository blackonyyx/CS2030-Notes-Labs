Trace.of("h").map(s -> s + "ello").get()
Trace.of("h").map(s -> s + "ello").history()
Trace.of(1, 0).map(x -> x + 1).map(y -> y + 2).history()
Trace.of(1, 0).map(x -> x + 1).back(1).map(y -> y + 2).history()
Trace.of("h").map(x -> x).get().equals(Trace.of("h").get())
Trace.of("h").map(x -> x).equals(Trace.of("h"))
Function<Integer,Integer> f = x -> x + 1
Function<Integer,Integer> g = x -> x * 10
Function<Integer,Integer> h = x -> g.apply(f.apply(x))
Trace.of(10).map(f).map(g).get().equals(Trace.of(10).map(h).get())
Trace.of(10).map(f).map(g).equals(Trace.of(10).map(h))
Function<Integer, Integer> collatz = x -> (x % 2 == 0) ? (x/2) : (3*x + 1)
Trace<Integer> t = Trace.of(9)
while (t.get() != 1) t = t.map(collatz)
t.history()
/exit
