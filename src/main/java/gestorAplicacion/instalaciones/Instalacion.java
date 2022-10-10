package gestorAplicacion.instalaciones;

import java.util.ArrayList;
import java.io.Serializable;

public class Instalacion implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private static ArrayList<Instalacion> instalaciones = new ArrayList<>();
    
    public Instalacion(){
        instalaciones.add(this);
    }
    
    public static void setInstalaciones(ArrayList<Instalacion> ins){
        instalaciones = ins;
    }
    
    public static ArrayList<Instalacion> getInstalaciones(){
        return instalaciones;
    }
}
