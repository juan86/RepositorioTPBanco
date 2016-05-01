package prod.objetos;

import prod.ExcepcionesVentanilla;
import prod.MiLanzador;
import prod.objetos.CuentaRetenciones;

public class OperacionesPorVentanilla {
	
	
	private double dolarHoyCompra =  14.15;
	MiLanzador ventanilla = new MiLanzador();
	
				//*************************	DEPOSITOVENTANILLA****************************************************************									
				//************************************************************************************************************
	/**
	 * 
	 * @param cuenta
	 * @param monto
	 * @param tipoMoneda
	 * @param tipoM
	 * @param mot
	 */
	
	public void depositoVentanilla(Cuenta cuenta, double monto, Moneda tipoMoneda, String tipoM, String mot)
	{
	
		try{	
				// Verifica Las excepciones
				ventanilla.excepDeposito(cuenta, monto, tipoMoneda);  
				
				if(cuenta instanceof CuentaAhorro) 
				{	
					cuenta.acreditar(monto);
					Transaccion nuevaTransaccion = new Transaccion( tipoM,  monto,  mot);
					 cuenta.agregarTransaccion(nuevaTransaccion);
					 System.out.println("La trasaccion se realizo corretctamente" );
				
				}
				
				if(cuenta instanceof CuentaCorriente)
				{			
					cuenta.acreditar(monto);
					Transaccion nuevaTransaccion = new Transaccion( tipoM,  monto,  mot);
					cuenta.agregarTransaccion(nuevaTransaccion);
					System.out.println("La trasaccion se realizo corretctamente" );
							
				} 
			
		
		
			}
	
		catch(ExcepcionesVentanilla ex)
		{
				System.out.println("No se puede validar debido a" + ex.getMessage());
			
		}
		
		
	
}
	
			
			//*************************	EXTRACCIONCAJAAHORRO********************************************************************									
			//******************************************************************************************************************
	
		/**
		 * 							
		 * @param solicitante
		 * @param suCuenta
		 * @param monto
		 * @param Motivo
		 * @param mot
		 * @param tipoM
		 */
	public void extraccionCajaAhorro(Cliente solicitante, Cuenta suCuenta, double monto, String mot,
									String tipoM)
{
		
		try{
				
			
				ventanilla.excepcionesExtraccion(solicitante, suCuenta, monto);	
				suCuenta.debitar(monto);
				Transaccion nueveTransaccion = new Transaccion( tipoM,  monto,  mot);
				suCuenta.agregarTransaccion(nueveTransaccion);
				System.out.println("La trasaccion se realizo corretctamente" );
				
			
			
		}
		catch(ExcepcionesVentanilla ex)
		{
			
				System.out.println("La operacion no se realizo debido a " + ex.getMessage());
			
		}
	
}
	
//************************************	TRANSFERENCIAS A CUENTA DE DIFERENTE NOMINACION*************************************									
//*************************************************************************************************************************

	/**
	 * 
	 * @param solicitante
	 * @param origen
	 * @param destino
	 * @param retenciones
	 * @param monto
	 * @param Motivo
	 * @param mot
	 * @param tipoM
	 * @param observacion
	 */
	
