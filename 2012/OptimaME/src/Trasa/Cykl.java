package Trasa;
import java.lang.Math;

public class Cykl {

    int Start;
    String Driver;
    String Auto;
    Sklep[] Sklepy;
    int Dystans;
    Float Czas;
    double Wypelnienie;
    int WypelnienieProc;

    public Cykl(int Start, String Driver, String Auto, Sklep[] Sklepy, int Dystans,
            Float Czas, double Wypelnienie, int WypelnienieProc) {
        this.Start = Start;
        this.Driver = Driver;
        this.Auto = Auto;
        this.Sklepy = Sklepy;
        this.Dystans = Dystans;
        this.Czas = Czas;
        this.Wypelnienie = Wypelnienie;
        this.WypelnienieProc = WypelnienieProc;
    }

    public String Tytul(){
        return Driver;
    }
    
    public int IleKm(){
        return Dystans;
    }
    public float IleH(){
        float a=Math.round(100*Sklep.SumaZap(Sklepy)/8);
        float b=a/100;
        float x=(int)((Czas+b)*100);
        float h=x/100;
        return h;
    }
    public String toString(){
        String napis;

               napis=""+Dystans+"km ["+IleH()+"h]["+Wypelnienie+"m3]"+Sklep.wypisz(Sklepy);
        return napis;
    }
    public int ileSklepow(){
        int sum=0;
        for (int i=0;i<Sklepy.length;i++){
            if(Sklepy[i]!=null){
                sum++;
            }
        }
        return sum;
    }
    public double ileWypel(){
        return Wypelnienie;
    }
    
public static int LacznaDlug(Cykl[] cykle){
    int suma=0;
    for(int i=0;i<cykle.length;i++){
        suma=suma+cykle[i].IleKm();
    }
    return suma;
}
public static int LacznieIleSklp(Cykl[] cykle){
    int suma=0;
    for(int i=0;i<cykle.length;i++){
        suma=suma+cykle[i].ileSklepow();
    }
    return suma;
}
public static double LaczneWyp(Cykl[] cykle){
    double suma=0;
    for(int i=0;i<cykle.length;i++){
        suma=suma+cykle[i].ileWypel();
    }
    return suma;
}
public static double LacznieCzas(Cykl[] cykle){
    double suma=0;
    for(int i=0;i<cykle.length;i++){
        suma=suma+cykle[i].IleH();
    }
    return suma;
}
public static double SrednieWyp(Cykl[] cykle){
    double suma=LaczneWyp(cykle);
    int razem=0;
    for(int i=0;i<cykle.length;i++){
        if(cykle[i].ileWypel()<12){
            razem=razem+10;
        }
        if(cykle[i].ileWypel()>12 && cykle[i].ileWypel()<28){
            razem=razem+28;
        }
        if(cykle[i].ileWypel()>28){
            razem=razem+32;
        }
        
    }
    return 100*suma/razem;
}

}
