package prod.objetos;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import prod.excepciones.ExcepcionClienteInexistente;
import prod.excepciones.ExcepcionCuenta;
import prod.excepciones.ExcepcionCuentaInactiva;
public class GestionDeCuentas {
	
	private static GestionDeCuentas instanciaGestionDeCuentas;
	private ArrayList<Cuenta> listadoDeCuentas;
	
	private GestionDeCuentas(){
		this.listadoDeCuentas = new ArrayList<Cuenta>();
	}
	
	public static GestionDeCuentas getInstance(){
		GestionDeCuentas instanciaCreada = null;
		if(instanciaGestionDeCuentas == null){
			instanciaCreada = new GestionDeCuentas();
		}
		return instanciaCreada;
	}
	
	/* Precondicion: ingresar una cadena de texto separada por comas con los cuits de los clientes titulares dados de alta correctamente + un monto inicial mayor a 0 + 
	 *  
	 * 
	 */
	
	public void aperturaDeCuentaAhorro(String titulares, double montoInicial, Moneda nominacionDeCuenta, double tasaDeInteres) {
		listadoDeCuentas = new ArrayList<Cuenta>();
		ArrayList<Cliente> titularesIngresados = new ArrayList<Cliente>();
		StringTokenizer st = new StringTokenizer(titulares, ",");
		while(st.hasMoreTokens()){
			try {
				titularesIngresados.add(GestionClientes.getInstance().getCliente(st.nextToken()));
			} catch (ExcepcionClienteInexistente e) {
				System.out.println(e.getMessage() + "Por favor verifique los cuits de los clientes que ha ingresado.");
			}
		}
		Random rndm = new Random();
		Cliente primerCliente = titularesIngresados.get(1);
		String cbuGenerado = primerCliente.getCuit() + (int)(rndm.nextDouble()*10000);
		CuentaAhorro cuentaACrear = null;
		try {
			cuentaACrear = new CuentaAhorro(montoInicial, cbuGenerado, nominacionDeCuenta, titularesIngresados, tasaDeInteres);
		} 
		catch (ExcepcionCuenta e) {
			System.out.println(e.getMessage());
		}
		this.listadoDeCuentas.add(cuentaACrear);
		return;
	}
	
	
	/*Pre-Condicion: se pide pasar el monto inicial mayor o igual a 10000, y el monto del sobregiro mayor igual a 0
	 * junto con el cliente/es titular/es de la cuenta en un ArrayList
	 */
	//Post-Condicion: Devuelve un objeto cuenta corriente inicializado con un CBU correspondiente al CUIT + numero aleatorio entre 0 y 9999
	
	
	public void aperturaDeCuentaCorriente(String titulares, double montoInicial, double sobregiro){
		listadoDeCuentas = new ArrayList<Cuenta>();
		ArrayList<Cliente> titularesIngresados = new ArrayList<Cliente>();
		StringTokenizer st = new StringTokenizer(titulares, ",");
		while(st.hasMoreTokens()){
			try {
				titularesIngresados.add(GestionClientes.getInstance().getCliente(st.nextToken()));
			} catch (ExcepcionClienteInexistente e) {
				System.out.println(e.getMessage() + "Por favor verifique los cuits de los clientes que ha ingresado.");
			}
		}
		Random rndm = new Random();
		Cliente primerCliente = titularesIngresados.get(1);
		String cbuGenerado = primerCliente.getCuit() + (int)(rndm.nextDouble()*10000);
		CuentaCorriente cuentaACrear = null;
		try {
			cuentaACrear = new CuentaCorriente(montoInicial, cbuGenerado, titularesIngresados, sobregiro);
		} 
		catch (ExcepcionCuenta e) {
			System.out.println(e.getMessage());
		}
		this.listadoDeCuentas.add(cuentaACrear);
		return;
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
