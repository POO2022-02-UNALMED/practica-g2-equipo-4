package gestorAplicacion.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Registro {
    protected static int cantidadClientesDiaActual = 0;
    protected static final int capacidadDiaActual = 1000;
    protected static HashMap<String, Integer> calendario = new HashMap<>();
    protected static ArrayList<Cliente> clientes = new ArrayList<>();
    protected static ArrayList<Cliente> reservas = new ArrayList<>();
    protected static ArrayList<Cliente> tarjetas = new ArrayList<>();

    /*El formato de fecha que vamos a utilizar es dd-mm-aaaa*/
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
                reservas.add(cliente);
                return true;
            }
            else{
                System.out.println("entro aqui");
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
       //    cliente.reserva.tarjeta.setActiva(true);             //se activa la tarjeta
       //    cliente.reserva.setDesactiva();                      //se desactiva la reserva
       //    eliminarReserva(id);                                 //se elimina la reserva
       //    cliente.reserva.tarjeta.agregarEntrada();            // se le agrega la entrada entrada´para futuro descuento
       //    cantidadClientesDiaActual++;
       //    return true;
           
       }
       //else{
            return false;
           
       //}
        
    }
    
    public static int verificarTarjeta(int id){
        Cliente cliente = buscarReserva(id);
        if (cliente.getTipoId()== "cc"){
            System.out.println("pasa cc");
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

    
    public static void main(String args[]) {
    
    int select = -1;
    
    //menu general
    while ( select != 0 ){
    
        System.out.println("\nElija una opcion:"
        + "\n1. Verificar disponibilidad del Parque"
        + "\n2. Agregar ingreso al parque"
        + "\n3. Buscar Cliente"
        + "\n4. Reservas"
        + "\n5. Tarjeta"
        + "\n0. Salir\n");
        
        Scanner sc = new Scanner(System.in);
        select = sc.nextInt();
        
        //seleccion del menu
        
        switch(select){
            case 1:
                System.out.println("El dia de hoy han ingresado "+ cantidadClientesDiaActual+" personas al parque "
                + "sobran "+capacidadDiaActual+" cupos.");
                break;
            case 2:
                System.out.println("Ingrese la id del cliente");
                Scanner id = new Scanner(System.in);
                int idcliente = id.nextInt();
               
                
                System.out.println(buscarCliente(idcliente));
                
                if (buscarCliente(idcliente)==null){
                    System.out.println("No se encontró ningun cliente con esta id.");
                }else{
                    System.out.println(buscarCliente(idcliente));
                }
                    
                
                break;
            case 3:
                
                System.out.println("Ingrese la id del cliente que desea buscar");
                Scanner id1 = new Scanner(System.in);
                int idx = id1.nextInt();
                buscarCliente(idx);
                
                if (buscarCliente(idx)==null){
                    System.out.println("No se encontró ningun cliente con esta id.");
                }else{
                    System.out.println(buscarCliente(idx));
                }
                break;
                
            case 4:
                
                while( select != 6){
                System.out.println("\nSeleccione una opcion:"
                        + "\n1. Agregar reserva"
                        + "\n2. Buscar reserva"
                        + "\n3. Eliminar reserva"
                        + "\n4. Ver todas las reservas"
                        + "\n5. Pagar Reserva"
                        + "\n6. Ir al menú principal\n");
                
                Scanner menureserv = new Scanner(System.in);
                select = menureserv.nextInt();
                
                switch(select){
                    case 1:
                        //Ingresa datos del cliente
                        System.out.println("Ingrese el tipo de identificacion del cliente: 1.Cedula 2.Tarjeta de identidad");
                        Scanner ident = new Scanner(System.in);
                        String identificacion = ident.nextLine();
                        System.out.println("Ingrese la id del cliente");
                        Scanner id2 = new Scanner(System.in);
                        int idcliente2 = id2.nextInt();
                        
                        Cliente cliente = new Cliente(identificacion, idcliente2);
                        agregarCliente(cliente);
                        System.out.println("los clientes son"+clientes);

                        //reserva
                        System.out.println("Ingrese la fecha de la resrva en formato dd-mm-aa");
                        Scanner fecha = new Scanner(System.in);
                        String fechar = fecha.nextLine();

                        //agregarReserva();
                        Reserva reserva = new Reserva(fechar, idcliente2);
                        agregarReserva(reserva);
                        System.out.println(reserva);
                        System.out.println(reservas);
                        System.out.println(clientes);
                        break;
                    
                    case 2:
                        System.out.println("Ingrese la identificacion del cliente");
                        Scanner ident1 = new Scanner(System.in);
                        int identificacion1 = ident1.nextInt();
                        
                        if (buscarReserva(identificacion1)==null){
                            System.out.println("Este cliente no tiene ninguna reserva");
                        }else{
                            System.out.println(buscarReserva(identificacion1));
                        }
                        
                        break;
                        
                    case 3:
                        System.out.println("Ingrese el id del cliente que desea eliminar la reserva");
                        Scanner x = new Scanner(System.in);
                        int ideliminar = x.nextInt();

                        if(eliminarReserva(ideliminar)== true){
                            System.out.println("Reserva eliminda");
                        }else{
                            System.out.println("No se le encontró ninguna reserva a este cliente");
                        };
                        
                        break;
                    
                    case 4:
                        int tamanio = reservas.size();
                            System.out.println("Las reservas que hay por el momento son: \n");
                        for (int i=0; i < tamanio; i++){
                            System.out.println(reservas.get(i));
                        }
                        break;
                     
                    case 5:
                        System.out.println("Para concretar la venta debe pagar la reserva. Ingrese el id del cliente");
                        Scanner idpagar = new Scanner(System.in);
                        int idc = idpagar.nextInt();               
                        
                        //Creamos tarjeta
                        int tarjeta1 = verificarTarjeta(idc); //1  0
                        
                        
                        Tarjeta tarjeta = new Tarjeta(idc, tarjeta1);
                        System.out.println(tarjeta);
                        
                        concretarVenta(idc, tarjeta);
                        
                       
                         if(concretarVenta(idc, tarjeta) == true){
                           System.out.println("Se concretó la venta");
                         }else{
                           System.out.println("Error al concretar la venta");
                         }
                       
                        
                    }
                    
                    
                        
                }
            
            case 5:
                /*
                while( select != 5){
                System.out.println("\nSeleccione una opcion:"
                        + "\n1. Buscar Tarjetas"
                
                        + "\n2. Cargar una tarjeta"
                        + "\n3. Ir al menu principal");
                
                Scanner menureserv = new Scanner(System.in);
                select = menureserv.nextInt();

        
                }*/
                
                
                
                
        }
    }
    
    
    
  
    }
}
    
