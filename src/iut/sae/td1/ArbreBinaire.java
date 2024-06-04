/*
 * ArbreBinaire.java                                     14 mai 2024
 * IUT de Rodez, TD1, pas de copyright
 */
package iut.sae.td1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe représentant un noeud dans l'arbre de Huffman.
 */
class Node implements Comparable<Node> {

    /** Le caractere a encoder. */
    String caractere;
	
    /** La fréquence du caractere. */
    double frequence;

    /** Le noeud enfant gauche. */
    Node gauche;
	
    /** Le noeud enfant droit. */
    Node droite;

    /**
     * Constructeur pour créer un nouveau noeud avec le caractère et la fréquence donnés.
     * @param caractere Le caractère à encoder.
     * @param frequence La fréquence du caractère.
     */
    Node(String caractere, double frequence) {
        this.caractere = caractere;
        this.frequence = frequence;
        this.gauche = null;
        this.droite = null;
    }

    /**
     * Méthode qui compare deux nodes en fonction de leur fréquence.
     * @param autre Le node qu'on veut comparer avec la node actuelle.
     * @return Un nombre négatif si le node passe en paramètre a une
     *         fréquence plus grande que la sienne, zéro si ils ont la même
     *         fréquence, ou un nombre positif si le node passe en paramètre a
     *         une fréquence plus petite que la sienne.
     */
    public int compareTo(Node autre) {
        return Double.compare(this.frequence, autre.frequence);
    }
}
/**
 * Créée un arbre binaire et sortir le code de Huffman correspondant
 * a une lettre ainsi que le code binaire du caractère.
 * @author MANZAN--MONS Tess
 * @author LADUREAU Baptiste
 * @author BONAFIS Louis
 * @author ALIBERT Marylou
 * @author DAURES Johan
 * @version 2.0
 */
public class ArbreBinaire {

    /**
     * Crée un arbre de Huffman a partir des symboles et leurs fréquences.
     * @param caracteres Les symboles a encoder (tableau de String).
     * @param frequences Les fréquences des symboles (tableau de double).
     * @return La racine de l'arbre de Huffman.
     */
    public static Node genererArbre(String[] caracteres, double[] frequences) {
        // Crée une liste de nodes, un pour chaque symbole et sa fréquence
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < caracteres.length; i++) {
            nodes.add(new Node(caracteres[i], frequences[i]));
        }

        // Construire l'arbre de Huffman en combinant de manière repetée les deux nodes ayant les fréquences les plus faibles.
        while (nodes.size() > 1) {
            Node gauche = nodes.remove(0);
            Node droite = nodes.remove(0);
            Node parent = new Node(gauche.caractere + droite.caractere, gauche.frequence + droite.frequence);
            parent.gauche = gauche;
            parent.droite = droite;
            nodes.add(parent);
            Collections.sort(nodes);
        }

        // Retourne la racine de l'arbre de Huffman
        return nodes.get(0);
    }

    /**
     * Génère les codes pour chaque symbole a partir de l'arbre de Huffman.
     * @param racine La racine de l'arbre de Huffman.
     * @param caracteres Les symboles a encoder (tableau de String).
     * @return Les codes pour chaque symbole (tableau de String).
     */
    public static String[] genererCode(Node racine, String[] caracteres) {
        String[] codes = new String[caracteres.length];
        StringBuilder code = new StringBuilder();
        dfs(racine, code, codes, caracteres);
        CalculCaractere.triInsertionTableau();
        return codes;
    }

    /**
     * Parcourt en profondeur l'arbre de Huffman et génère les codes pour chaque symbole.
     * @param node Le noeud courant.
     * @param code Le code courant.
     * @param codes Les codes pour chaque symbole.
     * @param caracteres Les symboles a encoder.
     */
    private static void dfs(Node node, StringBuilder code, String[] codes, String[] caracteres) {
        if (node.gauche == null && node.droite == null) {
            // Il s'agit d'un node feuille, il faut donc stocker le code de ce symbole
            for (int i = 0; i < caracteres.length; i++) {
                if (caracteres[i].equals(node.caractere)) {
                    codes[i] = code.toString();
                    break;
                }
            }
        } else {
            // Il s'agit d'un node interne, continuez donc à construire le code en allant à gauche ou à droite
            code.append('0');
            dfs(node.gauche, code, codes, caracteres);
            code.setLength(code.length() - 1);

            code.append('1');
            dfs(node.droite, code, codes, caracteres);
            code.setLength(code.length() - 1);
        }
    }

    /**
     * Convertit un tableau de chaînes de caractères en un tableau de chaînes de caractères binaires.
     * @param caractere Le tableau de chaînes de caractères à convertir.
     * @return Le tableau de chaînes de caractères binaires.
     */
    public static String[] convertirEnBinaireAvecGetBytes(String[] caractere) {
        String[] encodes = new String[caractere.length];
        for (int indexCaractere = 0; indexCaractere < caractere.length; indexCaractere++) {
            byte[] caracteresBinaire = caractere[indexCaractere].getBytes(StandardCharsets.UTF_8);
            StringBuilder resultat = new StringBuilder();
            for (byte compteur : caracteresBinaire) {
                resultat.append(String.format("%8s", Integer.toBinaryString(compteur)).replace(' ', '0'));
            }
            encodes[indexCaractere] = resultat.toString();
        }
        return encodes;
    }

    /**
     * Affiche les codes pour chaque symbole.
     * @param codes Les codes pour chaque symbole.
     * @param caracteres Les symboles a encoder.
     */
    public static void affichierCode(String[] codes, String[] encodes, String[] caracteres) {
        for (int i = 0; i < caracteres.length; i++) {
            if (caracteres[i].equals(" ")) {
                caracteres[i] = "espace";
            } else if(caracteres[i].equals("\n") || caracteres[i].equals("\r")){
                caracteres[i] = "retour chariot";   
            }
            System.out.println("codeHuffman = " + codes[i] + " ; encode = " + encodes[i] + " caractere = " + caracteres[i]);
        }
    }

    /**
     * Main méthode pour tester la classe ArbreBinaire.
     * @param args argument non utilisé
     */
    public static void main(String[] args) throws IOException {
        // Read the text file and store the characters and their frequencies in two arrays
        String fichier = "fichierACompter.txt";
        
        CalculCaractere.caracteresTemp = CalculCaractere.extraireLettresTableauString(fichier);
        String[] caracteresTemp = CalculCaractere.caracteresTemp;

        CalculCaractere.nbCaracteresDifferents = Integer.parseInt(CalculCaractere.caracteresTemp[256]);

        CalculCaractere.occurenceCaracteres = new double[CalculCaractere.nbCaracteresDifferents];
        double[] occurenceCaracteres = CalculCaractere.occurenceCaracteres;

        CalculCaractere.caracteres = new String[CalculCaractere.nbCaracteresDifferents];
        String[] caracteres = CalculCaractere.caracteres;

        
        for (int i = 0; i < caracteres.length; i++) {
            caracteres[i] = caracteresTemp[i];
        }
        
        occurenceCaracteres = CalculCaractere.nombreOccurencesLettres(caracteres, fichier);
        CalculCaractere.triInsertionTableau();
        CalculCaractere.calculerTauxApparition();
        
        String[] encodes = convertirEnBinaireAvecGetBytes(caracteres);

        // Create the Huffman tree and generate the Huffman codes
        Node racine = genererArbre(caracteres, occurenceCaracteres);
        String[] codes = genererCode(racine, caracteres);

        // Print the Huffman codes for each character
        affichierCode(codes, encodes, caracteres);
    }
}