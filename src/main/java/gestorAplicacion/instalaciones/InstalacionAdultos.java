package gestorAplicacion.instalaciones;

//importar la clase cliente
import java.util.ArrayList;
import java.io.Serializable;

public class InstalacionAdultos extends Instalacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private ArrayList<Object> clientesQueSubieron = new ArrayList<>();
    private static ArrayList<Instalacion> instalacionesAdultos = new ArrayList<>();
    
    
    public InstalacionAdultos(String nombre, int alturaRestriccion){
        super(nombre,alturaRestriccion);
        instalacionesAdultos.add(this);
    }
    
    @Override
    public void montarCliente(Object cliente){
        clientesQueSubieron.add(cliente);
    }
    
    @Override
    public String informacionInstalacion(){
        return "Nombre instalacion: "+getNombre()+
                "\nEdad restricción: 18"+
                "\nAltura restricción: "+getAlturaRestriccion();
    }
    
    @Override
    public String tipoInstalacion(){
        return "Instalación para adultos";
    }
    
    public static void setInstalacionesAdultos(ArrayList<Instalacion> ia){
        instalacionesAdultos = ia;
    }
    
    public static ArrayList<Instalacion> getInstalacionesAdultos(){
        return instalacionesAdultos;
    }
    
}
