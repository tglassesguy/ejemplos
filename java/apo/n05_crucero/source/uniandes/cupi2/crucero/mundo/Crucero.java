/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_crucero
 * Autor: Catalina Rodr�guez - 16-sep-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.crucero.mundo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.REST;

/**
 *  Clase que representa al crucero
 */
public class Crucero
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Lista de destinos del crucero
	 */
	private ArrayList destinos;
	
	/**
	 * �ndice del destino actual
	 */
	private int posDestinoActual;
	
	/**
	 * Conexi�n con Flickr
	 */
	private Flickr flickr;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * M�todo constructor de un nuevo Crucero <br>
     * <b>post: </b> Inicializa el crucero, inicializa la lista de destino, inicializa posDestinoActual en -1
     * y agrega el primer destino: Santa Marta - Colombia
     */
    public Crucero( )
    {
    	destinos = new ArrayList();
    	posDestinoActual = -1;
    	try 
    	{
            conectarFlickr();
			agregarDestino("Santa Marta", "Colombia");
		} 
    	catch (Exception e) 
    	{
            //No se genera excepci�n pues es el primer destino que se agrega
    	    //Si se genera una excepci�n es por que no se pudo conectar a flickr
    	    e.printStackTrace( );
		}
    	
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * Agrega un nuevo destino y carga sus imagenes con los datos dados y modifica el 
     * �ndice del destino actual a la posici�n del destino agregado<br>
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se agrego un nuevo destino a la lista de destinos
     * El �ndice del nuevo destino en la lista es el nuevo �ndice del destino actual
     * @param ciudad Ciudad destino. ciudad!=null y ciudad!=""
     * @param pais Pa�s destino. pais!=null y pais!=""
     * @throws Exception Se dispara una excepci�n si es destino ya existe.
     */
    public void agregarDestino(String ciudad, String pais) throws Exception
    {
    	Destino d = buscarDestino(ciudad, pais);
    	if(d == null)
    	{
    		d = new Destino(ciudad, pais);
    		destinos.add(d);
    		posDestinoActual = destinos.size() - 1;
            obtenerImagenesDestinoActual();
    	}
    	else
    	{
    		throw new Exception("El destino ya existe en el crucero");
    	}
    }
    
    /**
     * Elimina el destino actual <br>
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se elimin� el destino actual, el nuevo destino actual es el 
     * primer destino en la lista si no es vac�a o -1 de lo contrario
     * @throws Exception Se dispara una excepci�n si no existen destinos en el crucero
     */
    public void eliminarDestinoActual() throws Exception
    {
    	if(!destinos.isEmpty())
    	{
    		destinos.remove(posDestinoActual);
    		if(!destinos.isEmpty())
    		{
    			posDestinoActual = 0;
    		}
    		else
    		{
    			posDestinoActual = -1;
    		}
    	}
    	else
    	{
    		throw new Exception("El crucero no tiene destinos");
    	}
    }
    
    /**
     * Busca el destino que tenga la ciudad y pa�s dados por par�metro <br>
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retorn� el destino cuya ciudad y pa�s coincide con los datos dados
     * @param ciudad Ciudad destino. ciudad!=null y ciudad!=""
     * @param pais Pa�s destino. pais!=null y pais!="" 
     * @return destino Destino cuya ciudad y pa�s coincide con los datos dados. 
     * Si no encuentra el destino retorna null
     */
    public Destino buscarDestino(String ciudad, String pais) 
    {
		Destino d = null;
		boolean existe = false;
		for (int i = 0; i < destinos.size() && !existe; i++) 
		{
			Destino temp = (Destino) destinos.get(i);
			if(temp.darCiudad().equalsIgnoreCase(ciudad) && temp.darPais().equalsIgnoreCase(pais))
			{
				d = temp;
				existe = true;
				posDestinoActual = i;
			}
		}	
		return d;
	}
    
    /**
     * Retorna el destino en la posici�n actual de la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retorn� el destino en la posici�n actual
     * @return destino Destino en la posici�n actual de la lista
     * si no existen destinos en el crucero retorna null
     */
    public Destino darDestinoActual()
    {
    	if(!destinos.isEmpty())
    	{
    		return (Destino) destinos.get(posDestinoActual);
    	}
    	else
    	{
    		return null;
       	}
    }
    
    /**
     * Retorna el destino en la posici�n siguiente a la actual en la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retorn� el destino en la posici�n siguiente a la actual, 
     * el �ndice de la posici�n actual incrementa en uno
     * @return destino Destino en la posici�n siguiente a la actual 
     * @throws Exception Se dispara una excepci�n si la posici�n actual es la �ltima posici�n del arreglo
     * @throws Exception Se dispara una excepci�n si no existen destinos en el crucero 
     */
    public Destino darSiguienteDestino() throws Exception
    {
    	if(!destinos.isEmpty())
    	{
        	if(posDestinoActual < destinos.size() - 1)
        	{
        		posDestinoActual++;
        		return darDestinoActual();
        	}
        	else
        	{
        		throw new Exception("Este es el �ltimo destino");
        	}
    	}
    	else
    	{
    		throw new Exception("El crucero no tiene destinos");
    	}
    }
   
    /**
     * Retorna el destino en la posici�n anterior a la actual en la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retorn� el destino en la posici�n anterior a la actual,
     * el �ndice de la posici�n actual disminuye en uno
     * @return destino Destino en la posici�n anterior a la actual 
     * @throws Exception Se dispara una excepci�n si la posici�n actual es la primera posici�n del arreglo
     * @throws Exception Se dispara una excepci�n si no existen destinos en el crucero 
     */
    public Destino darAnteriorDestino() throws Exception
    {
    	if(!destinos.isEmpty())
    	{
    		if(posDestinoActual > 0)
	    	{
	    		posDestinoActual--;
	    		return darDestinoActual();
	    	}
	    	else
	    	{
	    		throw new Exception("Este es el primer destino");
	    	}
		}
		else
		{
			throw new Exception("El crucero no tiene destinos");
		}
    }
    
    /**
     * Retorna la imagen del destino en la posici�n actual de la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retorn� la imagen del destino en la posici�n actual
     * @return foto Imagen del destino en la posici�n actual de la lista
     * @throws Exception Se dispara una excepci�n si no existen destinos en el crucero
     */
    public Foto darImagenDestinoActual() throws Exception
    {
    	Destino actual = darDestinoActual();
    	if( actual != null )
    	{
    		return actual.darImagenActual();    		
    	}
    	else
    	{
    		throw new Exception("El crucero no tiene destinos");
    	}
    }
    
    /**
     * Retorna la imagen siguiente del destino en la posici�n actual en la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retorn� la imagen siguiente del destino en la posici�n actual
     * @return foto Imagen siguiente del destino en la posici�n actual de la lista
     * @throws Exception Se dispara una excepci�n si la imagen actual del destino es la �ltima
     */
    public Foto darImagenSiguienteDelDestinoActual() throws Exception
    {
    	Destino actual = darDestinoActual();
    	if( actual != null )
    	{
    		return actual.darImagenSiguiente();
    	}
    	else
    	{
    		throw new Exception("El crucero no tiene destinos");
    	}
    }
   
    /**
     * Retorna la imagen anterior del destino en la posici�n actual en la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retorn� la imagen anterior del destino en la posici�n actual
     * @return foto Imagen anterior del destino en la posici�n actual de la lista
     * @throws Exception Se dispara una excepci�n si la imagen actual del destino es la primera
     */
    public Foto darImagenAnteriorDelDestinoActual() throws Exception
    {
    	Destino actual = darDestinoActual();
    	if( actual != null )
    	{
    		return actual.darImagenAnterior();
    	}
    	else
    	{
    		throw new Exception("El crucero no tiene destinos");
    	}
    }
    
    /**
     * Realiza la b�squeda de las im�genes en Flickr del destino actual
     * <b>pre: </b> La lista de destinos ha sido inicializada y el destino actual existe
     * <b>post: </b> Se han buscado y guardado las im�genes del destino actual
     * @throws Exception Se dispara un excepci�n si la conexi�n con el buscador de im�genes falla
     */
    private void obtenerImagenesDestinoActual() throws Exception
    {
    	if( flickr != null)
    	{
    		Destino actual = darDestinoActual();
    		actual.obtenerImagenes(flickr);
    	}	
    	else
    	{
			throw new Exception("Error al obtener las im�genes del destino");
		}
    }

    /**
     * M�todo que retorna la lista de destinos del crucero
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retorn� la lista de destinos
     * @return Lista de destinos
     */
    public ArrayList darDestinos()
    {
    	return destinos;
    }
    
    /**
     * M�todo que retorna el �ndice del destino actual
     * @return �ndice del destino
     */
    public int darIndiceDestinoActual()
    {
    	return posDestinoActual;
    }
    
    /**
     * M�todo que realiza la conexi�n con Flick para obtener la im�genes de los destinos
     */
    public void conectarFlickr()
    {
    	try 
        {
        	InputStream in = new FileInputStream("./data/setup.properties");
            Properties properties = new Properties();
            properties.load(in);
            in.close();
            
            flickr = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), new REST());
        }
    	catch (Exception e) 
    	{
			flickr = null;
		}
    }
    
    /**
     * M�todo que retorna el objeto de tipo Flickr que 
     * representa la conexi�n con el sitio web
     * @return Conexi�n con Flickr
     */
    public Flickr darConexionFlickr()
    {
    	return flickr;
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