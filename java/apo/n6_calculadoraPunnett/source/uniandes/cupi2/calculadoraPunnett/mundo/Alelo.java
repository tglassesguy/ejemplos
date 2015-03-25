package uniandes.cupi2.calculadoraPunnett.mundo;

public class Alelo {
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	private char representacion;
	private String caracteristicaAlelo;
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	public Alelo(char representacionP, String caracteristicaAleloP) throws Exception {
		boolean mayusculas = (representacionP >= 65 && representacionP <= 90);
		boolean minusculas = (representacionP >= 97 && representacionP <= 122);
		if (!(mayusculas ^ minusculas)) throw new Exception("El caracter especificado para el Alelo "+caracteristicaAleloP+" debe estar entre A-Z o a-z");
		representacion = representacionP;
		caracteristicaAlelo = caracteristicaAleloP;
	}
	// -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
	/**
	 * M�todo que devuelve la representaci�n del alelo
	 */
	public char darRepresentacion() {
		return representacion;
	}
	/**
	 * M�todo que devuelve el nombre de la caracter�stica que este alelo representa.
	 * @return Nombre caracter�sitica del alelo
	 */
	public String darCaracteristica() {
		return caracteristicaAlelo;
	}
}
