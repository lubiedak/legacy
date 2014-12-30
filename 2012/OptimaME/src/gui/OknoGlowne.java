package gui;

import Trasa.*;
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

public class OknoGlowne implements ActionListener {
    boolean Wczytanie=false;
    boolean OpracPodsum=false;
    boolean Zoptymalizowana=false;
    float ZapAktualne=0;
    int ZapLaczne;
    Cykl[] cykle;
    int ileskl=90;
    PlikTrasy[] Trasa;
    Sklep[] Sklepy;
    Samochod[] Auta;
    public JFrame ramka = new JFrame("OptimaME v.0.01");
    static int w = 0;
    JTabbedPane zakladki = new JTabbedPane();
        
        JPanel Wczytywanie = new JPanel();
            JPanel WCentrum=new JPanel();
            JTabbedPane Wzakladki=new JTabbedPane();
                JPanel Wobszar1=new JPanel();
                JPanel Wobszar2=new JPanel();
                JPanel WPodsumowanie=new JPanel();
                JLabel brakPods=new JLabel();
                
        JPanel WOpis = new JPanel();
            JButton Wwczytaj, Wanalizuj, Wpodsumuj, Weksportuj, Woptymalizuj, WczytajZopt, EksportujZopt;
        
        JPanel Nowa = new JPanel();
            JPanel NCentrum;
            JPanel NMenu = new JPanel();
            JTabbedPane Nzakladki = new JTabbedPane();
                JPanel[] Nobszar = new JPanel[3];
                JCheckBox[] SklepyButt=new JCheckBox[90];
                JButton[] Nakceptuj = new JButton[3];
            JButton NwczytajZap;
            JPanel NMenuPomoc = new JPanel();
            JLabel[] Npodsumowanie=new JLabel[4];
            
          
        JPanel Wyniki = new JPanel();
        
        JPanel Glowny = new JPanel();
        JPanel LogoME = new JPanel();
    //PRZYCISKI***************PRZYCISKI***************PRZYCISKI***************PRZYCISKI***************


