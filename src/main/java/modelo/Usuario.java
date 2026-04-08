package modelo;

public class Usuario {
	private int id;
	private String usuario;
	private String password;
	private boolean esAdmin;
	
	public Usuario() {} // Crea el objeto y permite que al crearlo no tengamos que darle todos los atributos directamente
	
	public Usuario (int id, String usuario, String password, boolean esAdmin) {
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.esAdmin = esAdmin;
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
