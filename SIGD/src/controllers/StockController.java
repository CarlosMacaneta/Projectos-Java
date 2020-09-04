package controllers;

import models.dao.Propriedades;
import models.dao.Stock;
import models.dao.Stock;

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
import models.dao.Stock;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class StockController {

    private static final String URI = "src/arquivos/stock.dat";

    private static void create(ArrayList<Stock> stk){
        try(FileOutputStream fos = new FileOutputStream(URI)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(stk);
                oos.close();
            }catch(FileNotFoundException ex){}
        }catch(IOException ex){}
    }

    public static boolean create(Stock stock){
        if(Files.notExists(Paths.get(URI))){
            ArrayList<Stock> stock_ = new ArrayList<>();
            stock_.add(stock);
            create(stock_);
            return true;
        }else{
            ArrayList<Stock> stock_ = lista();
            stock_.add(stock);
            create(stock_);
            return true;
        }
    }

    public static ArrayList<Stock> lista(){
        ArrayList<Stock> list = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(URI)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                list = (ArrayList<Stock>) ois.readObject();
            }catch(FileNotFoundException|ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERRO: " + e, "ERROR", ERROR_MESSAGE);
            }
        }catch(IOException ex){}
        return list;
    }

    public static void update(int id, Stock stock){
        ArrayList<Stock> stocks = lista();
        if(!stocks.isEmpty()){
            for (int i = 0; i < stocks.size(); i++) {
                if(stocks.get(i).getId() == id){
                    stocks.remove(i);
                    stocks.add(i, stock);
                    create(stocks);
                    break;
                }
            }
        }
    }

    public static boolean delete(int id){
        ArrayList<Stock> stocks = lista();
        if(!stocks.isEmpty()){
            for (Stock stock_ : stocks) {
                if(stock_.getId() == id){
                    stocks.remove(stock_);
                    create(stocks);
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Stock> find(String str) {
        ArrayList<Stock> list = new ArrayList<>();

        if (!Propriedades.isNumber(str)){
            if (!lista().isEmpty()) {
                lista().forEach((l) -> {
                    if (l.getNome_prod().toLowerCase().startsWith(str.toLowerCase()))
                        list.add(l);
                });
            }
        }else list.add(findById(Integer.parseInt(str)));
        return list;
    }

    public static Stock findById(int id){
        if (!lista().isEmpty()){
            for(Stock s: lista()){
                if(s.getId() == id)
                    return s;
            }
        }
        return null;
    }

    public static int id(){
        if(Files.notExists(Paths.get(URI))){
            return 1;
        }else{
            ArrayList<Stock> list = lista();

            if(!list.isEmpty()){
                int maior = list.get(0).getId();

                for(Stock p: list){
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