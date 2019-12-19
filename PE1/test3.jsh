new NormalCab("SHA1234", 5)
new Booking(new NormalCab("SHA1234", 5), new JustRide(), new Request(20, 3, 1000))
new Booking(new NormalCab("SHA1234", 5), new TakeACab(), new Request(20, 3, 1000))
new NormalCab("SHA2345", 10)
new Booking(new NormalCab("SHA2345", 10), new JustRide(), new Request(10, 1, 900))
new Booking(new NormalCab("SHA2345", 10), new TakeACab(), new Request(10, 1, 900))
Comparable<Booking> b1 = new Booking(new NormalCab("SHA2345", 10), new JustRide(), new Request(10, 1, 900))
Booking b2 = new Booking(new NormalCab("SHA2345", 10), new TakeACab(), new Request(10, 1, 900))
b1.compareTo(b2) > 0
Comparable<Booking> b3 = new Booking(new NormalCab("SHA1234", 5), new JustRide(), new Request(10, 1, 900))
Booking b4 = new Booking(new NormalCab("SHA2345", 10), new JustRide(), new Request(10, 1, 900))
b3.compareTo(b4) < 0

