import models.Pendule;
import models.Emetteur;
import views.VueEmetteur;
import views.VuePendule;

/****************************************************************
 * Auteur:	    Eric Lefrançois                                 *
 * Groupe:	    HES_SO  Informatique & Télécommunications       *
 * Fichier:     1er Octobre 2015-  DEPART		                *
 * Projet:	    Horloges synchronisées                          *
 ****************************************************************
*/




public class Amorce {
    public static void main (String argv[]){

        Emetteur emetteur = new Emetteur(140);  	// Emetteur avec une seconde de 100msec
        VueEmetteur vueEmetteur1 = new VueEmetteur(emetteur);
        emetteur.addObserver(vueEmetteur1);


        // Création d'une pendule, avec une seconde valant 120msec (plus lente que l'emetteur
        Pendule pendule1 = new Pendule(120);
        emetteur.addMinuteObserver(pendule1);
        VuePendule vuePendule1 = new VuePendule(pendule1, "H1", 100,0);
        pendule1.addObserver(vuePendule1);

        Pendule pendule2 = new Pendule(800);
        emetteur.addMinuteObserver(pendule2);
        VuePendule vuePendule2 = new VuePendule(pendule2, "H2", 100,0);
        pendule2.addObserver(vuePendule2);

        Pendule pendule3 = new Pendule(1200);
        emetteur.addMinuteObserver(pendule3);
        VuePendule vuePendule3 = new VuePendule(pendule3, "H3", 100,0);
        pendule3.addObserver(vuePendule3);

        Pendule pendule4 = new Pendule(120);
        emetteur.addMinuteObserver(pendule4);
        VuePendule vuePendule4 = new VuePendule(pendule4, "H4", 100,0);
        pendule4.addObserver(vuePendule4);

        Pendule pendule5 = new Pendule(120);
        emetteur.addMinuteObserver(pendule5);
        VuePendule vuePendule5 = new VuePendule(pendule5, "H5", 100,0);
        pendule5.addObserver(vuePendule5);

    }
}
