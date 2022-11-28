from servicios.registro import Registro
from servicios.cliente import Cliente
from servicios.reserva import Reserva
from servicios.tarjeta import Tarjeta
from servicios.tiquete import Tiquete
from instalaciones.instalacionMenores import InstalacionMenores
from instalaciones.instalacionAdultos import InstalacionAdultos
from instalaciones.instalacion import Instalacion
from Deserializador import Deserializador
from Serializador import Serializador


import pickle



fichero_binario = open("pcs.pkl", "wb")

if __name__ == "__main__":
    x = -8
    Deserializador.deserializar()
    while (x == -8):

        #Creacion del registro
        Registro1 = Registro()

        print("\n Bienvenido a la taquilla\n************************************")
        print("1. Verificar disponibilidad del parque")
        print("2. Agregar ingreso")
        print("3. Clientes y reservas")
        print("4. Acceso a instalaciones")
        print("6. Opciones avanzadas")
        print("7. Salir")
        print("************************************")

        opcion = int(input("Seleccione una opcion: "))
        
        if opcion == 1:
            print(f"El dia de hoy han ingresado {Registro.cantidadClientesDiaActual} personas al parque sobran {(1000 - Registro.cantidadClientesDiaActual)} cupos.")

        if opcion == 2:                                                             
            id = int(input("Id del cliente: "))
            existe = Registro1.buscarCliente(id)
            if existe != False:                                                         #si el cliente existe se comprueba si hay espacio en el parque
                y = Registro1.agregarIngreso()
                t = Registro1.buscarTarjeta(id)
                if t != False:                                                          #se comprueba si el cliente tiene tarjeta, si no se crea
                    t.agregarEntrada()
                    tarjetas = int(input("tiene tarjeta fisica o desea imprimirla? \n1. Si tengo. \n2. Imprimir tarjeta. \n"))
                    if tarjetas == 1:
                        print("Bienvenido al parque. ya puede pasar.")
                    else:
                        print("Imprimiendo tarjeta...")
                        t.activarTarjeta()
                        t.setTarjetaFisica()
                        print(t)
                        print("Bienvenido al parque. ya puede pasar.")
                else:
                    edad = existe.getEdad()
                    if existe.getEdad() >= 18:
                        tarj = Tarjeta(id, 100, "Adulto")
                        tarj.activarTarjeta()
                        Registro1.agregarTarjeta(tarj)
                        existe.setTarjeta(tarj)
                    else:
                        tarj = Tarjeta(id, 50, "Infante")
                        tarj.activarTarjeta()
                        Registro1.agregarTarjeta(tarj)
                        existe.setTarjeta(tarj)
                    print("Imprimiendo tarjeta...")
                    tarj.setTarjetaFisica()
                    print(tarj)
                    print("Bienvenido al parque. ya puede pasar.")
                    tarj.agregarEntrada()
            else:                                                         #Cuando no esta en la lista de clientes
                edad = int(input("Edad del cliente: "))
                if edad>=18:
                    tarj = Tarjeta(id, 100, "Adulto")
                    print("tarjeta creada")
                    tipoid = "cc"
                else:
                    tarj = Tarjeta(id, 50, "Infante")
                    print("tarjeta creada")
                    tipoid = "ti"
                cli = Cliente(tipoid, id, edad)
                Registro1.agregarCliente(cli)
                y = Registro1.agregarIngreso()
                cli.setTarjeta(tarj)
                if y:
                    print("Imprimiendo tarjeta...")
                    tarj.activarTarjeta()
                    tarj.setTarjetaFisica()
                    print(tarj)
                    Registro1.agregarTarjeta(tarj)
                    tarj.agregarEntrada()
                    print("Bienvenido al parque. Ya puede pasar")

                else:
                    print("Error")

        if opcion == 3:
            select = -1
            while (select != 8):
                op = int(input("\n"
                + "\n1. Registrar cliente"
                + "\n2. Buscar cliente"
                + "\n3. Agregar reserva"
                + "\n4. Modificar reserva"
                + "\n5. Eliminar reserva"
                + "\n6. Ver reservas"
                + "\n7. Concretar venta"
                + "\n8. Ir al menú principal\n"
                + "\nSeleccione una opcion:  "))

                if op==1:

                    print("\n**Registrar**")
                    id = int(input("Id del cliente: "))
                    edad = int(input("Edad del cliente: "))
                    if edad>=18:
                        tipoid = "cc"
                    else:
                        tipoid = "ti"
                    cli = Cliente(tipoid, id, edad)
                    Registro1.agregarCliente(cli)


                if op == 2:
                    id = int(input("Id del cliente: "))
                    print(Registro1.buscarCliente(id))

                if op == 3:
                    print("\n**Registrar reserva**")
                    id = int(input("Id del cliente: "))
                    s = Registro1.buscarCliente(id)
                    if s != False:                                                            #si el cliente existe se le hace la reserva
                        l = Registro1.buscarReserva(id)
                        if l != False:
                            res = int(input("Usted ya tiene una reserva\n Desea modificar la fecha?: \n1. Si\n2. No\n"))
                            if res == 1:
                                l.setDesactiva()
                                Registro1.eliminarReserva(l)
                                f = input("para que fecha desea su reserva? formato dd-mm-aaa:  ")
                                reserva = Reserva(id, f)
                                Registro1.agregarReserva(reserva)
                                s.setReserva(reserva)
                            else:
                                pass
                        else:
                            f = input("para que fecha desea su reserva? formato dd-mm-aaa:  ")
                            reserva = Reserva(id, f)
                            Registro1.agregarReserva(reserva)
                            s.setReserva(reserva)

                    else:                                                                     #si no existe se regustra y se crea la reserva
                        edad = int(input("Edad del cliente: "))
                        if edad>=18:
                            tipoid = "cc"
                        else:
                            tipoid = "ti"
                        cli = Cliente(tipoid, id, edad)
                        Registro1.agregarCliente(cli)

                        f = input("Para que fecha desea su reserva? formato dd-mm-aaa:  ")
                        reserva = Reserva(id, f)
                        Registro1.agregarReserva(reserva)
                        cli.setReserva(reserva)

                if op == 4:
                    print("\n**Modificar reserva**")
                    id = int(input("Id del cliente: "))
                    l = Registro1.buscarReserva(id)
                    if l != False:
                        Registro1.eliminarReserva(l)
                        f = input("Escriba la nueva fecha de su reserva en formato dd-mm-aaa:  ")
                        reserva = Reserva(id, f)
                        Registro1.agregarReserva(reserva)
                        s.setReserva(reserva)
                    else:
                        print("No se encontraron reservas. Debe hacer una, dirijase al menú.")

                if op == 5:
                    print("\n**Eliminar reserva**")
                    id = int(input("Id del cliente: "))
                    l = Registro1.buscarReserva(id)
                    cli = Registro1.buscarCliente(id)
                    if l != False:
                        l.setDesactiva()
                        Registro1.eliminarReserva(l)
                        cli.setReserva(None)
                    else:
                        print("No se encontró ninguna reserva que coincida con la id")

                if op == 6:
                    p = int(input("1. Ver todas las reservas activas"+
                         "\n2. Buscar una reserva\n"))
                    
                    if p == 1:
                        Registro1.mostrarReservas()
                    
                    if p == 2:
                        print("\n**Ver reservas**")
                        id = int(input("id del cliente: "))
                        t = Registro1.buscarReserva(id)
                        if t == False:
                            print("No se encontró ninguna reserva que coincida con la id")
                        else:
                            print(t)
                
                if op == 7:
                    print("\n**Concretar Venta**")
                    id = int(input("id del cliente: "))
                    s = Registro1.buscarCliente(id)
                    if s==False:                                                         #Si no existe el cliente
                        print("No se encontró un cliente con esta id")
                    else:                                           
                        l = Registro1.buscarReserva(id)
                        if l==False:                                                     #Si existe el cliente se le busca la reserva
                            print("Este cliente no tiene ninguna reserva activa")
                        else:                                                               #Si tiene reserva se elimina y se le da la tarjeta

                            Registro1.eliminarReserva(l)
                            s.setReserva(None)
                            Registro1.agregarIngreso()

                            t = Registro1.buscarTarjeta(id)
                            if t != False:                                                          #se comprueba si el cliente tiene tarjeta, si no se crea
                                t.agregarEntrada()
                                tarjetas = int(input("tiene tarjeta fisica o desea imprimirla? \n1. Si tengo. \n2. Imprimir tarjeta. \n"))
                                if tarjetas == 1:
                                    print("Bienvenido al parque. ya puede pasar.")
                                else:
                                    print("Imprimiendo tarjeta...")
                                    t.activarTarjeta()
                                    t.setTarjetaFisica()
                                    print(t)
                                    print("Bienvenido al parque. ya puede pasar.")
                            else:
                                edad = s.getEdad()
                                if s.getEdad() >= 18:
                                    tarj = Tarjeta(id, 100, "Adulto")
                                    tarj.activarTarjeta()
                                    Registro1.agregarTarjeta(tarj)
                                else:
                                    tarj = Tarjeta(id, 50, "Infante")
                                    tarj.activarTarjeta()
                                    Registro1.agregarTarjeta(tarj)
                                s.setTarjeta(tarj)
                                print("Imprimiendo tarjeta...")
                                tarj.setTarjetaFisica()
                                tarj.agregarEntrada()
                                print(tarj)
                                print("Bienvenido al parque. ya puede pasar.")
            
                if op == 8:
                    select = 8
                    exit
        if opcion == 4:
            opss = -2
            while (opss != 3):
                opss = int(input("\n"
                + "\n1. Cargar saldo"
                + "\n2. Comprar Tiquetes"
                + "\n3. Ir al menú principal\n"
                + "\nSeleccione una opcion:  "))
                if opss == 1:
                    print("\n**Cargar saldo**")
                    id = int(input("id del cliente: "))
                    t = Registro1.buscarTarjeta(id)
                    if t!= False  :
                        if t.getTarjetaFisica !=False:
                            saldo = int(input(f"su saldo es {t}" + "\nCuanto desea cargar?  "))
                            t.cargarTarjeta(t, saldo)
                            print(f"su tarjeta fue cargada con {saldo}\n")
                            print("**Tarjeta**")
                            print(t)
                        else:
                            print("su tarjeta no esta activa")
                    else:
                        ("no se encontró esta tarjeta")
                
                if opss == 2:
                    print("\n*Comprar tiquetes**")
                    id = int(input("id del cliente: "))
                    t = Registro1.buscarTarjeta(id)
                    if t!= False  :
                        if t.getTarjetaFisica !=False:
                            print(t)
                            tipo = t.getTipo()
                            print("\nTenemos las siguientes instalaciones: \n")
                            Registro1.mostrarInstalaciones(tipo)
                            z = input("A cual desea entrar?: ")
                            i = Registro1.buscarInstalacion(z)
                            if i != False and tipo == i.getEdadRestriccion():
                                costo = i.getCosto()
                                if t.getSaldo() >= costo:
                                    tiquet = Tiquete(i, id)
                                    if tiquet.comprarTiquete(t) == True:
                                        print(f"Su saldo es de {t.getSaldo()}")
                                    else:
                                        pass
                                else:
                                    print("No tiene suficiente saldo como para entrar a esta atraccion")
                            else:
                                print("No tenemos ninguna instalacion con este nombre")
                        else:
                            print("su tarjeta no esta activa")
                    else:
                        print("no se encontró esta tarjeta")
                    

        if opcion == 6:
            selec = -2
            while (selec != 4):
                ops = int(input("\n"
                + "\n1. Ver instalaciones"
                + "\n2. Agregar instalaciones"
                + "\n3. Hacer mantenimiento"
                + "\n4. Ir al menú principal\n"
                + "\nSeleccione una opcion:  "))
            
                if ops == 1:
                    Registro1.mostrarInstalaciones("Todas")

                if ops == 2:
                    tipo = int(input("1. Niños \n2. Adultos \nLa instalacion es para: "))
                    if tipo == 1:
                        nombre = input("Ingrese el nombre de la instalacion: ")
                        r = Registro1.buscarInstalacion(nombre)
                        if r != False:
                            print("ya hay una instalacion con este nombre")
                        else:
                            instalacion = InstalacionMenores(nombre)
                            Registro1.agregarInstalacion(instalacion)
                    
                    if tipo == 2:
                        nombre = input("Ingrese el nombre de la instalacion: ")
                        r = Registro1.buscarInstalacion(nombre)
                        if r != False:
                            print("ya hay una instalacion con este nombre")
                        else:
                            instalacion = InstalacionAdultos(nombre)
                            Registro1.agregarInstalacion(instalacion)
                if ops == 3:
                    instal = Instalacion.mostrarSolicitudes()
                    if instal == False:
                        print("Por el momento no hay ninguna instalacion que necesite mantenimiento")
                    else:
                        activar = input("Seleccione una:  ")
                        instalacion = instalacion.instalacionesDeshuso(activar)
                        if instalacion != False:
                           instalacion.realizarMantenimiento()
                        else:
                            print("no se encontro una instalacion que requiera mantenimiento con este nombre")

                if ops == 4:
                    select = 4
                    break

        if opcion == 7:
            x = 0
            break

    guardar = int(input("Desea guardar los cambios? \n1. Si\n2. No \n"))
    if guardar == 1:
        Serializador.serializar()
    if guardar == 2:
        print("Gracias por usar la taquilla")
        

                        



