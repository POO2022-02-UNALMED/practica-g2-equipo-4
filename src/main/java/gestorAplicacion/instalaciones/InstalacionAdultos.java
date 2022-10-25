package gestorAplicacion.instalaciones;

//importar la clase cliente
import gestorAplicacion.servicios.Registro;

import java.util.ArrayList;
import java.io.Serializable;

public class InstalacionAdultos extends Instalacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /*private ArrayList<Object> clientesQueSubieron = new ArrayList<>();
    private static ArrayList<Instalacion> instalacionesAdultos = new ArrayList<>();*/
    
    
    public InstalacionAdultos(String nombre, int restriccionEdad){
        super(nombre,restriccionEdad);
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
                "\nEdad restricción: 16";
    }
    
    @Override
    public String tipoInstalacion(){
        return "Instalación para adultos";
    }
    
   /* public static void setInstalacionesAdultos(ArrayList<Instalacion> ia){
        instalacionesAdultos = ia;
    }
    
    public static ArrayList<Instalacion> getInstalacionesAdultos(){
        return instalacionesAdultos;
    }*/
    
}
