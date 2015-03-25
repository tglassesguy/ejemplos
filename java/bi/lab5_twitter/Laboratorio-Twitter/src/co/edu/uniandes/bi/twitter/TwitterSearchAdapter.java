/**
 * Clases para el acceso a datos
 */
package co.edu.uniandes.bi.twitter;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;
import twitter4j.Status;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import co.edu.uniandes.bi.exception.DataAccessException;
import co.edu.uniandes.bi.util.Commons;

/**
 * Ofrece los servicios de Twitter Search API
 	@author Sebastian
 *
 */
public class TwitterSearchAdapter {
	
	//--------------------------------------------------------------------------------------------------
	// Miembros de la clase
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Instancia compartida del adaptador 
	 */
	private static TwitterSearchAdapter instance;
	
	//--------------------------------------------------------------------------------------------------
	// Atributos
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Conexi�n a los servicios de Twitter
	 */
	private Twitter twitterService;
	
	/**
	 * Log de la instancia
	 */
	private Logger log;
	
	//--------------------------------------------------------------------------------------------------
	// Constructores
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Crea la conexi�n con el servicio de Twitter
	 */
	private TwitterSearchAdapter( ) {
		log = Logger.getLogger(this.getClass().getCanonicalName());
		// La f�brica toma la configuraci�n del archivo twitter4j.properties
		twitterService = new TwitterFactory().getInstance();
	}
	
	//--------------------------------------------------------------------------------------------------
	// M�todos privados
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Verifica el estado de la cuota asignada a la aplicaci�n 
	 * para hacer consultas sobre Twitter
	 * @throws DataAccessException Si se ha alcanzado el l�mite de llamadas al API
	 * @throws TwitterException si no es posible conectarse con Twitter
	 * 
	 */
	private void verifyLimit() throws DataAccessException, TwitterException {
//		RateLimitStatus status = twitterService.getRateLimitStatus();
//		if (status.getRemainingHits() <= 0) {
//			throw new DataAccessException("Se ha alcanzado la cuota de uso del servicio Twitter. Intente nuevamente en: "+status.getResetTime());
//		}
	}
	//--------------------------------------------------------------------------------------------------
	// M�todos p�blicos
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Busca un conjunto de tweets que cumplen las restricciones dadas
	   @param queryString cadena de b�squeda  
	   @see <a href="http://dev.twitter.com/doc/get/search">GET search</a>
	   @see <a href="http://search.twitter.com/operators">Twitter API / Search Operators</a>
	 * <b>pre: </b> el par�metro queryString no se ha codificado como URL   
	   @throws TwitterException 
	   @throws dataAccessException si ocurre un problema al realizar la consult
	   @return tweets
	 */
	public Collection<Status> search(String queryString) throws DataAccessException {
		Query query = null;
		Collection<Status> tweets = null;
		
		query = new Query(queryString);
		
		// 100 resultados por p�gina
		//query.setRpp(100);
		
		// en ingl�s
		query.setLang(Commons.ENGLISH_LANG);
		
		try {
			// Verifica el l�mite de consultas, si no hay excepci�n contin�a
			verifyLimit();
			// Realiza la consulta
			QueryResult result = twitterService.search(query);
			
			tweets = result.getTweets();
		} catch (TwitterException e) {
			log.severe("Error en searchPopularInEnglish(). Mensaje: " +e.getMessage());
			e.printStackTrace();
			throw new DataAccessException("Error de conexi�n con Twitter.C�digo del error:"+e.getExceptionCode());
		}
		
		return tweets;
	}
	
