package models.dao;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author Carlos Macaneta
 */
public class Event implements Serializable {
    
    private static String dinheiro;
    private static int quantidade;
    private static String upper;
    
    private static int quantidadeTotal(JTextPane jtp){       
        int sum = 0;
        final String REGEX = "\\d+";
  
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(jtp.getText().trim());
        
        while (m.find()) {
            sum += Integer.parseInt(m.group());
        }

        return sum;
    }    
    
    public static void quantidadeProdutos(JTextPane jtp, JLabel lbl){
        jtp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                int k = 0;
                if(jtp.isEnabled()) {
                    if(ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) k--;
                    
                    if(!jtp.getText().isEmpty()){
                        quantidade = quantidadeTotal(jtp);
                        lbl.setText(Integer.toString(quantidade)+" item(s)");
                    }else{
                        quantidade = 0; 
                        lbl.setText(Integer.toString(quantidade)+" itens"); 
                    }
                }
            }
        });
    }
    
    public static int total(JLabel lbl){
        int sum = 0;
        final String REGEX = "\\d+";
  
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(lbl.getText().trim());
        
        while (m.find()) {
            sum += Integer.parseInt(m.group());
        }
        System.out.println(sum);
        return sum; 
    }
    
    public static void upperCase(JTextField jtf) {
        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if(jtf.isEnabled()) {
                    upper = jtf.getText().toUpperCase();
                    jtf.setText(upper);
                }
            }
        });
    }
    
    public static void id(JTextField jtf, JLabel lbl) {
        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if(jtf.isEnabled()) {
                    lbl.setText("Código do doador: "+jtf.getText());
                    if(jtf.getText().equals("") || jtf.getText().equals(" "))
                        lbl.setText("Código do doador: 0"+jtf.getText());
                }
            }
        });
    }
}