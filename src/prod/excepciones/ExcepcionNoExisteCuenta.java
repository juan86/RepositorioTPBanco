package prod.excepciones;

public class ExcepcionNoExisteCuenta extends Exception{
	public ExcepcionNoExisteCuenta(String cbu){
		super("No existe la cuenta con el numero de CBU: " + cbu);
	}
}
