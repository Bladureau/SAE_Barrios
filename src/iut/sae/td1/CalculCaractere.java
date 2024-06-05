/*
 * CalculCaractere.java                                     30 avril 2024
 * IUT de Rodez, TD1, pas de copyright
 */
package iut.sae.td1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    /** Liste des caractères temporaire. */
    public static String[] caracteresTemp = new String[257];
    
    /** La fréquence du caractère. */
    public double[] frequences;

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
        try (FileReader fr = new FileReader(fichier);               // Ouvre le fichier spécifié
             BufferedReader br = new BufferedReader(fr)) {          // Lit le fichier défini dans le FileReader
                int c;
                int indice = 0;
                nbCaracteresTotal = 0;
                boolean estPresent = false;
                while ((c = br.read()) != -1) {                         // Lis le fichier caractères par caractères, tant qu'il ne rencontre pas la fin du fichier
                    char lettre = (char) c;                             // Converti c en char puis on le mets dans la variable lettre
                    estPresent = false;
                    for (int i = 0; i < caracteresTemp.length; i++) {
                        if (String.valueOf(lettre).equals(caracteresTemp[i])) {
                            estPresent = true;
                            break;
                        }
                    }
                    if (!estPresent) {
                        caracteresTemp[indice] = String.valueOf(lettre);
                        indice++;
                    }
                    nbCaracteresTotal++;
                }
                caracteresTemp[256] = Integer.toString(indice);
                return caracteresTemp;
        } catch (IOException e) {                                   
            System.err.println("Erreur lors de la lecture du fichier" + e.getMessage());
            return null;
        }
    }

    /**
     * Calcule le nombre d'occurences de chaque lettre dans le fichier texte.
     * @param caracteres 
     * @param fichier
     * @return un tableau de double contenant les fréquences.
     * @throws IOException si le fichier n'est pas détecter ou qu'il y a un probleme de lecture
     */
    public static double[] nombreOccurencesLettres(String[] caracteres, String fichier) {
        try (FileReader fr = new FileReader(fichier);               // Ouvre le fichier spécifié
             BufferedReader br = new BufferedReader(fr)) {          // Lit le fichier défini dans le FileReader
                double[] nbOccurences = new double[caracteres.length];
                int c;

                while ((c = br.read()) != -1) {                         // Lis le fichier caractères par caractères, tant qu'il ne rencontre pas la fin du fichier
                    char lettre = (char) c;                             // Converti c en char puis on le mets dans la variable lettre
                    for (int i = 0; i < caracteres.length; i++) {
                        if (String.valueOf(lettre).equals(caracteres[i])) {
                            nbOccurences[i] += 1;
                            break;
                        }
                    }
                }
                return nbOccurences;
            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture du fichier" + e.getMessage());
                return null;
            }
    }

    /**
     * Permet de trier les tableaux caracteres et occurenceCaracteres
     */
    public static void triInsertionTableau() {
        int taille = occurenceCaracteres.length;
        for (int i = 1; i < taille; i++) {
            double indexOccurences = occurenceCaracteres[i];
            int j = i-1;
            while(j >= 0 && occurenceCaracteres[j] > indexOccurences) {
                occurenceCaracteres[j+1] = occurenceCaracteres[j];
                j--;
            }
            occurenceCaracteres[j+1] = indexOccurences;
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
