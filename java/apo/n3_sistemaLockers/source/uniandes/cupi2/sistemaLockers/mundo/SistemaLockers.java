/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_sistemaLockers
 * Autor: Catalina Rodriguez - 23-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sistemaLockers.mundo;

import java.util.ArrayList;

/**
 *  Representa el sistema de lockers
 */
public class SistemaLockers
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * N�mero de locaciones en el sistema
	 */
	public final static int NUM_LOCACIONES = 5;
	
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	/**
	 * Arreglo que contiene las diferentes locaciones del sistema de lockers
	 */
	private Locacion[] locaciones;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     *  Constructor del sistema de lockers. <br>
	 * <b>post: </b> Se inicializa el arreglo de locaciones, as� como cada 
	 * una de las locaciones es su posici�n respectiva <br>
     *  [Posici�n 0] Locaci�n: ML
     *  [Posici�n 1] Locaci�n: O
     *  [Posici�n 2] Locaci�n: LL
     *  [Posici�n 3] Locaci�n: Q
     *  [Posici�n 4] Locaci�n: Caneca
     */
    public SistemaLockers( )
    {
		locaciones = new Locacion[ NUM_LOCACIONES ];
		
		locaciones[0] = new Locacion(Locacion.NOMBRE_LOC_1);
		locaciones[1] = new Locacion(Locacion.NOMBRE_LOC_2);
		locaciones[2] = new Locacion(Locacion.NOMBRE_LOC_3);
		locaciones[3] = new Locacion(Locacion.NOMBRE_LOC_4);
		locaciones[4] = new Locacion(Locacion.NOMBRE_LOC_5);
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Retorna la locaci�n en la posici�n que entra como par�metro <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retorn� la locaci�n en la posici�n dada <br>
     * @param pos Posici�n de la locaci�n dentro del arreglo. pos >= 0 y pos < NUM_LOCACIONES 
     * @return Locaci�n en la posici�n pos
     */
	public Locacion darLocacion(int pos) 
	{
		return locaciones[pos];
	}
    
    /**
     * Retorna una lista con los id de los casilleros disponibles en la locaci�n y el tipo dados por par�metro <br>
     * Si no hay resultados retorna una lista vacia.
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retorn� la lista con los casilleros en la locaci�n y del tipo dados <br>
     * @param nombreLocacion Nombre de la locaci�n. nombreLocacion != null y nombreLocacion != ""
     * @param tipoCasillero Tipo del casillero. Corresponde a una de las constantes de la clase Casillero
     * @return Lista diferente de null, con los id de los casilleros
     */
    public ArrayList buscarCasilleros(String nombreLocacion, String tipoCasillero)
    {
    	ArrayList respuesta = new ArrayList();
    	
    	boolean continuar = true;
    	for (int i = 0; i < locaciones.length && continuar; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			for (int j = 0; j < tempLocacion.darNumCasilleros(); j++) {
    				Casillero tempCasillero = tempLocacion.darCasillero(j);
    				if (!tempCasillero.estaAsignado() && tempCasillero.darTipo().equals(tipoCasillero))
    					respuesta.add(tempCasillero.darId());
    			}
    			continuar = false;
    		}
    	}
    	
    	return respuesta;
    }
    
    /**
     * M�todo que dado el nombre de una locaci�n, crea una cantidad de casillero de cierto tipo en dicha locaci�n <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se crearon los casilleros en la locaci�n y el tipo dado <br>
     * @param nombreLocacion Nombre de la locaci�n. nombreLocacion != null y nombreLocacion != ""
     * @param tipoCasillero Tipo de los casilleros a crear. Corresponde a una de las constantes de la clase Casillero
     * @param numCasilleros N�mero de casilleros a crear. Entero mayor a cero
     */
    public void crearCasilleros(String nombreLocacion, String tipoCasillero, int numCasilleros)
    {
    	boolean continuar = true;
    	for (int i = 0; i < locaciones.length && continuar; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			tempLocacion.crearCasilleros(tipoCasillero, numCasilleros);
    			continuar = false;
    		}
    	}
    }
    
    /**
     * M�todo que dado el nombre de una locaci�n, asigna de acuerdo al id un casillero de dicha locaci�n <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se asign� el casillero en la locaci�n dada <br>
     * @param nombreLocacion Nombre de la locaci�n. nombreLocacion != null y nombreLocacion != ""
     * @param idCasillero Identificador del casillero a asignar. idCasillero != null y idCasillero != ""
     */
    public void asignarCasillero(String nombreLocacion, String idCasillero)
    {
    	boolean continuari = true;
    	for (int i = 0; i < locaciones.length && continuari; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			boolean continuarj = true;
    			for (int j = 0; j < tempLocacion.darNumCasilleros() && continuarj; j++){
    				Casillero tempCasillero = tempLocacion.darCasillero(j);
    				if (tempCasillero.darId().equals(idCasillero)) {
    					tempCasillero.asignarCasillero();
    					continuarj = false;
    				}
    			}
    			continuari = false;
    		}
    	}
    }
    
    /**
     * M�todo que dado el nombre de una locaci�n, des-asigna de acuerdo al id un casillero de dicha locaci�n <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se desasign� el casillero en la locaci�n dada <br>
     * @param nombreLocacion Nombre de la locaci�n. nombreLocacion != null y nombreLocacion != ""
     * @param idCasillero Identificador del casillero a asignar. idCasillero != null y idCasillero != ""
     */
    public void desasignarCasillero(String nombreLocacion, String idCasillero)
    {
    	boolean continuari = true;
    	for (int i = 0; i < locaciones.length && continuari; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			boolean continuarj = true;
    			for (int j = 0; j < tempLocacion.darNumCasilleros() && continuarj; j++){
    				Casillero tempCasillero = tempLocacion.darCasillero(j);
    				if (tempCasillero.darId().equals(idCasillero)) {
    					tempCasillero.desasignarCasillero();
    					continuarj = false;
    				}
    			}
    			continuari = false;
    		}
    	}
    }
    
    /**
     * Retorna el porcentaje de casilleros asignados de la locaci�n cuyo nombre entra por par�metro <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retorn� el porcentaje de casilleros ocupados de la locaci�n dada <br>
     * @param nombreLocacion Nombre de la locaci�n. nombreLocacion != null y nombreLocacion != ""
     * @return Porcentaje de casillero asignados. Valor real mayor o igual a cero y menor o igual a 100
     */
    public double calcularPorcentajeAsignado(String nombreLocacion)
    {
    	double respuesta = 0;
    	boolean continuar = true;
    	for (int i = 0; i < locaciones.length && continuar; i++) {
    		Locacion tempLocacion = locaciones[i]; 
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			respuesta = tempLocacion.calcularPorcentajeAsignado();
    			continuar = false;
    		}
    	}
    	return respuesta;
    }
    
    /**
     * Retorna el nombre del tipo m�s popular de la locaci�n cuyo nombre entra por par�metro <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retorn� el nombre del tipo de casillero m�s popular de la locaci�n dada <br>
     * @param nombreLocacion Nombre de la locaci�n. nombreLocacion != null y nombreLocacion != ""
     * @return Nombre del tipo. Corresponde a una de las constantes de la clase Casillero
     */
    public String consultarTipoPopular(String nombreLocacion)
    {
    	String respuesta = "";
    	boolean continuar = true;
    	
    	for (int i = 0; i < locaciones.length && continuar; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.darNombre().equals(nombreLocacion)) {
    			continuar = false;
    			respuesta = tempLocacion.consultarTipoPopular();
    		}
    			
    	}
    	return respuesta;
    }
    
    /**
     * Retorna el porcentaje total de casilleros asignados <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retorn� el porcentaje de casilleros asignados en todo el sistema <br>
     * @return Porcentaje total de casilleros asignados. Valor real mayor o igual a cero y menor o igual que 100
     */
    public double calcularPorcentajeTotalAsignado()
    {
    	double respuesta = 0;
    	double asignados = 0;
    	double total = 0;
		
		for (int i = 0; i < locaciones.length; i++) {
			Locacion tempLocacion = locaciones[i];
			asignados += tempLocacion.darNumCasillerosAsignado();
			total += tempLocacion.darNumCasilleros();
		}
		if (total != 0)
			respuesta = 100*(asignados/total);
		
		return respuesta;
    }
    
    /**
     * Retorna el nombre de la locaci�n popular (locaci�n con mayor porcentaje de casilleros asignados) <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retorn� el nombre de la locaci�n popular en el sistemas <br>
     * @return Nombre de la locaci�n.
     */
    public String consultarLocacionPopular()
    {	
    	double[] porcentaje;
    	String[] nombre;
    	
    	porcentaje = new double[ NUM_LOCACIONES ];
    	nombre = new String[ NUM_LOCACIONES ];
    	
    	for (int i = 0; i < locaciones.length; i++) {
    		Locacion tempLocacion = locaciones[i];
    		
    		porcentaje[i] = tempLocacion.calcularPorcentajeAsignado();
    		nombre[i] = tempLocacion.darNombre();
    	}
    	
    	String respuesta = nombre[0];    	
    	double tempPorcentaje = porcentaje[0];
    	
    	for (int j = 1; j < NUM_LOCACIONES; j++) {
    		if (porcentaje[j] > tempPorcentaje) {
    			respuesta = nombre[j];
    			tempPorcentaje = porcentaje[j];
    		}
    	}
    	return respuesta;
    }
    
    /**
     * Retorna el nombre de la locaci�n con mayor n�mero de casilleros desocupados del tipo dado por par�metro <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retorn� el nombre de la locaci�n con m�s casilleros desocupados del tipo dado <br>
     * @param tipoCasillero Tipo del casillero. Corresponde a una de las constantes de la clase Casillero
     * @return Nombre de la locaci�n
     */
    public String darLocacionConMasCasillerosDesocupadosTipo(String tipoCasillero)
    {
    	int[] desocupados;
    	String[] nombre;
    	
    	desocupados = new int[ NUM_LOCACIONES ];
    	nombre = new String[ NUM_LOCACIONES ];
    	
    	for (int i = 0; i < locaciones.length; i++) {
    		Locacion tempLocacion = locaciones[i];
    		nombre[i] = tempLocacion.darNombre();
    		desocupados[i] = 0;
    		
    		for (int j = 0; j < tempLocacion.darNumCasilleros(); j++) {
    			Casillero tempCasillero = tempLocacion.darCasillero(j);

    			if (tempCasillero.darTipo().equals(tipoCasillero) && !tempCasillero.estaAsignado())
    				desocupados[i]++;
    		}
    	}
    	
    	String respuesta = Casillero.NINGUNO;
    	int desocupado = 0;
    	
    	for (int k = 0; k < NUM_LOCACIONES; k++) {
    		if (desocupados[k] > desocupado) {
    			desocupado = desocupados[k];
    			respuesta = nombre[k];
    		}
    	}
    	return respuesta;
    }
    
    /**
     * Retorna el n�mero de locaciones con todos los tipos de casilleros <br>
	 * <b>pre: </b> El arreglo de locaciones ha sido inicializado y las locaciones han sido creadas <br>
	 * <b>post: </b> Se retorn� el n�mero de locaciones con todos los tipos de casilleros <br>
     * @return N�mero de locaciones con todos los tipos de casilleros. Entero mayor o igual a cero y menor o igual a NUM_LOCACIONES
     */
    public int darNumLocacionesTodoTipo()
    {
    	int respuesta = 0;
    	
    	for (int i = 0; i < locaciones.length; i++) {
    		Locacion tempLocacion = locaciones[i];
    		if (tempLocacion.tieneTodoTipo())
    			respuesta++;
    	}
    	
    	return respuesta;
    }
    
    //-----------------------------------------------------------------
    // Puntos de Extensi�n
    //-----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1 
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}