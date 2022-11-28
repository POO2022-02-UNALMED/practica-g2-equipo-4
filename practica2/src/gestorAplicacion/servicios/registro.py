

class Registro():
    
    cantidadClientesDiaActual = 0
    capacidadDiaActual = 1000

    """Cosas para guardar en el serializador"""
    clientes = []
    reservas = []
    tarjetas = []
    instalaciones = []
    
    
    def __init__(self):
        self._clientes = []
        self._reservas = []
        self._tarjetas = []
        self._instalaciones = []
    

    @classmethod
    def agregarIngreso(cls):
        if(cls.cantidadClientesDiaActual < cls.capacidadDiaActual):
            cls.cantidadClientesDiaActual += 1
            return True
        else:
            return False

    def buscarCliente(self, id):
        for k in range(len(self.clientes)):
            if self.clientes[k].getId() == id:
                return self.clientes[k]
        return False

    def mostrarClientes(self):
        k=0
        while k < len(self.clientes):
            print("Id: ",self.clientes[k].getId()," Edad: ", self.clientes[k].getEdad())
            k += 1

    def mostrarReservas(self):
        k=0
        while k < len(self.reservas):
            print("Id: ",self.reservas[k].getIdReserva()," Fecha: ", self.reservas[k].getFecha())
            k += 1

    def agregarCliente(self, c):
        id = c.getId()
        if self.buscarCliente(id) !=False:
            print("Error: este cliente ya existe")
            return False
        else:
            self.clientes.append(c)
            return True
            
    def buscarReserva(self, id):
        for k in range(len(self.reservas)):
            if self.reservas[k].getIdReserva() == id:
                return self.reservas[k]
        return False
   
    def agregarReserva(self, r):
        id = r.getIdReserva()  
        self.reservas.append(r)
        print("Reserva registrada")
        return True

    def eliminarReserva(self, r):
        x = True
        id = r.getIdReserva()
        print(id)
        print(len(self.reservas))
        for k in range(len(self.reservas)):
            if self.reservas[k].getIdReserva() == id:
                self.reservas.pop(k)
                x=False
        if x:
            print('No se pudo eliminar la reserva')
        
    def buscarTarjeta(self, id):
        for k in range(len(self.tarjetas)):
            if self.tarjetas[k].getIdTarjeta() == id:
                return self.tarjetas[k]
        return False
    
    def agregarTarjeta(self, t):
        id = t.getIdTarjeta() 
        if self.buscarTarjeta(id) == False:
            self.tarjetas.append(t)
            return True
        else:
            False

    def mostrarInstalaciones(self, tipo):
        k = 0
        if tipo == "Adulto" or tipo == "Infante":
            while k < len(self.instalaciones):
                if self.instalaciones[k].getEdadRestriccion() == tipo:
                    print("Instalacion: ",  self.instalaciones[k] )
                    k += 1
                else:
                    k += 1
                    pass
        else:
            while k < len(self.instalaciones):
                print("Instalacion: ",  self.instalaciones[k])
                k += 1

    def buscarInstalacion(self, nombre):
        for k in range(len(self.instalaciones)):
            if self.instalaciones[k].getNombre() == nombre:
                return self.instalaciones[k]
        return False

    def agregarInstalacion(self, i):
        nombre = i.getNombre()
        if self.buscarInstalacion(nombre) !=False:
            print("Ya hay una instalacion con este nombre, elija otro")
            return False
        else:
            self.instalaciones.append(i)
            print("Instalacion registrada")
            return True



    """
    1. Creacion y eliminacion de reservas
    2. bono de descuento cada que etntra 3 veces al parque y cada que entra 5 veces a alguna atraccion
    """
