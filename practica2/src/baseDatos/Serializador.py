import pickle
from  servicios.registro import Registro
from  instalaciones.instalacion import Instalacion

class Serializador:
    @classmethod
    def serializar(cls):

        clientes = open("src/baseDatos/tmp/clientes.pkl","wb")
        pickle.dump(Registro.clientes, clientes)
        clientes.close()

        reservas = open("src/baseDatos/tmp/reservas.pkl","wb")
        pickle.dump(Registro.reservas, reservas)
        reservas.close()

        tarjetas = open("src/baseDatos/tmp/tarjetas.pkl","wb")
        pickle.dump(Registro.tarjetas, tarjetas)
        tarjetas.close()

        instalaciones = open("src/baseDatos/tmp/instalaciones.pkl","wb")
        pickle.dump(Registro.instalaciones, instalaciones)
        instalaciones.close()

        mantenimiento = open("src/baseDatos/tmp/mantenimiento.pkl","wb")
        pickle.dump(Registro.Mantenimiento, mantenimiento)
        mantenimiento.close()

