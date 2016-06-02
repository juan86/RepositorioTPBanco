package prod.excepciones;

public class ExcepcionTelefonoCliente extends ExcepcionAltaCliente{
	public ExcepcionTelefonoCliente(String telefono){
		super("Telefono invalido: "+telefono);
	}
}
