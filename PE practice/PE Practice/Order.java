import java.util.ArrayList;
import java.util.List;
/**
 * Represents a Order containing Menu and ordered items.
 * Is dependent on Menu class and dependent on Food class
 * A Order can only contain items from the menu assigned to it via its constructor
 */
class Order{
    /**
     * The order is represented as a arraylist of arraylists of food, similarly to how menu was represented.
     * Order is connected to the menu it derives orders from.
     * cost begins by default at 0, and only increaes as food items are added onto the order
     */
    private int cost = 0;
    private List<ArrayList<Food>> food= new ArrayList<ArrayList<Food>>();
    private Menu menu;
     /**
     * Constructor takes in a menu and returns a empty Order
     */
    public Order(Menu menu){
        this.menu = menu;
    }
    /**
     * Method to add a food item referenced by id number from a int array to the list of Food ordered.
     * Takes in int array of ids and adds according ArrayList of food item from menu to the Order food ArrayList
     * Then returns itself
     */
    public Order add(int[] lst){
        for (int id : lst){
            food.add(menu.getFood(id));
            cost += menu.getFood(id).get(0).getPrice();
        }
        return this;
    }
    public int getTotal(){
      return cost;
    }
    public List<ArrayList<Food>> getFood(){
      return this.food;
    }
    @Override
    /**
     * Method returning the string representation of a Order
     */
    
    public String toString(){
        String s = "\n";
        for (int i =0; i < food.size();i++){
            s += food.get(i).get(0)+"\n";
        }
        s+= String.format("Total: "+ cost);
        return s;
    }
}




