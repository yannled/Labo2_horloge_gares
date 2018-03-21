/********************************************************************
 * Auteur:	    Eric Lefrançois                                     *
 * Groupe:	    HES_SO/EIG  Informatique & Télécommunication        *
 * Fichier:     Pendule.java                                        *
 * Date :	    1er Octobre 2015-  DEPART                 		    *
 * Projet:	    Horloges synchronisées                              *
 ********************************************************************
 */

package models;

import java.awt.*;
import java.lang.Math;
import java.lang.reflect.Array;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class Pendule extends Observable implements Runnable, Observer {
//Classe qui décrit une montre avec un affichage des aiguilles

   private int dureeSeconde;       // Durée de la seconde en msec.
   private int minutes = 0;         // Compteurs de la pendule
   private int secondes = 0;
   private int heures = 0;

   private Thread time;

   // using à monitor for making the pendul wait for
   private static final Object monitor = new Object();
   private static boolean monitorState = false;

   private boolean incremented = false;


   public Pendule(int valSeconde) {

      dureeSeconde = valSeconde;

      time = new Thread(this);
      time.start();
   }

   public void incrementerSecondes() {
      secondes++;
      if (secondes == 60) {
         secondes = 0;
         incrementerMinutes();
         incremented = true;
      }
   }

   public void run() {
      while (true) {
         try {
            Thread.sleep(dureeSeconde);
         } catch (InterruptedException e) {
         }

         incrementerSecondes();
         setChanged();
         notifyObservers(new Integer[]{secondes, minutes, heures});

         if(incremented) {
            monitorState = true;
            while (monitorState) {
               try {
                  monitor.wait();
               } catch (Exception e) {

               }
            }
         }
      }
   }

   synchronized public void incrementerMinutes() {
      minutes = ++minutes % 60;
      if (minutes == 0) {
         heures = ++heures % 24;
      }
   }

   public void update(Observable o, Object p) {
      secondes = 0;
      if(!incremented){
         incrementerMinutes();
      }
      synchronized (monitor) {
         monitorState = false;
         incremented = false;
         monitor.notifyAll();
      }
   }
}
