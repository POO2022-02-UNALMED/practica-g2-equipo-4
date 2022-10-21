package baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import gestorAplicacion.instalaciones.*;
import gestorAplicacion.servicios.*;

public class Serializador {
    private static File rutaTemp = new File("src\\main\\java\\baseDatos\\temp");
    
    public static void serializar(){
        FileOutputStream fos;
        ObjectOutputStream oos;
        File[] docs = rutaTemp.listFiles();
        PrintWriter pw;
        
        for (File file: docs){
            try{
                pw = new PrintWriter(file);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
        
        for (File file: docs){
            if(file.getAbsolutePath().contains("instalacionesAdulto")){
                try{
                    fos = new FileOutputStream(file);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(InstalacionAdultos.getInstalacionesAdultos());
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
                
                
                
                
            }else if(file.getAbsolutePath().contains("instalacionesMenores")){
                try{
                    fos = new FileOutputStream(file);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(InstalacionMenores.getInstalacionesMenores());
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            
            else if(file.getAbsolutePath().contains("reservas")){
                try{
                    fos = new FileOutputStream(file);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(Reserva.getReservas());
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        
    }
}
