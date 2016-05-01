package prod.objetos;
import java.util.Iterator;
import java.util.LinkedList;

abstract class Cliente {
	private String nombre;
	private String cuit;
	private String direccion;
	private int codigoPostal;
	private String localidad;
	private String provincia;
	private String telefono;
	private LinkedList<String> cuentas = new LinkedList<String>();
	private boolean activo;
	
	
	public Cliente(String nombre,String cuit,String direccion,int cp,String localidad,String provincia, String telefono){
		this.nombre = nombre;
		this.cuit = cuit;
		this.direccion = direccion;
		this.codigoPostal = cp;
		this.localidad = localidad;
		this.provincia = provincia;
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
		return this.direccion+","+this.codigoPostal+","+this.localidad+","+this.provincia;
	}
	public String getTelefono(){
		return this.telefono;
	}
	public LinkedList<String> getCuentas(){
		return cuentas;
	}
	public boolean getActivo(){
		return this.activo;
	}
	public boolean contieneCBU(String cbu){
		Iterator<String> it = cuentas.iterator();
		while(it.hasNext()){
			if(it.next()== cbu){
				return true;
			}
		}
		return false;
	}
	public String getCuentasToString(){
		Iterator<String> it = cuentas.iterator();
		String cuentas = "No tiene Cuentas";
		if(it.hasNext()){
			while(it.hasNext()){
				cuentas += it.next()+",";
			}
		}
		return cuentas;
	}
	
	//SET---------------------------------
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	public void setCp(int cp){
		this.codigoPostal = cp;
	}
	public void setLocalidad(String localidad){
		this.localidad = localidad;
	}
	public void setProvincia(String provincia){
		this.provincia = provincia;
	}
	public void setTelefono(String telefono){
		this.telefono = telefono;
	}
	public void setCuenta(String cuenta){
		cuentas.add(cuenta);
	}
	public void setActivo(boolean activo){
		this.activo = activo;
	}
}
