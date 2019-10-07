import java.util.List;
import java.util.ArrayList;
import java.util.Formatter;
/**
 * Represents a Menu containing food Items.
 * Is dependent on Food class
 * A menu can contain unlimited number of food classes
 */
class Menu{
    /**
     * The menu has a nested arraylist of food representing the menu used for ordering.
     * Each menu also tracks a list of foodtypes input into it
     * Food items added into the menu are assumed to be unique size 1 ArrayList of Food.
     * Combo meals are represented by non size 1 ArrayLists of food.
     * id is tracked according to the size of the menu, begining with 0
     */
    private List <ArrayList<Food>> menu;
    private List <String> foodtype;
    private int id = 0; 
    /**
     * Constructor will return a empty menu
     */
    public Menu(){
        menu = new ArrayList<ArrayList<Food>>();
        foodtype = new ArrayList<String>();
    }
    public static int[] convertPrimitive(List<Integer> i){
      int[] ret = new int[i.size()];
      for (int n = 0; n<ret.length; n++){
        ret[n] = i.get(n).intValue();
      }
      return ret;
    }
    /**
     * Method to add a food item to the menu.
     * Takes in (String) food type, (String) name, (int) Price of food
     * Checks if foodtype is accounted for in menu, adds it to Menu foodtype arraylist if not
     * Creates a instance of a food item, and adds it to a new arraylist of food
     * then adds that arraylist to the menu
     */
    public Food add(String t,String n, int p){
        if (!foodtype.contains(t)){
            foodtype.add(t);
        }
        Food f = new Food(id,t,n,p);
        ArrayList<Food> nom = new ArrayList<Food>();
        nom.add(f);
        menu.add(nom);
        this.id++;
        return nom.get(0);
    }
    /**
     * Method to add a combo item to the menu.
     * Takes in (String) food type, (String) name, List<Integers> id of food items in the menu
     * Checks if foodtype is accounted for in menu, adds it to Menu foodtype arraylist if not
     * Creates a instance of a food item, and adds it to a new arraylist of food
     * then adds that arraylist to the menu
     */
    public Combo add(String t, String n, List<Integer> ids){
      if (!foodtype.contains(t)){
        foodtype.add(t);
      }
      int price = -50;
      ArrayList<Food> nom = new ArrayList<Food>();
      ArrayList<Food> nom1 = new ArrayList<Food>();
      for (Integer i : ids){
        nom.add(getFood(i.intValue()).get(0));
        price += getFood(i).get(0).getPrice();
      }
      Container fi = new Container(nom);
      Combo f = new Combo(id,t,n,price,fi);
      nom1.add(f);
      menu.add(nom1);
      this.id++;
      return (Combo)nom1.get(0);
    }
    /**
     * Method to retrieve ArrayList of Food.
     * Returns ArrayList of Food
     */
    public ArrayList<Food> getFood(int i){
        return menu.get(i);
    }
    /**
     * Static method to return a String Representation of either a combo item or a single Food item.
     * Takes in a ArrayList of Food items, determines if it is a single food item or a combo item, then 
     * Returns a String representation of the food item
     */
    /* Method to return a printable form of the Menu
     */
    public void print(){
      System.out.println(this);
     // return this;
    }
    @Override
    /** 
     * String representation of a Menu item, to be used in print()
     */
    public String toString(){
        String s = "";
        for (String n:foodtype){
          if (!n.matches("Combo")){
            for (ArrayList<Food> f : menu){
                  if(n == f.get(0).getType()){
                    s+= f.get(0)+"\n";
                }
            }
          }
        }
        for (ArrayList<Food> f : menu){
          if("Combo" == f.get(0).getType()){
            s+= ((Combo)f.get(0)).toString()+"\n"; 
          }
        }
        return s.trim();
    }
}
