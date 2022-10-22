package gestorAplicacion.servicios;

import java.io.Serializable;

public class Cliente implements Serializable {
	
	//private static final long serialVersionUID = 1L;
	
	protected String tipoId;
	protected int id;
	protected Reserva reserva;

	public int getId() {
		return this.id;
	}
	public void corregir_Datos(String nueva_tipoId, int nueva_id) {
		this.tipoId = nueva_tipoId;
		this.id = nueva_id;
	}
	
	}


