package Trasa;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.csvreader.CsvReader;


public class Odleglosci {
public static int[][] odleglosc=new int[75][75];

public static int[][] WczytajOdleglosci(){
        
        try {
			CsvReader plik = new CsvReader("dane//odleglosci.csv",';');
			plik.readHeaders();
                        short a=1;
			while (plik.readRecord()){a++;}
                        plik.close();
                        
                        CsvReader plikOdleglosci = new CsvReader("dane//odleglosci.csv",';');
                        plikOdleglosci.readHeaders();
                        short i=0,j=0;
                        String nr;
                        while (plikOdleglosci.readRecord()){
                            for (i=1;i<a;i++){
                        nr=""+i;
                        odleglosc[i-1][j] = new Short(plikOdleglosci.get(nr));
                        }
                            j++;
                        }
                        
			plikOdleglosci.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

}
    return odleglosc;    
    }

public static int Dystans(int[] miasta, int[][] odleglosci){
    int ile=0;
    
    for(int i=0;i<miasta.length-1;i++){
        if(miasta[i]!=-1 && miasta[i+1]!=-1){
        ile =ile + odleglosci[miasta[i]][miasta[i+1]];
        }
    }
    return ile;
}

}
