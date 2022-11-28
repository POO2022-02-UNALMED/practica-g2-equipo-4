import pickle
from  servicios.registro import Registro
from  instalaciones.instalacion import Instalacion

class Serializador:
    @classmethod
    def serializar(cls):

        clientes = open("clientes.pkl","wb")
        pickle.dump(Registro.clientes, clientes)
        clientes.close()
        del (clientes)

        reservas = open("reservas.pkl","wb")
        pickle.dump(Registro.reservas, reservas)
        reservas.close()
        del (reservas)

        tarjetas = open("tarjetas.pkl","wb")
        pickle.dump(Registro.tarjetas, tarjetas)
        tarjetas.close()

        instalaciones = open("instalaciones.pkl","wb")
        pickle.dump(Registro.instalaciones, instalaciones)
        instalaciones.close()
        del (instalaciones)

        mantenimiento = open("mantenimiento.pkl","wb")
        pickle.dump(Instalacion.Mantenimientos, mantenimiento)
        mantenimiento.close()
        
