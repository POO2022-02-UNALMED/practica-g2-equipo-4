from .instalacion import Instalacion
from servicios.registro import Registro

class InstalacionAdultos(Instalacion):

    def __init__(self, nombre):
        Instalacion.__init__(self,nombre, edadRestriccion= "Adulto", costo = 100)
        self._MANTENIMIENTO = 500

    def agregarInstalacion(self):
        """metodo para agregar una nueva instalacion al parque"""
        if not (Registro.instalaciones.containsKey(self.getNombre())):
            Registro.instalaciones.put(self.getNombre(), self)
            return "Instalacion agregada con exito"
            
        return "ya exite una instalacion con ese nombre"

    def getCosto(self):
        return self._costo

    def informacionInstalacion(self):
        """metodo que muestra en pantalla quienes pueden entrar a la instalacion"""
        return f"Esta es una instalacion para mayores de 16"
        
    def setEdadRestriccion (self, edadRestriccion):
        self._edadRestriccion = edadRestriccion
    
    
    def getEdadRestriccion(self):
        return self._edadRestriccion
        
    def setNombre(self, nombre):
        self._nombre = nombre
    
    def getNombre(self):
        return self._nombre

    def sumarUsoAntes(self):
        if self._MANTENIMIENTO > super().getUsosAntes():
            super().sumarUsosAntes()
            return True
        super().solicitarMantenimiento()
        return False

    def __str__(self):
        return f"Nombre: {self.getNombre()}, Restriccion: Mayores,  Costo: 100, Mantenimiento: {super().getNecesitaMantenimiento()}"
