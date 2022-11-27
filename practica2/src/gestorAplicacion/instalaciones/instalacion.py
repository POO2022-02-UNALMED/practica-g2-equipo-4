
class Instalacion:

    
    def __init__(self, nombre, edadRestriccion, costo):
        self._nombre = nombre
        self._edadRestriccion = edadRestriccion
        self._costo = costo
        MANTENIMIENTO = 5000
        usosAntes = 0

    def sumarUsosAntes(self):
        if self.MANTENIEMIENTO < self.usosAntes:
            self.usosAntes += 1
            return True
        return False

    def setEdadRestriccion (self, edadRestriccion):
        self._edadRestriccion = edadRestriccion
    
    
    def getEdadRestriccion(self):
        return self._edadRestriccion
        
    def setNombre(self, nombre):
        self._nombre = nombre
    
    def getNombre(self):
        return self._nombre
    
    def setCosto(self, costo):
        self._costo = costo
    
    def getCosto(self):
        return self._costo
    
    

