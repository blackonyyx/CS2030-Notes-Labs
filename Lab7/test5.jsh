SimState s = new SimState(1);
Customer c1 = new Customer(1.0, 1);
Customer c2 = new Customer(1.5, 2);
Customer c3 = new Customer(1.8, 3);
Server server = new Server(1);
s = s.noteArrival(1.0, c1);
s = s.noteServed(1.0, server, c1);
s = s.noteArrival(1.5, c2);
s = s.noteWait(1.5, server, c2);
s = s.noteArrival(1.8, c3);
s = s.noteLeave(1.8, c3);
s = s.noteDone(2.0, server, c1);
s = s.noteServed(2.0, server, c2);
s = s.noteDone(3.0, server, c1);
s
s = new SimState(2);
Server s1 = new Server(1);
Server s2 = new Server(2);
s = s.simulateArrival(1.0).simulateArrival(1.5).simulateArrival(1.8);
s = s.simulateDone(2.0, s1, c1).simulateDone(2.5, s2, c2).simulateDone(3.0, s1, c3);
s
SimState s = new SimState(1);
s = s.addEvent(1.0, state -> state.simulateArrival(1.0));
s = s.addEvent(1.5, state -> state.simulateArrival(1.5));
s = s.addEvent(1.8, state -> state.simulateArrival(1.8));
s.run()
/exit
