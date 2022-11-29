
class Tiquete:

    def __init__(self, instalacion, id):
        self._Instalacion = instalacion
        self._costo = instalacion.getCosto()
        self._id = id

    def comprarTiquete(self, tarjeta):
        if tarjeta.getAtracciones() == 5 :
            descuento = int(input("Aplica para desceunto del 20% para su proximo tiquete. Quiere utilizarlo?\n1. Si \n2. No\n"))
            if descuento == 1:
                precio = self._costo*0.8
            else:
                precio = self._costo
        else:
            precio = self._costo

        if self._Instalacion.sumarUsoAntes():
            tarjeta.setSaldo(tarjeta.getSaldo()-precio)
            tarjeta.sumarAtracciones()
            return True
        return print("Esta instalacion se encuentra en mantenimiento")

    def setInstalacion(self, instalacion):
        self._instalacion = instalacion
    
    def getInstalacion(self):
        return self._instalacion
    
    def setCosto(self, costo):
        self._costo = costo
    
    def getCosto(self):
        return self._costo
    
    def setId(self, id):
        self._id = id
    
    def getId(self):
        return self._id
    