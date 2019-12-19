import java.util.*;
class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    Request r = new Request(sc.nextInt(),sc.nextInt(),sc.nextInt());
    Vehicle v;
    PriorityQueue<Booking> b =new PriorityQueue<>();
    while (sc.hasNext()){
      if(sc.next().matches("NormalCab")){
        v =new NormalCab(sc.next(),sc.nextInt());
      }else{
        v =new PrivateCar(sc.next(),sc.nextInt());
      }
      b.add(new Booking(v, new JustRide(), r));
      if(v instanceof NormalCab){
        b.add(new Booking(v, new TakeACab(), r));
      }else{
        b.add(new Booking(v,new ShareARide(), r));
      }
    }
    while (b.size()>0){
      System.out.println(b.poll());
    }
  }
}
