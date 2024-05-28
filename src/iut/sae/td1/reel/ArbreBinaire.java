/* arbreBinaire                        27/05/2024
 * IUT rodez info1 2023-2024 , aucun droit d'auteur
 */
package iut.sae.td1.reel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Construire un arbre binaire (chaque noeud a au plus 2 fils), à 
 * partir de deux liste, une avec tous les caractères présent dans le
 * fichier et une avec leur fréquence correspondante dans le texte. 
 *
 * @author  ALIBERT Marylou, BONAFIS Louis, DAURES Johan, 
 *  LADUREAU Baptiste, MANZAN--MONS Tess
 */
public class ArbreBinaire  {

	String[] caractere;
	double[] frequence;
	ArbreBinaire droit;
	ArbreBinaire gauche;
	
	public ArbreBinaire(Map<String,Double> lettreTaux) {
		for (Map.Entry<String,Double> entry : lettreTaux.entrySet()) {
			this.caractere = entry.getKey();
			this.frequence = entry.getValue();
		}
		this.droit = null;
		this.gauche = null;
		System.out.print(caractere + " : ");
		System.out.println(frequence);
	}
	
	public String getValue(){
		return this.caractere;
	}
	
	public void setValue(String nouvelleValeur) {
		this.caractere = nouvelleValeur;
	}
	
	public ArbreBinaire getFilsDroit() {
		return this.droit;
	}
	
	public void setFilsDroit(final ArbreBinaire PointeurVersAutreArbre) {
		this.droit = PointeurVersAutreArbre;
	}
	
	public ArbreBinaire getFilsGauche() {
		return this.gauche;
	}

    public void setFilsGauche(final ArbreBinaire PointeurVersAutreArbre) {
		this.gauche = PointeurVersAutreArbre;
	}
    
	public String toString() {
		return this.caractere 
			    + "\n fils Droit : " + this.droit.getValue()
				+ "\n fils Gauche : " + this.gauche.getValue();
	}
}

