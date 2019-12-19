class Booking implements Comparable<Booking>{
    public final Vehicle v;
    public final Service s;
    public final Request r;
    public Booking(Vehicle vi, Service se, Request re){
        this.v = vi;
        this.s = se;
        this.r = re;
    }
    public static double inDollars(int cents){
        double temp = cents;
        return temp/100;
    }
    public int getFare(){
        return s.computeFare(r);
    }
    public int waitTime(){
        return this.v.getTime();
    }
    @Override
    public String toString(){
        return String.format("$%.2f ",Booking.inDollars(s.computeFare(r)))+ "using "+ v+" ("+s+")";
    }
    public int compareTo(Booking b){
        if (b.getFare() < this.getFare()){
            return 1;
        }else if (this.getFare()< b.getFare()){
            return -1;
        }else if (this.waitTime() < b.waitTime()){
            return -1;
        }else {
            return 1;
        }
    }
}
