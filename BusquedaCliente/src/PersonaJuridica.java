
class PersonaJuridica extends Cliente{
	private String fechaContrato;
	public PersonaJuridica(String nombre,String cuit,String direccion,String cp,String localidad,String provincia,String telefono,
			String fechaContrato){
		super(nombre,cuit,direccion,cp,localidad,provincia,telefono);
		this.fechaContrato = fechaContrato;
	}
	//GET------------------------------------
	public String getFechaContrato(){
		return this.fechaContrato;
	}
	
}
