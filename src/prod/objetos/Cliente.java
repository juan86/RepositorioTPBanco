package prod.objetos;

import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.LinkedList;

import prod.excepciones.ExcepcionAltaCliente;
import prod.excepciones.ExcepcionCPCliente;
import prod.excepciones.ExcepcionCliente;
import prod.excepciones.ExcepcionCuitCliente;
import prod.excepciones.ExcepcionTelefonoCliente;

public abstract class Cliente {
	private String nombre;
	private String cuit;
	private String direccion;
	private String codigoPostal;
	private String localidad;
	private String provincia;
	private String telefono;
	private LinkedList<String> cuentas = new LinkedList<String>();
	private boolean activo;
	
	
	public Cliente(String nombre,String cuit,String direccion,String cp,String localidad,String provincia, String telefono)throws ExcepcionCliente{
			this.nombre = nombre;
			validarNumeroCuit(cuit);
			this.direccion = direccion;
			validarNumeroCp(cp);
			this.localidad = localidad;
			this.provincia = provincia;
			validarTelefono(telefono);
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
	
	//SET---------------------------------
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	public void setCp(String cp) throws ExcepcionAltaCliente{
		validarNumeroCp(cp);
	}
	public void setLocalidad(String localidad){
		this.localidad = localidad;
	}
	public void setProvincia(String provincia){
		this.provincia = provincia;
	}
	public void setTelefono(String telefono) throws ExcepcionAltaCliente{
		try{
			this.telefono = telefono;
		}catch(IllegalFormatException e){
			throw new ExcepcionAltaCliente("No es un numero de Telefono Valido");
		}
	}
	public void setCuenta(String cuenta){
		cuentas.add(cuenta);
	}
	public void setActivo(boolean activo){
		this.activo = activo;
	}
	
	//--------------------METODOS-----------------------
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
	public boolean contieneCBU(String cbu){
		Iterator<String> it = cuentas.iterator();
		while(it.hasNext()){
			if(it.next()== cbu){
				return true;
			}
		}
		return false;
	}
	private void validarNumeroCuit(String cuit) throws ExcepcionAltaCliente{
		if(cuit.length()==11){
			try{
				validarEnteroCuit(cuit);
				this.cuit = cuit;
			}catch(IllegalFormatException e){
				throw new ExcepcionCuitCliente(cuit);
			}catch(ExcepcionCuitCliente ex){
				throw new ExcepcionCuitCliente(cuit);
			}
		}else{
			throw new ExcepcionCuitCliente(cuit);
		}
	}
	private void validarEnteroCuit(String cuit) throws ExcepcionCuitCliente{
		int numero;
		int m = 1;
			try{
				for(int i = 0;i<cuit.length();i++){
					numero = Integer.parseInt(cuit.substring(i, m));
					m++;
				}
			}catch(Exception e){
				throw new ExcepcionCuitCliente(cuit);
			}
	}
	private void validarNumeroCp(String cp)throws ExcepcionAltaCliente{
		int numero;
		try{
			numero = Integer.parseInt(cp);
			this.codigoPostal = cp;
		}catch(IllegalFormatException e){
			throw new ExcepcionCPCliente(cp);
		}catch(NumberFormatException ex){
			throw new ExcepcionCPCliente(cp);
		}catch(Exception ex){
			System.out.println("Error desconocido");
			ex.printStackTrace();
		}
	}
	private void validarTelefono(String telefono)throws ExcepcionAltaCliente{
		if(telefono.length()== 8 || telefono.length()== 10){
			try{
				int tel = Integer.parseInt(telefono);
				this.telefono = telefono;
			}catch(Exception e){
				throw new ExcepcionTelefonoCliente(telefono);
			}
		}else{
			throw new ExcepcionTelefonoCliente(telefono);
		}
	}
}
