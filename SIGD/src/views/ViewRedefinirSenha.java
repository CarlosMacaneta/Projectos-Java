package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import models.dao.RedefinirSenha;
import models.dao.ValidarDadosUser;

/**
 *
 * @author CarlosMacaneta
 */
public class ViewRedefinirSenha extends JFrame implements ActionListener{
    
    private JButton btnRedefinir = new JButton("Redefinir");
    private JButton btnCancelar = new JButton("Cancelar");
    
    private JLabel lblCheck = new JLabel(new ImageIcon("src/images/checkmark.png"));
    private JLabel lblUncheck = new JLabel(new ImageIcon("src/images/remove.png"));
    private JLabel lblIcon = new JLabel(new ImageIcon("src/images/donate2.png"));
    private JLabel lblTitle = new JLabel("Redefinir senha");
    private JLabel lblId = new JLabel("Código");
    private JLabel lblNewPass = new JLabel("Nova senha");
    private JLabel lblConfPass = new JLabel("Confirmar senha");
    
    private JPanel jpBg = new JPanel();
    private JPanel jpRf = new JPanel();
    
    private JTextField jtfId = new JTextField();
    
    private JPasswordField jpfNewPass = new JPasswordField();
    private JPasswordField jpfConfPass = new JPasswordField();

    public ViewRedefinirSenha() {
        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }
    
