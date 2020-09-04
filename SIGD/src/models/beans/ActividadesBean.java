package models.beans;

import java.io.Serializable;

/**
 *
 * @author CarlosMacaneta
 */
public abstract class ActividadesBean implements Serializable {
    
    private int id;
    private int doadoreReg;
    private int prod;
    private int ben;
    private int qtfBen;
    private String valor;
    private String valorBen;
    private String data;
    private int fk;

    public ActividadesBean() {
    }

    public ActividadesBean(int id, int doadoreReg, int prod, int ben, int qtfBen, int fk) {
        this.id = id;
        this.doadoreReg = doadoreReg;
        this.prod = prod;
        this.ben = ben;
        this.qtfBen = qtfBen;
        this.fk = fk;
    }

    public ActividadesBean(int id, int doadoreReg, int prod, int ben, int qtfBen, String valor, String valorBen, String data, int fk) {
        this.id = id;
        this.doadoreReg = doadoreReg;
        this.prod = prod;
        this.ben = ben;
        this.qtfBen = qtfBen;
        this.valor = valor;
        this.valorBen = valorBen;
        this.data = data;
        this.fk = fk;
    }
    
    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.fk = fk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoadoreReg() {
        return doadoreReg;
    }

    public void setDoadoreReg(int doadoreReg) {
        this.doadoreReg = doadoreReg;
    }

    public int getProd() {
        return prod;
    }

    public void setProd(int prod) {
        this.prod = prod;
    }

    public int getBen() {
        return ben;
    }

    public void setBen(int ben) {
        this.ben = ben;
    }

    public int getQtfBen() {
        return qtfBen;
    }

    public void setQtfBen(int qtfBen) {
        this.qtfBen = qtfBen;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValorBen() {
        return valorBen;
    }

    public void setValorBen(String valorBen) {
        this.valorBen = valorBen;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}