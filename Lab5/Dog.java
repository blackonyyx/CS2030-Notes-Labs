class Dog extends Animal{
  public final String bark;
  private int excite;
  public Dog(String n, int h,String c){
    super(n,h);
    bark = c;
    excite= 1;
  }
  public String greet(){
    String sound = super.toString()+" says ";
    int c = 0;
    while(c<excite){
      sound+=bark;
      c++;
    }
    excite++;
    return sound;
  }
}
