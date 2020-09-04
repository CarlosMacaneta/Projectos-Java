package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import static javax.swing.SwingUtilities.invokeLater;
import models.dao.ControleUsuario;
import models.dao.Propriedades;
/**
 *
 * @author Carlos Macaneta
 */
public class ViewLogin extends JFrame implements ActionListener {
    
    private JLabel fundo = new JLabel(new ImageIcon("src/images/wallpaper.png"));
    private JLabel donateImg = new JLabel(new ImageIcon("src/images/donate.png"));
    private JLabel nomeSys = new JLabel("Sys Donation");
    private JLabel logintxt = new JLabel("LOGIN");
    private JLabel lblNomeUser = new JLabel("Nome do usuário");
    private JLabel senhatxt = new JLabel("Palavra-passe");
    private JLabel close = new JLabel(new ImageIcon("src/images/closeIcon2.png"));
    private JLabel lblClose = new JLabel(new ImageIcon("src/images/close.png"));
    private JLabel esquecerPass = new JLabel("Esqueceu palavra-passe?");
    private JLabel eye = new JLabel(new ImageIcon("src/images/eye.png"));
    private JLabel hide = new JLabel(new ImageIcon("src/images/hide.png"));
    
    private JTextField userField = new JTextField();
    
    private JPasswordField passField = new JPasswordField();
    
    private JSeparator userSeparator = new JSeparator();
    private JSeparator passSeparator = new JSeparator();
    
    private JButton btLogin = new JButton();
    
    private ViewPrincipal menuGest = new ViewPrincipal();
    private ViewMenuFunc menuFunc = new ViewMenuFunc();
    private ViewRegistarUsuario regGest = new ViewRegistarUsuario();
    
    public ViewLogin() {
        setSize(800, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        Login();
    }
    
    private void Login() {
        setBackground(Color.WHITE);
        setLayout(null);
        setOpacity(0.99f);
        
        fundo.setBounds(150, 10, 800, 400);
        donateImg.setBounds(50, 70, 22, 19);
        lblNomeUser.setBounds(50, 110, 120, 20);
        nomeSys.setBounds(69, 65, 100, 30);
        logintxt.setBounds(50, 35, 100, 50);
        senhatxt.setBounds(50, 200, 100, 30);
        close.setBounds(100*7+67, 10, 16, 16);
        lblClose.setBounds(700+67, 10, 16, 16);
        userField.setBounds(50, 130, 250, 30);
        userSeparator.setBounds(50, 160, 250, 5);
        passField.setBounds(50, 225, 226, 30);
        passSeparator.setBounds(50, 256, 250, 5);
        esquecerPass.setBounds(170, 278, 140, 30);
        btLogin.setBounds(50, 330, 129+1, 29+1);
        eye.setBounds(275, 225, 24, 24);
        hide.setBounds(275, 225, 24, 24);
        
        new Propriedades().setBackground(new Color(49, 50, 115), userSeparator, passSeparator);
        btLogin.setBackground(new Color(51, 53, 131));
        
        new Propriedades().setBorder(null, userField, passField, btLogin);
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 16, lblNomeUser, senhatxt);
        new Propriedades().setFont("Calibri", Font.PLAIN, 18, userField, passField); 
        logintxt.setFont(new Font("Berlin Sans FB Bold", 0, 25));
        nomeSys.setFont(new Font("Bell MT", 0, 12));
        esquecerPass.setFont(new Font("Calibri", 0, 13));
        
        userField.setText("Nome de usuário");
        passField.setText("Palavra-passe");
        
        new Propriedades().setForeground(new Color(49, 50, 115), logintxt, lblNomeUser, senhatxt,userField, userSeparator, passSeparator);        
        nomeSys.setForeground(new Color(223, 152, 64));
        passField.setForeground(new Color(49, 50, 115, 50));
        esquecerPass.setForeground(new Color(49, 50, 115, 180));
        btLogin.setForeground(Color.WHITE);        
        
        btLogin.setIcon(new ImageIcon("src/images/btLogin.png"));
        
        lblClose.setVisible(false);
        eye.setVisible(false);
        
        new Propriedades().setOpaque(false, userField, passField, btLogin);
         
        new Propriedades().add(this, logintxt, donateImg, nomeSys, lblNomeUser, senhatxt, close, lblClose, userField, passField, userSeparator, passSeparator, eye, hide, esquecerPass, btLogin, fundo);
        
        passField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent ke){
                if(ke.getKeyChar() == KeyEvent.VK_ENTER) btLogin.doClick();
            }
        });
        
        hide.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                hide.setVisible(false);
                eye.setVisible(true);
                passField.setEchoChar((char)0);
            }
        });
        
        eye.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                eye.setVisible(false);
                hide.setVisible(true);
                passField.setEchoChar((char)'•');
            }
        });
               
        esquecerPass.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                esquecerPass.setForeground(new Color(255, 0, 0));
                new ViewRedefinirSenha().GUI();
            }
        });
        
        userField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe){
                userField.setForeground(new Color(49, 50, 115));
                
                if(userField.getText().equals("Nome de usuário"))
                    userField.setText(null);
            }
            
            @Override
            public void focusLost(FocusEvent fe){
                if(userField.getText().equals("")){
                    userField.setForeground(new Color(49, 50, 115, 50));
                    userField.setText("Nome de usuário");
                }
                userField.setText(userField.getText());
            }
        });
        
        passField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe){
                passField.setForeground(new Color(49, 50, 115));                
                char[] senha = {'P','a','l','a','v','r','a','-','p','a','s','s','e'};
                if(Arrays.equals(passField.getPassword(), senha)) passField.setText(null);
            }
            
            @Override
            public void focusLost(FocusEvent fe){
                String senha = "";
                
                for(char pass: passField.getPassword()) senha += pass;
                
                passField.setText(senha.trim());
                
                if(senha.equals("")||senha.endsWith("Palavra-passe")){
                    passField.setForeground(new Color(49, 50, 115, 50));
                    passField.setText("Palavra-passe");
                } 
            }
        });
        
        new Propriedades().moveWindow(this);
        new Propriedades().closeWindow(close, lblClose); 
        
        btLogin.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btLogin){
            switch(new ControleUsuario().controlUsuario(userField, passField.getPassword())){
                case 1:
                    dispose();
                    menuGest.setVisible(true);
                    break;
                case 2:
                    dispose();
                    menuFunc.init();
                    break;
                case 3:
                    dispose();
                    regGest.setVisible(true);
                    break;
                default:{
                    Point pnt = this.getLocation();
                    ViewLogin login = this;
                    new Thread() {
                        @Override
                        public void run(){
                            try {
                                for(int i = 0; i < 3; i++){
                                    login.setLocation(pnt.x - 10, pnt.y);
                                    sleep(20);
                                    login.setLocation(pnt.x + 10, pnt.y);
                                    sleep(20);
                                }
                                JOptionPane.showMessageDialog(login, "Nome do Usuario ou Senha incorrecta.", "Notificação", WARNING_MESSAGE);
                            } catch (InterruptedException ex) {
                                JOptionPane.showMessageDialog(login, "Nome do Usuario ou Senha incorrecta.", "Notificação", WARNING_MESSAGE);
                            }
                        }
                    }.start();
                }    
            }
        }
    }
    
    public static void main(String[] args){
        invokeLater(() -> {
            new ViewLogin().setVisible(true);
        });
    }
}