/open Cloneable.java
/open Face.java
/open SideViewable.java
/open Rubik.java
int[][][] grid = new int[6][3][3]
int d = 1
for (int k = 0; k < 6; k++)
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++) grid[k][i][j] = d++;
new Rubik(grid)
new Rubik(grid).clone()
new Rubik(grid).upView()
new Rubik(grid).rightView()
new Rubik(grid).downView()
new Rubik(grid).leftView()
new Rubik(grid).backView()
/exit
