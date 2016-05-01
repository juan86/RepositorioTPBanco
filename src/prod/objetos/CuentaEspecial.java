package prod.objetos;

import prod.excepciones.ExcepcionCuenta;
import prod.excepciones.ExcepcionCuentaInactiva;
import prod.excepciones.ExcepcionCuentaIncoherente;
import prod.excepciones.ExcepcionCuentaMonedaInvalida;

public class CuentaEspecial extends Cuenta {

	/* Constructor para Cuenta Especial
	 * Pre-condicion: ingresar el cbu y la moneda de la cuenta especial
	 * Post-Conficion: Crear una cuenta con los parametros ingresados y el saldo inicial en 0
	 */
	public CuentaEspecial(String cbuIngresado, Moneda nominacionIngresada){
		super(cbuIngresado, nominacionIngresada);
		super.setSaldo(0.0);
		TipoMovimiento movimiento = TipoMovimiento.credito;
		Transaccion primerTransaccion = new Transaccion(movimiento, 0.0, "Creacion", "Movimiento Inicial En la Creacion");
		this.agregarTransaccion(primerTransaccion);
	}

	public void acreditar(double montoAAcreditar, Moneda tipoMoneda) throws ExcepcionCuenta {

	double sumaMovimientos = 0.0;
		
		if(!super.getEstado()){
			throw new ExcepcionCuentaInactiva("La cuenta esta inactiva y no admite operaciones");
		
		}
		else if(tipoMoneda != this.getMoneda()){
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
			throw new ExcepcionCuentaMonedaInvalida("El tipo de moneda ingresado es invalido.");
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
}
