interface SideViewable<T extends SideViewable <T>>{
  public T upView();
  public T downView();
  public T rightView();
  public T leftView();
  public T backView();
  public T frontView();
}
