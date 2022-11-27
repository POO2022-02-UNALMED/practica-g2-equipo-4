from servicios.registro import Registro
from servicios.cliente import Cliente
from servicios.reserva import Reserva

if __name__ == "__main__":


    #Cliente1 = Cliente("cc", 12, 20)
    #print(Cliente1)
    x = -8
    while (x == -8):

        Registro1 = Registro()

        print("\n Bienvenido a la taquilla\n************************************")
        print("1. Verificar disponibilidad del parque")
        print("2. Agregar ingreso")
        print("3. Clientes y reservas")
        print("5. Acceso a instalaciones")

        print("************************************\n")

        opcion = int(input("Seleccione una opcion: "))
        
        if opcion == 1:
            print(f"El dia de hoy han ingresado {Registro.cantidadClientesDiaActual} personas al parque sobran {(1000 - Registro.cantidadClientesDiaActual)} cupos.")

        if opcion == 2:
            id = int(input("Id del cliente: "))
            existe = Registro1.buscarCliente(id)
            if existe != False:
                y = Registro1.agregarIngreso()
            else:
                edad = int(input("Edad del cliente: "))
                if edad>=18:
                    tipoid = "cc"
                else:
                    tipoid = "ti"
                cli = Cliente(tipoid, id, edad)
                Registro1.agregarCliente(cli)
                y = Registro1.agregarIngreso()
                if y:
                    print("Ya puede pasar")
                else:
                    print("Error")

        if opcion == 3:
            select = -1
            while (select != 8):
                op = int(input("\nSeleccione una opcion:"
                + "\n1. Registrar cliente"
                + "\n2. Buscar cliente"
                + "\n3. Agregar reserva"
                + "\n4. Modificar reserva"
                + "\n5. Eliminar reserva"
                + "\n6. Ver todas las reservas"
                + "\n7. Concretar venta"
                + "\n8. Ir al menú principal\n"))

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
                    Registro1.mostrarClientes()
                    id = int(input("id del cliente: "))
                    print(Registro1.buscarCliente(id))

                if op == 3:
                    print("\n**Registrar reserva**")
                    id = int(input("Id del cliente: "))
                    s = Registro1.buscarCliente(id)
                    if s != False:
                        l = Registro1.buscarReserva(id)
                        if l != False:
                            print("Ustaed ya tiene una reserva\n"+ l)
                        else:
                            f = input("para que fecha desea su reserva? formato dd-mm-aaa")
                            reserva = Reserva(id, f)

                    else:
                        edad = int(input("Edad del cliente: "))
                        if edad>=18:
                            tipoid = "cc"
                        else:
                            tipoid = "ti"
                        cli = Cliente(tipoid, id, edad)
                        Registro1.agregarCliente(cli)

                        f = input("para que fecha desea su reserva? formato dd-mm-aaa:  ")
                        reserva = Reserva(id, f)


                if op == 8:
                    select = 8
                    exit

        
                


