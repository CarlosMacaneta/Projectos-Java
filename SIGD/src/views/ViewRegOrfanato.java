package views;

import controllers.ActividadeController;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import controllers.OrfanatoController;
import controllers.UserLogged;
import java.util.ArrayList;
import models.dao.Actividades;
import models.dao.Log;
import models.dao.Orfanato;
import models.dao.Propriedades;
import models.dao.ValidarDadosUser;

/**
 *
 * @author CarlosMacaneta
 */
public class ViewRegOrfanato extends JFrame implements ActionListener {
    
    private Calendar c = Calendar.getInstance();
    private String data = String.format("%d-%02d-%02d", c.get(Calendar.YEAR), (c.get(Calendar.MONTH)+1), c.get(Calendar.DATE));
    private ArrayList<Log> logged = UserLogged.lista();
    private JButton btnReg = new JButton("Registar");
    
    private JLabel lblClose = new JLabel(new ImageIcon("src/images/closeIcon2.png"));
    private JLabel lblClose2 = new JLabel(new ImageIcon("src/images/close.png"));
    private JLabel lblFundo = new JLabel(new ImageIcon("src/images/wallpaper1.jpg"));
    private JLabel lblLogo = new JLabel(new ImageIcon("src/images/donate2.png"));
    private JLabel lblNome = new JLabel("Nome do orfanato:");
    private JLabel lblEndereco = new JLabel("Endereço:");
    private JLabel lblTel = new JLabel("Telefone:");
    private JLabel lblEmail = new JLabel("Email:");
    private JLabel lblNPessoas = new JLabel("Total de acolhidos:");
    private JLabel lblNCrianca = new JLabel("Nº. de crianças:");
    private JLabel lblNJovens = new JLabel("Nº. de jovens:");
    private JLabel lblNAdultos = new JLabel("Nº. de adultos:");
    
    private JTextField jtfNome = new JTextField();
    private JTextField jtfEndereco = new JTextField();
    private JTextField jtfTel = new JTextField();
    private JTextField jtfEmail = new JTextField();
    private JTextField jtfNPessoas = new JTextField();
    private JTextField jtfNCriancas = new JTextField();
    private JTextField jtfJovens = new JTextField();
    private JTextField jtfAdultos = new JTextField();
    
    private JPanel top = new JPanel();
    private JPanel screen = new JPanel();
    
    private JSeparator jsNome = new JSeparator();
    private JSeparator jsEndereco = new JSeparator();
    private JSeparator jsTel = new JSeparator();
    private JSeparator jsEmail = new JSeparator();
    private JSeparator jsNPessoas = new JSeparator();
    private JSeparator jsCriancas = new JSeparator();
    private JSeparator jsJovens = new JSeparator();
    private JSeparator jsAdultos = new JSeparator();

    public ViewRegOrfanato() {
        setSize(730,400);        
        setResizable(false); 
        //setLocationRelativeTo(null);
        setLocation(480, 170);
        setUndecorated(true);
        setLayout(null);
    }
    
