package Trasa;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.csvreader.CsvReader;

public class Miasto {
    String nazwa;
    int kat;
    Float N;
    Float E;
    int ileSklp;
    int pozMcrz;
    
    public Miasto(String nazwa,int kat,Float N,Float E,int ileSklp,int pozMcrz){
      this.nazwa=nazwa;
      this.kat=kat;
      this.N=N;
      this.E=E;
      this.ileSklp=ileSklp;
      this.pozMcrz=pozMcrz;
    }
    public static Miasto[] Miasta=new Miasto[300];
    
    public String toString(){
    String napis="Miasto: " + nazwa;
        napis=napis+"\nKąt: "+ kat;
        napis=napis+"\npolozenieN: "+N;
        napis=napis+"\npolozenieE: "+E;
        napis=napis+"\nliczba sklepow: "+ileSklp;
        napis=napis+"\npolozenieMacierz: "+pozMcrz+"\n\n";
        return napis;
}
    
    public static Miasto[] Wczytaj(){
        
        try {
			CsvReader plikMiasta = new CsvReader("dane//miasta.csv",';',java.nio.charset.Charset.forName("Windows-1250"));

			plikMiasta.readHeaders();
                        int i=0;
			while (plikMiasta.readRecord())
                        {
			String name= plikMiasta.get("Nazwa");
			int angle= Integer.parseInt(plikMiasta.get("Kat"));
                        Float Y= new Float(plikMiasta.get("N"));
                        Float X= new Float(plikMiasta.get("E"));
                        int ile= Integer.parseInt(plikMiasta.get("IleSklp"));
                        int gdzie=Integer.parseInt(plikMiasta.get("PozMcrz"));
			Miasta[i]=new Miasto(name,angle,Y,X,ile,gdzie);
                      //  System.out.println(Miasta[i]);
                        
                        i++; 
			
                        }
                        
			plikMiasta.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

}
    return Miasta;    
    }
    public static void Wypisz(){
        Miasto[] Miasteczk;
        Miasteczk=Wczytaj();
        for (int i=0;i<21;i++){
            System.out.println(Miasteczk[i]);
        }
    }
    public int Pozycja(){
        return pozMcrz;
    }
}
