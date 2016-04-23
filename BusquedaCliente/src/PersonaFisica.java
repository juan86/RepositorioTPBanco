
class PersonaFisica extends Cliente{
	private String tipDNI;
	private int numeroDocumento;
	private String estadoCivil;
	private String profesion;
	private String nombreConyuge;
	
	public PersonaFisica(String nombre,String cuit,String direccion,String cp,String localidad,String provincia,String telefono,
			String tipDNI,int numeroDocumento, String estadoCivil,String profesion,String nombreConyuge){
		super(nombre,cuit,direccion,cp,localidad,provincia,telefono);
		this.tipDNI = tipDNI;
		this.numeroDocumento = numeroDocumento;
		this.estadoCivil = estadoCivil;
		this.profesion = profesion;
		this.nombreConyuge = nombreConyuge;
	}
	//GET---------------------------------
	public String getTipDNI(){
		return this.tipDNI;
	}
	public int getNumeroDocumento(){
		return this.numeroDocumento;
	}
	public String getEstadoCivil(){
		return this.estadoCivil;
	}
	public String getProfesion(){
		return this.profesion;
	}
	public String getNombreConyuge(){
		return this.nombreConyuge;
	}
	//SET-------------------------------
	public void setEstadoCivil(String estadoCivil){
		this.estadoCivil = estadoCivil;
	}
	public void setProfesion(String profesion){
		this.profesion = profesion;
	}
	public void setNombreConyuge(String nombreConyuge){
		this.nombreConyuge = nombreConyuge;
	}
	//toString
	public String toString(){
		return "Nombre:"+super.getNombre()+", Tipo de Doc: "+this.numeroDocumento+", Cuenta: "+super.getCuentas();
	}
}
