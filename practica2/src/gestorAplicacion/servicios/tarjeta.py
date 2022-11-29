
class Tarjeta:

    tipoTarjeta = {"Adulto", "Infante"}
    costoTarjeta = {100, 50}
    atraccionesMontadas = 0
    def __init__(self, idTarjeta, costo, tipoT,):
        self._idTarjeta = idTarjeta
        self._costo = costo 
        self._tipoT = tipoT
        self._tarjetaFisica = False
        self._activa = False
        self._cantidadDeEntradas = 0
        self._saldo = 0
        self._atraccionesMontadas = 0


    def __str__(self):
        return f"Id: {self.getIdTarjeta()}, Tipo: {self._tipoT}, Entradas: {self._cantidadDeEntradas}, Saldo: {self._saldo}, Estado: {self._activa}"

    def getTipo(self):
        return self._tipoT

    def getIdTarjeta(self):
        return self._idTarjeta

    def getActiva(self):
        return self._activa

    def getSaldo(self):
        return self._saldo

    def setSaldo(self, saldo):
        self._saldo = saldo

    def cargarTarjeta(self, tarjeta, saldo):
        tarjeta.agregarSaldo(saldo)
        return True

    def agregarSaldo(self, saldo):
        self._saldo += saldo

    
    def sumarAtracciones(self):
        self._atraccionesMontadas += 1
        if self.getAtracciones() ==6 :              #Si ha montado en 5 atracciones vuelve a empezar para el descuento
            self._atraccionesMontadas = 0

    def getAtracciones(self):
        return self._atraccionesMontadas
        


    def agregarEntrada(self):
        self._cantidadDeEntradas +=1
        if self._cantidadDeEntradas == 3:
            self._cantidadDeEntradas=0
            print ("\n**Aplica para descuento**\n")
            return True
        else:
            return print("Faltan ",  3-self._cantidadDeEntradas ," visitas al parque para tener descuento")

    def setTarjetaFisica(self):
        self._tarjetaFisica = True

    def getTarjetaFisica(self):
        return self._tarjetaFisica

    def activarTarjeta(self):
	    self._activa = True
