package gestorAplicacion.servicios;

import gestorAplicacion.instalaciones.Instalacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Registro {
    public static int cantidadClientesDiaActual = 0;
    protected static final int capacidadDiaActual = 1000;
    protected static HashMap<String, Integer> calendario = new HashMap<>();
    public static HashMap<String, Instalacion> instalaciones = new HashMap<>();
    protected static ArrayList<Cliente> clientes = new ArrayList<>();
    public static ArrayList<Cliente> reservas = new ArrayList<>();
    protected static ArrayList<Cliente> tarjetas = new ArrayList<>();

    /*El formato de fecha que vamos a utilizar es dd-mm-aaaa*/
    public static void agregarInstalacion(Instalacion instalacion) {
        instalaciones.put(instalacion.getNombre(), instalacion);
    }
    public static void inicioCalendrio(String fechaInicio){
        String key;
        int dia = Integer.parseInt(fechaInicio.split("-")[0])+1;
        int mes = Integer.parseInt(fechaInicio.split("-")[1]);
        int anio = Integer.parseInt(fechaInicio.split("-")[2]);
        for(int i = 0; i < 365; i++){
            key = dia + "-" + mes + "-" + anio;
            calendario.put(key, 0);
            dia++;
            if(dia > 31 || (dia > 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) || (dia > 28 && mes == 2)){
                dia = 1;
                mes++;
                if(mes > 12){
                    mes = 1;
                    anio++;
                }
            }
        }

    }
    public static boolean agregarReservaCalendario(String fecha){
        if(calendario.containsKey(fecha)) {
            if (calendario.get(fecha) < capacidadDiaActual) {
                calendario.put(fecha, calendario.get(fecha) + 1);
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    }
    public static boolean eliminarReservaCalendario(String fecha){
        if(calendario.containsKey(fecha)) {
            if (calendario.get(fecha) < capacidadDiaActual) {
                calendario.put(fecha, calendario.get(fecha) - 1);
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    }
    public static boolean agregarIngreso(){
        if(cantidadClientesDiaActual < capacidadDiaActual){             
            cantidadClientesDiaActual++;
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
        if(buscarCliente(c.getId()) == null){
            clientes.add(c);
            return true;
        }
        else{
            return false;
        }

    }
    
    /*    
    public static boolean agregarCliente(Cliente c){
        Tarjeta tarjeta = c.reserva.tarjeta;
        if (agregarIngreso()){                               //se verifica si caben mas personas en el parque
            if(buscarCliente(c.getId()) != null){                   
                                                                    
                if(buscarTarjeta(c.getId()) != null){               //Si tiene tarjeta y existe el cliente se deja entrar
                    clientes.add(c); 
                    tarjeta.agregarEntrada();             //Se agrega una entrada en la tarjeta para futuros desceuntos
                    tarjeta.setActiva(true);              //Se le activa la tarjeta
                    return true;   
                
                }    
                else{                                                // si no tiene tarjeta no lo deja entrar
                    return false;
                }
            }
            else{                                                   // si se excedio la cantidad maxima del parque se rechaza la entrada
                return false;
            }
        }
        else{
            return false;
        }

    }
    
    */
    
    public static boolean agregarReserva(Reserva c){
        if(buscarReserva(c.idReserva) != null){             //si la reserva ya existe no se hace

            return false;
        }
        else{                                               //se busca al cliente por el id 
            Cliente cliente = buscarCliente(c.idReserva);
            if(cliente != null){                     
                cliente.reserva = c;
                if(agregarReservaCalendario(c.getFecha())){     //se verifica si hay cupo para la fecha de la reserva
                    reservas.add(cliente);
                    
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }

    }
    public static boolean eliminarReserva(int id){
        Cliente cliente = buscarReserva(id);
        if(cliente != null){
            if(eliminarReservaCalendario(cliente.reserva.getFecha())){
                cliente.reserva = null;
                reservas.remove(cliente);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    public static boolean modificarReserva(int id, String fecha){
        Cliente cliente = buscarReserva(id);
        if(cliente != null){
            if(eliminarReservaCalendario(fecha)){
                cliente.reserva.setFecha(fecha);
                if(agregarReservaCalendario(fecha)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return false;
    }
    public static boolean existeCliente(int id){
        if(buscarCliente(id) != null){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean existeReserva(int id){
        if(buscarReserva(id) != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static Cliente buscarTarjeta(int id){
        for(Cliente c: tarjetas){
            if(c.reserva.tarjeta.idTarjeta == id){
                return c;
            }
        }
        
        return null;
    }
    
    public static boolean agregarTarjeta(Tarjeta t){
        if(buscarTarjeta(t.idTarjeta) != null){              //si la tarjeta ya esta en la lista no se pone
            
            return false;
        }
        else {
            Cliente cliente = buscarCliente(t.idTarjeta);   //si la tarjeta no esta en la lista, entonces se agrega al cliente en la lista de tarjetas
            if(cliente != null){
                tarjetas.add(cliente);
                return true;
            }
            else{
                return false;
        
            }   
        }   
    
    }
    
    public static boolean concretarVenta(int id, Tarjeta tarjeta){
        Cliente cliente = buscarReserva(id);
        System.out.println(cliente);
        if (cliente != null){    
            cliente.reserva.tarjeta = tarjeta;
            agregarTarjeta(cliente.reserva.tarjeta);    //se agrega la tarjeta
            System.out.println("este es el cliente"+cliente);
            cliente.reserva.tarjeta.setActiva(true);             //se activa la tarjeta
            cliente.reserva.setDesactiva();                      //se desactiva la reserva
            eliminarReserva(id);                                 //se elimina la reserva
            cliente.reserva.tarjeta.agregarEntrada();            // se le agrega la entrada entrada´para futuro descuento
            cantidadClientesDiaActual++;
            return true;
           
       }
       //else{
            return false;
           
       //}
        
    }
    
    public static int verificarTarjeta(int id){
        Cliente cliente = buscarReserva(id);
        if (cliente.getEdad()<=15){
            System.out.println("Niño");
            return 1;
        }else{
            return 0;
        }
    }
 
    public boolean cargarTarjeta(Tarjeta tarjeta, float saldo){     //Para agregar saldo a una tarjeta se verifica qeu este activa
        if(tarjeta.getActiva()){
            tarjeta.agregarSaldo(saldo); 
            return true;
        }
        else{
            return false;
        }
    }
    public String mostrarInstalaciones(){
        String instalacion = "";
        Set<String> keys = instalaciones.keySet();
        for ( String key : keys ) {
            instalacion += key + " " + instalaciones.get(key) + "\n";
        }
        return instalacion;
    }

    
}
    
