import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main{
  public static int[][][] readGrid(Scanner sc){
    int[][][] grid = new int[6][3][3];
    for (int i = 0;i<grid.length;i++){
      for(int j = 0;j<grid[i].length;j++){
        for(int k = 0;k<grid[i][j].length;k++){
          grid[i][j][k] = sc.nextInt();
        }
      }
    }
    return grid;
  }
  public static Rubik interpretC(Rubik r,String c){
    Rubik rub = r.clone();
    switch(c){
      case "F":
          rub = new Rubik(rub).right();
          break;
      case "R":
          rub = new RubikRight(rub).right();
          break;
      case "U":
          rub = new RubikUp(rub).right();
          break;
      case "L":
          rub = new RubikLeft(rub).right();
          break;
      case "B":
          rub = new RubikBack(rub).right();
          break;
      case "D":
          rub = new RubikDown(rub).right();
          break;
      case "F\'":
          rub = new Rubik(rub).left();
          break;
      case "R\'":
          rub = new RubikRight(rub).left();
          break;
      case "U\'":
          rub = new RubikUp(rub).left();
          break;
      case "L\'":
          rub = new RubikLeft(rub).left();
          break;
      case "B\'":
          rub = new RubikBack(rub).left();
          break;
      case "D\'":
          rub = new RubikDown(rub).left();
          break;
      case "F2":
          rub = new Rubik(rub).half();
          break;
      case "R2":
          rub = new RubikRight(rub).half();
          break;
      case "U2":
          rub = new RubikUp(rub).half();
          break;
      case "L2":
          rub = new RubikLeft(rub).half();
          break;
      case "B2":
          rub = new RubikBack(rub).half();
          break;
      case "D2":
          rub = new RubikDown(rub).half();
          break;
      default:
          break;
    }
    return rub;
  }
  public static void main(String[] args){
    int[][][] g = new int[6][3][3];
    Scanner sc = new Scanner(System.in);
    g = readGrid(sc);
    Rubik r = new Rubik(g);
    String s;
    while (sc.hasNext()){
      s = sc.next();
      r = interpretC(r,s);
    }
    System.out.println(r);
  }

}
