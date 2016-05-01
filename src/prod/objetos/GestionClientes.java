package prod.objetos;
import java.util.LinkedList;
import java.util.Iterator;

public class GestionClientes {
	private ListadoClientes listaClientes = new ListadoClientes();
	public GestionClientes(){
		
	}
	//ALTA DE CLIENTES--------------------------------------------------------------------------
	/**
	 * 
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
	public void altaClienteFisico(String nombre,String cuit,String direccion,int cp,String localidad,String provincia,String telefono,TipDocumento tipDNI,int numeroDocumento, EnumCivil estadoCivil,String profesion,String nombreConyuge){
	PersonaFisica newCliente = new PersonaFisica(nombre,cuit,direccion,cp,localidad,provincia,telefono,tipDNI,numeroDocumento,estadoCivil,profesion,nombreConyuge);
	listaClientes.addCliente(newCliente);
	}
	/**
	 * 
	 * @param nombre
	 * @param cuit
	 * @param direccion
	 * @param cp
	 * @param localidad
	 * @param provincia
	 * @param telefono
	 * @param fechaContrato
	 */
	public void altaClienteJuridica(String nombre,String cuit,String direccion,int cp,String localidad,String provincia,String telefono,String fechaContrato){
		PersonaJuridica newCliente = new PersonaJuridica(nombre,cuit,direccion,cp,localidad,provincia,telefono,fechaContrato);
		listaClientes.addCliente(newCliente);
	}
	//BUSQUEDA DE CLIENTES-----------------------------------------------------------------------
	/**
	 * @param cuit
	 */
	public void buscarClienteporCuit(String cuit){
		Cliente cliente = listaClientes.buscarClientePorCuit(cuit);
		if(cliente == null){
			System.out.println("No existe el Cliente con el numero de cuit: "+cuit);
		}else{
			System.out.println(cliente.toString());
		}
	}
	/**
	 * @param nombre
	 */
	public void buscarClienteporNombre(String nombre){
		LinkedList<Cliente> lista = new LinkedList<Cliente>();
		lista = listaClientes.buscarClientePorNombre(nombre);
		if(lista!=null){	
			Iterator<Cliente> it = lista.iterator();
			while(it.hasNext()){
				System.out.println(it.next().toString());
			}
		}else{
			System.out.println("No existe ningun Cliente con el nombre: "+nombre);
		}
	}
	public void buscarClientePorDni(int dni){
		System.out.println(listaClientes.busquedaDeClientePorDni(dni).toString());
	}
	//ACTIVAR Y BAJAR DE CLIENTE---------------------------------------------------------------------------
	/**
	 * @param cuit
	 */
	public void bajaCliente(String cuit){
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente!=null){
			if(newCliente.getActivo()==true){
				newCliente.setActivo(false);
				System.out.println("Se dio de baja el Cliente: "+cuit);
			}else{
				System.out.println("El Cliente ya se encuentra dado de Baja");
			}
		}else{
			System.out.println("No existe el Cliente");
		}
	}
	/**
	 * @param cuit
	 */
	public void activarCliente(String cuit){
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente!=null){	
			if(newCliente.getActivo()==false){
				newCliente.setActivo(true);
				System.out.println("Se activo nuevamente el Cliente: "+cuit);
			}else{
				System.out.println("El Cliente ya se encuentra Activo");
			}
		}else{
			System.out.println("No existe el Cliente");
		}
	}
	//MODIFICACIONES A CLIENTES FISICOS---------------------------------------------------------------
	/**
	 * @param cuit
	 * @param estadoCivil
	 */
	public void cambiarEstadoCivilCliente(String cuit,EnumCivil estadoCivil){
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
	/**
	 * @param cuit
	 * @param profesion
	 */
	public void cambiarProfesionCliente(String cuit,String profesion){
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
	/**
	 * @param cuit
	 * @param telefono
	 */
	public void cambiarTelefonoCliente(String cuit,String telefono){
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			System.err.println("No existe el cliente con el cuit: "+cuit);
		}else {
			newCliente.setTelefono(telefono);
			System.out.println("Se Cambio exitosamente al Cliente: "+cuit+" el numero  de Telefono: "+telefono);
		}
	}
	/**
	 * @param cuit
	 * @param direccion
	 */
	public void cambiarDireccionCliente(String cuit, String direccion){
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			System.err.println("No existe el cliente con el cuit: "+cuit);
		}else {
			newCliente.setDireccion(direccion);
			System.out.println("Se Cambio exitosamente al Cliente: "+cuit+" la Direccion: "+direccion);
		}
	}
	/**
	 * @param cuit
	 * @param codigoPostal
	 */
	public void cambiarCodigoPostal(String cuit, int codigoPostal){
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			System.err.println("No existe el cliente con el cuit: "+cuit);
		}else if( newCliente instanceof PersonaFisica ){
			PersonaFisica ClienteFisico = (PersonaFisica) newCliente;
			ClienteFisico.setCp(codigoPostal);
			System.out.println("Se cambio exitosamente al Cliente: "+cuit+" el Codigo Postal "+codigoPostal);
		}else{
			System.out.println("El Cliente: "+cuit+" o es un Cliente Fisico");
		}
	}
	/**
	 * @param cuit
	 * @param localidad
	 */
	public void cambiarLocalidadCliente(String cuit, String localidad){
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			System.err.println("No existe el cliente con el cuit: "+cuit);
		}else {
			newCliente.setLocalidad(localidad);
			System.out.println("Se Cambio exitosamente al Cliente: "+cuit+" la localidad: "+localidad);
		}
	}
	/**
	 * @param cuit
	 * @param provincia
	 */
	public void cambiarProvinciaCliente(String cuit, String provincia){
		Cliente newCliente = listaClientes.obtenerCliente(cuit);
		if(newCliente==null){
			System.err.println("No existe el cliente con el cuit: "+cuit);
		}else {
			newCliente.setLocalidad(provincia);
			System.out.println("Se Cambio exitosamente al Cliente: "+cuit+" la Provincia: "+provincia);
		}
	}
	//DEVOLVER UUN CLIENTE----------------------------------------------------------------------------
	/**
	 * @param cuit 
	 */
	public Cliente getCliente(String cuit){
		return listaClientes.obtenerCliente(cuit);
	}	
}
