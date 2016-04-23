import java.util.LinkedList;
import java.util.Iterator;

abstract class Cliente {
	private String nombre;
	private String cuit;
	private String[] domicilio = new String[4];
	private String telefono;
	private LinkedList<Integer> cuentas = new LinkedList<Integer>();
	private boolean activo;
	public Cliente(String nombre,String cuit,String direccion,String cp,String localidad,String provincia,String telefono){
		this.nombre = nombre;
		this.cuit = cuit;
		domicilio[0] = direccion;
		domicilio[1] = cp;
		domicilio[2] = localidad;
		domicilio[3] = provincia;
		this.telefono = telefono;
		this.activo = true;
	}
	//GET--------------------------------
	public String getNombre(){
		return this.nombre;
	}
	public String getCuit(){
		return this.cuit;
	}
	public String getDomicilio(){
		return this.domicilio[0]+","+this.domicilio[1]+","+this.domicilio[2]+","+this.domicilio[3];
	}
	public String getTelefono(){
		return this.telefono;
	}
	public Integer getCuentas(){
		Iterator<Integer> it = cuentas.iterator();
		Integer cuenta = null;
		while(it.hasNext()){
			cuenta = it.next();
		}
		return cuenta;
	}
	public boolean getActivo(){
		return this.activo;
	}
	//SET---------------------------------
	public void setDireccion(String direccion){
		this.domicilio[0] = direccion;
	}
	public void setCp(String cp){
		this.domicilio[1] = cp;
	}
	public void setLocalidad(String localidad){
		this.domicilio[2] = localidad;
	}
	public void setProvincia(String provincia){
		this.domicilio[3] = provincia;
	}
	public void setTelefono(String telefono){
		this.telefono = telefono;
	}
	public void setCuenta(int cuenta){
		cuentas.add(cuenta);
	}
	public void setActivo(boolean activo){
		this.activo = activo;
	}
}
