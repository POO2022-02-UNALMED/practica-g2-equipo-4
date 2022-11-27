

class Registro():
    
    cantidadClientesDiaActual = 0
    capacidadDiaActual = 1000
    clientes = []
    reservas = []
    tarjetas = []
    
    def __init__(self):
        self._clientes = []
        self._reservas = []
        self._tarjetas = []

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
            print("Cliente registrado")
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
        self.tarjetas.append(t)
        print("Tarjeta creada")
        return True
