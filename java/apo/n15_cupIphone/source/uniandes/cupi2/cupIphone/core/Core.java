/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Core.java 1131 2010-09-14 20:06:52Z carl-veg $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_cupIphone
 * Autor: Yeisson Oviedo - 23-feb-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupIphone.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.swing.JPanel;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.lista.Lista;
import uniandes.cupi2.cupIphone.componentes.ProxyAplicacion;
import uniandes.cupi2.cupIphone.componentes.excepciones.EjecucionException;
import uniandes.cupi2.cupIphone.componentes.excepciones.InstalacionException;

/**
 *  
 */
public class Core implements ICore, Serializable
{
	//-----------------------------------------------------------------
	// Constantes
	//-----------------------------------------------------------------

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Directorio de aplicaciones
	 */
	public static final String DIR_APPS = "data/apps/";

	//-----------------------------------------------------------------
	// Atributos
	//-----------------------------------------------------------------
	
	private Lista<ProxyAplicacion> aplicaciones;

	/**
	 * Referencia a la aplicaci�n ejecutando actualmente
	 */
	private transient ProxyAplicacion aplicacionActual = null;
	
	//-----------------------------------------------------------------
	// Constructores
	//-----------------------------------------------------------------

	/**
	 *  Constructor
	 */
	public Core( )
	{
		aplicaciones = new Lista<ProxyAplicacion>();
	}

	//-----------------------------------------------------------------
	// M�todos
	//-----------------------------------------------------------------

	/**
	 * Retorna un iterador sobre el listado de aplicaciones instaladas
	 * @return Las aplicaciones
	 */
	public Iterador<ProxyAplicacion> darAplicaciones() {
		return aplicaciones.darIterador();
	}

	/**
	 * Intenta instalar una aplicaci�n a partir
	 * del jar recibido
	 * @param archivoJar Archivo jar a instalar
	 * @return La aplicaci�n instalada
	 * @throws InstalacionException 
	 */
	public ProxyAplicacion instalarProxyAplicacion(File archivoJar) throws InstalacionException {
		File jar = null;
		try {
			jar = copiarArchivo(archivoJar, DIR_APPS, crearSufijo());
		} catch (IOException e) {
			throw new InstalacionException("No se puede copiar el jar en la carpeta de aplicaciones");
		}
		try{
			ProxyAplicacion app = new ProxyAplicacion(jar, this);
			if (aplicaciones.buscar(app) != -1)
				throw new InstalacionException("Ya existe una aplicaci�n con el mismo ID: " + app.darID());
			
			aplicaciones.agregar(app);
			return app;
		}catch (InstalacionException e){
			//intentar borrar el jar
			jar.delete();			
			throw e;
		}
	}

	/**
	 * Des instala una aplicaci�n
	 * @param app Aplicaci�n
	 * @throws InstalacionException 
	 */
	public void eliminarProxyAplicacion(ProxyAplicacion app) throws InstalacionException {
		for (int i = 0; i < aplicaciones.darLongitud(); i++)
			if (aplicaciones.darElemento(i).esDependencia(app))
				throw new InstalacionException("La aplicaci�n es dependencia de otros componentes");
		app.eliminar();
		aplicaciones.eliminar(app);
	}
	
	/**
	 * Ejecuta la aplicaci�n con el ID especificado
	 * @param idAplicacion
	 * @return Panel principal de la aplicaci�n
	 * @throws EjecucionException 
	 */
	public JPanel ejecutarAplicacion(String idAplicacion) throws EjecucionException {		
		ProxyAplicacion ap = buscarAplicacion(idAplicacion);
		if (ap == null)
			throw new EjecucionException("Error al ejecutar la aplicaci�n");
		aplicacionActual = ap;
		return ap.ejecutar();		
	}

	/**
	 * Intenta terminar la aplicaci�n actual, si existe alguna corriendo.
	 * No hace nada en caso contrario
	 */
	public void terminarAplicacionActual() {
		if (aplicacionActual != null){
			aplicacionActual.terminar();
			aplicacionActual = null;
		}
	}

