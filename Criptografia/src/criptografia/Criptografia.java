package criptografia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 *
 * @author CarlosMacaneta
 */
public class Criptografia {
    
    public static String encriptografar(String senha){
        StringBuilder sd = new StringBuilder();
        
        try {
            /**Para criptografia esta sendo usando o algoritmo SHA-256 
             * Este algoritmo vai gerar 256 caracteres da senha ou qualquer outra palavra 
             * em codigo hexadecimal
            */
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String senha = JOptionPane.showInputDialog(null, "Sua senha");
        String senha_ = encriptografar(senha);
        
        JOptionPane.showMessageDialog(null, "Senha nao criptografada: "+senha+
            "\n-----------------------------------------\nSenha criptografada: \n"+senha_);
        
        //para validacao da senha
        if(senha_.equals(senha))
            System.out.println("Nao funcionou");
        else if(senha_.equals(encriptografar(senha)))
            System.out.println("Funciona");
    }
}