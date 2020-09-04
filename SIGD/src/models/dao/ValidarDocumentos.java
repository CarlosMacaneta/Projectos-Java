package models.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Carlos Macaneta
 */
public class ValidarDocumentos implements Serializable {
    
    private static final String BI = "Bilhete de identidade";
    private static final String PASSAPORTE = "Passaporte";
    private static final String DIRE = "DIRE";
    private static final String CC = "Carta de condução";
    private static final String CE = "Cartão de eleitor";
    
    private static final String REGEX_BI = "[0-9]{12}[A-Z]{1}";
    private static final String REGEX_PASSPORT = "[0-9]{2}[A-Z]{2}[0-9]{5}";
    private static final String REGEX_DIRE = "[0-9]{7}[A-Z]{2}";
    private static final String REGEX_CC = "[0-9]{8}/[0-9]{1}";
    private static final String REGEX_CE = "[0-9]{11}/[0-9]{9}";
    
    private static final Pattern PATTERN_BI = Pattern.compile(REGEX_BI);
    private static final Pattern PATTERN_PASSPORT = Pattern.compile(REGEX_PASSPORT);
    private static final Pattern PATTERN_DIRE = Pattern.compile(REGEX_DIRE);
    private static final Pattern PATTERN_CC = Pattern.compile(REGEX_CC);
    private static final Pattern PATTERN_CE = Pattern.compile(REGEX_CE);
    
    
    private static ArrayList<String> receberDocumento(JComboBox jcbDocumentos){
        
        ArrayList<String> documentos = new ArrayList<>();
        
        for(int i = 0; i < jcbDocumentos.getItemCount(); i++){
            documentos.add((String) jcbDocumentos.getItemAt(i));
        }
        return documentos;
    }
    
    private static boolean validarBi(JComboBox jcbDocumentos, JTextField jtfDocumento){
        
        Matcher matcher = PATTERN_BI.matcher(jtfDocumento.getText().toUpperCase());
        
        for(int i = 0; i < receberDocumento(jcbDocumentos).size(); i++){
            if(BI.equals(receberDocumento(jcbDocumentos).get(i))){
                return matcher.matches();
            }
        }
        return !matcher.matches();
    }
    
    private static boolean validarPassaPorte(JComboBox jcbDocumentos, JTextField jtfDocumento){
        
        Matcher matcher = PATTERN_PASSPORT.matcher(jtfDocumento.getText().toUpperCase());
        
        for(int i = 0; i < receberDocumento(jcbDocumentos).size(); i++){
            if(PASSAPORTE.equals(receberDocumento(jcbDocumentos).get(i))){
                return matcher.matches();
            }
        }
        return !matcher.matches();
    }
    
    private static boolean validarDIRE(JComboBox jcbDocumentos, JTextField jtfDocumento){
        
        Matcher matcher = PATTERN_DIRE.matcher(jtfDocumento.getText().toUpperCase());
        
        for(int i = 0; i < receberDocumento(jcbDocumentos).size(); i++){
            if(DIRE.equals(receberDocumento(jcbDocumentos).get(i))){
                return matcher.matches();
            }
        }
        return !matcher.matches();
    }
    
    private static boolean validarCC(JComboBox jcbDocumentos, JTextField jtfDocumento){
        
        Matcher matcher = PATTERN_CC.matcher(jtfDocumento.getText());
        
        for(int i = 0; i < receberDocumento(jcbDocumentos).size(); i++){
            if(CC.equals(receberDocumento(jcbDocumentos).get(i))){
                return matcher.matches();
            }
        }
        return !matcher.matches();
    }
    
    private static boolean validarCE(JComboBox jcbDocumentos, JTextField jtfDocumento){
        
        Matcher matcher = PATTERN_CE.matcher(jtfDocumento.getText());
        
        for(int i = 0; i < receberDocumento(jcbDocumentos).size(); i++){
            if(CE.equals(receberDocumento(jcbDocumentos).get(i))){
                return matcher.matches();
            }
        }
        return !matcher.matches();
    }   
    
    public static boolean validarDocumento(JComboBox jcbTipoDocumento, JTextField jtfNDocumento){
        return validarBi(jcbTipoDocumento, jtfNDocumento)||validarPassaPorte(jcbTipoDocumento, jtfNDocumento)||validarDIRE(jcbTipoDocumento, jtfNDocumento)
            ||validarCC(jcbTipoDocumento, jtfNDocumento)||validarCE(jcbTipoDocumento, jtfNDocumento);
    }
}