import java.util.List;
import java.util.ArrayList;
/**
 * Container Class to contain food items for combo
 */
class Container{
  private List<Food> food;
  public Container(ArrayList<Food> f){
    this.food = f;
  }
  @Override
  public String toString(){
    String s = "";
    for (int i = 0; i < food.size();i++){
      s +="\n   ";
      s+= (food.get(i)).toString();
    }
    return s;
  }
}

