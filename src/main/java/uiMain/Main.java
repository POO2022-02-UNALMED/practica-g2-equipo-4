package uiMain;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorAplicacion.servicios.*;
import gestorAplicacion.instalaciones.*;

public class Main {
    
    public static void main(String args[]){
        Deserializador.deserializar();
        //código...
        Serializador.serializar();
        System.exit(0);
    }
    
}
