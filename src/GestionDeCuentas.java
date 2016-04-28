import java.util.ArrayList;
import java.util.Random;
public class GestionDeCuentas {

	private Banco bancoGestionado;
	
	public GestionDeCuentas(Banco bancoAGestionar){
		this.bancoGestionado = bancoAGestionar;
	}
	
	/*Pre-Condicion: se pide pasar el monto inicial mayor a cero, ingresar una nominacion valida, y una tasa de interes mayor a cero
	 * junto con el cliente/es titular/es de la cuenta en un ArrayList
	 */
	//Post-Condicion: Devuelve un objeto cuenta ahorro inicializado con un CBU correspondiente al CUIT + numero aleatorio entre 0 y 9999
	
	public void aperturaDeCuenta(ArrayList<Cliente> titulares, double montoInicial, Moneda nominacionDeCuenta, double tasaDeInteres){
		Random rndm = new Random();
		Cliente primerCliente = titulares.get(1);
		String cbuGenerado = primerCliente.getCuit + (int)(rndm.nextDouble()*10000);
		CuentaAhorro cuentaACrear = new CuentaAhorro(montoInicial, cbuGenerado, nominacionDeCuenta, titulares, tasaDeInteres);
		bancoGestionado.agregarCuenta(cuentaACrear);
		return;
	}
	
	
	/*Pre-Condicion: se pide pasar el monto inicial mayor o igual a 10000, y el monto del sobregiro mayor igual a 0
	 * junto con el cliente/es titular/es de la cuenta en un ArrayList
	 */
	//Post-Condicion: Devuelve un objeto cuenta corriente inicializado con un CBU correspondiente al CUIT + numero aleatorio entre 0 y 9999
	
	
	public void aperturaDeCuenta(ArrayList<Cliente> titulares, double montoInicial, double sobregiro){
		Random rndm = new Random();
		Cliente primerCliente = titulares.get(1);
		String cbuGenerado = primerCliente.getCuit + (int)(rndm.nextDouble()*10000);
		CuentaCorriente cuentaACrear = new CuentaCorriente(montoInicial, cbuGenerado, titulares, sobregiro);
		bancoGestionado.agregarCuenta(cuentaACrear);
		return;
		}
	
	/* Pre-Condicion: ingresar una cuenta que este activada.
	*  Post-condicion: cambiar el estado de la cuenta a inactiva.
	*/
	
	public void inhabilitarCuenta(Cuenta cuentaAInhabilitar){
		cuentaAInhabilitar.setActiva(false);
		return;		
	}
	
	/* Pre-Condicion: ingresar una cuenta que este desactivada.
	*  Post-condicion: cambiar el estado de la cuenta a activa.
	*/
	
	public void habilitarCuenta(Cuenta cuentaAHabilitar){
		cuentaAHabilitar.setActiva(true);
		return;
	}
}
