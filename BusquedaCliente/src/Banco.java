import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;

class Banco {
	static private HashMap<String,Cliente> lista = new HashMap<String,Cliente>();
	//HASHMAP AUXILIARES----------------------------------------
	static private HashMap<String,LinkedList<String>> listaAuxiliar = new HashMap<String,LinkedList<String>>();
	static private HashMap<Integer,String> listaAuxiliar2 = new HashMap<Integer,String>();
	
	public static void main(String[] arg){
		crearClienteFisico("Juan Mercado","20324878379","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487837,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan Merca","20324878369","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487836,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan Merc","20324878359","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487835,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan Mer","20324878349","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487834,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan Me","20324878339","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487833,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan M","20324878329","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487832,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan Mercado","20324878319","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487831,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan Marcad","20324878309","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487830,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan Marca","20324878299","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487829,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan Marc","20324878289","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487828,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan Mar","20324878279","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487827,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan M","20324878269","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487826,"Soltero","vago", "ninguno");
		crearClienteFisico("Juan","20324878259","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487825,"Soltero","vago", "ninguno");
		crearClienteJuridico("Juan Perez","20324878249","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","23-05-1986");
		crearClienteJuridico("Juan Pere","20324878239","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","23-05-1986");
		crearClienteJuridico("Juan Nada","20324878229","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","23-05-1986");
		crearClienteJuridico("Juan Mas","20324878219","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","23-05-1986");
		crearClienteJuridico("Juan Mercado","20324878209","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","23-05-1986");
		crearClienteJuridico("Juan Distinto","20324878199","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","23-05-1986");
		crearClienteJuridico("Juan Igual","20324878189","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","23-05-1986");
		crearClienteJuridico("Juan Marcas","20324878179","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","23-05-1986");
		crearClienteJuridico("Juan Marcs","20324878169","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","23-05-1986");
		crearClienteJuridico("Juan Mard","20324878159","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","23-05-1986");
		crearClienteJuridico("Juan Mes","20324878149","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","23-05-1986");
		
		//buscarClientePorCuit("20324878319");
		buscarClientePorNombre("Juan Mercado");
		busquedaDeClientePorDni(32487820);
		busquedaDeClientePorDni(32487818);
		//buscarClientePorCuit("20324878149");
		//buscarClientePorNombre("Juan Mes");
	}
	
	//SE CREA EL CLIENTE_FISICO---------------------------------------------------
	static public void crearClienteFisico(String nombre,String cuit,String direccion,String cp,String localidad,String provincia,String telefono,String tipDNI,int numeroDocumento, String estadoCivil,String profesion,String nombreConyuge){
		if(lista.containsKey(cuit)){
			System.out.println("La persona ya esta Regristrada");
		}else{
			PersonaFisica cliente1 = new PersonaFisica(nombre,cuit,direccion,cp,localidad,provincia,telefono,tipDNI,numeroDocumento,estadoCivil,profesion,nombreConyuge);
			lista.put(cuit,cliente1);
			listaAuxiliar2.put(numeroDocumento,cuit);
			//SI EL NOMBRE DEL NUEVO CLIENTE EXISTE ENTONCES SOLAMENTE AGREGAR EL DOCUMENTO EN LA LISTA QUE SE ENCUENTRA CON ESE NOMBRE
			//SI NO AGREGAR UN NUEVO <KEY,CONTENEDOR>
			if(listaAuxiliar.containsKey(nombre)&&listaAuxiliar2.containsKey(numeroDocumento)){
				listaAuxiliar.get(nombre).add(cuit);
			}else{
				LinkedList<String> listaTemporal = new LinkedList<String>();
				listaTemporal.add(cuit);
				listaAuxiliar.put(nombre, listaTemporal);
			}
		}
	}
	//SE CREA EL CLIENTE_JURIDICO---------------------------------------------------
	static public void crearClienteJuridico(String nombre,String cuit,String direccion,String cp,String localidad,String provincia,String telefono,String fechaContrato){
		if(lista.containsKey(cuit)){
			System.out.println("La persona ya esta Regristrada");
		}else{
			PersonaJuridica cliente1 = new PersonaJuridica(nombre,cuit,direccion,cp,localidad,provincia,telefono,fechaContrato);
			lista.put(cuit,cliente1);
			//SI EL NOMBRE DEL NUEVO CLIENTE EXISTE ENTONCES SOLAMENTE AGREGAR EL DOCUMENTO EN LA LISTA QUE SE ENCUENTRA CON ESE NOMBRE
			//SI NO AGREGAR UN NUEVO <KEY,CONTENEDOR>
			if(listaAuxiliar.containsKey(nombre)){
				listaAuxiliar.get(nombre).add(cuit);
			}else{
				LinkedList<String> listaTemporal = new LinkedList<String>();
				listaTemporal.add(cuit);
				listaAuxiliar.put(nombre, listaTemporal);
			}
		}
	}
	//OBTENER CLIENTE---------------------------------------------------------------
	static public Cliente obtenerCliente(String cuit){
		if(!lista.containsKey(cuit)){
			return null;
		}else{
			return lista.get(cuit);
		}
	}
	
	//BUSQUEDA DE CLIENTES--------------------------------------------------
	static public void buscarClientePorCuit(String cuit){
		Cliente cliente = obtenerCliente(cuit);		
		String mostrar = null;
		if(cliente == null){
			mostrar = "No existe el Cliente";
		}else if(cliente instanceof PersonaFisica ){
				PersonaFisica newCliente = (PersonaFisica) cliente;
				System.out.println("Resultado de la busqueda:");
				mostrar = "(Nombre Completo): "+newCliente.getNombre()+"\n"+
						"(CUIT): "+cuit+"\n"+
						"(Domicilio): "+newCliente.getDomicilio()+"\n"+
						"(Telefono): "+newCliente.getTelefono()+"\n"+
						"(Tipo DNI/Pasaporte): "+newCliente.getTipDNI()+"\n"+
						"(Numero): "+newCliente.getNumeroDocumento()+"\n"+
						"(Estado Civil): "+newCliente.getEstadoCivil()+"\n"+
						"(Profesion): "+newCliente.getProfesion()+"\n"+
						"(Nom y Ape Cónyuge): "+newCliente.getNombreConyuge()+"\n";	
			  }else{
				  System.out.println("Resultado de la busqueda: ");
				  PersonaJuridica newCliente = (PersonaJuridica) cliente;
				  mostrar = "(Nombre Completo): "+newCliente.getNombre()+"\n"+
						  "(CUIT): "+cuit+"\n"+
						  "(Domicilio): "+newCliente.getDomicilio()+"\n"+
						  "(Telefono): "+newCliente.getTelefono()+"\n"+
						  "(Fecha de contrato): "+newCliente.getFechaContrato()+"\n";
			  	}
		System.out.println(mostrar);
	}
	
	static public void buscarClientePorNombre(String nombre){
		if(listaAuxiliar.containsKey(nombre)){
			Iterator<String> it = listaAuxiliar.get(nombre).iterator();
			System.out.println("----------------------"+"Busqueda con el nombre:'"+nombre+"'------------------------------");
			while(it.hasNext()){
				buscarClientePorCuit(it.next());
			}
			System.out.println("------------------------"+"Fin"+"----------------------------");
		}else{
			System.out.println("No existe ese cliente");
		}
	}
	static public void busquedaDeClientePorDni(int numeroDni){
		System.out.println("-------------------------"+"Busqueda por DNI: "+numeroDni+"--------------------------------------");
		buscarClientePorCuit(listaAuxiliar2.get(numeroDni));
		System.out.println("----------------------------------------"+"Fin"+"------------------------------------------------");
	}
}
