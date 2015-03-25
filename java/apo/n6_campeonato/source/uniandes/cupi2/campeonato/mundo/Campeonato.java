/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_campeonato 
 * Autor: Mario S�nchez - 21/07/2005 
 * Autor: J. Villalobos - 28/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.campeonato.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Esta clase representa un campeonato de f�tbol
 */
public class Campeonato
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Indica que el partido no se ha jugado
     */
    public static final int SIN_JUGAR = -1;

    /**
     * Indica que el partido no se puede jugar porque ser�a un equipo jugando contra �l mismo
     */
    public static final int INVALIDO = -2;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * N�mero de equipos en el campeonato
     */
    private int maxEquipos;

    /**
     * Es la tabla con los goles. En la posici�n (i,j) aparece el n�mero de goles que el equipo i le hizo al equipo j
     */
    private int[][] tablaGoles;

    /**
     * Es un arreglo con los equipos en el campeonato. En la posici�n i aparece la descripci�n del equipo i
     */
    private Equipo[] equipos;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo campeonato de f�tbol.
     * @param arch Es el archivo que contiene la descripci�n de los equipos del campeonato. arch != null.
     * @throws Exception Se lanza esta excepci�n si hay problemas cargando el archivo.
     */
    public Campeonato( File arch ) throws Exception
    {
        Properties datos = cargarInfoCampeonato( arch );
        inicializarEquipos( datos );
        inicializarTablaGoles( );
    }

    /**
     * Carga la informaci�n de los equipos del campeonato en un objeto de tipo Properties.
     * @param arch Es el archivo que contiene la descripci�n de los equipos del campeonato
     * @return un objeto de la clase Properties con la informaci�n del archivo.
     * @throws Exception Se lanza esta excepci�n si el archivo no existe o si el formato del archivo no es v�lido y no puede ser le�do.
     */
    private Properties cargarInfoCampeonato( File arch ) throws Exception
    {
        Properties datos = new Properties( );
        FileInputStream in = new FileInputStream( arch );
        try
        {
            datos.load( in );
            in.close( );
        }
        catch( Exception e )
        {
            throw new Exception( "Formato inv�lido" );
        }
        return datos;
    }

    /**
     * Inicializa el arreglo de equipos con la informaci�n que recibe en el par�metro de entrada. <br>
     * <b>post: </b> El arreglo de equipos fue inicializado con los nombres de los equipos que ven�an en el par�metro de entrada.
     * @param datos contiene la informaci�n cargada del archivo para inicializar el campeonato. Esta informaci�n es completa y v�lida.
     */
    private void inicializarEquipos( Properties datos )
    {
        String strNumeroEquipos = datos.getProperty( "campeonato.equipos" );
        maxEquipos = Integer.parseInt( strNumeroEquipos );
        // Crea el arreglo de equipos, reservando el espacio definido en la propiedad "campeonato.equipos"
        equipos = new Equipo[maxEquipos];
        // Lee el nombre de cada equipo de la respectiva propiedad y crea el objeto que lo representa
        for( int i = 0; i < maxEquipos; i++ )
        {
            String nombreEquipo = datos.getProperty( "campeonato.nombre" + i );
            equipos[ i ] = new Equipo( nombreEquipo );
        }
    }

    /**
     * Crea la matriz que contiene la tabla de goles. Es una matriz cuadrada de maxEquipos filas y maxEquipos columnas
     */
    private void inicializarTablaGoles( )
    {
        // Crea la matriz que contiene la tabla de goles
        tablaGoles = new int[maxEquipos][maxEquipos];
        // Inicializa todos los marcadores, dejando en la diagonal una marca especial
        for( int i = 0; i < maxEquipos; i++ )
        {
            for( int j = 0; j < maxEquipos; j++ )
            {
                if( i != j )
                    tablaGoles[ i ][ j ] = SIN_JUGAR;
                else
                    tablaGoles[ i ][ j ] = INVALIDO;
            }
        }
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Registra el resultado de un partido. <br>
     * <b>pre: </b> Los equipos que participan en el campeonato ya fueron inicializados. <br>
     * <b>post: </b> Se actualiz� la tabla de goles con el resultado indicado.
     * @param eq1 Es el n�mero del equipo 1.
     * @param eq2 Es el n�mero del equipo 2.
     * @param gol1 Es el n�mero de goles marcados por el equipo eq1.
     * @param gol2 Es el n�mero de goles marcados por el equipo eq2.
     * @throws Exception Se lanza esta excepci�n si los equipos no son v�lidos, si el n�mero de goles es inv�lido o si el partido ya se ha jugado.
     */
    public void registrarResultado( int eq1, int eq2, int gol1, int gol2 ) throws Exception
    {
        if( eq1 < 0 || eq1 >= maxEquipos || eq2 < 0 || eq2 >= maxEquipos )
        {
            throw new Exception( "Equipos incorrectos" );
        }
        if( eq1 == eq2 )
        {
            throw new Exception( "Son el mismo equipo" );
        }
        if( gol1 < 0 || gol2 < 0 )
        {
            throw new Exception( "N�mero de goles inv�lido" );
        }
        if( tablaGoles[ eq1 ][ eq2 ] != SIN_JUGAR || tablaGoles[ eq2 ][ eq1 ] != SIN_JUGAR )
        {
            throw new Exception( "Partido ya jugado" );
        }
        tablaGoles[ eq1 ][ eq2 ] = gol1;
        tablaGoles[ eq2 ][ eq1 ] = gol2;
    }

    /**
     * Retorna el n�mero de goles marcados por el equipo eq1 al equipo eq2.
     * @param eq1 Es el n�mero del equipo 1. eq1 es un n�mero de equipo v�lido.
     * @param eq2 Es el n�mero del equipo 2. eq2 es un n�mero de equipo v�lido.
     * @return n�mero de goles marcados
     */
    public int darGolesMarcados( int eq1, int eq2 )
    {
        return tablaGoles[ eq1 ][ eq2 ];
    }

    /**
     * Retorna el n�mero de equipos del campeonato.
     * @return n�mero de equipos del campeonato.
     */
    public int darNumeroEquipos( )
    {
        return maxEquipos;
    }

    /**
     * Retorna el equipo cuyo n�mero se pasa como par�metro.
     * @param eq N�mero del equipo. eq es un n�mero de equipo v�lido.
     * @return el equipo cuyo n�mero se pasa como par�metro.
     */
    public Equipo darEquipo( int eq )
    {
        return equipos[ eq ];
    }

    /**
     * Retorna el n�mero total de partidos ganados por un equipo.
     * @param equipo N�mero del equipo. equipo es un n�mero v�lido.
     * @return n�mero de partidos ganados. n�mero >= 0.
     */
    public int darPartidosGanados( int equipo )
    {
        int ganados = 0;
        for( int i = 0; i < maxEquipos; i++ )
            if( tablaGoles[ equipo ][ i ] != SIN_JUGAR && tablaGoles[ equipo ][ i ] != INVALIDO && tablaGoles[ equipo ][ i ] > tablaGoles[ i ][ equipo ] )
                ganados++;
        return ganados;
    }

    /**
     * Retorna el n�mero total de partidos perdidos por un equipo.
     * @param equipo N�mero del equipo. equipo es un n�mero v�lido.
     * @return n�mero de partidos perdidos. n�mero >= 0.
     */
    public int darPartidosPerdidos( int equipo )
    {
        int perdidos = 0;
        for( int i = 0; i < maxEquipos; i++ )
            if( tablaGoles[ equipo ][ i ] != SIN_JUGAR && tablaGoles[ equipo ][ i ] != INVALIDO && tablaGoles[ equipo ][ i ] < tablaGoles[ i ][ equipo ] )
                perdidos++;
        return perdidos;
    }

    /**
     * Retorna el n�mero total de partidos empatados por un equipo.
     * @param equipo N�mero del equipo. equipo es un n�mero v�lido.
     * @return n�mero de partidos empatados. n�mero >= 0.
     */
    public int darPartidosEmpatados( int equipo )
    {
        int empatados = 0;
        for( int i = 0; i < maxEquipos; i++ )
            if( tablaGoles[ equipo ][ i ] != SIN_JUGAR && tablaGoles[ equipo ][ i ] != INVALIDO && tablaGoles[ equipo ][ i ] == tablaGoles[ i ][ equipo ] )
                empatados++;
        return empatados;
    }

    /**
     * Retorna el n�mero total de partidos jugados por un equipo.
     * @param equipo N�mero del equipo. equipo es un n�mero v�lido.
     * @return n�mero de partidos jugados. n�mero >= 0.
     */
    public int darPartidosJugados( int equipo )
    {
        int jugados = 0;
        for( int i = 0; i < maxEquipos; i++ )
            if( tablaGoles[ equipo ][ i ] != SIN_JUGAR && tablaGoles[ equipo ][ i ] != INVALIDO )
                jugados++;
        return jugados;
    }

    /**
     * @param equipo N�mero del equipo. equipo es un n�mero v�lido.
     * @return n�mero de goles en contra. n�mero >= 0.
     */
    public int darGolesEnContra( int equipo )
    {
        int golesEnContra = 0;
        for( int i = 0; i < maxEquipos; i++ )
            if( tablaGoles[ equipo ][ i ] != SIN_JUGAR && tablaGoles[ equipo ][ i ] != INVALIDO )
                golesEnContra += tablaGoles[ i ][ equipo ];
        return golesEnContra;
    }

    /**
     * @param equipo N�mero del equipo. equipo es un n�mero v�lido.
     * @return n�mero de goles a favor. n�mero >= 0.
     */
    public int darGolesAFavor( int equipo )
    {
        int golesAFavor = 0;
        for( int i = 0; i < maxEquipos; i++ )
            if( tablaGoles[ equipo ][ i ] != SIN_JUGAR && tablaGoles[ equipo ][ i ] != INVALIDO )
                golesAFavor += tablaGoles[ equipo ][ i ];
        return golesAFavor;
    }

    /**
     * Retorna el n�mero total de puntos que tiene un equipo.
     * @param equipo n�mero del equipo. equipo es un n�mero v�lido.
     * @return n�mero total de puntos en el campeonato que tiene el equipo. n�mero >= 0.
     */
    public int darTotalPuntos( int equipo )
    {
        return 3 * darPartidosGanados( equipo ) + darPartidosEmpatados( equipo );
    }

    //-----------------------------------------------------------------
    // Puntos de Extensi�n
    //-----------------------------------------------------------------

    /**
     * Es el punto de extensi�n 1
     * @return Respuesta 1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Es el punto de extensi�n 2
     * @return Respuesta 2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}