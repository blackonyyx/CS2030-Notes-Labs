import java.util.Scanner;
class Main{
    public static Cruise[] readCruise(){
      Scanner sc = new Scanner(System.in);
      String id;
      char c;
      int entries = sc.nextInt();
      Cruise[] cruises;
      cruises = new Cruise[entries];
      for (int i = 0; i< entries;i++){
        id = sc.next();
        c = id.charAt(0);
      if (c == 'B'){
         cruises[i] = new BigCruise(id,sc.nextInt(),sc.nextInt(),sc.nextInt());
      }
      else{
          cruises[i] = new Cruise(id,sc.nextInt());
        }
      }
      return cruises;
    }
    public static Loader[] serveCruise (Cruise c, Loader[] l,int purchased){
        int i = 0;
        Loader temp;
        while (c.isServiced()==false){
            if (i<=purchased-1&&purchased!=0){
             temp = l[i];
            l[i]=l[i].serve(c);
            if (l[i]== null){
              l[i] = temp;
              i++;
            }
            else{
            System.out.println(l[i]);
            i++;
            }
          }else{
            purchased++;
            l[i] = new Loader(purchased);
            l[i] = l[i].serve(c);
            System.out.println(l[i]);
            i++;
          }
        }
      return l;
    }
    public static Loader[] purchaseLoader(Loader[] l, int p){
      int capacity = p+1;
      Loader[] loaders = new Loader[999];
      for (int i = 0; i < p;i++){
        loaders[i] = l[i];
      }
      loaders[p] = new Loader(capacity);
      return loaders;
    }
    public static int howMany(Loader[] l){
      int i = 0;
      while (l[i] != null){
        i++;
      }
      return i;
    }

    public static void main(String[] args){
        
        Loader[] loaders;
        Cruise[] cruise;
        cruise = readCruise();
        int purchased = 0;
        loaders = new Loader[999];
        for (int i = 0; i < cruise.length; i++){
            loaders = serveCruise(cruise[i],loaders,purchased);
            purchased = howMany(loaders);
        }
    }
}
