package prod.objetos;
import java.util.ArrayList;

import prod.excepciones.ExcepcionCuenta;
import prod.excepciones.ExcepcionCuentaSaldoInicialInvalido;
import prod.excepciones.ExcepcionCuentaTitularInvalido;

public abstract class CuentaCliente extends Cuenta {

	private final ArrayList<Cliente> titulares;
	
	//Constructor para Cuenta Ahorro
	
	public CuentaCliente(double saldoInicial, String cbuIngresado, Moneda nominacionIngresada, ArrayList<Cliente> titularesIniciales) throws ExcepcionCuenta{
		super(cbuIngresado, nominacionIngresada);
		if(saldoInicial <= 0.0){
			throw new ExcepcionCuentaSaldoInicialInvalido("La cantidad ingresada no esta permitida para su cuenta.");
		}
		this.setSaldo(saldoInicial);
		for(int i=0 ; i<titularesIniciales.size(); i++){
			if(!(titularesIniciales.get(i) instanceof PersonaFisica)){
				throw new ExcepcionCuentaTitularInvalido("Uno de los titulares no cumple las condiciones para abrir esta cuenta.");
			}
		}
		this.titulares = titularesIniciales;
		Transaccion primerDeposito = new Transaccion(TipoMovimiento.credito, saldoInicial, "Deposito Inicial");
		this.agregarTransaccion(primerDeposito);
	}
	
	//Constructor para Cuenta Corriente
	public CuentaCliente(double saldoInicial, String cbuIngresado, ArrayList<Cliente> titularesIniciales) throws ExcepcionCuenta{
		super(cbuIngresado);
		if(saldoInicial <= 10000.0){
			throw new ExcepcionCuentaSaldoInicialInvalido("La cantidad ingresada no esta permitida para su cuenta.");
		}
		this.setSaldo(saldoInicial);
		this.titulares = titularesIniciales;
		Transaccion primerDeposito = new Transaccion(TipoMovimiento.credito, saldoInicial, "Deposito Inicial");
		this.agregarTransaccion(primerDeposito);
	}
}
