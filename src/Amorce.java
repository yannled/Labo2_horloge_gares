import models.Pendule;
import models.Emetteur;
import views.VueEmetteur;

/****************************************************************
 * Auteur:	    Eric Lefrançois                                 *
 * Groupe:	    HES_SO  Informatique & Télécommunications       *
 * Fichier:     1er Octobre 2015-  DEPART		                *
 * Projet:	    Horloges synchronisées                          *
 ****************************************************************
*/




public class Amorce {
    public static void main (String argv[]){

        Emetteur emetteur = new Emetteur(100);  	// Emetteur avec une seconde de 100msec
        VueEmetteur vueEmetteur1 = new VueEmetteur();
        emetteur.addObserver(vueEmetteur1);


        // Création d'une pendule, avec une seconde valant 120msec (plus lente que l'emetteur
        Pendule pendule = new Pendule("H", 120, 100, 0);


    }
}
