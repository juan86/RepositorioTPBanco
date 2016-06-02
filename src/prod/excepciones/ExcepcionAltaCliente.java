package prod.excepciones;

public class ExcepcionAltaCliente extends ExcepcionCliente{

	public ExcepcionAltaCliente(String excepcion){
		super("Error Alta de Cliente."+excepcion);
	}
}
