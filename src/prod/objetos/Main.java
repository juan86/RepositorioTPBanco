package prod.objetos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import prod.excepciones.ExcepcionBatch;
import prod.excepciones.ExcepcionCPCliente;
import prod.excepciones.ExcepcionCuitCliente;
import prod.excepciones.ExcepcionDniCliente;
import prod.excepciones.ExcepcionFechaCliente;
import prod.excepciones.ExcepcionIOBatch;
import prod.excepciones.ExcepcionTelefonoCliente;
import prod.excepciones.ExcepcionTipDNICliente;


public class Main {
	static private Banco santander = new Banco();
	//BigInteger hola = new BigInteger("1");
	
	public static void main(String[] agr) throws IOException{
		comenzar();
	}
	
	static private void comenzar() throws IOException{
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		int select = -1;
		do{
			try{
				System.out.println("----------BIENVENIDOS AL BANCO FAFAFA!!-----------\n\n"+
						"1. Gesti�n de Clientes.\n"+
						"2. Gestion de Cuentas.\n"+
						"3. Operaciones por Ventanilla.\n"+
						"4. Proceso Batch.\n"+
						"0. Finalizar Programa.");
				System.out.print("Ingrese una Opcion Valida: ");
				//Espera que ingresemos una opcion
				select = Integer.parseInt(io.readLine());
				System.out.println("");
				//Dependiendo de la opcion ingresada ejecuta un metodo
				switch (select){
					case 1:
						select = menuGestionClientes();
						break;
					case 2:
						select = menuGestionCuentas();
						break;
					case 3:
						select = menuGestionClientes();
						break;
					case 4:
						select = menuProcesoBatch();
						break;
					case 100:
						break;
					case 0:
						System.out.println("PROGRAMA FINALIZADO...");
						break;
					default:
						System.out.println("Opcion Desconocida");
						break;
				}
			}catch(NumberFormatException e){
				System.out.println(e.getMessage()+", No se puede leer esa Opci�n");
			}
			catch(Exception e){
				System.out.println("Error desconocido");
			}
		}while(select !=0);
	}
	//******************************************************* CLIENTES *******************************************************
	static private int menuGestionClientes(){
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		int select = -1;
		do{
			try{
				System.out.println("------------GESTION DE CLIENTES--------------\n\n"+
						"1. Alta de Cliente F�sico.\n"+
						"2. Alta de Cliente Jur�dico.\n"+
						"3. Busqueda de cliente por Cuit.\n"+
						"4. Busqueda de cliente por DNI.\n"+
						"5. Busqueda de cliente por Nombre.\n"+
						"6. Deshabilitar Cliente.\n"+
						"7. Habilitar cliente.\n"+
						"0. Volver al Menu Anterior.");
				System.out.print("Ingrese una Opcion Valida: ");
				//Espera que ingresemos una opcion
				select = Integer.parseInt(io.readLine());
				//Dependiendo de la opcion ingresada ejecuta un metodo
				switch (select){
					case 1:
						altaClienteFisico();
						System.out.println("Presione cualquier tecla ENTER para contiuaar...");
						io.readLine();
						break;
					case 2:
						altaClienteJuridico();
						System.out.println("Presione cualquier tecla ENTER para contiuaar...");
						io.readLine();
						break;
					case 3:
						busquedaDeClientePorCuit();
						System.out.println("Presione cualquier tecla ENTER para contiuaar...");
						io.readLine();
						break;
					case 4:
						busquedaDeClientePorDni();
						System.out.println("Presione cualquier tecla ENTER para contiuaar...");
						io.readLine();
						break;
					case 5:
						busquedaDeClientePorNombre();
						System.out.println("Presione cualquier tecla ENTER para contiuaar...");
						io.readLine();
						break;
					case 6:
						habilitarCliente();
						System.out.println("Presione cualquier tecla ENTER para contiuaar...");
						io.readLine();
						break;
					case 7:
						deshabilitarCliente();
						System.out.println("Presione cualquier tecla ENTER para contiuaar...");
						io.readLine();
						break;
					case 10:
						break;
					case 0:
						break;
					default:
						System.out.println("Opcion Desconocida");
						break;
				}
				System.out.println("");
			}catch(NumberFormatException e){
				System.out.println("La Letras no son una opcion");
			}
			catch(Exception e){
				System.out.println("Error desconocido");
			}
		}while(select !=0);
		select = 100;
		return select;
	}
	static private  void altaClienteFisico(){
		String nombre,cuit,direccion,localidad,provincia,profesion,nombreConyuge,cp,telefono,numeroDocumento;
		TipDocumento tipDocumento;
		EnumCivil estadoCivil;
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		try{
			System.out.println("Ingrese los datos Del Cliente Fisico");
			System.out.println("Nombre: (ej: 'Juan'):");
			nombre = io.readLine().toUpperCase();
			System.out.println("Cuit: (ej: '20324878379')");
			cuit = io.readLine();
			if(cuit.length()!=11 || !esNumero(cuit)){
				throw new  ExcepcionCuitCliente(cuit);
			}
			System.out.println("Domicilio: (ej: Av. Eva Per�n 4321)");
			direccion = io.readLine().toUpperCase();
			System.out.println("Codigo Postal: (ej: 1650):");
			cp = io.readLine();
			if(cp.length() != 4 || !esNumero(cp)){
				throw new  ExcepcionCPCliente(cp);
			}
			System.out.println("Localidad: (ej 'San Martin')");
			localidad = io.readLine().toUpperCase();
			System.out.println("Provincia: (ej 'Buenos Aires')");
			provincia = io.readLine().toUpperCase();
			System.out.println("Telefono: (ej 1555647477)");
			telefono = io.readLine();
			if(!esNumero(telefono)){
				throw new  ExcepcionTelefonoCliente(telefono);
			}
			System.out.println("Ingrese 1 para DNI o 2 para Pasaporte')");
			String valor = io.readLine();
			if(valor.length() != 1 || !esNumero(valor)){
				throw new ExcepcionTipDNICliente();
			}else if(valor.equals("1")){
				tipDocumento = TipDocumento.DNI;
			}else if(valor.equals("2")){
				tipDocumento = TipDocumento.PASAPORTE;
			}else{
				System.out.println("no funciona el equal");
				throw new ExcepcionTipDNICliente();
			}
			System.out.println("Numero de Documento: (ej 32487837)");
			String entrada = io.readLine();
			if(entrada.length()!=8 || !esNumero(entrada)){
				System.err.println("Documento no valido");
				throw new ExcepcionDniCliente(entrada);
			}else{
				numeroDocumento = entrada;
			}
			System.out.println("Ingrese (1) para SOLTERO o  (2) CASADO)");
			valor = io.readLine();
			if(valor.length() != 1 || !esNumero(valor)){
				throw new ExcepcionTipDNICliente();
			}else if(Integer.parseInt(valor) == 1){
				estadoCivil = EnumCivil.SOLTERO;
			}else if(Integer.parseInt(valor) == 2){
				estadoCivil = EnumCivil.CASADO;
			}else{
				throw new ExcepcionTipDNICliente();
			}
			System.out.println("Profesion: (ej 'Carpintero')");
			profesion = io.readLine().toUpperCase();
			System.out.println("Nombre Conyuge (ej 'Bruja')");
			nombreConyuge=io.readLine().toUpperCase();
			if(estadoCivil == EnumCivil.SOLTERO){
				nombreConyuge = "Estado Civil Soltero";
			}
			santander.altaClienteFisico(nombre, cuit, direccion, cp, localidad, provincia, telefono, tipDocumento, numeroDocumento, estadoCivil, profesion, nombreConyuge);
			System.out.println("Se dio de alta al Cliente con el CUIT: "+cuit);
		
		}catch(ExcepcionCPCliente e){
			System.out.println(e);
		}catch(ExcepcionCuitCliente e){
			System.out.println(e);
		}catch(ExcepcionTelefonoCliente e){
			System.out.println(e);
		}catch(ExcepcionTipDNICliente e){
			System.out.println(e);
		}catch(ExcepcionDniCliente e){
			System.out.println(e);
		}catch(NumberFormatException e){
			System.out.println("Los datos Ingresados del cliente son Incorrectos");
		}catch(IOException e){
			System.out.println("Error de ingreso de datos");
		}
	}
	static private void altaClienteJuridico(){
		String nombre,cuit,direccion,localidad,provincia,fechaContrato = null,cp,telefono;
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		try{
			System.out.println("Ingrese los datos Del Cliente Fisico");
			System.out.println("Nombre: (ej: 'Juan'):");
			nombre = io.readLine().toUpperCase();
			System.out.println("Cuit: (ej: '20324878379')");
			cuit = io.readLine();
			if(cuit.length()!=11 || !esNumero(cuit)){
				System.err.println("Numero de Cuit mal cargado");
				throw new  NumberFormatException();
			}
			System.out.println("Domicilio: (ej: Av. Eva Per�n 4321)");
			direccion = io.readLine().toUpperCase();
			System.out.println("Codigo Postal: (ej: 1650):");
			cp = io.readLine();
			System.out.println("Localidad: (ej 'San Martin')");
			localidad = io.readLine().toUpperCase();
			System.out.println("Provincia: (ej 'Buenos Aires')");
			provincia = io.readLine().toUpperCase();
			System.out.println("Telefono: (ej 1555647477)");
			telefono =io.readLine();
			System.out.println("Ingrese la fecha de contrato");
			fechaContrato = io.readLine();
			if(!validarFecha(fechaContrato)){
				System.err.println("Ingrese una fecha valida");
				throw new NumberFormatException();
			}
			santander.altaClienteJuridico(nombre, cuit, direccion, cp, localidad, provincia, telefono, fechaContrato);
			System.out.println("Se ah dado de alta al Cliente");
		}catch(NumberFormatException | IOException e){
			System.out.println("Los datos Ingresados del cliente son Incorrectos");
		}
	}
	static private void busquedaDeClientePorCuit(){
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		String cuit = null;
		try{
			System.out.println("Ingrese el Numero de Cuit de un cliente Registrado:");
			cuit = io.readLine();
			santander.buscarClientePorCuit(cuit);
		}catch(IOException e){
			System.out.println("Error de Ingreso de datos");
		}
		catch(NumberFormatException e){
			System.out.println("No se ha Ingresado un formato correcto de Cuit");
		}
	}
	static private void busquedaDeClientePorDni(){
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		String dni;
		try{
			System.out.println("Ingrese el Numero de Cuit de un cliente Registrado:");
			dni = io.readLine();
			santander.buscarClientesPorDni(dni);
		}catch(IOException e){
			System.out.println("Error de Ingreso de datos");
		}
		catch(NumberFormatException e){
			System.out.println("El valor Ingresado no es un DNI");
		}
	}
	static private void busquedaDeClientePorNombre(){
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		String nombre = null;
		try{
			System.out.println("Ingrese el Numero de Cuit de un cliente Registrado:");
			nombre = io.readLine();
			santander.buscarClientesPorNombre(nombre);
		}catch(IOException e){
			System.out.println("Error de Ingreso de datos");
		}
		catch(NumberFormatException e){
			System.out.println("No se ha Ingresado un nombre");
		}
	}
	static private void habilitarCliente(){
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		String cuit = null;
		try{
			System.out.println("Ingrese el Numero de Cuit de un Cliente Deshabilitado:");
			cuit = io.readLine();
			santander.bajaCliente(cuit);
		}catch(IOException e){
			System.out.println("Error de Ingreso de datos");
		}
		catch(NumberFormatException e){
			System.out.println("No se ha Ingresado un nombre");
		}
	}
	static private void deshabilitarCliente(){
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		String cuit = null;
		try{
			System.out.println("Ingrese el Numero de Cuit de un Cliente Habilitado:");
			cuit = io.readLine();
			santander.activarCliente(cuit);
			
		}catch(IOException e){
			System.out.println("Error de Ingreso de datos");
		}
		catch(NumberFormatException e){
			System.out.println("No se ha Ingresado un nombre");
		}
	}
	//******************************************************* CUENTAS *******************************************************
	static private int menuGestionCuentas(){
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		int select = -1;
		do{
			try{
				System.out.println("------------GESTION DE CUENTAS--------------\n\n"+
						"1. Alta de Caja de Ahorro.\n"+
						"0. Volver al Menu Anterior.");
				System.out.print("Ingrese una Opcion Valida: ");
				//Espera que ingresemos una opcion
				select = Integer.parseInt(io.readLine());
				//Dependiendo de la opcion ingresada ejecuta un metodo
				switch (select){
					case 1:
						altaCajaDeAhorro();
						System.out.println("Presione cualquier tecla ENTER para contiuaar...");
						io.readLine();
						break;
					case 10:
						break;
					case 0:
						break;
					default:
						System.out.println("Opcion Desconocida");
						break;
				}
				System.out.println("");
			}catch(NumberFormatException e){
				System.out.println("La Letras no son una opcion");
			}
			catch(Exception e){
				System.out.println("Error desconocido");
			}
		}while(select !=0);
		select = 100;
		return select;
	}
	static private <thrown> void altaCajaDeAhorro() throws IOException{
		
		String cuit;
		double montoInicial,tasaDeInteres;
		Moneda nominacion = Moneda.pesos;
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		try{
			System.out.println("Ingrese cuit");
			System.out.println("Cuit: (ej: '336673519'):");
			cuit = io.readLine().toUpperCase();
			System.out.println("Monto: (ej: 10000)");
			montoInicial = Double.parseDouble(io.readLine());
			System.out.println("Tasa: ");
			tasaDeInteres = Double.parseDouble(io.readLine());
			System.out.println("Codigo Postal: (ej: 1650):");
			
			santander.aperturaDeCuentaAhorro(cuit, montoInicial, nominacion, tasaDeInteres);
			
			System.out.println("Se dio de alta al Cliente con el CUIT: "+cuit);
		}catch(NumberFormatException e){
			System.out.println("Los datos Ingresados del cliente son Incorrectos");
		}
	}	
	//************************************************************** BATCH **************************************************************
	static private int menuProcesoBatch(){
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		int select = -1;
		do{
			try{
				System.out.println("------------PROCESO BATCH--------------\n\n"+
						"1. Ejecutar Mantenimiento.\n"+
						"0. Volver al Menu Anterior.");
				System.out.print("Ingrese una Opcion Valida: ");
				//Espera que ingresemos una opcion
				select = Integer.parseInt(io.readLine());
				//Dependiendo de la opcion ingresada ejecuta un metodo
				switch (select){
					case 1:
						ejecutarMantenimiento();
						System.out.println("Presione cualquier tecla ENTER para contiuaar...");
						io.readLine();
						break;
					case 10:
						break;
					case 0:
						break;
					default:
						System.out.println("Opcion Desconocida");
						break;
				}
				System.out.println("");
			}catch(NumberFormatException e){
				System.out.println("La Letras no son una opcion");
			}
			catch(Exception e){
				System.out.println("Error desconocido");
			}
		}while(select !=0);
		select = 100;
		return select;
	}
	static private void ejecutarMantenimiento(){
			try {
				santander.correrBatch();
			} catch (ExcepcionIOBatch | ExcepcionBatch e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	static private boolean esNumero(String cbu){
		boolean esNumero = false;
		int m = 1;
		try{
			for(int i = 0; i<cbu.length();i++){
				Integer.parseInt(cbu.substring(i,m));
				esNumero = true;
				m++;
			} 
		}catch (NumberFormatException nfe){	
			return esNumero = false;
		}
			return esNumero;
	}
	static private boolean validarFecha(String fecha){
		try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
	}
}
		
		//santander.altaClienteFisico(nombre, cuit, direccion, cp, localidad, provincia, telefono, tipDNI, numeroDocumento, estadoCivil, profesion, nombreConyuge);("Juan Mercado","20324878379","av. Ev Peron 4321","1650","San Martin","Buenos Aires","1555647477","DNI",32487837,"Soltero","vago", "ninguno");
		/*
		santander.altaClienteFisico("Juan Mercado","20324878369","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487836,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteFisico("Juan Merc","20324878359","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487835,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteFisico("Juan Mer","20324878349","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487834,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteFisico("Juan Me","20324878339","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487833,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteFisico("Juan M","20324878329","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487832,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteFisico("Juan Mercado","20324878319","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487831,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteFisico("Juan Marcad","20324878309","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487830,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteFisico("Juan Marca","20324878299","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487829,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteFisico("Juan Marc","20324878289","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487828,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteFisico("Juan Mar","20324878279","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487827,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteFisico("Juan M","20324878269","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487826,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteFisico("Juan","20324878259","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,TipDocumento.DNI,32487825,EnumCivil.SOLTERO,"vago", "ninguno");
		santander.altaClienteJuridico("Juan Perez","20324878249","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,"23-05-1986");
		santander.altaClienteJuridico("Juan Pere","20324878239","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,"23-05-1986");
		santander.altaClienteJuridico("Juan Nada","20324878229","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,"23-05-1986");
		santander.altaClienteJuridico("Juan Mas","20324878219","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,"23-05-1986");
		santander.altaClienteJuridico("Juan Mercado","20324878209","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,"23-05-1986");
		santander.altaClienteJuridico("Juan Distinto","20324878199","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,"23-05-1986");
		santander.altaClienteJuridico("Juan Igual","20324878189","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,"23-05-1986");
		santander.altaClienteJuridico("Juan Marcas","20324878179","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,"23-05-1986");
		santander.altaClienteJuridico("Juan Marcs","20324878169","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,"23-05-1986");
		santander.altaClienteJuridico("Juan Mard","20324878159","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,"23-05-1986");
		santander.altaClienteJuridico("Juan Mes","20324878149","av. Ev Peron 4321",1650,"San Martin","Buenos Aires",1555647477,"23-05-1986");
		santander.buscarClientePorCuit("20324878369");
		santander.buscarClientesPorNombre("Juan Mercado");
		santander.buscarClientesPorDni(32487836);
		santander.cambiarCodigoPostalCliente("20324878369", 1653);
		santander.cambiarLocalidadCliente("20324878369","Villa Ballester");
		santander.cambiarTelefonoCliente("20324878369",1566123948);
		santander.buscarClientePorCuit("20324878369");
		*/


