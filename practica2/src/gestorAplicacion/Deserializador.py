import pickle
from os import remove
from  servicios.registro import Registro
from  instalaciones.instalacion import Instalacion

class Deserializador:
    @classmethod
    def deserializar(clc):

        clientes = open("clientes.pkl","rb")
        Registro.clientes = Registro.clientes +  pickle.load(clientes)
        #clientes.close()

        reservas = open("reservas.pkl","rb")
        Registro.reservas = Registro.reservas + pickle.load(reservas)
        #reservas.close()

        tarjetas = open("tarjetas.pkl","rb")
        Registro.tarjetas = Registro.tarjetas + pickle.load(tarjetas)
        #tarjetas.close()

        instalaciones = open("instalaciones.pkl","rb")
        Registro.instalaciones = Registro.instalaciones  + pickle.load(instalaciones)
        #instalaciones.close()

        mantenimiento = open("mantenimiento.pkl","rb")
        Instalacion.Mantenimientos = Instalacion.Mantenimientos + pickle.load(mantenimiento)
        #mantenimiento.close()

