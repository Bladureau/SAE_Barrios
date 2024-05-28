/* arbreBinaire                        27/05/2024
 * IUT rodez info1 2023-2024 , aucun droit d'auteur
 */
package iut.sae.td1.reel;

/**
 * Construire un arbre binaire (chaque noeud a au plus 2 fils), à 
 * partir de deux liste, une avec tous les caractères présent dans le
 * fichier et une avec leur fréquence correspondante dans le texte. 
 *
 * @author  ALIBERT Marylou, BONAFIS Louis, DAURES Johan, 
 *  LADUREAU Baptiste, MANZAN--MONS Tess
 */
public class ArbreBinaire  {

	private char caractereValeur;
	private ArbreBinaire filsDroit;
	private ArbreBinaire filsGauche;
	
	public ArbreBinaire(char caractere) {
		caractereValeur = caractere;
		//filsDroit = filsDroitConst;
		//filsGauche = filsGaucheConst;
		
	}
	
	public char getValue(){
		return this.caractereValeur;
	}
	
	public void setValue(char nouvelleValeur) {
		this.caractereValeur = nouvelleValeur;
	}
	
	public ArbreBinaire getFilsDroit() {
		return this.filsDroit;
	}
	
	public void setFilsDroit(final ArbreBinaire PointeurVersAutreArbre) {
		this.filsDroit = PointeurVersAutreArbre;
	}
	
	public ArbreBinaire getFilsGauche() {
		return this.filsGauche;
	}

    public void setFilsGauche(final ArbreBinaire PointeurVersAutreArbre) {
		this.filsGauche = PointeurVersAutreArbre;
	}
    
	public String toString() {
		return this.caractereValeur 
			    + "\n fils Droit : " + this.filsDroit.getValue()
				+ "\n fils Gauche : " + this.filsGauche.getValue();
	}
}

