import pickle
from  servicios.registro import Registro
from  instalaciones.instalacion import Instalacion

class Deserializador:
    @classmethod
    def deserializar(cls):

        clientes = open("/baseDatos/clientes.pkl","rb")
        Registro.clientes = pickle.load(clientes)
        clientes.close()

        reservas = open("reservas.pkl","rb")
        Registro.reservas = pickle.load(reservas)
        reservas.close()

        tarjetas = open("tarjetas.pkl","rb")
        Registro.tarjetas = pickle.load(tarjetas)
        tarjetas.close()

        instalaciones = open("instalaciones.pkl","rb")
        Registro.instalaciones = pickle.load(instalaciones)
        instalaciones.close()

        mantenimiento = open("mantenimiento.pkl","rb")
        Registro.mantenimiento = pickle.load(mantenimiento)
        mantenimiento.close()