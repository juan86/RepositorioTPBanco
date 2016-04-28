
public class OperacionesPorVentanilla {
	
	private Banco bancoGestionado;
	
	public OperacionesPorVentanilla(Banco bancoAGestionar){
		this.bancoGestionado = bancoAGestionar;
	}
	
	
	public void depositoEnEfectivo (Moneda tipoDeMoneda, double monto, Cuenta cuentaADepositar){
		double comision;
		
		if(cuentaADepositar instanceof CuentaAhorro ){
			cuentaADepositar.acreditar(monto);
			//Transaccion nuevaTransaccion = new Transaccion("Credito", monto, "Deposito por Ventanilla" );
			//cuentaADepositar.agregarTransaccion(nuevaTransaccion);
		}
		
		else if (cuentaADepositar instanceof CuentaCorriente){
			comision = monto*0.03;
			cuentaADepositar.acreditar(monto);
			//Transaccion nuevaTransaccion = new Transaccion("Credito", monto, "Deposito por Ventanilla" );
			//cuentaADepositar.agregarTransaccion(nuevaTransaccion);
			cuentaADepositar.debitar(comision);
			
		}
		return;
	}
}
