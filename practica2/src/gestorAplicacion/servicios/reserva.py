from registro import Registro

class Reserva:

    def __init__(self, idReserva, fecha):
        self._idReserva = idReserva
        self._fecha = fecha
        self._activa = True
        self._tarjeta = None

    def setFecha(self, fecha):
        self._fecha = fecha

    def getFecha(self, fecha):
        return self._fecha

    def setActiva(self):
        self._activa = True
    
    def getActiva(self):
        return self._activa
    
    def setDesactiva(self):
        self._activa = False
    

    def cancelarReserva(self):
        r = Registro.existeReserva(self._idReserva)
        if r == True:
            self._idReserva = False
            self.setFecha = None
            Registro.eliminarReserva(self._idReserva)
        
            return "Reserva cancelada"

        else:
            return "La reserva no existe"

    def modificarReserva(self, fecha_nueva):      
        r = Registro.existeReserva(self._idReserva)
        if r == True:
            obj = Registro.buscarReserva(self._idReserva).reserva
            obj._activa = True
            obj.setFecha(fecha_nueva)
            return "Reserva modificada"
        
        else:
            return "La reserva no existe"
            
