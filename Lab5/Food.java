abstract class Food{
  public String name;
  public String type;
  public Food(String n){
    this.name = n;
  }
  public abstract String getType();
  public String getName(){
    return name;
  }
  @Override
  public String toString(){
    return name +" " + type;
  }
}
