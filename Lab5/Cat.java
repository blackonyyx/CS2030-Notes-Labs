class Cat extends Animal{
  public final String color;
  private boolean lazy;
  public Cat(String n, int h,String c){
    super(n,h);
    color = c;
    lazy = false;
  }
  public String greet(){
    if (this.lazy == false){
      lazy = true;
      return this+" says meow and gets lazy";
    }else{
      lazy = false;
      return this+ " is lazy";
    }
  }
  @Override
  public String toString(){
    return super.toString()+"("+color+")";
  }
}
