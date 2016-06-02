package prod.objetos;

import prod.excepciones.ExcepcionCuenta;
import prod.excepciones.ExcepcionCuentaInactiva;
import prod.excepciones.ExcepcionCuentaIncoherente;

class CuentaRetenciones extends CuentaEspecial{
	
	
	/**
	 * 
	 * @param cbuIngresado
	 * @param nominacionIngresada
	 */
	CuentaRetenciones (String cbuIngresado, Moneda nominacionIngresada) {
		
		super(cbuIngresado,nominacionIngresada);
		super.getSaldo();
		
		
		
	}
	/**
	 * this.retenciones = this.retenciones+ retencion;
	 * @param retencion
	 */
	
	public void sumarComicion(double retencion) throws ExcepcionCuenta
	{
		
		double sumaMovimientos = 0.0;
		
		if(!super.getEstado())
		{
			throw new ExcepcionCuentaInactiva("La cuenta esta inactiva y no admite operaciones");
		
		} 
		
		else
		{
			for(int i=0; i < this.historicoMov.size() ; i++){
				if(historicoMov.get(i).getTipoMovimiento() == TipoMovimiento.credito){
					sumaMovimientos += historicoMov.get(i).getMonto();
				}
				sumaMovimientos -= historicoMov.get(i).getMonto();
			}
			if(sumaMovimientos != this.getSaldo()){
				throw new ExcepcionCuentaIncoherente("Error de coherencia entre los movimientos y el saldo.");
			}
		}
		
		this.setSaldo(this.getSaldo() + retencion);
		
	
	}
	
	public double retencionesAcumuladas()
	{
		return this.getSaldo();
	}
	


}
