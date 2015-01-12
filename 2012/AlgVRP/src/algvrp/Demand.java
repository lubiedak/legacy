
package algvrp;
import java.lang.Math;

public class Demand {
static int[] magazyn = {15, 55, 84};  

  public static int[] generuj(){
  int[] zapotrzebowanie=new int[100];
  for(int i=0;i<100;i++){
      zapotrzebowanie[i]=(int)(400+500*Math.random());
  }
  zapotrzebowanie[magazyn[0]]=0;
  zapotrzebowanie[magazyn[1]]=0;
  zapotrzebowanie[magazyn[2]]=0;
      return zapotrzebowanie;
  }
}