	public void transferenciasDiferenteNominacion(Cliente solicitante,Cuenta origen, Cuenta destino, CuentaRetenciones retenciones, double monto,
								String mot, String tipoM,String observacion)
{
		
		try{		
					
					
					ventanilla.excepcionesTransferenciasDiferenteNominacion(origen, destino, monto);
						
					//**************************CUENTA AHORRO A CUENTA AHORRO****************************
						//***********************************************************************************
					
					if( origen  instanceof CuentaAhorro && destino instanceof CuentaAhorro)
					{
						//*******************SE REALIZA LA CONVERSION DE PESO A DOLAR******************
						if(origen.getMoneda() == Moneda.pesos && destino.getMoneda() == Moneda.dolares)
						{
							monto=(monto)/this.dolarHoyCompra;
							origen.debitar(monto);
							if(origen.getSaldo()>=0)
							{
							destino.acreditar(monto);
							Transaccion nuevaTransaccionDolar = new Transaccion( tipoM,  monto,  mot,observacion);
							origen.agregarTransaccion(nuevaTransaccionDolar);
							destino.agregarTransaccion(nuevaTransaccionDolar);
							System.out.println("La trasaccion se realizo corretctamente" );
							}
							
							else
							{
								origen.acreditar(monto);
								System.out.println("La trasaccion 'no' se realizo corretctamente el saldo no le alcanza" );

							}
							
						}
						//************************SE REALIZA LA CONVERSION A PESOS**************************
						if(origen.getMoneda() == Moneda.dolares && destino.getMoneda() == Moneda.pesos)
						{
							monto = monto*this.dolarHoyCompra;
							origen.debitar(monto);
							
							if(origen.getSaldo()>=0)
							{
							destino.acreditar(monto);
							Transaccion nuevaTransaccion = new Transaccion( tipoM,  monto,  mot, observacion);
							origen.agregarTransaccion(nuevaTransaccion); 
							destino.agregarTransaccion(nuevaTransaccion);
							System.out.println("La trasaccion se realizo corretctamente" );

							}
							else
							{
								origen.acreditar(monto);
								System.out.println("La trasaccion 'no' se realizo corretctamente el saldo no le alcanza" );

							}
					
						}
						
					}
					
				
					
						//*************************CUENTA CORRIENTE A CUENTA CORRIENTE****************************
						//***************************************************************************************
					
				if(origen instanceof CuentaCorriente && destino instanceof CuentaCorriente) 
				{	
					
						if(origen.getMoneda() == Moneda.pesos && destino.getMoneda() == Moneda.dolares)
						{	
							
							CuentaCorriente cuentaAux =(CuentaCorriente)origen;
							monto=(monto)/this.dolarHoyCompra;
							
							origen.debitar(monto+((monto*cuentaAux.getSobreGiro())/100));//SE LE PASA A COBRAR LA COMISION AL MISMO MOMENTO DE DEBITAR
							
							//Ver si al momento de realizar el debito el monto es mayor al sobre giro: Sobregiro debe ser Negativo 
							if(cuentaAux.getSaldo()>= cuentaAux.getSobreGiro())
							{
								
								destino.acreditar(monto-((monto*cuentaAux.getSobreGiro())/100));
								retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//suma retencion de la de origen
								retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//Suma la retencion de la de destino
								Transaccion nuevaTransaccionDolar = new Transaccion( tipoM,  monto,  mot,observacion);
								origen.agregarTransaccion(nuevaTransaccionDolar);
								retenciones.agregarTransaccion(nuevaTransaccionDolar);
								destino.agregarTransaccion(nuevaTransaccionDolar);
								retenciones.agregarTransaccion(nuevaTransaccionDolar);
								System.out.println("La trasaccion se realizo corretctamente" );

							}
							else
							{
								origen.acreditar(monto+((monto*cuentaAux.getSobreGiro())/100));
								System.out.println("La trasaccion'no' se realizo corretctamente el saldo no le alcanza" );

							}
	
	
						}
						
						if(origen.getMoneda() == Moneda.dolares && destino.getMoneda() == Moneda.pesos)
						{
							monto = monto*this.dolarHoyCompra;
							CuentaCorriente cuentaAux = (CuentaCorriente)origen;
							origen.debitar(monto+((monto*cuentaAux.getSobreGiro())/100));
							 
							
							if(cuentaAux.getSaldo()>= cuentaAux.getSobreGiro()) // EL SOBREGIRO DEBE SER NEGATIVO
							{
								destino.acreditar(monto-((monto*cuentaAux.getSobreGiro())/100));
								retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//suma retencion de la de origen
								retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//Suma la retencion de la de destino
								Transaccion nuevaTransaccionDolar = new Transaccion(  tipoM,  monto,  mot,observacion);
								origen.agregarTransaccion(nuevaTransaccionDolar);
								retenciones.agregarTransaccion(nuevaTransaccionDolar);
								destino.agregarTransaccion(nuevaTransaccionDolar);
								retenciones.agregarTransaccion(nuevaTransaccionDolar);
								System.out.println("La trasaccion se realizo corretctamente" );

							}
							else
							{
								origen.acreditar(monto+((monto*cuentaAux.getSobreGiro())/100));
								System.out.println("La trasaccion 'no' se realizo corretctamente el saldo no le alcanza" );

							}
						}
				}
						//***************************CUENTA AHORRO A CUENTA CORRIENTE**********************
						//********************************************************************************
				
				if(origen instanceof CuentaAhorro && destino instanceof CuentaCorriente)
				{
					
					if(origen.getMoneda() == Moneda.pesos && destino.getMoneda() == Moneda.dolares)
					{	
						
						monto=(monto)/this.dolarHoyCompra;
						origen.debitar(monto);
						//Ver si al momento de realizar el debito el monto es mayor 0
						if(origen.getSaldo()>= 0)
						{
							CuentaCorriente cuentaAux = (CuentaCorriente)destino;
							destino.acreditar(monto-((monto*cuentaAux.getSobreGiro())/100));
							retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//Suma la retencion de la de destino
							Transaccion nuevaTransaccionDolar = new Transaccion( tipoM,  monto,  mot,observacion);
							origen.agregarTransaccion(nuevaTransaccionDolar);
							retenciones.agregarTransaccion(nuevaTransaccionDolar);
							destino.agregarTransaccion(nuevaTransaccionDolar);
							retenciones.agregarTransaccion(nuevaTransaccionDolar);
							System.out.println("La trasaccion se realizo corretctamente el saldo no le alcanza" );

						}
						else 
						{
							origen.acreditar(monto);
							System.out.println("La trasaccion 'no' se realizo corretctamente el saldo no le alcanza" );

						}
					
					}
					
					if(origen.getMoneda() == Moneda.dolares && destino.getMoneda() == Moneda.pesos)
					{
						monto = monto*this.dolarHoyCompra;
						origen.debitar(monto);
						
						
						if(origen.getSaldo()>=0) // EL SOBREGIRO DEBE SER NEGATIVO
						{	
							CuentaCorriente cuentaAux = (CuentaCorriente)destino;
							destino.acreditar(monto-((monto*cuentaAux.getSobreGiro())/100));
							retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//Suma la retencion de la de destino
							Transaccion nuevaTransaccionDolar = new Transaccion(  tipoM,  monto,  mot,observacion);
							origen.agregarTransaccion(nuevaTransaccionDolar);
							retenciones.agregarTransaccion(nuevaTransaccionDolar);
							destino.agregarTransaccion(nuevaTransaccionDolar);
							retenciones.agregarTransaccion(nuevaTransaccionDolar);
							System.out.println("La trasaccion se realizo corretctamente" );

						}
						else
						{
							origen.acreditar(monto);
							System.out.println("La trasaccion 'no' se realizo corretctamente el saldo no le alcanza" );

						}
						
					
					}
				//****************************CUENTA CORRIENTE A CUENTA AHORRO**********************************
				//*********************************************************************************************	
				if(origen instanceof CuentaCorriente && destino instanceof CuentaAhorro){
					
					if(origen.getMoneda() == Moneda.pesos && destino.getMoneda() == Moneda.dolares)
					{	
						
						monto=(monto)/this.dolarHoyCompra;
						CuentaCorriente cuentaAux = (CuentaCorriente)origen;
						origen.debitar(monto+((monto*cuentaAux.getSobreGiro())/100));//SE LE COBRA LA COMISION POR LA TRANSFERENCIA
						 //SE CASTE LA CUENTA ORIGEN PARA PODER UTILIZAR EL ATRIBUTO SOBREGIRO
						
						if(cuentaAux.getSaldo() >= cuentaAux.getSobreGiro())//EL SOBREGIRO DEBE SER NEGATIVO
						{
							retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//suma retencion de la de origen
							destino.acreditar(monto);
							Transaccion nuevaTransaccionDolar = new Transaccion(  tipoM,  monto,  mot,observacion);
							origen.agregarTransaccion(nuevaTransaccionDolar);
							retenciones.agregarTransaccion(nuevaTransaccionDolar);
							destino.agregarTransaccion(nuevaTransaccionDolar);
							System.out.println("La trasaccion se realizo corretctamente" );

							
						}
						else
						{
						origen.acreditar((monto*cuentaAux.getSobreGiro())/100);
						System.out.println("La trasaccion 'no' se realizo corretctamente el saldo no le alcanza" );

						}
						
					}
					
					if(origen.getMoneda() == Moneda.dolares && destino.getMoneda() == Moneda.pesos)
					{
						monto = monto*this.dolarHoyCompra;
						CuentaCorriente cuentaAux = (CuentaCorriente)origen;
						origen.debitar(monto-(monto*cuentaAux.getSobreGiro())/100);
						
						
						if(cuentaAux.getSaldo() >= cuentaAux.getSobreGiro()) // EL SOBREGIRO DEBE SER NEGATIVO
						{
							retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//suma retencion de la de origen
							destino.acreditar(monto);
							Transaccion nuevaTransaccionDolar = new Transaccion(   tipoM,  monto,  mot,observacion);
							origen.agregarTransaccion(nuevaTransaccionDolar);
							retenciones.agregarTransaccion(nuevaTransaccionDolar);
							destino.agregarTransaccion(nuevaTransaccionDolar);
							System.out.println("La trasaccion se realizo corretctamente " );

						}
						
						
						else
						{
							origen.acreditar(monto +(monto*cuentaAux.getSobreGiro())/100);
							System.out.println("La trasaccion 'no'se realizo corretctamente el saldo no le alcanza" );

						}
					}
			}
		}
	}
		
		catch(ExcepcionesVentanilla ex)
		{
			System.out.println("La operacion no se realizo debido a "+ ex.getMessage());
		}
		
		
}
	
	
	//*************************	TRANSFERENCIAS A CUENTA DE LA MISMA NOMINACION*****************************************									
	//*******************************************************************************************************************
/**
 * 
 * @param solicitante
 * @param origen
 * @param retenciones
 * @param destino
 * @param monto
 * @param Motivo
 * @param mot
 * @param tipoM
 */
	
public void transferenciasmMismaNominacion(Cliente solicitante,Cuenta origen,CuentaRetenciones retenciones, Cuenta destino, double monto,String Motivo,String mot, String tipoM)
{
		try{	
			
			ventanilla.excepcionestransferenciasmMismaNominacion(origen, destino, monto);
			
			//**************************CUENTA AHORRO A CUENTA AHORRO****************************
			//***********************************************************************************
		
		if( origen  instanceof CuentaAhorro && destino instanceof CuentaAhorro)
		{
			
				origen.debitar(monto);
				if(origen.getSaldo()>=0)
				{
				destino.acreditar(monto);
				Transaccion nuevaTransaccionDolar = new Transaccion(  tipoM,  monto,  mot);
				origen.agregarTransaccion(nuevaTransaccionDolar);
				destino.agregarTransaccion(nuevaTransaccionDolar);
				System.out.println("La trasaccion se realizo corretctamente " );
				}
				else{
					origen.acreditar(monto);
					System.out.println("La trasaccion 'no'se realizo corretctamente el saldo no le alcanza" );
				}
		
		}
		
	// Las comiciones las realiza La clase cuenta corriente
		
			//*************************CUENTA CORRIENTE A CUENTA CORRIENTE****************************
			//***************************************************************************************
		
	if(origen instanceof CuentaCorriente && destino instanceof CuentaCorriente) 
	{	
		
			
				
				CuentaCorriente cuentaAux =(CuentaCorriente)origen;
				origen.debitar(monto+((monto*cuentaAux.getSobreGiro())/100));//SE LE PASA A COBRAR LA COMISION AL MISMO MOMENTO DE DEBITAR
				
				//Ver si al momento de realizar el debito el monto es mayor al sobre giro: Sobregiro debe ser Negativo 
				if(origen.getSaldo()>= cuentaAux.getSobreGiro())
				{
					
					destino.acreditar(monto-((monto*cuentaAux.getSobreGiro())/100));
					retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//suma retencion de la de origen
					retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//Suma la retencion de la de destino
					Transaccion nuevaTransaccionDolar = new Transaccion(  tipoM,  monto,  mot);
					origen.agregarTransaccion(nuevaTransaccionDolar);
					retenciones.agregarTransaccion(nuevaTransaccionDolar);
					destino.agregarTransaccion(nuevaTransaccionDolar);
					retenciones.agregarTransaccion(nuevaTransaccionDolar);
					System.out.println("La trasaccion se realizo corretctamente " );
				}
				
				else
				{
					origen.acreditar(monto+(monto*cuentaAux.getSobreGiro())/100);
					System.out.println("La trasaccion 'no' se realizo corretctamente su saldo no es suficiente " );
				}


			
			
			
	}
			//***************************CUENTA AHORRO A CUENTA CORRIENTE**********************
			//********************************************************************************
	
	if(origen instanceof CuentaAhorro && destino instanceof CuentaCorriente)
	{
		
		
			origen.debitar(monto);
			//Ver si al momento de realizar el debito el monto es mayor 0
			if(origen.getSaldo()>= 0)
			{
				CuentaCorriente cuentaAux = (CuentaCorriente)destino;
				destino.acreditar(monto-((monto*cuentaAux.getSobreGiro())/100));
				retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//Suma la retencion de la de destino
				Transaccion nuevaTransaccionDolar = new Transaccion(   tipoM,  monto,  mot);
				origen.agregarTransaccion(nuevaTransaccionDolar);
				destino.agregarTransaccion(nuevaTransaccionDolar);
				retenciones.agregarTransaccion(nuevaTransaccionDolar);
				System.out.println("La trasaccion se realizo corretctamente " );
			}
			
			else
			{
			origen.acreditar(monto);
			System.out.println("La trasaccion 'no' se realizo corretctamente su saldo no le alcanza" );
			}
		
		
		
		
	}
			//****************************CUENTA CORRIENTE A CUENTA AHORRO**********************************
			//***********************************************************************************************
	if(origen instanceof CuentaCorriente && destino instanceof CuentaAhorro)
		{
			
			
				CuentaCorriente cuentaAux = (CuentaCorriente)origen;
				origen.debitar(monto+((monto*cuentaAux.getSobreGiro())/100));//SE LE COBRA LA COMISION POR LA TRANSFERENCIA
				 //SE CASTE LA CUENTA ORIGEN PARA PODER UTILIZAR EL ATRIBUTO SOBREGIRO
				
				if(cuentaAux.getSaldo() >= cuentaAux.getSobreGiro())//EL SOBREGIRO DEBE SER NEGATIVO
				{
					
					destino.acreditar(monto);
					retenciones.sumarComicion((monto*cuentaAux.getSobreGiro())/100);//suma retencion de la de origen
					Transaccion nuevaTransaccionDolar = new Transaccion(  tipoM,  monto,  mot);
					origen.agregarTransaccion(nuevaTransaccionDolar);
					retenciones.agregarTransaccion(nuevaTransaccionDolar);
					destino.agregarTransaccion(nuevaTransaccionDolar);
					System.out.println("La trasaccion se realizo corretctamente " );
				}
				 
				else{
				origen.acreditar((monto*cuentaAux.getSobreGiro())/100);
				System.out.println("La trasaccion 'no'  se realizo corretctamente su saldo no le alcanza " );
				}
				
			
				
		}
		
	}
	
		catch(ExcepcionesVentanilla ex){
			System.out.println("La operacion no se realizo debido a: " + ex.getMessage());
		}
		
		
}
	
	
	
	
	
	//*************************	LISTARMOVIMIENTOS**********************************************************************									
	//*******************************************************************************************************************
/**
 * 
 * @param solicitante
 */
	
	public void listarMovimientos(Cuenta solicitante)
	
	{
		
		solicitante.imprimirTodasTransaccion();
		
		
	}
	
	//*************************	LISTAR"N"MOVIMIENTOS**********************************************************************									
	//*******************************************************************************************************************
	
	/**
	 * 
	 * @param solicitante
	 * @param cantidadMovimiento
	 */
	public void listarNMoivimientos(Cuenta solicitante, int cantidadMovimiento)
	{
		solicitante.ultimasNTransaccion(cantidadMovimiento);
	}
	
	
	
	



















}

