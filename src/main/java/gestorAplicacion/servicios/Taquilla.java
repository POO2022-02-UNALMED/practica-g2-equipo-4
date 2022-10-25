/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorAplicacion.servicios;

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
import java.util.Scanner;

/**
 *
 * @author paula
 */
public class Taquilla {
    
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
                + "sobran "+(1000-cantidadClientesDiaActual)+" cupos.");
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
                        + "\n5. Concretar venta"
                        + "\n6. Ir al menú principal\n");
                
                Scanner menureserv = new Scanner(System.in);
                select = menureserv.nextInt();
                
                switch(select){
                    case 1:
                        //Ingresa datos del cliente
                        System.out.println("Ingrese el tipo de identificacion del cliente:");
                        Scanner ident = new Scanner(System.in);
                        String identificacion = ident.nextLine();
                        System.out.println("Ingrese la id del cliente");
                        Scanner id2 = new Scanner(System.in);
                        int idcliente2 = id2.nextInt();
                        System.out.println("Ingrese la edad del cliente:");
                        Scanner edad1 = new Scanner(System.in);
                        int edad = edad1.nextInt();
                        
                        Cliente cliente = new Cliente(identificacion, idcliente2, edad);
                        agregarCliente(cliente);

                        //reserva
                        System.out.println("Ingrese la fecha de la resrva en formato dd-mm-aa");
                        Scanner fecha = new Scanner(System.in);
                        String fechar = fecha.nextLine();

                        //agregarReserva();
                        Reserva reserva = new Reserva(fechar, idcliente2);
                        agregarReserva(reserva);
                        System.out.println("Su reserva es:\n"+ reserva);
                        break;
                    
                    case 2:
                        System.out.println("Ingrese la identificacion del cliente");
                        Scanner ident1 = new Scanner(System.in);
                        int identificacion1 = ident1.nextInt();
                        
                        if (buscarReserva(identificacion1)==null){
                            System.out.println("Este cliente no tiene ninguna reserva");
                        }else{
                            System.out.println("Su reserva es:\n"+buscarReserva(identificacion1));
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
                        Cliente c = buscarCliente(idc);
                        if(c.getEdad()<=15){
                            int n = 1;
                            Tarjeta tarjeta = new Tarjeta(idc, 1);
                            concretarVenta(idc, tarjeta);
                            tarjeta.setActiva(true);
                            eliminarReserva(idc);
                        }
                        else{
                            int n = 0;
                            Tarjeta tarjeta = new Tarjeta(idc, 0);
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
    
