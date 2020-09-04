package models.dao;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Carlos Macaneta
 */
public class ValidarDadosUser implements Serializable {
    
    private final static String REGEX_PHONE  = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    private final static String REGEX_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!$%&'*+/=?`{|}~^-]+)"
        + "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final static String REGEX_USERNAME = "^[a-z0-9_-]{3,15}$";
    private final static String REGEX_SENHA = "^(?=.*[A-z]).{8,16}$";
    
    private final static Pattern PATTERN_PHONE = Pattern.compile(REGEX_PHONE);
    private final static Pattern PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    private final static Pattern PATTERN_USERNAME = Pattern.compile(REGEX_USERNAME);
    private final static Pattern PATTERN_SENHA = Pattern.compile(REGEX_SENHA);
    
    
    public static String primeirosNomes(String[] nome){
        String primeiroNome = "";
                 
        for(int i = 0; i < nome.length; i++){
            if(nome[i].equals(nome[nome.length - 1])){
                break;
            }else{
                primeiroNome += nome[i]+" ";
            }
        }
        if(nome.length <= 1){
            primeiroNome += nome[0];
        }System.out.println(primeiroNome);
        return primeiroNome.trim();
    }
    
    public static boolean validarTelefone(JTextField jtfTelefone){
        Matcher matcher = PATTERN_PHONE.matcher(jtfTelefone.getText());
        return matcher.matches();
    }
    
    public static boolean validarEmail(JTextField jtfEmail){
        Matcher matcher = PATTERN_EMAIL.matcher(jtfEmail.getText());
        return matcher.matches();
    }
    
    public static boolean validarUsername(JTextField jtfUsername){
        Matcher matcher = PATTERN_USERNAME.matcher(jtfUsername.getText());
        return matcher.matches();
    }
    
    public static boolean validarSenha(JPasswordField senha){   
        Matcher matcher = PATTERN_SENHA.matcher(returnSenha(senha)); 
        return matcher.matches();    
    }
    
    public static boolean validarSenha(String senha){   
        Matcher matcher = PATTERN_SENHA.matcher(senha); 
        return matcher.matches();    
    } 
    
    public static String returnSenha(JPasswordField senha){
        char[] charSenha = senha.getPassword();
        String password = "";

        for(int i = 0; i < charSenha.length; i++){
            password += charSenha[i];
        }
        return password.trim();
    }
}