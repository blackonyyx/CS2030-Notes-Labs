import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Menu m = new Menu();
        String cmd;
        String type;
        String food;
        int input;
        int[] orderrray;
        List <Integer>indexes = new ArrayList<Integer>();
        while (sc.hasNext()){
            cmd = sc.next();
            if (cmd.matches("end")){
              System.out.println("begin ordering");
              break;
            }
            else{
              type = sc.next();
              food = sc.next();
              System.out.println(type+" "+food);
              if (!type.matches("Combo")){
                m.add(type,food,sc.nextInt());
               System.out.println();
              }else{
                while (sc.hasNextInt()){
                    input= sc.nextInt();
                    indexes.add(input);
                }
                m.add(type,food,indexes);
                System.out.println();
                System.out.println(indexes);
                System.out.println("combo");
                indexes.removeAll(indexes);
              }
            }
            System.out.println("Menu" + m);
        }
        System.out.println("now printing formally");
        m.print();
        List<Integer> orders = new ArrayList<Integer>();
        Order o = new Order(m);
        int ord;
        while (sc.hasNext()){
            ord = sc.nextInt();
            orders.add(ord);
        }
       orderrray = Menu.convertPrimitive(orders);
        System.out.println("\n--- Order ---");
        o.add(orderrray);
        System.out.println(o);
    }
}
