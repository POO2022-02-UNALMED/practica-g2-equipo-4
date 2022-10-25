/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorAplicacion.servicios;

import java.util.ArrayList;
import java.io.Serializable;



public class Reserva implements Serializable {
    //private static final long serialVersionUID = 1L;
    protected int idReserva;
    private String fecha; /*La fecha tiene el formato dd-mm-aaa*/
    private boolean activa= false;
    protected Tarjeta tarjeta;

    public Reserva(String fecha, int idReserva){
        this.idReserva = idReserva;
        this.fecha = fecha;
        this.activa = true;
        //boolean r = Registro.agregarReserva(this);
        //boolean c = Registro.existeCliente(this.idReserva);
        //if(r){
            //return "Reserva exitosa";
        //}
        //else if(c){
            //return "El cliente ya tiene una reserva";
        //}
        //else{
            //return "El cliente no existe";
        //}
    
    
    }

    public static void setReservas(ArrayList<Reserva> readObject) {
    }

    /*
    public String generarReserva(String fecha){
        this.fecha = fecha;
        this.activa = true;
        boolean r = Registro.agregarReserva(this);
        boolean c = Registro.existeCliente(this.idReserva);
        if(r){
            return "Reserva exitosa";
        }
        else {if(c){
            return "El cliente ya tiene una reserva";
        }
        else{
            return "El cliente no existe";
        }

        }
    }
    */
    public String cancelarReserva(){
        boolean r= Registro.existeReserva(this.idReserva);
        if(r){
            this.activa = false;
            this.setFecha(null);
            Registro.eliminarReserva(this.idReserva);
            return "Reserva cancelada";
        }
        else{
            return "La reserva no existe";
        }
    }
    public String modificarReserva(String nufecha){
        boolean r= Registro.existeReserva(this.idReserva);
        if(r){
            Reserva obj = Registro.buscarReserva(this.idReserva).reserva;
            obj.activa = true;
            obj.setFecha(nufecha);
            return "Reserva modificada";
        }
        else{
            return "La reserva no existe";
        }
    }

    public String getFecha(){
        return fecha;
    }
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    public void setActiva(){
        activa = true;
    }
    public boolean getActiva(){
        return activa;
    }
    public void setDesactiva(){
        activa = false;
    }
    
	/*//no se. L
    //private static final long serialVersionUID = 1L;
    private static ArrayList<Reserva> reservas = new ArrayList<>();

    //atributos basicos. L
    protected int idReserva;
    protected Cliente cliente; considero que la reserva no tiene un cliente ya que el cliente es el que posee
    una reserva
    protected String tipoTiquete; Tiquete ya no existe
      reservaEstado: 0 = pendiente, 1 = concretada/pagada y convierte a ticket, 2 = cancelada. L

    private int estado = 0;

    //no se. L
    public Reserva(){
        reservas.add(this);
    }

    duplicado de generar reserva? o que es. L
    public static void setReservas(ArrayList<Reserva> res){
        reservas = res;
    }

    public static ArrayList<Reserva> getReservas(){
        return reservas;
    }

    //Metodos del genmymod. L

    	public void generar_reserva () {
    		//Falta concretar como hacerlo. L
    	}
        public void set_estado () {
        	this.estado = 2;
    	//falta borrarla del registro de reservas activas. L
    }
        public void modificar_cliente (Cliente clienteNuevo) {
        	this.cliente = clienteNuevo;
        }
        public String verificar_tipoId() { //no logré poner que fuera solo de pr
        	return this.cliente.tipoId;
        }
        public Cliente get_Cliente() {
        	return this.cliente;
        }
        public int consultarEstado() {
        	return this.estado;
        }*/

    @Override
    public String toString() {
        return "Reserva{" + "idReserva=" + idReserva + ", fecha=" + fecha + ", activa=" + activa + ", tarjeta=" + tarjeta + '}';
    }
}
