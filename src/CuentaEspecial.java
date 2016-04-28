public class CuentaEspecial extends Cuenta {

	/* Constructor para Cuenta Especial
	 * Pre-condicion: ingresar el cbu y la moneda de la cuenta especial
	 * Post-Conficion: Crear una cuenta con los parametros ingresados y el saldo inicial en 0
	 */
	public CuentaEspecial(String cbuIngresado, Moneda nominacionIngresada){
		super(cbuIngresado, nominacionIngresada);
		super.setSaldo(0.0);
	}
	

}
