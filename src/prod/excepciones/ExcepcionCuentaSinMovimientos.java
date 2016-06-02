package prod.excepciones;

public class ExcepcionCuentaSinMovimientos extends Exception{
	public ExcepcionCuentaSinMovimientos(){
		super("La Cuenta ingresada no contiene Movimientos");
	}
}
