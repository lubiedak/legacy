package wczytanie;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.csvreader.CsvReader;


public class Odleglosci {
public static double[][] odleglosc=new double[100][100];

public static double[][] Wczytaj(){
        
        try {

                        
                        CsvReader plikOdleglosci = new CsvReader("dane//odleglosci.csv",';');
                        plikOdleglosci.readHeaders();
                        short i=0,j=0;
                        String nr;
                        while (plikOdleglosci.readRecord()){
                            for (i=0;i<100;i++){
                        nr=""+(i+1);
                        odleglosc[j][i] = new Double(plikOdleglosci.get(nr));
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
