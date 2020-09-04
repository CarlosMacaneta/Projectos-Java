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
import models.dao.Donativo;

public class DonativoController {

    private static final String URI = "src/arquivos/Donativo.dat";

    private static void create(ArrayList<Donativo> stk){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(stk);
                oos.close();
            }catch(FileNotFoundException ex){}
        }catch(IOException ex){}
    }

    public static boolean create(Donativo Donativo){
        if(Files.notExists(Paths.get(URI))){
            ArrayList<Donativo> Donativo_ = new ArrayList<>();
            Donativo_.add(Donativo);
            create(Donativo_);
            return true;
        }else{
            ArrayList<Donativo> Donativo_ = lista();
            Donativo_.add(Donativo);
            create(Donativo_);
            return true;
        }
    }

    public static ArrayList<Donativo> lista(){
        ArrayList<Donativo> list = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<Donativo>) ois.readObject();
            }catch(FileNotFoundException|ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e, "ERROR", ERROR_MESSAGE);
            }
        }catch(IOException ex){}
        return list;
    }
    
    public static ArrayList<Donativo> donativo(int id){
        ArrayList<Donativo> don = new ArrayList<>();
        
        if(!lista().isEmpty()){
            lista().forEach((l)->{
                if(id == l.getId())
                    don.add(l);
            });
        }
        return don;
    }

    public static void update(int id, Donativo Donativo){
        ArrayList<Donativo> Donativos = lista();
        if(!Donativos.isEmpty()){
            for (int i = 0; i < Donativos.size(); i++) {
                if(Donativos.get(i).getId() == id){
                    Donativos.remove(i);
                    Donativos.add(i, Donativo);
                    create(Donativos);
                    break;
                }
            }
        }
    }

    public static boolean delete(int id){
        ArrayList<Donativo> Donativos = lista();
        if(!Donativos.isEmpty()){
            for (Donativo Donativo_ : Donativos) {
                if(Donativo_.getId() == id){
                    Donativos.remove(Donativo_);
                    create(Donativos);
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
            ArrayList<Donativo> list = lista();

            if(!list.isEmpty()){
                int maior = list.get(0).getId();

                for(Donativo p: list){
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