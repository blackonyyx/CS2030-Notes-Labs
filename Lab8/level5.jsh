InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 5).toArray()
InfiniteList.iterate(1, x -> x + 1).limit(2).takeWhile(x -> x < 5).toArray()
InfiniteList.iterate(1, x -> x + 1).filter(x -> x %2 == 0).takeWhile(x -> x < 5).toArray()
InfiniteList.iterate(1, x -> x + 1).filter(x -> x %2 == 0).takeWhile(x -> x < 10).takeWhile(x -> x < 5).toArray()
InfiniteList.iterate(1, x -> x + 1).filter(x -> x %2 == 0).takeWhile(x -> x < 10).takeWhile(x -> x < 50).toArray()
InfiniteList.iterate(1, x -> x + 1). takeWhile(x -> x == -1).toArray()
/exit
