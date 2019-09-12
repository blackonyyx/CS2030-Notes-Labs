class Point {
  private final double x;
  private final double y;
  Point (double x, double y){
    this.x = x;
    this.y = y;
    }
  public static Point makePoint( double x, double y){
    if (Double.isNaN(x) ||Double.isInfinite(x)){
      return null;
    }else if (Double.isNaN(y) || Double.isInfinite(y)){
      return null;
    }else{
      return new Point(x,y);
    }
  }
  public Point setX (double x){
    return makePoint(x, this.y);
  }
  public Point setY (double y){
    return makePoint(this.x,y);
  }
  public  Point addX( double x) {
    return makePoint (this.x + x,this.y);
  }
  public Point addY(double y) {
    return makePoint (this.x ,this.y+y);
  }
  public double getX(){
    return this.x;
  }
  public double getY(){
    return this.y;
  }
  public double getDistance(Point q){
    double dy = this.y - q.y;
    double dx = this.x - q.x;
    return Math.sqrt(dx*dx +dy*dy);
  }
  public  Point midPoint(Point q){
    double x = (this.x +q.x)/2;
    double y = (this.y + q.y)/2;
    return makePoint(x,y);
  }
  public double angleTo(Point q){
    double dx = q.x- this.x; 
    double dy = q.y- this.y;
    return Math.atan2(dy,dx);
  }
  public Point moveTo ( double angle , double d){
    return makePoint(this.x + d*Math.cos(angle),this.y + d*Math.sin(angle));
  }
  @Override
  public String toString(){
    return String.format("point (%.3f, %.3f)", this.x , this.y);
  }
   /* @Override
  public boolean equals (Object obj){
    if (this == obj){
      return true;
    }else {
      if (obj instanceof Point){

    Point p = (Point) obj;
    return this.x == p.x && this.y == p.y;
  }*/
}
