public class CuentaEspecial extends Cuenta {

	
	public CuentaEspecial(String cbuIngresado, Moneda nominacionIngresada, double saldoInicial){
		super(cbuIngresado, nominacionIngresada);
		super.setSaldo(0.0);
	}
	

}
