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

from tkinter import *
from tkinter import messagebox
import pickle

fichero_binario = open("pcs.pkl", "wb")

if __name__ == "__main__":

    Registro1 = Registro()

    ventana = Tk()
    ventana.title("Parque de Diversiones")
    ventana.geometry("420x340")
    etiqueta = Label(ventana, text = "Menu Principal", font=("Arial", 15), bg="black", fg = "white").pack(fill = X)
    descripcion = Label(ventana, text = "Bienvenido al menu principial aqui podrá encontrar las principales \nfunciones para hacer uno del parque\n").pack()



    def disponibilidad():
        ventanasup = Toplevel()
        ventanasup.geometry("400x100")
        ventanasup.title("Disponiblidad")
        etiqueta = Label(ventanasup, text = f"El dia de hoy han ingresado {Registro.cantidadClientesDiaActual} personas al parque sobran {(1000 - Registro.cantidadClientesDiaActual)} cupos.")
        etiqueta.pack()
        etiqueta.place(x=10,y=40)

############################################################################################
############################################################################################
############################################################################################
############################################################################################
############################################################################################
#    etiqueta = Label(ventana, text = "Disponibilidad del parque").place(x=40, y=75)
    botondisponibilidad = Button(ventana, text = "Disponibilidad del parque",padx=47, pady=7, command=disponibilidad)
    botondisponibilidad.place(x=90, y=70)

    def buscar ():
        text = id.get()
        print(text)
        return None
        existe = Registro1.buscarCliente(id)
        if existe != False:                                                         #si el cliente existe se comprueba si hay espacio en el parque
            y = Registro1.agregarIngreso()
            t = Registro1.buscarTarjeta(id)
            if t != False:                                                          #se comprueba si el cliente tiene tarjeta, si no se crea
                t.agregarEntrada()
                t.activarTarjeta()
                t.setTarjetaFisica()
                messagebox.showinfo("Imprimiendo tarjeta...\n",t,"\nBienvenido al parque. ya puede pasar.")
        
        else:                                                         #Cuando no esta en la lista de clientes
            if edad>=18:
                tarj = Tarjeta(id, 100, "Adulto")
                messagebox.showinfo("tarjeta creada")
                tipoid = "cc"
            else:
                tarj = Tarjeta(id, 50, "Infante")
                messagebox.showinfo("tarjeta creada")
                tipoid = "ti"
            cli = Cliente(tipoid, id, edad)
            Registro1.agregarCliente(cli)
            y = Registro1.agregarIngreso()

    def ingreso():

        def buscar ():
            idl = ids.get()
            id = int(idl)
            edadl = edads.get()
            edad = int(edadl)
            existe = Registro1.buscarCliente(id)
            if existe != False:                                                         #si el cliente existe se comprueba si hay espacio en el parque
                y = Registro1.agregarIngreso()
                if y:
                    t = Registro1.buscarTarjeta(id)
                    if t != False:                                                          #se comprueba si el cliente tiene tarjeta, si no se crea
                        t.agregarEntrada()
                        t.activarTarjeta()
                        t.setTarjetaFisica()
                        t.agregarEntrada()
                        etiqueta = Label(ventanasup, text = f"Imprimiendo tarjeta...\n{t} \nBienvenido al parque")
                        etiqueta.pack()
                    else:
                        if edad>=18:
                            tarj = Tarjeta(id, 100, "Adulto")
                            tipoid = "cc"
                        else:
                            tarj = Tarjeta(id, 50, "Infante")
                            tipoid = "ti"

                        Registro1.agregarTarjeta(tarj)
                        existe.setTarjeta(tarj)
                        tarj.activarTarjeta()
                        tarj.setTarjetaFisica()
                        etiqueta = Label(ventanasup, text = f"Imprimiendo tarjeta...\n{tarj} \nBienvenido al parque")
                        etiqueta.pack()
                        tarj.agregarEntrada()
                else:
                    etiqueta = Label(ventanasup, text = f"No puede entrar")
                    etiqueta.pack()
            else:                                                         #Cuando no esta en la lista de clientes
                if edad>=18:
                    tarj = Tarjeta(id, 100, "Adulto")
                    tipoid = "cc"
                else:
                    tarj = Tarjeta(id, 50, "Infante")
                    tipoid = "ti"
                cli = Cliente(tipoid, id, edad)
                Registro1.agregarCliente(cli)
                y = Registro1.agregarIngreso()
                cli.setTarjeta(tarj)
                if y:
                    tarj.activarTarjeta()
                    tarj.setTarjetaFisica()
                    Registro1.agregarTarjeta(tarj)
                    etiqueta = Label(ventanasup, text = f"Imprimiendo tarjeta...\n{tarj} \nBienvenido al parque")
                    etiqueta.place(x=195, y=80)
                    tarj.agregarEntrada()

                else:
                    messagebox.showinfo("Entrada al parque", "Error")

        ventanasup = Toplevel()
        ventanasup.geometry("580x235")
        ventanasup.title("Ingresar Cliente")
        etiqueta = Label(ventanasup, text = "Ingresar Cliente", font=("Arial", 15), bg="black", fg = "white").pack(fill = X)
        descripcion = Label(ventanasup, text = "Agregar ingreso sin reserva\n\n Para cada ingreso, cierre y abra la ventana de nuevo").pack()
        etiqueta = Label(ventanasup, text="id").place(x=40, y=100)
        ids = Entry(ventanasup)
        ids.place(x=70, y=100)
        etiqueta2 = Label(ventanasup, text="edad").place(x=35, y=140)
        edads = Entry(ventanasup)
        edads.place(x=70, y=140)
        Button(ventanasup, text="Ingresar", padx=7, pady=7, command=buscar).place(x=90, y=175)


