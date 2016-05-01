package prod.objetos;
import java.util.ArrayList;

import prod.excepciones.ExcepcionCuenta;

public abstract class Cuenta {

	private final String cbu;
	private boolean activo;
	private final Moneda nominacion;
	private double saldo;
	protected ArrayList<Transaccion> historicoMov;
	
	
	//Constructor para las cuentas de ahorro y especiales con la nominacion indicada
	
	public Cuenta(String cbuIngresado, Moneda nominacionIngresada){
		this.cbu = cbuIngresado;
		this.nominacion = nominacionIngresada;
		this.activo = true;
	}
	
	
	//Constructor para las cuentas corriente las cuales no especifican nominacion ( por default es pesos)
	public Cuenta (String cbuIngresado){
		this.cbu = cbuIngresado;
		this.activo = true;
		this.nominacion = Moneda.pesos;
	}
	
	
	//Metodos heredados por todas las subclases
	
	public abstract void acreditar(double montoAAcreditar, Moneda tipoMoneda) throws ExcepcionCuenta;

	public abstract void debitar(double montoADebitar, Moneda tipoMonedaS) throws ExcepcionCuenta;
	
	
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
	
	public double getSaldo(){
		return this.saldo;
	}
		
	public boolean getEstado(){
		return this.activo;
	}
	
	public ArrayList<Transaccion> getHistoricoMov(){
		return this.historicoMov;
	}
	
	
	public void setSaldo(double nuevoSaldo){
		this.saldo = nuevoSaldo;
		return;
	}
	
	public void setActiva(boolean nuevoEstado){
		this.activo = nuevoEstado;
		return;
	}

}