package views;

import controllers.EstController;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.LineBorder;
import models.Codigo;
import models.Estudante;

/**
 *
 * @author CarlosMacaneta
 */
public class RegEstudante extends JFrame implements ActionListener{
    private int id = Codigo.idEst();
    private JButton btnReg = new JButton("Registar");
    private JComboBox<String> genero = new JComboBox<>(new String[]{"Masculino", "Feminino"});
    
    private JTextField nome = new JTextField();
    private JTextField nota1 = new JTextField();
    private JTextField nota2 = new JTextField();
    
    private JLabel lblId = new JLabel("Id: "+id);
    private JLabel lblNome = new JLabel("Nome:");
    private JLabel lblGenero = new JLabel("Género:");
    private JLabel lblNota1 = new JLabel("Nota 1:");
    private JLabel lblNota2 = new JLabel("Nota 2:");
    
    private JPanel panel = new JPanel();

    public RegEstudante() {
        super("Registo do estudante");
        setSize(600, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(238, 232, 170)));
    }
    
    public void init(){
        Container main = this.getContentPane();
        main.setLayout(null);
        
        main.setBackground(new Color(238, 232, 170));
        panel.setOpaque(false);
        
        panel.setBorder(new LineBorder(new Color(20, 63, 114), 1));
        
        lblId.setBounds(50, 50, 120, 30);
        lblNome.setBounds(50, 95, 120, 30);
        nome.setBounds(200-80, 95, 200, 30);
        lblGenero.setBounds(50, 135, 120, 30);
        genero.setBounds(200-80, 135, 200, 30);
        lblNota1.setBounds(410-50, 95, 120, 30);
        nota1.setBounds(420, 95, 120, 30);
        lblNota2.setBounds(410-50, 135, 120, 30);
        nota2.setBounds(420, 135, 120, 30);
        panel.setBounds(10, 10, 565, 245);
        btnReg.setBounds(420-60, 200, 180, 30);
        
        main.add(lblId);
        main.add(lblNome);
        main.add(nome);
        main.add(lblGenero);
        main.add(genero);
        main.add(lblNota1);
        main.add(nota1);
        main.add(lblNota2);
        main.add(nota2);
        main.add(btnReg);
        main.add(panel);
        
        btnReg.addActionListener(this);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnReg){
            if(!nome.getText().isEmpty() &&! nota1.getText().isEmpty() &&! nota2.getText().isEmpty()){
                Estudante f = new Estudante();
                f.setMedia(Double.parseDouble(nota1.getText()), Double.parseDouble(nota2.getText()));
                double media = f.getMedia();
                f.setSituacao(media);
                String situacao = f.getSitucao();
                EstController.create(new Estudante(id, nome.getText(), (String) genero.getSelectedItem(),Double.parseDouble(nota1.getText()), 
                    Double.parseDouble(nota2.getText()), media, situacao));
                JOptionPane.showMessageDialog(this, "Estudante registado com sucesso");
                dispose();
            }else JOptionPane.showMessageDialog(this, "Preencha todos os campos");
        }
    }   
}