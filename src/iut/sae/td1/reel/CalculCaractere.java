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
     * @param fichier Le nom du fichier qui doit être analysé.
     * @return Un tableau de String
     * @throws IOException si le fichier n'est pas détecter ou qu'il y a un probleme de lecture
     */
    public static String[] extraireLettresTableauString(String fichier) throws IOException {
        try (FileReader fr = new FileReader(fichier);               // Ouvre le fichier spécifié
             BufferedReader br = new BufferedReader(fr)) {          // Lit le fichier défini dans le FileReader
            StringBuilder sb = new StringBuilder();                 // Permet de construire la châine de caractère plus facilement
            int c;
            while ((c = br.read()) != -1) {                         // Lis le fichier caractères par caractères, tant qu'il ne rencontre pas la fin du fichier
                char lettre = (char) c;                             // Converti c en char puis on le mets dans la variable lettre
                if (Character.isDefined(lettre)) {                  // Si la lettre detecté est une lettre...
                    sb.append(lettre);                              // ... on l'ajoute dans le StringBuilder
                }
            }
            return sb.toString().split("");                   // Transforme le StringBuilder en chaîne de caractères, puis le tableau de String est retourné
        } catch (IOException e) {                                   
            System.err.println("Erreur lors de la lecture du fichier" + e.getMessage());
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
        for (String lettre : lettres) {                             // Cette boucle permet de mettre les éléments du tableau lettres en paramètres, dans la variable lettre
            if (occurences.containsKey(lettre)) {                   // Si la lettre est présente dans la HashMap...
                double count = occurences.get(lettre) + 1;          // ... On récupère le nombre d'occurence de la lettre puis on l'incremente de 1...
                occurences.put(lettre, count);                      // ... Puis on met à jour le nombre d'occurence de la lettre en question.
            } else {                                                // Sinon si la letre n'est pas présente dans la HashMap...
                occurences.put(lettre, 1.0);                  // ... On initialise la valeur de la lettre a 1.0
            }
        }
        return occurences;                                          // On retourne la Map occurences.
    }

    /**
     * Sorts a map by its values in descending order.
     * 
     * @param <K> the type of the keys in the map
     * @param <V> the type of the values in the map, which must implement {@link Comparable}
     * @param map the map to be sorted
     * @return a new map with the same key-value pairs, but sorted by the values in descending order
     */
    public static <K, V extends Comparable<V>> Map<K, V> trierParValeur(final Map<K, V> map) {
        Comparator<K> valueComparator =  new Comparator<K>() {
            public int compare(K k1, K k2) {                            // Méthode de comparaison 
                int compare = map.get(k2)                               // 1) Récupere la clé de k2 
                                 .compareTo(                            // 3) si k2 < k1, compareTo renvoie une valeur < 0 ; si k2 = k1 ca renvoie 0 ; si k2 > k1 ca renvoie une valeur > 0.
                                    map.get(k1));                       // 2) Récupere la clé de k1
                if (compare == 0) return 1;                             // si les deux valeurs sont égales, on renvoie 1 pour garantir un ordre de tri cohérent.
                else return compare;                                    // ... sinon ca renvoie la valeur de compare.
            }
        };
        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);  // On crée une nouvelle Map de type TreeMap pour trié automatiquement les valeurs comparées 
        sortedByValues.putAll(map);                                     // On copie toutes les valeurs de map dans la nouvelle map 
        //System.out.println(sortedByValues);
        return sortedByValues;                                          // On retourne la Map triés 
    }

    /**
     * Calcule les taux d'apparition de chaque lettre.
     * @param occurences La Map contenant les lettres et leurs occurrences.
     * @param nombreLettresTotal Le nombre total de lettres.
     */
    public static Map<String,Double> calculerTauxApparition(Map<String, Double> occurences, int nombreLettresTotal) {
        // System.out.println("\n\nTaux d'apparition des lettres (triés croissants) : ");        // Temporaire (débug)
        Map<String,Double> lettreTaux = new TreeMap<>();
        for (Map.Entry<String, Double> entry : occurences.entrySet()) {                         // La variable entry représente l'entrée courante de la Map, contenant la lettre et le nombre d'occurrences.
            double taux = (entry.getValue() / nombreLettresTotal);                              // Puis on extait la lettre avec entry.getValue()...
            //System.out.println(entry.getKey() + " : " + String.format("%.4f", taux));    // ... et le nbre d'occurences avec entry.getKey() puis formate le taux d'apparition en chaîne de caractères avce 4 chiffres après la virgule.
            lettreTaux.put(entry.getKey(), taux);
            //System.out.println(lettreTaux);
        }
        return lettreTaux;
    }
}