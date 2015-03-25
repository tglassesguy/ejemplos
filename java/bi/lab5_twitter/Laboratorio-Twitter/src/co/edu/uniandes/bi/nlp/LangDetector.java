package co.edu.uniandes.bi.nlp;

import java.util.Hashtable;

import org.knallgrau.utils.textcat.TextCategorizer;

import co.edu.uniandes.bi.util.Commons;

/**
 * Encuentra el idioma de un texto dado
 * @author Sebastian
 *
 */
public class LangDetector {

	// -------------------------------------------------------------------------------------
	// Constantes
	// -------------------------------------------------------------------------------------
	
	/**
	 * Idioma ingl�s
	 */
	public static final String ENGLISH="english";
	
	/**
	 * Idioma espa�ol
	 */
	public static final String SPANISH="spanish";
	
	/**
	 * Idioma franc�s
	 */
	public static final String FRENCH="french";

	// -------------------------------------------------------------------------------------
	// Miembros de la clase
	// -------------------------------------------------------------------------------------
	
	/**
	 * Clase que detecta los idiomas
	 */
	private static TextCategorizer categorizer;
	
	/**
	 * Traduce los lenguajes detectados a c�digos ISO 639-1
	 */
	private static Hashtable<String, String> textToCode;
	
	static {
		categorizer = new TextCategorizer();
		
		textToCode = new Hashtable<String, String>();
		textToCode.put(ENGLISH, Commons.ENGLISH_LANG);
		textToCode.put(SPANISH, Commons.SPANISH_LANG);
		textToCode.put(FRENCH, Commons.FRENCH_LANG);
	}
	
	// -------------------------------------------------------------------------------------
	// M�todos est�ticos
	// -------------------------------------------------------------------------------------
	
	/**
	 * Encuentra el idioma del texto dado
	 * @param text
	 * @return
	 */
	public static String detectLanguage(String text) {
		return textToCode.get(categorizer.categorize(text));
	}

}
