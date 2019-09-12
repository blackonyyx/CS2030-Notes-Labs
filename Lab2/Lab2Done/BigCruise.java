class BigCruise extends Cruise{
    private final int loader;
    private final int serveTime;
    public BigCruise(String id,int arrive, int loader, int serveTime){
        super(id,arrive); 
        this.loader = loader;
        this.serveTime = serveTime;
    }
    @Override
    public int getServiceCompletionTime(){
      return this.serveTime+ ((this.arrive/100)*60) + this.arrive%100;
    }
    @Override
    public int getNumLoadersRequired(){
      return this.loader;
    }
    public int getCurrentLoaders(){
      return this.loaderserve;
    }
    @Override
    public void addLoader(){
      if (this.loaderserve < this.loader){
        this.loaderserve++;
      }
    }
    @Override
    public boolean isServiced(){
      return this.loader == this.loaderserve;
    }
}

