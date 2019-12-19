class TakeACab extends Service{
    public int ppk = 33;
    public int booking = 200;
    public TakeACab(){
        super();
    }
    public int pricePerKM(){
        return ppk;
    }
    public int computeFare(Request r){
        return 200 + r.getDist()*pricePerKM();
    }
    @Override
    public String toString(){
        return "TakeACab";
    }
}
