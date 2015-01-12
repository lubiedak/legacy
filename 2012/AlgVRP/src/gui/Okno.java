package gui;
import algvrp.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.csvreader.CsvWriter;

public class Okno implements ActionListener {

    public JFrame ramka = new JFrame("Algorytm VRP++");
    
    JPanel baner = new JPanel();
    JButton algorytm=new JButton("<html>Uruchom<br>algorytm</html> ");
    JPanel menucale=new JPanel();
    JPanel mgeneruj=new JPanel();
    JPanel menu = new JPanel();
    JButton generowany=new JButton("Generuj rządania");
    JLabel[] sklNapis=new JLabel[97]; 
    int[] sklIle=new int[97];
    JPanel centrum = new JPanel();
    JPanel podsumowanie=new JPanel();
    private Wykres wykres=new Wykres();
    
    int[] magazyn = {15, 55, 84};
    int[] grupy = {0, 36, 71};
    int[][] grupki;

    public void wyswietl() {
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        baner.setLayout(new GridLayout(0, 1, 30, 20));
        menucale.setLayout(new BorderLayout(0,0));
        podsumowanie.setLayout(new BoxLayout(podsumowanie, BoxLayout.Y_AXIS));
        menu.setLayout(new GridLayout(0, 3, 2, 0));
        mgeneruj.setLayout(new GridLayout(0, 1, 2, 0));
        
        algorytm.addActionListener(this);
        baner.add(algorytm);
        
        int a;
        for(int j=0;j<3;j++){
            if(j==2){
                JLabel pusty=new JLabel();
                menu.add(pusty);
                a=97;
            }else{
                a=grupy[j+1];
            }
        
        for(int i=grupy[j];i<a;i++){
        sklNapis[i]=new JLabel(""+sklIle[i]);
        if(j==0){
        sklNapis[i].setForeground(Color.black);    
        }
        if(j==1){
        sklNapis[i].setForeground(Color.red);    
        }
        if(j==2){
        
            sklNapis[i].setForeground(Color.blue);    
        
        }
        menu.add(sklNapis[i]);
        }
        }
        generowany.addActionListener(this);
        mgeneruj.add(generowany);
        menucale.add(mgeneruj, BorderLayout.NORTH);
        menucale.add(menu, BorderLayout.CENTER);
        
        
       JLabel pods=new JLabel("Podsumowanie:");
       // Wykres.set();
        podsumowanie.add(pods);
        ramka.getContentPane().add(baner, BorderLayout.NORTH);
        ramka.getContentPane().add(menucale, BorderLayout.WEST);
        ramka.getContentPane().add(wykres,BorderLayout.CENTER);
        ramka.getContentPane().add(podsumowanie,BorderLayout.EAST);
        
        ramka.setSize(800, 750);
        ramka.setResizable(true);
        ramka.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == generowany) {
        sklIle=Demand.generuj();
        for(int i=0;i<sklIle.length;i++){
            sklNapis[i].setText(""+i+": "+sklIle[i]+"   ");            
        }
        menu.revalidate();
    }
    if (e.getSource() == algorytm) {
        grupki=Algorytm.opracuj(sklIle);
    }
    }
}
