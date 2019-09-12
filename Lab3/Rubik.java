class Rubik implements Cloneable<Rubik>,SideViewable<Rubik>{
  public final Face[] faces = new Face[6];
  /*
  public Face top; index 0
  public Face left; 1
  public Face front; 2 The one spinning cube
  public Face right; 3
  public Face down; 4
  public Face back; 5
  */
  public Rubik(int[][][] grid){
    int [][] ng = new int[grid[0].length][grid[0][0].length];
    for (int i=0;i < grid.length;i++){
      ng = new int[3][3];
      for(int j=0;j<grid[i].length;j++){
        for (int k = 0;k<grid[i][j].length;k++){
          ng[j][k] = grid[i][j][k];
        }
      }
      this.faces[i] = new Face(ng);
    }
  }
  public Rubik(Rubik r){
    int i = 0;
    Face ng;
    for (Face f : r.getFaces()){
      ng = f.clone();
      this.faces[i] = ng;
      i++;
    }
  }
  public Rubik(Face[] listfaces){
   Face temp;
   for (int i= 0;i<listfaces.length;i++){
     temp = listfaces[i].clone();
     this.faces[i] = temp;
    }
  }
  public Face[] getFaces(){
    return this.faces;
  }
  @Override
  public Rubik clone(){
    Face[] newfaces = new Face[6];
    int i = 0;
    for (Face f : this.faces){
      newfaces[i] = f.clone();
      i++;
    }
    return new Rubik(newfaces);
  }
  public Rubik left(){
    //Rotate index 2
    //swap 0,top's index 3j row with 1,left i3 column -> 4,down 1j row -> i1 col of 3,right ->3j of 0,top
    Face[] newfaces = new Face[6];
    Rubik c = clone();
    newfaces[2] = c.faces[2].left();
    int[] top3j;
    int[] lefti3;
    int[] downi1;
    int[] righti1;
    newfaces[5] = c.faces[5].clone();
    top3j = Face.reverseList(c.faces[0].getRow(2));
    lefti3 = c.faces[1].getColumn(2);
    downi1 = Face.reverseList(c.faces[4].getRow(0));
    righti1 = c.faces[3].getColumn(0);
    newfaces[1] = c.faces[1].replaceCol(2,top3j);
    newfaces[4] = c.faces[4].replaceRow(0,lefti3);
    newfaces[3] = c.faces[3].replaceCol(0,downi1);
    newfaces[0] = c.faces[0].replaceRow(2,righti1);
    return new Rubik(newfaces);
  }
  public Rubik half(){
    Rubik c = clone().right().right();
    return c;
  }
  public Rubik right(){
    //reverses the lists 1,3j 7 8 9 -> 9 8 7
    Face[] newfaces = new Face[6];
    Rubik c = clone();
    newfaces[2] = c.faces[2].right();
    int[] top3j;
    int[] lefti3;
    int[] downi1;
    int[] righti1;
    newfaces[5] = c.faces[5].clone();
    top3j = c.faces[0].getRow(2);
    lefti3 = Face.reverseList(c.faces[1].getColumn(2)); 
    downi1 = c.faces[4].getRow(0);
    righti1 = Face.reverseList(c.faces[3].getColumn(0));
    newfaces[1] = c.faces[1].replaceCol(2,downi1);
    newfaces[4] = c.faces[4].replaceRow(0,righti1);
    newfaces[3] = c.faces[3].replaceCol(0,top3j);
    newfaces[0] = c.faces[0].replaceRow(2,lefti3);

    return new Rubik(newfaces);
  }
  @Override
  public Rubik upView(){
    Face[] newfaces = new Face[6];
    Rubik c = clone();
    newfaces[2] = c.faces[0];
    newfaces[0] = c.faces[5];
    newfaces[1] = c.faces[1].right();
    newfaces[3] = c.faces[3].left();
    newfaces[4] = c.faces[2];
    newfaces[5] = c.faces[4];
    return new Rubik(newfaces);
  }
  @Override
  public Rubik rightView(){
    Face[] newfaces = new Face[6];
    Rubik c = clone();
    newfaces[0] = c.faces[0].right();
    newfaces[1] = c.faces[2];
    newfaces[2] = c.faces[3];
    newfaces[3] = c.faces[5].half();
    newfaces[4] = c.faces[4].left();
    newfaces[5] = c.faces[1].half();
    return new Rubik(newfaces);
  }
  @Override
  public Rubik backView(){
    Face[] newfaces = new Face[6];
    Rubik c = clone();
    newfaces[0] = c.faces[0].half();
    newfaces[1] = c.faces[3];
    newfaces[2] = c.faces[5].half();
    newfaces[3] = c.faces[1];
    newfaces[4] = c.faces[4].half();
    newfaces[5] = c.faces[2].half();
    return new Rubik(newfaces);
  }
  @Override
  public Rubik leftView(){
    Rubik c = clone();
    return c.rightView().rightView().rightView();
  }
  @Override
  public Rubik frontView(){
    Rubik c = clone();
    c = c.upView().upView().upView().upView();
    return c;
  }
  @Override
  public Rubik downView(){
    Rubik c = clone();
    return c.upView().upView().upView();
  }
  public int[][][] getArrayform(){
    int[][][] a = new int[6][3][3];
    for (int i = 0; i<6;i++){
      a[i] = this.faces[i].toIntArray();
    }
    return a;
  }
  @Override
  public String toString(){
    int[][][] matrix = getArrayform();
    String s="\n";
    for (int i =0;i<3;i++){
      s = s+ "......";
      for (int j = 0; j<3;j++){
        s= s+ String.format("%02d",matrix[0][i][j]);
      }
      s = s+ "......\n";
      }
    for (int i = 0; i<3;i++){
      for (int j = 1;j<4;j++){
        for (int k = 0;k<3;k++){
          s = s+String.format("%02d",matrix[j][i][k]);
        }
      }
      s = s+"\n";

    }
    for(int k = 4;k<6;k++){
     for (int i =0;i<3;i++){
      s = s+ "......";
      for (int j = 0; j<3;j++){
        s= s+ String.format("%02d",matrix[k][i][j]);
      }
      s = s+ "......\n";
      }
    }
     return s;
  }
}
