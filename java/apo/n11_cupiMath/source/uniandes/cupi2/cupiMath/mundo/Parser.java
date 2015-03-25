/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_cupiMath
 * Autor: cupi2 - 15-mar-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiMath.mundo;

/**
 * Clase auxiliar para leer la expresi�n aritm�tica ingresada por el usuario
 */
public class Parser
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La cadena de caracteres con la expresi�n aritm�tica
     */
    private String expresionAritmetica;

    /**
     * La posici�n en la cual se encuentra leyendo en la expresi�n aritm�tica
     */
    int posicion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa la posici�n en 0 y la expresionAritmetica con el valor recibido por par�metro
     * <b>pre: </b>En la expresi�n aritm�tica no hay sub-expresiones entre par�ntesis al mismo nivel, �nicamente pueden haber par�ntesis anidados<br>
     * @param laExpresion La expresi�n aritm�tica. laExpresion != null
     */
    public Parser( String laExpresion )
    {
        expresionAritmetica = laExpresion;
        posicion = 0;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna true s� en la posici�n actual hay un d�gito, false en caso contrario
     * @return true si en la posici�n actual hay un d�gito, false en caso contrario
     */
    public boolean esDigito( )
    {
        boolean esDigito = false;
        try
        {
            String digito = expresionAritmetica.substring( posicion, posicion + 1 );
            Integer.parseInt( digito );
            esDigito = true;
        }
        catch( NumberFormatException ex )
        {
            //No hace nada 
        }
        return esDigito;

    }

    /**
     * Retorna el d�gito que hay en posici�n actual y aumenta la posici�n en uno
     * @return El d�gito en la posici�n actual
     */
    public int darDigito( )
    {
        int digito = Integer.parseInt( expresionAritmetica.substring( posicion, posicion + 1 ) );
        posicion++;
        return digito;
    }

    /**
     * Verifica si el car�cter actual es un punto decimal
     * @return true si es un punto decimal, false en caso contrario
     */
    public boolean esPuntoDecimal( )
    {
        boolean esPuntoDecimal = false;
        String punto = expresionAritmetica.substring( posicion, posicion + 1 );
        if( punto.equals( "." ) )
            esPuntoDecimal = true;
        return esPuntoDecimal;
    }

    /**
     * Retorna un punto decimal y aumenta la posici�n en uno
     * @return El punto decimal de la expresi�n
     */
    public String darPuntoDecimal( )
    {
        String puntoDecimal = expresionAritmetica.substring( posicion, posicion + 1 );
        posicion++;
        return puntoDecimal;
    }

    /**
     * Verifica si en la posici�n actual hay un operador binario. <br>
     * Un operador binario es un car�cter. Los operadores binarios son: SUMA, RESTA, MULTIPLICACION y DIVISION
     * @return true s� a partir de la posici�n actual hay un operador, false en caso contrario
     */
    public boolean esOperadorBinario( )
    {
        boolean esOperador = false;
        String operadorBinario = expresionAritmetica.substring( posicion, posicion + 1 );
        for(int i=0;i<CupiMath.OPERADORES_BINARIOS.length && !esOperador;i++)
        {
            if(operadorBinario.equals( CupiMath.OPERADORES_BINARIOS[i] ))
                esOperador=true;
        }
        
        return esOperador;
    }

    /**
     * Verifica si en la posici�n actual hay un operador unario: <br>
     * Un operador unario es una cadena de caracteres. Los operadores unarios son: SENO y SQRT
     * @return true s� a partir de la posici�n actual hay un operador, false en caso contrario
     */
    public boolean esOperadorUnario( )
    {
        boolean esOperador = false;
        String operadorUnario = "";
        char caracterActual = expresionAritmetica.substring( posicion, posicion + 1 ).charAt( 0 );
        int posicionTemp = posicion;
        while( ( caracterActual >= 'a' && caracterActual <= 'z' ) || ( caracterActual >= 'A' && caracterActual <= 'Z' ) )
        {
            operadorUnario = operadorUnario + caracterActual;
            posicionTemp++;
            caracterActual = expresionAritmetica.substring( posicionTemp, posicionTemp + 1 ).charAt( 0 );
        }
        for(int i=0; i< CupiMath.OPERADORES_UNARIOS.length&&!esOperador;i++)
        {
            if(operadorUnario.equals( CupiMath.OPERADORES_UNARIOS[i] ) )
                esOperador=true;
        }
        
        return esOperador;
    }

    /**
     * Retorna el operador binario en la posici�n actual y aumenta la posici�n actual en uno
     * @return El operador binario en la posici�n actual
     */
    public String darOperadorBinario( )
    {
        String operadorBinario = expresionAritmetica.substring( posicion, posicion + 1 );
        posicion++;
        return operadorBinario;

    }

    /**
     * Retorna el operador unario que se encuentra a partir de la posici�n actual y aumenta la posici�n actual de acuerdo al n�mero de caracteres del operador
     * @return El operador unario de la expresi�n
     */
    public String darOperadorUnario()
    {
        String operadorUnario = "";
        char caracterActual = expresionAritmetica.substring( posicion, posicion + 1 ).charAt( 0 );
        int posicionTemp = posicion;
        while( ( caracterActual >= 'a' && caracterActual <= 'z' ) || ( caracterActual >= 'A' && caracterActual <= 'Z' ) )
        {
            operadorUnario = operadorUnario + caracterActual;
            posicionTemp++;
            caracterActual = expresionAritmetica.substring( posicionTemp, posicionTemp + 1 ).charAt( 0 );

        }
        posicion = posicion + operadorUnario.length( );
        return operadorUnario;
    }

    /**
     * Devuelve si la expresi�n inicia con un par�ntesis de apertura
     * @return True en caso que sea un par�ntesis de apertura, false en caso contrario
     */
    public boolean esParentesisApertura( )
    {
        boolean esParentesisApertura = false;
        String parentesis = expresionAritmetica.substring( posicion, posicion + 1 );
        if( parentesis.equals( "(" ) )
            esParentesisApertura = true;
        return esParentesisApertura;
    }

    /**
     * Verifica que la posici�n sea parte de la cadena. Ya que el car�cter en la posici�n no ha sido le�da, y en cuyo caso deben haber m�s caracteres.
     * @return True en caso que hayan m�s caracteres sin leer en la expresi�n False en caso contrario
     */
    public boolean hayMasCaracteres( )
    {
        boolean hayMas = false;
        if( posicion <= expresionAritmetica.length( ) - 1 )
            hayMas = true;
        return hayMas;
    }

    /**
     * Retorna una subcadena de la expresi�n aritm�tica, la cual contiene a una sub-expresi�n entre par�ntesis La subcadena empieza en la posici�n + 1 y termina en la posici�n
     * del �ltimo par�ntesis que haya en la expresi�n
     * @return Una sub-expresi�n que est� entre par�ntesis
     */
    public String darExpresionEntreParentesis( )
    {
        int ultimaPosicionParentesis = expresionAritmetica.lastIndexOf( ")", expresionAritmetica.length( ) - 1 );
        String respuesta = expresionAritmetica.substring( posicion + 1, ultimaPosicionParentesis );
        posicion = ultimaPosicionParentesis + 1;
        return respuesta;
    }

    /**
     * Retorna una subcadena que corresponde a la sub-expresi�n que hace de operando del operador unario
     * @return La expresi�n que hace de operando del operador unario
     */
    public String darExpresionEntreParentesisOperadorUnario( )
    {
        int siguientePosicionParentesis = expresionAritmetica.indexOf( "]", posicion );
        String respuesta = expresionAritmetica.substring( posicion + 1, siguientePosicionParentesis );
        posicion = siguientePosicionParentesis + 1;
        return respuesta;
    }
    
    /**
     * Retorna la posici�n de la cadena en la cual est� el parser
     * @return La posici�n de la cadena en la cual est� el parser
     */
    public int darPosicion()
    {
        return posicion;
    }
}
