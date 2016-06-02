package prod.objetos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import prod.excepciones.ExcepcionCliente;
import prod.excepciones.ExcepcionFechaCliente;

public class PersonaJuridica extends Cliente{
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
	 * @throws ExcepcionCliente 
	 */
	public PersonaJuridica(String nombre,String cuit,String direccion,String cp,String localidad,String provincia,String telefono,
			String fechaContrato) throws ExcepcionCliente{
		super(nombre,cuit,direccion,cp,localidad,provincia,telefono);
		guardarFecha(fechaContrato);
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
	private void guardarFecha(String fecha) throws ExcepcionFechaCliente{
		if(validaFecha(fecha)){
			this.fechaContrato = fecha;
		}else{
			throw new ExcepcionFechaCliente(fecha);
		}
	}
	private boolean validaFecha(String fecha){
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
