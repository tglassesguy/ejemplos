/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 * Autor: Luis Ricardo Ruiz Rodr�guez - 10-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.cliente.mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import uniandes.cupi2.blog.comun.Articulo;
import uniandes.cupi2.blog.comun.Comentario;
import uniandes.cupi2.blog.comun.Comunicacion;
import uniandes.cupi2.blog.comun.InfoUsuario;
import uniandes.cupi2.blog.excepciones.CupiBlogComunicacionException;
import uniandes.cupi2.blog.excepciones.CupiBlogProtocoloException;

/**
 * Clase que se encarga de la comunicaci�n con el servidor del blog.<br>
 * <b>inv:</b> El v�nculo con la clase principal debe existir.<br>
 * Los canales de comunicaci�n debe estar inicializado.<br>
 */
public class ComunicacionServidorBlog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El v�nculo con la clase principal del cliente
     */
    private ClienteBlog cliente;

    /**
     * El hilo encargado de recibir los mensajes
     */
    private ThreadComunicacion threadComunicacion;

    /**
     * El canal de comunicaci�n con el servidor
     */
    private Socket socket;

    /**
     * El canal de escritura con el servidor
     */
    private PrintWriter out;

    /**
     * El canal de escritura con el servidor
     */
    private BufferedReader in;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el componente de comunicaci�n con el servidor a partir del cliente de la aplicaci�n y el socket
     * @param pCliente La clase principal del cliente. pCliente != null
     * @param pSocket El canal de comunicaci�n con el servidor. socket != null
     * @throws CupiBlogComunicacionException Se lanza excepci�n cuando no se puede crear los canales de comunicaci�n
     */
    public ComunicacionServidorBlog( ClienteBlog pCliente, Socket pSocket ) throws CupiBlogComunicacionException
    {
        cliente = pCliente;
        socket = pSocket;

        try
        {
            out = new PrintWriter( socket.getOutputStream( ), true );
            in = new BufferedReader( new InputStreamReader( socket.getInputStream( ) ) );
        }
        catch( IOException e )
        {
            throw new CupiBlogComunicacionException( "No se pueden inicializar los canales de escritura y lectura: " + e.getMessage( ) );
        }
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos de recepci�n
    // -----------------------------------------------------------------

    /**
     * Procesa cada mensaje proveniente del servidor y devuelve el comando
     * @return El comando generado por el mensaje proveniente del servidor
     * @throws CupiBlogComunicacionException En caso de que exista un error en la comunicaci�n
     */
    public Comando recibirMensaje( ) throws CupiBlogComunicacionException
    {
        Comando comando = null;
        try
        {
            String linea = in.readLine( );
            if( linea == null )
                throw new CupiBlogComunicacionException( "El usuario no se ha podido conectar." );

            // Ahora procesa, pide el nombre del comando
            String[] comandos = linea.split( Comunicacion.SEPARADOR_COMANDO );
            String nombreComando = comandos[ 0 ];
            String[] parametros = new String[0];
            if(comandos.length > 1){
                parametros = comandos[ 1 ].split( Comunicacion.SEPARADOR_PARAMETROS );
            }
            

            comando = new Comando( nombreComando, parametros );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
            throw new CupiBlogComunicacionException( "Error recibiendo un mensaje desde el servidor: " + e.getMessage( ) );
        }
        return comando;
    }

    /**
     * M�todo encargado de procesar un mensaje proveniente del servidor
     * @param comando El comando a validar. comando != null
     * @throws CupiBlogComunicacionException En caso de que exista un error en la comunicaci�n
     * @throws CupiBlogProtocoloException En caso de que haya un error en el protocolo del sistema
     */
    public void procesarMensaje( Comando comando ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        String[] parametros = comando.darParametros( );
        
        // Al iniciar la sesi�n del usuario
        if( comando.darNombre( ).equals( Comunicacion.LOGIN ) )
        {
            String nombreUsuario = parametros[ 0 ];
            String nombres = parametros[ 1 ];
            String apellidos = parametros[ 2 ];

            InfoUsuario usuario = new InfoUsuario( nombreUsuario, nombres, apellidos );
            cliente.modificarUsuario( usuario );

            threadComunicacion = new ThreadComunicacion( this );
            threadComunicacion.start( );
        }

        // Al solicitar la informaci�n de los art�culos
        else if( comando.darNombre( ).equals( Comunicacion.ARTICULOS ) )
        {
            ArrayList articulos = new ArrayList( );
            int tamanioArticulos = Integer.parseInt( parametros[ 0 ] );
            for( int i = 0; i < tamanioArticulos; i++ )
            {
                Comando comandoArticulo = recibirMensaje( );
                if( comandoArticulo.darNombre( ).equals( Comunicacion.ARTICULO ) )
                {
                    try
                    {
                        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd kk:mm" );
                        Date fecha = dateFormat.parse( comandoArticulo.darParametros( )[ 4 ] );

                        Articulo articulo = new Articulo( comandoArticulo.darParametros( )[ 0 ], comandoArticulo.darParametros( )[ 1 ], comandoArticulo.darParametros( )[ 2 ], comandoArticulo.darParametros( )[ 3 ], fecha );
                        articulos.add( articulo );
                    }
                    catch( Exception e )
                    {
                        throw new CupiBlogProtocoloException( "Error en la construcci�n del mensaje de art�culo: " + e.getMessage( ) );
                    }
                }
                else
                {
                    throw new CupiBlogProtocoloException( "No envi� correctamente la lista de las salas" );
                }
            }
            cliente.modificarListaArticulos( articulos );
        }

        // Al solicitar la informaci�n de los comentarios de un art�culo
        else if( comando.darNombre( ).equals( Comunicacion.COMENTARIOS ) )
        {
            ArrayList comentarios = new ArrayList( );
            int tamanioComentarios = Integer.parseInt( parametros[ 0 ] );
            for( int i = 0; i < tamanioComentarios; i++ )
            {
                Comando comandoComentario = recibirMensaje( );
                if( comandoComentario.darNombre( ).equals( Comunicacion.COMENTARIO ) )
                {
                    Articulo articulo = cliente.darArticuloActual( );
                    if( articulo != null )
                    {
                        try
                        {
                            SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd kk:mm" );
                            Date fecha = dateFormat.parse( comandoComentario.darParametros( )[ 2 ] );

                            Comentario comentario = new Comentario( comandoComentario.darParametros( )[ 0 ], articulo, comandoComentario.darParametros( )[ 1 ], fecha );
                            comentarios.add( comentario );
                        }
                        catch( Exception e )
                        {
                            throw new CupiBlogProtocoloException( "Error en la construcci�n del mensaje del comentario: " + e.getMessage( ) );
                        }
                    }
                }
                else
                {
                    throw new CupiBlogProtocoloException( "No envi� correctamente la lista de visitas del historial" );
                }
            }
            cliente.modificarListaComentariosArticuloActual( comentarios );
        }


        // Al recibir un mensaje con las estad�sticas
        else if( comando.darNombre( ).equals( Comunicacion.ESTADISTICAS ) )
        {
            int articulosPublicados = Integer.parseInt( parametros[ 0 ] );
            int comentariosPublicados = Integer.parseInt( parametros[ 1 ] );
            cliente.notificarResultadosEstadisticas(articulosPublicados, comentariosPublicados);
        }

        // Al recibir un mensaje de error
        else if( comando.darNombre( ).equals( Comunicacion.LOGOUT ) )
        {
            try
            {
                cerrarConexiones( );
            }
            catch( IOException e )
            {
                throw new CupiBlogComunicacionException( "No se cerr� correctamente la conexi�n con el servidor." );               
            }
        }
        // Al recibir un mensaje de error

        else if( comando.darNombre( ).equals( Comunicacion.ERROR ) )
        {
            String mensajeError = parametros[ 0 ];
            throw new CupiBlogProtocoloException( "Error en el servidor: " + mensajeError );
        }

        // En caso de enviar el contenido que no deb�a
        else
        {
            throw new CupiBlogProtocoloException( "No recibi� correctamente uno de los mensajes: " + comando.darNombre( ) );
        }

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos de env�o
    // -----------------------------------------------------------------

    /**
     * Inicia la sesi�n del usuario dado su nombre de usuario
     * @param nombreUsuario El nombre de usuario con el que se abre sesi�n. nombreUsuario != null
     * @throws CupiBlogComunicacionException En caso de que hayan errores de comunicaci�n
     * @throws CupiBlogProtocoloException En caso de que haya un error en el protocolo del sistema
     */
    public void iniciarSesion( String nombreUsuario ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        out.println( Comunicacion.LOGIN + Comunicacion.SEPARADOR_COMANDO + nombreUsuario );
        procesarMensaje( recibirMensaje( ) );
    }

    /**
     * Registra un usuario dado su nombre de usuario, los nombres y los apellidos
     * @param nombreUsuario El nombre de usuario. nombreUsuario != null
     * @param nombres Los nombres del usuario. nombres != null
     * @param apellidos Los apellidos del usuario. apellidos != null
     * @throws CupiBlogComunicacionException En caso de que hayan errores de comunicaci�n
     * @throws CupiBlogProtocoloException En caso de que haya un error en el protocolo del sistema
     */
    public void registrarUsuario( String nombreUsuario, String nombres, String apellidos ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        out.println( Comunicacion.REGISTRAR + Comunicacion.SEPARADOR_COMANDO + nombreUsuario + Comunicacion.SEPARADOR_PARAMETROS + nombres + Comunicacion.SEPARADOR_PARAMETROS + apellidos );
        procesarMensaje( recibirMensaje( ) );
    }

    /**
     * Solicita la lista de art�culos del blog.
     * @throws CupiBlogComunicacionException En caso de que hayan errores de comunicaci�n
     * @throws CupiBlogProtocoloException En caso de que haya un error en el protocolo del sistema
     */
    public void solicitarListaArticulos( ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        out.println( Comunicacion.LISTA_ARTICULOS );
    }

    /**
     * Publica el art�culo en el servidor del blog, dado el t�tulo, la categoria y el contenido.
     * @param titulo El t�tulo del art�culo. titulo != null
     * @param categoria La categor�a del art�culo. categoria != null
     * @param contenido El contenido del art�culo. contenido != null
     * @throws CupiBlogComunicacionException En caso de que hayan errores de comunicaci�n
     * @throws CupiBlogProtocoloException En caso de que haya un error en el protocolo del sistema
     */
    public void publicarArticulo( String titulo, String categoria, String contenido ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        out.println(Comunicacion.PUBLICAR_ARTICULO+Comunicacion.SEPARADOR_COMANDO+titulo+Comunicacion.SEPARADOR_PARAMETROS+categoria+Comunicacion.SEPARADOR_PARAMETROS+contenido);
    }
    
    /**
     * Publica el comentario a un art�culo dado el art�culo y el contenido del comentario.
     * @param articulo El art�culo que se va a comentar. articulo != null
     * @param contenido El contenido del comentario. contenido != null
     * @throws CupiBlogComunicacionException En caso de que hayan errores de comunicaci�n
     * @throws CupiBlogProtocoloException En caso de que haya un error en el protocolo del sistema
     */
    public void publicarComentario( Articulo articulo, String contenido ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        out.println( Comunicacion.PUBLICAR_COMENTARIO + Comunicacion.SEPARADOR_COMANDO + articulo.darUsuario( ) + Comunicacion.SEPARADOR_PARAMETROS + articulo.darTitulo( ) + Comunicacion.SEPARADOR_PARAMETROS + contenido );
    }
    
    /**
     * Busca los art�culos que pertenezcan a la categor�a que llega por par�metro.
     * @param categoria El nombre de la categoria sobre la cual se busca. categoria != null
     * @throws CupiBlogComunicacionException En caso de que hayan errores de comunicaci�n
     * @throws CupiBlogProtocoloException En caso de que haya un error en el protocolo del sistema
     */
    public void buscarArticulosCategoria( String categoria ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        out.println( Comunicacion.BUSQUEDA_CATEGORIA + Comunicacion.SEPARADOR_COMANDO + categoria );
    }

    /**
     * Solicita los comentarios del art�culo.
     * @param articulo El art�culo del cu�l se va a solicitar los comentarios. articulo != null
     * @throws CupiBlogComunicacionException En caso de que hayan errores de comunicaci�n
     * @throws CupiBlogProtocoloException En caso de que haya un error en el protocolo del sistema
     */
    public void solicitarComentariosArticulo( Articulo articulo ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        out.println( Comunicacion.COMENTARIOS_ARTICULO + Comunicacion.SEPARADOR_COMANDO + articulo.darUsuario( ) + Comunicacion.SEPARADOR_PARAMETROS + articulo.darTitulo( ) );
    }

    /**
     * Solicita las estad�sticas de publicaci�n del usuario.
     * @param nombreUsuario El nombre de usuario del cu�l se va a solicitar las estad�sticas. nombreUsuario != null
     * @throws CupiBlogComunicacionException En caso de que hayan errores de comunicaci�n
     * @throws CupiBlogProtocoloException En caso de que haya un error en el protocolo del sistema
     */
    public void solicitarEstadisticasUsuario( String nombreUsuario ) throws CupiBlogComunicacionException, CupiBlogProtocoloException
    {
        out.println( Comunicacion.ESTADISTICAS + Comunicacion.SEPARADOR_COMANDO + nombreUsuario );
    }

    /**
     * Notifica que sale del sistema de blog
     * @throws IOException En caso de error al cerrar los canales de comunicaci�n
     */
    public void cerrarSesion( ) throws IOException
    {
        out.println( Comunicacion.LOGOUT );
        verificarInvariante( );
    }

    /**
     * Cierra las conexiones con el servidor
     * @throws IOException En caso de error al cerrar los canales de comunicaci�n
     */
    public void cerrarConexiones( ) throws IOException
    {
        //reset de los elementos de la interfaz
        cliente.reiniciar( );
        
        //Cierra la conexi�n
        out.close( );
        in.close( );
        socket.close( );
    }

    /**
     * Notifica que se lanz� una excepci�n
     * @param e La excepci�n lanzada
     */
    public void notificarExcepcion( Exception e )
    {
        cliente.notificarExcepcion( e );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica la invariante de la clase <b>inv: </b> cliente != null<br>
     * socket != null<br>
     * in != null<br>
     * out != null<br>
     */
    private void verificarInvariante( )
    {
        assert cliente != null : "El v�nculo con la clase principal debe existir";
        assert socket != null : "El canal debe estar inicializado";
        assert in != null : "El buffer de lectura debe estar inicializado";
        assert out != null : "El buffer de escritura debe estar inicializado";
    }
}