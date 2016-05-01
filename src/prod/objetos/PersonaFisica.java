package prod.objetos;

class PersonaFisica extends Cliente{
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
	 */
	public PersonaFisica(String nombre,String cuit,String direccion,int cp,String localidad,String provincia,String telefono,
			TipDocumento tipDNI,int numeroDocumento, EnumCivil estadoCivil,String profesion,String nombreConyuge){
		super(nombre,cuit,direccion,cp,localidad,provincia,telefono);
		this.tipDNI = tipDNI;
		this.numeroDocumento = numeroDocumento;
		this.estadoCivil = estadoCivil;
		this.profesion = profesion;
		this.nombreConyuge = nombreConyuge;
	}
	//GET---------------------------------
	public TipDocumento getTipDNI(){
		return this.tipDNI;
	}
	public int getNumeroDocumento(){
		return this.numeroDocumento;
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
}