############################################################################################
############################################################################################
############################################################################################
############################################################################################
############################################################################################
#   etiqueta = Label(ventana, text="Agregar ingreso").place(x=40, y=125)
    agregaringreso = Button(ventana, text = "Agregar ingreso", padx=72, pady=7, command=ingreso)
    agregaringreso.place(x=90, y=120)

#Clientes y Reservas
    def agregarc():

        def buscar ():
            idl = ids.get()
            id = int(idl)
            edadl = edads.get()
            edad = int(edadl)
            l = Registro1.buscarCliente(id)
            if l ==False:
                if edad>=18:
                    tarj = Tarjeta(id, 100, "Adulto")
                    tipoid = "cc"
                else:
                    tarj = Tarjeta(id, 50, "Infante")
                    tipoid = "ti"
                cli = Cliente(tipoid, id, edad)
                Registro1.agregarCliente(cli)
                Registro1.agregarTarjeta(tarj)
                etiqueta = Label(ventanasup, text = f"Cliente agregado \n {cli}")
                etiqueta.place(x=240, y=90)
                cli.setTarjeta(tarj)
            else:
                etiqueta = Label(ventanasup, text = f"Cliente ya existe")
                etiqueta.place(x=250, y=90)

        ventanasup = Toplevel()
        ventanasup.geometry("500x250")
        ventanasup.title("Agregar Cliente")
        etiqueta = Label(ventanasup, text = "Agregar Cliente", font=("Arial", 15), bg="black", fg = "white").pack(fill = X)
        descripcion = Label(ventanasup, text = "Por favor llene los espacios que se piden para poderlos guardar en la base de datos\n").pack()
        etiqueta = Label(ventanasup, text="id").place(x=40, y=75)
        ids = Entry(ventanasup)
        ids.place(x=70, y=75)
        etiqueta2 = Label(ventanasup, text="edad").place(x=35, y=125)
        edads = Entry(ventanasup)
        edads.place(x=70, y=125)
        Button(ventanasup, text="Agregar", padx=7, pady=10, command=buscar).place(x=90, y=155)


    def buscarc():
        def buscar ():
            idl = ids.get()
            id = int(idl)
            if Registro1.buscarCliente(id) == False:
                etiqueta = Label(ventanasup, text = f"no encontramos ningun cliente con esta id")
                etiqueta.place(x=300, y=90)
            else:
                etiqueta = Label(ventanasup, text = f"Cliente encontrado \n{Registro1.buscarCliente(id)}")
                etiqueta.place(x=200, y=90)


        ventanasup = Toplevel()
        ventanasup.geometry("700x200")
        ventanasup.title("Buscar Cliente")
        etiqueta = Label(ventanasup, text = "Buscar Cliente", font=("Arial", 15), bg="black", fg = "white").pack(fill = X)
        descripcion = Label(ventanasup, text = "Por favor llene los espacios que se piden para poderlos guardar en la base de datos\n").pack()
        etiqueta = Label(ventanasup, text="id").place(x=40, y=75)
        ids = Entry(ventanasup)
        ids.place(x=70, y=75)
        Button(ventanasup, text="Buscar", padx=7, pady=10, command=buscar).place(x=90, y=105)

    def agregarr():

        def buscar ():
            idl = ids.get()
            id = int(idl)
            edadl = edads.get()
            edad = int(edadl)
            fechal = fechas.get()
            s = Registro1.buscarCliente(id)
            if s==False:
                if edad>=16:
                    tarj = Tarjeta(id, 100, "Adulto")
                    tipoid = "cc"
                else:
                    tarj = Tarjeta(id, 50, "Infante")
                    tipoid = "ti"
                cli = Cliente(tipoid, id, edad)
                Registro1.agregarCliente(cli)
                Registro1.agregarTarjeta(tarj)
                etiqueta = Label(ventanasup, text = f"Cliente agregado \n {cli}")
                etiqueta.place(x=250 , y=125)
                cli.setTarjeta(tarj)

                reserva = Reserva(id, fechal)
                Registro1.agregarReserva(reserva)
                cli.setReserva(reserva)
                etiqueta = Label(ventanasup, text = f"Reserva exitosa \n {reserva}")
                etiqueta.place(x=250 , y=125)
                
            else:
                l = Registro1.buscarReserva(id)
                if l != False:
                    etiqueta = Label(ventanasup, text = f"Este cliente ya tiene una reserva.")
                    etiqueta.place(x=250 , y=125)

                else:
                    reserva = Reserva(id, fechal)
                    Registro1.agregarReserva(reserva)
                    s.setReserva(reserva)
                    etiqueta = Label(ventanasup, text = f"Reserva exitosa \n {reserva}")
                    etiqueta.place(x=250 , y=125)
        #

        ventanasup = Toplevel()
        ventanasup.geometry("550x300")
        ventanasup.title("Agregar Reserva")
        etiqueta = Label(ventanasup, text = "Agregar Reserva", font=("Arial", 15), bg="black", fg = "white").pack(fill = X)
        descripcion = Label(ventanasup, text = "Por favor llene los espacios que se piden para poderlos guardar en la base de datos\n").pack()
        etiqueta = Label(ventanasup, text="id").place(x=40, y=75)
        ids = Entry(ventanasup)
        ids.place(x=75, y=75)
        etiqueta2 = Label(ventanasup, text="edad").place(x=40, y=125)
        edads = Entry(ventanasup)
        edads.place(x=75, y=125)
        etiqueta2 = Label(ventanasup, text="fecha").place(x=40, y=175)
        fechas = Entry(ventanasup)
        fechas.place(x=75, y=175)
        Button(ventanasup, text="Buscar", padx=7, pady=10, command=buscar).place(x=95, y=225)

