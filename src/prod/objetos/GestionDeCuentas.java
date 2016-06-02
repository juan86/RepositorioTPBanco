package prod.objetos;
import java.util.ArrayList;
import java.util.Random;

import prod.excepciones.ExcepcionCuenta;
import prod.excepciones.ExcepcionCuentaInactiva;
public class GestionDeCuentas {
	
	public GestionDeCuentas(){
	}
	
	/*Pre-Condicion: se pide pasar el monto inicial mayor a cero, ingresar una nominacion valida, y una tasa de interes mayor a cero
	 * junto con el cliente/es titular/es de la cuenta en un ArrayList
	 */
	//Post-Condicion: Devuelve un objeto cuenta ahorro inicializado con un CBU correspondiente al CUIT + numero aleatorio entre 0 y 9999
	
	public CuentaAhorro aperturaDeCuentaAhorro(ArrayList<Cliente> titulares, double montoInicial, Moneda nominacionDeCuenta, double tasaDeInteres) {
		Random rndm = new Random();
		Cliente primerCliente = titulares.get(1);
		String cbuGenerado = primerCliente.getCuit() + (int)(rndm.nextDouble()*10000);
		CuentaAhorro cuentaACrear = null;
		try {
			cuentaACrear = new CuentaAhorro(montoInicial, cbuGenerado, nominacionDeCuenta, titulares, tasaDeInteres);
		} 
		catch (ExcepcionCuenta e) {
			e.printStackTrace();
		}
		return cuentaACrear;
	}
	
	
	/*Pre-Condicion: se pide pasar el monto inicial mayor o igual a 10000, y el monto del sobregiro mayor igual a 0
	 * junto con el cliente/es titular/es de la cuenta en un ArrayList
	 */
	//Post-Condicion: Devuelve un objeto cuenta corriente inicializado con un CBU correspondiente al CUIT + numero aleatorio entre 0 y 9999
	
	
	public CuentaCorriente aperturaDeCuentaCorriente(ArrayList<Cliente> titulares, double montoInicial, double sobregiro){
		Random rndm = new Random();
		Cliente primerCliente = titulares.get(1);
		String cbuGenerado = primerCliente.getCuit() + (int)(rndm.nextDouble()*10000);
		CuentaCorriente cuentaACrear = null;
		try {
			cuentaACrear = new CuentaCorriente(montoInicial, cbuGenerado, titulares, sobregiro);
		} catch (ExcepcionCuenta e) {
			e.printStackTrace();
		}
		return cuentaACrear;
		}
	
	/* Pre-Condicion: ingresar una cuenta que este activada.
	*  Post-condicion: cambiar el estado de la cuenta a inactiva.
	*/
	
	public void inhabilitarCuenta(Cuenta cuentaAInhabilitar) throws ExcepcionCuenta{
		if(cuentaAInhabilitar.getEstado()){
			cuentaAInhabilitar.setActiva(false);
			}
		else{
			throw new ExcepcionCuentaInactiva("La cuenta ya se encuentra inactiva.");
		}
		return;
	}
	
	/* Pre-Condicion: ingresar una cuenta que este desactivada.
	*  Post-condicion: cambiar el estado de la cuenta a activa.
	*/
	
	public void habilitarCuenta(Cuenta cuentaAHabilitar) throws ExcepcionCuenta{
		if(!(cuentaAHabilitar.getEstado())){
			cuentaAHabilitar.setActiva(true);
		}
		else{
			throw new ExcepcionCuentaInactiva("La cuenta ya se encuentra activada.");
		}
		return;
	}
}
