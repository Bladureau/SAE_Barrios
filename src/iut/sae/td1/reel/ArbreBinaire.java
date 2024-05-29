/* arbreBinaire                        27/05/2024
 * IUT rodez info1 2023-2024 , aucun droit d'auteur
 */
package iut.sae.td1.reel;

import java.util.Map;

/**
 * Construire un arbre binaire (chaque noeud a au plus 2 fils), à 
 * partir de deux liste, une avec tous les caractères présent dans le
 * fichier et une avec leur fréquence correspondante dans le texte. 
 *
 * @author  ALIBERT Marylou, BONAFIS Louis, DAURES Johan, 
 *  LADUREAU Baptiste, MANZAN--MONS Tess
 */
public class ArbreBinaire  {
	/** Le caractere a analyser */
	String caractere;

	/** Le tableau qui permet de stocker les caractères deja analyser  */
	String[] listeCaractere;

	/** Le taux d'apparition de la lettre a analyser */
	double frequence;
	
	/** Le tableau qui permet de stocker les taux d'apparition de la lettre deja analyser  */
	double[] listeFrequence;
	
	ArbreBinaire droite;
	ArbreBinaire gauche;
	
	/**
	 * 
	 * @param lettreTaux 
	 */
	public ArbreBinaire(Map<String,Double> lettreTaux) {
		for (Map.Entry<String,Double> entry : lettreTaux.entrySet()) {
			this.caractere = entry.getKey();
			this.frequence = entry.getValue();
		}

		// Créer une node et affecter dasn la boucle les caractères et fréquences a la node.
		this.gauche = null;
		this.droite = null;
		// System.out.print(caractere + " : ");
		// System.out.println(frequence);
	}
	
	public String getValue(){
		return this.caractere;
	}
	
	public void setValue(String nouvelleValeur) {
		this.caractere = nouvelleValeur;
	}
	
	public ArbreBinaire getFilsDroit() {
		return this.droite;
	}
	
	public void setFilsDroit(final ArbreBinaire PointeurVersAutreArbre) {
		this.droite = PointeurVersAutreArbre;
	}
	
	public ArbreBinaire getFilsGauche() {
		return this.gauche;
	}

    public void setFilsGauche(final ArbreBinaire PointeurVersAutreArbre) {
		this.gauche = PointeurVersAutreArbre;
	}
    
	public String toString() {
		return this.caractere 
			    + "\n fils Droit : " + this.droite.getValue()
				+ "\n fils Gauche : " + this.gauche.getValue();
	}
}
