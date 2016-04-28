import java.util.ArrayList;

public class CuentaCorriente extends CuentaCliente {

	private double sobregiro;
	private static final double comision = 3.0;
	
	public CuentaCorriente(double saldoInicial, String cbuIngresado, ArrayList<Cliente> titularesIniciales, double sobregiroIngresado){
		super(saldoInicial, cbuIngresado, titularesIniciales);
		this.setMoneda(Moneda.pesos);
		this.sobregiro = sobregiroIngresado;
	}
	
}
