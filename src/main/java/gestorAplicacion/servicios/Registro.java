package gestorAplicacion.servicios;

import java.util.ArrayList;

public class Registro {
    protected static int cantidadClientesDiaActual = 0;
    protected static final int capacidadDiaActual = 1000;
    protected static ArrayList<Cliente> clientes = new ArrayList<>();
    protected static ArrayList<Cliente> reservas = new ArrayList<>();
    protected static ArrayList<Cliente> tarjetas = new ArrayList<>();

    public static boolean agregarIngreso(){                       //verifica que los clientes no superen la capacidad total del parque
        if(cantidadClientesDiaActual < capacidadDiaActual){       // AQUI SE DEBE RESTAR A LA CAPACIDAD DE CLIENTES LOS QUE YA RESERVARON .P
            cantidadClientesDiaActial++;
            return true;                                          //si los clientes actuales son menores a la capacidad actual. deja entrar
        }
        else return false;  
    }
    public static Cliente buscarCliente(int id){                 //busca el id del cliente en la lista de clientes
        for(Cliente c: clientes){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
    
    /*
    public static boolean agregarCliente(Cliente c){
        Tarjeta tarjeta = c.reserva.tarjeta;
        if (agregarIngreso()== true){                               //se verifica si caben mas personas en el parque
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
    
    public static Cliente buscarReserva(int id){              //busca que el id este en la lista de las reservas
        for(Cliente c: reservas){
            if(c.reserva.idReserva == id){
                return c;
            }
        }
        return null;
    }
     public static boolean agregarCliente(Cliente c){            //P.
        if(buscarCliente(c.getId()) != null){                   //si el cliente no esta en lista de clientes lo agrega
            clientes.add(c);
            return true;    
        }
        else{
            return false;
        }

    }
    public static boolean agregarReserva(Reserva c){
        if(buscarReserva(c.idReserva) != null){              //si la reserva ya existe no se agrega

            return false;
        }
        else{                                               //si no existe la reserva. se busca al cliente y se le agrega la reserva
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
    public static void eliminarReserva(int id){
        Cliente cliente = buscarReserva(id);
        if(cliente != null){
            cliente.reserva = null;
            reservas.remove(cliente);
        }

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
    
    ////---------------------------------------- //P.
    
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
    
    public boolean concretarVenta(int id){
       Cliente cliente = buscarReserva(id); 
       if (cliente != null){                                    //si el cliente existe en las reservas           
           Registro.agregarTarjeta(cliente.reserva.tarjeta);    //se agrega la tarjeta
           cliente.reserva.tarjeta.setActiva(true);             //se activa la tarjeta
           clientes.add(cliente);                               // el cliente entra directamente porque ya tiene reservado
           cantidadClientesDiaActial++;
           return true;
           
       }
       else{
           return false;
           
       }
        
    }
 
    public boolean cargarTarjeta(Tarjeta tarjeta, float saldo){     //Para agregar saldo a una tarjeta se verifica qeu este activa
        if(tarjeta.getActiva() == true){
            tarjeta.agregarSaldo(saldo); 
            return true;
        }
        else{
            return false;
        }
    }
}
