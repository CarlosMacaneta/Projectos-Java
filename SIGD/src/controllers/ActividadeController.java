
package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import models.dao.Actividades;

/**
 *
 * @author CarlosMacaneta
 */
public class ActividadeController {
    
    private static final String URI = "src/arquivos/act.dat";

    private static void create(ArrayList<Actividades> fnc){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(fnc);
                oos.close();
            }catch(FileNotFoundException ex){}
        }catch(IOException ex){}
    }

    public static boolean create(Actividades act){
        if(Files.notExists(Paths.get(URI))){
            ArrayList<Actividades> act_ = new ArrayList();
            act_.add(act);
            create(act_);
            return true;
        }else{
            ArrayList<Actividades> act_ = lista();
            act_.add(act);
            create(act_);
            return true;
        }
    }

    public static ArrayList<Actividades> lista(){
        ArrayList<Actividades> list = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<Actividades>) ois.readObject();
                ois.close();
            }catch(FileNotFoundException|ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e, "ERROR", ERROR_MESSAGE);
            }
        }catch(IOException ex){}
        return list;
    }
    
    public static ArrayList<Actividades> lista(int fk){
        ArrayList<Actividades> list = new ArrayList<>();
        
        if(!lista().isEmpty()){
            lista().forEach((l)->{
                if(l.getFk() == fk)
                    list.add(l);
            });
        }
        return list;
    }

    public static void update(int id, Actividades act){
        ArrayList<Actividades> acts = lista();
        if(!acts.isEmpty()){
            for (int i = 0; i < acts.size(); i++) {
                if(acts.get(i).getId() == id){
                    acts.remove(i);
                    acts.add(i, act);
                    create(acts);
                    break;
                }
            }
        }
    }
    
    public static void update(Actividades act, int fk){
        ArrayList<Actividades> acts = lista();
        if(!acts.isEmpty()){
            for (int i = 0; i < acts.size(); i++) {
                if(acts.get(i).getFk() == fk){
                    acts.remove(i);
                    acts.add(i, act);
                    create(acts);
                    break;
                }
            }
        }
    }

    public static boolean delete(int id){
        ArrayList<Actividades> acts = lista();
        if(!acts.isEmpty()){
            for (Actividades act_ : acts) {
                if(act_.getId() == id){
                    acts.remove(act_);
                    create(acts);
                    return true;
                }
            }
        }
        return false;
    }

    public static int id(){
        if(Files.notExists(Paths.get(URI))){
            return 1;
        }else{
            ArrayList<Actividades> list = lista();

            if(!list.isEmpty()){
                int maior = list.get(0).getId();

                for(Actividades a: list){
                    if(a.getId() > maior){
                        maior = a.getId();
                    }
                }
                return maior+1;
            }
        }
        return 1;
    }
}