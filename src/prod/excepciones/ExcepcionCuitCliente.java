package prod.excepciones;

public class ExcepcionCuitCliente extends ExcepcionAltaCliente {
	public ExcepcionCuitCliente(String cuit){
		super("(Cuit Invalido): "+cuit);
	}
}
