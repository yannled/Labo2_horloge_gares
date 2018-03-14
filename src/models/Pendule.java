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
    private int minutes = 0;       	// Compteurs de la pendule
    private int secondes = 0;
    private int heures = 0;

    private Thread time;

    public Pendule (int valSeconde) {

	    dureeSeconde = valSeconde;

	    time = new Thread(this);
	    time.start();
   }

    public void incrementerSecondes(){
    	secondes ++;
        if (secondes == 60) {   
        	secondes = 0;
        }
    }

	public void run(){
		while (true) {
			try{
				Thread.sleep(dureeSeconde);
			}
			catch(InterruptedException e){
			}

			incrementerSecondes();
			setChanged();
			notifyObservers(new Integer[]{secondes, minutes, heures});
		}
	}

    public void incrementerMinutes() {
      minutes = ++minutes % 60 ;
      if (minutes == 0) {
          heures = ++heures % 24;
      }
    }

    public void update(Observable o, Object p){
		 secondes = 0;
		 incrementerMinutes();
	 }
}
