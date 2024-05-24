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
     * Extraie toutes les lettres d'un fichier.
     * @param fichier Le nom du fichier qui doit être analyser.
     * @return Un tableau de String
     * @throws IOException si le fichier n'est pas détecter ou qu'il y a un probleme de lecture
     */
    public static String[] extraireLettresTableauString(String fichier) throws IOException {
        try (FileReader fr = new FileReader(fichier);               // Ouvre le fichier spécifié
             BufferedReader br = new BufferedReader(fr)) {          // Lit le fichier défini dans le FileReader
            StringBuilder sb = new StringBuilder();                 // Permet de construire la châine de caractère plus facilement
            int c;
            while ((c = br.read()) != -1) {                         // Permet de lire le fichier caractères par caractères tant qu'il ne rencontre pas la fin du fichier
                char lettre = (char) c;                             // Converti c en char puis on le mets dans lettre
                if (Character.isLetter(lettre)) {                   // Si la lettre detecté est une lettre...
                    sb.append(lettre);                              // .. si c'est le cas on l'ajoute dans le StringBuilder
                }
            }
            return sb.toString().split("");                         // Transforme le StringBuilder en chaîne de caractères, puis le tableau de String est retourné
        } catch (IOException e) {                                   
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            return null;
        }
    }

    /**
     * Compte les occurrences de chaque lettre puis renvoi un tableau de String.
     * @param lettres Le tableau de String contenant les lettres à compter.
     * @return Une Map contenant chaque lettre et son nombre d'occurrences.
     */
    public static Map<String, Double> compterOccurencesDouble(String[] lettres) {
        Map<String, Double> occurences = new HashMap<>();           // Initialisation de la HashMap pour stocké les occurences des maps
        for (String lettre : lettres) {                             // Cette boucle permet de mettre les éléments du tableau lettres en paramètres dans la variable lettre
            if (occurences.containsKey(lettre)) {                   // Si la lettre est présente dans la HashMap...
                double count = occurences.get(lettre) + 1;          // ... On récupère le nombre d'occurence de la lettre puis on l'incremente de 1...
                occurences.put(lettre, count);                      // ... Puis on met à jour le nombre d'occurence de la lettre en question.
            } else {                                                // Sinon si la letre n'est pas présente dans la HashMap...
                occurences.put(lettre, 1.0);                        // ... On initialise la valeur de la lettre a 1.0
            }
        }
        return occurences;                                          // On retourne la Map occurences.
    }

    /**
     * Trie les lettres par leur taux d'apparition.
     * @param occurences La Map contenant les lettres et leurs occurrences.
     * @return La Map triée par taux d'apparition.
     */
    public static Map<String, Double> trierTauxApparition(Map<String, Double> occurences) {
        Map<String, Double> occurencesTriees                        // Initialise la map occurencesTriees.
            = new TreeMap<>                                         // Structure qui permet de trier automatiquement les éléments qui la composent.
                (Comparator.comparingDouble(                        // Le Comparator définit comment les éléments de la Map soivent être comparés pour le tri.
                    occurences::get));                              // On compare les nombres d'occurences (de type double) des lettres.
        occurencesTriees.putAll(occurences);                        // Copie les associations clé-valeur de la Map occurences dans la Map occurencesTriees.
        return occurencesTriees;                                    // Retourne la Map triées.
    }

    /**
     * Calcule les taux d'apparition de chaque lettre.
     * @param occurences La Map contenant les lettres et leurs occurrences.
     * @param nombreLettresTotal Le nombre total de lettres.
     */
    public static void calculerTauxApparition(Map<String, Double> occurences, int nombreLettresTotal) {
        System.out.println("\n\nTaux d'apparition des lettres (triés croissants) : ");          // Temporaire (débug)
        for (Map.Entry<String, Double> entry : occurences.entrySet()) {                         // La variable entry représente l'entrée courante de la Map, contenant la lettre et le nombre d'occurrences.
            double taux = (entry.getValue() / nombreLettresTotal);                              // Puis on extait la lettre avec entry.getValue()...
            System.out.println(entry.getKey() + " : " + String.format("%.4f", taux));           // ... et le nbre d'occurences avec entry.getKey() puis formate le taux d'apparition en chaîne de caractères avce 4 chiffres après la virgule.
        }
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
    }
}
