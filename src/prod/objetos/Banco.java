package prod.objetos;
import java.util.ArrayList;
import java.util.StringTokenizer;

import prod.excepciones.ExcepcionAltaCliente;
import prod.excepciones.ExcepcionBatch;
import prod.excepciones.ExcepcionCliente;
import prod.excepciones.ExcepcionClienteInexistente;
import prod.excepciones.ExcepcionCuenta;
import prod.excepciones.ExcepcionExtraccionCCorriente;
import prod.excepciones.ExcepcionIOBatch;
import prod.excepciones.ExcepcionNoEsTitular;
import prod.excepciones.ExcepcionNoExisteCuenta;

public class Banco {
	
	private ArrayList<CuentaAhorro> Ahorro = new ArrayList<CuentaAhorro>();
	private ArrayList<CuentaCorriente> Corriente = new ArrayList<CuentaCorriente>();
	//SE CREA EL NUEVO ARRAYLIST DE CUENTAS
	private ArrayList<CuentaCliente> cuentasCliente = new ArrayList<CuentaCliente>();
	//
	private GestionClientes gestionClientes;
	private GestionDeCuentas gestionCuentas;
	private OperacionesPorVentanilla operaciones;
	private CuentaEspecial mantenimientos;
	private CuentaEspecial retenciones;
	private Batch adminMantenimientos;
	private double cotizacionDolar;
	private static Banco instanciaDeBanco = null;
	
	private Banco(){
		
		//postCondicion: Las cuentas del Banco son PESOS. 
		this.mantenimientos = new CuentaEspecial("Especial1", Moneda.pesos);
		this.retenciones = new CuentaEspecial("Especial2",Moneda.pesos);
		this.cotizacionDolar = 14.5;
		this.adminMantenimientos = Batch.getInstance(mantenimientos, cotizacionDolar);
		this.gestionClientes = GestionClientes.getInstance();
		this.gestionCuentas = GestionDeCuentas.getInstance();
		this.operaciones = OperacionesPorVentanilla.getInstance();
		
		System.out.println("");
	}
	
	public static Banco getInstance(){
		Banco instanciaCreada = null;
		if(instanciaDeBanco == null){
			instanciaCreada = new Banco();
		}
		return instanciaCreada;
	}
	
	//******************************************** BANCO *********************************************************
	
	public double getCotizacionDolar(){
		return this.cotizacionDolar;
	}
	
	public double getSaldoCuentaMantenimiento(){
		return mantenimientos.getSaldo();
	}
	
	//******************************************* CLIENTES *******************************************************
	
