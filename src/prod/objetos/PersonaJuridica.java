package prod.objetos;

class PersonaJuridica extends Cliente{
	private String fechaContrato;
	/**
	 * @param nombre
	 * @param cuit
	 * @param direccion
	 * @param cp
	 * @param localidad
	 * @param provincia
	 * @param telefono
	 * @param fechaContrato
	 */
	public PersonaJuridica(String nombre,String cuit,String direccion,int cp,String localidad,String provincia,String telefono,
			String fechaContrato){
		super(nombre,cuit,direccion,cp,localidad,provincia,telefono);
		this.fechaContrato = fechaContrato;
	}
	//GET------------------------------------
	public String getFechaContrato(){
		return this.fechaContrato;
	}
	public String toString(){
		String mostrar=null;
		mostrar = "(Nombre Completo): "+super.getNombre()+"\n"+
				"(CUIT): "+super.getCuit()+"\n"+
				"(Domicilio): "+super.getDomicilio()+"\n"+
				"(Telefono): "+super.getTelefono()+"\n"+
				"(Fecha Contrato): "+this.getFechaContrato()+"\n"+
				"(Habilitado): "+super.getActivo()+"\n"+
				"(Cuentas Asociadas): "+super.getCuentasToString();
		return mostrar;
	}
}
