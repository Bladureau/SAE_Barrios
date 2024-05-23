package iut.sae.td1.reel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * La classe Huffman contient la méthode principale pour créer l'arbre de Huffman
 * et générer les codes pour chaque symbole.
 */
public class CalculCaractere {
    
    // Le fichier à lire
    static File fichierALire = new File("FichierACompter.txt");

    private int compteurOccurence = 0;

    private static double tauxApparition = 0.0;

    private static int count = 0;

    private static char lettre = ' ';

    private static String lettreStr;

    HashMap<String,Double> assembleur = new HashMap<String,Double>();

    /**
     * Permet de trier un tableau en utilisant la méthode par insertion
     * @param tab tableau a trier
     */

    /*
    public static void triInsertion(int tab[]) {
        int taille = tab.length;
        for (int indexI = 1; indexI < taille; indexI++) {
            int valeurI = tab[indexI];
            int valeurJ = indexI-1;

            while (valeurJ >= 0 && tab[valeurJ] > valeurI) {
                tab[valeurJ+1] = tab[valeurJ];
                valeurJ--;
            }
            tab[valeurJ+1] = valeurI;
        }
    }
    */
    /**
     * Permet de savoir le nombre de lettre total dans un fichier 
     * @return le nombre de caractères dans le fichier
     * @throws IOException si le fichier n'arrive pas à être lu ou
     *                     ou qu'il n'existe pas.
     */
    private static int nombreCaractereTotal(String nomFichier) throws IOException {
        fichierALire = new File(nomFichier);
        FileReader fr = new FileReader(fichierALire);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            for (char c : line.toCharArray()) {
                if (Character.isDefined(c)) {
                    count++;
                }
            }
        }
        
        br.close();
        fr.close();
    
        return count;
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
        int compteurOccurence = 0;
        for (int i = 0; i < chaineACompter.length(); i++) {
            if (chaineACompter.charAt(i) == lettreAComparer.charAt(0)) {
                compteurOccurence++;
            }
        }
        String compteurStr = String.valueOf(compteurOccurence);
        return compteurStr;
    }


    /**
     * Permet de 
     * @param nomFichier
     * @return
     * @throws IOException
     */
    private static String lettreFichier(String nomFichier) throws IOException {
        fichierALire = new File(nomFichier);
        FileReader fr = new FileReader(fichierALire);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            for (char c : line.toCharArray()) {
                if (Character.isDefined(c)) {
                    lettre = c;
                    lettreStr = String.valueOf(c);
                }
            }
            br.close();
            fr.close();
        }
        return lettreStr;
    }
    
    public int getCount() {
        return count;
    }

    public int getNombreCaractere() {
        return compteurOccurence;
    }

    public static double calculTauxApparition(int occurenceLettre, int occurenceTotal) {
        tauxApparition = occurenceLettre/occurenceTotal;
        return tauxApparition;
    }
    
    public String getLettre() {
        return lettreStr;
    }

    public static HashMap<String,Double> assemblerLettreTauxAppartion(String lettre, double tauxApparition)  {
        HashMap<String,Double> assembleur = new HashMap<String,Double>();
        assembleur.put(lettre, tauxApparition);
        return assembleur;
    }

    public static HashMap<String, Double> triAvecValeur(HashMap<String, Double> map){
        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 ) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<String, Double> map_apres = new LinkedHashMap<String, Double>();
        for(Map.Entry<String, Double> entry : list)
        map_apres.put( entry.getKey(), entry.getValue() );
        return map_apres;
    }
    
    public static void main(String[] args) throws IOException {
        String fileName = "FichierACompter.txt"; // Remplacez par le nom de votre fichier

        
        System.out.println(triAvecValeur(assemblerLettreTauxAppartion(lettreStr, tauxApparition)));
    }
}