package prod.objetos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Main {
	static private Banco santander = new Banco();
	BigInteger hola = new BigInteger("1");
	
	public static void main(String[] agr) throws IOException{
		comenzar();
	}
	
	static private void comenzar() throws IOException{
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		int select = -1;
		do{
			try{
				System.out.println("----------BIENVENIDOS AL BANCO FAFAFA!!-----------\n\n"+
						"1. GestiÛn de Clientes.\n"+
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
						select = menuGestionClientes();
						break;
					case 3:
						select = menuGestionClientes();
						break;
					case 4:
						select = menuGestionClientes();
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
				System.out.println(e.getMessage()+", No se puede leer esa OpciÛn");
			}
			catch(Exception e){
				System.out.println("Error desconocido");
			}
		}while(select !=0);
	}
	static private int menuGestionClientes(){
		BufferedReader io = new BufferedReader (new InputStreamReader(System.in));
		int select = -1;
		do{
			try{
				System.out.println("------------GESTION DE CLIENTES--------------\n\n"+
						"1. Alta de Cliente F˙êico.\n"+
						"2. Alta de Cliente Jur˙Åico.\n"+
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
	static private <thrown> void altaClienteFisico() throws IOException{
		String nombre,cuit,direccion,localidad,provincia,profesion,nombreConyuge;
		int cp,telefono,tipDNI,estadoCivil,numeroDocumento;
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
			System.out.println("Domicilio: (ej: Av. Eva PerÛn 4321)");
			direccion = io.readLine().toUpperCase();
			System.out.println("Codigo Postal: (ej: 1650):");
			cp = Integer.parseInt(io.readLine());
			System.out.println("Localidad: (ej 'San Martin')");
			localidad = io.readLine().toUpperCase();
			System.out.println("Provincia: (ej 'Buenos Aires')");
			provincia = io.readLine().toUpperCase();
			System.out.println("Telefono: (ej 1555647477)");
			telefono = Integer.parseInt(io.readLine());
			System.out.println("Tipo de Documento: (ej '1.DNI / 2.Pasaporte')");
			tipDNI = Integer.parseInt(io.readLine());
			if(tipDNI == 1){				
			}else if(tipDNI ==2){				
			}else{
				System.err.println("Error tipo DNI, debe ingresar la opcion 1 o 2");
				throw new NumberFormatException();
			}
			System.out.println("Numero de Documento: (ej 32487837)");
			String entrada = io.readLine();
			if(entrada.length()!=8 || !esNumero(entrada)){
				System.err.println("Documento no valido");
				throw new NumberFormatException();
			}else{
				numeroDocumento = Integer.parseInt(entrada);
			}
			System.out.println("Estado Civil (ej '1.SOLTERO / 2.CASADO')");
			estadoCivil = Integer.parseInt(io.readLine());
			if(estadoCivil == 1){				
			}else if(estadoCivil ==2){				
			}else{
				System.err.println("Error Estadi Civil, debe ingresar la opcion 1 o 2");
				throw new NumberFormatException();
			}
			System.out.println("Profesion: (ej 'Carpintero')");
			profesion = io.readLine().toUpperCase();
			System.out.println("Nombre Conyuge (ej 'Bruja')");
			nombreConyuge=io.readLine().toUpperCase();
			if(tipDNI == 1 && estadoCivil == 1){
				santander.altaClienteFisico(nombre, cuit, direccion, cp, localidad, provincia, telefono, TipDocumento.DNI, numeroDocumento, EnumCivil.SOLTERO, profesion, nombreConyuge);
			}else{
				santander.altaClienteFisico(nombre, cuit, direccion, cp, localidad, provincia, telefono, TipDocumento.PASAPORTE, numeroDocumento, EnumCivil.CASADO, profesion, nombreConyuge);
			}
			System.out.println("Se dio de alta al Cliente con el CUIT: "+cuit);
		}catch(NumberFormatException e){
			System.out.println("Los datos Ingresados del cliente son Incorrectos");
		}
	}
	static private void altaClienteJuridico(){
		String nombre,cuit,direccion,localidad,provincia,fechaContrato = null;
		int cp,telefono;
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
			System.out.println("Domicilio: (ej: Av. Eva PerÛn 4321)");
			direccion = io.readLine().toUpperCase();
			System.out.println("Codigo Postal: (ej: 1650):");
			cp = Integer.parseInt(io.readLine());
			System.out.println("Localidad: (ej 'San Martin')");
			localidad = io.readLine().toUpperCase();
			System.out.println("Provincia: (ej 'Buenos Aires')");
			provincia = io.readLine().toUpperCase();
			System.out.println("Telefono: (ej 1555647477)");
			telefono = Integer.parseInt(io.readLine());
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
	static private void busquedaDeClientePorCuit() throws IOException{
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
		int dni =0;
		try{
			System.out.println("Ingrese el Numero de Cuit de un cliente Registrado:");
			dni = Integer.parseInt(io.readLine());
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
			esNumero = false;
		}finally{
			return esNumero;
		}
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


