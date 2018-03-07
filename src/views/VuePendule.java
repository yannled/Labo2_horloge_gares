package views;

import models.Pendule;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VuePendule extends JFrame implements Observer {
   private static int TAILLE = 100; // Taille de la demi-fenétre

   private int minutes = 0;       	// Compteurs de la pendule
   private int secondes = 0;
   private int heures = 0;

   private VuePendule.ToileGraphique toile;

   //------------------------------------------------------------------------
   class ToileGraphique extends JPanel {

      public ToileGraphique() {
         setBackground(Color.white);
      }

      public void paintComponent (Graphics g) {
         super.paintComponent(g);
         dessinerAiguilles (g);
      }

      public Dimension getPreferredSize() {
         return new Dimension (2*TAILLE, 2*TAILLE);
      }

      public void dessinerAiguilles(Graphics g) {
         // calculer les coordonnées des aiguilles
         int cosxm = (int)(TAILLE + (TAILLE/2)*
                 Math.cos(2*((double)minutes/60*Math.PI - Math.PI/4)));
         int sinym = (int)(TAILLE + (TAILLE/2)*
                 Math.sin(2*((double)minutes/60*Math.PI - Math.PI/4)));
         int cosxh = (int)(TAILLE+(TAILLE/4)*
                 Math.cos(2*((double)heures/12*Math.PI - Math.PI/4)));
         int sinyh = (int)(TAILLE+(TAILLE/4)*
                 Math.sin(2*((double)heures/12*Math.PI - Math.PI/4)));

         g.setColor(Color.red);
         g.drawLine(TAILLE,TAILLE,
                 (int)(TAILLE+(TAILLE-20.0)*
                         Math.cos(2*((double)secondes/60*Math.PI - Math.PI/4))),
                 (int) (TAILLE+(TAILLE-20)*
                         Math.sin(2*((double)secondes/60*Math.PI - Math.PI/4))));

         g.setColor(Color.blue);
         g.drawLine(TAILLE,TAILLE,cosxm,sinym);
         g.drawLine(TAILLE,TAILLE,cosxh,sinyh);
      }
   }
   //------------------------------------------------------------------------

   public VuePendule (String nom, int posX, int posY) {

      toile = new ToileGraphique();
      setTitle(nom);
      getContentPane().add (toile, BorderLayout.CENTER);

      pack();
      setResizable(false);
      setLocation (posX, posY);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }

   public void update(Observable o, Object p){
      secondes = ((Integer[])p)[0];
      minutes = ((Integer[])p)[1];
      heures = ((Integer[])p)[2];
      toile.repaint();
   }
}
