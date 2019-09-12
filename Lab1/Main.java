import java.util.Scanner;

class Main{
    public static Circle createCircle( Point p ,Point q, double r){
    if (p.getDistance(q) > 2*r|| p.getDistance(q)== 0){
      return null;
    }else{
    Point m =  p.midPoint(q);
    double theta = p.angleTo(m) + Math.PI/2;
    double mp = m.getDistance(p);
    double d = Math.sqrt(r*r - mp*mp);
    Point c = m.moveTo(theta,d);
    
    return Circle.getCircle(c,r);
    }
  }
  static int DiscCoverage ( Point p, Point q, Point[] pts){
    // Checks for valid conditions before entering loop 
    if (p.getDistance(q) > 2){
      return 0;
    }else{
    Circle[] circa = new Circle[2]; 
    circa[0] =  Main.createCircle(p,q,1);
    circa[1] =  Main.createCircle(q,p,1);
    int max = 0;
    int temp = 0;
    for (Circle circle : circa){
      temp = 0;
      if (circle != null){
      for (Point poi: pts){
       if (circle.isInside(poi)){
            temp++;
          }
        }
      if (max < temp){
        max = temp;
      }
     }
    }
    return max;
    }
  }
  static int Iterate(Point[] points){
  int max = 0;
  int temp = 0;
  for (int i = 0;i<points.length;i++){
    for (int j = i+1;j<points.length; j++){
      temp = Main.DiscCoverage( points[i] , points[j], points);
        if (max < temp){
          max = temp;
        }
      }
    }
  return max;
}
   public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Point[] points;
    points = new Point[sc.nextInt()];
    for (int i = 0; i< points.length; i++){
    points[i] =  Point.makePoint(sc.nextDouble(),sc.nextDouble());
    }
    int result = Iterate(points);
    System.out.printf("Maximum Disc Coverage: %d",result);
  }
}
