package gestorAplicacion.servicios;

import java.io.Serializable;

public class Cliente implements Serializable {
	
	//private static final long serialVersionUID = 1L;
	
	protected String tipoId;
	protected int id;
        protected int edad;
        protected Tarjeta tarjeta;
	protected Reserva reserva;

    public Cliente(String tipoId, int id, int edad) {
        this.tipoId = tipoId;
        this.id = id;
        this.edad = edad;
    }
        
        public int getEdad() {
            return this.edad;
        }
        
	public int getId() {
            return this.id;
	}
        
        public String getTipoId() {
            return this.tipoId;
	}
        
	public void corregir_Datos(String nueva_tipoId, int nueva_id) {
            this.tipoId = nueva_tipoId;
            this.id = nueva_id;
	}

    @Override
    public String toString() {
        return "Cliente{" + "tipoId=" + tipoId + ", id=" + id + ", edad=" + edad + ", reserva=" + reserva + '}';
    }

    
        
    
	
}


