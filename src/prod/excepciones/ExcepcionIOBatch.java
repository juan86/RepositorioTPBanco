package prod.excepciones;

import java.io.IOException;

public class ExcepcionIOBatch extends Exception {
	public ExcepcionIOBatch(){
		super("Error al escribir documento Batch");
	}
}
