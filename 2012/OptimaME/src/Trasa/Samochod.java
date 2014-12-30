package Trasa;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.csvreader.CsvReader;

public class Samochod {
    String numerRej; 
    short ladownosc;
    String model;
    String domyslnyKierowca;
    boolean dostepSamochodu;
    
    

    public Samochod(String numerRej,short ladownosc,String model,String domyslnyKierowca,boolean dostepSamochodu){
      this.numerRej=numerRej;
      this.ladownosc=ladownosc;
      this.model=model;
      this.domyslnyKierowca=domyslnyKierowca;
      this.dostepSamochodu=dostepSamochodu;
    }
    
    public String toString(){
    String napis="Nr. rejestracyjny: " + numerRej;
        napis=napis+", ładowność "+ ladownosc;
        napis=napis+", "+model;
        napis=napis+", "+domyslnyKierowca;
        return napis;
}
    
    public static Samochod[] Samochody=new Samochod[33];
   
    public static Samochod[] Wczytaj(){
       
        try {
			CsvReader plikAuta = new CsvReader("dane//samochody.csv",';',java.nio.charset.Charset.forName("Windows-1250"));


			plikAuta.readHeaders();
                        int i=0;
			while (plikAuta.readRecord())
                        {
			String name= plikAuta.get("Kierowca");
			String rej = plikAuta.get("NumerRej");
                        String mod =plikAuta.get("Model");
                        short lad=new Short(plikAuta.get("Ladownosc"));
                        String dost=plikAuta.get("Dostepnosc");
                        boolean dostep=false;
                        if(dost.equals("tak") || dost.equals("Tak") || dost.equals("TAK")){
                        dostep=true;
                        }
                        Samochody[i]=new Samochod(rej,lad,mod,name,dostep);
                        i++; 
			
                        }
                        
			plikAuta.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

}
    return Samochody;    
    }
    short Ladownosc(){
        return ladownosc;
    }
    boolean Dostep(){
        return dostepSamochodu;
    }
    
   public static short ileDostepnych(Samochod[] Auta){
       short ile=0;
       for(short i=0;i<Auta.length;i++){
       if (Auta[i].Dostep()){
           ile++;
       }
       }
           return ile;
       
    }
   public static short[] jakieLadownosci(Samochod[] Auta){
       short[] tab={0,0,0};
       for (int i=0;i<Auta.length;i++){
           if (Auta[i].Dostep()){
               switch (Auta[i].Ladownosc()){
                   case 10:
                       tab[2]++;
                       break;
                   case 28:
                       tab[1]++;
                       break;
                   case 32:
                       tab[0]++;
                       break;

               }
           }
       }
       
       return tab;
   }
   public static int minLiczbaAut(Samochod[] Auta, float ZapLaczne){
       short[] tab=Samochod.jakieLadownosci(Auta);
       int i=0;
       int min=0;
       int minimum=0;
       while(ZapLaczne>0 && i<3){
           
           if(min<tab[i]){
               min++;
               switch (i){
                   case 0:
                       ZapLaczne-=32;
                       break;
                   case 1:
                       ZapLaczne-=28;
                       break;
                   case 2:
                       ZapLaczne-=10;
                       break;
               }
               
           }
           if(min==tab[i]){
           i++;
           minimum=minimum+min;
           min=0;
           }
       }
       minimum=minimum+min;
       return minimum;
   }
    
}