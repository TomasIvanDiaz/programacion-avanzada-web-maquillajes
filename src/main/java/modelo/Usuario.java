package modelo;

public class Usuario {
	private int id;
	private String usuario;
	private String password;
	private boolean esAdmin;
	private String nombre;
	private String apellido;
	private String domicilio;
	private String telefono;
	private String codigoPostal;
	
	public Usuario() {} // Crea el objeto y permite que al crearlo no tengamos que darle todos los atributos directamente
	
	public Usuario (int id, String usuario, String password, boolean esAdmin, String nombre, String apellido, String domicilio, String telefono, String codigoPostal) {
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.esAdmin = esAdmin;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.codigoPostal = codigoPostal;
		
	}
	// Hacemos getters y setters de los atributos porque son variables privadas, asi que accedemos a los valores por medio de los metodos
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) { // Es void porque no devuelve nada
		this.id = id;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEsAdmin() { //Usamos el 'is' que para un booleano es como el get
		return esAdmin;
	}
	
	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	
	
	
}