package gestorAplicacion.servicios;

import java.io.Serializable;

public class Tarjeta implements Serializable {
	/*La clase tarjeta solo maneja temas de informacion que
	* van a ser utilizados para las restricciones de acceso a instalaiones*/
	
	//private static final long serialVersionUID = 1L;
	/*Constantes*/
	private final String[] tipoTarjeta = {"Adulto", "Infante"};
	private final float[] costoTarjeta = {100, 50};
	/*Atributos*/
	//private Reserva reserva;
	private int idTarjeta;
	//private int fecha;
	private float costo;
	private int tipoT;
	private boolean tarjetaFisica;
	private boolean activa = false; //Por defecto la tarjeta no esta activa hasta que se realice el pago de la misma

	public Tarjeta() {}
	public Tarjeta(int idTarjeta, float costo, int tipoTarjeta) {
		//this.reserva = reserva;
		this.idTarjeta = idTarjeta;
		//this.fecha = fecha;
		this.costo = costo;
		this.tipoT = tipoTarjeta;
	}
	/*public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}*/
	public int getIdTarjeta() {
		return idTarjeta;
	}
	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}
	/*public int getFecha() {
		return fecha;
	}
	public void setFecha(int fecha) {
		this.fecha = fecha;
	}*/
	public void asignarCosto() {
		this.costo = costoTarjeta[tipoT];
	}
	public float getCosto() {
		return costo;
	}
	public String getTipoTarjeta() {
		return tipoTarjeta[tipoT];
	}
	public boolean getActiva(){
		return activa;
	}
	public void setActiva(boolean activa){
		this.activa = activa;
	}
	public boolean getTarjetaFisica(){
		return tarjetaFisica;
	}
	public void setTarjetaFisica(boolean tarjetaFisica){
		this.tarjetaFisica = tarjetaFisica;
	}


}
