package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import prod.excepciones.ExcepcionAltaCliente;
import prod.objetos.EnumCivil;
import prod.objetos.PersonaFisica;
import prod.objetos.TipDocumento;

public class ClienteTest{
	
	PersonaFisica personaFisicaTest;
	private String nombre = "Pablo Camdessanche";
	private String cuit = "20336673519";
	private String direccion = "Bonifacini 4237";
	private String cp = "1684";
	private String localidad = "Caseros";
	private String provincia = "Buenos Aires";
	private String telefono = "1531544980";
	private TipDocumento tipDNI = TipDocumento.DNI;
	private String numeroDocumento = "33667351";
	private EnumCivil estadoCivil = EnumCivil.SOLTERO;
	private String profesion = "Carpintero";
	private String nombreConyuge = "Bruja";
	
	@Before
	public void setUp() throws Exception{
		personaFisicaTest = new PersonaFisica(nombre
											 ,cuit
											 ,direccion
											 ,cp
											 ,localidad
											 ,provincia
											 ,telefono
											 ,tipDNI
											 ,numeroDocumento
											 ,estadoCivil
											 ,profesion
											 ,nombreConyuge);
	}
	@Test
	public void getNombreTest(){
		assertEquals(nombre, personaFisicaTest.getNombre());
	}
	@Test
	public void getCuitTest(){
		assertEquals(cuit, personaFisicaTest.getCuit());
	}
	@Test
	public void getDomicilioTest(){
		assertEquals(direccion+","+cp+","+localidad+","+provincia
					,personaFisicaTest.getDomicilio());
	}
	@Test
	public void getTelefonoTest(){
		assertEquals(telefono, personaFisicaTest.getTelefono());
	}
//	@Test
//	public void getCuentasTest(){
//		assertEquals(cuenta, personaFisicaTest.getCuentas());
//		//FALTA IMPLEMENTACION  - VERIFICAR
//	}
	@Test
	public void getEstadoTest(){
		assertTrue(personaFisicaTest.getActivo());
	}
	@Test
	public void setDireccionTest(){
		String nuevaDireccion = "Gamarra 13";
		
		personaFisicaTest.setDireccion(nuevaDireccion);
		
		assertEquals(nuevaDireccion+","+cp+","+localidad+","+provincia
					,personaFisicaTest.getDomicilio());
		//FALTARIA GETDIRECCION - VERIFICAR
	}
	@Test
	public void setCodigoPostalTest() throws Exception{
		String nuevoCp = "1234";
		personaFisicaTest.setCp(nuevoCp);

		assertEquals(direccion+","+nuevoCp+","+localidad+","+provincia
					,personaFisicaTest.getDomicilio());
		// ASI DEBERIA SER EL DE VALIDAR TELEFONO?  - VERIFICAR
	}
	@Test
	public void setLocalidadTest(){
		String nuevaLocalidad = "Palomar";
		personaFisicaTest.setLocalidad(nuevaLocalidad);
		
		assertEquals(direccion+","+cp+","+nuevaLocalidad+","+provincia
					,personaFisicaTest.getDomicilio());
		//FALTARIA GETLOCALIDAD - VERIFICAR
	}
	@Test
	public void setProvinciaTest(){
		String nuevaProvincia = "Bs As";
		personaFisicaTest.setProvincia(nuevaProvincia);
		
		assertEquals(direccion+","+cp+","+localidad+","+nuevaProvincia
					,personaFisicaTest.getDomicilio());
		//FALTARIA GETPROVINCIA - VERIFICAR
	}
	@Test
	public void setTelefonoTest() throws ExcepcionAltaCliente{
		String nuevoTelefono = "15000-00000";
		
		personaFisicaTest.setTelefono(nuevoTelefono);
		//EL SET NO VALIDA EL STRING DE TELEFONO COMO EN CODIGO POSTAL - VERIFICAR
	}
//	@Test
//	public void setCuentaTest(){
//		String nuevoCbu = "987654321";
//		personaFisicaTest.setCuenta(nuevoCbu);
//		//FALTA IMPLEMENTACION  - VERIFICAR
//	}
	@Test
	public void setEstadoTest(){
		boolean nuevoEstado = false;
		personaFisicaTest.setActivo(nuevoEstado);
		
		assertFalse(personaFisicaTest.getActivo());
	}
//	@Test
//	public void getCuentasToStringTest(){
//		//FALTA IMPLEMENTACION
//	}
//	@Test
//	public void contieneCbuTest(){
//		//FALTA IMPLEMENTACION
//	}
	//---------------------------------------------------- CUIT
	@Test
	public void cuitValidoConOnceNumero(){
		String nuevoCuit = "20336673519";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,nuevoCuit
															,direccion
															,cp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cuit invalido: "+nuevoCuit);
		}
	}
	@Test
	public void cuitInvalidoConNumeroNegativo(){
		String nuevoCuit = "-20336673519";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,nuevoCuit
															,direccion
															,cp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cuit invalido: "+nuevoCuit);
		}
	}
	@Test
	public void cuitInvalidoConLetraYNumeros(){
		String nuevoCuit = "A20336673519";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,nuevoCuit
															,direccion
															,cp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cuit invalido: "+nuevoCuit);
		}
	}
	@Test
	public void cuitInvalidoConDoceCaracteres(){
		String nuevoCuit = "203366735191";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,nuevoCuit
															,direccion
															,cp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cuit invalido: "+nuevoCuit);
		}
	}
	@Test
	public void cuitInvalidoConMenosDeOnceCaracteres(){
		String nuevoCuit = "2033667351";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,nuevoCuit
															,direccion
															,cp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cuit invalido: "+nuevoCuit);
		}
	}
	@Test
	public void elCuitNoPuedenSerLetras(){
		String nuevoCuit = "ABCDEFGHIJK";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,nuevoCuit
															,direccion
															,cp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cuit invalido: "+nuevoCuit);
		}
	}
	//---------------------------------------------------- CODIGO POSTAL
	@Test
	public void cpValidoConCuatroCaracteres(){
		String nuevoCp = "1684";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,cuit
															,direccion
															,nuevoCp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cp invalido: "+nuevoCp);
		}
	}
	@Test
	public void cpInvalidoConNumeroNegativo(){
		String nuevoCp = "-1684";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,cuit
															,direccion
															,nuevoCp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cp invalido: "+nuevoCp); // ERROR
		}
	}
	@Test
	public void cpInvalidoConLetraYNumeros(){
		String nuevoCp = "A168";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,cuit
															,direccion
															,nuevoCp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cp invalido: "+nuevoCp);
		}
	}
	@Test
	public void cpInvalidoConMasDeCuatroCaracteres(){
		String nuevoCp = "12345";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,cuit
															,direccion
															,nuevoCp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cp invalido: "+nuevoCp); // ERROR
		}
	}
	@Test
	public void cpInvalidoConMenosDeCuatroCaracteres(){
		String nuevoCp = "1";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,cuit
															,direccion
															,nuevoCp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cp invalido: "+nuevoCp); // ERROR
		}
	}
	@Test
	public void cpInvalidoConLetras(){
		String nuevoCp = "ABCD";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,cuit
															,direccion
															,nuevoCp
															,localidad
															,provincia
															,telefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Cp invalido: "+nuevoCp);
		}
	}
	//---------------------------------------------------- TELEFONO
	@Test
	public void telefonoValidoConNumeros(){
		String nuevoTelefono = "1531544980";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,cuit
															,direccion
															,cp
															,localidad
															,provincia
															,nuevoTelefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Telefono invalido: "+nuevoTelefono);
		}
	}
	@Test
	public void telefonoInvalidoConGuion(){
		String nuevoTelefono = "15-3154-4980";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,cuit
															,direccion
															,cp
															,localidad
															,provincia
															,nuevoTelefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Telefono invalido: "+nuevoTelefono);
		}
	}
	@Test
	public void telefonoInvalidoConLetras(){
		String nuevoTelefono = "ABCDEFGH";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,cuit
															,direccion
															,cp
															,localidad
															,provincia
															,nuevoTelefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Telefono invalido: "+nuevoTelefono);
		}
	}
	@Test
	public void elTelefonoNoPuedeTenerMasDeDiezCaracteres(){
		String nuevoTelefono = "12345678901";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,cuit
															,direccion
															,cp
															,localidad
															,provincia
															,nuevoTelefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Telefono invalido: "+nuevoTelefono);
		}
	}
	@Test
	public void elTelefonoPuedeTenerOchoCaracteres(){
		String nuevoTelefono = "12345678";
		try{
			PersonaFisica personaFisica = new PersonaFisica(nombre
															,cuit
															,direccion
															,cp
															,localidad
															,provincia
															,nuevoTelefono
															,tipDNI
															,numeroDocumento
															,estadoCivil
															,profesion
															,nombreConyuge);
			System.out.print("Ok");
		}catch(Exception e){
			System.out.print("Telefono invalido: "+nuevoTelefono);
		}
	}
}