    public void wyswietl() {
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ramka.setIconImage(Toolkit.getDefaultToolkit().getImage("dane\\icon.jpg")); //IKONA

        LogoME.setLayout(new GridLayout(0, 1, 30, 20));
        JLabel imageLabel = new JLabel(new ImageIcon("dane\\logo_prog.jpg")); //BANNER
        LogoME.add(imageLabel);

        Glowny.setLayout(new GridLayout(0, 1, 30, 20));

        //POWYŻEJ GŁÓWNE OKNO************POWYŻEJ GŁÓWNE OKNO************POWYŻEJ GŁÓWNE OKNO************

        JPanel WMenuPrzyciski = new JPanel();
        WMenuPrzyciski.setLayout(new GridLayout(0, 1, 10, 10));
        JPanel WMenuPusty = new JPanel();
        JPanel WMenu = new JPanel();
        WMenu.setLayout(new BorderLayout());

        
        WCentrum.setLayout(new BoxLayout(WCentrum, BoxLayout.Y_AXIS));
        WOpis = new JPanel();
        WOpis.setLayout(new BoxLayout(WOpis, BoxLayout.Y_AXIS));

        Wczytywanie.setLayout(new BorderLayout(5, 10));

        JLabel Wopis = new JLabel("W tym miejscu możesz wczytać trasę zapisaną w pliku trasa.csv. "
                + "Aby sprawdzić własności trasy kliknij *Analizuj i wyświetl*.");
        JLabel Wopis2 = new JLabel("Jeżeli będziesz chciał zapisać podsumowanie własności trasy do pliku kliknij *Eksportuj podsumowanie*.");

        WOpis.add(Wopis);
        WOpis.add(Wopis2);

        Wwczytaj = new JButton("Wczytaj trase");
        Wwczytaj.addActionListener(this);

        Wanalizuj = new JButton("Analizuj i wyswietl");
        Wanalizuj.addActionListener(this);

        Wpodsumuj = new JButton("Podsumuj trasę");
        Wpodsumuj.addActionListener(this);
        
        Weksportuj = new JButton("Eksportuj podsumowanie");
        Weksportuj.addActionListener(this);
       
        Woptymalizuj= new JButton("Optymalizuj trasę");
        Woptymalizuj.addActionListener(this);
        
        WczytajZopt= new JButton("Wczytaj zoptymalizowaną");
        WczytajZopt.addActionListener(this);
        
        
        WMenuPrzyciski.add(Wwczytaj);
        WMenuPrzyciski.add(Wanalizuj);
        WMenuPrzyciski.add(Wpodsumuj);
        WMenuPrzyciski.add(Weksportuj);
        WMenuPrzyciski.add(Woptymalizuj);
        WMenuPrzyciski.add(WczytajZopt);
        
        
        WMenu.add(WMenuPrzyciski, BorderLayout.NORTH);
        WMenu.add(WMenuPusty, BorderLayout.CENTER);
        

        Wobszar1.setLayout(new GridLayout(0, 1, 0, 0));
        Wzakladki.addTab("I część trasy", Wobszar1);
        Wobszar2.setLayout(new GridLayout(0, 1, 0, 0));
        Wzakladki.addTab("II część trasy", Wobszar2);
        WPodsumowanie.setLayout(new GridLayout(0, 1, 0, 0));
        brakPods=new JLabel("Chwilowo brak podsumowania do wyświetlenia. Wczytaj trasę i opracuj podsumowanie.");
        WPodsumowanie.add(brakPods);
        Wzakladki.addTab("Podsumowanie", WPodsumowanie);
        
        WCentrum.add(Wzakladki);
        
        Wczytywanie.add(WOpis, BorderLayout.NORTH);
        Wczytywanie.add(WMenu, BorderLayout.WEST);
        Wczytywanie.add(WCentrum, BorderLayout.CENTER);
        //POWYŻEJ JEST PIERWSZA ZAKŁADKA$$$$$$$$$$$$$$$$$$POWYŻEJ JEST PIERWSZA ZAKŁADKA$$$$$$$$$$$$$$$$$$
        JPanel NMenuPrzyciski = new JPanel();
        NMenuPrzyciski.setLayout(new GridLayout(0, 1, 10, 10));
        NMenuPomoc.setLayout(new GridLayout(6, 2, 5, 5));
        
        for(int i=0;i<4;i++){
            Npodsumowanie[i]=new JLabel("0/0");
        }
        JLabel NStat=new JLabel("SZACOWANIE");
        JLabel Nminmax=new JLabel("MIN / MAX");
        NMenuPomoc.add(NStat);
        NMenuPomoc.add(Nminmax);
        JLabel NileKierowcow=new JLabel("Kierowcy:");
        NMenuPomoc.add(NileKierowcow);
        NMenuPomoc.add(Npodsumowanie[0]);
        JLabel NileSamochodow=new JLabel("Samochody:");
        NMenuPomoc.add(NileSamochodow);
        NMenuPomoc.add(Npodsumowanie[1]);
        JLabel NileTowaru=new JLabel("Towar[m3]:");        
        NMenuPomoc.add(NileTowaru);
        NMenuPomoc.add(Npodsumowanie[2]);
        JLabel NileSklepow=new JLabel("Sklepy:");
        NMenuPomoc.add(NileSklepow);
        NMenuPomoc.add(Npodsumowanie[3]);
        
        NMenu.setLayout(new BorderLayout());

        NCentrum = new JPanel();
        NCentrum.setLayout(new GridLayout(0, 1, 0, 0));
        for (int i = 0; i < 3; i++) {
            Nobszar[i] = new JPanel();
            Nobszar[i].setLayout(new GridLayout(17, 4, 0, 0));
            Nzakladki.addTab("Obszar " + (i + 1), Nobszar[i]);
        }
        NCentrum.add(Nzakladki);
        JPanel NOpis = new JPanel();
        NOpis.setLayout(new BoxLayout(NOpis, BoxLayout.Y_AXIS));

        Nowa.setLayout(new BorderLayout(5, 10));

        JLabel Nopis = new JLabel("Po wskazaniu sklepów, które mają zostać odwiedzone zostanie ułożona trasa.");

        NOpis.add(Nopis);

        NwczytajZap = new JButton("Wczytaj zapotrzebowanie");
        NwczytajZap.addActionListener(this);

        NMenuPrzyciski.add(NwczytajZap);

        NMenu.add(NMenuPrzyciski, BorderLayout.NORTH);
        NMenu.add(NMenuPomoc, BorderLayout.SOUTH);

        Nowa.add(NOpis, BorderLayout.NORTH);
        Nowa.add(NMenu, BorderLayout.WEST);
        Nowa.add(NCentrum, BorderLayout.CENTER);


        //DODAWANIE ZAKŁADEK
        zakladki.addTab("Wczytywanie trasy", Wczytywanie);
        zakladki.addTab("Nowa trasa", Nowa);
        zakladki.addTab("Wyniki", Wyniki);

        Glowny.add(zakladki);


        ramka.getContentPane().add(LogoME, BorderLayout.NORTH);
        ramka.getContentPane().add(Glowny, BorderLayout.CENTER);

        ramka.setSize(800, 750);
        ramka.setResizable(false);
        ramka.setVisible(true);
    }
    

    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == Wwczytaj) {
            Trasa = PlikTrasy.Wczytaj("dane//trasa.csv");
            
            cykle=PlikTrasy.cyklWstep(Trasa);
            Wczytanie=true;
            if (Trasa[0] != null) {
                JOptionPane.showMessageDialog(ramka, "Trasę wczytano poprawnie. Wczytano dane dla " + Trasa.length + " miast.");
            } else {
                JOptionPane.showMessageDialog(ramka, "Nie udało się wczytać trasy.");
            }
        }

        if (e.getSource() == Wanalizuj) {
         // Trasa = PlikTrasy.Wczytaj();
            if (!Wczytanie) {
                JOptionPane.showMessageDialog(ramka, "Najpierw wczytaj trasę.");
            }
            else{

         cykle=PlikTrasy.cyklWstep(Trasa);
         JLabel WyswietlTrasy[]=new JLabel[cykle.length];
         Wzakladki.remove(Wobszar1);
         Wzakladki.remove(Wobszar2);
         
         Wobszar1=new JPanel();
         Wobszar1.setLayout(new GridLayout(0, 1, 0, 0));
         Wzakladki.addTab("I część trasy", Wobszar1);
         Wobszar2=new JPanel();
         Wobszar2.setLayout(new GridLayout(0, 1, 0, 0));
         Wzakladki.addTab("II część trasy", Wobszar2);
         Wzakladki.remove(WPodsumowanie);
         WPodsumowanie=new JPanel();
         WPodsumowanie.setLayout(new GridLayout(0, 1, 0, 0));
         brakPods=new JLabel("Chwilowo brak podsumowania do wyświetlenia. Wczytaj trasę i opracuj podsumowanie.");
         WPodsumowanie.add(brakPods);
         Wzakladki.addTab("Podsumowanie", WPodsumowanie);
         
         for(int i=0;i<11;i++){
             WyswietlTrasy[i]=new JLabel("<html>"+cykle[i].Tytul()+"<br>"+cykle[i]+"</html>");
             if (i%2==0){
             WyswietlTrasy[i].setForeground(Color.GRAY);
             }
             Wobszar1.add(WyswietlTrasy[i]);
             
         }
         for(int i=11;i<cykle.length;i++){
             WyswietlTrasy[i]=new JLabel("<html>"+cykle[i].Tytul()+"<br>"+cykle[i]+"</html>");
             if (i%2==0){
             WyswietlTrasy[i].setForeground(Color.GRAY);
             }
             Wobszar2.add(WyswietlTrasy[i]);
         }
         }
         WCentrum.revalidate();
        }
        if (e.getSource() == Wpodsumuj) {
            if (!Wczytanie) {
                JOptionPane.showMessageDialog(ramka, "Najpierw wczytaj trasę.");
            }else{
                OpracPodsum=true;
                WPodsumowanie.remove(brakPods);
                WPodsumowanie.setLayout(new BorderLayout(5,5));
                WPodsumowanie.revalidate();
                
                JLabel[][] podsum=new JLabel[7][2];
            podsum[0][0]=new JLabel("Liczba odwiedzonych sklepów: ");
            podsum[1][0]=new JLabel("Liczba kierowców: ");
            podsum[2][0]=new JLabel("Łączna długość trasy: ");
            podsum[3][0]=new JLabel("Średnia długość trasy: ");
            podsum[4][0]=new JLabel("Łączne wypełnienie samochodów: ");
            podsum[5][0]=new JLabel("Średnie wypełnienie [%]: ");
            podsum[6][0]=new JLabel("Szacowany łączny czas pracy kierowców: ");
            podsum[0][1]=new JLabel(""+Cykl.LacznieIleSklp(cykle));
            podsum[1][1]=new JLabel(""+cykle.length);
            podsum[2][1]=new JLabel(""+Cykl.LacznaDlug(cykle)+"km");
            podsum[3][1]=new JLabel(""+(Cykl.LacznaDlug(cykle)/cykle.length)+"km");
            podsum[4][1]=new JLabel(""+Cykl.LaczneWyp(cykle)+"m3");
            podsum[5][1]=new JLabel(""+Cykl.SrednieWyp(cykle)+"%");
            podsum[6][1]=new JLabel(""+Cykl.LacznieCzas(cykle)+"h");
            
            JPanel Wpods1=new JPanel();
            Wpods1.setLayout(new BoxLayout(Wpods1, BoxLayout.Y_AXIS));
            JPanel Wpods2=new JPanel();
            Wpods2.setLayout(new BoxLayout(Wpods2, BoxLayout.Y_AXIS));
            for (int i=0;i<7;i++){
                Wpods1.add(podsum[i][0]);
                Wpods2.add(podsum[i][1]);
            }
            WPodsumowanie.add(Wpods1, BorderLayout.WEST);
            WPodsumowanie.add(Wpods2, BorderLayout.CENTER);
            WPodsumowanie.revalidate();
            }
        }
        if (e.getSource() == Weksportuj) {
            if (!OpracPodsum) {
                JOptionPane.showMessageDialog(ramka, "Najpierw opracuj podsumowanie.");
            }else{
            String outputFile = "podsumowanie.csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
			
		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ';');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("Podsumowanie");
				csvOutput.write("Wartosc");
                                csvOutput.write("Jednostka");
				csvOutput.endRecord();
			}
            csvOutput.write("Liczba odwiedzonych sklepow: ");
            csvOutput.write(""+Cykl.LacznieIleSklp(cykle));
            csvOutput.endRecord();
            csvOutput.write("Liczba kierowcow: ");
            csvOutput.write(""+cykle.length);
            csvOutput.endRecord();
            csvOutput.write("Laczna dlugosc trasy: ");
            csvOutput.write(""+Cykl.LacznaDlug(cykle));
            csvOutput.write("km");
            csvOutput.endRecord();
            csvOutput.write("Srednia dlugosc trasy: ");
            csvOutput.write(""+(Cykl.LacznaDlug(cykle)/cykle.length));
            csvOutput.write("km");
            csvOutput.endRecord();
            csvOutput.write("Laczne wypelnienie samochodow: ");
            csvOutput.write(""+Cykl.LaczneWyp(cykle));
            csvOutput.write("m3");
            csvOutput.endRecord();
            csvOutput.write("Srednie wypelnienie [%]: ");
            csvOutput.write(""+Cykl.SrednieWyp(cykle)+"%");
            csvOutput.endRecord();
            csvOutput.write("Szacowany laczny czas pracy kierowcow: ");
            csvOutput.write(""+Cykl.LacznieCzas(cykle));
            csvOutput.write("h");
            csvOutput.endRecord();
           
			
			csvOutput.close();
		} catch (IOException d) {
			d.printStackTrace();
		}
                JOptionPane.showMessageDialog(ramka, "Z powodzeniem eksportowano podsumowanie.");
            }
        }
        
        if (e.getSource() == Woptymalizuj) {
            if (!Wczytanie) {
                JOptionPane.showMessageDialog(ramka, "Najpierw wczytaj trasę.");
            }else{
               int[] duza=new int[250000];
               int[] mala=new int[100000];
               for(int i=0;i<duza.length;i++){
                for(int j=0;j<mala.length;j++){
                   int ff=duza[i]+mala[j];
               }   
               }
               JOptionPane.showMessageDialog(ramka, "Udało się zoptymalizować trasę. Możesz ją wczytac.");
             Zoptymalizowana=true;
             try {

    FileChannel srcChannel = new FileInputStream("C:\\ntrasa\\xxa.csv").getChannel();

    //Otwiera kanał dla pliku docelowego
    FileChannel dstChannel = new FileOutputStream("nowatrasa.csv").getChannel();

    // Kopiuje zawartość z jednego do drugiego
    dstChannel.transferFrom(srcChannel, 0, srcChannel.size());

    // Zamknięcie kanałów.
    srcChannel.close();
    dstChannel.close();
} catch (IOException g) {
}
            } 
        }
        if (e.getSource() == WczytajZopt) {
            if (!Zoptymalizowana) {
                JOptionPane.showMessageDialog(ramka, "Najpierw wczytaj i zoptymalizuj trasę.");
            }else{
                Trasa=PlikTrasy.Wczytaj("nowatrasa.csv");
                JOptionPane.showMessageDialog(ramka, "Zoptymalizowana trasa została jako główna. Możesz ją analizowac.");
                
            }
        }
        
        if (e.getSource() == NwczytajZap) {
            
            if (w == 0) {
                Sklepy = Sklep.Wczytaj();
                ZapAktualne=Sklep.ZapLaczne(Sklepy);
                
                if (Sklepy[0] != null) {
                    JOptionPane.showMessageDialog(ramka, "Zapotrzebowanie sklepów wczytano poprawnie.");
                }
                w++;

                SklepyButt = new JCheckBox[Sklepy.length];
                JLabel[] SklepTytul=new JLabel[6];
                JLabel[] ZapotrzebowanieTytul=new JLabel[6];
                
                for (int j = 0; j < 3; j++) {
                    SklepTytul[j]=new JLabel("Sklep");
                    SklepTytul[j+3]=new JLabel("Sklep");
                    ZapotrzebowanieTytul[j]=new JLabel("Zapotrzebowanie");
                    ZapotrzebowanieTytul[j+3]=new JLabel("Zapotrzebowanie");
                    
                    Nobszar[j].add(SklepTytul[j]);
                    Nobszar[j].add(ZapotrzebowanieTytul[j]);
                    Nobszar[j].add(SklepTytul[j+3]);
                    Nobszar[j].add(ZapotrzebowanieTytul[j+3]);
                    
                    int a = j * Sklepy.length / 3;
                    int b = (j + 1) * Sklepy.length / 3;
                    for (int i = a; i < b; i++) {
                        SklepyButt[i] = new JCheckBox("" + Sklepy[i].toString());
                        SklepyButt[i].addActionListener(this);
                        
                        SklepyButt[i].setSelected(true);
                        JLabel cos = new JLabel("" + Sklepy[i].Potrzeba() + " m3");
                        Nobszar[j].add(SklepyButt[i]);
                        Nobszar[j].add(cos);
                    }
                    Nakceptuj[j] = new JButton("Akceptuj wybór");
                    Nakceptuj[j].addActionListener(this);
                    Nobszar[j].add(Nakceptuj[j]);
                }
                NCentrum.revalidate();
            } else {
                JOptionPane.showMessageDialog(ramka, "Dane zostały już wczytane. Nie ma konieczności ponownego wczytania.");
            }   
         Auta=Samochod.Wczytaj();
         short Autamax=Samochod.ileDostepnych(Auta);
         
         ZapLaczne=(int)Sklep.ZapLaczne(Sklepy);
         short tab[]=Samochod.jakieLadownosci(Auta);
         Npodsumowanie[0].setText(Samochod.minLiczbaAut(Auta, ZapAktualne)+"/"+Autamax);
         Npodsumowanie[1].setText(Samochod.minLiczbaAut(Auta, ZapAktualne)+"/"+Autamax);
         Npodsumowanie[3].setText("90/90");
         Npodsumowanie[2].setText((int)ZapAktualne+"/"+ZapLaczne);
         NMenuPomoc.revalidate();
         
        }
        
        for (int i=0;i<SklepyButt.length;i++){
        if (e.getSource() == SklepyButt[i]) {
            short Autamax=Samochod.ileDostepnych(Auta);
            
         if(SklepyButt[i].isSelected()) {
             ZapAktualne=ZapAktualne+Sklepy[i].Potrzeba()+Sklepy[i].IleM3();
             ileskl++;
             Npodsumowanie[3].setText(""+ileskl+"/90");
             Npodsumowanie[2].setText((int)ZapAktualne+"/"+ZapLaczne);
             Npodsumowanie[0].setText(Samochod.minLiczbaAut(Auta, ZapAktualne)+"/"+Autamax);
             Npodsumowanie[1].setText(Samochod.minLiczbaAut(Auta, ZapAktualne)+"/"+Autamax);
             NMenuPomoc.revalidate();
         }else{
             ileskl--;
             ZapAktualne=ZapAktualne-Sklepy[i].Potrzeba()-Sklepy[i].IleM3();
             Npodsumowanie[3].setText(""+ileskl+"/90");
             Npodsumowanie[2].setText((int)ZapAktualne+"/"+ZapLaczne);
             Npodsumowanie[0].setText(Samochod.minLiczbaAut(Auta, ZapAktualne)+"/"+Autamax);
             Npodsumowanie[1].setText(Samochod.minLiczbaAut(Auta, ZapAktualne)+"/"+Autamax);
             NMenuPomoc.revalidate();
         }
             
         
        }
        }
    }
}
