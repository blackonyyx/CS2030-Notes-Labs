class ShareARide extends Service{
    public int ppk = 50;
    public int peakStart = 600;
    public int peakEnd = 900;
    public ShareARide(){
        super();
    }
    public int pricePerKM(){
        return ppk;
    }
    public int computeFare(Request r){
        int price = r.getDist() * pricePerKM();
        if (r.getTime() >900 || r.getTime() < 600){
            return (price - price%r.pax)/r.pax;
        }else{
            return ((price + 500)-(price+500)%r.pax)/r.pax;
        }
    }
    @Override
    public String toString(){
        return "ShareARide";
    }
}
