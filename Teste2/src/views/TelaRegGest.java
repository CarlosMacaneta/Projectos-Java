package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author CarlosMacaneta
 */
public class TelaRegGest extends JFrame implements ActionListener {
    private JButton btnCancelar = new JButton("Cancelar");
    private JButton btnReg = new JButton("Registar");
    
    private JComboBox<String> jcbGenero = new JComboBox<>(new String[]{"Masculino", "Femenino"});
    
    private JLabel lblNome = new JLabel("Nome");
    private JLabel lblUsername = new JLabel("Nome de usuario");
    private JLabel lblSenha = new JLabel("Senha");
    private JLabel lblNDoc = new JLabel("Nº. documento");
    private JLabel lblGenero = new JLabel("Genero");
    private JLabel lblMorada = new JLabel("Morada");
    private JLabel lblTel = new JLabel("Telefone");
    private JLabel lblEmail = new JLabel("Email");
    
    private JTextField jtfNome = new JTextField();
    private JTextField jtfUsername = new JTextField();
    private JTextField jtfNDoc = new JTextField();
    private JTextField jtfMorada = new JTextField();
    private JTextField jtfTel = new JTextField();
    private JTextField jtfEmail = new JTextField();
    
    private JPanel line = new JPanel();
    
    private JPasswordField jpfSenha = new JPasswordField();
            
    public TelaRegGest(){
        super("Registo");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        init();
    }
    
    private void init(){
        
        line.setBounds(13, 10, 720, 345);
        lblNome.setBounds(50, 70, 120, 30);
        jtfNome.setBounds(150, 70, 200, 30);
        lblNDoc.setBounds(50, 110, 120, 30);
        jtfNDoc.setBounds(150, 110, 200, 30);
        lblGenero.setBounds(50, 140, 120, 30);
        jcbGenero.setBounds(150, 140, 120, 30);
        lblMorada.setBounds(270+130, 70, 120, 30);
        jtfMorada.setBounds(390+100, 70, 200, 30);
        lblTel.setBounds(270+130, 110, 120, 30);
        jtfTel.setBounds(390+100, 110, 200, 30);
        lblEmail.setBounds(270+130, 150, 120, 30);
        jtfEmail.setBounds(390+100, 150, 200, 30);
        lblUsername.setBounds(50, 180, 120, 30);
        jtfUsername.setBounds(150, 180, 200, 30);
        lblSenha.setBounds(270+130, 185, 120, 30);
        jpfSenha.setBounds(390+100, 185, 200, 30);
        btnCancelar.setBounds(400, 280, 150, 30);
        btnReg.setBounds(220, 280, 150, 30);
        
        line.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        
        add(lblNome);
        add(jtfNome);
        add(lblNDoc);
        add(jtfNDoc);
        add(lblGenero);
        add(jcbGenero);
        add(lblMorada);
        add(jtfMorada);
        add(lblTel);
        add(jtfTel);
        add(lblEmail);
        add(jtfEmail);
        add(lblUsername);
        add(jtfUsername);
        add(lblSenha);
        add(jpfSenha);
        add(btnCancelar);
        add(btnReg);
        add(line);
        
        btnReg.addActionListener(this);
        btnCancelar.addActionListener(this);
        
        setVisible(true);
    }
    
    private String senha(){
        String s = "";
        for (char pass : jpfSenha.getPassword()) {
            s += pass;
        }
        return s;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
}