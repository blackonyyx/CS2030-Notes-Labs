public class RubikUp extends Rubik{
  RubikUp(Rubik r){
  super(r);
  }
  @Override
  public Rubik left(){
    return super.upView().left().downView();
  }
  @Override 
  public Rubik right(){
    return super.upView().right().downView();
  }
  @Override
  public Rubik half(){
    return super.upView().half().downView();
  }
}