    public void GUI(){
        setLayout(null);
        
        jpBg.setBackground(new Color(239, 210, 112));
        jpRf.setBackground(new Color(239, 210, 112));
        jtfId.setBackground(new Color(239, 210, 112));
        jpfConfPass.setBackground(new Color(239, 210, 112));
        jpfNewPass.setBackground(new Color(239, 210, 112));
        btnCancelar.setBackground(new Color(105, 122, 121));
        btnRedefinir.setBackground(new Color(105, 122, 121));
        
        jpRf.setBounds(10, 10, 380, 230);
        jpBg.setBounds(0, 0, 400, 250);
        lblIcon.setBounds(120, 45, 160, 160);
        lblTitle.setBounds(150, 20, 150, 30);
        lblId.setBounds(50, 55, 120, 30);
        lblNewPass.setBounds(50, 95, 120, 30);
        lblConfPass.setBounds(50, 127, 120, 30);
        jtfId.setBounds(170-120, 50, 200+50+40, 30+10);
        jpfNewPass.setBounds(170-120, 90, 200+50+40, 30+10);
        jpfConfPass.setBounds(170-120, 130, 200+50+40, 30+10);
        btnRedefinir.setBounds(80, 180+5, 100, 30);
        btnCancelar.setBounds(200, 180+5, 100, 30);
        
        lblTitle.setFont(new Font("Calibri", Font.BOLD|Font.ITALIC, 14));
        lblId.setFont(new Font("Calibri", Font.BOLD|Font.CENTER_BASELINE, 14));
        lblNewPass.setFont(new Font("Calibri", Font.BOLD|Font.CENTER_BASELINE, 14));
        lblConfPass.setFont(new Font("Calibri", Font.BOLD|Font.CENTER_BASELINE, 14));
        jtfId.setFont(new Font("Calibri", Font.BOLD, 14));
        jpfNewPass.setFont(new Font("Calibri", Font.BOLD, 14));
        jpfConfPass.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCancelar.setFont(new Font("Calibri", Font.BOLD, 14));
        btnRedefinir.setFont(new Font("Calibri", Font.BOLD, 14));
        
        lblTitle.setForeground(Color.BLACK);
        lblId.setForeground(Color.BLACK);
        lblNewPass.setForeground(Color.BLACK);
        lblConfPass.setForeground(Color.BLACK);
        jtfId.setForeground(Color.BLACK);
        jpfConfPass.setForeground(Color.BLACK);
        jpfNewPass.setForeground(Color.BLACK);
        btnCancelar.setForeground(Color.BLACK);
        btnRedefinir.setForeground(Color.BLACK);
        
        jpRf.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(105, 122, 121), 2), lblTitle.getText(), 1, 0, lblTitle.getFont(), Color.BLACK));
        jtfId.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(105, 122, 121), 2), lblId.getText(), TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, lblId.getFont(), Color.BLACK));
        jpfConfPass.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(105, 122, 121), 2), lblConfPass.getText(), TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, lblConfPass.getFont(), Color.BLACK));
        jpfNewPass.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(105, 122, 121), 2), lblNewPass.getText(), TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, lblNewPass.getFont(),Color.BLACK));
        btnCancelar.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        btnRedefinir.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        
        lblCheck.setVisible(false);
        lblUncheck.setVisible(false);
        
        add(lblCheck);
        add(lblUncheck);
        add(jtfId);
        add(jpfConfPass);
        add(jpfNewPass);
        add(btnRedefinir);
        add(btnCancelar);
        add(jpRf);
        add(jpBg);
        
        jpfNewPass.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe){
                
            }
            
            @Override
            public void focusLost(FocusEvent fe){
                if(ValidarDadosUser.validarSenha(jpfNewPass)){
                    lblUncheck.setVisible(false);
                    lblCheck.setBounds(345, 103, 16, 16);
                    lblCheck.setVisible(true);                    
                }else{
                    lblCheck.setVisible(false);
                    lblUncheck.setBounds(345, 103, 16, 16);
                    lblUncheck.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Senha inserida é inválida.\nTente novamante.");
                }
            }
        });
        
        jpfConfPass.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe){   
                jpfConfPass.addKeyListener(new KeyAdapter(){
                    @Override
                    public void keyReleased(KeyEvent ke){
                    if(Arrays.equals(jpfConfPass.getPassword(), jpfNewPass.getPassword())){
                        try{
                            Thread.sleep(1);
                            lblUncheck.setVisible(false);
                            lblCheck.setVisible(true);
                            lblCheck.setBounds(345, 143, 16, 16);
                        }catch(InterruptedException e){
                            System.err.println(e.getMessage());
                        }finally{
                            lblUncheck.setVisible(false);
                            lblCheck.setVisible(true);
                            lblCheck.setBounds(345, 143, 16, 16);
                        }
                    }else{
                        lblUncheck.setBounds(345, 143, 16, 16);
                        lblUncheck.setVisible(true);
                    }
                    }
                });
            }
            
            @Override
            public void focusLost(FocusEvent fe){
                if(!Arrays.equals(jpfConfPass.getPassword(), jpfNewPass.getPassword())){
                    lblUncheck.setBounds(345, 143, 16, 16);
                    lblUncheck.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Senha inserida não pode ser diferente da nova senha.");
                }else{
                    lblUncheck.setVisible(false);
                    lblCheck.setVisible(true);
                    lblCheck.setBounds(345, 143, 16, 16);
                }
            }
        });
        
        setVisible(true);
        
        btnRedefinir.addActionListener(this);
        btnCancelar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnRedefinir){      
            if(!jtfId.getText().isEmpty()&&!jpfNewPass.getPassword().equals("")&&!jpfConfPass.getPassword().equals("")){
                if(Arrays.equals(jpfConfPass.getPassword(), jpfNewPass.getPassword())){
                    if(new RedefinirSenha().reset(Integer.parseInt(jtfId.getText()), jpfConfPass)){
                        dispose();
                        JOptionPane.showMessageDialog(this, "A sua senha foi redefinida com sucesso.");
                    }else JOptionPane.showMessageDialog(null, "Código indroduzido é inválido.");
                }else JOptionPane.showMessageDialog(null, "Senha inserida não pode ser diferente da nova senha.");
            }else JOptionPane.showMessageDialog(null, "Preencha os campos vazios.");
        }else if(ae.getSource() == btnCancelar) dispose();
    }
}