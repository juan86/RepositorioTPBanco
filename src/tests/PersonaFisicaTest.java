package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import prod.excepciones.ExcepcionCliente;
import prod.objetos.EnumCivil;
import prod.objetos.PersonaFisica;
import prod.objetos.TipDocumento;

public class PersonaFisicaTest {

	PersonaFisica personaFisicaTest;
	private String nombre = "Pablo Camdessanche";
	private String cuit = "20336673519";
	private String direccion = "Bonifacini 4237";
	private String cp = "1684";
	private String localidad = "Caseros";
	private String provincia = "Buenos Aires";
	private String telefono = "1531544980";
	private TipDocumento tipDni = TipDocumento.DNI;
	private String numeroDocumento = "33667351";
	private EnumCivil estadoCivil = EnumCivil.SOLTERO;
	private String profesion = "Carpintero";
	private String nombreConyuge = "Bruja";
	
	@Before
	public void setUp() throws Exception {
		personaFisicaTest = new PersonaFisica(nombre
											 ,cuit
											 ,direccion
											 ,cp
											 ,localidad
											 ,provincia
											 ,telefono
											 ,tipDni
											 ,numeroDocumento
											 ,estadoCivil
											 ,profesion
											 ,nombreConyuge);
	}

	@Test
	public void getTipoDniTest() {
		assertEquals(tipDni, personaFisicaTest.getTipDNI());
	}
	@Test
	public void getNumeroDocumentoTest(){
		assertEquals(numeroDocumento, personaFisicaTest.getNumeroDocumento());
	}
	@Test
	public void getEstadoCivilTest(){
		assertEquals(estadoCivil, personaFisicaTest.getEstadoCivil());
	}
	@Test
	public void getProfesionTest(){
		assertEquals(profesion, personaFisicaTest.getProfesion());
	}
	@Test
	public void getNombreConyugeTest(){
		assertEquals(nombreConyuge, personaFisicaTest.getNombreConyuge());
	}
	@Test
	public void toStringTest(){
		String mostrar;
		mostrar = "(Nombre Completo): "+nombre+"\n"+
				"(CUIT): "+cuit+"\n"+
				"(Domicilio): "+direccion+","+cp+","+localidad+","+provincia+"\n"+
				"(Telefono): "+telefono+"\n"+
				"(Tipo DNI/Pasaporte): "+tipDni+"\n"+
				"(Numero): "+numeroDocumento+"\n"+
				"(Estado Civil): "+estadoCivil+"\n"+
				"(Profesion): "+profesion+"\n"+
				"(Nom y Ape Cónyuge): "+nombreConyuge+"\n"+
				"(Habilitado): "+personaFisicaTest.getActivo()+"\n"+
				"(Cuentas Asociadas): "+personaFisicaTest.getCuentasToString();
		
		assertEquals(mostrar, personaFisicaTest.toString());
	}
	@Test
	public void setEstadoCivilTest(){
		EnumCivil nuevoEstadoCivil = EnumCivil.CASADO;
		personaFisicaTest.setEstadoCivil(nuevoEstadoCivil);
		
		assertEquals(nuevoEstadoCivil, personaFisicaTest.getEstadoCivil());
	}
	@Test
	public void setProfesionTest(){
		String nuevaProfesion = "Ingeniero";
		personaFisicaTest.setProfesion(nuevaProfesion);
		
		assertEquals(nuevaProfesion, personaFisicaTest.getProfesion());
		
	}
	@Test
	public void setNombreConyugeTest(){
		String nuevoNombreConyuge = "Nueva Bruja";
		personaFisicaTest.setNombreConyuge(nuevoNombreConyuge);
		
		assertEquals(nuevoNombreConyuge, personaFisicaTest.getNombreConyuge());
	}
	@Test
	public void elDniNoPuedeTenerMenosDe8Caracteres(){
		String nuevoDni = "1234567";
		try{
			PersonaFisica nuevaPersonaFisica = new PersonaFisica(nombre
																,cuit
																,direccion
																,cp
																,localidad
																,provincia
																,telefono
																,tipDni
																,nuevoDni
																,estadoCivil
																,profesion
																,nombreConyuge);
			System.out.print("OK");
		}catch(Exception e){
			System.out.print("Dni invalido: "+nuevoDni); // ESTA BIEN ESTO????
		}
	}
	@Test
	public void elDniNoPuedoTenerMasDe8Caracteres(){
		String nuevoDni = "1234567890";
		try{
			PersonaFisica nuevaPersonaFisica = new PersonaFisica(nombre
																,cuit
																,direccion
																,cp
																,localidad
																,provincia
																,telefono
																,tipDni
																,nuevoDni
																,estadoCivil
																,profesion
																,nombreConyuge);
			System.out.print("OK");
		}catch(Exception e){
			System.out.print("Dni invalido: "+nuevoDni); // ESTA BIEN ESTO????
		}
	}
	@Test
	public void elDniPuedeSerNegativoCon8Digitos(){
		String nuevoDni = "-1234567";
		try{
			PersonaFisica nuevaPersonaFisica = new PersonaFisica(nombre
																,cuit
																,direccion
																,cp
																,localidad
																,provincia
																,telefono
																,tipDni
																,nuevoDni
																,estadoCivil
																,profesion
																,nombreConyuge);
			System.out.print("OK");
		}catch(Exception e){
			System.out.print("Dni invalido: "+nuevoDni); // ERROR
		}
	}
	@Test
	public void elDniPuedeSerDistintoAlCuit(){
		String nuevoDni = "12345678";
		try{
			PersonaFisica nuevaPersonaFisica = new PersonaFisica(nombre
																,cuit
																,direccion
																,cp
																,localidad
																,provincia
																,telefono
																,tipDni
																,nuevoDni
																,estadoCivil
																,profesion
																,nombreConyuge);
			System.out.print("OK");
		}catch(Exception e){
			System.out.print("Dni invalido: "+nuevoDni); // ESTA BIEN ESTO????
		}
	}
	@Test
	public void elDniNoPuedenSerLetras(){
		String nuevoDni = "ABCDEFGH";
		try{
			PersonaFisica nuevaPersonaFisica = new PersonaFisica(nombre
																,cuit
																,direccion
																,cp
																,localidad
																,provincia
																,telefono
																,tipDni
																,nuevoDni
																,estadoCivil
																,profesion
																,nombreConyuge);
			System.out.print("OK");
		}catch(Exception e){
			System.out.print("Dni invalido: "+nuevoDni); // ESTA BIEN ESTO????
		}
	}
}
