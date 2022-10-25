/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiMain;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorAplicacion.instalaciones.Instalacion;
import gestorAplicacion.instalaciones.InstalacionAdultos;
import gestorAplicacion.instalaciones.InstalacionMenores;
import gestorAplicacion.servicios.*;

import static gestorAplicacion.servicios.Registro.agregarCliente;
import static gestorAplicacion.servicios.Registro.agregarReserva;
import static gestorAplicacion.servicios.Registro.buscarCliente;
import static gestorAplicacion.servicios.Registro.buscarReserva;
import static gestorAplicacion.servicios.Registro.cantidadClientesDiaActual;
import static gestorAplicacion.servicios.Registro.concretarVenta;
import static gestorAplicacion.servicios.Registro.eliminarReserva;
import static gestorAplicacion.servicios.Registro.reservas;
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
        Taquilla obj = new Taquilla();
        Scanner sc = new Scanner(System.in);
        cargar();
        if(Registro.calendario.isEmpty()){
            System.out.println("Ingrese la fecha de hoy en formato dd-mm-aaaa");
            String fecha = sc.nextLine();
            obj.inicioDia(fecha);
        }
            if(Registro.instalaciones.isEmpty()){
                System.out.println("Ingrese a la seccion de opciones avanzadas para agregar instalaciones");
            }
            int opcion = -1;
            while (opcion != 6) {
                System.out.println("Bienvenido a la taquilla");
                System.out.println("************************************");
                System.out.println("1. Verificar disponibilidad del Parque");
                System.out.println("2. Agregar ingreso al parque");
                System.out.println("3. Buscar Cliente");
                System.out.println("4. Reservas");
                System.out.println("5. Acceso a instalaciones");
                System.out.println("6. Salir");
                System.out.println("7. Opciones avanzadas");
                System.out.println("************************************");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("El dia de hoy han ingresado " + cantidadClientesDiaActual + " personas al parque "
                                + "sobran " + (1000 - cantidadClientesDiaActual) + " cupos.");
                        break;
                    case 2:
                        System.out.println("Ingrese el tipo de identificacion del cliente:");
                        String identificacion = sc.nextLine();
                        System.out.println("Ingrese la id del cliente");
                        int idcliente = sc.nextInt();
                        Cliente cliente = buscarCliente(idcliente);
                        if (cliente == null) {                                                    //si el cliente no existe se crea
                            //Ingresa datos del cliente
                            System.out.println("Ingrese la edad del cliente:");
                            int edad = sc.nextInt();
                            cliente = new Cliente(identificacion, idcliente, edad);                   // se crea el cliente
                            agregarCliente(cliente);
                        }
                        if (cliente.tarjeta == null) {                                          //se comprueba si tiene tarjeta. si no tiene se le crea
                            if (cliente.getEdad() <= 15) {
                                int n = 1;
                                Tarjeta tarjeta = new Tarjeta(idcliente, 1);
                                cliente.tarjeta = tarjeta;
                            } else {
                                int n = 0;
                                Tarjeta tarjeta = new Tarjeta(idcliente, 0);
                                cliente.tarjeta = tarjeta;
                            }
                        }
                        if (Registro.agregarIngreso() == true) {            //se comprueba que haya espacio en el parque, se le agrega la entrada y activa la tarjeta
                            cliente.tarjeta.agregarEntrada();
                            cliente.tarjeta.setActiva(true);
                            System.out.println("Su tarjeta es \n" + cliente.tarjeta + "\nTiene tarjeta fisica o desea imprimirla. 1.Ya tiene tarjeta fisica  2.Imprimirla");
                            int tfisica = sc.nextInt();
                            if (tfisica == 1) {
                                System.out.println("Bienvenido al parque, ya puede pasar");

                            } else {
                                System.out.println("Imprimiendo tarjeta.\n"
                                        + "Bienvenido al parque, ya puede pasar");
                            }
                        }                                                               //si no hay espacio en el parque no se le deja pasar
                        else {
                            System.out.println("El parque esta lleno. no puede pasar");
                        }
                        guardar();
                        break;
                    case 3:
                        System.out.println("Ingrese la id del cliente que desea buscar");
                        int idx = sc.nextInt();
                        buscarCliente(idx);
                        if (buscarCliente(idx) == null) {
                            System.out.println("No se encontró ningun cliente con esta id.");
                        } else {
                            System.out.println(buscarCliente(idx).toString());
                        }
                        break;
                    case 4:
                        int select = -1;
                        while (select != 6) {
                            System.out.println("\nSeleccione una opcion:"
                                    + "\n1. Agregar reserva"
                                    + "\n2. Modificar reserva"
                                    + "\n3. Eliminar reserva"
                                    + "\n4. Ver todas las reservas"
                                    + "\n5. Concretar venta"
                                    + "\n6. Ir al menú principal\n");
                            select = sc.nextInt();

                            switch (select) {
                                case 1:
                                    System.out.println("Ingrese el tipo de identificacion del cliente:");
                                    String tipoIden = sc.nextLine();//RESERVAR
                                    System.out.println("Ingrese la id del cliente");
                                    int idcliente2 = sc.nextInt();

                                    if (Registro.existeCliente(idcliente2) == false) {                  //comprobamos si existe el cliente. si no existe se cea
                                        System.out.println("Ingrese la edad del cliente:");
                                        int edad = sc.nextInt();

                                        cliente = new Cliente(tipoIden, idcliente2, edad);
                                        agregarCliente(cliente);
                                    }


                                    if (Registro.buscarReserva(idcliente2) != null) {
                                        System.out.println("Usted ya tiene una reserva.  1.Ver reserva   2.Remplazar reserva");
                                        int reserva = sc.nextInt();
                                        if (reserva == 1) {
                                            System.out.println(buscarReserva(idcliente2));
                                        } else {
                                            System.out.println("Ingrese la fecha de la reserva en formato dd-mm-aa");
                                            String fechar = sc.nextLine();

                                            //agregarReserva();
                                            Reserva reserva1 = new Reserva(fechar, idcliente2);
                                            agregarReserva(reserva1);
                                            System.out.println("Su nueva reserva es:\n" + reserva1);
                                        }
                                    } else {
                                        //reserva                                                                       //se crea la reserva del cliente
                                        System.out.println("Ingrese la fecha de la reserva en formato dd-mm-aa");
                                        Scanner fec = new Scanner(System.in);
                                        String fechar = fec.nextLine();

                                        //agregarReserva();
                                        Reserva reserva = new Reserva(fechar, idcliente2);
                                        agregarReserva(reserva);
                                        System.out.println("Su reserva es:\n" + reserva);
                                    }
                                    break;

                                case 2:                                                                     //BUSCAR RESERVA
                                    System.out.println("Ingrese la identificacion del cliente");
                                    Scanner ident1 = new Scanner(System.in);
                                    int identificacion1 = ident1.nextInt();
                                    Cliente c= buscarCliente(identificacion1);
                                    if (c == null) {
                                        System.out.println("Este cliente no tiene ninguna reserva");
                                    } else {
                                        System.out.println("Ingrese la nueva fecha de la reserva en formato dd-mm-aa");
                                        String nuFecha  = sc.nextLine();
                                        c.reserva.setFecha(nuFecha);
                                        Registro.modificarReserva(c.getId(), c.reserva.getFecha());
                                        System.out.println("Su reserva es:\n" + buscarReserva(identificacion1));
                                    }

                                    break;

                                case 3:                                                                             //ELIMINAR RESERVA
                                    System.out.println("Ingrese el id del cliente que desea eliminar la reserva");
                                    Scanner x = new Scanner(System.in);
                                    int ideliminar = x.nextInt();

                                    if (eliminarReserva(ideliminar) == true) {
                                        System.out.println("Reserva eliminda");
                                    } else {
                                        System.out.println("No se le encontró ninguna reserva a este cliente");
                                    }
                                    ;

                                    break;

                                case 4:                                                                                 //VER TODAS LAS RESERVAS
                                    int tamanio = reservas.size();
                                    System.out.println("Las reservas que hay por el momento son: \n");
                                    for (int i = 0; i < tamanio; i++) {
                                        System.out.println(reservas.get(i));
                                    }
                                    break;

                                case 5:                                                                                   //CONCRETAR VENTA
                                    System.out.println("Para concretar la venta debe pagar la reserva. Ingrese el id del cliente");
                                    Scanner idpagar = new Scanner(System.in);
                                    int idc = idpagar.nextInt();

                                    //Creamos tarjeta
                                    Cliente cln = buscarCliente(idc);
                                    if (cln.getEdad() <= 15) {
                                        int n = 1;
                                        Tarjeta tarjeta = new Tarjeta(idc, 1);
                                        cln.tarjeta = tarjeta;
                                        concretarVenta(idc);
                                        tarjeta.setActiva(true);
                                        eliminarReserva(idc);
                                    } else {
                                        int n = 0;
                                        Tarjeta tarjeta = new Tarjeta(idc, 0);
                                        cln.tarjeta = tarjeta;
                                        concretarVenta(idc);
                                        tarjeta.setActiva(true);
                                        eliminarReserva(idc);

                                    }

                                    System.out.println("Tiene tarjeta fisica o desea imprimirla. 1.Ya tiene tarjeta fisica  2.Imprimirla");
                                    Scanner f = new Scanner(System.in);
                                    int tfisica = f.nextInt();
                                    if (tfisica == 1) {
                                        System.out.println("Bienvenido al parque, ya puede pasar");

                                    } else {
                                        System.out.println("Imprimiendo tarjeta.\n"
                                                + "Bienvenido al parque, ya puede pasar");
                                    }

                            }


                        }
                        guardar();
                        break;
                    case 5:
                        int opcion2 = -1;
                        while (opcion2 != 3) {
                            System.out.println("\nSeleccione una opcion:"
                                    + "\n1. Cargar saldo"
                                    + "\n2. Comprar tiquete"
                                    + "\n3. Ir al menu principal\n");
                            opcion2 = sc.nextInt();

                            switch (opcion2) {
                                case 1:

                                    System.out.println("ingrese el id del cliente");
                                    int idtarjet = sc.nextInt();

                                    Cliente client = buscarCliente(idtarjet);

                                    if (buscarCliente(idtarjet) != null) {
                                        if (client.tarjeta != null) {
                                            System.out.println("su saldo es: " + client.tarjeta.getSaldo() + "\ndigite la cantidad que desea recargar a su tarjeta");
                                            float saldo = sc.nextFloat();
                                            client.tarjeta.agregarSaldo(saldo);
                                            System.out.println("su saldo es: " + client.tarjeta.getSaldo());
                                        } else {
                                            System.out.println("El cliente no tiene tarjeta");
                                        }
                                    } else {
                                        System.out.println("El cliente no existe");
                                    }
                                    break;

                                case 2:
                                    System.out.println("ingrese el id del cliente");
                                    int idt = sc.nextInt();

                                    Cliente clientex = buscarCliente(idt);

                                    if (clientex.tarjeta == null) {
                                        System.out.println("El cliente no tiene tarjeta");
                                    } else {
                                        Tarjeta tarjetax = clientex.tarjeta;
                                        int edad = clientex.getEdad();
                                        System.out.println("Tenemos las siguientes instalaciones: \n");
                                        System.out.println(Registro.mostrarInstalaciones());
                                        System.out.println("elija una: \n");
                                        String nombreInst = sc.nextLine();
                                        Instalacion instalacion = Registro.buscarInstalacion(nombreInst);
                                        Tiquete tiquet = new Tiquete(instalacion, idt);

                                        if (tiquet.comprarTiquete(tarjetax) == true) {
                                            System.out.println("Su saldo es:\n" + tarjetax.getSaldo());
                                        } else {
                                            System.out.println("No se pudo comprar el tiquetesu saldo es:\n" + tarjetax.getSaldo());

                                        }


                                    }
                            }


                        }
                    case 6:
                        System.out.println("Gracias por usar la taquilla");
                        break;
                    case 7:
                        int opcion3= -1;
                        while (opcion3 != 3) {
                            System.out.println("Seleccione una opcion:"
                                    + "\n1. Ver instalaciones"
                                    + "\n2. Agregar instalacion"
                                    + "\n3. Ir al menu principal\n");
                            opcion3 = sc.nextInt();
                            switch (opcion3) {
                                case 1:
                                    System.out.println(Registro.mostrarInstalaciones());
                                    break;
                                case 2:
                                    System.out.println("La instalacion es para niños? 1.Si 2.No");
                                    int n = sc.nextInt();
                                    if (n == 1) {
                                        System.out.println("Ingrese el nombre de la instalacion");
                                        String nombre = sc.nextLine();
                                        System.out.println("Ingrese la restriccion de edad");
                                        int restriccion = sc.nextInt();
                                        Instalacion instalacion = new InstalacionMenores(nombre, restriccion);
                                        Registro.agregarInstalacion(instalacion);
                                    } else {
                                        System.out.println("Ingrese el nombre de la instalacion");
                                        String nombre = sc.nextLine();
                                        System.out.println("Ingrese la restriccion de edad");
                                        int restriccion = sc.nextInt();
                                        Instalacion instalacion = new InstalacionAdultos(nombre, restriccion);
                                        Registro.agregarInstalacion(instalacion);
                                    }
                            }
                        }
                        break;
                }
            }

    }
    public static void guardar() {
        Serializador.serializarTodo();
    }

    public static void cargar() {
        Deserializador.deserializarTodo();
    }
}



