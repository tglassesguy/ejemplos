/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Impuestos de Carros
 * Autor: Katalina Marcos - Abr 15, 2005
 * Autor: Diana Puentes - Jun 23, 2005
 * Autor: Jorge Villalobos - Jul 10, 2005
 * Autor: Pablo Barvo - 30-Ago-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.impuestosCarro.mundo;

import java.io.*;
import java.util.*;

/**
 * Calculador de impuestos
 */
public class CalculadorImpuestos
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /** Porcentaje de descuento por pronto pago */
    public static final double PORC_DESC_PRONTO_PAGO = 10.0;
    /** Valor de descuento por servicio publico */
    public static final double VALOR_DESC_SERVICIO_PUBLICO = 50000.0;
    /** Porcentaje de descuento por traslado de cuenta */
    public static final double PORC_DESC_TRASLADO_CUENTA = 5.0;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Marcas de veh�culos que maneja el calculador */
    private ArrayList marcas;
    /** Rangos de los impuestos */
    private ArrayList rangosImpuesto;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un calculador de impuestos, cargando la informaci�n de dos archivos. <br>
     * <b>post: </b> Se ley� la informaci�n de los veh�culos y los impuestos y se inicializaron los datos correctamente.
     * @throws Exception al cargar los archivos.
     */
    public CalculadorImpuestos( ) throws Exception
    {
        marcas = new ArrayList( );
        rangosImpuesto = new ArrayList( );
        cargarVehiculos( "data/vehiculos.txt" );
        cargarTablaImpuestos( "data/impuestos.properties" );
    }

    //----------------------------------------------------------------
    // M�todos
    //----------------------------------------------------------------

    /**
     * Busca una marca dado su nombre. <b>pre: </b> EL vector de marcas ya fue inicializado.
     * @param nombre Nombre de la marca. nombre != null.
     * @return La marca si la encuentra o null si no la encuentra.
     */
    private Marca buscarMarca( String nombre )
    {
        Marca marca = null;
        for( int i = 0; i < marcas.size( ) && marca == null; i++ )
        {
            Marca marcaAux = ( Marca )marcas.get( i );
            if( marcaAux.darNombre( ).equalsIgnoreCase( nombre ) )
                marca = marcaAux;
        }
        return marca;
    }

    /**
     * Adiciona una marca a la que se le podr�n calcular los impuestos. Si la marca ya existe no la adiciona. <br>
     * <b>post: </b> Si la marca no existe, se adiciona la marca a la lista.
     * @param unaMarca Marca a adicionar. unaMarca != null.
     */
    private void adicionarMarca( Marca unaMarca )
    {
        if( buscarMarca( unaMarca.darNombre( ) ) == null )
            marcas.add( unaMarca );
    }

    /**
     * Carga los datos de los veh�culos que maneja el calculador de impuestos. <br>
     * <b>post: </b> Se cargan todos los veh�culos del archivo con los datos de los veh�culos.
     * @param archivo Nombre del archivo donde se encuentran los datos de los veh�culos. archivo != null.
     * @throws Exception si ocurre alg�n error cargando los datos.
     */
    private void cargarVehiculos( String archivo ) throws Exception
    {
        Marca marca;
        Linea linea;
        Modelo modelo;
        String texto, valores[], sMarca, sLinea, sModelo;
        double precio;
        BufferedReader lector;
        try
        {
            File datos = new File( archivo );
            lector = new BufferedReader( new FileReader( datos ) );
            texto = lector.readLine( );
        }
        catch( Exception e )
        {
            throw new Exception( "Error al cargar los datos almacenados de veh�culos" );
        }

        while( texto != null )
        {
            //Si comienza con # es comentario
            if( !texto.startsWith( "#" ) && !texto.equals( "" ) )
            {
                //Lee los datos
                valores = texto.split( "," );

                if( valores.length < 3 )
                    throw new Exception( "Faltan datos l�nea: " + texto );

                sMarca = valores[ 0 ];
                sLinea = valores[ 1 ];
                sModelo = valores[ 2 ];
                try
                {
                    precio = Double.parseDouble( valores[ 3 ] );
                }
                catch( Exception e )
                {
                    throw new Exception( "El valor de precio debe ser num�rico: " + valores[ 3 ] );
                }
                //Crea la configuraci�n de un veh�culo seg�n los datos
                //Busca o crea la marca
                marca = buscarMarca( sMarca );
                if( marca == null )
                {
                    marca = new Marca( sMarca );
                    adicionarMarca( marca );
                }
                //A la marca le busca o le adiciona una l�nea
                linea = marca.buscarLinea( sLinea );
                if( linea == null )
                {
                    linea = new Linea( sLinea );
                    marca.adicionarLinea( linea );
                }
                //A la l�nea le busca o adiciona un modelo
                modelo = linea.buscarModelo( sModelo );
                if( modelo == null )
                {
                    modelo = new Modelo( sModelo, precio );
                    linea.adicionarModelo( modelo );
                }
            }
            try
            {
                //siguiente l�nea
                texto = lector.readLine( );
            }
            catch( Exception e )
            {
                throw new Exception( "Error al cargar los datos almacenados de veh�culos" );
            }
        }
    }

    /**
     * Carga la tabla de impuestos por los rangos. <br>
     * <b>post: </b> Se cargan todos valores de impuestos seg�n los rangos de valores.
     * @param archivo Ubicaci�n del archivo a leer. archivo != null.
     * @throws Exception Si ocurre un error al cargar los rangos.
     */
    private void cargarTablaImpuestos( String archivo ) throws Exception
    {
        Properties datos = new Properties( );
        int rangos = 0;
        String texto, valores[];
        double inicio, fin, porcentaje;
        RangoImpuesto rango;
        try
        {
            FileInputStream input = new FileInputStream( archivo );
            datos.load( input );
        }
        catch( Exception e )
        {
            throw new Exception( "Error al cargar los rangos de impuestos" );
        }

        try
        {
            rangos = Integer.parseInt( datos.getProperty( "numero.rangos" ) );
        }
        catch( Exception e )
        {
            throw new Exception( "El n�mero de rangos de impuestos es inv�lido" );
        }

        //Carga todos los rangos
        for( int i = 1; i <= rangos; i++ )
        {
            texto = datos.getProperty( "rango" + i );
            if( texto == null )
                throw new Exception( "Falta la definici�n de rango" + i );
            valores = texto.split( "," );
            try
            {
                inicio = Double.parseDouble( valores[ 0 ] );
                fin = Double.parseDouble( valores[ 1 ] );
                porcentaje = Double.parseDouble( valores[ 2 ] );
            }
            catch( Exception e )
            {
                throw new Exception( "Error en la definici�n de rango" + i );
            }

            rango = new RangoImpuesto( inicio, fin, porcentaje );
            //Adiciona el rango
            rangosImpuesto.ensureCapacity( 1 );
            rangosImpuesto.add( i - 1, rango );
        }
    }

    /**
     * Busca, dado un valor, el rango de impuestos al que corresponde.
     * @param valor Valor a buscar.
     * @return rango de impuesto que contiene al valor o null si no lo encuentra.
     */
    private RangoImpuesto buscarRangoImpuesto( double valor )
    {
        RangoImpuesto rango = null;
        for( int i = 0; i < rangosImpuesto.size( ) && rango == null; i++ )
        {
            RangoImpuesto rangoAux = ( RangoImpuesto )rangosImpuesto.get( i );
            if( rangoAux.contieneA( valor ) )
                rango = rangoAux;
        }
        return rango;
    }

    /**
     * Retorna el valor de aval�o de un veh�culo de la marca, l�nea y modelo dado. <br>
     * <b>pre: </b> La informaci�n de marcas, l�neas y modelos de los veh�culos ya fue inicializada correctamente.
     * @param unaMarca Marca del veh�culo. unaMarca != null.
     * @param unaLinea L�nea del veh�culo. unaLinea != null.
     * @param unModelo Modelo del veh�culo. unModelo != null.
     * @return precio de aval�o del veh�culo.
     * @throws Exception si no encuentra la marca o la l�nea o el modelo registrados.
     */
    public double buscarAvaluoVehiculo( String unaMarca, String unaLinea, String unModelo ) throws Exception
    {
        Marca marca = buscarMarca( unaMarca );
        if( marca == null )
            throw new Exception( "La marca " + unaMarca + " no est� registrada" );
        Linea linea = marca.buscarLinea( unaLinea );
        if( linea == null )
            throw new Exception( "La l�nea " + unaLinea + " no est� registrada" );
        Modelo modelo = linea.buscarModelo( unModelo );
        if( modelo == null )
            throw new Exception( "El modelo " + unModelo + " no est� registrado" );
        return modelo.darPrecio( );
    }

    /**
     * Calcular el pago de impuesto que debe hacer un veh�culo de un modelo dado. Si no encuentra un rango para el modelo devuelve 0.<br>
     * <b>pre: </b> La informaci�n de marcas, l�neas y modelos de los veh�culos ya fue inicializada correctamente.
     * @param unaMarca Marca del veh�culo. unaMarca != null.
     * @param unaLinea L�nea del veh�culo. unaLinea != null.
     * @param unModelo Modelo del veh�culo. unModelo != null.
     * @param descProntoPago Indica si aplica el descuento por pronto pago.
     * @param descServicioPublico Indica si aplica el descuento por servicio p�blico.
     * @param descTrasladoCuenta Indica si aplica el descuento por traslado de cuenta.
     * @return valor a pagar de acuerdo con las caracter�sticas del veh�culo y los descuentos que se pueden aplicar.
     * @throws Exception si no encuentra el veh�culo dado por la marca, la l�nea y el modelo.
     */
    public double calcularPago( String unaMarca, String unaLinea, String unModelo, boolean descProntoPago, boolean descServicioPublico, boolean descTrasladoCuenta ) throws Exception
    {
        double pago = 0.0;
        double precio = buscarAvaluoVehiculo( unaMarca, unaLinea, unModelo );
        //Calcula el impuesto seg�n el precio del veh�culo
        RangoImpuesto rango = buscarRangoImpuesto( precio );
        if( rango != null )
            pago = precio * ( rango.darPorcentaje( ) / 100.0 );
        // Aplica descuento por pronto pago
        if( descProntoPago )
            pago -= pago * ( PORC_DESC_PRONTO_PAGO / 100 );
        // Aplica descuento por ser un veh�culo de servicio publico
        if( descServicioPublico )
            pago -= VALOR_DESC_SERVICIO_PUBLICO;
        // Aplica descuento por cambio de ciudad del veh�culo
        if( descTrasladoCuenta )
            pago -= pago * ( PORC_DESC_TRASLADO_CUENTA / 100 );
        //si por descuentos se va a n�mero negativo se deja en cero
        if( pago < 0 )
            pago = 0;
        return pago;
    }

    //----------------------------------------------------------------
    // Puntos de Extensi�n
    //----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1 del ejercicio
     * @return respuesta
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n 2 del ejercicio
     * @return respuesta
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}