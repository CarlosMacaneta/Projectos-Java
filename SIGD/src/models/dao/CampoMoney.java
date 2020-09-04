package models.dao;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JTextField;

/**
 *
 * @author Carlos Macaneta
 */
public class CampoMoney implements Serializable {
    
    private static final String REGEX = "^\\$?([1-9]{1}[0-9]{0,2}(\\,[0-9]{3})*(\\.[0-9]"
        + "{0,2})?|[1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|(\\.[0-9]{1,2})?)$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    
    public static boolean validarMoeda(String[] moedas, JTextField jtfMoeda, JComboBox jcbMoedas){
        Matcher matcher = PATTERN.matcher(jtfMoeda.getText());
        if(matcher.matches()){
            if(formatMoney(moedas, jtfMoeda, jcbMoedas) != null){
                return true;
            }
        }
        return false;
    }
    
    public static boolean validarMoeda(JTextField jtfMoeda){
        Matcher matcher = PATTERN.matcher(jtfMoeda.getText());
        return matcher.matches();
    }
    
    public static String dinheiro(String[] moedas, JTextField jtfMoeda, JComboBox jcbMoedas){
        if(formatMoney(moedas, jtfMoeda, jcbMoedas) != null){
            return formatMoney(moedas, jtfMoeda, jcbMoedas);
        }
        return null;
    }
    
    private static String formatMoney(String[] moedas, JTextField jtfMoeda, JComboBox jcbMoedas) {
        try{
            if(!jtfMoeda.getText().isEmpty()){
                for(int i = 0; i < jcbMoedas.getItemCount(); i++){
                    if(jcbMoedas.getSelectedItem().equals(moedas[i])){

                        switch(i){
                            case 0:
                                Locale moz = new Locale("pt", "MZ");
                                NumberFormat cf = NumberFormat.getCurrencyInstance(moz);
                                return cf.format(Double.parseDouble(jtfMoeda.getText().trim()));
                            case 1: 
                                Locale us = new Locale("pt", "US");
                                NumberFormat ci0 = NumberFormat.getCurrencyInstance(us);
                                return ci0.format(Double.parseDouble(jtfMoeda.getText().trim()));
                            case 2:
                                Locale pt = new Locale("", "PT");
                                NumberFormat ci1 = NumberFormat.getCurrencyInstance(pt);
                                return ci1.format(Double.parseDouble(jtfMoeda.getText().trim()));
                            case 3:    
                                Locale za = new Locale("pt", "ZA");
                                NumberFormat ci2 = NumberFormat.getCurrencyInstance(za);
                                return ci2.format(Double.parseDouble(jtfMoeda.getText().trim()));
                            case 4:
                                Locale gb = new Locale("pt", "GB");
                                NumberFormat ci3 = NumberFormat.getCurrencyInstance(gb);
                                return ci3.format(Double.parseDouble(jtfMoeda.getText().trim()));
                            case 5:
                                Locale cn = new Locale("pt", "CN");
                                NumberFormat ci4 = NumberFormat.getCurrencyInstance(cn);
                                return ci4.format(Double.parseDouble(jtfMoeda.getText().trim()));
                            default: break;
                        } 
                    }
                }
            }
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "ERRO: "+e.getMessage(), "MENSAGEM DE ERRO", WARNING_MESSAGE);
        }
        return null;
    }
    
    public static String formattedMoney(double money, String moeda){
        switch(moeda.toUpperCase()){
            case "MZN":
                Locale moz = new Locale("pt", "MZ");
                NumberFormat cf = NumberFormat.getCurrencyInstance(moz);
                return cf.format(money);
            case "USD":
                Locale us = new Locale("pt", "US");
                NumberFormat ci0 = NumberFormat.getCurrencyInstance(us);
                return ci0.format(money);
            case "EUR":
                Locale pt = new Locale("", "PT");
                NumberFormat ci1 = NumberFormat.getCurrencyInstance(pt);
                return ci1.format(money);
            case "ZAR":
                Locale za = new Locale("pt", "ZA");
                NumberFormat ci2 = NumberFormat.getCurrencyInstance(za);
                return ci2.format(money);
            case "GBP":
                Locale gb = new Locale("pt", "GB");
                NumberFormat ci3 = NumberFormat.getCurrencyInstance(gb);
                return ci3.format(money);
            case "CNY":
                Locale cn = new Locale("pt", "CN");
                NumberFormat ci4 = NumberFormat.getCurrencyInstance(cn);
                return ci4.format(money);
            default: break;    
        }
        return null;
    }
}