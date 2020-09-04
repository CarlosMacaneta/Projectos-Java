package models.dao;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Macaneta
 */
public class Criptografia implements Serializable {
    
    public static String encriptografar(String senha){
        StringBuilder sd = new StringBuilder();
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(senha.getBytes("UTF-8"));
            
            for(byte b: messageDigest){
                sd.append(String.format("%02X", 0xFF & b));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, "Erro de criptografia.\nErro: "+ex.getMessage());
        }
        return sd.toString();
    }
}
