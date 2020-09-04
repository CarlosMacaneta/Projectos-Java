package controllers;

import models.dao.Financa;
import models.dao.Financa;

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
import models.dao.Financa;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class FinancaController {

    private static final String URI = "src/arquivos/fin.dat";

    private static void create(ArrayList<Financa> fnc){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(fnc);
                oos.close();
            }catch(FileNotFoundException ex){}
        }catch(IOException ex){}
    }

    public static boolean create(Financa fin){
        if(Files.notExists(Paths.get(URI))){
            ArrayList<Financa> fin_ = new ArrayList();
            fin_.add(fin);
            create(fin_);
            return true;
        }else{
            ArrayList<Financa> fin_ = lista();
            fin_.add(fin);
            create(fin_);
            return true;
        }
    }

    public static ArrayList<Financa> lista(){
        ArrayList<Financa> list = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<Financa>) ois.readObject();
                ois.close();
            }catch(FileNotFoundException|ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e, "ERROR", ERROR_MESSAGE);
            }
        }catch(IOException ex){}
        return list;
    }

    public static void update(int id, Financa fin){
        ArrayList<Financa> fins = lista();
        if(!fins.isEmpty()){
            for (int i = 0; i < fins.size(); i++) {
                if(fins.get(i).getId() == id){
                    fins.remove(i);
                    fins.add(i, fin);
                    create(fins);
                    break;
                }
            }
        }
    }

    public static void delete(int id){
        ArrayList<Financa> fins = lista();
        if(!fins.isEmpty()){
            for (Financa fin_ : fins) {
                if(fin_.getId() == id){
                    fins.remove(fin_);
                    create(fins);
                    break;
                }
            }
        }
    }

    public static int id(){
        if(Files.notExists(Paths.get(URI))){
            return 1;
        }else{
            ArrayList<Financa> list = lista();

            if(!list.isEmpty()){
                int maior = list.get(0).getId();

                for(Financa p: list){
                    if(p.getId() > maior){
                        maior = p.getId();
                    }
                }
                return maior+1;
            }
        }
        return 1;
    }
}