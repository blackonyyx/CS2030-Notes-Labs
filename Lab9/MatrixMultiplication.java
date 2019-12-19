import java.util.concurrent.RecursiveTask;

class MatrixMultiplication extends RecursiveTask<Matrix> {
  
  /** The fork threshold. */
  private static final int FORK_THRESHOLD = 128;

  /** The first matrix to multiply with. */
  private Matrix m1;

  /** The second matrix to multiply with. */
  private Matrix m2;

  /** The starting row of m1. */
  private int m1Row;

  /** The starting col of m1. */
  private int m1Col;

  /** The starting row of m2. */
  private int m2Row;

  /** The starting col of m2. */
  private int m2Col;

  /**
   * The dimension of THRESHOLDe input (sub)-matrices and the size of the output
   * matrix.
   */
  private int dimension;

  /**
   * A constructor for the Matrix Multiplication class.
   * @param  m1 The matrix to multiply with.
   * @param  m2 The matrix to multiply with.
   * @param  m1Row The starting row of m1.
   * @param  m1Col The starting col of m1.
   * @param  m2Row The starting row of m2.
   * @param  m2Col The starting col of m2.
   * @param  dimension The dimension of the input (sub)-matrices and the size
   *     of the output matrix.
   */
  MatrixMultiplication(Matrix m1, Matrix m2, int m1Row, int m1Col, int m2Row,
                       int m2Col, int dimension) {
    this.m1 = m1;
    this.m2 = m2;
    this.m1Row = m1Row;
    this.m1Col = m1Col;
    this.m2Row = m2Row;
    this.m2Col = m2Col;
    this.dimension = dimension;
  }


  @Override
  public Matrix compute(){
    return MatrixMultiplication.recursiveMultiply(m1,m2,m1Row,m1Col,m2Row,m2Col,dimension);
  }
  public static Matrix recursiveMultiply(Matrix m1, Matrix m2, int m1Row, int m1Col, int m2Row,
                       int m2Col, int dimension) {
    if (dimension <= FORK_THRESHOLD) {
      return Matrix.nonRecursiveMultiply(m1, m2, m1Row, m1Col, m2Row, m2Col, dimension);
    }
    Matrix mat = new Matrix(dimension);
    int size = dimension/2;
    MatrixMultiplication a11b11f = new MatrixMultiplication(m1,m2, m1Row,m1Col,m2Row,m2Col,size);
    MatrixMultiplication a12b21f = new MatrixMultiplication(m1,m2,m1Row,m1Col+size,m2Row+size,m2Col,size);
    a11b11f.fork();
    a12b21f.fork();
    MatrixMultiplication a11b12f = new MatrixMultiplication(m1,m2,m1Row,m1Col,m2Row,m2Col+size,size);
    MatrixMultiplication a12b22f = new MatrixMultiplication(m1,m2,m1Row,m1Col+size,m2Row+size,m2Col+size,size);
    a11b12f.fork();
    a12b22f.fork();
    MatrixMultiplication a21b11f = new MatrixMultiplication(m1,m2,m1Row+size,m1Col,m2Row,m2Col,size);
    MatrixMultiplication a22b21f = new MatrixMultiplication(m1,m2,m1Row+size,m1Col+size,m2Row+size,m2Col,size);
    a21b11f.fork();
    a22b21f.fork();
    MatrixMultiplication a21b12f = new MatrixMultiplication(m1,m2,m1Row+size,m1Col,m2Row,m2Col+size,size);
    MatrixMultiplication a22b22f = new MatrixMultiplication(m1,m2,m1Row+size,m1Col+size,m2Row+size,m2Col+size,size);
    a21b12f.fork();
    a22b22f.fork();
    //now to .join()
    Matrix a22b22 = a22b22f.join();
    Matrix a21b12 = a21b12f.join();
    Matrix a22b21 = a22b21f.join();
    Matrix a21b11 = a21b11f.join();
    Matrix a12b22 = a12b22f.join();
    Matrix a11b12 = a11b12f.join();
    Matrix a12b21 = a12b21f.join();
    Matrix a11b11 = a11b11f.join();


    for (int i = 0; i < size; i++) {
      double[] m1m = a11b11.m[i];
      double[] m2m = a12b21.m[i];
      double[] r1m = mat.m[i];
      for (int j = 0; j < size; j++) {
        r1m[j] = m1m[j] + m2m[j];
      }
    }


    for (int i = 0; i < size; i++) {
      double[] m1m = a11b12.m[i];
      double[] m2m = a12b22.m[i];
      double[] r1m = mat.m[i];
      for (int j = 0; j < size; j++) {
        r1m[j + size] = m1m[j] + m2m[j];
      }
    }
    for (int i = 0; i < size; i++) {
      double[] m1m = a21b11.m[i];
      double[] m2m = a22b21.m[i];
      double[] r1m = mat.m[i + size];
      for (int j = 0; j < size; j++) {
        r1m[j] = m1m[j] + m2m[j];
      }
    }

    for (int i = 0; i < size; i++) {
      double[] m1m = a21b12.m[i];
      double[] m2m = a22b22.m[i];
      double[] r1m = mat.m[i + size];
      for (int j = 0; j < size; j++) {
        r1m[j + size] = m1m[j] + m2m[j];
      }
    }
    return mat;
  }
}
