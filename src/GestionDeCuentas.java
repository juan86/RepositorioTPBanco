import java.util.ArrayList;
import java.util.Random;
public class GestionDeCuentas {

	private Banco bancoGestionado;
	
	public GestionDeCuentas(Banco bancoAGestionar){
		this.bancoGestionado = bancoAGestionar;
	}
	
	public CuentaAhorro aperturaDeCuenta(ArrayList<Cliente> titulares, double montoInicial, Moneda nominacionDeCuenta, double tasaDeInteres){
		Random rndm = new Random();
		Cliente primerCliente = titulares.get(1);
		String cbuGenerado = primerCliente.getcuit + (int)(rndm.nextDouble()*100);
	}
}