#############################################################################################
#############################################################################################
#############################################################################################
#############################################################################################

    def modr():
        def buscar ():
            idl = ids.get()
            id = int(idl)
            fechal = fechas.get()
            l = Registro1.buscarReserva(id)
            s = Registro1.buscarCliente(id)
            if l != False:
                Registro1.eliminarReserva(l)
                reserva = Reserva(id, fechal)
                Registro1.agregarReserva(reserva)
                s.setReserva(reserva)
                etiqueta = Label(ventanasup, text=f"Su nueva reserva es \n{reserva}").pack()

            else:
                etiqueta = Label(ventanasup, text=f"No se encontraron reservas. Debe crear una.").pack()
        ventanasup = Toplevel()
        ventanasup.geometry("400x200")
        ventanasup.title("Modificar Reserva")
        etiqueta = Label(ventanasup, text="id").pack()
        ids = Entry(ventanasup)
        ids.pack()
        etiqueta = Label(ventanasup, text="fecha en formato dd-mm-aa").pack()
        fechas = Entry(ventanasup)
        fechas.pack()
        Button(ventanasup, text="Aceptar", padx=7, pady=10, command=buscar).pack()
        print("g")

#
    def elimr():
        def buscar ():
            idl = ids.get()
            id = int(idl)
            l = Registro1.buscarReserva(id)
            cli = Registro1.buscarCliente(id)
            if l != False:
                l.setDesactiva()
                Registro1.eliminarReserva(l)
                cli.setReserva(None)
                etiqueta = Label(ventanasup, text=f"Reserva eliminada").pack()
            else:
                etiqueta = Label(ventanasup, text=f"No se encontró ninguna reserva que coincida con la id").pack()
        ventanasup = Toplevel()
        ventanasup.geometry("400x200")
        ventanasup.title("Eliminar Reserva")
        etiqueta = Label(ventanasup, text="id").pack()
        ids = Entry(ventanasup)
        ids.pack()
        Button(ventanasup, text="Eliminar", padx=7, pady=10, command=buscar).pack()

    def verr():
        def buscar ():
            idl = ids.get()
            id = int(idl)
            t = Registro1.buscarReserva(id)
            if t == False:
                etiqueta = Label(ventanasup, text="No se encontró ninguna reserva que coincida con la id").pack()
            else:

                etiqueta = Label(ventanasup, text=f"{t}").pack()
