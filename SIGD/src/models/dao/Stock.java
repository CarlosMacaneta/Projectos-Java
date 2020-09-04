package models.dao;

import controllers.StockController;
import java.io.Serializable;
import java.util.ArrayList;
import models.beans.Estoque;

/**
 *
 * @author CarlosMacaneta
 */
public class Stock extends Estoque implements Serializable {
    
    public Stock(){     
    }

    public Stock(int id, String nome_prod, String categoria, int qtdActual, int qtdGasta) {
        super(id, nome_prod, categoria, qtdActual, qtdGasta);
    }

    public static boolean prodExists(String nome){
        ArrayList<Stock> prod = StockController.lista();
        if (!prod.isEmpty()){
            for (Stock s: prod){
                if (s.getNome_prod().equalsIgnoreCase(nome))
                    return true;
            }
        }
        return false;
    }
    
    public static boolean qtdDisponivel(String nome, int qtd){
        ArrayList<Stock> prod = StockController.lista();
        if(!prod.isEmpty()){
            for(Stock p: prod){
                if(nome.equalsIgnoreCase(p.getNome_prod())){
                    if(p.getQtdActual() >= qtd)
                        return true;
                }
            }
        }
        return false;
    }
    
    public boolean increaseQtd(Stock prod_){
        ArrayList<Stock> prod = StockController.lista();
        if(!prod.isEmpty()){            
            for (Stock stock : prod) {
                if (prod_.getNome_prod().equalsIgnoreCase(stock.getNome_prod())) {
                    prod_.setId(stock.getId());
                    prod_.setQtdActual(stock.getQtdActual() + prod_.getQtdActual());
                    prod_.setQtdGasta(stock.getQtdGasta());
                    StockController.update(stock.getId(), prod_);
                    return true;
                }
            }        
        }
        return false;
    }
    
    public void qtdGasta(String prod_, int qtd){
        ArrayList<Stock> prod = StockController.lista();
        if(!prod.isEmpty()){
            prod.forEach((stock)->{
                if(prod_.equalsIgnoreCase(stock.getNome_prod())){
                    stock.setQtdActual(stock.getQtdActual() - qtd);
                    stock.setQtdGasta(stock.getQtdGasta() + qtd);
                    stock.setValorGasto(stock.getValorGasto() + qtd);
                    StockController.update(stock.getId(), stock);
                }
            });
        }
    }
    
    public void decrease(String prod_, int qtd){
        ArrayList<Stock> prod = StockController.lista();
        if(!prod.isEmpty()){
            prod.forEach((stock)->{
                if(prod_.equalsIgnoreCase(stock.getNome_prod())){
                    stock.setQtdActual(stock.getQtdActual() - qtd);
                    StockController.update(stock.getId(), stock);
                }
            });
        }
    }
}