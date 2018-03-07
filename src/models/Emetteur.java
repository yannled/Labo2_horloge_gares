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

public class Emetteur extends Observable implements Runnable{

  private int dureeSeconde ;                    // Durée sec. en msec.
  private int secondes = 0;						// Compteur de secondes

  private Thread time;

// Constructeur
    public Emetteur (int dureeSeconde) {
        this.dureeSeconde = dureeSeconde;

		    time = new Thread(this);
		    time.start();
    }

    public void run(){
       while (true) {
          try{
             Thread.sleep(dureeSeconde);
          }
          catch(InterruptedException e){
          }
          
          heureMettreAJour();
       }
    }

    private void heureMettreAJour () {
        secondes = ++secondes % 60;
        setChanged();
        notifyObservers(secondes);
    }
}
