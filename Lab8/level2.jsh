InfiniteList<Integer> ifl = InfiniteList.generate(() -> 1).map(x -> x * 2)
ifl instanceof InfiniteListImpl
ifl = InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0)
ifl instanceof InfiniteListImpl
InfiniteListImpl<Integer> ifl1 = InfiniteListImpl.iterate(1, x -> x + 1).map(x -> x * 2)
InfiniteListImpl<Integer> ifl2 = ifl1.get().get()
ifl2 = ifl1.get().get()
ifl1 = InfiniteListImpl.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).get().get()
ifl2 = ifl1.get().get().get()
ifl1 = InfiniteListImpl.iterate(1, x -> x + 1).map(x -> x * 2).map(x -> x - 1).get().get()
ifl1 = InfiniteListImpl.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).filter(x -> x < 4).get().get().get().get()
/exit
