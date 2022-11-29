from abc import ABC, abstractmethod

class Instalacion(ABC):

    """Para guardar en el serializador"""
    Mantenimientos = []

    def __init__(self, nombre, edadRestriccion, costo):
        self._nombre = nombre
        self._edadRestriccion = edadRestriccion     #Adultos o infantes
        self._costo = costo
        self._usosAntes = 0
        self._necesitaManteniemiento = False

    def getNecesitaMantenimiento(self):
        return self._necesitaManteniemiento

    def realizarMantenimiento(self):
        self._necesitaManteniemiento = False
        self._usosAntes = 0
        print("La instalacion " ,self.getNombre()," ya se encuentra en uso.")

    def solicitarMantenimiento(self):
        self._necesitaManteniemiento = True
        solicitud = Instalacion.instalacionesDeshuso(self._nombre)
        if solicitud == False:
            Instalacion.agregarMantenimiento(self)

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
    
    def getUsosAntes(self):
        return self._usosAntes

    def sumarUsosAntes(self):
        self._usosAntes += 1

    @classmethod
    def mostrarSolicitudes(clc):
        if len(clc.Mantenimientos) == 0:
            return False
        print("Solicitudes de mantenimiento:")
        k=0
        while k < len(clc.Mantenimientos):
            print("Nombre: ",clc.Mantenimientos[k].getNombre(), " Resestriccion: ",clc.Mantenimientos[k].getEdadRestriccion())
            k += 1
    
    @abstractmethod
    def informacionInstalacion(self):
        """metodo que muestra en pantallaquienes pueden entrar a la instalacion"""
        pass

    @abstractmethod
    def agregarInstalacion(self):
        """metodo para agregar una nueva instalacion al parque"""
        pass

    @classmethod
    def instalacionesDeshuso(clc, nombre):
        for k in range(len(clc.Mantenimientos)):
            if clc.Mantenimientos[k].getNombre() == nombre:
                return clc.Mantenimientos[k]
        return False

    @classmethod
    def agregarMantenimiento(clc, i):
        clc.Mantenimientos.append(i)
        return True

