/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorAplicacion.servicios;

import gestorAplicacion.instalaciones.Instalacion;
import gestorAplicacion.instalaciones.InstalacionAdultos;
import gestorAplicacion.instalaciones.InstalacionMenores;
import static gestorAplicacion.servicios.Registro.agregarCliente;
import static gestorAplicacion.servicios.Registro.agregarReserva;
import static gestorAplicacion.servicios.Registro.buscarCliente;
import static gestorAplicacion.servicios.Registro.buscarReserva;
import static gestorAplicacion.servicios.Registro.cantidadClientesDiaActual;
import static gestorAplicacion.servicios.Registro.capacidadDiaActual;
import static gestorAplicacion.servicios.Registro.clientes;
import static gestorAplicacion.servicios.Registro.concretarVenta;
import static gestorAplicacion.servicios.Registro.eliminarReserva;
import static gestorAplicacion.servicios.Registro.reservas;
import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author paula
 */
public class Taquilla {
    
    public void inicioDia(String fecha){
        Registro.inicioCalendrio(fecha);
    }
    public static void main(String args[]) {
    
    Hashtable<Integer, Instalacion> atracciones = new Hashtable<Integer, Instalacion>();
    InstalacionMenores inf1 = new InstalacionMenores("trencito", 1);
    atracciones.put(1, inf1);
    InstalacionMenores inf2 = new InstalacionMenores("carrusel", 2);
    atracciones.put(2, inf2);
    InstalacionMenores inf3 = new InstalacionMenores("gusanito", 3);
    atracciones.put(3, inf3);
    InstalacionMenores inf4 = new InstalacionMenores("ins4", 4);
    atracciones.put(4, inf4);
    
    InstalacionAdultos adul1 = new InstalacionAdultos ("carritos chocones", 5);
    atracciones.put(5, adul1);
    InstalacionAdultos adul2 = new InstalacionAdultos ("montaña rusa", 6);
    atracciones.put(6, adul2);
    InstalacionAdultos adul3 = new InstalacionAdultos ("tren", 7);
    atracciones.put(7, adul3);
    InstalacionAdultos adul4 = new InstalacionAdultos ("ins4", 8);
    atracciones.put(8, adul4);
    
    
    int select = -1;
    
    Scanner sc = new Scanner(System.in);
    Taquilla obj = new Taquilla();
    System.out.println("Bienvenido a la taquilla");
    System.out.println("Ingrese la fecha actual en el formato dd-mm-aaaa");
    String fecha = sc.nextLine();
    obj.inicioDia(fecha);
    
    //menu general
    while ( select != 0 ){
        //Scanner sc = new Scanner(System.in);
        //Taquilla obj = new Taquilla();
        //System.out.println("Bienvenido a la taquilla");
        //System.out.println("Ingrese la fecha actual en el formato dd-mm-aaaa");
        //String fecha = sc.nextLine();
        //obj.inicioDia(fecha);

        System.out.println("\n---MENU----"
        + "\n1. Verificar disponibilidad del Parque"
        + "\n2. Agregar ingreso al parque"
        + "\n3. Buscar Cliente"
        + "\n4. Reservas"
        + "\n5. Tarjeta"
        + "\n0. Salir\n");
        

        select = sc.nextInt();
        
        //seleccion del menu
        
        switch(select){
            case 1:
                System.out.println("El dia de hoy han ingresado "+ cantidadClientesDiaActual+" personas al parque "
                + "sobran "+(1000-cantidadClientesDiaActual)+" cupos.");
                break;
                
            case 2:                                                                     //ingresa desde la taquilla sin reserva
                
                
                
                System.out.println("Ingrese la id del cliente");
                Scanner id = new Scanner(System.in);
                int idcliente = id.nextInt();
                
                if (buscarCliente(idcliente)==null){                                                    //si el cliente no existe se crea
                    
                
                    //Ingresa datos del cliente
                    System.out.println("Ingrese el tipo de identificacion del cliente:");
                    Scanner ident = new Scanner(System.in);
                    String identificacion = ident.nextLine();
                    System.out.println("Ingrese la edad del cliente:");
                    Scanner edad1 = new Scanner(System.in);
                    int edad = edad1.nextInt();
                        
                    Cliente cliente = new Cliente(identificacion, idcliente, edad);                   // se crea el cliente
                    agregarCliente(cliente);                               
                }      
                Cliente ingresoc = buscarCliente(idcliente);
                
                if (ingresoc.tarjeta == null){                                          //se comprueba si tiene tarjeta. si no tiene se le crea        
                        if(ingresoc.edad<=15){
                            int n = 1;
                            Tarjeta tarjeta = new Tarjeta(idcliente, 1);
                            ingresoc.tarjeta=tarjeta;
                        }
                        else{
                            int n = 0;
                            Tarjeta tarjeta = new Tarjeta(idcliente, 0);
                            ingresoc.tarjeta=tarjeta;
                        }
                    }           
                if(Registro.agregarIngreso()==true){            //se comprueba que haya espacio en el parque, se le agrega la entrada y activa la tarjeta
                    ingresoc.tarjeta.agregarEntrada();   
                    ingresoc.tarjeta.setActiva(true);
                    System.out.println("Su tarjeta es \n"+ingresoc.tarjeta+"\nTiene tarjeta fisica o desea imprimirla. 1.Ya tiene tarjeta fisica  2.Imprimirla");
                        Scanner f = new Scanner(System.in);
                        int tfisica = f.nextInt();
                        if (tfisica==1){
                            System.out.println("Bienvenido al parque, ya puede pasar");
                            
                        }else{
                            System.out.println("Imprimiendo tarjeta.\n"
                                    + "Bienvenido al parque, ya puede pasar");
                        }            
                }                                                               //si no hay espacio en el parque no se le deja pasar
                else{
                    System.out.println("El parque esta lleno. no puede pasar");
                }
                break;
                
            case 3: //Buscar cliente
                
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
                        + "\n5. Concretar venta"
                        + "\n6. Ir al menú principal\n");
                
                Scanner menureserv = new Scanner(System.in);
                select = menureserv.nextInt();
                
                switch(select){
                    case 1:                                                                         //RESERVAR
                        System.out.println("Ingrese la id del cliente");
                        Scanner id2 = new Scanner(System.in);
                        int idcliente2 = id2.nextInt();
                        
                        if(Registro.existeCliente(idcliente2)==false){                  //comprobamos si existe el cliente. si no existe se cea
                            System.out.println("Ingrese el tipo de identificacion del cliente:");
                            Scanner ident = new Scanner(System.in);
                            String identificacion = ident.nextLine();
                            System.out.println("Ingrese la edad del cliente:");
                            Scanner edad1 = new Scanner(System.in);
                            int edad = edad1.nextInt();

                            Cliente cliente = new Cliente(identificacion, idcliente2, edad);
                            agregarCliente(cliente);
                        }
                        
                        
                        if(Registro.buscarReserva(idcliente2) != null ){
                            System.out.println("Usted ya tiene una reserva.  1.Ver reserva   2.Remplazar reserva");
                            Scanner reserv = new Scanner(System.in);
                            int reserva = reserv.nextInt();
                            
                            if(reserva==1){
                                System.out.println(buscarReserva(idcliente2));
                            }
                            else{
                                System.out.println("Ingrese la fecha de la reserva en formato dd-mm-aa");
                                Scanner fech = new Scanner(System.in);
                                String fechar = fech.nextLine();

                                //agregarReserva();
                                Reserva reserva1 = new Reserva(fechar, idcliente2);
                                agregarReserva(reserva1);
                                System.out.println("Su nueva reserva es:\n"+ reserva1);
                            }
                        }
                        
                        else{
                            //reserva                                                                       //se crea la reserva del cliente
                            System.out.println("Ingrese la fecha de la reserva en formato dd-mm-aa");
                            Scanner fec = new Scanner(System.in);
                            String fechar = fec.nextLine();

                            //agregarReserva();
                            Reserva reserva = new Reserva(fechar, idcliente2);
                            agregarReserva(reserva);
                            System.out.println("Su reserva es:\n"+ reserva);
                        }
                        break;
                    
                    case 2:                                                                     //BUSCAR RESERVA
                        System.out.println("Ingrese la identificacion del cliente");
                        Scanner ident1 = new Scanner(System.in);
                        int identificacion1 = ident1.nextInt();
                        
                        if (buscarReserva(identificacion1)==null){
                            System.out.println("Este cliente no tiene ninguna reserva");
                        }else{
                            System.out.println("Su reserva es:\n"+buscarReserva(identificacion1));
                        }
                        
                        break;
                        
                    case 3:                                                                             //ELIMINAR RESERVA
                        System.out.println("Ingrese el id del cliente que desea eliminar la reserva");
                        Scanner x = new Scanner(System.in);
                        int ideliminar = x.nextInt();

                        if(eliminarReserva(ideliminar)== true){
                            System.out.println("Reserva eliminda");
                        }else{
                            System.out.println("No se le encontró ninguna reserva a este cliente");
                        };
                        
                        break;
                    
                    case 4:                                                                                 //VER TODAS LAS RESERVAS
                        int tamanio = reservas.size();
                            System.out.println("Las reservas que hay por el momento son: \n");
                        for (int i=0; i < tamanio; i++){
                            System.out.println(reservas.get(i));
                        }
                        break;
                     
                    case 5:                                                                                   //CONCRETAR VENTA
                        System.out.println("Para concretar la venta debe pagar la reserva. Ingrese el id del cliente");
                        Scanner idpagar = new Scanner(System.in);
                        int idc = idpagar.nextInt();               
                        
                        //Creamos tarjeta
                        Cliente c = buscarCliente(idc);
                        if(c.getEdad()<=15){
                            int n = 1;
                            Tarjeta tarjeta = new Tarjeta(idc, 1);
                            c.tarjeta = tarjeta;
                            concretarVenta(idc, tarjeta);
                            tarjeta.setActiva(true);
                            eliminarReserva(idc);
                        }
                        else{
                            int n = 0;
                            Tarjeta tarjeta = new Tarjeta(idc, 0);
                            c.tarjeta = tarjeta;
                            concretarVenta(idc, tarjeta);
                            tarjeta.setActiva(true);
                            eliminarReserva(idc);
                            
                        }
                        
                        System.out.println("Tiene tarjeta fisica o desea imprimirla. 1.Ya tiene tarjeta fisica  2.Imprimirla");
                        Scanner f = new Scanner(System.in);
                        int tfisica = f.nextInt();
                        if (tfisica==1){
                            System.out.println("Bienvenido al parque, ya puede pasar");
                            
                        }else{
                            System.out.println("Imprimiendo tarjeta.\n"
                                    + "Bienvenido al parque, ya puede pasar");
                        }
                        
                    }
                    
                    
                        
                }
            
            case 5:
                
                while( select != 4){
                System.out.println("\nSeleccione una opcion:"
                        + "\n1. Buscar Tarjeta"
                        + "\n2. Cargar saldo"
                        + "\n3. Comprar tiquete"
                        + "\n4. Ir al menu principal\n");
                
                Scanner menureserv = new Scanner(System.in);
                select = menureserv.nextInt();
                
                switch(select){
                    
                    case 1: 
                        System.out.println("ingrese el id del cliente");
                        Scanner idtarj = new Scanner(System.in);
                        int idc = idtarj.nextInt(); 
                        
                        Cliente cliente = buscarCliente(idc);
                        if(cliente.tarjeta != null){
                            System.out.println("Su tarjeta es \n"+ cliente.tarjeta);
                        }
                        else{
                            System.out.println("No hemos encontrado ninguna tarjeta");
                        }
                        
                        break;
                        
                    case 2:
                        
                        System.out.println("ingrese el id del cliente");
                        Scanner ids = new Scanner(System.in);
                        int idtarjet = ids.nextInt(); 
                        
                        Cliente client = buscarCliente(idtarjet);
                        
                        if(buscarCliente(idtarjet) != null){
                            if (client.tarjeta!= null){
                                System.out.println("su saldo es: "+ client.tarjeta.getSaldo() + "\ndigite la cantidad que desea recargar a su tarjeta");
                                Scanner sald = new Scanner(System.in);
                                float saldo = sald.nextFloat();
                                client.tarjeta.agregarSaldo(saldo);
                                System.out.println("su saldo es: "+ client.tarjeta.getSaldo());
                            }
                            else{
                                System.out.println("El cliente no tiene tarjeta");
                            }
                        }
                        else{
                            System.out.println("El cliente no existe");
                        }
                        break;
                        
                    case 3:
                        System.out.println("ingrese el id del cliente");
                        Scanner idtiq = new Scanner(System.in);
                        int idt = idtiq.nextInt();
                        
                        Cliente clientex = buscarCliente(idt);
                        
                        if(clientex.tarjeta == null){
                            System.out.println("El cliente no tiene tarjeta");
                        }
                          
                            
                        else{
                            Tarjeta tarjetax = clientex.tarjeta;
                            int edad = clientex.getEdad();
                                System.out.println("Tenemos las siguientes instalaciones: \n");
                                InstalacionMenores.getInstalacionMenores();
                                InstalacionAdultos.getInstalacionAdultos();
                                System.out.println("elija una: \n");
                                
                                
                                Scanner ins = new Scanner(System.in);
                                int instalaciones = ins.nextInt();
                                
                                Instalacion contenido = atracciones.get(instalaciones);
                                    Tiquete tiquet = new Tiquete(contenido, idt);

                                if(tiquet.comprarTiquete(tarjetax)==true){
                                    System.out.println("Su saldo es:\n"+tarjetax.getSaldo());
                                }
                                else{
                                    System.out.println("No se pudo comprar el tiquetesu saldo es:\n"+tarjetax.getSaldo());
                                        
                                }
                                
                               
                                }
                                
                                
                                
                                }
                                
                                
                                
                            }
                            
                            
                        }
                        
                            }
                        }
                        
                    }                 
                
            
        
    