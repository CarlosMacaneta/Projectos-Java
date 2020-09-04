package models.dao;

import controllers.FinancaController;
import java.io.Serializable;
import java.util.ArrayList;
import models.beans.Estoque;

/**
 *
 * @author CarlosMacaneta
 */
public class Financa extends Estoque implements Serializable {

    public Financa(){}
    
    public Financa(int id, String valorActual, String valorGasto) {
        super(id, valorActual, valorGasto);
    }

    public static boolean currencyExists(String moeda){
        ArrayList<Financa> money = FinancaController.lista();
        if(!money.isEmpty()){
            for (Financa f: money){
                if (f.getValorActual().toUpperCase().contains(moeda.toUpperCase()))
                    return true;
            }
        }
        return false;
    }
    
    public static boolean valorDisponivel(double valor){
        ArrayList<Financa> money = FinancaController.lista();
        
        if(!money.isEmpty()){
            for(Financa f: money){
                String[] cash = f.getValorActual().split(" ");
                double dinheiro = Double.parseDouble(cash[1].replace(".", "").replace(",", "."));
                
                if(dinheiro >= valor) return true;
            }
        }
        return false;
    } 

    public void increaseValorActual(double valor, String moeda){
        ArrayList<Financa> money = FinancaController.lista();

        for (Financa financa : money) {
            if (moeda.equalsIgnoreCase(financa.getValorActual().substring(0, 3))) {
                String[] cash = financa.getValorActual().split(" ");
                double dinheiro = Double.parseDouble(cash[1].replace(".", "").replace(",", "."));

                double valor1 = dinheiro + valor;
                financa.setValorActual(CampoMoney.formattedMoney(valor1, moeda));

                FinancaController.update(financa.getId(),financa);
                break;
            }
        }
    }
    
    public void valorGasto(double valor, String moeda){
        ArrayList<Financa> money = FinancaController.lista();

        for (Financa financa : money) {
            if (moeda.equalsIgnoreCase(financa.getValorActual().substring(0, 3))) {
                String[] cash = financa.getValorActual().split(" ");
                double dinheiro = Double.parseDouble(cash[1].replace(".", "").replace(",", "."));

                if (dinheiro >= valor) {
                    double valor1 = dinheiro - valor;

                    String[] bucks = financa.getValorGasto().split(" ");
                    double male = Double.parseDouble(bucks[1].replace(".", "").replace(",", "."));

                    double valor2 = male + valor;

                    financa.setValorActual(CampoMoney.formattedMoney(valor1, moeda));
                    financa.setValorGasto(CampoMoney.formattedMoney(valor2, moeda));

                    FinancaController.update(financa.getId(),financa);
                    break;
                }
            }
        }
    }
    
    public void reduce(double valor, String moeda){
        ArrayList<Financa> money = FinancaController.lista();

        for (Financa financa : money) {
            if (moeda.equalsIgnoreCase(financa.getValorActual().substring(0, 3))) {
                String[] cash = financa.getValorActual().split(" ");
                double dinheiro = Double.parseDouble(cash[1].replace(".", "").replace(",", "."));

                if (dinheiro >= valor) {
                    double valor1 = dinheiro - valor;

                    financa.setValorActual(CampoMoney.formattedMoney(valor1, moeda));

                    FinancaController.update(financa.getId(),financa);
                    break;
                }
            }
        }
    }
}