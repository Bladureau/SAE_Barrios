package iut.sae.td1.reel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * La classe CalculCaractere contient des méthodes pour compter les occurrences d'un caractère spécifique dans une chaîne,
 * extraire les lettres d'un fichier dans un tableau de String, compter les occurrences de chaque lettre dans le tableau,
 * trier les lettres par leur taux d'apparition, calculer les taux d'apparition de chaque lettre,
 * et assembler un HashMap contenant une lettre et son taux d'apparition.
 */
public class CalculCaractere {
    /**
     * Compte le nombre d'occurrences d'un caractère spécifique dans une chaîne.
     *
     * @param chaineACompter La chaîne dans laquelle compter les occurrences du caractère.
     * @param lettreAComparer Le caractère dont il faut compter les occurrences.
     * @return Le nombre d'occurrences du caractère dans la chaîne.
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
     * Compte les occurrences de chaque lettre dans un tableau de String.
     *
     * @param lettres Le tableau de String contenant les lettres à compter.
     * @return Une Map contenant chaque lettre et son nombre d'occurrences.
     */
    public static String[] extraireLettresTableauString(String fichier) throws IOException {
        try (FileReader fr = new FileReader(fichier);
             BufferedReader br = new BufferedReader(fr)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = br.read()) != -1) {
                char lettre = (char) c;
                if (Character.isLetter(lettre)) {
                    sb.append(lettre);
                }
            }
            return sb.toString().split(""); // Sépare la chaîne en un tableau de String
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            return null;
        }
    }

    /**
     * Trie les lettres par leur taux d'apparition.
     *
     * @param occurences La Map contenant les lettres et leurs occurrences.
     * @return La Map triée par taux d'apparition.
     */
    public static Map<String, Double> compterOccurencesDouble(String[] lettres) {
        Map<String, Double> occurences = new HashMap<>();
        for (String lettre : lettres) {
            if (occurences.containsKey(lettre)) {
                double count = occurences.get(lettre) + 1; // On utilise un double pour les occurrences
                occurences.put(lettre, count);
            } else {
                occurences.put(lettre, 1.0); // Valeur initiale de 1.0
            }
        }
        return occurences;
    }

    /**
     * Calcule les taux d'apparition de chaque lettre.
     *
     * @param occurences La Map contenant les lettres et leurs occurrences.
     * @param nombreLettresTotal Le nombre total de lettres.
     */
    public static Map<String, Double> trierTauxApparition(Map<String, Double> occurences) {
        Map<String, Double> occurencesTriees = new TreeMap<>(Comparator.comparingDouble(occurences::get)); // Trie par valeur (occurrences)
        occurencesTriees.putAll(occurences);
        return occurencesTriees;
    }

    /**
     * Assemble une lettre et son taux d'apparition dans un HashMap.
     *
     * @param lettre La lettre à assembler.
     * @param tauxApparition Le taux d'apparition de la lettre.
     * @return Le HashMap contenant la lettre et son taux d'apparition.
     */
    public static void calculerTauxApparition(Map<String, Double> occurences, int nombreLettresTotal) {
        System.out.println("\n\nTaux d'apparition des lettres (triés croissants) : ");
        for (Map.Entry<String, Double> entry : occurences.entrySet()) {
            double taux = (entry.getValue() / nombreLettresTotal) * 100; // Taux en pourcentage
            System.out.println(entry.getKey() + " : " + String.format("%.2f", taux) + "%"); // Formatage à 2 décimales
        }
    }

    public static HashMap<String,Double> assemblerLettreTauxAppartion(String lettre, double tauxApparition)  {
        HashMap<String,Double> assembleur = new HashMap<String,Double>();
        assembleur.put(lettre, tauxApparition);
        return assembleur;
    }
    
    public static void main(String[] args) throws IOException {
        String fichier = "FichierACompter.txt"; // Remplacez par le nom de votre fichier

        String[] lettres = extraireLettresTableauString(fichier);
        if (lettres != null) {
            int nombreLettresTotal = lettres.length;

            Map<String, Double> occurences = compterOccurencesDouble(lettres);
            Map<String, Double> occurencesTriees = trierTauxApparition(occurences);
            calculerTauxApparition(occurencesTriees, nombreLettresTotal);
        } else {
            System.err.println("Échec de l'extraction des lettres.");
        }
        // System.out.println(triAvecValeur(assemblerLettreTauxAppartion(lettreStr, tauxApparition)));
    }
}