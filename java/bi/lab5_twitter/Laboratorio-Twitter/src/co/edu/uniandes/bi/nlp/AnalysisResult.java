/**
 * 
 */
package co.edu.uniandes.bi.nlp;

/**
 * Contiene el resultado del an�lisis de sentimientos realizado
 * sobre un mensaje
 * @author Sebastian
 *
 */
public class AnalysisResult {
	
	//--------------------------------------------------------------------------------------------------
	// Constantes
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * �ndice para la emoci�n alegr�a
	 */
	public static int HAPPINESS = 0;

	/**
	 * �ndice para la emoci�n tristeza
	 */
	public static int SADNESS = 1;

	/**
	 * �ndice para la emoci�n miedo
	 */
	public static int FEAR = 2;

	/**
	 * �ndice para la emoci�n ira
	 */
	public static int ANGER = 3;

	/**
	 * �ndice para la emoci�n disgusto
	 */
	public static int DISGUST = 4;

	/**
	 * �ndice para la emoci�n sorpresa
	 */
	public static int SURPRISE = 5;
	
	//--------------------------------------------------------------------------------------------------
	// Atributos
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Identificador del mensaje analyzado
	 */
	private long msgId;
	
	/**
	 * Polaridad/valencia del mensaje
	 */
	private int valence;
	
	/**
	 * Pesos asociados a cada emoci�n
	 */
	private double[] emotionsWeight;
	
	/**
	 * El orden de cada emoci�n seg�n su peso en el mensaje
	 */
	private int[] emotionsOrder;
	
	//--------------------------------------------------------------------------------------------------
	// Getters y Setters
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * @return the valence
	 */
	public int getValence() {
		return valence;
	}

	/**
	 * @param valence the valence to set
	 */
	public void setValence(int valence) {
		this.valence = valence;
	}

	/**
	 * @return the emotionsWeight
	 */
	public double[] getEmotionsWeight() {
		return emotionsWeight;
	}

	/**
	 * @param emotionsWeight the emotionsWeight to set
	 */
	public void setEmotionsWeight(double[] emotionsWeight) {
		this.emotionsWeight = emotionsWeight;
	}

	/**
	 * @return the emotionsOrder
	 */
	public int[] getEmotionsOrder() {
		return emotionsOrder;
	}

	/**
	 * @param emotionsOrder the emotionsOrder to set
	 */
	public void setEmotionsOrder(int[] emotionsOrder) {
		this.emotionsOrder = emotionsOrder;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	/**
	 * @return the msgId
	 */
	public long getMsgId() {
		return msgId;
	}

}
