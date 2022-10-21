package gestorAplicacion.instalaciones;

//importar la clase cliente...
import java.util.ArrayList;
import java.io.Serializable;

public class InstalacionMenores extends Instalacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private ArrayList<Object> clientesQueSubieron = new ArrayList<>();
    private static ArrayList<Instalacion> instalacionesMenores = new ArrayList<>();
    
    
    public InstalacionMenores(String nombre,int edadRestriccion, int alturaRestriccion){
        super(nombre,edadRestriccion, alturaRestriccion);
        instalacionesMenores.add(this);
    }
    
    @Override
    public void montarCliente(Object cliente){
        clientesQueSubieron.add(cliente);
    }
    
    
    @Override
    public String informacionInstalacion(){
        return "Nombre instalacion: "+getNombre()+
                "\nEdad restricción: "+getEdadRestriccion()+
                "\nAltura restricción: "+getAlturaRestriccion();
    }
    
    @Override
    public String tipoInstalacion(){
        return "Instalación para Menores de edad";
    }
    
    public static void setInstalacionesMenores(ArrayList<Instalacion> im){
        instalacionesMenores = im;
    }
    
    public static ArrayList<Instalacion> getInstalacionesMenores(){
        return instalacionesMenores;
    }
    
}