package prod.objetos;
import java.text.SimpleDateFormat;
import java.util.Date;

class Transaccion {
	
	private TipoMovimiento movimiento;
	private double monto;
	private String motivo;
	private String observaciones = null;
	private String fechaYHora = this.setFechaYHora();
	
	public Transaccion(TipoMovimiento movimientoIngresado, double monto, String motivo){
		
		this.movimiento = movimientoIngresado;
		this.monto = monto;
		this.motivo = motivo;
	}
	public Transaccion(TipoMovimiento movimientoIngresado, double monto, String motivo, String observaciones){
		
		this.movimiento = movimientoIngresado;
		this.monto = monto;
		this.motivo = motivo;
		this.observaciones = observaciones;
	}
	
	public TipoMovimiento getTipoMovimiento(){
		return this.movimiento;
	}
	
	public double getMonto(){
		return this.monto;
	}
	
	private String setFechaYHora() {
        Date dia = new Date();
        Date hora = new Date();
        SimpleDateFormat formatoDia = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");
        return formatoDia.format(dia)+" - "+formatoHora.format(hora);
    }
	
	private String getFechaYHora() {
		return this.fechaYHora;
	}
	public String toString(){
		
		if(this.observaciones != null){
			return "Fecha y Hora: "+getFechaYHora()+
					", Tipo De Movimiento: "+this.movimiento+
					", Monto: "+this.monto+
					", Motivo: "+this.motivo+
					", Observaciones: "+this.observaciones;
		}else{
			return "Fecha y Hora: "+getFechaYHora()+
					", Tipo De Movimiento: "+this.movimiento+
					", Monto: "+this.monto+
					", Motivo: "+this.motivo;
		}
	}
}