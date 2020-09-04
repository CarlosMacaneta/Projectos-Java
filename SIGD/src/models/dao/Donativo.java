package models.dao;

import controllers.DoadorController;
import controllers.DonativoController;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.beans.Doador;

/**
 *
 * @author CarlosMacaneta
 */
public class Donativo extends Doador implements Serializable {

    private Calendar c = Calendar.getInstance();
    private String data = String.format("%d-%02d-%02d", c.get(Calendar.YEAR), (c.get(Calendar.MONTH)+1), c.get(Calendar.DATE));
    private int qtd;
    private int num;
    private double dinheiro;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Donativo(){}

    public Donativo(int id, String nomeProd, String categoria, int qtd, String valorDoado, String data, String foreingkey) {
        super(id, nomeProd, categoria, qtd, valorDoado, data, foreingkey);
    }

    public Donativo(int id, String nomeProd, String categoria, int qtd, String valorDoado, String data, String nome, String apelido, String fk) {
        super(id, nomeProd, categoria, qtd, valorDoado, data, nome, apelido, fk);
    }

    public ArrayList<Donativo> findDonater(){
        ArrayList<Donativo> dados = new ArrayList<>();
        ArrayList<DoadorDAO> dd = new DoadorController().lista();
        ArrayList<Donativo> don = DonativoController.lista();

        dd.forEach((d)-> don.forEach((d_)->{
            if(d.getnDocumento().equalsIgnoreCase(d_.getForeignkey())){
                d_.setNome(d.getNome());
                d_.setApelido(d.getApelido());
                dados.add(d_);
            }
        }));
        return dados;
    }
    
    public int qtdCategoria(String categoria, String data_){
        ArrayList<Donativo> don = DonativoController.lista();
        qtd = 0;
        if(!don.isEmpty()){
            don.forEach((d)->{
                if(d.getCategoria().equalsIgnoreCase(categoria)&&d.getData().equals(data_)){
                    qtd += d.getQtd();
                }
            });
        }
        return qtd;
    }
    
    public int qtdProd_(String nomeProd){
        ArrayList<Donativo> don = DonativoController.lista();
        qtd = 0;
        if(!don.isEmpty()){
            don.forEach((d)->{
                if(d.getNomeProd().equalsIgnoreCase(nomeProd)){
                    qtd += d.getQtd();
                }
            });
        }
        return qtd;
    }

    public ArrayList<Donativo> findDonater(String fk){
        ArrayList<Donativo> dados = new ArrayList<>();
        ArrayList<Donativo> don = DonativoController.lista();

        don.forEach((d_)-> {
            if(fk.equalsIgnoreCase(d_.getForeignkey()) && d_.getData().equalsIgnoreCase(data))
                dados.add(d_);
        });
        return dados;
    }
    
    public ArrayList<Donativo> findByName(String nome){
        ArrayList<Donativo> list = DonativoController.lista();
        ArrayList<Donativo> dados = new ArrayList<>();

        if (!list.isEmpty()){
            list.forEach((l)->{
                if (l.getNomeProd().equalsIgnoreCase(nome))
                    dados.add(l);
            });
        }
        return dados;
    }

    public ArrayList<Donativo> findDonativo(String id){
        ArrayList<Donativo> don = DonativoController.lista();
        ArrayList<Donativo> list = new ArrayList<>();
        if (!don.isEmpty()){
            don.forEach((d)->{
                if (d.getForeignkey().equalsIgnoreCase(id))
                    list.add(d);
            });
        }
        return list;
    }
    
    public ArrayList<Donativo> findDonativos(String id){
        ArrayList<Donativo> don = DonativoController.lista();
        ArrayList<Donativo> list = new ArrayList<>();
        if (!don.isEmpty()){
            don.forEach((d)->{
                if (d.getForeignkey().equalsIgnoreCase(id) && d.getData().equals(data))
                    list.add(d);
            });
        }
        return list;
    }

    public int qtdTotalProd(String fk){
        ArrayList<Donativo> don = DonativoController.lista();
        qtd = 0;
        don.forEach((Donativo d)->{
            if(fk.equals(d.getForeignkey()))
                qtd += d.getQtd();
        });
        return qtd;
    }
    
    public int qtdTotalProd(){
        ArrayList<Donativo> don = DonativoController.lista();
        
        don.forEach((Donativo d)-> qtd += d.getQtd());
        return qtd;
    }
    
