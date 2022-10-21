package gestorAplicacion.servicios;

import java.util.ArrayList;

public class Registro {
    protected static int cantidadClientesDiaActial = 0;
    protected static final int capacidadDiaActual = 1000;
    protected static ArrayList<Cliente> clientes = new ArrayList<>();
    protected static ArrayList<Cliente> reservas = new ArrayList<>();

    public static boolean agregarIngreso(){
        if(cantidadClientesDiaActial < capacidadDiaActual){
            cantidadClientesDiaActial++;
            return true;
        }
        else return false;

    }
    public static Cliente buscarCliente(int id){
        for(Cliente c: clientes){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
    public static Cliente buscarReserva(int id){
        for(Cliente c: reservas){
            if(c.reserva.idReserva == id){
                return c;
            }
        }
        return null;
    }
    public static boolean agregarCliente(Cliente c){
        if(buscarCliente(c.getId()) != null){
            clientes.add(c);
            return true;
        }
        else{
            return false;
        }

    }
    public static boolean agregarReserva(Reserva c){
        if(buscarReserva(c.idReserva) != null){

            return false;
        }
        else{
            Cliente cliente = buscarCliente(c.idReserva);
            if(cliente != null){
                cliente.reserva = c;
                reservas.add(cliente);
                return true;
            }
            else{
                return false;
            }
        }

    }
    public static boolean eliminarReserva(int id){
        Cliente cliente = buscarReserva(id);
        if(cliente != null){
            cliente.reserva = null;
            reservas.remove(cliente);
            return true;
        }
        else{
            return false;
        }
    }
}
