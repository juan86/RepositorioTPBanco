package prod.objetos;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import prod.excepciones.ExcepcionClienteInexistente;
import prod.excepciones.ExcepcionClienteYaRegistrado;

public class ListadoClientes {
	private HashMap<String,Cliente> lista = new HashMap<String,Cliente>();
	//HASHMAP AUXILIARES----------------------------------------
	//HashMap<NOMBRE,CUIT>
	private HashMap<String,LinkedList<String>> listaAuxiliar = new HashMap<String,LinkedList<String>>();
	//HashMap<DNI,CUIT>
	private HashMap<String,String> listaAuxiliar2 = new HashMap<String,String>();
	
	public ListadoClientes(){
		
	}
	public void addCliente(Cliente newCliente) throws ExcepcionClienteInexistente, ExcepcionClienteYaRegistrado{
		//SI ES UNA PERSONA FISICA COPIA EL DNI A LA LISTA AUXILIAR CON SU CUIT HASHMAP<DNI,CUIT>
		if(newCliente instanceof PersonaFisica ){
			PersonaFisica cliente = (PersonaFisica) newCliente;
			if(!listaAuxiliar2.containsKey(cliente.getNumeroDocumento())){
				listaAuxiliar2.put(cliente.getNumeroDocumento(),cliente.getCuit());
			}else{
				throw new ExcepcionClienteYaRegistrado(cliente.getNumeroDocumento());
			}
		}
		
		if(lista.containsKey(newCliente.getCuit())){
			throw new ExcepcionClienteYaRegistrado(newCliente.getCuit());
		}else{
			lista.put(newCliente.getCuit(),newCliente);
			//SI EL NOMBRE EXISTE DEL NUEVO CLIENTE SOLAMENTE AGREGA SU CUIT AL HASHMAP<NOMBRE,CUIT>
			if(listaAuxiliar.containsKey(newCliente.getNombre())){
				listaAuxiliar.get(newCliente.getNombre()).add(newCliente.getCuit());
			}else{
				//SINO AGREGA EL NOMBRE Y SU CUIT AL HASHMAP<NOMBRE,CUIT>
				LinkedList<String> listaTemporal = new LinkedList<String>();
				listaTemporal.add(newCliente.getCuit());
				listaAuxiliar.put(newCliente.getNombre(), listaTemporal);
			}
		}
	}
	//BUSCAR CLIENTE POR CUIT--------------------------------------------------
	public Cliente buscarClientePorCuit(String cuit) throws ExcepcionClienteInexistente{
		Cliente cliente = obtenerCliente(cuit);
		return cliente;
	}
	
	//BUSCAR CLIENTE POR NOMBRE--------------------------------------------------
	public LinkedList<Cliente> buscarClientePorNombre(String nombre) throws ExcepcionClienteInexistente{
		LinkedList<Cliente> lista= new LinkedList<Cliente>();
		if(listaAuxiliar.containsKey(nombre)){
			Iterator<String> it = listaAuxiliar.get(nombre).iterator();
			while(it.hasNext()){
				lista.add(buscarClientePorCuit(it.next()));
			}
			return lista;
		}else{
			return lista = null;
		}
	}
	public Cliente busquedaDeClientePorDni(String numeroDni) throws ExcepcionClienteInexistente{
		return buscarClientePorCuit(listaAuxiliar2.get(numeroDni));
	}
	//OBTENER CLIENTE---------------------------------------------------------------
	public Cliente obtenerCliente(String cuit) throws ExcepcionClienteInexistente{
		if(!lista.containsKey(cuit)){
			throw new ExcepcionClienteInexistente(cuit);
		}else{
			return lista.get(cuit);
			}
	}
}
