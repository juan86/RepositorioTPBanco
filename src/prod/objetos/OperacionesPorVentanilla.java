package prod.objetos;
import java.util.ArrayList;
import prod.excepciones.ExcepcionCuenta;
import prod.excepciones.ExcepcionCuentaSinMovimientos;
import prod.excepciones.ExcepcionExtraccionCCorriente;

public class OperacionesPorVentanilla {
	
	private static OperacionesPorVentanilla instanciaOperacionesVentanilla;
	
	private OperacionesPorVentanilla(){
	}
	
	public static OperacionesPorVentanilla getInstance(){
		OperacionesPorVentanilla instanciaCreada = null;
		if(instanciaOperacionesVentanilla == null){
			instanciaCreada = new OperacionesPorVentanilla();
		}
		return instanciaCreada;
	}
	
	public void depositoEfectivo(double monto, Moneda tipoMoneda,Cuenta cuenta) throws ExcepcionCuenta{
		cuenta.acreditar(monto, tipoMoneda);
		Transaccion newTransaccion = new Transaccion(TipoMovimiento.credito,monto,"Deposito en Efectivo");
		cuenta.agregarTransaccion(newTransaccion);
	}
	public void extraccionEfectivo(double monto,Moneda tipoMoneda,Cuenta cuenta) throws ExcepcionExtraccionCCorriente, ExcepcionCuenta{
		if(cuenta instanceof CuentaCorriente){
			throw new ExcepcionExtraccionCCorriente();
		}
		cuenta.debitar(monto, tipoMoneda);
		Transaccion newTransaccion = new Transaccion(TipoMovimiento.debito,monto,"Extraccion en Efectivo");
		cuenta.agregarTransaccion(newTransaccion);
	}
	public void transferencia(Cuenta cuentaOrigen,Cuenta cuentaDestino,double monto,double cambioActual) throws ExcepcionCuenta{
		if(cuentaOrigen.getMoneda()==cuentaDestino.getMoneda()){
			cuentaOrigen.debitar(monto, cuentaDestino.getMoneda());
			cuentaDestino.acreditar(monto,cuentaOrigen.getMoneda());
			Transaccion transaccionOrigen = new Transaccion(TipoMovimiento.debito,monto,"TransferenciaDesde");
			cuentaOrigen.agregarTransaccion(transaccionOrigen);
			Transaccion transaccionDestino = new Transaccion(TipoMovimiento.credito,monto,"TransferenciaHacia");
			cuentaDestino.agregarTransaccion(transaccionDestino);
		}else if(cuentaOrigen.getMoneda()== Moneda.dolares){
			double montoTransferencia = monto * cambioActual;
			cuentaOrigen.debitar(monto,Moneda.dolares);
			cuentaDestino.acreditar(montoTransferencia,Moneda.pesos);
			Transaccion transaccionOrigen = new Transaccion(TipoMovimiento.debito,monto,"TransferenciaDesde");
			cuentaOrigen.agregarTransaccion(transaccionOrigen);
			Transaccion transaccionDestino = new Transaccion(TipoMovimiento.credito,montoTransferencia,"TransferenciaHacia");
			cuentaDestino.agregarTransaccion(transaccionDestino);
		}else{
			double montoTransferencia = monto / cambioActual;
			cuentaOrigen.debitar(monto,Moneda.pesos);
			cuentaDestino.acreditar(montoTransferencia,Moneda.pesos);
			Transaccion transaccionOrigen = new Transaccion(TipoMovimiento.debito,monto,"TransferenciaDesde");
			cuentaOrigen.agregarTransaccion(transaccionOrigen);
			Transaccion transaccionDestino = new Transaccion(TipoMovimiento.credito,montoTransferencia,"TransferenciaHacia");
			cuentaDestino.agregarTransaccion(transaccionDestino);
		}
	}
	public void todosMovimientosCuenta(Cuenta cuenta) throws ExcepcionCuentaSinMovimientos{
		ArrayList<Transaccion> listadoTransacciones = cuenta.getHistoricoMov();
		if(listadoTransacciones.size() == 0){
			throw new ExcepcionCuentaSinMovimientos();
		}
		for(int i = 0;i < listadoTransacciones.size();i++){
			System.out.println(listadoTransacciones.get(i).toString());
		}
	}
	public void ultimosNMovimientos(Cuenta cuenta,int numero) throws ExcepcionCuentaSinMovimientos{
		ArrayList<Transaccion> listadoTransacciones = cuenta.getHistoricoMov();
		if(listadoTransacciones.size() == 0){
			throw new ExcepcionCuentaSinMovimientos();
		}
		for(int i = (listadoTransacciones.size()-numero);i<listadoTransacciones.size();i++){
			System.out.println(listadoTransacciones.get(i).toString());
		}
	}
}	