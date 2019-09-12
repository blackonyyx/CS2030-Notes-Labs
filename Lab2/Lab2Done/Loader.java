class Loader{
    private final int id;
    private final Cruise serving;
    public Loader(int id) {
        //Makes a new Loader from nothing
        this.id = id;
        this.serving = null;
    }
    private Loader(int id, Cruise s){
        //To service a cruise
        this.id = id;
        this.serving = s;
    }

    public Loader serve(Cruise c){
        if (this.serving != null &&(this.serving.getServiceCompletionTime()>c.getArrivalTime())){
            return null;
        }else if (this.serving == c){
          return null;
        }else{
            c.addLoader();
            return new Loader(this.id, c);
        }
    }
    
   @Override
    public String toString(){
      if (this.serving == null){
        return "Loader "+ this.id;
      }else{
        return "Loader "+ this.id+ " serving " + this.serving.toString();
      }
    }

}
