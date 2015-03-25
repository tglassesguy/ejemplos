/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 * Autor: Luis Ricardo Ruiz Rodr�guez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.servidor.mundo;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import uniandes.cupi2.blog.comun.Articulo;
import uniandes.cupi2.blog.comun.Comentario;
import uniandes.cupi2.blog.comun.InfoUsuario;
import uniandes.cupi2.blog.excepciones.CupiBlogPersistenciaException;
import uniandes.cupi2.blog.excepciones.CupiBlogProtocoloException;

/**
 * Clase que represente el Servidor del blog<br>
 * <b>inv: </b><br>
 * La lista de los usuarios conectados est�n inicializada<br>
 * El manejador de eventos est� inicializado<br>
 * El manejador de persistencia est� inicializado<br>
 */
public class BlogServidor
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La lista de usuarios conectados
     */
    private ArrayList<Usuario> usuariosConectados;

    /**
     * El administrador de la persistencia en la Base de Datos
     */
    private ManejadorPersistencia manejadorPersistencia;

    /**
     * El serverSocket encargado de recibir las conexiones entrantes
     */
    private ServerSocket socketServidor;

    /**
     * La estructura de la que se carga la configuraci�n del servidor
     */
    private Properties configuracion;

    /**
     * La lista de observadores que est�n observando el mundo
     */
    private IManejadorEventosBlogServidor manejadorEventos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo servidor del blog.
     * @param manejador El manejador de eventos del servidor. manejador != null
     * @throws Exception En caso de haber error en la lectura del archivo o la creaci�n del manejador de persistencia
     */
    public BlogServidor( IManejadorEventosBlogServidor manejador ) throws Exception
    {
       configuracion = new Properties();
       configuracion.load(new FileInputStream("./data/servidor.properties"));

       manejadorPersistencia = new ManejadorPersistencia(configuracion);
       manejadorPersistencia.conectarDB();
       manejadorPersistencia.crearTablas();

       usuariosConectados = new ArrayList<Usuario>();
       manejadorEventos = manejador;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Registra un nuevo usuario
     * @throws SQLException 
     */
    public InfoUsuario registrarUsuario(String nombreUsuario, String nombresPila, String apellidos) throws CupiBlogPersistenciaException, SQLException {
    	return manejadorPersistencia.registrarUsuario(nombreUsuario, nombresPila, apellidos);
    }
    /**
     * Realiza el login de un usuario registrado
     * @throws CupiBlogPersistenciaException 
     * @throws SQLException 
     */
    public InfoUsuario loginUsuario(String nombreUsuario) throws SQLException, CupiBlogPersistenciaException {
    	return manejadorPersistencia.loginUsuario(nombreUsuario);
    }
    /**
     * Realiza en logout de un usuario
     */
    public void logoutUsuario(Usuario usuarioDesconectado) {
    	usuariosConectados.remove(usuarioDesconectado);
    	manejadorEventos.cambiosUsuariosConectados();
    }
    /**
     * Devuelve la lista de art�culos disponible
     * @throws ParseException 
     * @throws SQLException 
     */
    public ArrayList<Articulo> listarArticulos() throws SQLException, ParseException {
    	return manejadorPersistencia.listarArticulos();
    }
    /**
     * Devuelve los comentarios de un art�culo
     * @throws ParseException 
     * @throws CupiBlogPersistenciaException 
     * @throws SQLException 
     */
    public ArrayList<Comentario> listarComentarios(String tituloArticulo) throws SQLException, CupiBlogPersistenciaException, ParseException {
    	return manejadorPersistencia.listarComentarios(tituloArticulo);
    }
    /**
     * Permite la publicaci�n de un art�culo
     * @throws CupiBlogPersistenciaException 
     */
    public void publicarArticulo(String nombreUsuario, String tituloArticulo, String categoriaArticulo, String contenidoArticulo) throws CupiBlogPersistenciaException {
    	try {
    		ArrayList<Articulo> articulos = manejadorPersistencia.publicarArticulo(nombreUsuario, tituloArticulo, categoriaArticulo, contenidoArticulo);
    		manejadorEventos.nuevoArticuloPublicado();
    		for (int i = 0; i< usuariosConectados.size(); i++) usuariosConectados.get(i).refrescarArticulos(articulos);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new CupiBlogPersistenciaException("El art�culo no se ha podido publicar");
    	}
    }
    /**
     * Permite la publicaci�n de un comentario
     * @throws CupiBlogPersistenciaException 
     */
    public ArrayList<Comentario> publicarComentario(String nombreUsuario, String nombreArticulo, String contenidoComentario) throws CupiBlogPersistenciaException {
    	try {
    		return manejadorPersistencia.publicarComentario(nombreUsuario, nombreArticulo, contenidoComentario);
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new CupiBlogPersistenciaException("Comentario no se pudo publicar");
    	}
    }
    /**
     * Realiza una b�squeda por categoria de los art�culos disponibles
     */
    public ArrayList<Articulo> buscarPorCategoria(String nombreCategoria) {
    	try {
			return manejadorPersistencia.buscarPorCategoria(nombreCategoria);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
    }
    /**
     * Devuelve las estad�sticas de un usuario
     * @throws SQLException 
     */
    public int[] estadisticasUsuario(String nombreUsuario) throws SQLException {
    	return manejadorPersistencia.estadisticasUsuario(nombreUsuario);
    }
    /**
     * Devuelve la lista de art�culos escritos por el usuario dado.
     * @param usuario El usuario del que se va a consultar los art�culos. usuario != null
     * @return La lista de art�culos escritos por un usuario
     * @throws CupiBlogPersistenciaException En caso de no hacer la consulta correctamente en la base de datos
     */
    public ArrayList darArticulosUsuario( InfoUsuario usuario ) throws CupiBlogPersistenciaException {
    	System.out.println("MAIN: Solicitando articulos usuario...");
       try {
    	   ArrayList vacio = new ArrayList();
    	   vacio.add("Ning�n art�culo publicado hasta ahora");
    	   ArrayList respuesta = manejadorPersistencia.listarArticulosUsuario(usuario.darNombreUsuario());
    	   if (respuesta.size() == 0) return vacio;
    	   return respuesta;
       } catch (Exception e) {
    	   e.printStackTrace();
    	   throw new CupiBlogPersistenciaException("Error al recuperar los art�culos del usuario: \n"+e.getMessage());
       }
    }
    /**
     * Devuelve la lista de los usuarios conectados al blog
     * @return La lista de usuarios conectados al blog
     */
    public ArrayList<Usuario> darUsuariosConectados( )
    {
    	return usuariosConectados;
    }
    /**
     * Refresca la lista de usuarios conectados
     */
    public void refrescarUsuariosConectados() {
    	manejadorEventos.cambiosUsuariosConectados();
    }
    /**
     * Desconecta el servidor de la aplicaci�n
     * @throws IOException En caso de error al cerrar el canal de comunicaci�n
     * @throws  
     */
    public void desconectar() throws IOException {
        // Cierra los flujos de entrada y salida de todos los usuarios
    	for (int i = 0; i < usuariosConectados.size(); i++) usuariosConectados.get(i).desconectar();
    	// Se desconecta de la base de datos
        try {
        	manejadorPersistencia.desconectarBD();
        	// Cierra el socket de escucha del servidor
            socketServidor.close();
        } catch (Exception e) {
        	
        }
    }
    /**
     * Inicializa el socket de escucha y espera conexiones de los clientes
     * @throws IOException
     * @throws SQLException
     */
	public void esperarConexiones() throws IOException, SQLException {
		try {
			socketServidor = new ServerSocket(Integer.parseInt(configuracion.getProperty("servidor.puerto")));
			while (true) {
				Socket socketNuevoCliente = socketServidor.accept();
				System.out.println("MAIN: Solicitud de conexi�n detectada...");
				Usuario nuevoUsuario = new Usuario(this, socketNuevoCliente);
				nuevoUsuario.start();
				usuariosConectados.add(nuevoUsuario);
				manejadorEventos.cambiosUsuariosConectados();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} 
	}
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    /**
     * Verifica la invariante de la clase.<br>
     * <b>inv: </b><br>
     * usuariosConectados != null<br>
     * manejadorEventos != null<br>
     * manejadorPersistencia != null<br>
     */
    private void verificarInvariante( )
    {
        assert usuariosConectados != null : "La lista de las usuarios conectados est� inicializada";
        assert manejadorEventos != null : "El manejador de eventos del servidor del blog est� inicializada";
        assert manejadorPersistencia != null : "El manejador de persistencia del sistema de las salas debe estar inicializado";
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

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