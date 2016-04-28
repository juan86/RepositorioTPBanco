import java.util.ArrayList;

public abstract class Cuenta {

	private final String cbu;
	private boolean activo;
	private Moneda nominacion;
	private double saldo;
	private ArrayList<Transaccion> historicoMov;
	
	
	//Constructor para las cuentas de ahorro y especiales con la nominacion indicada
	
	public Cuenta(String cbuIngresado, Moneda nominacionIngresada){
		this.cbu = cbuIngresado;
		this.nominacion = nominacionIngresada;
		this.activo = true;
	}
	
	
	//Constructor para las cuentas corriente las cuales no especifican nominacion ( por default es pesos)
	public Cuenta(String cbuIngresado){
		this.cbu = cbuIngresado;
		this.activo = true;
	}
	
	
	//Metodos heredados por todas las subclases
	
	public void acreditar(double montoAAcreditar) {
		this.setSaldo(this.getSaldo() + montoAAcreditar);
	}

	public void debitar(double montoADebitar) {
		this.setSaldo(this.getSaldo() - montoADebitar);
	}
	
	
	public void agregarTransaccion(Transaccion nuevaTr){
		this.historicoMov.add(nuevaTr);
	}
	
	public Transaccion obtenerTransaccion(int cantidad){
		return (this.historicoMov.get(cantidad));
	}
	
	
	//----------------------Declaracion de getters y setters---------------------------
	
	public String getCbu(){
		return this.cbu;
	}
	
	public Moneda getMoneda(){
		return this.nominacion;
	}
	
	public void setMoneda(Moneda nuevaNominacion){
		this.nominacion = nuevaNominacion;
		return;
	}
	
	public double getSaldo(){
		return this.saldo;
	}
	
	public void setSaldo(double nuevoSaldo){
		this.saldo = nuevoSaldo;
		return;
	}
	
	public boolean getActiva(){
		return this.activo;
	}
	
	
	public void setActiva(boolean nuevoEstado){
		this.activo = nuevoEstado;
		return;
	}

}

