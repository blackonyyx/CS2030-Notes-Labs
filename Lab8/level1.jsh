/open InfiniteList.java
/open InfiniteListImpl.java
InfiniteList<Integer> ifl = InfiniteList.generate(() -> 1)
ifl instanceof InfiniteListImpl
ifl = InfiniteList.iterate(1, x -> x + 1)
ifl instanceof InfiniteListImpl
InfiniteListImpl<Integer> ifl1 = InfiniteListImpl.generate(() -> 1).get()
ifl1 = InfiniteListImpl.iterate(1, x -> x + 1).get()
ifl1 = InfiniteListImpl.iterate(1, x -> x + 1).get().get()
InfiniteListImpl<Integer> ifl2 = ifl1.get()
ifl1 != ifl2
InfiniteListImpl<String> ifls = InfiniteListImpl.iterate("A", x -> x + "Z").get().get().get()
/exit
