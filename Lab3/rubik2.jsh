/open Cloneable.java
/open Face.java
/open Rubik.java
int[][][] grid = new int[6][3][3]
int d = 1
for (int k = 0; k < 6; k++)
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++) grid[k][i][j] = d++;
new Rubik(grid)
new Rubik(grid).left()
new Rubik(grid).right()
new Rubik(grid).half()
/exit
