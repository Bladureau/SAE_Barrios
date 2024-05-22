package iut.sae.td1.reel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * La classe Huffman contient la méthode principale pour créer l'arbre de Huffman
 * et générer les codes pour chaque symbole.
 */
public class Huffman {
    
    // Le fichier à lire
    static File fichierALire = new File("FichierACompter.txt");

    // Tableau de symboles 
    String[] symboles = {};

    // Tableau des frequences 
    int[] frequences = {};

    /**
     * Permet de trier un tableau en utilisant la méthode par insertion
     * @param tab tableau a trier
     */
    public static void triInsertion(int tab[]) {
        int taille = tab.length;
        for (int i = 1; i < taille; i++) {
            int index = tab[i];
            int j = i-1;

            while (j >= 0 && tab[j] > index) {
                tab[j+1] = tab[j];
                j--;
            }
            tab[j+1] = index;
        }
    }

    /**
     * Permet de compter le nombre de lettre entre une chaine de 
     * caractère et une lettre choisi
     * @param chaineACompter Chaine de caractère a compter
     * @param lettreACompter Lettre a comparer avec la chaine de 
     *                       caractère.
     * @return  le nombre de lettre en commun entre la chaine de 
     *          caractère et la lettre choisie.
     */
    public static String compterOccurence(String chaineACompter, String lettreAComparer) {
        int compteur = 0;
        for (int i = 0; i < chaineACompter.length(); i++) {
            if (chaineACompter.charAt(i) == lettreAComparer.charAt(0)) {
                compteur++;
            }
        }
        String compteurStr = String.valueOf(compteur);
        return compteurStr;
    }

    /**
     * Permet de savoir le nombre de lettre total dans un fichier 
     * @return le nombre de caractères dans le fichier
     * @throws IOException si le fichier n'arrive pas à être lu ou
     *                     ou qu'il n'existe pas.
     */
    private static int nombreLettreTotal(String nomFichier) throws IOException {
        fichierALire = new File(nomFichier);
        FileReader fr = new FileReader(fichierALire);
        BufferedReader br = new BufferedReader(fr);

        int count = 0;
        String line;
        while ((line = br.readLine()) != null) {
            for (char c : line.toCharArray()) {
                if (Character.isLetter(c)) {
                    count++;
                }
            }
        }
    
        br.close();
        fr.close();
    
        return count;
    }
    


    public static void main(String[] args) throws IOException {
        String fileName = "FichierACompter.txt"; // Remplacez par le nom de votre fichier

        int nombreLettreTotal = nombreLettreTotal(fileName);
        System.out.println("Le nombre total de lettres dans le fichier est : " + nombreLettreTotal);
    }
}