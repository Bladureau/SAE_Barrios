/*
 * ArbreBinaire.java                                     14 mai 2024
 * IUT de Rodez, TD1, pas de copyright
 */
package iut.sae.td1.reel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Node implements Comparable<Node> {
    /** Le symbole a encoder. */
    String caractere;
	
    /** La fréquence du symbole. */
    double frequence;

    /** Le noeud enfant gauche. */
    Node gauche;
	
    /** Le noeud enfant droit. */
    Node droite;

    /**
     * Construit un nouveau noeud avec le symbole donne.
     * @param symbole Le symbole a encoder.
     * @param fréquence La fréquence du symbole.
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
 * a une lettre.
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
    public static Node createTree(String[] caracteres, double[] frequences) {
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
    public static String[] createCode(Node racine, String[] caracteres) {
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
     * Affiche les codes pour chaque symbole.
     * @param codes Les codes pour chaque symbole.
     * @param caracteres Les symboles a encoder.
     */
    public static void printCodes(String[] codes, String[] caracteres) {
        for (int i = 0; i < caracteres.length; i++) {
            if (caracteres[i].equals(" ")) {
                caracteres[i] = "espace";
            } else if(caracteres[i].equals("\n") || caracteres[i].equals("\r")){
                caracteres[i] = "retour chariot";   
            }
            System.out.println("codeHuffman = " + codes[i] + " ; encode = ? " + "caractere = " + caracteres[i]);
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

        // Create the Huffman tree and generate the Huffman codes
        Node racine = createTree(caracteres, occurenceCaracteres);
        String[] codes = createCode(racine, caracteres);

        // Print the Huffman codes for each character
        printCodes(codes, caracteres);
    }
}