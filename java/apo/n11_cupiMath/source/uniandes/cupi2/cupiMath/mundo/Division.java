package uniandes.cupi2.cupiMath.mundo;

public final class Division extends OperadorBinario {
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante con el s�mbolo de la divisi�n
     */
	public final static String SIMBOLO = "/";
	 // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo de la divisi�n
     * @param elTipo El tipo del operador. elTipo != null
     */
	public Division(String elTipo) {
		super(elTipo);
	}
	// -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor de aplicar el operador sobre el sub�rbol izquierdo y el derecho
     */
	@Override
	public double evaluar() {
		return darNodoIzquierdo().evaluar()/darNodoDerecho().evaluar();
	}
	

}
