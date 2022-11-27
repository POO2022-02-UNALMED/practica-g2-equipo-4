from reserva import Reserva

class Tarjeta:

    tipoTarjeta = {"Adulto", "Infante"}
    costoTarjeta = {100, 50}
    atraccionesMontadas = 0
    def __init__(self, idTarjeta, costo, tipoT, tarjetaFisica):
        self._idTarjeta = idTarjeta
        self._costo = costo 
        self._tipoT = tipoT
        self._tarjetaFisica = tarjetaFisica
        self._activa = False
        self._cantidadDeEntradas = 0
        self._saldo = 0

    def getActiva(self):
        return self._activa

    def cargarTarjeta(self, tarjeta, saldo):
        if tarjeta.getActiva():
            tarjeta.agregarSaldo(saldo)
            return "su tarjeta ha sido cargada con" + saldo 

        else:
            return "su tarjeta no está activa"

    def agregarSaldo(self, saldo):
        self._saldo += saldo

    def agregarEntrada(self):
        self._cantidadDeEntradas +=1

        if self._cantidadDeEntradas == 3:
            self._cantidadDeEntradas=0
            return print ("Aplica para descuento")
        else:
            return print("Faltan "+ (3-self._cantidadDeEntradas) +" entradas para tener descuento")

    def activarTarjeta(self):
	    self._activa = True

	
