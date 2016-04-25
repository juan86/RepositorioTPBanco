import java.util.ArrayList;

public class CuentaAhorro extends CuentaCliente {
	
	private double tasaInteres;
	
	public CuentaAhorro(double saldoInicial, String cbuIngresado, Moneda nominacionIngresada,
			ArrayList<Cliente> titularesIniciales, double tasaInteresInicial) {
		super(saldoInicial, cbuIngresado, nominacionIngresada, titularesIniciales);
		this.tasaInteres = tasaInteresInicial;
		
	}


	public void acreditar(double montoAAcreditar) {
		super.saldo = super.saldo + montoAAcreditar;

	}

	public void debitar(double montoADebitar) {
		super.saldo = super.saldo - montoADebitar;

	}

}
