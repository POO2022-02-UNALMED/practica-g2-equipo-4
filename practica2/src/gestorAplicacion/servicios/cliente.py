from registro import Registro
class Cliente:

    def __init__(self, tipoId, id, edad):
        self._tipoId = tipoId
        self._id = id
        self._edad = edad
        self._tarjeta = None
        self._reserva = None

    def getEdad(self):
        return self._edad

    def getId(self):
        return self._id

    def getTipoId(self):
        return self._tipoId

    def corregirDatos(self, nueva_tipoId, nueva_id):
        self._tipoId = nueva_tipoId
        self._id = nueva_id

    def __str__(self):

        return 'id: '+(self.getId()) + 'edad: ' + (self.getEdad) 

    