	/**
	 * Retorna la instancia del modelo de la aplicaci�n
	 * indicada
	 */
	public Object darInstanciaModelo(String idAplicacion) throws EjecucionException
	{
		ProxyAplicacion ap = null;
		ap = buscarAplicacion(idAplicacion);
		ProxyAplicacion temp = aplicacionActual;
		if (ap == null)
			throw new EjecucionException("No existe ninguna aplicaci�n con el ID suministrado");
		try{
			aplicacionActual = ap;
			Object res = ap.darInstanciaModelo();
			return res;
		}catch (Throwable t)
		{
			throw new EjecucionException(t.getMessage(), t);
		}finally{
			aplicacionActual = temp;
		}	
	}
	
	/**
	 * Inicializa el core luego de cargarse 
	 */
	public void inicializar() {
		//Intentar borrar los jars y las carpetas de datos
		//que no se est�n usando
		File dir = new File(DIR_APPS);
		File[] files = dir.listFiles();
		for (File f: files){
			//Si no se est� usando, eliminarlo
			boolean usado = false;
			for (int i = 0; i < aplicaciones.darLongitud() && !usado; i++){
				ProxyAplicacion ap = aplicaciones.darElemento(i);
				if (f.equals(ap.darJar()) || f.equals(ap.darDirDatos())){
					usado = true;
				}
			}
			//Tratar de eliminar, si no se puede, no se hace nada
			if (!usado)
				borrarArchivo(f);
		}
		
		//Inicializar los jars de todas las aplicaciones
		for (int i = 0; i < aplicaciones.darLongitud(); i++){
			try {
				aplicaciones.darElemento(i).cargar();
			} catch (InstalacionException e) {				
				e.printStackTrace();
				System.err.println();
				System.err.println("La aplicaci�n " + aplicaciones.darElemento(i).darID() + " no se pudo cargar.");
				System.err.println("La aplicaci�n fue eliminada");
				aplicaciones.eliminar(i);

			}
		}
	}

	/**
	 * Busca una aplicaci�n y la retorna
	 * @param id Id de la aplicaci�n a buscar
	 * @return Referencia a la aplicaci�n, null si no la encuentra
	 */
	public ProxyAplicacion buscarAplicacion(String id) {
		for (int i = 0; i < aplicaciones.darLongitud(); i++)
			if (aplicaciones.darElemento(i).darID().equals(id))
				return aplicaciones.darElemento(i);
		return null;
	}
		
	public File darDirectorioDatos(){
		if (aplicacionActual != null)
			return aplicacionActual.darDirDatos();
		return null;
	}

	//-----------------------------------------------------------------
	// M�todos auxiliares
	//-----------------------------------------------------------------

	/**
	 * M�todo utilitario para copiar un archivo a una nueva ubicaci�n
	 */
	private File copiarArchivo(File archivoJar, String nuevoDir, String sufijo) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try{
			File f = new File(nuevoDir, archivoJar.getName() + sufijo);
			if (f.exists())
				throw new IOException();
			f.createNewFile();
			fis = new FileInputStream(archivoJar);
			fos = new FileOutputStream(f);
			byte[] buffer = new byte[1024];
			int i = fis.read(buffer);
			while (i != -1){			
				fos.write(buffer, 0, i);
				i = fis.read(buffer);
			}		
			return f;
		}
		catch(IOException e)
		{
			throw e;
		}
		finally{
			try{
				fis.close();
				fos.close();
			}catch (Throwable t){}
		}
	}
	
	/**
	 * M�todo utilitario que permite borrar un archivo
	 * Si se est� borrando una carpeta, se borra todo su contenido
	 * recursivamente
	 * @param f Archivo o carpeta a borrar
	 */
	private void borrarArchivo(File f) {
		boolean dir = f.isDirectory();
		if (dir){
			File[] archivos = f.listFiles();
			for (File f1: archivos)
				borrarArchivo(f1);			
		}
		System.out.println(f.delete()? (dir ? "Directorio " : "Archivo ") + f.toString() + " eliminado exitosamente" : "No de pudo borrar el archivo " + f.toString() );		
	}
	
	/**
	 * Utilitario que permite crear sufijos para archivos que se
	 * est�n copiando. Se implementa como un m�todo independiente para
	 * facilitar su modificaci�n en caso que sea necesario
	 * @return
	 */
	private String crearSufijo() {		
		return "_" + (new Date().getTime());
	}
}