package baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import gestorAplicacion.instalaciones.*;
import gestorAplicacion.servicios.*;

public class Deserializador {
    private static File rutaTemp = new File("src\\main\\java\\baseDatos\\temp");

    public static void deserializar(){
        File[] docs = rutaTemp.listFiles();
        FileInputStream fis;
        ObjectInputStream ois;
        
        for(File file: docs){
            if(file.getAbsolutePath().contains("instalacionesAdultos")){
                try{
                    fis = new FileInputStream(file);
                    ois = new ObjectInputStream(fis);
                    InstalacionAdultos.setInstalacionesAdultos((ArrayList<Instalacion>) ois.readObject());
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
                
            }else if(file.getAbsolutePath().contains("instalacionesMenores")){
                try{
                    fis = new FileInputStream(file);
                    ois = new ObjectInputStream(fis);
                    InstalacionMenores.setInstalacionesMenores((ArrayList<Instalacion>) ois.readObject());
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
            
            
            else if(file.getAbsolutePath().contains("reservas")){
                try{
                    fis = new FileInputStream(file);
                    ois = new ObjectInputStream(fis);
                    Reserva.setReservas((ArrayList<Reserva>) ois.readObject());
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
