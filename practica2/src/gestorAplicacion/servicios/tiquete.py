
class Tiquete:

    def __init__(self, instalacion, id):
        self._Instalacion = instalacion
        self._costo = instalacion.getCosto()
        self._id = id

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
    