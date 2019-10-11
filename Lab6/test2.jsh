Trace.of("hello", "h", "he", "hel", "hell").back(2).get()
Trace.of("hello", "h", "he", "hel", "hell").back(2).history()
Trace.of("hello", "h", "he", "hel", "hell").back(9).get()
Trace.of(1, 5, 4, 3, 2).equals(Trace.of(1, 2, 3, 4, 5))
Trace.of(1, 5, 4, 3, 2).equals(Trace.of(1))
Trace.of(1, 5, 4, 3, 2).equals(Trace.of(0, 5, 4, 3, 2, 1).back(1))
/exit
