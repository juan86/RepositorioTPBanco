import java.util.ArrayList;

public abstract class CuentaCliente extends Cuenta {

	private ArrayList<Cliente> titulares;
	
	//Constructor para Cuenta Ahorro
	
	public CuentaCliente(double saldoInicial, String cbuIngresado, Moneda nominacionIngresada, ArrayList<Cliente> titularesIniciales){
		super(cbuIngresado, nominacionIngresada);
		this.setSaldo(saldoInicial);
		for (int i=0; i<titularesIniciales.size(); i++){
			titulares.add(titularesIniciales.get(i));
		}
	}
	
	//Constructor para Cuenta Corriente
	public CuentaCliente(double saldoInicial, String cbuIngresado, ArrayList<Cliente> titularesIniciales){
		super(cbuIngresado);
		this.setSaldo(saldoInicial);
		for (int i=0; i<titularesIniciales.size(); i++){
			titulares.add(titularesIniciales.get(i));
		}
	}
}
