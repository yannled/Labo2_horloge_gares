package views;

import models.Pendule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class VuePendule extends JFrame implements Observer {
   private static int TAILLE = 100; // Taille de la demi-fenetre

   private int minutes = 0;       	// Compteurs de la pendule
   private int secondes = 0;
   private int heures = 0;

   private VuePendule.ToileGraphique toile;

   private Pendule modelPendule;

   private NumericHour numHours;
   private buttonPlus btnPlus;

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

   class NumericHour extends JLabel {
      public NumericHour() {
         setBackground(Color.gray);
         setSize( 1024, 768 );
         updateNumericHours();
      }
      public void updateNumericHours(){
         String time = heures + ":" + minutes + ":" + secondes;
         this.setText(time);
      }
   }

   class buttonPlus extends JButton{
      public buttonPlus(String str){
         super(str);
      }
      public void updateMin(){
         modelPendule.incrementerMinutes();
      }
   }

   //------------------------------------------------------------------------

   public VuePendule (Pendule modelPendule, String nom, int posX, int posY) {

      toile = new ToileGraphique();
      setTitle(nom);
      getContentPane().add (toile, BorderLayout.CENTER);

      this.modelPendule = modelPendule;

      //Au nord
      btnPlus = new buttonPlus("+");
      btnPlus.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
            btnPlus.updateMin();
         }
      });
      this.getContentPane().add(btnPlus, BorderLayout.NORTH);

      //Au sud
      numHours = new NumericHour();
      this.getContentPane().add(numHours, BorderLayout.SOUTH);

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
      numHours.updateNumericHours();

      toile.repaint();
   }
}
