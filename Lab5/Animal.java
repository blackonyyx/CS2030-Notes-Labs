abstract class Animal{
  public final String name;
  private int hunger;
  public Animal(String n,int h){
    name = n;
    hunger = h;
  }
  public String getName(){
    return name;
  }
  public int getHunger(){
    return hunger;
  }
  public abstract String greet();
  @Override
  public String toString(){
    return getName();
  }
}
