package prod.excepciones;

public class ExcepcionTipDNICliente extends Exception{
	public ExcepcionTipDNICliente(){
		super("Error tipo DNI, debe ingresar la opcion 1 o 2");
	}
}
