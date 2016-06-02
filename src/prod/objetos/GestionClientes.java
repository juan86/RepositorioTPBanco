package prod.objetos;

import java.util.Iterator;
import java.util.LinkedList;

import prod.excepciones.ExcepcionAltaCliente;
import prod.excepciones.ExcepcionCliente;
import prod.excepciones.ExcepcionClienteInexistente;

public class GestionClientes {
	private ListadoClientes listaClientes = new ListadoClientes();
	private static GestionClientes instanciaGestionClientes;
	
	private GestionClientes(){
	}
	
	public static GestionClientes getInstance(){
		GestionClientes instanciaCreada = null;
		if(instanciaGestionClientes == null){
			instanciaCreada = new GestionClientes();
		}
		return instanciaCreada;
	}
	//ALTA DE CLIENTES--------------------------------------------------------------------------

	public void altaClienteFisico(String nombre,String cuit,String direccion,String cp,String localidad,String provincia,String telefono,TipDocumento tipDNI,String numeroDocumento, EnumCivil estadoCivil,String profesion,String nombreConyuge) throws ExcepcionCliente{
		PersonaFisica newCliente = new PersonaFisica(nombre,cuit,direccion,cp,localidad,provincia,telefono,tipDNI,numeroDocumento,estadoCivil,profesion,nombreConyuge);
		listaClientes.addCliente(newCliente);
	}
	
	
	public void altaClienteJuridico(String nombre,String cuit,String direccion,String cp,String localidad,String provincia,String telefono,String fechaContrato) throws ExcepcionCliente{
		PersonaJuridica newCliente = new PersonaJuridica(nombre,cuit,direccion,cp,localidad,provincia,telefono,fechaContrato);
		listaClientes.addCliente(newCliente);
	}
	
	
	//BUSQUEDA DE CLIENTES-----------------------------------------------------------------------

	public void buscarClientePorCuit(String cuit) throws ExcepcionClienteInexistente{
		Cliente cliente = listaClientes.buscarClientePorCuit(cuit);
		if(cliente == null){
			throw new ExcepcionClienteInexistente(cuit);
		}else{
			System.out.println(cliente.toString());
		}
	}

	
	public void buscarClientePorNombre(String nombre) throws ExcepcionClienteInexistente{
		LinkedList<Cliente> lista = new LinkedList<Cliente>();
		lista = listaClientes.buscarClientePorNombre(nombre);
		if(lista!=null){	
			Iterator<Cliente> it = lista.iterator();
			while(it.hasNext()){
				System.out.println(it.next().toString());
			}
		}else{
			throw new ExcepcionClienteInexistente(nombre);
		}
	}
	public void buscarClientePorDni(String dni) throws ExcepcionClienteInexistente{
		Cliente cliente = listaClientes.busquedaDeClientePorDni(dni);
		if(cliente == null){
			throw new ExcepcionClienteInexistente(dni);
		}else{
			System.out.println(cliente.toString());
		}
		
	}
	//ACTIVAR Y BAJAR DE CLIENTE---------------------------------------------------------------------------

	public void bajaCliente(String cuit) throws ExcepcionClienteInexistente{
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente!=null){
			if(newCliente.getActivo()==true){
				newCliente.setActivo(false);
				System.out.println("Se dio de baja el Cliente: "+cuit);
			}else{
				System.out.println("El Cliente ya se encuentra dado de Baja");
			}
		}else{
			throw new ExcepcionClienteInexistente(cuit);
		}
	}

	
	public void activarCliente(String cuit) throws ExcepcionClienteInexistente{
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente!=null){	
			if(newCliente.getActivo()==false){
				newCliente.setActivo(true);
				System.out.println("Se activo nuevamente el Cliente: "+cuit);
			}else{
				System.out.println("El Cliente ya se encuentra Activo");
			}
		}else{
			throw new ExcepcionClienteInexistente(cuit);
		}
	}
	//MODIFICACIONES A CLIENTES FISICOS---------------------------------------------------------------

	
	public void cambiarEstadoCivilCliente(String cuit,EnumCivil estadoCivil) throws ExcepcionClienteInexistente{
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			System.err.println("No existe el cliente con el cuit: "+cuit);
		}else if( newCliente instanceof PersonaFisica ){
			PersonaFisica ClienteFisico = (PersonaFisica) newCliente;
			ClienteFisico.setEstadoCivil(estadoCivil);
			System.out.println("Se modifico exitosamente el Cliente: "+cuit+" a su nuevo Estado Civil: "+estadoCivil);
		}else{
			System.err.println("El Cliente: "+cuit+" no es un Cliente Fisico");
		}
	}

	
	public void cambiarProfesionCliente(String cuit,String profesion) throws ExcepcionClienteInexistente{
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			System.err.println("No existe el cliente con el cuit: "+cuit);
		}else if( newCliente instanceof PersonaFisica ){
			PersonaFisica ClienteFisico = (PersonaFisica) newCliente;
			ClienteFisico.setProfesion(profesion);
			System.out.println("Se cambio exitosamente al Cliente: "+cuit+"a la Profesion: "+profesion);
		}else{
			System.out.println("El Cliente: "+cuit+" o es un Cliente Fisico");
		}
	}

	
	public void cambiarTelefonoCliente(String cuit,String telefono) throws ExcepcionClienteInexistente, ExcepcionAltaCliente{
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			throw new ExcepcionClienteInexistente(cuit);
		}else {
			newCliente.setTelefono(telefono);
			System.out.println("Se Cambio exitosamente al Cliente: "+cuit+" el numero  de Telefono: "+telefono);
		}
	}

	
	public void cambiarDireccionCliente(String cuit, String direccion) throws ExcepcionClienteInexistente{
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			throw new ExcepcionClienteInexistente(cuit);
		}else {
			newCliente.setDireccion(direccion);
			System.out.println("Se Cambio exitosamente al Cliente: "+cuit+" la Direccion: "+direccion);
		}
	}

	
	public void cambiarCodigoPostal(String cuit,String codigoPostal) throws ExcepcionAltaCliente, ExcepcionClienteInexistente{
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			throw new ExcepcionClienteInexistente(cuit);
		}else if( newCliente instanceof PersonaFisica ){
			PersonaFisica ClienteFisico = (PersonaFisica) newCliente;
			ClienteFisico.setCp(codigoPostal);
			System.out.println("Se cambio exitosamente al Cliente: "+cuit+" el Codigo Postal "+codigoPostal);
		}else{
			System.out.println("El Cliente: "+cuit+" o es un Cliente Fisico");
		}
	}

	
	public void cambiarLocalidadCliente(String cuit, String localidad) throws ExcepcionClienteInexistente{
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			throw new ExcepcionClienteInexistente(cuit);
		}else {
			newCliente.setLocalidad(localidad);
			System.out.println("Se Cambio exitosamente al Cliente: "+cuit+" la localidad: "+localidad);
		}
	}

	
	public void cambiarProvinciaCliente(String cuit, String provincia) throws ExcepcionClienteInexistente{
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			throw new ExcepcionClienteInexistente(cuit);
		}else {
			newCliente.setLocalidad(provincia);
			System.out.println("Se Cambio exitosamente al Cliente: "+cuit+" la Provincia: "+provincia);
		}
	}
	//DEVOLVER UUN CLIENTE----------------------------------------------------------------------------

	public Cliente getCliente(String cuit) throws ExcepcionClienteInexistente{
		return listaClientes.obtenerCliente(cuit);
	}	
}