	/**
	 * Busca los tweets que contienen una o m�s palabras claves del conjunto dado
	   @param keywords conjunto de palabras que se van a buscar
	   @param page n�mero de la p�gina de resultados que se desea ver (m�ximo te�rico = 15)
	   @see <a href="http://dev.twitter.com/doc/get/search">GET search</a>
	   @see <a href="http://search.twitter.com/operators">Twitter API / Search Operators</a>
	   @return tweets
	 */
	public Collection<Status> searchTweetsWithKeywords(Set<String> keywords, int page) throws DataAccessException {
		Query query = null;
		Collection<Status> tweets = null;
		StringBuffer queryStr = new StringBuffer(keywords.size());
		boolean first = true;
		
		for (String keyword : keywords) {
			// e.g. query = "love+OR+hate";
			queryStr.append(first ? keyword : "+OR+"+keyword);
			first = false;
		}
		
		query = new Query(queryStr.toString());
		// 100 resultados por p�gina
	//	query.setRpp(100);
		// Aprox. hasta 15 si rpp = 100
		//query.setPage(page);
		
		try {
			// Verifica el l�mite de consultas, si no hay excepci�n contin�a
			verifyLimit();
			// Realiza la consulta
			QueryResult result = twitterService.search(query);
			
			tweets = result.getTweets();
		} catch (TwitterException e) {
			log.severe("Error en searchTweetsWithKeywords(). Mensaje: " +e.getMessage());
			e.printStackTrace();
			throw new DataAccessException("Error de conexi�n con Twitter. C�digo del error:"+e.getExceptionCode());
		}
		
		return tweets;
		
	}
		
	/**
	 * Busca un conjunto de tweets populares escritos en ingl�s que cumplen con las restricciones dadas
	   @param queryString cadena de b�squeda  
	   @see <a href="http://dev.twitter.com/doc/get/search">GET search</a>
	   @see <a href="http://search.twitter.com/operators">Twitter API / Search Operators</a>
	 * <b>pre: </b> el par�metro queryString no se ha codificado como URL   
	   @throws TwitterException 
	   @throws dataAccessException si ocurre un problema al realizar la consult
	   @return tweets
	 */
	public Collection<Status> searchPopularInEnglish(String queryString) throws DataAccessException {
		Query query = null;
		Collection<Status> tweets = null;
		
		query = new Query(queryString);
		// Busca tweets en ingl�s (al filtrar por idioma los resultados se restringen a los �ltimos 7 d�as)
		query.setLang(Commons.ENGLISH_LANG);
		// Filtra por tweets populares
		//query.setResultType(Query.POPULAR);
		// 100 resultados por p�gina
		//query.setRpp(100);
		
		try {
			// Verifica el l�mite de consultas, si no hay excepci�n contin�a
			verifyLimit();
			// Realiza la consulta
			QueryResult result = twitterService.search(query);
			
			tweets = result.getTweets();
		} catch (TwitterException e) {
			log.severe("Error en searchPopularInEnglish(). Mensaje: " +e.getMessage());
			e.printStackTrace();
			throw new DataAccessException("Error de conexi�n con Twitter.C�digo del error:"+e.getExceptionCode());
		}
		
		return tweets;
	}
	
	/**
	 * Obtiene los �ltimos 20 tweets para el usuario dado
	 * @param screenName
	 * @return statuses
	 * @throws DataAccessException
	 */
	public List<Status> getTweetsFrom(String screenName) throws DataAccessException {
		ResponseList<Status> response = null;
		try {
			response = twitterService.getUserTimeline(screenName);
		} catch (TwitterException e) {
			log.severe("Error al obtener los tweets de "+screenName+". Mensaje: " +e.getMessage());
			e.printStackTrace();
			throw new DataAccessException("Error de conexi�n con Twitter.C�digo del error:"+e.getExceptionCode());
		}
		return response;
	}
	
	//--------------------------------------------------------------------------------------------------
	// M�todos est�ticos
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Obtiene una instancia del adaptador de los servicios provistos por Twitter Search
	 * @return instance
	 */
	public static TwitterSearchAdapter getInstance() {
		if (instance == null) {
			instance = new TwitterSearchAdapter();
		}
		return instance;
	}
	
	

}