	public void altaClienteFisico(String nombre,String cuit,String direccion,String cp,String localidad,String provincia,String telefono,TipDocumento tipDNI,String numeroDocumento, EnumCivil estadoCivil,String profesion,String nombreConyuge){
		try {
			this.gestionClientes.altaClienteFisico(nombre, cuit, direccion, cp, localidad, provincia, telefono, tipDNI, numeroDocumento, estadoCivil, profesion, nombreConyuge);
		} catch (ExcepcionCliente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public void altaClienteJuridico(String nombre,String cuit,String direccion,String cp,String localidad,String provincia,String telefono,String fechaContrato){
		try {
			gestionClientes.altaClienteJuridico(nombre, cuit, direccion, cp, localidad, provincia, telefono, fechaContrato);
		} catch (ExcepcionCliente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	//**************************************** BUSQUEDA DE CLIENTES **********************************************
	
	public void buscarClientePorCuit(String cuit){
		System.out.println("------------------Busqueda por Cuit: "+cuit+"----------------------");
		try {
			gestionClientes.buscarClientePorCuit(cuit);
		} catch (ExcepcionClienteInexistente e) {
			System.out.println(e);
		}
		System.out.println("----------------------------FIN----------------------------------");
	}

	public void buscarClientesPorNombre(String nombre){
		System.out.println("----------------Busqueda por Nombre: "+nombre+"--------------------");
		try {
			gestionClientes.buscarClientePorNombre(nombre);
		} catch (ExcepcionClienteInexistente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		System.out.println("----------------------------FIN---------------------------------");
	}
	
	public void buscarClientesPorDni(String dni){
		System.out.println("------------------Busqueda por DNI: "+dni+"----------------------");
		try {
			gestionClientes.buscarClientePorDni(dni);
		} catch (ExcepcionClienteInexistente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		System.out.println("----------------------------FIN---------------------------------");
	}
	
	//********************************** MODIFICACIONES DE CLIENTES *********************************************
	
	public void bajaCliente(String cuit){
		try {
			gestionClientes.bajaCliente(cuit);
		} catch (ExcepcionClienteInexistente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public void activarCliente(String cuit){
			try {
				gestionClientes.activarCliente(cuit);
			} catch (ExcepcionClienteInexistente e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
	}

	public void cambiarDireccionCliente(String cuit,String direccion ){
		try {
			gestionClientes.cambiarDireccionCliente(cuit, direccion);
		} catch (ExcepcionClienteInexistente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public void cambiarCodigoPostalCliente(String cuit,String cp){
		try {
			gestionClientes.cambiarCodigoPostal(cuit, cp);
		} catch (ExcepcionAltaCliente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (ExcepcionClienteInexistente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public void cambiarLocalidadCliente(String cuit, String localidad){
		try {
			gestionClientes.cambiarLocalidadCliente(cuit, localidad);
		} catch (ExcepcionClienteInexistente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public void cambiarProvinciaCliente(String cuit, String provincia){
		try {
			gestionClientes.cambiarProvinciaCliente(cuit, provincia);
		} catch (ExcepcionClienteInexistente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public void cambiarEstadoCivilCliente(String cuit, EnumCivil estadoCivil){
		try {
			gestionClientes.cambiarEstadoCivilCliente(cuit, estadoCivil);
		} catch (ExcepcionClienteInexistente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public void cambiarProfesionCliente(String cuit, String profesion){
		try {
			gestionClientes.cambiarProfesionCliente(cuit, profesion);
		} catch (ExcepcionClienteInexistente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	public void cambiarTelefonoCliente(String cuit,String telefono){
		try {
			gestionClientes.cambiarTelefonoCliente(cuit, telefono);
		} catch (ExcepcionClienteInexistente e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcepcionAltaCliente e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	//******************************************* CUENTAS ******************************************************
	
	//PRECONDICION: INGRESAR UN LISTADO DE CUITS CON LOS CLIENTES A TITULARIZAR, SEPARADO POR COMAS.
	
	public void aperturaDeCuentaAhorro(String titulares, double montoInicial, Moneda nominacion, double tasaDeInteres){
		
		gestionCuentas.aperturaDeCuentaAhorro(titulares, montoInicial, nominacion, tasaDeInteres);
	}
	
	public void aperturaDeCuentaCorriente(String titulares, double montoInicial, double sobregiro){
		
		gestionCuentas.aperturaDeCuentaCorriente(titulares, montoInicial, sobregiro);
	}
	
	public void habilitarCuenta(String cbu){
		try {
			Cuenta cuentaAHabilitar = this.obtenerCuenta(cbu);
			gestionCuentas.habilitarCuenta(cuentaAHabilitar);
		}catch(ExcepcionNoExisteCuenta e){
			System.out.println(e);
		}catch (ExcepcionCuenta e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void inhabilitarCuenta(String cbu){
		try {
			Cuenta cuentaAInhabilitar = this.obtenerCuenta(cbu);
			gestionCuentas.inhabilitarCuenta(cuentaAInhabilitar);
		}catch(ExcepcionNoExisteCuenta e){
			System.out.println(e);
		}catch (ExcepcionCuenta e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	//*********************************************** OPERACIONES VENTANILLA ***************************************************
	public void depositoEnEfectivo(double monto, Moneda tipoMoneda,String cbu){
		
		try {
			Cuenta cuenta = obtenerCuenta(cbu);
			operaciones.depositoEfectivo(monto, tipoMoneda, cuenta);
		} catch (ExcepcionCuenta e) {
			System.out.println(e);
		}catch(ExcepcionNoExisteCuenta e){
			System.out.println(e);
		}
	}
	
	public void extraccionEfectivo(String cuit,String cbu,double monto,Moneda tipoMoneda){
		
		try {
			Cliente newCliente = this.gestionClientes.getCliente(cuit);
			Cuenta newCuenta = obtenerCuenta(cbu);
			if(!newCliente.contieneCBU(cbu)){
				throw new ExcepcionNoEsTitular(cuit,cbu);
			}
			operaciones.extraccionEfectivo(monto, tipoMoneda, newCuenta);
		}catch(ExcepcionClienteInexistente e){
			System.out.println(e);
		}catch (ExcepcionExtraccionCCorriente e) {
			System.out.println(e);
		}catch(ExcepcionNoEsTitular e){
			System.out.println(e);
		}catch(ExcepcionNoExisteCuenta e){
			System.out.println(e);
		}catch(ExcepcionCuenta e){
			System.out.println(e);
		}
	}
	
	//*************************************************** BATCH ***********************************************************
	
	public void correrBatch() throws ExcepcionIOBatch, ExcepcionBatch {
		if(cuentasCliente.size() == 0){
			System.out.println("----------------------- INICIO DE MANTENIMIENTO ------------------------");
			System.out.println("ATENCION: No es posible realizar el mantenimiento sobre una lista VACIA.");
			System.out.println("--------------------------------- FIN ----------------------------------");
		}else{
			System.out.println("----------------------- INICIO DE MANTENIMIENTO ------------------------");
			adminMantenimientos.cobroDeMantenimientos(cuentasCliente);
			System.out.println("El mantenimiento fue realizado con exito.");
			System.out.println("--------------------------------- FIN ----------------------------------");
		}	
	}
	private Cuenta obtenerCuenta(String cbu) throws ExcepcionNoExisteCuenta{
		Cuenta newCuenta;
		for(int i = 0 ; i < this.cuentasCliente.size();i++){
			newCuenta = this.cuentasCliente.get(i);
			if(newCuenta.getCbu() == cbu){
				return newCuenta;
			}
		}
		throw new ExcepcionNoExisteCuenta(cbu);
	}
}