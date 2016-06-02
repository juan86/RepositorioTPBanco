package prod.excepciones;

public class ExcepcionDniCliente extends ExcepcionAltaCliente{
	public ExcepcionDniCliente(String dni){
		super("Documento Invalido: "+dni);
	}
}
