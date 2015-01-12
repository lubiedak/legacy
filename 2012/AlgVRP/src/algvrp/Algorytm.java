package algvrp;
import wczytanie.*;

public class Algorytm {
   public static double[][] xy=Wspolrzedne.Wczytaj();
   public static double[][] odleglosci=Odleglosci.Wczytaj();
   static int[][] grupy=new int[8][15];
    static int[] magazyn = {15, 55, 84};
    static int[] rozmiargrupy = {0, 37, 73};
   
   static int licznik=0;

public static int[][] pogrupuj(){   
   for(int i=0;i<rozmiargrupy[1];i++){
    if(xy[i][0]>66 && xy[i][1]<55){
        grupy[0][licznik]=i;
        System.out.println(""+grupy[0][licznik]);
        licznik++;
    }
}
    System.out.println("\n"+licznik);
   licznik=0;
      for(int i=0;i<rozmiargrupy[1];i++){
    if(xy[i][0]<66 && xy[i][1]<60&& i!=magazyn[0]){
        grupy[1][licznik]=i;
        System.out.println(""+grupy[1][licznik]);
        licznik++;
    }
}
       System.out.println("\n"+licznik);
      licznik=0;
      for(int i=0;i<rozmiargrupy[1];i++){
    if(xy[i][1]>60){
        grupy[2][licznik]=i;
        System.out.println(""+grupy[2][licznik]);
        licznik++;
    }
}
   
   System.out.println(""+licznik);
   return grupy;
}

   public static int[][] opracuj(int[] zapotrzebowanie){
      int[][] cykle=new int[50][7];
      grupy=pogrupuj();
      
      
      
      
      
       return cykle;
   }
}
