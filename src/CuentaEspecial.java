public class CuentaEspecial extends Cuenta {

	
	public CuentaEspecial(String cbuIngresado, Moneda nominacionIngresada, double saldoInicial){
		super(cbuIngresado, nominacionIngresada);
		super.setSaldo(0.0);
	}
	
	public void acreditar(double montoAAcreditar) {
		super.saldo = super.saldo + montoAAcreditar;
		return;
	}

	
	public void debitar(double montoADebitar) {
		super.saldo = super.saldo - montoADebitar;
		return;
	}

}
