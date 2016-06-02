package prod.objetos;

import java.util.IllegalFormatException;
import prod.excepciones.ExcepcionAltaCliente;
import prod.excepciones.ExcepcionCliente;
import prod.excepciones.ExcepcionDniCliente;

public class PersonaFisica extends Cliente{
	private TipDocumento tipDNI;
	private int numeroDocumento;
	private EnumCivil estadoCivil;
	private String profesion;
	private String nombreConyuge;
	/**
	 * @param nombre
	 * @param cuit
	 * @param direccion
	 * @param cp
	 * @param localidad
	 * @param provincia
	 * @param telefono
	 * @param tipDNI
	 * @param numeroDocumento
	 * @param estadoCivil
	 * @param profesion
	 * @param nombreConyuge
	 * @throws ExcepcionCliente 
	 */
	public PersonaFisica(String nombre,String cuit,String direccion,String cp,String localidad,String provincia,String telefono,
		TipDocumento tipDNI,String numeroDocumento, EnumCivil estadoCivil,String profesion,String nombreConyuge) throws ExcepcionCliente{
		super(nombre,cuit,direccion,cp,localidad,provincia,telefono);
		try{	
			this.tipDNI = tipDNI;
			validarNumeroDocumento(numeroDocumento);
			this.estadoCivil = estadoCivil;
			this.profesion = profesion;
			this.nombreConyuge = nombreConyuge;
		}catch(IllegalFormatException e){
			
		}
	}
	//GET---------------------------------
	public TipDocumento getTipDNI(){
		return this.tipDNI;
	}
	public String getNumeroDocumento(){
		return Integer.toString(this.numeroDocumento);
	}
	public EnumCivil getEstadoCivil(){
		return this.estadoCivil;
	}
	public String getProfesion(){
		return this.profesion;
	}
	public String getNombreConyuge(){
		return this.nombreConyuge;
	}
	public String toString(){
		String mostrar;
		mostrar = "(Nombre Completo): "+super.getNombre()+"\n"+
				"(CUIT): "+super.getCuit()+"\n"+
				"(Domicilio): "+super.getDomicilio()+"\n"+
				"(Telefono): "+super.getTelefono()+"\n"+
				"(Tipo DNI/Pasaporte): "+this.tipDNI+"\n"+
				"(Numero): "+this.numeroDocumento+"\n"+
				"(Estado Civil): "+this.getEstadoCivil()+"\n"+
				"(Profesion): "+this.getProfesion()+"\n"+
				"(Nom y Ape Cónyuge): "+this.getNombreConyuge()+"\n"+
				"(Habilitado): "+super.getActivo()+"\n"+
				"(Cuentas Asociadas): "+super.getCuentasToString();
		return mostrar;
	}
	//SET-------------------------------
	/**
	 * @param estadoCivil
	 */
	public void setEstadoCivil(EnumCivil estadoCivil){
		this.estadoCivil = estadoCivil;
	}
	/**
	 * @param profesion
	 */
	public void setProfesion(String profesion){
		this.profesion = profesion;
	}
	/**
	 * @param nombreConyuge
	 */
	public void setNombreConyuge(String nombreConyuge){
		this.nombreConyuge = nombreConyuge;
	}
	//---------------METODOS-------------------------
	private void validarNumeroDocumento(String documento) throws ExcepcionAltaCliente{
		int numero;
		try{
			numero = Integer.parseInt(documento);
			if(documento.length()== 8){
				this.numeroDocumento = numero;
			}else{
				throw new ExcepcionDniCliente(documento);
			}
		}catch(IllegalFormatException e){
			throw new ExcepcionDniCliente(documento);
		}catch(NumberFormatException e){
			throw new ExcepcionDniCliente(documento);
		}
	}
}