#
        #
        ventanasup = Toplevel()
        ventanasup.geometry("400x550")
        ventanasup.title("Ver Reservas")
        
        listar = Listbox(ventanasup, width=50)

        etiqueta = Label(ventanasup, text="Reservas: ", width=100).pack()
        k=0
        while k < len(Registro.reservas):
            listar.insert(k,Registro.reservas[k])
            k += 1
        listar.pack()
        etiqueta = Label(ventanasup, text="Tarjetas: ", width=100).pack()
        listat = Listbox(ventanasup, width=50)
        print(Registro.tarjetas)
        k=0
        while k < len(Registro.tarjetas):
            listat.insert(k,Registro.tarjetas[k])
            k += 1

        listat.pack()
        etiqueta = Label(ventanasup, text="para buscar una reserva ingrese la id", width=100).pack()
        ids = Entry(ventanasup)
        ids.pack()
        Button(ventanasup, text="Buscar", padx=7, pady=10, command=buscar).pack()

    def venta():
        def buscar ():
            idl = ids.get()
            id = int(idl)
            l = Registro1.buscarReserva(id)
            s = Registro1.buscarCliente(id)
            if l == False:
                etiqueta = Label(ventanasup, text="Este cliente no tiene ninguna reserva activa", width=100).pack()
            else:
                Registro1.eliminarReserva(l)
                s.setReserva(None)
                Registro1.agregarIngreso()
                t = Registro1.buscarTarjeta(id)
                t.activarTarjeta()
                t.setTarjetaFisica()
                etiqueta = Label(ventanasup, text=f"Imprimiendo tarjeta...\n{t}\nBienvenido al parque. ya puede pasar.", width=100).pack()
                t.agregarEntrada()
            

        ventanasup = Toplevel()
        ventanasup.geometry("400x350")
        ventanasup.title("Pagar reserva")
        etiqueta = Label(ventanasup, text="id", width=100).pack()
        ids = Entry(ventanasup)
        ids.pack()
        Button(ventanasup, text="Pagar", padx=7, pady=10, command=buscar).pack()


############################################################################################
############################################################################################
############################################################################################
############################################################################################
############################################################################################
#Botones de clientes y reservas"""
    def clientesyreservas():
        ventanasup = Toplevel()
        ventanasup.title("Clientes y Reservas")
        ventanasup.geometry("450x500")
        etiqueta = Label(ventanasup, text = "Menu de clientes y reservas", font=("Arial", 15), bg="black", fg = "white")
        etiqueta.pack(fill = X)
        etiqueta = Label(ventanasup, text = "En este menu podra crear, modificar y visualizar clientes reservas y tarjetas\nelija una opcion").pack()
