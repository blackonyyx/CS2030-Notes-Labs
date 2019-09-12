public class RubikRight extends Rubik{
  RubikRight(Rubik r){
  super(r);
  }
  @Override
  public Rubik left(){
    return super.rightView().left().leftView();
  }
  @Override 
  public Rubik right(){
    return super.rightView().right().leftView();
  }
  @Override
  public Rubik half(){
    return super.rightView().half().leftView();
  }
}
