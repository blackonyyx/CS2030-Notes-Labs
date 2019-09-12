class Cruise {
    protected final String id;
    protected final int arrive;
    private static int LOADER = 1;
    private static int SERVETIME = 30;
    protected int loaderserve;
    public Cruise(String id, int arrive){
        this.id = id;
        this.arrive = arrive;
        this.loaderserve = 0;
    }
    public int getServiceCompletionTime(){
        return Cruise.SERVETIME+ ((this.arrive/100)*60) + this.arrive%100;
    }
    public int getArrivalTime(){
        return ((this.arrive/100)*60) + this.arrive%100;

    }
    public int getNumLoadersRequired(){
        return Cruise.LOADER;
    }
    public void addLoader(){
      if (this.loaderserve< Cruise.LOADER){
        this.loaderserve++;
      }
    }
    public boolean isServiced(){
      return this.loaderserve == Cruise.LOADER;
    }
    @Override
    public String toString(){
        return String.format(this.id+"@%04d",this.arrive);
    }
}
