/****************************************************************
 * Auteur:	    Eric Lefrançois                                 *
 * Groupe:	    HES_SO      Informatique & Télécommunications   *
 * Fichier:     Emetteur.java                                   *
 * Date :	      1er Octobre 2016    - Départ             		    *
 * Projet:	    Horloges synchronisées                          *
 ****************************************************************
*/

package models;

import java.util.Observable;
import java.util.Observer;

public class Emetteur extends Observable implements Runnable{

  private int dureeSeconde ;                    // Durée sec. en msec.
  private int secondes = 0;						// Compteur de secondes

  private Thread time;

  private MinuteEmetteur minuteEmetteur;

  private class MinuteEmetteur extends Observable{
     private void minuteUpdate(){
        this.setChanged();
        this.notifyObservers();
     }
  }

// Constructeur
    public Emetteur (int dureeSeconde) {
        this.dureeSeconde = dureeSeconde;

		    time = new Thread(this);
		    time.start();

		    minuteEmetteur = new MinuteEmetteur();
    }

    public void run(){
       while (true) {
          try{
             Thread.sleep(dureeSeconde);
          }
          catch(InterruptedException e){
          }
          
          heureMettreAJour();

          if(secondes == 0)
             minuteEmetteur.minuteUpdate();
       }
    }

    synchronized private void heureMettreAJour () {
        secondes = ++secondes % 60;
        setChanged();
        notifyObservers(secondes);
    }

    public void addMinuteObserver(Observer o){
     minuteEmetteur.addObserver(o);
    }

}
