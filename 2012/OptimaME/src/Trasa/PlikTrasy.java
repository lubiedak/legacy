package Trasa;

import java.io.IOException;
import java.io.FileNotFoundException;
import com.csvreader.CsvReader;

public class PlikTrasy {

    String Sklepp;
    String Kierowca;
    int Kolejnosc;
    float Zapotrzebowanie;
    int Miasteczko;
    String Uwagi;
    float ile_m3;

    public PlikTrasy(String Sklep, String Kierowca, short Kolejnosc, Float Zapotrzebowanie, short Miasto, String Uwagi, float ile_m3) {
        this.Sklepp = Sklep;
        this.Kierowca = Kierowca;
        this.Kolejnosc = Kolejnosc;
        this.Zapotrzebowanie = Zapotrzebowanie;
        this.Miasteczko = Miasto;
        this.Uwagi = Uwagi;
        this.ile_m3 = ile_m3;
    }
    public static PlikTrasy[] Trasa = new PlikTrasy[90];

    public String toString() {
        String napis;
        napis = Sklepp + Kierowca + Kolejnosc + Zapotrzebowanie + Miasteczko + Uwagi + ile_m3;
        return napis;
    }

    public static PlikTrasy[] Wczytaj(String skad) {

        try {
            CsvReader plikTrasa = new CsvReader(skad, ';', java.nio.charset.Charset.forName("Windows-1250"));

         //   String[][] rezultat = new String[100][6];
            plikTrasa.readHeaders();
            int i = 0;
            while (plikTrasa.readRecord()) {
                short Kolej = 0;
                short Miast = 0;
                float Zapotrz = 0;
                float ile = 0;
                String Skl = plikTrasa.get("Sklep");
                String Kier = plikTrasa.get("Kierowca");
                if (!plikTrasa.get("Kolejnosc").equals("")) {
                    Kolej = new Short(plikTrasa.get("Kolejnosc"));
                }
                if (!plikTrasa.get("Zapotrzebowanie").equals("")) {
                    Zapotrz = new Float(plikTrasa.get("Zapotrzebowanie"));
                }
                Miast = new Short(plikTrasa.get("Miasto"));
                String Uwaga = plikTrasa.get("Uwagi");
                if (!plikTrasa.get("ile_m3").equals("")) {
                    ile = new Float(plikTrasa.get("ile_m3"));
                }

                Trasa[i] = new PlikTrasy(Skl, Kier, Kolej, Zapotrz, Miast, Uwaga, ile);
                i++;
            }

            plikTrasa.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return Trasa;
    }

public int Kolejny(){
        return Kolejnosc;
    }
    String Driver() {
        return Kierowca;
    }

    public static int czyNowyKierowca(String[] starzy, String nowy) {
        int ktory = -1;
        for (int i = 0; i < starzy.length; i++) {
            if (starzy[i].equals(nowy)) {
                ktory = i;
                break;
            }
        }
        return ktory;
    }
static  Sklep[] sklepy;
static Miasto[] miasta;
static int[][] odleglosci;
    static public Cykl[] cyklWstep(PlikTrasy[] trasa) {
        sklepy=Sklep.Wczytaj();
        miasta=Miasto.Wczytaj();
        odleglosci=Odleglosci.WczytajOdleglosci();
        
        int[][] grupy = new int[30][6];
        String[] kierowcy = new String[30];
        kierowcy[0] = trasa[0].Driver();

        for (int i = 1; i < kierowcy.length; i++) {
            kierowcy[i] = "nic";
        }
        int[] pozycja = new int[trasa.length];
        grupy[0][0] = 1;
        int licznik = 1;
        int ktory;
        
        for (int i = 1; i < trasa.length; i++) {
            if (!trasa[i].Driver().equals("brak")) {
                ktory = czyNowyKierowca(kierowcy, trasa[i].Driver());
                if (ktory == -1) {
                    kierowcy[licznik] = trasa[i].Driver();
                    grupy[licznik][trasa[i].Kolejny()-1] = i+1;
                    licznik++;
                } else {
                    pozycja[ktory]++;
                    grupy[ktory][trasa[i].Kolejny()-1] = i+1;   
                }
            }
        }
        Sklep[] shop=new Sklep[6];
        int[] dystans={-1,-1,-1,-1,-1,-1,-1};
        int ileKm=0;
        float v=50;
        
        Cykl[] cykle=new Cykl[licznik];
        for(int i=0;i<licznik;i++){
            dystans=new int[7];
            dystans[0]=74;
            for (int x=1;x<7;x++){
            dystans[x]=-1;    
            }
            //System.out.print(""+dystans[0]);
            shop=new Sklep[6];
            int w=3;
            for(int j=0;j<grupy[i].length;j++){
            
                if(grupy[i][j]!=0){
                shop[j]=sklepy[grupy[i][j]-1];
                dystans[j+1]=miasta[shop[j].KtoreMiasto()-1].Pozycja()-1;
                //System.out.print(" "+dystans[j+1]);
                dystans[j+2]=74;
                w=j+2;
                }
                
            }//System.out.print(" "+dystans[w]+" \n");
                    ileKm=Odleglosci.Dystans(dystans,odleglosci);
                    
                    cykle[i]= new Cykl(75,kierowcy[i],"MAN",shop,ileKm,ileKm/v,Sklep.SumaZap(shop),0);
        }
        
        return cykle;
    }
}
