/* arbreBinaireTest                        28/05/2024
 * IUT rodez info1 2023-2024 , aucun droit d'auteur
 */
package iut.sae.td1.reel;


public class ArbreBinaireTest {
	
	static ArbreBinaire PremierFils = new ArbreBinaire('C');
	static ArbreBinaire DeuxiemeFils = new ArbreBinaire('B');
	static ArbreBinaire Parent = new ArbreBinaire('A'); 
	
		
	public static void main(String[] args) {
			
		System.out.println("valeur du parent : " + Parent.getValue());
			
		Parent.setFilsGauche(new ArbreBinaire('C'));
			
		System.out.println("valeur du fils gauche : " + Parent.getFilsGauche().getValue());
			
		ArbreBinaire toto = Parent.getFilsGauche();
			
		toto.setFilsGauche(new ArbreBinaire('B'));
			
		System.out.println("valeur du fils droit : " + toto.getFilsGauche().getValue());
			
		toto = toto.getFilsGauche();
			
		toto.setFilsGauche(new ArbreBinaire('A'));
		System.out.println("valeur du fils droit : " + Parent.getFilsGauche().getFilsGauche().getFilsGauche().getValue());
			
			
			
		//System.out.println("valeur du parent : " + PremierFils.getValue());
	}
}
