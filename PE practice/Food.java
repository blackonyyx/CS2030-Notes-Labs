/**
 * Class representing a Food Item on the menu.
 * Contains type of food item, name, price and id number
 * A Combo is also represented as a food item, with the total price of all constituent food items as its price
 */

class Food{
    protected final int id;
    protected final String type;
    protected final String name;
    protected final int price;
    /**
     * Creates a Food item with the (int) id number, (String) food type, (String) food name and (int) price
     */
    public Food(int id, String t,String n, int p){
        this.id = id;
        this.type= t;
        this.name = n;
        this.price = p;
    }
    /**
     * Getter for the price of the food item
     * @return the price of the food item in int
     */
    public int getPrice(){
        return price;
    }
    /**
     * Getter for the type of the food item
     * @return the type of the food item
     */
    public String getType(){
        return this.type;
    }
    @Override
    public String toString(){
        return String.format("#"+this.id+" "+this.type+": "+this.name+" ("+this.price+")");
    }
}
