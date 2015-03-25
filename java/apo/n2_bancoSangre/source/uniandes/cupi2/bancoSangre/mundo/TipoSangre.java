package uniandes.cupi2.bancoSangre.mundo;

/**
 *  Clase que representa un tipo de sangre del banco de sangre
 */
public class TipoSangre 
{
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	/**
	 * Representa el tipo de sangre A
	 */
	public final static String TIPO_A = "A";
	
	/**
	 * Representa el tipo de sangre B
	 */
	public final static String TIPO_B = "B";
	
	/**
	 * Representa el tipo de sangre AB
	 */
	public final static String TIPO_AB = "AB";
	
	/**
	 * Representa el tipo de sangre O
	 */
	public final static String TIPO_O = "O";
	
	/**
	 * Representa el factor RH positivo
	 */
	public static final String RH_P = "+";
	
	/**
	 * Representa el factor RH negativo
	 */
	public static final String RH_N = "-";
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	/**
	 * Tipo de sangre
	 */
	private String tipo;
	
	/**
	 * Factor RH
	 */
	private String rh;
	
	/**
	 * Cantidad de bolsas disponibles en el banco
	 */
	private int bolsasDisponibles;
	
	/**
	 * Cantidad de bolsas suministradas por el banco
	 */
	private int bolsasSuministradas;
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

	/**
	 * Constructor de la clase que inicializa los atributos con la informaci�n recibida por par�metro <br>
	 * <b> post: </b> bolsasSuministradas y bolsasDisponibles se inicializan en cero
	 * @param tipoP Tipo de sangre. Diferente de null. Debe ser uno de los valores de las constantes definidas para el tipo
	 * @param rhP Rh. Diferente de null. Debe ser uno de los valores de las constantes definidas para el rh
	 */
	public TipoSangre(String tipoP, String rhP)
	{
		if (tipoP != null && rhP != null) {
			tipo = tipoP;
			rh = rhP;			
			bolsasDisponibles = 0;
			bolsasSuministradas = 0;
		}
	}
	
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

	/**
	 * Retorna el tipo de sangre
	 * @return String cuyos valores pueden ser las constantes TIPO_A, TIPO_B, TIPO_AB o TIPO_O
	 */
	public String darTipo()
	{
		return tipo;
	}
	
	/**
	 * Retorna el factor RH
	 * @return String cuyos valores pueden ser las constantes TIPO_N o TIPO_P
	 */
	public String darRh()
	{
		return rh;
	}
	
	/**
	 * Retorna el n�mero de bolsas disponibles en el banco
	 * @return El n�mero de bolsas disponibles 
	 */
	public int darCantidadDisponible()
	{
		return bolsasDisponibles;
	}
	
	/**
	 * Retorna en n�mero de bolsas de este tipo de sangre suministradas por el banco
	 * @return N�mero de bolsas suministradas
	 */
	public int darCantidadSuministrada()
	{
		return bolsasSuministradas;
	}
	
	/**
	 * Registra la donaci�n de una bolsa de sangre.
	 * <b> post: </b> Se aumento en uno la cantidad de bolsas disponibles
	 */
	public void registrarDonacion()
	{
		bolsasDisponibles += 1;
	}
	
	/**
	 * Suministra la cantidad de bolsas de sangre solicitadas por par�metro, si es posible. 
	 * <b> post: </b> El n�mero de bolsas disponibles se redujo en la cantidad de bolsas suministradas.
	 * 				  Se aumento el n�mero de bolsas suministradas en el n�mero de bolsas suministradas.
	 *                Debe quedar al menos una bolsa de sangre de este tipo disponible.
	 * @param numBolsas N�mero de bolsas a suministrar. Entero mayor a cero
	 * @return True si exist�an el n�mero de bolsas suficientes para suministrar y fueron suministradas, false en caso contrario
	 */
	public boolean suministrar(int numBolsas)
	{
		if (numBolsas < bolsasDisponibles){
			bolsasDisponibles -= numBolsas;
			bolsasSuministradas += numBolsas;
			return true;
		}
		return false;
	}
}