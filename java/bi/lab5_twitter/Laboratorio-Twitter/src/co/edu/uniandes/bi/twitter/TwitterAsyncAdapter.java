/**
 * 
 */
package co.edu.uniandes.bi.twitter;

import java.util.Set;
import java.util.logging.Logger;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.Query;
import twitter4j.TwitterListener;
import co.edu.uniandes.bi.util.Commons;

/**
 * Consume asincr�nicamente los servicios de Twitter
 * @author Sebastian
 *
 */
public class TwitterAsyncAdapter
{

    // --------------------------------------------------------------------------------------------------
    // Miembros de la clase
    // --------------------------------------------------------------------------------------------------

    /**
     * Instancia compartida de la clase
     */
    private static TwitterAsyncAdapter instance;

    // --------------------------------------------------------------------------------------------------
    // Atributos
    // --------------------------------------------------------------------------------------------------

    /**
     * Conexi�n a los servicios de Twitter
     */
    private AsyncTwitter asyncTwitter;

    /**
     * Log de la instancia
     */
    private Logger log;

    // --------------------------------------------------------------------------------------------------
    // Constructor
    // --------------------------------------------------------------------------------------------------

    /**
     * Constructor privado
     */
    private TwitterAsyncAdapter( )
    {
        log = Logger.getLogger( this.getClass( ).getCanonicalName( ) );

        // La f�brica toma la configuraci�n del archivo twitter4j.properties
        asyncTwitter = new AsyncTwitterFactory( ).getInstance( );
    }

    // --------------------------------------------------------------------------------------------------
    // M�todos privados
    // --------------------------------------------------------------------------------------------------

    // --------------------------------------------------------------------------------------------------
    // M�todos p�blicos
    // --------------------------------------------------------------------------------------------------

    /**
     * Registra una instancia que va a escuchar los resultados de los servicios llamados
     * @param listener
     */
    public void addTwitterListener( TwitterListener listener )
    {
        asyncTwitter.addListener( listener );
    }

    /**
     * Busca un conjunto de tweets que cumplen las restricciones dadas
     * @param queryString cadena de b�squeda
     * @see http://twitter4j.org/en/code-examples.html</a>
    * @see Search Operators: https://dev.twitter.com/rest/public/search</a> 
 *   * <b>pre: </b> el par�metro queryString no se ha codificado como URL
     */
    public void search( String queryString, int page )
    {
        Query query = new Query(queryString+" filter:links");
        query.setLang( Commons.ENGLISH_LANG );
        query.setSince("2014-10-17");
        System.err.println(query);
        log.info("TwitterAsyncAdapter.search() :: Preparada la consulta para "+queryString);
        asyncTwitter.search(query);
    }

    /**
     * Busca los tweets que contienen una o m�s palabras claves del conjunto dado
     * @param keywords conjunto de palabras que se van a buscar
     * @param page n�mero de la p�gina de resultados que se desea ver (m�ximo te�rico = 15)
     * @see <a href="http://dev.twitter.com/doc/get/search">GET search</a>
     * @see <a href="http://search.twitter.com/operators">Twitter API / Search Operators</a> <b>pre: </b> Se ha registrado un listener a trav�s del m�todo
     *      TwitterAsyncAdapter#addTwitterListener
     */
    public void searchTweetsWithKeywords( Set<String> keywords, int page )
    {
        Query query = null;
        StringBuffer queryStr = new StringBuffer( keywords.size( ) );
        boolean first = true;

        for( String keyword : keywords )
        {
            // e.g. query = "love+OR+hate";
            queryStr.append( first ? keyword : "+OR+" + keyword );
            first = false;
        }

        query = new Query( queryStr.toString( ) );
        // 100 resultados por p�gina
        // query.setRpp(100);
        // // Aprox. hasta 15 si rpp = 100
        // query.setPage(page);
        log.info( "Se va a enviar la consulta: " + query );
        // Realiza la consulta
        asyncTwitter.search( query );
    }

    /**
     * Busca un conjunto de tweets populares escritos en ingl�s que cumplen con las restricciones dadas
     * @param queryString cadena de b�squeda
     * @see <a href="http://dev.twitter.com/doc/get/search">GET search</a>
     * @see <a href="http://search.twitter.com/operators">Twitter API / Search Operators</a> <b>pre: </b> Se ha registrado un listener a trav�s del m�todo
     *      TwitterAsyncAdapter#addTwitterListener
     */
    public void searchPopularInEnglish( String queryString )
    {
        Query query = null;

        query = new Query( queryString );
        // Busca tweets en ingl�s (al filtrar por idioma los resultados se restringen a los �ltimos 7 d�as)
        query.setLang( Commons.ENGLISH_LANG );
        // Filtra por tweets populares
        query.setResultType( Query.POPULAR );
        // 100 resultados por p�gina
        // query.setRpp(100);

        // Realiza la consulta
        asyncTwitter.search( query );
    }

    /**
     * Obtiene los �ltimos 20 tweets del usuario dado
     * @param screenName
     */
    public void getTweetsFrom( String screenName )
    {
        asyncTwitter.getUserTimeline( screenName );
    }

    // --------------------------------------------------------------------------------------------------
    // M�todos est�ticos
    // --------------------------------------------------------------------------------------------------

    /**
     * Obtiene una instancia compartida de la clase
     */
    public static TwitterAsyncAdapter getInstance( )
    {
        if( instance == null )
        {
            instance = new TwitterAsyncAdapter( );
        }
        return instance;
    }

}
