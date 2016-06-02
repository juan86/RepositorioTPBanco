package prod.excepciones;

public class ExcepcionNoEsTitular extends Exception{
	public ExcepcionNoEsTitular(String cuit,String cbu){
		super("Al Cliente con el cuit: "+cuit+" no es titular de la cuenta con el cbu: "+cbu);
	}
}