    public void view(){
        
        top.setBounds(0, 0, this.getWidth(), 30);
        screen.setBounds(10, 40, this.getWidth()-20, this.getHeight()-60);
        lblFundo.setBounds(0, 0, this.getWidth(), this.getHeight());
        //lblLogo.setBounds(this.getWidth()-380, this.getHeight()-280, 160, 161);
        lblClose.setBounds(this.getWidth()-30, 6, 18, 18);
        lblClose2.setBounds(this.getWidth()-30, 6, 18, 18);
        lblNome.setBounds(50, 105, 120, 30);
        jtfNome.setBounds(170, 100, 200, 30);
        jsNome.setBounds(170, 130, 200, 5);
        lblEndereco.setBounds(100, 155, 120, 30);
        jtfEndereco.setBounds(170, 150, 200, 30);
        jsEndereco.setBounds(170, 180, 200, 5);
        lblTel.setBounds(100, 205, 120, 30);
        jtfTel.setBounds(170, 200, 200, 30);
        jsTel.setBounds(170, 230, 200, 5);
        lblEmail.setBounds(115, 255, 120, 30);
        jtfEmail.setBounds(170, 250, 200, 30);
        jsEmail.setBounds(170, 280, 200, 30);
        lblNPessoas.setBounds(420, 105, 120, 30);
        jtfNPessoas.setBounds(545, 100, 120, 30);
        jsNPessoas.setBounds(545, 130, 120, 5);
        lblNCrianca.setBounds(435, 155, 120, 30);
        jtfNCriancas.setBounds(545, 150, 120, 30);
        jsCriancas.setBounds(545, 180, 120, 30);
        lblNJovens.setBounds(445, 205, 120, 30);
        jtfJovens.setBounds(545, 200, 120, 30);
        jsJovens.setBounds(545, 230, 120, 30);
        lblNAdultos.setBounds(440, 255, 120, 30);
        jtfAdultos.setBounds(545, 250, 120, 30);
        jsAdultos.setBounds(545, 280, 120, 30);
        btnReg.setBounds(this.getWidth()-180, this.getHeight()-80, 120, 30);
        
        top.setBackground(new Color(116, 120, 149));
        btnReg.setBackground(new Color(45, 82, 124));
        new Propriedades().setBackground(Color.black, jsAdultos, jsCriancas, jsEmail, jsEndereco, jsJovens, jsNPessoas, jsNome, jsTel);
        
        screen.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Registo de orfanato", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
   
        new Propriedades().setOpaque(false, jtfAdultos, jtfEmail, jtfEndereco, jtfJovens, jtfNCriancas, jtfNPessoas, jtfNome, jtfTel, screen);
        
        lblClose2.setVisible(false);
        
        new Propriedades().setBorder(null, jtfAdultos, jtfEmail, jtfEndereco, jtfJovens, jtfNCriancas, jtfNPessoas, jtfNome, jtfTel);
        
        new Propriedades().setForeground(Color.black, lblEmail, lblEndereco, lblNAdultos, lblNCrianca, lblNJovens,
            lblNPessoas, lblNome, lblTel, jtfAdultos, jtfEmail, jtfEndereco, jtfJovens, jtfNCriancas, jtfNPessoas, jtfNome, jtfTel,
            jsAdultos, jsCriancas, jsEmail, jsEndereco, jsJovens, jsNPessoas, jsNome, jsTel, btnReg);
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, lblEmail, lblEndereco, lblNAdultos, lblNCrianca, lblNJovens,
            lblNPessoas, lblNome, lblTel, jtfAdultos, jtfEmail, jtfEndereco, jtfJovens, jtfNCriancas, jtfNPessoas, jtfNome, jtfTel, btnReg);
      
        new Propriedades().add(this, lblClose, lblClose2, top, lblEmail, lblEndereco, lblNAdultos, lblNCrianca, lblNJovens, lblNPessoas, lblNome, lblTel, 
            jtfAdultos, jtfEmail, jtfEndereco, jtfJovens, jtfNCriancas, jtfNPessoas, jtfNome, jtfTel, jsAdultos, jsCriancas, jsEmail, 
            jsEndereco, jsJovens, jsNPessoas, jsNome, jsTel, btnReg, lblLogo, screen, lblFundo);
        
        Propriedades.isTypingNumber(jtfNPessoas, jtfNCriancas, jtfJovens, jtfAdultos);
        
        btnReg.addActionListener(this);
        
        new Propriedades().moveWindow(this);
        new Propriedades().disposeWindow(this, lblClose, lblClose2);
        setVisible(true);
    }    
    
    /*private void clean(){
        jtfAdultos.setText(null);
        jtfEmail.setText(null);
        jtfEndereco.setText(null);
        jtfJovens.setText(null);
        jtfNCriancas.setText(null);
        jtfNome.setText(null);
        jtfTel.setText(null);
        jtfNPessoas.setText(null);
    }*/
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnReg){
            int total = Integer.parseInt(jtfNCriancas.getText())+ Integer.parseInt(jtfJovens.getText())+ Integer.parseInt(jtfAdultos.getText());
            jtfNPessoas.setText(String.valueOf(total));
            
            if(!jtfNome.getText().isEmpty()&&!jtfEndereco.getText().isEmpty()&&ValidarDadosUser.validarTelefone(jtfTel)&&!jtfEmail.getText().isEmpty()&&!
                jtfNPessoas.getText().isEmpty()&&!jtfNCriancas.getText().isEmpty()&&!jtfJovens.getText().isEmpty()&&!jtfAdultos.getText().isEmpty()){
                int id = OrfanatoController.id();
                OrfanatoController.create(new Orfanato(id, jtfNome.getText(), jtfEndereco.getText(), jtfTel.getText(), jtfEmail.getText(),
                    total, Integer.parseInt(jtfNCriancas.getText()), Integer.parseInt(jtfJovens.getText()),
                    Integer.parseInt(jtfAdultos.getText()), data));
                JOptionPane.showMessageDialog(this, "Orfanato registado com sucesso.");
                
                int id_ = ActividadeController.id();
                Actividades act = new Actividades(id_, 0, 0, 1, 0, "MZN 0", "MZN 0",data, logged.get(0).getId());
                Actividades.dailyActivity(logged.get(0).getId(), act); 
                
                dispose();
            }else JOptionPane.showMessageDialog(this, "Preencha os campos com a infomação\nsolicitada para concluir o registo.", "Notificação", INFORMATION_MESSAGE);
        }
    }
}