package gestorAplicacion.instalaciones;

//importar la clase cliente...
import gestorAplicacion.servicios.Registro;

import java.util.ArrayList;
import java.io.Serializable;

public class InstalacionMenores extends Instalacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /*private ArrayList<Object> clientesQueSubieron = new ArrayList<>();
    private static ArrayList<Instalacion> instalacionesMenores = new ArrayList<>();*/
    
    
    public InstalacionMenores(String nombre,int edadRestriccion){
        super(nombre,edadRestriccion);

    }
    
    @Override
    public String agregarInstalacion(){
        if(!Registro.instalaciones.containsKey(this.getNombre())){
            Registro.instalaciones.put(this.getNombre(), this);
            return "Instalación agregada con éxito";
        }
        return "Ya existe una instalación con ese nombre";
    }
    
    
    @Override
    public String informacionInstalacion(){
        return "Nombre instalacion: "+getNombre()+
                "\nEdad restricción: "+getEdadRestriccion();
    }
    
    @Override
    public String tipoInstalacion(){
        return "Instalación para Menores de: 16 años";
    }
    
/*    public static void setInstalacionesMenores(ArrayList<Instalacion> im){
        instalacionesMenores = im;
    }
    
    public static ArrayList<Instalacion> getInstalacionesMenores(){
        return instalacionesMenores;
    }*/
    
}