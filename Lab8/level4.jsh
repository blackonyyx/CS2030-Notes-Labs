InfiniteList.iterate(1, x -> x + 1).limit(10).filter(x -> x % 2 == 0).count()
InfiniteList.iterate(1, x -> x + 1).limit(10).filter(x -> x % 2 == 0).map(x -> x + 1).reduce(0, (x, y) -> x + y)
InfiniteList.iterate(1, x -> x + 1).limit(10).filter(x -> x % 2 == 0).map(x -> x + 1).reduce((x, y) -> x + y)
InfiniteList.iterate(1, x -> x + 1).limit(0).reduce((x, y) -> x + y)
InfiniteList.iterate(1, x -> x + 1).limit(-1).reduce((x, y) -> x + y)
/exit