    public int qtdTotalProd(ArrayList<DoadorDAO> donater){
        ArrayList<Donativo> don = DonativoController.lista();
        qtd = 0;
        donater.forEach((dtr)-> don.forEach((Donativo d)->{
            if(dtr.getnDocumento().equals(d.getForeignkey()))
                qtd += d.getQtd();
        }));
        return qtd;
    }
    
    public int qtdTotalProd(String dataInicial, String dataFinal){
        ArrayList<Donativo> don = DonativoController.lista();
        qtd = 0;
        try {
            Date data1 = sdf.parse(dataInicial);
            Date data2 = sdf.parse(dataFinal);
            
            don.forEach((Donativo d)->{  
                try {
                    if((data1.before(sdf.parse(d.getData()))||data1.equals(sdf.parse(d.getData()))) &&
                            (data2.after(sdf.parse(d.getData()))||data2.equals(sdf.parse(d.getData())))){
                        qtd += d.getQtd();
                    }else qtd = 0;
                } catch (ParseException ex) {
                    Logger.getLogger(Donativo.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (ParseException ex) {
            Logger.getLogger(Donativo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qtd;
    }
    
    public String valorTotal(String fk){
        ArrayList<Donativo> don = DonativoController.lista();
        dinheiro = 0;
        don.forEach((d)->{
            if(fk.equals(d.getForeignkey())){
                if (d.getValorDoado().contains("MZN")) {
                    String[] valor = d.getValorDoado().split(" ");
                    dinheiro += Double.parseDouble(valor[1].replace(".", "").replace(",", "."));
                }
            }
        });
        return "MZN "+dinheiro;
    }

    public String valorTotal(){
        ArrayList<Donativo> don = DonativoController.lista();
        dinheiro = 0;
        don.forEach((d)->{
            if (d.getValorDoado().contains("MZN")) {
                String[] valor = d.getValorDoado().split(" ");
                dinheiro += Double.parseDouble(valor[1].replace(".", "").replace(",", "."));
            } 
        });
        return "MZN "+dinheiro;
    }

    public String valorTotal(ArrayList<DoadorDAO> donater){
        ArrayList<Donativo> don = DonativoController.lista();
        dinheiro = 0;
        donater.forEach((dtr)-> don.forEach((d) -> {
            if (dtr.getnDocumento().equals(d.getForeignkey())) {
                if (d.getValorDoado().contains("MZN")) {
                    String[] valor = d.getValorDoado().split(" ");
                    dinheiro += Double.parseDouble(valor[1].replace(".", "").replace(",", "."));
                }
            }
        }));
        return "MZN "+dinheiro;
    }

    public String valorTotalD(ArrayList<Donativo> don){
        dinheiro = 0;
        don.forEach((d) -> {
            if (d.getValorDoado().contains("USD")) {
                String[] valor = d.getValorDoado().split(" ");
                dinheiro += Double.parseDouble(valor[1].replace(".", "").replace(",", "."));
            } 
        });
        return "MZN "+dinheiro;
    }

    public int numDoacoes(String fk){
        ArrayList<Donativo> don = DonativoController.lista();
        num = 0;
        don.forEach((d)->{
            if(fk.equals(d.getForeignkey()))
                num++;
        });
        return num;
    }
    
    public ArrayList<Donativo> pesqData(String dataInicial, String DataFinal){
        ArrayList<Donativo> result = new ArrayList<>();
        
        try {
            Date data1 = sdf.parse(dataInicial);
            Date data2 = sdf.parse(DataFinal);
            
            if(data1.before(data2)){
                if(!findDonater().isEmpty()){
                    findDonater().forEach((d)->{ 
                        try {
                            if((data1.before(sdf.parse(d.getData()))||data1.equals(sdf.parse(d.getData()))) &&
                                (data2.after(sdf.parse(d.getData()))||data2.equals(sdf.parse(d.getData())))){
                                
                                result.add(d);
                            }
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "Para a data inicial e a data final\nuse o seguinte formato: yyyy-mm-dd\n"
                            + "Exemplo: 2020-01-31");
                        }
                    });
                }
            }        
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Para a data inicial e a data final\nuse o seguinte formato: yyyy-mm-dd\n"
            + "Exemplo: 2020-01-31");
        }
        return result;        
    }
    
    public ArrayList<Donativo> pesqData(ArrayList<DoadorDAO> donater){
        ArrayList<Donativo> result = new ArrayList<>();
        
        donater.forEach((dtr)-> findDonater().forEach((d)->{
            if(dtr.getnDocumento().equalsIgnoreCase(d.getForeignkey()))
                result.add(d);
        }));
        return result;        
    }
}