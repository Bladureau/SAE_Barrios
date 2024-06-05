/*
 * CalculCaractere.java                                     30 avril 2024
 * IUT de Rodez, TD1, pas de copyright
 */
package iut.sae.td1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * La classe CalculCaractere sert a calculer le nombre d'appartition
 * d'une lettre dans un fichier texte.
 * @author MANZAN--MONS Tess
 * @author LADUREAU Baptiste
 * @author BONAFIS Louis
 * @author ALIBERT Marylou
 * @author DAURES Johan
 * @version 3.0
 */
public class CalculCaractere {
    
    /** La fréquence du caractère. */
    public static double[] frequences;

    /** Nombre de caracteres différents dans le fichier. */
    public static int nbCaracteresDifferents;

    /** Nombre d'occurences de chaque caractères. */
    public static double[] occurenceCaracteres;

    /** Liste des caractères a encoder. */
    public static String[] caracteres;

    /** Nombre total de caractères dans le fichier texte. */
    public static int nbCaracteresTotal;

    /**
     * Extraie toutes les lettres d'un fichier.
     * @param fichier Le nom du fichier qui doit être analysé.
     * @return Un tableau de String
     * @throws IOException si le fichier n'est pas détecter ou qu'il y a un probleme de lecture
     */
    public static String[] extraireLettresTableauString(String fichier) throws IOException {
        String[] caracteres = new String[257];
        int indice = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            int c;
            while ((c = br.read()) != -1) {
                char lettre = (char) c;
                boolean estPresent = false;
                for (int i = 0; i < indice; i++) {
                    if (String.valueOf(lettre).equals(caracteres[i])) {
                        estPresent = true;
                        break;
                    }
                }
                if (!estPresent) {
                    caracteres[indice] = String.valueOf(lettre);
                    indice++;
                }
            }
            return Arrays.copyOf(caracteres, indice);
        }
    }

    /**
    * Calcule le nombre d'occurences de chaque lettre dans le fichier texte.
    * @param caracteres 
    * @param fichier
    * @return un tableau de double contenant les fréquences.
    * @throws IOException si le fichier n'est pas détecter ou qu'il y a un probleme de lecture
    */
    public static double[] nombreOccurencesLettres(String[] caracteres, String fichier) throws IOException {
        double[] occurences = new double[caracteres.length];
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            int c;
            while ((c = br.read()) != -1) {
                char lettre = (char) c;
                for (int i = 0; i < caracteres.length; i++) {
                    if (String.valueOf(lettre).equals(caracteres[i])) {
                        occurences[i]++;
                        break;
                    }
                }
            }
            return occurences;
        }
    }

    /**
    * Calcul le taux d'apparition de chaque lettre
    */
    public static void calculerTauxApparition() {
        for (int i = 0; i < occurenceCaracteres.length; i++) {
            occurenceCaracteres[i] = occurenceCaracteres[i] / nbCaracteresTotal;
        }
    }
}
