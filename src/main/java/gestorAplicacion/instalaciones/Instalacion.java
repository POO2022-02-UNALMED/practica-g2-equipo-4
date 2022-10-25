package gestorAplicacion.instalaciones;

import java.util.ArrayList;
import java.io.Serializable;
public abstract class Instalacion implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private int edadRestriccion;
    /*private int alturaRestriccion;*/
    private int costo;
    final  int usosMantenimiento = 5000;
    private int usosAntes = 0 ;

    public boolean sumarUsoAntes() {
        if (usosMantenimiento< usosAntes) {
            this.usosAntes ++;
            return true;
        }
        return false;
    }
    public void setCosto(int costo){
        this.costo = costo;
    }
    public int getCosto(){
        return costo;
    }
    
    public Instalacion(String nombre, int edadRestriccion){
        this.nombre = nombre;
        this.edadRestriccion = edadRestriccion;
        /*this.alturaRestriccion = alturaRestriccion;*/
    }
    
    public Instalacion(String nombre){
        this.nombre = nombre;
        /*this.alturaRestriccion = alturaRestriccion;*/
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setEdadRestriccion(int edadRestriccion){
        this.edadRestriccion = edadRestriccion;
    }
    
    public int getEdadRestriccion(){
        return edadRestriccion;
    }
    
/*    public void setAlturaRestriccion(int alturaRestriccion){
        this.alturaRestriccion = alturaRestriccion;
    }*/
    
/*    public int getAlturaRestriccion(){
        return alturaRestriccion;
    }*/
    
    public abstract String informacionInstalacion();
    public abstract String tipoInstalacion();
    public abstract String agregarInstalacion();
}
