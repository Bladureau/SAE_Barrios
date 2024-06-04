package iut.sae.td1;

import java.util.HashMap;

/**
 * 
 */
public class CaculCaractereTest {
	
	static HashMap<String, Double> testValeurOcc = new HashMap<String, Double>();
	
	static HashMap<String, Double> testValeurTaux = new HashMap<String, Double>();
	
	final static int NOMBRE_DE_CARACTERE = 862;
	
	/**
	 * 
	 */
	public static void ajouteValeurHashMapOcc() {
		testValeurOcc.put( " ",129.0);
		testValeurOcc.put("e",97.0);
		testValeurOcc.put("i",71.0);
		testValeurOcc.put("u",62.0);
		testValeurOcc.put("t",58.0);
		testValeurOcc.put("n",54.0);
		testValeurOcc.put("s",52.0);
		testValeurOcc.put("a",49.0);
		testValeurOcc.put("r",44.0);
		testValeurOcc.put("m",34.0);
		testValeurOcc.put("c",33.0);
		testValeurOcc.put("l",28.0);
		testValeurOcc.put("d",23.0);
		testValeurOcc.put("o",23.0);
		testValeurOcc.put("p",15.0);
		testValeurOcc.put(",",14.0);
		testValeurOcc.put(".",14.0);
		testValeurOcc.put("v",10.0);
		testValeurOcc.put("b",8.0);
		testValeurOcc.put("g",6.0);
		testValeurOcc.put("q",6.0);
		testValeurOcc.put("h",5.0);
		testValeurOcc.put("M",4.0);
		testValeurOcc.put("f",4.0);
		testValeurOcc.put("P",3.0);
		testValeurOcc.put("x",3.0);
		testValeurOcc.put("C",2.0);
		testValeurOcc.put("I",2.0);
		testValeurOcc.put("",1.0);
		testValeurOcc.put("",1.0);
		testValeurOcc.put(":",1.0);
		testValeurOcc.put("?",1.0);
		testValeurOcc.put("A",1.0);
		testValeurOcc.put("N",1.0);
		testValeurOcc.put("O",1.0);
		testValeurOcc.put("S",1.0);
		testValeurOcc.put("j",1.0);
	}

	public static void ajouteValeurHashMapTaux() {
		testValeurTaux.put( " ",129.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("e",97.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("i",71.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("u",62.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("t",58.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("n",54.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("s",52.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("a",49.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("r",44.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("m",34.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("c",33.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("l",28.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("d",23.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("o",23.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("p",15.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put(",",14.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put(".",14.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("v",10.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("b",8.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("g",6.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("q",6.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("h",5.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("M",4.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("f",4.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("P",3.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("x",3.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("C",2.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("I",2.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("",1.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("",1.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put(":",1.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("?",1.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("A",1.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("N",1.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("O",1.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("S",1.0/NOMBRE_DE_CARACTERE);
		testValeurTaux.put("j",1.0/NOMBRE_DE_CARACTERE);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ajouteValeurHashMapOcc();
		ajouteValeurHashMapTaux();
		System.out.println(testValeurOcc);
		System.out.println(testValeurTaux);
	}

}
