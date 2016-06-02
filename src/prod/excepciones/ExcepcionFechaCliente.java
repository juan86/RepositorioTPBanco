package prod.excepciones;

public class ExcepcionFechaCliente extends ExcepcionAltaCliente{
	public ExcepcionFechaCliente(String fecha){
		super("Fecha Invalidad: "+fecha+" (Ej: 'dd/mm/aaaa')");
	}
}
