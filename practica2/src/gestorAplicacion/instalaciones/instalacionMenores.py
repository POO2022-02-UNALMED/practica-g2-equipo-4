from .instalacion import Instalacion
from servicios.registro import Registro

class InstalacionMenores(Instalacion):

    def __init__(self, nombre):
        Instalacion.__init__(self,nombre, edadRestriccion= "Infante", costo = 50)
        self._MANTENIMIENTO = 300


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
        return f"Esta es una instalacion para menores de 16"
        
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
        return f"Nombre: {self.getNombre()}, Restriccion: Menores,  Costo: 50, Mantenimiento: {super().getNecesitaMantenimiento()}"

