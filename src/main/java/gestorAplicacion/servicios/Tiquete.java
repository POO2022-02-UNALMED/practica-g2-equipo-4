package gestorAplicacion.servicios;

import java.io.Serializable;


public class Tiquete extends Reserva implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private boolean incluyeCajitaInfantil;
    private boolean incluyeCombo;
    private int idTiquete;
    private int fecha;
    private final float costo = 100;

    public Tiquete(boolean incluyeCajitaInfantil, boolean incluyeCombo, int idTiquete, int fecha, int idReserva, Cliente cliente, String tipoTiquete) {
        super(idReserva, cliente, tipoTiquete);
        this.incluyeCajitaInfantil = incluyeCajitaInfantil;
        this.incluyeCombo = incluyeCombo;
        this.idTiquete = idTiquete;
        this.fecha = fecha;
    }

    public void setCliente(Cliente cliente){
        Reserva.modificar_cliente(cliente);
    }
    
    //getters setters
    
    public boolean isIncluyeCajitaInfantil() {
        return incluyeCajitaInfantil;
    }

    public void setIncluyeCajitaInfantil(boolean incluyeCajitaInfantil) {
        this.incluyeCajitaInfantil = incluyeCajitaInfantil;
    }

    public boolean isIncluyeCombo() {
        return incluyeCombo;
    }

    public void setIncluyeCombo(boolean incluyeCombo) {
        this.incluyeCombo = incluyeCombo;
    }

    public int getIdTiquete() {
        return idTiquete;
    }

    public void setIdTiquete(int idTiquete) {
        this.idTiquete = idTiquete;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }
    
    
}
    