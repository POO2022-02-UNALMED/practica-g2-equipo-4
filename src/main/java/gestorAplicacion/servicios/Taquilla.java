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
import static gestorAplicacion.servicios.Registro.verificarTarjeta;
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
                        int tarjeta1 = verificarTarjeta(idc); //1 niño  0 adulto
                        
                        
                        Tarjeta tarjeta = new Tarjeta(idc, tarjeta1);
                        System.out.println("holi"+tarjeta);
                        
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
    
