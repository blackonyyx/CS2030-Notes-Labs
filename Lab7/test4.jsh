Shop shop1 = new Shop(10);
shop1.find(server -> server.equals(new Server(1)))
shop1.find(server -> server.isIdle())
shop1.find(server -> !server.isIdle())
shop1.replace(new Server(1).serve(new Customer(1, 1))).find(server -> server.isIdle())
/exit
