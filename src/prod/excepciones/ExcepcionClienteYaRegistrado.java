package prod.excepciones;

public class ExcepcionClienteYaRegistrado extends ExcepcionAltaCliente {

	public ExcepcionClienteYaRegistrado(String mensaje){
		super("El Cliente: "+mensaje+" ya se encuentra Registrado");
	}
}
