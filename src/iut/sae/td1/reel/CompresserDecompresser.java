package iut.sae.td1.reel;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Essai de mise en oeuvre de l'algorithme de compression et décompression
 * à l'aide des classes iut.sae.td1.reel.CalculCaractere
 *                   et iut.sae.td1.reel.ArbreBinaire
 * @author Louis Bonafis, Tess Manzan--Mons
 */
public class CompresserDecompresser {
	
	/**
	 * appelle les méthodes des classes CalculCaracteres et ArbreBinaire
	 * pour compresser le fichier passé en paramètres
	 * @param fichier fichier à compresser
	 * @throws IOException 
	 */
	private static void compresser(String fichier) throws IOException {
		
		String[] lettres;
		
		lettres = CalculCaractere.extraireLettresTableauString(fichier);
		if (lettres != null) {
            int nombreLettresTotal = lettres.length;

            Map<String, Double> occurences = CalculCaractere.compterOccurencesDouble(lettres);
            Map<String, Double> occurencesTriees = CalculCaractere.trierParValeur(occurences);
            Map<String,Double> mapLettreTaux = CalculCaractere.calculerTauxApparition(occurencesTriees, nombreLettresTotal);
			ArbreBinaire arbreBinaireLettreTaux = new ArbreBinaire(mapLettreTaux);
        } else {
            System.err.println("Échec de l'extraction des lettres.");
        }
		
	}
	
	/**
	 * Demande le nom du fichier à compresser/décompresser
	 * sur ligne de commande (nom_du_fichier.txt)
	 * appelle les fonctions de compression ou décompression 
	 * selon la demande de l'utilisateur
	 * @param args non utilisé
	 * @throws IOException si le nom du fichier est invalide ou n'existe pas.
	 */
	public static void main(String[] args) throws IOException {
		String fichier;
		Scanner analyseurEntree = new Scanner(System.in);
		System.out.print("Entrez le nom du fichier a compréssé suivi de l'extension (nom_du_fichier.txt) : ");
        fichier = analyseurEntree.nextLine();
        analyseurEntree.close();
        compresser(fichier);
	}
}