#       etiqueta = Label(ventanasup, text = "Agregar cliente").place(x=40, y=80)
#       etiqueta = Label(ventanasup, text = "Buscar cliente").place(x=40, y=130)
#       etiqueta = Label(ventanasup, text = "Agregar reserva").place(x=40, y=180)
#       etiqueta = Label(ventanasup, text = "Modificar reserva").place(x=40, y=230)
#       etiqueta = Label(ventanasup, text = "Eliminar reserva").place(x=40, y=285) 
#       etiqueta = Label(ventanasup, text = "Ver reservas").place(x=40, y=334)
#       etiqueta = Label(ventanasup, text = "Concretar venta").place(x=40, y=380)
        Button(ventanasup, text="Agregar cliente", padx=53, pady=7, command=agregarc).place(x=129, y=75)
        Button(ventanasup, text="Buscar cliente", padx=56, pady=7, command=buscarc).place(x=129, y=125)
        Button(ventanasup, text="Agregar reserva", padx=52, pady=7, command=agregarr).place(x=129, y=175)
        Button(ventanasup, text="Modificar reserva", padx=47, pady=7, command=modr).place(x=129, y=225)
        Button(ventanasup, text="Eliminar reserva", padx=51, pady=7, command=elimr).place(x=129, y=275)
        Button(ventanasup, text="Ver reservas", padx=61, pady=7, command=verr).place(x=129, y=325)
        Button(ventanasup, text="Concretar venta", padx=50, pady=7, command=venta).place(x=129, y=375)
        

############################################################################################
############################################################################################
############################################################################################
############################################################################################
############################################################################################

#   etiqueta = Label(ventana, text = "Menu de clientes y reservas").place(x=40, y=170)
    agregaringreso = Button(ventana, text = "Menu de clientes y reservas", padx=42, pady=7, command=clientesyreservas)
    agregaringreso.place(x=90, y=165)
    
    #

    def saldo():

        def buscar ():

            def cargar():
                saldol = saldos.get()
                saldo = int(saldol)
                t.cargarTarjeta(t, saldo)
                etiqueta = Label(ventanasup, text=f"su tarjeta fue cargada con {saldo}\n su tarjeta es {t}").grid(row= 6, column=0, sticky=E)

            idl = ids.get()
            id = int(idl)
            t = Registro1.buscarTarjeta(id)
            if t!= False  :
                if t.getTarjetaFisica !=False:
                    etiqueta = Label(ventanasup, text=f"su saldo es {t.getSaldo()}" + "\nCuanto desea cargar?  ").grid(row= 5, column=1, sticky=E)
                    saldos = Entry(ventanasup)
                    saldos.grid(row= 6, column=0)
                    Button(ventanasup, text="Pagar", padx=7, pady=10, command=cargar).grid(row= 6, column=1, sticky=E)
                else:
                    etiqueta = Label(ventanasup, text=f"Su tarjeta no esta activa  ").grid(row= 5, column=1, sticky=E)
            else:
                etiqueta = Label(ventanasup, text=f"no se encontró esta tarjeta").grid(row= 5, column=1, sticky=E)

        ventanasup = Toplevel()
        ventanasup.geometry("600x300")
        ventanasup.title("Cargar saldo")
        etiqueta = Label(ventanasup, text="id").grid(row= 3, column=0)
        ids = Entry(ventanasup)
        ids.grid(row= 3, column=1)
        Button(ventanasup, text="Buscar", padx=30, pady=6, command=buscar).grid(row= 4, column=1, sticky=E)

    def tiquete ():

        def buscar():

                def instalacion(nombre):

                    i = Registro1.buscarInstalacion(nombre)
                    if i != False and tipo == i.getEdadRestriccion():
                        costo = i.getCosto()
                        if t.getSaldo() >= costo:
                            tiquet = Tiquete(i, id)
                            if tiquet.comprarTiquete(t) == True:
                                messagebox.showinfo(f"Su saldo es de {t.getSaldo()}")
                            else:
                                pass
                        else:
                            messagebox.showinfo("No tiene suficiente saldo como para entrar a esta atraccion")
                    else:
                        messagebox.showinfo("No tenemos ninguna instalacion con este nombre")
                

                idl = ids.get()
                id = int(idl)
                t = Registro1.buscarTarjeta(id)     
                if t!= False  :                                                      #si la tarjeta existe
                    if t.getTarjetaFisica !=False:                                   #si la tarjeta esta activada
                        print(t)
                        tipo = t.getTipo()
                        
                        ventanasup1 = Toplevel()
                        ventanasup1.geometry("400x350")
                        ventanasup1.title("Tiquete")
                        lista=[]
                        for x in range(len(Registro.instalaciones)):
                            lista.append(Registro.instalaciones[x].getNombre())

                        k=0
                        while k < len(lista):
                            etiqueta = Label(ventanasup1, text=lista[k])
                            etiqueta.grid(row = k, column=0)
                            etiqueta2 = Label(ventanasup1, text=Registro.instalaciones[k].getEdadRestriccion())
                            etiqueta2.grid(row = k, column=1)
                            Button(ventanasup1, text=f"opcion {k+1}", padx=7, pady=10, command=instalacion).grid(row = k, column=2)
                            k += 1
                        k+=1
                        etiqueta = Label(ventanasup1, text="Seleccione una opcion para comprar el tiquete").grid(row=k, column=1)



                    else:
                        etiqueta = Label(ventanasup, text="La tarjeta no esta activa", width=100).grid(row=k+2, column=1)
                else:
                    etiqueta = Label(ventanasup, text="No tenemos ninguna tarjeta que coincida con el id", width=100).grid(row=k+3, column=1)


        ventanasup = Toplevel()
        ventanasup.geometry("200x100")
        ventanasup.title("Comprar Tiquete")
        etiqueta = Label(ventanasup, text="id")
        etiqueta.grid(row=0, column=0, sticky=W)
        ids = Entry(ventanasup)
        ids.grid(row=0, column=1)
        Button(ventanasup, text="Buscar", padx=7, pady=10, command=buscar).grid(row = 2, column=1)

   

    #Botones de acceso a instalaciones
    def acceso():
        ventanasup = Toplevel()
        ventanasup.title("Clientes y Reservas")
        ventanasup.geometry("360x250")
        etiqueta = Label(ventanasup, text = "Menu de acceso a instalaciones", font=("Arial", 15), bg="black", fg = "white")
        etiqueta.pack(fill = X)
        descripcion = Label(ventanasup, text = "Aquí puede recargar salgo a la tarjeta del cliente \n o concretar la compra de tiquetes para instalaciones").pack()
        Button(ventanasup, text="Cargar saldo", padx=22, pady=10, command=saldo).place(x=120, y=100)
        Button(ventanasup, text="Comprar tiquete", padx=11, pady=10, command=tiquete).place(x=120, y=165)

