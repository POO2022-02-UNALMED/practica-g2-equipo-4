/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorAplicacion.servicios;

import java.util.ArrayList;
import java.io.Serializable;

// no se. L
/**
 *
 * @author USUARIO
 */


public class Reserva implements Serializable {
    
	//no se. L
    private static final long serialVersionUID = 1L;
    private static ArrayList<Reserva> reservas = new ArrayList<>();
    
    //atributos basicos. L
    public int idReserva;
    public Cliente cliente;
    public String tipoTiquete;
    ///  reservaEstado: 0 = pendiente, 1 = concretada/pagada y convierte a ticket, 2 = cancelada. L
    public int reservaEstado = 0;
    
    //no se. L
    public Reserva(){
        reservas.add(this);
    }
    
    //duplicado de generar reserva? o que es. L
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
        public void cancelar_reserva () {  
        	this.reservaEstado = 2;
    	//falta borrarla del registro de reservas activas. L  	    
    }
        public void modificar_cliente (Cliente clienteNuevo) {
        	this.cliente = clienteNuevo;      	
        }
        public String verificar_tipoId() {
        	return this.cliente.tipoId;
        }
        public Cliente get_Cliente() {
        	return this.cliente;        	
        }
}
