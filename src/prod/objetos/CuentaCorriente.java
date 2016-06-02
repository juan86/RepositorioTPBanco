package prod.objetos;
import java.util.ArrayList;

import prod.excepciones.ExcepcionCuenta;
import prod.excepciones.ExcepcionCuentaInactiva;
import prod.excepciones.ExcepcionCuentaIncoherente;

public class CuentaCorriente extends CuentaCliente {

	private double sobregiro;
	private static final double comision = 3.0;
	
	public CuentaCorriente(double saldoInicial, String cbuIngresado, ArrayList<Cliente> titularesIniciales, double sobregiroIngresado) throws ExcepcionCuenta{
		super(saldoInicial, cbuIngresado, titularesIniciales);
		this.sobregiro = sobregiroIngresado;
	}


	public void acreditar(double montoAAcreditar, Moneda tipoMoneda) throws ExcepcionCuenta {
		double sumaMovimientos = 0.0;
		
		if(!super.getEstado()){
			throw new ExcepcionCuentaInactiva("La cuenta esta inactiva y no admite operaciones");
		
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
	
	public double getSobreGiro(){
		return this.sobregiro;
	}
	
	public static double getComision(){
		return comision;
	}

}
