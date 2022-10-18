/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorAplicacion.servicios;

import java.util.ArrayList;
import java.io.Serializable;

// no se
/**
 *
 * @author USUARIO
 */


public class Reserva implements Serializable {
    
	//no se
    private static final long serialVersionUID = 1L;
    private static ArrayList<Reserva> reservas = new ArrayList<>();
    
    //atributos basicos
    public int idReserva;
    public Cliente client;
    public String tipoTiquete;
    
    //no se
    public Reserva(){
        reservas.add(this);
    }
    
    public static void setReservas(ArrayList<Reserva> res){
        reservas = res;
    }
    
    public static ArrayList<Reserva> getReservas(){
        return reservas;
    }
    
    //Metodos del genmymod
    
    
}
