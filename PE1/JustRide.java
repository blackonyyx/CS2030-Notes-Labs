class JustRide extends Service{
    public int ppk = 22;
    public int peakStart = 600;
    public int peakEnd = 900;
    public JustRide(){
        super();
    }
    public int pricePerKM(){
        return ppk;
    }
    public int computeFare(Request r){
        int price = r.getDist() * pricePerKM();
        if (r.getTime() >900 || r.getTime() < 600){
            return price;
        }else{
            return price+500;
        }
    }
    @Override
    public String toString(){
        return "JustRide";
    }
}
