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
import models.dao.Log;

public class UserLogged {

    private static final String URI = "log/log.dat";

    private static void create(ArrayList<Log> fnc){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(fnc);
            }catch(FileNotFoundException ignored){}
        }catch(IOException ignored){}
    }

    public static boolean create(Log log){
        ArrayList<Log> log_ = new ArrayList<>();
        log_.add(log);
        create(log_);
        return true;
    }

    public static ArrayList<Log> lista(){
        ArrayList<Log> list = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<Log>) ois.readObject();
                ois.close();
            }catch(FileNotFoundException|ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e, "ERROR", ERROR_MESSAGE);
            }
        }catch(IOException ignored){
        }
        return list;
    }

    public static void delete(){
        try {
            Files.deleteIfExists(Paths.get(URI));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}