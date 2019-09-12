class Face implements Cloneable<Face> {
    public final int[][] grid;
    Face(int[][] grid){
        this.grid = grid;
    }

    public Face rotateLeft(){
        int[][] ng = new int[3][3];
        for (int i = 0; i < grid.length;i++){
          for (int j = 0; j<grid[i].length;j++){
            ng[2-j][i] = this.grid[i][j];
          }
        }
        return new Face(ng);
    }
    public Face left(){
      Face f = this.clone();
      return new Face(f.rotateLeft().toIntArray());
    }
    public Face half(){
      Face f = this.clone();
      return new Face(f.rotateLeft().rotateLeft().toIntArray());
    }
    public Face right(){
      Face f = this.clone();
      int[][] g = f.toIntArray();
      return new Face(f.rotateLeft().rotateLeft().rotateLeft().toIntArray());

    }
    public static int[] reverseList(int[] a){
      int[] rev = new int[3];
      for (int i = 0;i<a.length;i++){
        rev[i] = a[a.length - i-1];
      }
      return rev;
    }
    public int[][] toIntArray(){
      //deepcopy arrayvalues
      Face f = this.clone();
      int[][] g = f.grid;
      return g;
    }
    public int[] getRow(int i){
      int[] data = new int[3];
      for (int j = 0; j< 3; j++){
        data[j] = this.grid[i][j];
      }
      return data;
    }
    public int[] getColumn(int i){
      int[] data = new int[3];
      for (int j = 0; j < 3;j++){
        data[j] = this.grid[j][i];
      }
      return data;
    }
    public Face replaceCol(int i,int[] column){
      // i as index to replace column of as well as array of column replacing
      int[][] ng;
      ng = toIntArray();
      for (int j = 0; j< 3;j++){
        ng[j][i] = column[j];
      }
      return new Face(ng);
    }
    public Face replaceRow(int j,int[] row){
      // j as index to replace rows  of as well as array of row replacing
      int[][] ng;
      ng = toIntArray();
      for (int i = 0; i< 3;i++){
        ng[j][i] = row[i];
      }
      return new Face(ng);
    }

    @Override
    public Face clone(){
        int[][] ng = new int[3][3];
        for (int i = 0; i <grid.length ; i++){
            for (int j = 0;j<grid[i].length ;j++){
                ng[i][j] = grid[i][j];
            }
        }
        return new Face(ng);
    }

    @Override
    public String toString(){
        String x= "\n";
        for (int i=0; i< grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                x = x+ String.format("%02d",grid[i][j]);
            }
            x = x+"\n";
        }
        return x;
    }
}

