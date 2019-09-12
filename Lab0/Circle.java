class Circle{
  private final Point c;
  private final double r;
  private Circle (Point c , double r){
    this.c = c;
    this.r = r;
  }
  public static Circle getCircle( Point p, double r){
    
    if (r<=0||Double.isNaN(r)||Double.isInfinite(r)){
      return null; 
    }else if(p == null){
      //if no point is created
      return null;
    }
    else{
      return new Circle (p,r);
    }
  }
 public String toString() {
    return String.format("circle of radius %.1f centered at point (%.3f, %.3f)",this.r,c.getX(),c.getY());
  }
  
}
