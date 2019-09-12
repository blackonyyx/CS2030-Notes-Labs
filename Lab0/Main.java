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
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Point p =  Point.makePoint(sc.nextDouble(),sc.nextDouble());
    Point q =  Point.makePoint(sc.nextDouble(),sc.nextDouble());
    double r = sc.nextDouble();
    Circle n = createCircle(p,q,r);
    if (n==null){
        System.out.println("No valid circle can be created");
    }else {
        System.out.printf("Created: %s\n",n.toString());
    }
  }
}
