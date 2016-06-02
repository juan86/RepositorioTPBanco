package prod.objetos;
import java.util.ArrayList;

import prod.excepciones.ExcepcionCuenta;
import prod.excepciones.ExcepcionCuentaInactiva;
import prod.excepciones.ExcepcionCuentaIncoherente;
import prod.excepciones.ExcepcionCuentaMonedaInvalida;
import prod.excepciones.ExcepcionCuentaSaldoInsuficiente;

public class CuentaAhorro extends CuentaCliente {
	
	private double tasaInteres;
	private static double costoMantenimientoPesos = 200.0;
	private static double costoMantenimientoDolares = 10.0;
	private double costoActualMantenimiento;
	
	public CuentaAhorro(double saldoInicial, String cbuIngresado, Moneda nominacionIngresada,
			ArrayList<Cliente> titularesIniciales, double tasaInteresInicial) throws ExcepcionCuenta {
		super(saldoInicial, cbuIngresado, nominacionIngresada, titularesIniciales);
		this.tasaInteres = tasaInteresInicial;
		if(nominacionIngresada == Moneda.pesos){
			this.costoActualMantenimiento = costoMantenimientoPesos;
		}
		else{
			this.costoActualMantenimiento = costoMantenimientoDolares;
		}
	}
	
	
	public void acreditar (double montoAAcreditar, Moneda tipoMoneda) throws ExcepcionCuenta{
		double sumaMovimientos = 0.0;
		
		if(!super.getEstado()){
			throw new ExcepcionCuentaInactiva("La cuenta esta inactiva y no admite operaciones");
		
		} 
		else if (tipoMoneda != this.getMoneda()){
			throw new ExcepcionCuentaMonedaInvalida("El tipo de moneda ingresado es invalido.");
		}
		else
		{
			for(int i=0; i < this.historicoMov.size() ; i++){
				if(historicoMov.get(i).getTipoMovimiento() == TipoMovimiento.credito){
					sumaMovimientos += historicoMov.get(i).getMonto();
				}
				sumaMovimientos -= historicoMov.get(i).getMonto();
			}
			if(sumaMovimientos != this.getSaldo()){
				throw new ExcepcionCuentaIncoherente("Error de coherencia entre los movimientos y el saldo.");
			}
		}
		
		this.setSaldo(this.getSaldo() + montoAAcreditar);
		return;
	}
	
	public void debitar(double montoADebitar, Moneda tipoMoneda) throws ExcepcionCuenta {
		double sumaMovimientos = 0.0;
		
		if(!this.getEstado()){
			throw new ExcepcionCuentaInactiva("La cuenta esta inactiva y no admite operaciones");
		}
		else if(tipoMoneda != this.getMoneda()){
			throw new ExcepcionCuentaMonedaInvalida("El tipo de moneda ingresado es invalido");
		}
			else if(montoADebitar > this.getSaldo()){
			throw new ExcepcionCuentaSaldoInsuficiente("Saldo insuficiente");
		}
		else
		{
			for(int i=0; i<historicoMov.size(); i++){
				if(historicoMov.get(i).getTipoMovimiento() == TipoMovimiento.credito){
					sumaMovimientos += historicoMov.get(i).getMonto();
				}
				sumaMovimientos -= historicoMov.get(i).getMonto();
			}
			if(sumaMovimientos != this.getSaldo()){
				throw new ExcepcionCuentaIncoherente("Error de coherencia entre los movimientos y el saldo.");
			}
		}
		this.setSaldo(this.getSaldo() - montoADebitar);
	}
	

	public double getCostoMantenimiento(){
		return this.costoActualMantenimiento;
	}
	
	public static double getCostoMantenimientoPesos(){
		return costoMantenimientoPesos;
	}
	
	public static double getCostoMantenimientoDolares(){
		return costoMantenimientoDolares;
	}
}