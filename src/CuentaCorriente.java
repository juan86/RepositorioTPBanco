import java.util.ArrayList;

public class CuentaCorriente extends CuentaCliente {

	private double sobregiro;
	private static final double comision = 3.0;
	
	public CuentaCorriente(double saldoInicial, String cbuIngresado, Moneda nominacionIngresada, ArrayList<Cliente> titularesIniciales, double sobregiroIngresado){
		super(saldoInicial, cbuIngresado, nominacionIngresada, titularesIniciales);
		this.sobregiro = sobregiroIngresado;
	}
	
	public void acreditar(double montoAAcreditar){
		
	}
	
	public void debitar(double montoADebitar){
		
	}
}
