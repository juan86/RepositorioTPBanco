package prod.excepciones;

public class ExcepcionExtraccionCCorriente extends Exception{
	public ExcepcionExtraccionCCorriente(){
		super("No se puede Extraer efectivo de una cuenta corriente por Ventanilla");
	}
}
