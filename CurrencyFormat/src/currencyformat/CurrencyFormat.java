package currencyformat;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author CarlosMacaneta
 */
public class CurrencyFormat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Locale moz = new Locale("pt", "MZ");
        NumberFormat cf = NumberFormat.getCurrencyInstance(moz);
        String money = JOptionPane.showInputDialog("Valor monetario");
        String mola = cf.format(Double.parseDouble(money.trim()));
        System.out.println(mola);

        System.out.println("----------------------------------------");
        
        Locale us = new Locale("pt", "US");
        NumberFormat ci0 = NumberFormat.getCurrencyInstance(us);
        String money_ = JOptionPane.showInputDialog("Valor monetario");
        System.out.println(ci0.format(Double.parseDouble(money_.trim())));
    }
    
}