############################################################################################
############################################################################################
############################################################################################
############################################################################################
############################################################################################

#   etiqueta = Label(ventana, text = "Acceso a instalaciones").place(x=40, y=220)
    agregaringreso = Button(ventana, text = "Acceso a instalaciones", padx=55, pady=7, command=acceso)
    agregaringreso.place(x=90, y=215)


#Botoenes "Gestión de instalaciones"

    def avanzado():

        def veri ():

            ventanasup = Toplevel()
            ventanasup.geometry("480x400")
            ventanasup.title("Ver Instalaciones")

            listar = Listbox(ventanasup, width=50)

            etiqueta = Label(ventanasup, text="Instalaciones: ", width=300).pack()
            k=0
            while k < len(Registro.instalaciones):
                listar.insert(k,Registro.instalaciones[k])
                k += 1
            listar.pack()

        def agi ():

            def niños():
                
                nombre = nombresn.get()
                r = Registro1.buscarInstalacion(nombre)
                if r != False:
                    etiqueta = Label(ventanasup1, text = "ya hay una instalacion con este nombre").pack()
                else:
                    instalacion = InstalacionMenores(nombre)
                    Registro1.agregarInstalacion(instalacion)
                    etiqueta = Label(ventanasup1, text = "Instalacion registrada").pack()
            
            def adultos():

                nombre = nombresa.get()
                r = Registro1.buscarInstalacion(nombre)
                if r != False:
                    etiqueta = Label(ventanasup1, text = "ya hay una instalacion con este nombre").pack()
                else:
                    instalacion = InstalacionAdultos(nombre)
                    Registro1.agregarInstalacion(instalacion)
                    etiqueta = Label(ventanasup1, text = "Instalacion registrada").pack()

            ventanasup1 = Toplevel()
            ventanasup1.title("Gestión de instalaciones")
            ventanasup1.geometry("400x350")
            etiqueta = Label(ventanasup1, text = "Tipo de instalacion",font=("Arial", 12)).pack()
            etiqueta = Label(ventanasup1, text = "Instalacion Niños").pack()
            nombresn = Entry(ventanasup1)
            nombresn.pack()
            Button(ventanasup1, text="Crear", padx=30, pady=10, command=niños).pack()
            etiqueta = Label(ventanasup1, text = "Instalacion Adultos").pack()
            nombresa = Entry(ventanasup1)
            nombresa.pack()
            Button(ventanasup1, text="Crear", padx=25, pady=10, command=adultos).pack()
            print("f")

        def mant ():

            def solicitar ():
                nombre = nombresn.get()
                instalacion = instalacion.instalacionesDeshuso(nombre)
                if instalacion != False:
                   instalacion.realizarMantenimiento()
                else:
                    print("no se encontro una instalacion que requiera mantenimiento con este nombre")


            ventanasup1 = Toplevel()
            ventanasup1.title("Gestión de instalaciones")
            ventanasup1.geometry("400x350")
            instal = Instalacion.mostrarSolicitudes()
            if instal != False:
                
                instalacion = Label(ventanasup1, text = "Seleccione una:").pack()

                listar = Listbox(ventanasup1, width=50)

                etiqueta = Label(ventanasup, text="Mantenimientos: ", width=100).pack()
                k=0
                while k < len(Instalacion.Mantenimientos):
                    listar.insert(k,Instalacion.Mantenimientos[k])
                    k += 1
                listar.pack()                

                nombresn = Entry(ventanasup1)
                nombresn.pack()
                Button(ventanasup1, text="Crear", padx=30, pady=10, command=solicitar).pack()

                activar = input("Seleccione una:  ")
                instalacion = instalacion.instalacionesDeshuso(activar)
                if instalacion != False:
                   instalacion.realizarMantenimiento()
                else:
                    etiqueta = Label(ventanasup1, text = "no se encontro una instalacion que requiera mantenimiento con este nombre").pack()
            else:
                etiqueta = Label(ventanasup1, text = "Por el momento no hay ninguna instalacion que necesite mantenimiento").pack()
        
        #
        
        #
            print("f")

        ventanasup = Toplevel()
        ventanasup.title("Opciones Avanzadas")
        ventanasup.geometry("300x280")
        etiqueta = Label(ventanasup, text = "Gestión de instalaciones", font=("Arial", 15), bg="black", fg = "white")
        etiqueta.pack(fill=X)
        etiqueta1 = Label(ventanasup, text = "Aquí puede ver, agregar o verificar si \n una instalación requiere mantenimiento").pack()
        Button(ventanasup, text="Ver instalaciones", padx=25, pady=10, command=veri).place(x=80, y= 100)
        Button(ventanasup, text="Agregar instalaciones", padx=12, pady=10, command=agi).place(x=80, y= 150)
        Button(ventanasup, text="Hacer mantenimiento", padx=11, pady=10, command=mant).place(x=80, y= 200)



