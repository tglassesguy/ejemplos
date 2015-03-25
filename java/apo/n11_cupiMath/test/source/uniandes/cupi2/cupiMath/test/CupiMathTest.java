/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_expresionesAritmeticas
 * Autor: cupi2 - 15-mar-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.CupiMath;
import uniandes.cupi2.cupiMath.mundo.Multiplicacion;
import uniandes.cupi2.cupiMath.mundo.Nodo;
import uniandes.cupi2.cupiMath.mundo.Numero;
import uniandes.cupi2.cupiMath.mundo.OperadorUnario;
import uniandes.cupi2.cupiMath.mundo.Parser;
import uniandes.cupi2.cupiMath.mundo.Resta;
import uniandes.cupi2.cupiMath.mundo.Seno;
import uniandes.cupi2.cupiMath.mundo.Sqrt;
import uniandes.cupi2.cupiMath.mundo.Suma;

/**
 * Clase para verificar que los m�todos de la clase ExpresionesAritmeticas est�n <br>
 * correctamente implementados
 * Es necesario correr las pruebas usando aserciones 
 */
public class CupiMathTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private CupiMath cupiMath;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva cupiMath vac�a
     *  
     */
    private void setupEscenario1( )
    {
        cupiMath = new CupiMath( );
    }
    
    /**
     * Valida que la ra�z del �rbol de una expresi�n aritm�tica no se construya <br>
     * antes de haberse ingresado una expresi�n
     */
    public void testCupiMath()
    {
        setupEscenario1( );
        assertNull( "La raiz del �rbol de una expresi�n aritm�tica s�lo se debe construir despues que se ha ingresado la expresi�n",cupiMath.darRaiz( ) );
    }


    /**
     * Probar que se construya un nodo correctamente que represente a un n�mero <br>
     * Se valida que el nodo sea una instancia de un n�mero, <br>
     * que su valor sea correcto y que no tenga hijos
     */
    public void construirNodoNumero()
    {
        setupEscenario1( );
        String expresionAritmetica="852.25";
        Parser parser=new Parser(expresionAritmetica);
        try
        {
            Nodo nodoOperando=cupiMath.construirNodoNumero( parser );
            String valorNodoCreado=nodoOperando.darValor();
            assertEquals( "El nodo no se cre� con el valor esperado",expresionAritmetica, valorNodoCreado );
            assertTrue("El nodo creado debe ser un n�mero",nodoOperando instanceof Numero);
            assertNull( "El n�mero creado no debe tener hijos", nodoOperando.darNodoDerecho( ) );
            assertNull( "El n�mero creado no debe tener hijos", nodoOperando.darNodoIzquierdo( ) );
        }
        catch( Exception e )
        {
            fail("Error al construir un n�mero");
        }
    }
    
    /**
     * Valida que se construya correctamente un nodo que represente a un operador binario, <br>
     * Se valida que el nodo sea una instancia de un operador binario y que sus dos hijos <br>
     * hayan sido asignados correctamente(Tipo y Valor). <br>
     */
    public void construirOperadorBinario()
    {
        setupEscenario1( );
        String expresionTemp="2+5";
        Parser parserTemp=new Parser(expresionTemp);
        Nodo nodoOpBinario;
        try
        {
            nodoOpBinario = cupiMath.construirExpresionAritmetica( parserTemp );
            assertTrue( "El operador creado debe ser una suma",nodoOpBinario instanceof Suma );
            Nodo nodoIzquierdo=nodoOpBinario.darNodoIzquierdo( );
            assertTrue( "El nodo izquierdo deb�a ser un n�mero",nodoIzquierdo instanceof Numero );
            assertEquals( "2", nodoIzquierdo.darValor( ) );
            Nodo nodoDerecho=nodoOpBinario.darNodoDerecho( );
            assertTrue( "El nodo derecho deb�a ser un n�mero",nodoDerecho instanceof Numero );
            assertEquals( "5", nodoDerecho.darValor( ) );
        }
        catch( Exception e )
        {
            fail();
        }
        
    }
    
    
    /**
     * Valida que se construya correctamente un nodo que represente a un operador unario, <br>
     * Se valida que el nodo sea una instancia de un operador unario, que su hijo izquierdo <br>
     * haya sido asignado y que su hijo derecho sea null <br>
     */
    public void testConstruirOperadorUnario()
    {
        setupEscenario1( );
        String expresionTemp=Seno.SIMBOLO+"[45]";
        Parser parserTemp=new Parser(expresionTemp);
        Nodo nodoOpUnario;
        try
        {
            nodoOpUnario = cupiMath.construirOperadorUnario( parserTemp );
            assertTrue( "El nodo creado debe ser un operador unario",nodoOpUnario instanceof OperadorUnario );
            Nodo nodoOperando=nodoOpUnario.darNodoIzquierdo( );
            assertTrue( "El operador unario no se cre� con su operando",nodoOperando instanceof Numero );
            assertEquals("El valor del operando del operador unario no es el esperado" ,"45", nodoOperando.darValor( ) );
            assertNull("El nodo derecho de un operador unario debe ser null",nodoOpUnario.darNodoDerecho( ));
        }
        catch( Exception e )
        {
            fail("Error al construir un operador unario");
        }    
        
    }
    
    /**
     * Valida que se construya correctamente el operando de un operador unario. El sub�rbol <br>
     * que lo representa debe ser igual al de la expresi�n que est� entre corchetes cuadrados <br>
     */
    public void testConstruirOperandoDeOperadorUnario()
    {
        setupEscenario1( );
        String expresionTemp="[57.7-30.7]";
        Parser parserTemp=new Parser(expresionTemp);
        Nodo operando;
        try
        {
            operando = cupiMath.construirOperandoDeOperadorUnario( parserTemp );
            assertTrue( "El operador creado debe ser una resta",operando instanceof Resta );
            Nodo nodoIzquierdo=operando.darNodoIzquierdo( );
            assertTrue( "El nodo izquierdo deb�a ser un n�mero",nodoIzquierdo instanceof Numero );
            assertEquals( "57.7", nodoIzquierdo.darValor( ) );
            Nodo nodoDerecho=operando.darNodoDerecho( );
            assertTrue( "El nodo derecho deb�a ser un n�mero",nodoDerecho instanceof Numero );
            assertEquals( "30.7", nodoDerecho.darValor( ) );
        }
        catch( Exception e )
        {
            fail("Error al construir el operando de un operador unario");
        }
       
    }
    
    
    /**
     * Verifica que se construya correctamente una expresi�n aritmetica que est� entre parentesis
     */
    public void testConstruirSubExpresionAritmetica()
    {
        setupEscenario1( );
        String expresionTemp="("+Sqrt.SIMBOLO+ "[100]"+ "*20" + ")";
        Parser parserTemp=new Parser(expresionTemp);
        try
        {
            Nodo raiz=cupiMath.construirSubExpresionAritmetica( parserTemp );
            assertTrue("La raiz de la subexpresi�n debe ser una multiplicaci�n", raiz instanceof Multiplicacion);
            assertTrue("El hijo izquierdo de la raiz de la subexpresi�n debe ser una ra�z cuadrada", raiz.darNodoIzquierdo( ) instanceof Sqrt);
            assertTrue("El hijo derecho de la raiz de la subexpresi�n debe ser una ra�z cuadrada", raiz.darNodoDerecho( ) instanceof Numero);
            
        }
        catch( Exception e )
        {
           fail("Error al construir una subexpresi�n");
        }
    }
    
    /**
     * Verifica que una expresi�n aritmetica se exprese correctamente en notaci�n polaca
     */
    public void testConvertirEnNotacionPolaca()
    {
        setupEscenario1( );
        String expresion="25.10*("+Sqrt.SIMBOLO+"[100]+(300-295)"+")";
        String expresionEnNotacionPolaca="* 25.10 + Sqrt 100 - 300 295";
        try
        {
            String resultadoTemp=cupiMath.convertirEnNotacionPolaca( expresion );
            assertEquals( "La notaci�n polaca no est� bien formada", expresionEnNotacionPolaca.trim( ), resultadoTemp.trim( ) );
        }
        catch( Exception e )
        {
        	e.printStackTrace();
            fail("Error al construir la expresi�n aritm�tica");
        }  
    }
    
    
    /**
     * Valida que el resultado de evaluar una expresi�n aritm�tica sea el esperado
     */
    public void testEvaluar()
    {
        setupEscenario1( );
        String expresion="25.10*("+Sqrt.SIMBOLO+"[100]+(300-295)"+")";
        double resultado;
        try
        {
            cupiMath.construirArbol( expresion );
            resultado = cupiMath.evaluarExpresion( expresion );
            assertEquals( "El resultado de la evaluaci�n es incorrecto",376.5, resultado );
        }
        catch( Exception e )
        {
            fail("Error al evaluar la expresi�n aritm�tica");
        } 
    }
    
    /**
     * Verifica que los nodos del �rbol est�n en la posici�n correcta y tengan los valores <br>
     * y tipos esperados
     */
    public void testConstruirArbolDeExpresionAritmerica()
    {
        setupEscenario1( );
        String expresion="25.10*("+Sqrt.SIMBOLO+"[100]+(300-295)"+")";
        try
        {
            cupiMath.construirArbol( expresion );
            Nodo raiz=cupiMath.darRaiz( );
            
            assertTrue("La ra�z no es la esperada", raiz instanceof Multiplicacion );
            //Revisa los nodos del primer nivel del �rbol
            assertTrue("Tipo del hijo izquierdo de la ra�z incorrecto", raiz.darNodoIzquierdo( ) instanceof Numero );
            assertEquals( "Valor del hijo izquierdo de la ra�z incorrecto", "25.10",raiz.darNodoIzquierdo( ).darValor( ) );
            assertTrue("Tipo del hijo derecho de la ra�z incorrecto", raiz.darNodoDerecho( ) instanceof Suma );
            //Revisa los nodos del segundo nivel del �rbol
            Nodo hijoDerechoRaiz=raiz.darNodoDerecho( );
            assertTrue("Tipo del hijo izquierdo del hijo izquierdo de la ra�z es incorrecto", hijoDerechoRaiz.darNodoIzquierdo( ) instanceof Sqrt );
            assertTrue("Tipo del hijo derecho del hijo izquierdo de la ra�z es incorrecto", hijoDerechoRaiz.darNodoDerecho( ) instanceof Resta );
            //Revisa los nodos del tercer nivel del �rbol
            Nodo nodoRaizCuadrada= hijoDerechoRaiz.darNodoIzquierdo( );
            assertTrue("Tipo del hijo izquierdo de la ra�z cuadrada no es el esperado", nodoRaizCuadrada.darNodoIzquierdo( ) instanceof Numero && nodoRaizCuadrada.darNodoIzquierdo( ).darValor( ).equals( "100" ));
            Nodo nodoResta= hijoDerechoRaiz.darNodoDerecho( );
            assertTrue(nodoResta.darNodoIzquierdo( ) instanceof Numero && nodoResta.darNodoIzquierdo( ).darValor( ).equals( "300" ));
            assertTrue(nodoResta.darNodoDerecho( ) instanceof Numero && nodoResta.darNodoDerecho( ).darValor( ).equals( "295" ));
            
            
            
        }
        catch( Exception e )
        {
            fail("Error al construir el �rbol de la expresi�n aritm�tica");
        }
                
    }
    

}