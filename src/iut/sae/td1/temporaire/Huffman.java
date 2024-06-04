/*
 * Huffman              11 mars 2024
 * IUT de Rodez, info1 2023-2024, TD1, aucun droit d'auteur
 */

package iut.sae.td1.temporaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 
 * Cette classe implante le noeud d'un arbre binaire contenant
 * contenant les données nécessaires a l'algorithme de Huffman (le
 * symbole encode et sa fréquence). 
 */
class Node implements Comparable<Node> {
    /** Le symbole a encoder. */
    char symbole;
	
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
    Node(char symbole, double frequence) {
        this.symbole = symbole;
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
 * La classe Huffman contient la méthode principale pour créer l'arbre de Huffman
 * et générer les codes pour chaque symbole.
 */
public class Huffman {
    static char[] symboles = {'a', 'b', 'c', 'd'};
    static double[] frequences = {0.20, 0.40, 0.30, 0.30};

    /**
     * Crée un arbre de Huffman a partir des symboles et leurs fréquences.
     * @param symboles Les symboles a encoder.
     * @param frequences Les fréquences des symboles.
     * @return La racine de l'arbre de Huffman.
     */
    public static Node createTree(char[] symboles, double[] frequences) {
        // Crée une liste de nodes, un pour chaque symbole et sa fréquence
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < symboles.length; i++) {
            nodes.add(new Node(symboles[i], frequences[i]));
        }

        // Construire l'arbre de Huffman en combinant de manière répétée les deux nodes ayant les fréquences les plus faibles.
        while (nodes.size() > 1) {
            Node gauche = nodes.remove(0);
            Node droite = nodes.remove(0);
            Node parent = new Node('\0', gauche.frequence + droite.frequence);
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
     * @return Les codes pour chaque symbole.
     */
    public static String[] createCode(Node racine) {
        String[] codes = new String[symboles.length];
        StringBuilder code = new StringBuilder();
        dfs(racine, code, codes);
        return codes;
    }

    /**
     * Parcourt en profondeur l'arbre de Huffman et génère les codes pour chaque symbole.
     * @param node Le noeud courant.
     * @param code Le code courant.
     * @param codes Les codes pour chaque symbole.
     */
    private static void dfs(Node node, StringBuilder code, String[] codes) {
        if (node.gauche == null && node.droite == null) {
            // Il s'agit d'un node feuille, il faut donc stocker le code de ce symbole
            codes[node.symbole - 'a'] = code.toString();
        } else {
            // Il s'agit d'un node interne, continuez donc à construire le code en allant à gauche ou à droite
            code.append('0');
            dfs(node.gauche, code, codes);
            code.setLength(code.length() - 1);

            code.append('1');
            dfs(node.droite, code, codes);
            code.setLength(code.length() - 1);
        }
    }

    /**
     * Affiche les codes pour chaque symbole.
     *
     * @param codes Les codes pour chaque symbole.
     */
    public static void printCodes(String[] codes) {
        for (int i = 0; i < symboles.length; i++) {
            System.out.println(symboles[i] + ": " + codes[i]);
        }
    }

    /**
     * Main méthode pour tester la classe Huffman.
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        Node racine = createTree(symboles, frequences);
        String[] codes = createCode(racine);
        printCodes(codes);
    }
}