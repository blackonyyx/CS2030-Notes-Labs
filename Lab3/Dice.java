class Dice implements SideViewable<Dice>{
  public final String[] faces;
  public Dice(){
    this.faces =new String[] {"U","F","R","B","L","D"};
  }
    public Dice(String[] f){
    this.faces = f;
  }
  public Dice clone(){
    String[] n = new String[6];
    int i = 0;
    for (String s : this.faces){
      n[i] = s;
      i++;
    }
    return new Dice(n);
  }
  @Override
  public Dice upView(){
  Dice c = clone();
  String[] n = new String[6];
  n[0] = c.faces[3];
  n[1] = c.faces[0];
  n[2] = c.faces[2];
  n[3] = c.faces[5];
  n[4] = c.faces[4];
  n[5] = c.faces[1];
  return new Dice(n);
  }
  public Dice frontView(){
    return new Dice();
  }
  public Dice backView(){
    return rightView().rightView();
  }
  public Dice rightView(){
  Dice c = clone();
  String[] n = new String[6];
  n[0] = c.faces[0];
  n[1] = c.faces[2];
  n[2] = c.faces[3];
  n[3] = c.faces[4];
  n[4] = c.faces[1];
  n[5] = c.faces[5];
  return new Dice(n);
  }
  public Dice leftView(){
    return rightView().rightView().rightView();
  }
  public Dice downView(){
    return upView().upView().upView();
  }
  @Override
  public String toString(){
    return "\n"+ this.faces[0]+"\n"+this.faces[1]+this.faces[2]+this.faces[3]+this.faces[4]+"\n   "+this.faces[5];
  }
}
