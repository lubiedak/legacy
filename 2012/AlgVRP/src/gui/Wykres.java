package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.lang.Math;
import wczytanie.*;

public class Wykres extends JPanel {

    int[][] cykle=new int[50][7];
    double[][] xy = Wspolrzedne.Wczytaj();
    int[] magazyn = {15, 55, 84};
    int[] grupy = {0, 37, 73};
    

    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);

        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        int rozmiar = 5;
        int b = 0;
        for (int j = 0; j < 3; j++) {
            if (j == 1) {
                g.setColor(Color.red);
            }
            if (j == 2) {
                g.setColor(Color.blue);
                b = 100;
            } else {
                b = grupy[j + 1];
            }

            for (int i = grupy[j]; i < b; i++) {
                if (i == magazyn[j]) {
                    g.drawRect((int) (this.getWidth() * xy[i][0] / 200) - 6, (int) (this.getHeight() * (200 - xy[i][1]) / 200) - 5, 12, 10);
                } else {
                    g.fillOval((int) (this.getWidth() * xy[i][0] / 200) - 3, (int) (this.getHeight() * (200 - xy[i][1]) / 200) - 3, rozmiar, rozmiar);
                }
            }
        }
        int i=0;
        cykle[0][0]=15;
        cykle[0][1]=5;
        cykle[0][2]=11;
        cykle[0][3]=10;
        cykle[0][4]=15;
        
        while(cykle[i][0]!=0 && cykle[i][1]!=0 ){
            g.setColor(new Color((int)(250*Math.random()),(int)(250*Math.random()),(int)(250*Math.random())));
            for(int j=0;j<6;j++){
                if(cykle[i][j+1]!=0){
            g.drawLine((int) (this.getWidth() * xy[cykle[i][j]][0] / 200), (int) (this.getHeight() * (200 - xy[cykle[i][j]][1]) / 200),
                       (int) (this.getWidth() * xy[cykle[i][j+1]][0] / 200), (int) (this.getHeight() * (200 - xy[cykle[i][j+1]][1]) / 200));
                }
            }
        i++;
        }
        
    
    
    }

    public static void set() {
    }
}
