package iut.sae.td1;

import java.io.IOException;
import java.util.Scanner;

public class CompresserDecompresser {
	
	private static Scanner analyseurEntree = new Scanner(System.in);
	
	/**
	 * Méthode d'appel de la compression du fichier choisi
	 * @throws IOException
	 */
	public static void compresser() throws IOException {
		
		System.out.print("Entrez le nom du fichier source (format : nom_du_fichier.txt) : ");
        String fichierSource = analyseurEntree.nextLine();
        System.out.print("\nEntrez le nom du fichier de destination (format : nom_du_fichier.bin) : ");
        String fichierDestination = analyseurEntree.nextLine();
        ArbreBinaire.encoderFichier(fichierSource, fichierDestination);
	}
	
	/**
	 * Méthode d'appel de la décompression du fichier choisi
	 */
	public static void decompresser() {
		System.out.print("Entrez le nom du fichier source (format : nom_du_fichier.bin) : ");
		String fichierSource = analyseurEntree.nextLine();
        System.out.print("\nEntrez le nom du fichier de destination (format : nom_du_fichier.txt) : ");
        String fichierDestination = analyseurEntree.nextLine();
		ArbreBinaire.decoderFichier(fichierSource, fichierDestination);
	}
	
	/**
	 * Sauvegarde les caractères ainsi que leur code binaire et leur code de Huffman
	 * dans un autre fichier texte choisi par l'utilisateur
	 * @throws IOException
	 */
	public static void sauvegarder() throws IOException {
		System.out.print("Entrez le nom du fichier source (format : nom_du_fichier.txt) : ");
        String fichierSource = analyseurEntree.nextLine();
        System.out.print("\nEntrez le nom du fichier de destination (format : nom_du_fichier.txt) : ");
        String fichierSauvegarde = analyseurEntree.nextLine();
        ArbreBinaire.sauvegarderArbre(fichierSource, fichierSauvegarde);
        System.out.println("Fichier sauvegardé");
	}
	
	public static void quitter() {
		System.out.println("Au revoir");
        System.exit(1);
	}
	
	public static void main(String[] args) throws IOException {

        System.out.println("Sélectionnez une action :");
        System.out.println("1. Compression");
        System.out.println("2. Décompression");
        System.out.println("3. Sauvegarde de l'arbre");
        System.out.println("4. Quitter l'application");
        System.out.print("Votre choix : ");

        int choix = analyseurEntree.nextInt();
        analyseurEntree.nextLine(); // Consommer le retour chariot
        
        switch(choix) {
        case 1 :
        	compresser();
        	break;
        case 2 :
        	decompresser();
        	break;
        case 3 :
        	sauvegarder();
        	break;
        case 4 :
        	quitter();
        	break;
        }
	}
}
