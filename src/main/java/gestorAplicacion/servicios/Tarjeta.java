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
	//private Reserva reserva;//
	protected int idTarjeta;
	//private int fecha;
        private float costo;
	private int tipoT;
	private boolean tarjetaFisica;
	private boolean activa = false; //Por defecto la tarjeta no esta activa hasta que se realice el pago de la misma
        private int cantidadDeEntradas = 0;
        private float saldo = 0;
        
	public Tarjeta() {}
	public Tarjeta(int idTarjeta, int tipoTarjeta) {
		//this.reserva = reserva;
		this.idTarjeta = idTarjeta;
		//this.fecha = fecha;
		this.tipoT = tipoTarjeta;
                if(tipoTarjeta == 1){
                this.costo =100;
                }
                else{
                this.costo =50;
                }
                
                
        }      
                
        public void agregarEntrada(){
            cantidadDeEntradas ++;
            
            if (cantidadDeEntradas%3 == 0){
                System.out.println("Aplica para descuento") ;
            }
            else{
                int entradas =  cantidadDeEntradas%3;
                System.out.println("Faltan" + entradas + "entradas para tener descuento");
            }
        }
                
        public boolean agregarSaldo(float saldo){
            this.saldo += saldo;
            return true;
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
		this.saldo = costoTarjeta[tipoT];
	}
	public float getSaldo() {
		return saldo;
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


        
        
        //
        
}
