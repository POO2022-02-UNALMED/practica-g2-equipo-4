
package gestorAplicacion.servicios;

import gestorAplicacion.instalaciones.Instalacion;

import java.io.Serializable;


public class Tiquete  {
    private Instalacion instalacion;
    private float costo;
    private int id;

    public Tiquete(Instalacion instalacion, int id){
        this.instalacion = instalacion;
        this.costo = instalacion.getCosto();
        this.id = id;
    }
    
    

    public void setInstalacion(Instalacion instalacion){
        this.instalacion = instalacion;
    }
    public Instalacion getInstalacion(){
        return instalacion;
    }
    public void setCosto(float costo){
        this.costo = costo;
    }
    public float getCosto(){
        return costo;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public  boolean comprarTiquete(Tarjeta tj){
        float precio;
        if(tj.getAtracciones()>=5){
             precio = costo*0.8f;
        }
        else{
             precio = costo;
        }
        if(Registro.buscarCliente(tj.idTarjeta).edad >= instalacion.getEdadRestriccion()){
            if(tj.getSaldo() >= precio){
                //if(instalacion.sumarUsoAntes()){
                if(instalacion.sumarUsoAntes()){
                    tj.setSaldo(tj.getSaldo() - precio);
                    tj.sumarAtracciones();
                    return true;
                }
                return false;
            }
        }
        else{
            
            System.out.println("No puede entrar a esta atraccion");
        }
        return false;
    }

    @Override
    public String toString() {
        return "Tiquete{" + "instalacion=" + instalacion + ", costo=" + costo + ", id=" + id + '}';
    }

    
}

