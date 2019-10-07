class Combo extends Food{
  private Container foodlist;
  public Combo( int id, String t, String n,int p,Container f){
    super(id,t,n,p);
    this.foodlist = f;
 }
  @Override
  public String toString(){
    String s = "";
    s+= super.toString();
    s+= foodlist.toString();
  
    return s;
  }
}

