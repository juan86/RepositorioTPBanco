package prod.objetos;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

class Batch {

	private double costoMantenimientoEnDolar;
	private double cotizacionDolar;
	private ArrayList<CuentaAhorro> listaDeCuentas;
	// LISTA DE CUENTAS DE LOS CLIENTES
	//private CuentaCliente[] cuentasDeLosClientes = new CuentaCliente[14];
	// CUENTA ESPECIAL DONDE VAN LOS MONTOS COBRADOS
	private CuentaEspecial mantenimientos;
	
	public Batch(ArrayList<CuentaAhorro> listaDeCuentas, CuentaEspecial mantenimiento, double cotizacionDolar) {
		
		this.listaDeCuentas= listaDeCuentas;
		this.cotizacionDolar = cotizacionDolar;
		this.mantenimientos = mantenimiento;
		// ASI TENDRIA QUE IR A BUSCAR EL MANTENIMIENTO A LA CLASE CUENTA:
		// this.costoMantenimientoEnDolar = getCostoMantenimientoActual();
		// SE CREAN LAS CONDICIONES APTAS PARA LA PRUEBA DEL BATCH
		//crearCondicionesParaBatch();
	}
	public void cobroDeMantenimientos(){
		
		ArrayList<String> listaMantenimientosCobrados = new ArrayList<String>();
		ArrayList<String> listaErroresMantenimiento = new ArrayList<String>();
		String motivo = null;
		double aux = 0;
		
		for(int i = 0; i < listaDeCuentas.size(); i++){
		
				if(listaDeCuentas.get(i).getEstado() == true){
					//
					if (listaDeCuentas.get(i).getMoneda() == Moneda.dolares){
						//
						if(listaDeCuentas.get(i).getSaldo() < listaDeCuentas.get(i).getCostoMantenimiento()){
							motivo = "El monto de la cuenta es insuficiente.";
							listaErroresMantenimiento.add(getDescripcion(listaDeCuentas.get(i).getCbu(),
																		 listaDeCuentas.get(i).getSaldo(),
														  				 motivo));
						}else{
							listaDeCuentas.get(i).setSaldo(-listaDeCuentas.get(i).getCostoMantenimiento());
							aux = aux + (listaDeCuentas.get(i).getCostoMantenimiento() * cotizacionDolar);
							//cobrarMantenimiento(costoMantenimientoEnDolar * cotizacionDolar);
							listaMantenimientosCobrados.add(getDescripcion(listaDeCuentas.get(i).getCbu(),
																			listaDeCuentas.get(i).getSaldo(),
																			listaDeCuentas.get(i).getMoneda()));
						}
					}else{
						//
						if(listaDeCuentas.get(i).getSaldo() < (listaDeCuentas.get(i).getCostoMantenimiento() * cotizacionDolar)){
							motivo = "El monto de la cuenta es insuficiente.";
							listaErroresMantenimiento.add(getDescripcion(listaDeCuentas.get(i).getCbu(),
																		listaDeCuentas.get(i).getSaldo(),
														  				motivo));
						}else{
							listaDeCuentas.get(i).setSaldo(-listaDeCuentas.get(i).getCostoMantenimiento() * cotizacionDolar);
							aux = aux + (listaDeCuentas.get(i).getCostoMantenimiento() * cotizacionDolar);
							//cobrarMantenimiento(costoMantenimientoEnDolar * cotizacionDolar);
							listaMantenimientosCobrados.add(getDescripcion(listaDeCuentas.get(i).getCbu(),
																		   listaDeCuentas.get(i).getSaldo(),
																		   listaDeCuentas.get(i).getMoneda()));
						}
					}
				}else{ 
					motivo = "La Cuenta se encuentra Inactiva.";
					listaErroresMantenimiento.add(getDescripcion(listaDeCuentas.get(i).getCbu(),
																 listaDeCuentas.get(i).getSaldo(),
												  				 motivo));
				}
			}
		//
		generaArchivoMantTxt(listaMantenimientosCobrados);
		generaArchivoErroresTxt(listaErroresMantenimiento);
		
	}
	public void generaArchivoMantTxt(ArrayList<String> arrayListMant){
		
		ListIterator<String> iteradorInicio = arrayListMant.listIterator(0);
		//
		try{
			File mantenimientoCobrados = new File("mantenimientoCobrados"+getFechaActual()+".txt");
			FileWriter escribir = new FileWriter(mantenimientoCobrados, true);
			//
			escribir.write("--------------------------------- Mantenimientos realizados ---------------------------------\r\n");
			while(iteradorInicio.hasNext()){
				escribir.write(iteradorInicio.next()+"\r\n");
			}
			escribir.write("----------------------------------- Fin de Mantenimientos -----------------------------------");
			escribir.close();
		}
		catch(Exception E){
			System.out.println("Error al escribir");
		}
	}
	public void generaArchivoErroresTxt(ArrayList<String> arrayListErrores){
		
		ListIterator<String> iteradorInicio = arrayListErrores.listIterator(0);
		//
		try{
			File ErroresMantenimiento = new File("ErroresMantenimiento"+getFechaActual()+".txt");
			FileWriter escribir = new FileWriter(ErroresMantenimiento, true);
			//
			escribir.write("---------------------------------- Errores de Mantenimiento ----------------------------------\r\n");
			while(iteradorInicio.hasNext()){
				escribir.write(iteradorInicio.next()+"\r\n");
			}
			escribir.write("----------------------------------- Fin de Mantenimientos ---------------------------------------");
			escribir.close();
		}
		catch(Exception E){
			System.out.println("Error al escribir");
		}
	}
	public String getDescripcion(String cbu, double saldo, String motivo){
		
		 return "CBU: "+cbu+", Tipo de cuenta: Caja de Ahorro"+", Monto: "+saldo+", Motivo: "+motivo;
	}
	public String getDescripcion(String cbu, double saldo, Moneda nominacion){
		
		return "CBU: "+cbu+", Tipo de cuenta: Caja de Ahorro"+", Monto: "+saldo+", Moneda: "+nominacion+", Tipo de Cambio: "+this.cotizacionDolar;
	}
	public void cobrarMantenimiento(double monto){
		
		this.mantenimientos.setSaldo(monto); 
	}
	public String getFechaActual() {
        Date dia = new Date();
        SimpleDateFormat formatoDia = new SimpleDateFormat("yyyy-MM-dd");
        return formatoDia.format(dia);
    }
	/*
	public void crearCondicionesParaBatch(){
		
		// SE CREA LA CUENTA ESPECIAL MANTENIMIENTOS
		CuentaEspecial mantenimientos = new CuentaEspecial();
		this.mantenimientos = mantenimientos;
			// NOTA APARTE IMPORTANTE:
			// SE CREAN CUENTAS ALEATORIAS EL BOOLEAN DENOMINA EL TIPO DE CUENTA:
				// TRUE = CAJA DE AHORRO
				// FALSE = CUENTA CORRIENTE
			// SE ELIMINA EL TIPO DE CUENTA YA QUE SE PRESUME QUE EL BATCH SOLO CORRERA SOBRE
			// EL ARRAYLIST, O TIPO DE ESTRUCTURA QUE CORRESPONDA, QUE SOLO CONTENGA
			// CAJAS DE AHORRO. POR LO TANTO EL BATCH NO LLEGA A CORRER SOBRE CUENTAS CORRIENTES.
			// POR ESO PUEDE SER QUE TENGA QUE HABER 2 CONTRUCTORES PARA CUENTA:
			// UNA LISTA PARA CC Y OTRA LISTA PARA CA.
		CuentaCliente CC1 = new CuentaCliente(Moneda.PESO, 1000);
		CuentaCliente CC2 = new CuentaCliente(Moneda.PESO, 0);
		CuentaCliente CC3 = new CuentaCliente(Moneda.DOLAR, 1000);
		CuentaCliente CC4 = new CuentaCliente(Moneda.DOLAR, 0);
		CuentaCliente CC5 = new CuentaCliente(Moneda.PESO, 1000);
		CuentaCliente CC6 = new CuentaCliente(Moneda.PESO, 0);
		CuentaCliente CC7 = new CuentaCliente(Moneda.DOLAR, 1000);
		CuentaCliente CC8 = new CuentaCliente(Moneda.DOLAR, 0);
		CuentaCliente CC9 = new CuentaCliente(Moneda.PESO, 1000);
		CuentaCliente CC10 = new CuentaCliente(Moneda.PESO, 1000);
		CuentaCliente CC11 = new CuentaCliente(Moneda.DOLAR, 2.5);
		CuentaCliente CC14 = new CuentaCliente(Moneda.DOLAR, 0);
		CuentaCliente CC12 = new CuentaCliente(Moneda.PESO, 17.75);
		CuentaCliente CC13 = new CuentaCliente(Moneda.PESO, -1);
		// SE DESHABILITA LA ULTIMA CC10
		CC10.cambiarEstado();
		CC14.cambiarEstado();
		// SE REALIZA LA POBLACION DEL ARRAY DE TIPO CUENTA
		cuentasDeLosClientes[0] = CC1;
		cuentasDeLosClientes[1] = CC2;
		cuentasDeLosClientes[2] = CC3;
		cuentasDeLosClientes[3] = CC4;
		cuentasDeLosClientes[4] = CC5;
		cuentasDeLosClientes[5] = CC6;
		cuentasDeLosClientes[6] = CC7;
		cuentasDeLosClientes[7] = CC8;
		cuentasDeLosClientes[8] = CC9;
		cuentasDeLosClientes[9] = CC10;
		cuentasDeLosClientes[10] = CC11;
		cuentasDeLosClientes[11] = CC12;
		cuentasDeLosClientes[12] = CC13;
		cuentasDeLosClientes[13] = CC14;
	}*/
}