class RecycledLoader extends Loader {
    private static int DOWNTIME = 60;
    public RecycledLoader(int id){
      super(id);

    }
    private RecycledLoader(int id, Cruise s){
      super(id,s);
    }
    @Override
    public Loader serve(Cruise c){
      if (this.serving==null|| this.serving.getServiceCompletionTime()+RecycledLoader.DOWNTIME<=c.getArrivalTime()){
        c.addLoader();
        return new RecycledLoader(this.id,c);
      }else{
        return null;
      }
    }
    @Override
    public String toString(){
      if (this.serving == null){
        return "Loader " + this.id + " (recycled)";
      }else{
        return "Loader " + this.id + " (recycled) serving " + this.serving.toString();
      }
    }
}
