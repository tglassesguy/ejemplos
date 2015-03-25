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

import java.util.ArrayList;

/**
 * Clase encargada de procesar las expresiones aritm�ticas<br>
 * <b>inv: </b>El �rbol debe estar bien formado.
 */
public class CupiMath
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Los operadores binarios de la aplicaci�n
     */
    
    public final static String[] OPERADORES_BINARIOS = new String[]{ Suma.SIMBOLO, Resta.SIMBOLO, Multiplicacion.SIMBOLO, Division.SIMBOLO };

    /**
     * Los operadores unarios de la aplicaci�n
     */
    public final static String[] OPERADORES_UNARIOS = new String[]{ Seno.SIMBOLO, Sqrt.SIMBOLO };

    /**
     * Los tipos de par�ntesis de la aplicaci�n
     */
    public final static String[] TIPOS_PARENTESIS = new String[]{ "(", ")", "[", "]" };

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El nodo ra�z del �rbol
     */
    private Nodo raiz;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la clase principal
     */
    public CupiMath( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un parser para procesar la expresi�n ingresada por el usuario <br>
     * Invoca a la funci�n construirExpresionAritmetica para que construya el �rbol <br>
     * de la expresi�n, por �ltimo verifica el invariante de la clase.
     * @param laExpresion La expresi�n aritm�tica
     * @throws Exception En caso que la expresi�n est� mal formada
     */
    public void construirArbol( String laExpresion ) throws Exception
    {
        try
        {
            Parser parser = new Parser( laExpresion );
            raiz = construirExpresionAritmetica( parser );
            verificarInvariante( );
        }
        catch( Exception ex )
        {
            throw ex;
        }
    }

    /**
     * Funci�n para construir el �rbol de una expresi�n. El parser debe validar, en caso de que hayan m�s caracteres:<br>
     * 1. Si es un d�gito.<br>
     * 2. Si es un operador binario.<br>
     * 3. Si es un operador unario.<br>
     * 4. Si es un par�ntesis de apertura<br>
     * @param parser El ayudante usado para leer una expresi�n aritm�tica
     * @return El nodo ra�z de un �rbol con el resultado de la expresi�n
     * @throws Exception En caso que la expresi�n est� malformada
     */
    public Nodo construirExpresionAritmetica( Parser parser ) throws Exception
    {
        if( parser.hayMasCaracteres( ) && parser.esDigito( ) )
            return construirNodoNumero( parser );
        else if( parser.hayMasCaracteres( ) && parser.esOperadorBinario( ) )
            return construirOperadorBinario( parser );
        else if( parser.hayMasCaracteres( ) && parser.esOperadorUnario( ) )
            return construirOperadorUnario( parser );
        else if( parser.hayMasCaracteres( ) && parser.esParentesisApertura( ) )
            return construirSubExpresionAritmetica( parser );
        else
            throw new Exception( "Expresi�n mal formada" );
    }

    /**
     * Funci�n recursiva para construir un nodo de tipo n�mero <br>
     * Construye un n�mero que puede ser entero o decimal. Debe validar si tiene puntos que separan los decimales de la parte entera.<br>
     * <b>pre: </b>El caracter actual en el parser es un valor num�rico.
     * En caso que exista un operador posterior al n�mero, el n�mero se le debe asignar como su operando
     * @param parser El parser encargado de procesar las expresiones. parser != null
     * @return En caso que no haya un operador posterior al n�mero debe retornar un nodo que represente al n�mero, en caso contrario debe 
     * retornar un nodo producto de procesar el resto de la expresi�n aritm�tica
     * @throws Exception En caso que la expresi�n est� mal formada
     */
    public Nodo construirNodoNumero( Parser parser ) throws Exception
    {
        int elDigito = parser.darDigito( );
        Numero numero = new Numero( elDigito );
        int numDecimales = 0;
        // Revisar si se est� leyendo un n�mero de m�s de un d�gito
        while( parser.hayMasCaracteres( ) && ( parser.esDigito( ) || parser.esPuntoDecimal( ) ) )
        {
            if( parser.esDigito( ) )
            {
                int nuevoDigito = parser.darDigito( );
                numero.asignarValor( numero.darValor( ) + nuevoDigito );
            }
            else
            {
                if( numDecimales > 0 )
                    throw new Exception( "Un n�mero solo debe tener un punto decimal" );
                else
                {
                    String puntoDecimal = parser.darPuntoDecimal( );
                    numero.asignarValor( numero.darValor( ) + puntoDecimal );
                    numDecimales++;
                }
            }

        }
        if( parser.hayMasCaracteres( ) )
        {
            Nodo padre = construirExpresionAritmetica( parser );
            padre.asignarNodoIzquierdo( numero );
            return padre;
        }
        else
        {
            return numero;
        }
    }

    /**
     * Construye un nodo para representar un operador binario.<br>
     * <b>pos: </b>La expresi�n aritm�tica nunca puede terminar en un operador binario por lo tanto se debe procesar el resto de la expresi�n aritm�tica.
     * @param parser El parser de la expresi�n aritm�tica. parser != null
     * @return Un nodo que representa a un operador binario
     * @throws Exception En caso que la expresi�n este mal formada
     */
    public Nodo construirOperadorBinario( Parser parser ) throws Exception
    {
        Nodo padre = null;
        String elOperador = parser.darOperadorBinario( );
        if( elOperador.equals( Suma.SIMBOLO ) )
            padre = new Suma( elOperador );
        else if( elOperador.equals( Resta.SIMBOLO ) )
            padre = new Resta( elOperador );
        else if( elOperador.equals( Multiplicacion.SIMBOLO ) )
            padre = new Multiplicacion( elOperador );
        else if(elOperador.equals(Division.SIMBOLO))
        	padre = new Division(elOperador);
        
        padre.asignarNodoDerecho( construirExpresionAritmetica( parser ) );
        return padre;
    }

    /**
     * Construye un nodo para representar un operador unario <br>
     * Luego de procesar el operador hay que procesar su operando <br>
     * <b>pos: </b>Un operador unario siempre viene seguido de un operando, el cual es una expresi�n aritm�tica entre par�ntesis cuadrados.<br>
     * @param parser El parser de la expresi�n aritm�tica. parser != null
     * @return Un nodo que representa a un operador unario
     * @throws Exception En caso que la expresi�n este mal formada
     */
    public Nodo construirOperadorUnario( Parser parser ) throws Exception
    {
        Nodo padre = null;
        String elOperador = parser.darOperadorUnario( );

        if( elOperador.equals( Seno.SIMBOLO ) )
            padre = new Seno( Seno.SIMBOLO );
        else if( elOperador.equals( Sqrt.SIMBOLO ) )
            padre = new Sqrt( Sqrt.SIMBOLO );

        padre.asignarNodoIzquierdo( construirOperandoDeOperadorUnario( parser ) );
        if( parser.hayMasCaracteres( ) )
        {
            Nodo operadorUnario = padre;
            Nodo operadorBinario = construirExpresionAritmetica( parser );
            operadorBinario.asignarNodoIzquierdo( operadorUnario );
            return operadorBinario;
        }
        else
        {
            return padre;
        }
    }

    /**
     * Construye un nodo que contiene una expresi�n aritm�tica entre par�ntesis <br>
     * Si hay m�s caracteres luego de la sub-expresi�n debe procesar el resto de la expresi�n
     * @param parser El parser de la expresi�n aritm�tica. parser != null
     * @return Un nodo que corresponde a la ra�z de una expresi�n aritm�tica
     * @throws Exception En caso que la sub-expresi�n sea una expresi�n aritm�tica mal formada
     */
    public Nodo construirSubExpresionAritmetica( Parser parser ) throws Exception
    {
        Parser parserSubexpresion = new Parser( parser.darExpresionEntreParentesis( ) );
        Nodo nodoParentesis = construirExpresionAritmetica( parserSubexpresion );
        if( parser.hayMasCaracteres( ) )
        {
            Nodo padre = construirExpresionAritmetica( parser );
            padre.asignarNodoIzquierdo( nodoParentesis );
            return padre;
        }
        else
            return nodoParentesis;
    }

    /**
     * Construye un nodo que contiene una expresi�n aritm�tica entre par�ntesis y representa al operando de un operador unario <br>
     * @param parser El parser de la expresi�n aritm�tica. parser != null
     * @return Un nodo que corresponde a la ra�z de una expresi�n
     * @throws Exception En caso que el operando sea una expresi�n aritm�tica mal formada
     */
    public Nodo construirOperandoDeOperadorUnario( Parser parser ) throws Exception
    {
        Parser parserSubexpresion = new Parser( parser.darExpresionEntreParentesisOperadorUnario( ) );
        Nodo nodoParentesis = construirExpresionAritmetica( parserSubexpresion );
        return nodoParentesis;
    }

    /**
     * Construye un �rbol a partir de una expresi�n aritm�tica Recorre dicho �rbol para evaluar la expresi�n <b>pre: </b>El �rbol de la expresi�n aritm�tica est� construido<br>
     * @param laExpresion La expresi�n aritm�tica. laExpresion != null
     * @return El valor de la expresi�n aritm�tica
     * @throws Exception En caso que la expresi�n no comience por un d�gito
     */
    public double evaluarExpresion( String laExpresion ) throws Exception
    {
        Parser expr = new Parser(laExpresion);
        raiz = construirExpresionAritmetica(expr);
        return raiz.evaluar();
    }

    /**
     * Convierte una expresi�n aritm�tica expresada en notaci�n infija a notaci�n polaca. Recorre el �rbol en preorden
     * @param laExpresion La cadena de caracteres que contiene la expresi�n. laExpresion != null
     * @return La expresi�n ingresada por el usuario en notaci�n polaca
     * @throws Exception En caso de que la expresi�n no sea correcta
     */
    public String convertirEnNotacionPolaca( String laExpresion ) throws Exception
    {
        construirArbol( laExpresion );
        return raiz.convertirEnNotacionPolaca();
    }

    /**
     * Retorna la ra�z del �rbol que representa la expresi�n aritm�tica
     * @return La ra�z del �rbol que representa la expresi�n aritm�tica
     */
    public Nodo darRaiz( )
    {
        return raiz;
    }
    public String buscarNumero(double numeroBuscado) {
    	ArrayList resultados = new ArrayList();
    	raiz.buscarNumero(0,resultados,numeroBuscado);
    	if (resultados.size() == 0) {
    		return "NO existe ning�n n�mero que coincida con el valor ingresado";
    	} else {
    		String respuesta = "El n�mero est� en los niveles:";
    		for (int i = 0; i < resultados.size(); i++)
    			respuesta += " "+resultados.get(i);
    		return respuesta;
    	}
    }
    
    public String elementosRama(double numeroBuscado) {
    	String resultado = raiz.elementosRama(numeroBuscado);
    	String respuesta = "";
    	if (resultado.equals("")) {
    		respuesta += "NO existe ning�n n�mero que coincida con el valor ingresado";
    	} else {
    		respuesta += "Los elementos de la primera rama en la que aparece el n�mero son: "+resultado;
    	}
    	if (raiz.arbolCompleto()) return respuesta + " y el arbol est� completo.";
    	else return respuesta + " y el arbol no est� completo.";
    	
    }
    // -----------------------------------------------------------------
    // Verificaci�n Invariante
    // -----------------------------------------------------------------
    
    /**
     * Verifica que el �rbol de la expresi�n aritm�tica est� bien formado.
     */
    private void verificarInvariante( )
    {
    	raiz.verificarInvariante();
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
