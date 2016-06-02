package prod.objetos;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import prod.excepciones.ExcepcionBatch;
import prod.excepciones.ExcepcionIOBatch;

class Batch {

	private double cotizacionDolar;
	private CuentaEspecial mantenimientos;
	
	public Batch(CuentaEspecial mantenimiento, double cotizacionDolar) {
		
		this.cotizacionDolar = cotizacionDolar;
		this.mantenimientos = mantenimiento;
	}
	public void cobroDeMantenimientos(ArrayList<CuentaCliente> cuentasCliente) throws ExcepcionIOBatch, ExcepcionBatch{
		
		ArrayList<CuentaCliente> listaDeCuentas = cuentasCliente;
		ArrayList<String> listaMantenimientosCobrados = new ArrayList<String>();
		ArrayList<String> listaErroresMantenimiento = new ArrayList<String>();
		String motivo = null;
		double aux = 0;
		
			for(int i = 0; i < listaDeCuentas.size(); i++){
				//
				try{
				if(listaDeCuentas.get(i) instanceof CuentaAhorro){
					//
					if(listaDeCuentas.get(i).getEstado() == true){
						//
						if (listaDeCuentas.get(i).getMoneda() == Moneda.dolares){
							//
							if(listaDeCuentas.get(i).getSaldo() < ((CuentaAhorro) listaDeCuentas.get(i)).getCostoMantenimiento() ){
								motivo = "El monto de la cuenta es insuficiente.";
								listaErroresMantenimiento.add(getDescripcion(listaDeCuentas.get(i).getCbu(),
																			 listaDeCuentas.get(i).getSaldo(),
															  				 motivo));
							}else{
								listaDeCuentas.get(i).setSaldo( listaDeCuentas.get(i).getSaldo() - ((CuentaAhorro) listaDeCuentas.get(i)).getCostoMantenimiento() );
								aux = aux + ( ((CuentaAhorro)listaDeCuentas.get(i)).getCostoMantenimiento() * cotizacionDolar );
								listaMantenimientosCobrados.add(getDescripcion(listaDeCuentas.get(i).getCbu(),
																			   listaDeCuentas.get(i).getSaldo(),
																			   listaDeCuentas.get(i).getMoneda()));
							}
						}else{
							//
							if(listaDeCuentas.get(i).getSaldo() < ((CuentaAhorro) listaDeCuentas.get(i)).getCostoMantenimiento() ){
								motivo = "El monto de la cuenta es insuficiente.";
								listaErroresMantenimiento.add(getDescripcion(listaDeCuentas.get(i).getCbu(),
																			 listaDeCuentas.get(i).getSaldo(),
															  				 motivo));
							}else{
								listaDeCuentas.get(i).setSaldo( listaDeCuentas.get(i).getSaldo() - ( (CuentaAhorro) listaDeCuentas.get(i)).getCostoMantenimiento() ) ;
								aux = aux + ( ((CuentaAhorro)listaDeCuentas.get(i)).getCostoMantenimiento() );
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
				}catch(Exception e){
				}
			}
		cobrarMantenimiento(aux);
		generaTxtOk(listaMantenimientosCobrados);
		generaTxtErr(listaErroresMantenimiento);
		
	}
	private void generaTxtOk(ArrayList<String> arrayListMant) throws ExcepcionIOBatch{
		
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
			escribir.write("----------------------------------- Fin de Mantenimientos -----------------------------------\r\n");
			escribir.close();
		}catch (IOException e) {
			throw new ExcepcionIOBatch();
		}
	}
	private void generaTxtErr(ArrayList<String> arrayListErrores) throws ExcepcionIOBatch{
		
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
			escribir.write("----------------------------------- Fin de Mantenimientos ------------------------------------\r\n");
			escribir.close();
		}catch (IOException e) {
			throw new ExcepcionIOBatch();
		}
	}
	
	private String getDescripcion(String cbu, double saldo, String motivo){
		return "CBU: "+cbu+", Tipo de cuenta: Caja de Ahorro"+", Monto: "+saldo+", Motivo: "+motivo;
	}
	private String getDescripcion(String cbu, double saldo, Moneda nominacion){
		return "CBU: "+cbu+", Tipo de cuenta: Caja de Ahorro"+", Monto: "+saldo+", Moneda: "+nominacion+", Tipo de Cambio: "+this.cotizacionDolar;
	}
	private void cobrarMantenimiento(double monto){
		this.mantenimientos.setSaldo(monto); 
	}
	private String getFechaActual() {
        Date dia = new Date();
        SimpleDateFormat formatoDia = new SimpleDateFormat("yyyy-MM-dd");
        return formatoDia.format(dia);
    }
}