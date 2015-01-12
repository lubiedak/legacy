
package wczytanie;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.csvreader.CsvReader;


public class Wspolrzedne {
public static double[][] xy=new double[100][2];

public static double[][] Wczytaj(){
        
        try {
                        
                        CsvReader plikWspolrzedne = new CsvReader("dane//wspolrzedne.csv",';');
                        plikWspolrzedne.readHeaders();
                        int i=0;
                        while (plikWspolrzedne.readRecord()){
                        
                        xy[i][0] = new Double(plikWspolrzedne.get("x"));
                        xy[i][1] = new Double(plikWspolrzedne.get("y"));
                        
                        i++;
                        }
			plikWspolrzedne.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

}
    return xy;    
    }

}