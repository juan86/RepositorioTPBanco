package prod.objetos;
import java.util.ArrayList;

public class Banco {
	
	private ArrayList<CuentaAhorro> listadoCuentaAhorro = new ArrayList<CuentaAhorro>();
	private ArrayList<CuentaCorriente> listadoCuentaCorriente = new ArrayList<CuentaCorriente>();
	
	private GestionClientes gestionClientes = new GestionClientes();
	private GestionDeCuentas adminCuentas = new GestionDeCuentas();
	
	private CuentaEspecial mantenimientos;
	private CuentaEspecial retenciones;
	
	private Batch adminMantenimientos;
	
	private double cotizacionDolar;
	
	public Banco(){
		
		this.mantenimientos = new CuentaEspecial("Especial1", Moneda.pesos);
		this.retenciones = new CuentaEspecial("Especial2",Moneda.pesos);
		this.cotizacionDolar = 14.5;
		this.adminMantenimientos = new Batch(listadoCuentaAhorro, mantenimientos, cotizacionDolar);
		System.out.println("");
	}
	/**
	 * @param nombre
	 * @param cuit
	 * @param direccion
	 * @param cp
	 * @param localidad
	 * @param provincia
	 * @param telefono
	 * @param tipDNI
	 * @param numeroDocumento
	 * @param estadoCivil
	 * @param profesion
	 * @param nombreConyuge
	 */
	public void altaClienteFisico(String nombre,String cuit,String direccion,int cp,String localidad,String provincia,int telefono,TipDocumento tipDNI,int numeroDocumento, EnumCivil estadoCivil,String profesion,String nombreConyuge){
		this.gestionClientes.altaClienteFisico(nombre, cuit, direccion, cp, localidad, provincia, telefono, tipDNI, numeroDocumento, estadoCivil, profesion, nombreConyuge);
	}
	/**
	 * @param nombre
	 * @param cuit
	 * @param direccion
	 * @param cp
	 * @param localidad
	 * @param provincia
	 * @param telefono
	 * @param fechaContrato
	 */
	public void altaClienteJuridico(String nombre,String cuit,String direccion,int cp,String localidad,String provincia,int telefono,String fechaContrato){
		gestionClientes.altaClienteJuridica(nombre, cuit, direccion, cp, localidad, provincia, telefono, fechaContrato);
	}
	/**
	 * @param cuit
	 */
	public void buscarClientePorCuit(String cuit){
		System.out.println("------------------Busqueda por Cuit: "+cuit+"----------------------");
		gestionClientes.buscarClienteporCuit(cuit);
		System.out.println("----------------------------FIN----------------------------------");
	}
	/**
	 * @param nombre
	 */
	public void buscarClientesPorNombre(String nombre){
		System.out.println("----------------Busqueda por Nombre: "+nombre+"--------------------");
		gestionClientes.buscarClienteporNombre(nombre);
		System.out.println("----------------------------FIN---------------------------------");
	}
	/**
	 * @param dni
	 */
	public void buscarClientesPorDni(int dni){
		System.out.println("------------------Busqueda por DNI: "+dni+"----------------------");
		gestionClientes.buscarClientePorDni(dni);
		System.out.println("----------------------------FIN---------------------------------");
	}
	//MODIFICACIONES DE CLIENTES
	/**
	 * @param cuit
	 */
	public void bajaCliente(String cuit){
		gestionClientes.bajaCliente(cuit);
	}
	/**
	 * @param cuit
	 */
	public void activarCliente(String cuit){
			gestionClientes.activarCliente(cuit);
	}
	/**
	 * @param cuit
	 * @param direccion
	 */
	public void cambiarDireccionCliente(String cuit,String direccion ){
		gestionClientes.cambiarDireccionCliente(cuit, direccion);
	}
	/**
	 * @param cuit
	 * @param cp
	 */
	public void cambiarCodigoPostalCliente(String cuit,int cp){
		gestionClientes.cambiarCodigoPostal(cuit, cp);
	}
	/**
	 * @param cuit
	 * @param localidad
	 */
	public void cambiarLocalidadCliente(String cuit, String localidad){
		gestionClientes.cambiarLocalidadCliente(cuit, localidad);
	}
	/**
	 * @param cuit
	 * @param provincia
	 */
	public void cambiarProvinciaCliente(String cuit, String provincia){
		gestionClientes.cambiarProvinciaCliente(cuit, provincia);
	}
	/**
	 * @param cuit
	 * @param estadoCivil
	 */
	public void cambiarEstadoCivilCliente(String cuit, EnumCivil estadoCivil){
		gestionClientes.cambiarEstadoCivilCliente(cuit, estadoCivil);
	}
	/**
	 * @param cuit
	 * @param profesion
	 */
	public void cambiarProfesionCliente(String cuit, String profesion){
		gestionClientes.cambiarProfesionCliente(cuit, profesion);
	}
	/**
	 * @param cuit
	 * @param telefono
	 */
	public void cambiarTelefonoCliente(String cuit,int telefono){
		gestionClientes.cambiarTelefonoCliente(cuit, telefono);
	}
	//******************************************* CUENTAS ******************************************************
	/**
	 * @param cuit
	 * @param telefono
	 */
	public double getCotizacionDolar(){
		return this.cotizacionDolar;
	}
	public void aperturaDeCuenta(){
		GestionDeCuentas.aperturaDeCuentas();
		listadoCuentaAhorro.add(arg0);
	}
}
