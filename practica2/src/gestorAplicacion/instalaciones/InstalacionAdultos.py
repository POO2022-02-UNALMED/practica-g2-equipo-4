from instalacion import Instalacion

class InstalacionAdultos(Instalacion):

    def __init__(self, nombre, edadRestriccion):
        Instalacion.__init__(self,nombre, edadRestriccion)
