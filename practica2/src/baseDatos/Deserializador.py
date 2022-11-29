import pickle
from  servicios.registro import Registro
from  instalaciones.instalacion import Instalacion

class Deserializador:
    @classmethod
    def deserializar(cls):

        clientes = open("src/baseDatos/tmp/clientes.pkl","rb")
        Registro.clientes = pickle.load(clientes)
        clientes.close()

        reservas = open("src/baseDatos/tmp/reservas.pkl","rb")
        Registro.reservas = pickle.load(reservas)
        reservas.close()

        tarjetas = open("src/baseDatos/tmp/tarjetas.pkl","rb")
        Registro.tarjetas = pickle.load(tarjetas)
        tarjetas.close()

        instalaciones = open("src/baseDatos/tmp/instalaciones.pkl","rb")
        Registro.instalaciones = pickle.load(instalaciones)
        instalaciones.close()

        mantenimiento = open("src/baseDatos/tmp/mantenimiento.pkl","rb")
        Registro.mantenimiento = pickle.load(mantenimiento)
        mantenimiento.close()