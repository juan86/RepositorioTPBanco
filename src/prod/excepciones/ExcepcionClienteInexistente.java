package prod.excepciones;

public class ExcepcionClienteInexistente extends ExcepcionCliente{
	public ExcepcionClienteInexistente(String cliente){
		super("El Cliente: "+cliente+" no existe");
	}
}
