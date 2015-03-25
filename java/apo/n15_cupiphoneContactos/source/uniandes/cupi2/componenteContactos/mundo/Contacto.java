package uniandes.cupi2.componenteContactos.mundo;

import java.io.Serializable;

/**
 * Clase que modela un contacto
 * @author Yeisson Oviedo
 *
 */
public class Contacto implements Serializable{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	private String nombre;
	
	private String telCasa;

	private String telTrabajo;

	private String telPersonal;

	private String telMovil;

	private String correo;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------	
	
	/**
	 * Constructor
	 */
	public Contacto(){
		
	}
	
	/**
	 * Constructor
	 * @param nombre Nombre del contacto 
	 * @param telCasa Tel�fono de la casa
	 * @param telTrabajo Tel�fono del trabajo
	 * @param telPersonal Tel�fono personal
	 * @param telMovil Tel�fono m�vil
	 * @param correo Correo electr�nico
	 */
	public Contacto(String nombre, String telCasa, String telTrabajo,
			String telPersonal, String telMovil, String correo) {
		this.nombre = nombre;
		this.telCasa = telCasa;
		this.telTrabajo = telTrabajo;
		this.telPersonal = telPersonal;
		this.telMovil = telMovil;
		this.correo = correo;
	}

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

	/**
	 * Retorna el nombre del contacto 
	 * @return Nombre del contacto
	 */
	public String darNombre() {
		return nombre;
	}

	/**
	 * Permite cambiar el nombre del contacto
	 * @param nombre Nombre a cambiar
	 */
	public void cambiarNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna el tel�fono de la casa del contacto 
	 * @return Tel�fono de la casa del contacto
	 */
	public String darTelefonoCasa() {
		return telCasa;
	}

	/**
	 * Permite cambiar el tel�fono de la casa del contacto
	 * @param telCasa Nuevo n�mero de tel�fono
	 */
	public void cambiarTelefonoCasa(String telCasa) {
		this.telCasa = telCasa;
	}

	/**
	 * Retorna el tel�fono del trabajo del contacto 
	 * @return Tel�fono del trabajo del contacto
	 */
	public String darTelefonoTrabajo() {
		return telTrabajo;
	}

	/**
	 * Permite cambiar el tel�fono del trabajo del contacto
	 * @param telTrabajo Nuevo n�mero de tel�fono
	 */
	public void cambiarTelefonoTrabajo(String telTrabajo) {
		this.telTrabajo = telTrabajo;
	}

	/**
	 * Retorna el tel�fono personal del contacto 
	 * @return Tel�fono personal del contacto
	 */
	public String darTelefonoPersonal() {
		return telPersonal;
	}

	/**
	 * Permite cambiar el tel�fono personal del contacto
	 * @param telPersonal Nuevo n�mero de tel�fono
	 */
	public void cambiarTelefonoPersonal(String telPersonal) {
		this.telPersonal = telPersonal;
	}

	/**
	 * Retorna el tel�fono m�vil del contacto 
	 * @return Tel�fono de m�vil del contacto
	 */
	public String darTelefonoMovil() {
		return telMovil;
	}

	/**
	 * Permite cambiar el tel�fono m�vil del contacto
	 * @param telMovil Nuevo n�mero de tel�fono
	 */
	public void cambiarTelefonoMovil(String telMovil) {
		this.telMovil = telMovil;
	}

	/**
	 * Retorna el correo del contacto
	 * @return Correo del contacto
	 */
	public String darCorreo() {
		return correo;
	}

	/**
	 * permite cambiar el correo del contacto
	 * @param correo Correo del contacto
	 */
	public void cambiarCorreo(String correo) {
		this.correo = correo;
	}
	
    //-----------------------------------------------------------------
    // M�todos Auxiliares
    //-----------------------------------------------------------------

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals (Object o){
		if (o instanceof Contacto && ((Contacto)o).darNombre().equals(nombre))
			return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return nombre + " " + telMovil;
	}
}
