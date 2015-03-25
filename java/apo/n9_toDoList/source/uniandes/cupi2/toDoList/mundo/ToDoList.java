/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_toDoList
 * Autor: Carlos Navarrete Puentes - 24-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.toDoList.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * Clase principal que representa una lista de tareas organizadas por categor�as.
 * <b> inv: </b> <br>
 * rutaArchivo != null && rutaArchivo no es una cadena vac�a<br>
 */
public class ToDoList
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Primera categor�a en la lista de tareas
     */
    private Categoria primeraCategoria;

    /**
     * Ruta del archivo
     */
    private String rutaArchivo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva lista de tareas
     * @param rutaArchivoP Ruta al archivo serializado con la informaci�n rutaArchivoP != null y rutaArchivoP != ""
     * @throws PersistenciaException Si ocurre alg�n error al restaurar el estado del mundo
     */
    public ToDoList( String rutaArchivoP ) throws PersistenciaException
    {
        rutaArchivo = rutaArchivoP;
        abrir( );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que recupera el estado de la lista de tareas a partir del archivo serializado. 
     * @throws PersistenciaException Si se presenta alg�n problema al cargar la informaci�n
     */
    public void abrir() throws PersistenciaException
    {
    	File archivo = new File(rutaArchivo);
    	if (archivo.exists()){
	        try {
	        	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
	        	primeraCategoria = (Categoria)ois.readObject();
	        	ois.close();
	        } catch (Exception e) {
	        	primeraCategoria = null;
				throw new PersistenciaException("No fue posible cargar la lista de tareas: \n"+e.getMessage());
			}
    	} else primeraCategoria = null;
    }
    
    /**
     * Retorna la primera categor�a
     * @return Primera categor�a
     */
    public Categoria darPrimeraCategoria( )
    {
        return primeraCategoria;
    }

    /**
     * Agrega una nueva categoria en orden alfab�tico. No pueden existir dos categor�as con el mismo nombre.
     * @param nombreP Nombre de la categor�a. nombreP != null y nombreP != ""
     * @throws ElementoExisteException Si ya existe una categor�a con ese nombre
     */
    public void agregarCategoria( String nombreP ) throws ElementoExisteException
    {
    	// Creo la nueva categoria
    	Categoria nuevaCategoria = new Categoria(nombreP);
    	// Si no tengo ninguna categor�a, la asigno a la primera y termino
    	if (primeraCategoria == null)  primeraCategoria = nuevaCategoria;
    	// Si s� tengo alguna categor�a, debo buscar donde insertar la nueva
    	else {
    		// Empiezo en la primera categor�a
    		Categoria tempCategoria = primeraCategoria;
    		// Mientras la categor�a sea diferente de NULL y este antes de la que estoy buscando... 
    		while (tempCategoria.darSiguienteCategoria() != null 
    				&& tempCategoria.darSiguienteCategoria().darNombre().compareToIgnoreCase(nombreP) <= 0) {
    			// Avanzo...    			
    			tempCategoria = tempCategoria.darSiguienteCategoria();
    		}
    		// Si resulta ser la misma, lanzo excepci�n...
    		if (tempCategoria != null && tempCategoria.darNombre().equals(nombreP)) throw new ElementoExisteException("Ya existe una categor�a con este nombre");
    		// Si no es la misma, aqu� debo agregar la nueva categor�a...
    		nuevaCategoria.cambiarAnterior(tempCategoria);
    		nuevaCategoria.cambiarSiguiente(tempCategoria.darSiguienteCategoria());
    		tempCategoria.cambiarSiguiente(nuevaCategoria);
    	}
    }

    /**
     * Busca una categor�a con el nombre dado.
     * @param nombre Nombre de la categor�a que se est� buscando. nombre != null y nombre != ""
     * @return La categor�a con el nombre dado si existe, null en caso contrario.
     */
    public Categoria buscarCategoria( String nombre )
    {
    	Categoria tempApuntador = primeraCategoria;
    		
    	if (primeraCategoria == null) return null;
        
        Categoria tempCategoria = primeraCategoria;
        while (tempCategoria != null && tempCategoria.darNombre().compareToIgnoreCase(nombre) < 0) {
        	tempCategoria = tempCategoria.darSiguienteCategoria();
        }

        return tempCategoria != null && tempCategoria.darNombre().equals(nombre) ? tempCategoria : null;
    }

    /**
     * Agrega una nueva Tarea al final de la lista de tareas de la categor�a dada.
     * @param nombreCategoria Categor�a a la que pertenece la nueva tarea. nombreCategoria != null y nombreCategoria != ""
     * @param nombreTarea Nombre de la nueva tarea. nombreTarea != null y nombreTarea != ""
     * @param descripcionTarea Descripci�n de la nueva tarea. descripcionTarea != null && descripcionTarea != ""
     * @param fechaInicioTarea Fecha de inicio de la nueva tarea. fechaInicioTarea != null
     * @param fechaFinTarea Fecha de finalizaci�n de la nueva tarea. Puede ser null. Si fechaFinTarea != null entonces fechaFinTarea > fechaInicioTarea.
     * @throws ElementoExisteException Se lanza si ya existe una tarea con ese nombre
     * @throws ElementoNoExisteException Se lanza si no existe ninguna categor�a con ese nombre
     */
    public void agregarTarea( String nombreCategoria, String nombreTarea, String descripcionTarea, Date fechaInicioTarea, Date fechaFinTarea ) throws ElementoExisteException, ElementoNoExisteException
    {
    	Categoria tempCategoria = buscarCategoria(nombreCategoria);
    	if (tempCategoria == null) throw new ElementoNoExisteException("La categor�a "+nombreCategoria+" no existe");
    	tempCategoria.agregarTarea(nombreTarea, descripcionTarea, fechaInicioTarea, fechaFinTarea);
    }

    /**
     * Edita una tarea con la informaci�n dada por par�metro. El nombre de la tarea no cambia. <br> 
     * La informaci�n que se puede editar es su descripci�n, su fecha de inicio y su fecha de finalizaci�n.
     * @param nombreCategoria Nombre de la categor�a a la que pertenece la tarea que se quiere editar. nombreCategoria != null y nombreCategoria != ""
     * @param nombreTarea Nombre de la tarea que se quiere editar. nombreTarea != null y nombreTarea != ""
     * @param descripcionTarea Nueva descripci�n de la tarea. descripcionTarea != null y descripcionTarea != ""
     * @param fechaInicio Nueva fecha de inicio de la tarea. fechaInicio != null
     * @param fechaFin Nueva fecha de finalizaci�n de la tarea. Puede ser null. Si fechaFin != null entonces fechaFin > fechaInicio.
     * @throws ElementoNoExisteException Se lanza si:<br>
     *         - No existe una categor�a con el nombre dado. - No existe una tarea con el nombre dado.
     */
    public void editarTarea( String nombreCategoria, String nombreTarea, String descripcionTarea, Date fechaInicio, Date fechaFin ) throws ElementoNoExisteException
    {
    	Categoria tempCategoria = buscarCategoria(nombreCategoria);
    	if (tempCategoria == null) throw new ElementoNoExisteException("La categor�a "+nombreCategoria+" no existe");
    	tempCategoria.editarTarea(nombreTarea, descripcionTarea, fechaInicio, fechaFin);
    }

    /**
     * Termina una tarea dado su nombre y la categor�a a la que pertenece. 
     * @param nombreCategoria Nombre de la categor�a a la que pertenece. nombreCategoria != null y nombreCategoria != ""
     * @param nombreTarea Nombre de la tarea que se quiere terminar. nombreTarea != null y nombreTarea != ""
     * @throws ElementoNoExisteException Se lanza si:<br>
     *         - No existe una categor�a con el nombre dado. - No existe una tarea con el nombre dado.
     */
    public void terminarTarea( String nombreCategoria, String nombreTarea ) throws ElementoNoExisteException
    {
    	Categoria tempCategoria = buscarCategoria(nombreCategoria);
    	if (tempCategoria == null) throw new ElementoNoExisteException("La categor�a "+nombreCategoria+" no existe");
    	tempCategoria.terminarTarea(nombreTarea);
    }

    /**
     * Retorna una nueva lista encadenada que contiene las tareas pendientes. Se considera una tarea como pendiente si no ha sido terminada y a�n no ha pasado la fecha de
     * finalizaci�n. Cada tarea de la lista que se retorna es una copia de tarea respectiva para evitar problemas en las referencias (siguienteTarea) de la lista de tareas
     * original.
     * @return Lista encadenada con las tareas pendientes. Si no hay ninguna se retorna null.
     */
    public Tarea darTareasPendientes( )
    {	// Si no tengo ninguna categor�a devuelvo null...
    	if (primeraCategoria == null) return null;
    	// Si s� tengo categorias, empiezo por la primera...
    	Categoria tempCategoria =  primeraCategoria;
    	// creo una nueva lista de tareas...
    	Tarea nuevaLista = null;
    	// y busco la primera categor�a con tareas pendientes...
    	while (tempCategoria != null && tempCategoria.darTareasPendientes() == null) tempCategoria = tempCategoria.darSiguienteCategoria();
    	// depronto no encuentro ninguna, entonces devuelvo null...
    	if (tempCategoria == null) return null;
    	// si encuentro alguna, vuelvo sus tareas pendientes las primeras de la nueva lista...
    	nuevaLista = tempCategoria.darTareasPendientes();
    	// guardo su cabeza...
    	Tarea cabezaNuevaLista = nuevaLista;
    	// y paso a la siguiente categoria...
    	tempCategoria = tempCategoria.darSiguienteCategoria();
    	// Ahora debo recorrer el resto de las categorias...
    	while (tempCategoria != null) {
    		// Primero debo encontrar el final de la nueva lista...
    		while(nuevaLista.darSiguienteTarea() != null) nuevaLista = nuevaLista.darSiguienteTarea();
    		// Agrego al final las tareas pendientes las de la categor�a actual...
    		nuevaLista.cambiarSiguienteTarea(tempCategoria.darTareasPendientes());
    		// y avanzo en las categorias...
    		tempCategoria = tempCategoria.darSiguienteCategoria();
    	}
    	// devuelvo la cabeza de la nueva lista...
    	return cabezaNuevaLista;
    }

    /**
     * Retorna una nueva lista encadenada que contiene las tareas terminadas. 
     * Cada tarea de la lista que se retorna es una copia de tarea respectiva para evitar problemas en <br>
     * la lista de tareas original y deben estar ordenadas por categor�as.
     * @return Lista encadenada con las tareas terminadas. Si no hay ninguna se retorna null.
     */
    public Tarea darTareasTerminadas( ){
    	if (primeraCategoria == null) return null;
    	Categoria tempCategoria =  primeraCategoria;
    	Tarea nuevaLista = null;
    	while (tempCategoria != null && tempCategoria.darTareasTerminadas() == null) tempCategoria = tempCategoria.darSiguienteCategoria();
    	if (tempCategoria == null) return null;
    	nuevaLista = tempCategoria.darTareasTerminadas();
    	Tarea cabezaNuevaLista = nuevaLista;
    	tempCategoria = tempCategoria.darSiguienteCategoria();
    	while (tempCategoria != null) {
    		while(nuevaLista.darSiguienteTarea() != null) nuevaLista = nuevaLista.darSiguienteTarea();
    		nuevaLista.cambiarSiguienteTarea(tempCategoria.darTareasTerminadas());
    		tempCategoria = tempCategoria.darSiguienteCategoria();
    	}
    	return cabezaNuevaLista;
    }

    /**
     * Retorna una nueva lista encadenada que contiene las tareas vencidas. Se considera una tarea como vencida si no ha sido terminada y su fecha de finalizaci�n es anterior
     * a la fecha actual. Cada tarea de la lista que se retorna es una copia de tarea respectiva para evitar problemas en la lista de tareas original y deben 
     * estar ordenadas por categor�as.
     * @return Lista encadenada con las tareas terminadas. Si no hay ninguna se retorna null.
     */
    public Tarea darTareasVencidas() {	
    	if (primeraCategoria == null) return null;
    	Categoria tempCategoria =  primeraCategoria;
    	Tarea nuevaLista = null;
    	while (tempCategoria != null && tempCategoria.darTareasVencidas() == null) tempCategoria = tempCategoria.darSiguienteCategoria();
    	if (tempCategoria == null) return null;
    	nuevaLista = tempCategoria.darTareasVencidas();
    	Tarea cabezaNuevaLista = nuevaLista;
    	tempCategoria = tempCategoria.darSiguienteCategoria();
    	while (tempCategoria != null) {
    		while(nuevaLista.darSiguienteTarea() != null) nuevaLista = nuevaLista.darSiguienteTarea();
    		nuevaLista.cambiarSiguienteTarea(tempCategoria.darTareasVencidas());
    		tempCategoria = tempCategoria.darSiguienteCategoria();
    	}
    	return cabezaNuevaLista;
    }

    /**
     * Elimina las tareas terminadas
     * @return Retorna el n�mero de tareas eliminadas
     */
    public int eliminarTareasTerminadas() {
    	if (primeraCategoria == null) return 0;
    	Categoria tempCategoria = primeraCategoria;
    	int contador = 0;
    	while(tempCategoria != null) {
    		contador += tempCategoria.eliminarTareasTerminadas();
    		tempCategoria = tempCategoria.darSiguienteCategoria();
    	}
    	return contador;
    }

   

    /**
     * M�todo que guarda el estado del mundo en un archivo serializado. 
     * @throws PersistenciaException Se lanza excepci�n si ocurre alg�n problema al guardar.
     */
    public void guardar( ) throws PersistenciaException
    {
    	try {
    		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
    		oos.writeObject(primeraCategoria);
    		oos.close();
    	} catch (IOException e) {
    		throw new PersistenciaException("No fue posible persistir la lista de tareas: \n"+e.getMessage());
    	}
    }
   /**
    * M�todo que permite conocer el nombre de las categorias que tengan al menos una tarea compartida entre si.
    */
    public String darNombresCategor�asConTareasCompartidas() {
    	if (primeraCategoria == null) return "";
    	
    	String respuesta = "";
    	
    	for (Categoria recorredoraPrincipal = primeraCategoria; recorredoraPrincipal != null; 
    		recorredoraPrincipal = recorredoraPrincipal.darSiguienteCategoria())
    		for(Categoria recorredoraSecundaria= recorredoraPrincipal.darSiguienteCategoria(); 
    			recorredoraSecundaria != null; recorredoraSecundaria = recorredoraSecundaria.darSiguienteCategoria()) {
    			boolean encontreAlguna = false;
    			for (Tarea listaTareasPrincipal = recorredoraPrincipal.darPrimeraTarea(); 
    				!encontreAlguna && listaTareasPrincipal != null; listaTareasPrincipal = listaTareasPrincipal.darSiguienteTarea())
    				for (Tarea listaTareasSecundaria = recorredoraSecundaria.darPrimeraTarea(); 
    					!encontreAlguna && listaTareasSecundaria != null; listaTareasSecundaria = listaTareasSecundaria.darSiguienteTarea())
    						encontreAlguna = listaTareasPrincipal.darNombre().equals(listaTareasSecundaria.darNombre()) 
    										&& listaTareasPrincipal.darFechaInicio().equals(listaTareasSecundaria.darFechaInicio());
    			respuesta = encontreAlguna ? respuesta += recorredoraPrincipal.darNombre()+","+recorredoraSecundaria.darNombre(): respuesta;
    		}
    	return respuesta;
    }
    /**
    * M�todo que elimina las categor�as cuyo n�mero de tareas sea mayor al valor que
    * ingresa como primer par�metro y menor al que ingresa como segundo par�metro
    * @param rangoInicial Valor m�nimo del rango - rangoInicial >= 0
    * @param rangoFinal Valor m�ximo del rango - rangoFinal >= 0
    * && rangoFinal >= rangoInicial
    * @return N�mero de categor�as eliminadas
    */
    public int eliminarCategoriasPorNumeroDeTareas( int rangoInicial, int rangoFinal ) {
    	int respuesta = 0;
    	for (Categoria recorredora = primeraCategoria; recorredora != null; recorredora = recorredora.darSiguienteCategoria())
    		if (rangoInicial < recorredora.darNumeroTareas() && recorredora.darNumeroTareas() < rangoFinal) {
    			respuesta++;
    			recorredora.darAnteriorCategoria().cambiarSiguiente(recorredora.darSiguienteCategoria());
    		}
    	return respuesta;
    }
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * M�todo que verifica la invariante de la clase <br>
     * <b>inv: </b> <br>
     * rutaArchivo != null && rutaArchivo no es una cadena vac�a<br>
     */
    private void verificarInvariante( )
    {
        assert rutaArchivo != null : "La ruta del archivo es null";
        assert !rutaArchivo.equals("") : "La ruta del archivo es vac�a";
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * 
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * 
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}