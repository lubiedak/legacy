package Trasa;

import java.io.IOException;
import java.io.FileNotFoundException;
import com.csvreader.CsvReader;


public class Sklep {

    String Nazwa;
    float Zapotrzebowanie;
    int Miejscowosc;
    String Uwagi;
    float Ile_m3;

    public Sklep(String Nazwa, float Zapotrzebowanie, short Miejscowosc, String Uwagi, float Ile_m3) {
        this.Nazwa = Nazwa;
        this.Zapotrzebowanie = Zapotrzebowanie;
        this.Miejscowosc = Miejscowosc;
        this.Uwagi = Uwagi;
        this.Ile_m3 = Ile_m3;
    }
    public static Sklep[] Sklepy = new Sklep[90];

    public String toString() {
        return Nazwa;
    }

    public float Potrzeba() {
        return Zapotrzebowanie + Ile_m3;
    }
    public float IleM3(){
        return Ile_m3;
    }
    public int KtoreMiasto(){
        return Miejscowosc;
    }

    public static String wypisz(Sklep[] Sklepiki) {
        String napis = " " + Sklepiki[0] + "(" + Sklepiki[0].Potrzeba() + ")";
        for (int i = 1; i < 6; i++) {
            if (Sklepiki[i] != null) {
                napis = napis + ", " + Sklepiki[i] + "(" + Sklepiki[i].Potrzeba() + ")";
            }
        }
        return napis;
    }

    public static Double SumaZap(Sklep[] Sklepiki) {
        double sum = 0;
        for (int i = 0; i < 6; i++) {

            if (Sklepiki[i] != null) {
                sum = sum + Sklepiki[i].Potrzeba();
            }
        }
        double pom=(int)(sum*100);
        pom=pom+0.0;
        sum=pom/100;
        return sum;
    }

    public static Sklep[] Wczytaj() {

        try {
            CsvReader plikSklepyZap = new CsvReader("dane//trasa.csv", ';', java.nio.charset.Charset.forName("Windows-1250"));
            plikSklepyZap.readHeaders();
            int i = 0;

            while (plikSklepyZap.readRecord()) {
                String name = plikSklepyZap.get("Sklep");
                float zap = new Float(plikSklepyZap.get("Zapotrzebowanie"));
                short nrmiasta = new Short(plikSklepyZap.get("Miasto"));
                String uwag = plikSklepyZap.get("Uwagi");
                float ile = 0;
                if (!plikSklepyZap.get("ile_m3").equals("")) {
                    ile = new Float(plikSklepyZap.get("ile_m3"));
                }
                Sklepy[i] = new Sklep(name, zap, nrmiasta, uwag, ile);
                i++;
            }

            plikSklepyZap.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return Sklepy;
    }

    public static float ZapLaczne(Sklep[] Sklepiki) {
        float suma = 0;
        for (short i = 0; i < Sklepiki.length; i++) {
            suma = suma + Sklepiki[i].Potrzeba()+Sklepiki[i].IleM3();
        }
        return suma;
    }
}
