/* arbreBinaire                        27/05/2024
 * IUT rodez info1 2023-2024 , aucun droit d'auteur
 */
package iut.sae.td1.reel;

import java.util.Map;

/**
 * Construire un arbre binaire (chaque noeud a au plus 2 fils), à 
 * partir de deux liste, une avec tous les caractères présent dans le
 * fichier et une avec leur fréquence correspondante dans le texte. 
 * @author ALIBERT Marylou, BONAFIS Louis, DAURES Johan, 
 *         LADUREAU Baptiste, MANZAN--MONS Tess
 */
public class ArbreBinaire  {

	String caractere;
	double frequence;
	ArbreBinaire droit;
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
		this.droit = null;
		this.gauche = null;
		//System.out.print(caractere + " : ");
		//System.out.println(frequence);
	}
}



