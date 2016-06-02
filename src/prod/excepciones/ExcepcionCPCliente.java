package prod.excepciones;

public class ExcepcionCPCliente extends ExcepcionAltaCliente{
	public ExcepcionCPCliente(String cp){
		super("Codigo Postal Invalido:"+cp+" (Ej: 1650)");
	}
}
