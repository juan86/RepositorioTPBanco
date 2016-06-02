package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import prod.objetos.PersonaJuridica;

public class PersonaJuridicaTest {
	
	PersonaJuridica personaJuridicaTest;
	private String nombre = "Pablo Camdessanche";
	private String cuit = "20336673519";
	private String direccion = "Bonifacini 4237";
	private String cp = "1684";
	private String localidad = "Caseros";
	private String provincia = "Buenos Aires";
	private String telefono = "1531544980";
	private String fechaContrato = "23/02/2016";

	@Before
	public void setUp() throws Exception {
		personaJuridicaTest = new PersonaJuridica(nombre
												 ,cuit
												 ,direccion
												 ,cp
												 ,localidad
												 ,provincia
												 ,telefono
												 ,fechaContrato);
	}
	@Test
	public void getFechaContratoTest() {
		assertEquals(fechaContrato, personaJuridicaTest.getFechaContrato());
	}
	@Test
	public void toStringTest(){
		String mostrar = null;
		mostrar = "(Nombre Completo): "+nombre+"\n"+
				"(CUIT): "+cuit+"\n"+
				"(Domicilio): "+direccion+","+cp+","+localidad+","+provincia+"\n"+
				"(Telefono): "+telefono+"\n"+
				"(Fecha Contrato): "+fechaContrato+"\n"+
				"(Habilitado): "+personaJuridicaTest.getActivo()+"\n"+
				"(Cuentas Asociadas): "+personaJuridicaTest.getCuentasToString();
		
		assertEquals(mostrar, personaJuridicaTest.toString());
	}
	@Test
	public void laFechaNoPuedenSerSoloNumeros(){
		String nuevaFecha = "23022016";
		try {
			PersonaJuridica personaJuridicaTest = new PersonaJuridica(nombre
																	 ,cuit
																	 ,direccion
																	 ,cp
																	 ,localidad
																	 ,provincia
																	 ,telefono
																	 ,nuevaFecha);
			System.out.print("OK");
		}catch(Exception e){
			System.out.print("FechaContrato invalido: "+nuevaFecha);
		}
	}
	@Test
	public void laFechaNoPuedeTenerOtroFormato(){
		String nuevaFecha = "23+02+2016";
		try {
			PersonaJuridica personaJuridicaTest = new PersonaJuridica(nombre
																	 ,cuit
																	 ,direccion
																	 ,cp
																	 ,localidad
																	 ,provincia
																	 ,telefono
																	 ,nuevaFecha);
			System.out.print("OK");
		}catch(Exception e){
			System.out.print("FechaContrato invalido: "+nuevaFecha);
		}
	}
}