#

    instal = Instalacion.mostrarSolicitudes()
    if instal == False:
        print("Por el momento no hay ninguna instalacion que necesite mantenimiento")
    else:
        activar = input("Seleccione una:  ")
        instalacion =   Instalacion.instalacionesDeshuso(activar)
        if instalacion != False:
           instalacion.realizarMantenimiento()
        else:
            print("no se encontro una instalacion que requiera mantenimiento con este nombre")



#
############################################################################################
############################################################################################
############################################################################################
############################################################################################
############################################################################################
    #Botoenes "Gestión de instalaciones"

#   etiqueta = Label(ventana, text = "Gestión de instalaciones").place(x=40, y=270)
    agregaringreso = Button(ventana, text = "Gestión de instalaciones", padx=50, pady=7, command=avanzado)
    agregaringreso.place(x=90, y=265)

    """
    barraMenu = Menu(ventana)
    menuArchivo = Menu(barraMenu)
    menuArchivo.add_command(label="Abrir")
    """
    ventana.mainloop()

    # menu normal y tkinter
    x = -8
    Deserializador.deserializar()
    while (x == -8):

        #Creacion del registro
        #Registro1 = Registro()

        print("\n Bienvenido a la taquilla\n************************************")
        print("1. Verificar disponibilidad del parque")
        print("2. Agregar ingreso")
        print("3. Clientes y reservas")
        print("4. Acceso a instalaciones")
        print("6. Gestión de instalaciones")
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
        

                        







