package models.dao;

import controllers.ActividadeController;
import java.io.Serializable;
import java.util.ArrayList;
import models.beans.ActividadesBean;

/**
 *
 * @author CarlosMacaneta
 */
public class Actividades extends ActividadesBean implements Serializable {

    private static int qtd;
    private static double valor;
    
    public Actividades() {
    }

    public Actividades(int id, int doadoreReg, int prod, int ben, int qtfBen, String valor, String valorBen, String data, int fk) {
        super(id, doadoreReg, prod, ben, qtfBen, valor, valorBen, data, fk);
    }
    
    public static void dailyActivity(int fk, Actividades atv){
        ArrayList<Actividades> act = ActividadeController.lista(fk);
        
        if(!act.isEmpty()){
            act.forEach((a)->{
                if(a.getData().equals(atv.getData()) && fk == a.getFk()){
                    a.setProd(a.getProd()+atv.getProd());
                    a.setDoadoreReg(a.getDoadoreReg()+atv.getDoadoreReg());
                    a.setValor(increaseMoney(a.getValor(), atv.getValor()));
                    a.setBen(a.getBen()+atv.getBen());
                    a.setQtfBen(a.getQtfBen()+atv.getQtfBen());
                    a.setValorBen(increaseMoney(a.getValorBen(), atv.getValorBen()));
                    a.setFk(atv.getFk());
                    ActividadeController.update(a.getId(), a);
                }
            });
        }else ActividadeController.create(atv);
    }
    
    public static int countProd(ArrayList<Integer> qtd_){
        qtd = 0;
        qtd_.forEach((q)->{
            qtd += q;
        });
        return qtd;
    }
    
    public static String countMoney(ArrayList<String> money_){
        valor = 0;
        money_.forEach((m) -> {
            valor += Double.parseDouble(m.substring(4).replace(",", "").replace(",", "."));
        });
        return "MZN "+valor;
    }
    
    public static String increaseMoney(String valor, String valor_){
        double dinheiro = Double.parseDouble(valor.substring(4).replace(".", "").replace(",", "."));
        double dinheiro_ = Double.parseDouble(valor_.substring(4).replace(".", "").replace(",", "."));
        
        dinheiro += dinheiro_;
         
        return "MZN "+dinheiro;
    }
}