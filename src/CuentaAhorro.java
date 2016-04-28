import java.util.ArrayList;

public class CuentaAhorro extends CuentaCliente {
	
	private double tasaInteres;
	private static double costoMantenimientoPesos = 200.0;
	private static double costoMantenimientoDolares = 10.0;
	private double costoActualMantenimiento;
	
	public CuentaAhorro(double saldoInicial, String cbuIngresado, Moneda nominacionIngresada,
			ArrayList<Cliente> titularesIniciales, double tasaInteresInicial) {
		super(saldoInicial, cbuIngresado, nominacionIngresada, titularesIniciales);
		this.tasaInteres = tasaInteresInicial;
		if(nominacionIngresada == Moneda.pesos){
			this.costoActualMantenimiento = this.costoMantenimientoPesos;
		}
		else{
			this.costoActualMantenimiento = this.costoMantenimientoDolares;
		}
	}


	public void acreditar(double montoAAcreditar) {
		super.setSaldo(super.getSaldo() + montoAAcreditar);
	}

	public void debitar(double montoADebitar) {
		super.setSaldo(super.getSaldo() - montoADebitar);
	}

	public double getCostoMantenimiento(){
		return this.costoActualMantenimiento;
	}
	
	
	public static void setCostoMantenimientoPesos(double nuevoCosto){
		costoMantenimientoPesos = nuevoCosto;
		return;
	}
	
	public static void setCostoMantenimientoDolares(double nuevoCosto){
		costoMantenimientoDolares = nuevoCosto;
		return;
	}
}