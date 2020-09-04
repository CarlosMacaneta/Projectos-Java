package controllers;

import models.dao.Gestor;

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

public class GestorController {


    private static final String URI = "src/arquivos/gest.dat";

    private void create(ArrayList<Gestor> fnc){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(fnc);
                oos.close();
            }catch(FileNotFoundException ex){}
        }catch(IOException ex){}
    }

    public boolean create(Gestor func){
        if(Files.notExists(Paths.get(URI))){
            ArrayList<Gestor> func_ = new ArrayList();
            func_.add(func);
            create(func_);
            return true;
        }else{
            ArrayList<Gestor> func_ = lista();
            func_.add(func);
            create(func_);
            return true;
        }
    }

    public ArrayList<Gestor> lista(){
        ArrayList<Gestor> list = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<Gestor>) ois.readObject();
                ois.close();
            }catch(FileNotFoundException|ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e, "ERROR", ERROR_MESSAGE);
            }
        }catch(IOException ex){}
        return list;
    }

    public ArrayList<Gestor> pesquisaNome(String nome){
        ArrayList<Gestor> funcs = new ArrayList<>();

        if(!lista().isEmpty()){
            lista().forEach((nome_) ->{
                if(nome_.getNome().equalsIgnoreCase(nome)){
                    funcs.add(nome_);
                }
            });
        }
        return funcs;
    }

    public void update(int id, Gestor func){
        ArrayList<Gestor> funcs = lista();
        if(!funcs.isEmpty()){
            for (int i = 0; i < funcs.size(); i++) {
                if(funcs.get(i).getId() == id){
                    funcs.remove(i);
                    funcs.add(i, func);
                    create(funcs);
                    break;
                }
            }
        }
    }

    public void delete(int id){
        ArrayList<Gestor> funcs = lista();
        if(!funcs.isEmpty()){
            for (Gestor func_ : funcs) {
                if(func_.getId() == id){
                    funcs.remove(func_);
                    create(funcs);
                    break;
                }
            }
        }
    }
}
