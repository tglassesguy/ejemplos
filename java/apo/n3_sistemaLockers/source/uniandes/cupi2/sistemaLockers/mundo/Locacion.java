package uniandes.cupi2.sistemaLockers.mundo;

import java.util.ArrayList;

/**
 * Representa una locaci�n de casilleros
 */
public class Locacion 
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Nombre de la locaci�n 1
	 */
	public final static String NOMBRE_LOC_1 = "ML";
	
	/**
	 * Nombre de la locaci�n 2
	 */
	public final static String NOMBRE_LOC_2 = "O";
	
	/**
	 * Nombre de la locaci�n 3
	 */
	public final static String NOMBRE_LOC_3 = "LL";
	
	/**
	 * Nombre de la locaci�n 4
	 */
	public final static String NOMBRE_LOC_4 = "Q";

	/**
	 * Nombre de la locaci�n 5
	 */
	public final static String NOMBRE_LOC_5 = "Caneca";
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Nombre de la locaci�n
	 */
	private String nombre;
	
	/**
	 * Contenedor con los casilleros de la locaci�n
	 */
	private ArrayList casilleros;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

	/**
	 * M�todo constructor. Inicializa el nombre con el par�metro. Inicializa la lista de casilleros vac�a 
	 * @param nombreP nombre de la locaci�n. nombreP != null y nombreP != ""
	 */
	public Locacion(String nombreP)
	{
		nombre = nombreP;
		casilleros = new ArrayList();
	}
	
	//-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
  	
	/**
	 * Retorna el nombre de la locaci�n
	 * @return Nombre de la locaci�n
	 */
	public String darNombre()
	{
		return nombre;
	}
	
	/**
	 * Retorna la lista de casilleros
	 * @return Lista de casilleros
	 */
	public ArrayList darCasilleros()
	{
		return casilleros;
	}
	
	/**
	 * Retorna el casillero que se encuentra en la posici�n pos de la lista. <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retorn� el casillero en la posici�n dada por par�metro <br>
	 * @param pos Posici�n del casillero. Entero mayor a cero y menor al tama�o de la lista
	 * @return Casillero en la posici�n pos
	 */
	public Casillero darCasillero(int pos)
	{
		return (Casillero)casilleros.get(pos);
	}
	
	/**
	 * Crea nuevos casilleros para la locaci�n. 
	 * El id que se asigna a los casillero creados debe ser el nombre de la locaci�n unido al n�mero (un consecutivo) del casillero (Por ejemplo: ML1)
	 * El n�mero del casillero es un n�mero consecutivo que inicia en 1 y se va incrementando a medida que se crea un casillero <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se agregaron nuevos casilleros a la locaci�n <br>
	 * @param tipo Tipo de casilleros a crear. Debe corresponder a alguna de las constantes de la clase Casillero
	 * @param numCasilleros N�mero de casillero a crear. Entero mayor a cero.
	 */
	public void crearCasilleros(String tipo, int numCasilleros)
	{
		// Recuperando n�maro de casilleros ya creados
		int cuantos = darNumCasilleros();
		
		for (int i = 0; i < numCasilleros; i++) {
			Casillero casillero = new Casillero(tipo, nombre+(cuantos+i+1));
			casilleros.add(casillero);
		}
	}
	
	/**
	 * Asigna el casillero cuyo id entra por par�metro <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se cambi� a ocupado el casillero con id dado <br>
	 * @param id Id del casillero. id != null y id != ""
	 */
	public void asignarCasillero(String id)
	{
		boolean continuar = true;
		for (int i = 0; i < casilleros.size() && continuar; i++) {
			// Recuperando casillero i
			Casillero temp = (Casillero)casilleros.get(i);
			// Comparando id casillero i con par�metro
			if (temp.darId().equals(id)) {
			// Asignando casillero en caso de coincidir
				temp.asignarCasillero();
				continuar = false;
			}
		}
	}
	
	/**
	 * Des-asigna el casillero cuyo id entra por par�metro <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se cambi� a desocupado el casillero con id dado <br>
	 * @param id Id del casillero. id != null y id != ""
	 */
	public void desasignarCasillero(String id)
	{
		boolean continuar = true;
		for (int i = 0; i < casilleros.size() && continuar; i++) {
			// Recuperando casillero i
			Casillero temp = (Casillero)casilleros.get(i);
			// Comparando id casillero i con par�metro
			if (temp.darId().equals(id)) {
			// Desasignando casillero en caso de coincidir
				temp.desasignarCasillero();
				continuar = false;
			}
		}
	}
	
	/**
	 * Devuelve una lista con los IDs de los casilleros disponibles de la locaci�n del mismo tipo que el que entra por par�metro. <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retorn� una lista con los casilleros disponibles del tipo dado <br>
	 * @param tipo Tipo de casillero. Corresponde a una de las constantes de la clase Casillero
	 * @return Lista con los id de los casilleros
	 */
	public ArrayList buscarCasilleros(String tipo)
	{
		ArrayList respuesta = new ArrayList();
		
		for (int i = 0; i < casilleros.size(); i++) {
			Casillero temp = (Casillero)casilleros.get(i);
			if (temp.darTipo().equals(tipo) && !temp.estaAsignado()) {
				respuesta.add(temp.darId());
			}
		}
		
		return respuesta;
	}
	
	/**
	 * Devuelve el n�mero de casilleros de la locaci�n <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retorn� en n�mero de casilleros en la locaci�n <br>
	 * @return N�mero de casilleros
	 */
	public int darNumCasilleros()
	{
		return casilleros.size();
	}
	
	/**
	 * Devuelve el n�mero de casilleros asignados en la locaci�n <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retorn� el n�mero de casilleros ocupados <br>
	 * @return N�mero de casilleros
	 */
	public int darNumCasillerosAsignado()
	{
		int respuesta = 0;
		for (int i = 0; i < casilleros.size(); i++) {
			if (((Casillero)casilleros.get(i)).estaAsignado()) {
				respuesta ++;
			}
		}
		return respuesta;
	}
	
	/**
	 * Calcula el porcentaje de casillero asignados (porcentaje de casillero ocupados en la locaci�n #casillerosAsignados/#casilleros) <br>
	 * Si no hay casilleros retorna 0.0 <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retorn� el porcentaje de casilleros ocupados <br>
	 * @return Porcentaje de asignados. Valor real mayor o igual cero y menor o igual a 100
	 */
	public double calcularPorcentajeAsignado()
	{
		double respuesta = 0;
		
		if (darNumCasilleros() != 0)
			respuesta = 100*(darNumCasillerosAsignado()/(double)darNumCasilleros());
		
		return respuesta;
	}
	
	/**
	 * Devuelve el nombre del tipo m�s popular (tipo con mayor n�mero de casilleros asignados) <br>
	 * Si no hay casilleros retorna la constante NINGUNO <br>
	 * Si hay varios tipos populares, informa el primer tipo popular <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retorn� el tipo de casillero con mayor n�mero de asignados <br>
	 * @return Nombre del tipo. Corresponde a alguna de las constantes de la clase Casillero
	 */
	public String consultarTipoPopular()
	{
		int tipo1 = 0;
		int tipo2 = 0;
		int tipo3 = 0;
		
		for (int i = 0; i < casilleros.size(); i++) {
			Casillero temp = (Casillero)casilleros.get(i);
			if (temp.darTipo().equals(Casillero.TIPO_1) && temp.estaAsignado())
				tipo1++;
			else if (temp.darTipo().equals(Casillero.TIPO_2) && temp.estaAsignado())
				tipo2++;
			else if (temp.darTipo().equals(Casillero.TIPO_3) && temp.estaAsignado())
				tipo3++;
		}
		
		if (tipo1 == 0 && tipo2 == 0 && tipo3 == 0)
			return Casillero.NINGUNO;
		else if (tipo1 >= tipo2 && tipo1 >= tipo3)
			return Casillero.TIPO_1;
		else if (tipo2 >= tipo1 && tipo2 >= tipo3)
			return Casillero.TIPO_2;
		else
			return Casillero.TIPO_3;
	}

	/**
	 * Indica si la locaci�n tiene casilleros de todos los tipos <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retorn� un booleano indicando si la locaci�n tiene o no casilleros de todos los tipos<br>
	 * @return Booleano que indica si la locaci�n tiene casilleros de todos los tipos
	 */
	public boolean tieneTodoTipo() 
	{		
		boolean tipo1 = false;
		boolean tipo2 = false;
		boolean tipo3 = false;
		
		for (int i = 0; i < casilleros.size() && !(tipo1 && tipo2 && tipo3); i++) {
			Casillero temp = (Casillero)casilleros.get(i);
			if (temp.darTipo().equals(Casillero.TIPO_1))
				tipo1 = true;
			else if (temp.darTipo().equals(Casillero.TIPO_2))
				tipo2 = true;
			else if (temp.darTipo().equals(Casillero.TIPO_3))
				tipo3 = true;
		}
		
		return tipo1 && tipo2 && tipo3;
	}

	/**
	 * Devuelve el n�mero de casilleros desocupados del tipo dado por par�metro <br>
	 * <b>pre: </b> La lista de casilleros ha sido inicializada <br>
	 * <b>post: </b> Se retorn� el n�mero de casilleros del tipo dado <br>
	 * @param tipoCasillero Tipo de casillero. Corresponde a una de las constantes de la clase Casillero
	 * @return N�mero de casilleros. Entero mayor o igual a cero
	 */
	public int darNumCasillerosDesocupados(String tipoCasillero) 
	{
		int respuesta = 0;
		
		for (int i = 0; i < casilleros.size(); i++) {
			Casillero temp = (Casillero)casilleros.get(i);
			if (!temp.estaAsignado() && temp.darTipo().equals(tipoCasillero))
				respuesta++;
		}
		
		return respuesta;
	}
}