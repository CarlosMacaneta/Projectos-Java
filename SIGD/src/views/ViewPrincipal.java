package views;

import controllers.GestorController;
import controllers.UserLogged;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import models.dao.Criptografia;
import models.dao.Gestor;
import models.dao.Log;
import models.dao.Propriedades;

/**
 *
 * @author CarlosMacaneta
 */
public class ViewPrincipal extends JFrame implements ActionListener {
    
    private ArrayList<Gestor> gest = new GestorController().lista();
    private Calendar data = Calendar.getInstance(new Locale("PT", "MZ"));
    private int index;
    private final int[] DATA = {data.get(Calendar.DATE), (data.get(Calendar.MONTH)+1), data.get(Calendar.YEAR)};
    private final String STR_DATA = String.format("%02d/%02d/%d",DATA[0], DATA[1], DATA[2]); 
    
    private Dimension screenSize = getToolkit().getScreenSize();
    
    private JButton btnOrfanatos = new JButton("Benefinciário", new ImageIcon("src/images/helping.png")); 
    private JButton btnDoar = new JButton("Doar", new ImageIcon("src/images/doar.png"));
    private JButton btnDoadores = new JButton("Doadores", new ImageIcon("src/images/give.png"));
    private JButton btnFunc = new JButton("Funcionários", new ImageIcon("src/images/func.png"));
    private JButton btnStock = new JButton("Stock de doações", new ImageIcon("src/images/stock.png"));
    private JButton btnRelatorios = new JButton("Relatórios", new ImageIcon("src/images/report.png"));
    private JButton btnOpcoes = new JButton("Configurações", new ImageIcon("src/images/config.png"));
    private JButton btnSobre = new JButton("Sobre", new ImageIcon("src/images/about.png"));
    private JButton btnLogOut = new JButton("Terminar sessão", new ImageIcon("src/images/log-off.png"));
    private JButton btnCloseTab = new JButton("Fechar aba", new ImageIcon("src/images/close_tab.png"));
    private JButton btnCloseTab1 = new JButton("Fechar aba", new ImageIcon("src/images/close_tab.png"));
    private JButton btnCloseTab2 = new JButton("Fechar aba", new ImageIcon("src/images/close_tab.png"));
    private JButton btnCloseTab3 = new JButton("Fechar aba", new ImageIcon("src/images/close_tab.png"));
    private JButton btnCloseTab4 = new JButton("Fechar aba", new ImageIcon("src/images/close_tab.png"));
    private JButton btnCloseTab5 = new JButton("Fechar aba", new ImageIcon("src/images/close_tab.png"));
    private JButton btnMeusDados = new JButton("Meus dados", new ImageIcon("src/images/details.png"));
    private JButton btnRedefinir = new JButton("Redefinir senha", new ImageIcon("src/images/password.png"));
    private JButton btnSair = new JButton("Voltar", new ImageIcon("src/images/go_back.png"));
    private JButton btnRemoverConta = new JButton("Apagar minha conta", new ImageIcon("src/images/delete.png"));

    private JLabel lblMenu = new JLabel(new ImageIcon("src/images/menu.png"));
    private JLabel lblSys = new JLabel("Sys Donation");
    private JLabel lblData = new JLabel(STR_DATA);
    private JLabel lblTime = new JLabel();
    private JLabel lblConfig = new JLabel(new ImageIcon("src/images/configs.png"));
    
    private JPanel barra = new JPanel();
    private JPanel logo = new JPanel();
    private JPanel leftBar = new JPanel();
    private JPanel jpHome = new JPanel();
    private JPanel center = new JPanel();
    private JPanel middleCont = new JPanel();
    private JPanel jpDate = new JPanel();
    private JPanel config = new JPanel();
    
    private JTabbedPane tab = new JTabbedPane();
    
    private ViewRelatorio relatorio = new ViewRelatorio();
    private ViewFuncionario funcionario = new ViewFuncionario();
    private ViewDoadores doadores = new ViewDoadores();
    private ViewDoar doar = new ViewDoar();
    private ViewListaOrfanato orfanatos = new ViewListaOrfanato();
    private ViewStock stock = new ViewStock();
    
    private void clock(){
        new Thread(){
            @Override
            public void run(){
                try {
                    while(true){
                        Calendar c = Calendar.getInstance(new Locale("PT", "MZ"));
                        int hour = c.get(Calendar.HOUR_OF_DAY);
                        int min = c.get(Calendar.MINUTE);
                        int sec = c.get(Calendar.SECOND);
                        lblTime.setText(String.format("%02d:%02d:%02d", hour, min, sec));
                        sleep(1000);
                    }
                }catch (InterruptedException ex) {
                    System.err.println("Error: "+ex.getMessage());
                }
            }
        }.start();
    }
    
    public ViewPrincipal(){
        setSize(screenSize.width, screenSize.height);        
        setResizable(!false); 
        setLocationRelativeTo(null);
        setUndecorated(!true);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        clock();
        view();
    }
    
    private void view(){
        
        Container main = this.getContentPane();
        Container topBar = new JPanel();
        
        new Propriedades().setBackground(new Color(22, 41, 80), topBar, jpDate, leftBar, middleCont, btnOrfanatos, btnDoar, btnDoadores, btnFunc, btnStock, btnRelatorios, btnOpcoes, btnSobre,
            btnLogOut, btnMeusDados, btnRedefinir, btnSair, config, btnRemoverConta);
        
        new Propriedades().setLineBorder(Color.white,1, btnOrfanatos, btnDoar, btnDoadores, btnFunc, btnStock, btnRelatorios, btnOpcoes, btnSobre, btnLogOut, btnMeusDados, btnRedefinir, btnSair, btnRemoverConta);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE));
        new Propriedades().setBorder(new LineBorder(new Color(22, 41, 80), 3), middleCont, leftBar, center);
        
        new Propriedades().setFocusable(false,  btnOrfanatos, btnDoar, btnDoadores, btnFunc, btnStock, btnRelatorios, btnOpcoes, btnSobre, btnLogOut, btnMeusDados, btnRedefinir, btnSair, btnRemoverConta);
        
        lblSys.setFont(new Font("Bell MT", Font.ITALIC|Font.HANGING_BASELINE, 28));
        new Propriedades().setFont("Calibri", Font.PLAIN, 16, lblTime, lblData, btnOrfanatos, btnDoar, btnDoadores, btnFunc, btnStock, btnRelatorios, btnOpcoes, btnSobre, btnRemoverConta,
            btnLogOut, btnMeusDados, btnRedefinir, btnSair);
        
        new Propriedades().setForeground(Color.white, lblSys, lblTime, lblData,btnOrfanatos, btnDoar, btnDoadores, btnFunc, btnStock, btnRelatorios, btnOpcoes, btnSobre, btnLogOut
            , btnMeusDados, btnRedefinir, btnSair, btnRemoverConta);
        
        leftBar.setLayout (new GridLayout(9, 1, 20, 20));
        middleCont.setLayout(new FlowLayout(9, 9, 9));
        config.setLayout(new GridLayout(6,1, 20, 20));
        center.setLayout(new BorderLayout());
        topBar.setLayout(new GridLayout(1, 3, 400, 450));
        jpDate.setLayout(new FlowLayout(FlowLayout.CENTER, 350, 0));
        main.setLayout(new BorderLayout(10, 10));        
        
        new Propriedades().setOpaque(true, btnOrfanatos, btnDoar, btnDoadores, btnFunc, btnStock, btnRelatorios, btnOpcoes, btnSobre,
            btnMeusDados, btnRedefinir, btnSair, btnLogOut, btnRemoverConta);
        config.setVisible(false);

        new Propriedades().setPreferredSize(new Dimension(200, 40) , btnOrfanatos, btnDoar, btnDoadores, btnFunc, btnStock, btnRelatorios,
            btnOpcoes, btnSobre, btnMeusDados, btnRedefinir, btnSair, btnLogOut, btnRemoverConta);

        new Propriedades().addContainer(topBar, lblSys, lblTime, lblData);
        new Propriedades().addContainer(leftBar, lblMenu, btnOrfanatos, btnDoar, btnDoadores, btnFunc, btnStock, btnRelatorios, btnOpcoes, btnLogOut);
        new Propriedades().addContainer(config, lblConfig, btnMeusDados, btnRedefinir, btnRemoverConta, btnSobre, btnSair);
        middleCont.add(leftBar);
        middleCont.add(config);
        center.add(tab);
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                UserLogged.delete();
            }
        });
        
        main.add(topBar, BorderLayout.NORTH);
        main.add(middleCont, BorderLayout.WEST);
        main.add(center, BorderLayout.CENTER);     
        
        new Propriedades().addActioListener(this, btnOrfanatos, btnDoar, btnDoadores, btnFunc, btnStock, btnRelatorios, btnOpcoes, btnSobre,
            btnCloseTab, btnMeusDados, btnRedefinir, btnSair, btnRemoverConta, btnLogOut);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnOrfanatos){
            tab.add("Beneficiários", orfanatos.init(tab, "Beneficiários", btnCloseTab));
        }else if(ae.getSource() == btnDoar){
            tab.add("Doar para orfanatos", doar.init(tab, "Doar para orfanatos", btnCloseTab4));
        }else if(ae.getSource() == btnFunc){
            tab.add("Funcionários", funcionario.init(tab, "Funcionários", btnCloseTab1));
        }else if(ae.getSource() == btnDoadores){
            tab.add("Doadores", doadores.init(tab, "Doadores", btnCloseTab2)); 
        }else if(ae.getSource() == btnStock){
            tab.add("Stock", stock.init(tab, "Stock", btnCloseTab3));
        }else if(ae.getSource() == btnRelatorios){
            tab.add("Relatórios", relatorio.init(tab, "Relatórios", btnCloseTab5));
        }else if(ae.getSource() == btnSobre) {
            try {
                Desktop.getDesktop().open(new File("sobre/sobre.html"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "ERRO: " + ex.getMessage(), "ERRO", ERROR_MESSAGE);
            }
        }else if(ae.getSource() == btnOpcoes) {
            leftBar.setVisible(false);
            config.setVisible(true);
        }else if(ae.getSource() == btnMeusDados) {
            new MeusDados().view();
        }else if(ae.getSource() == btnRedefinir) {
            ArrayList<Log> logged = UserLogged.lista();
            String senha = JOptionPane.showInputDialog(this, "Digite sua nova senha.");
            for(Gestor g: gest){
                if(g.getId() == logged.get(0).getId()){
                    if(!senha.isEmpty()){
                        g.setSenha(Criptografia.encriptografar(senha));
                        new GestorController().update(logged.get(0).getId(), g);
                        JOptionPane.showMessageDialog(this, "Sua senha foi alterada com sucesso.");
                        break;
                    }else break;
                }
            }
        }else if(ae.getSource() == btnRemoverConta){
            Object[] options = {"Sim","Não"};
            int yesNo = JOptionPane.showOptionDialog(this, "Tem certeza de que deseja remover ?",null, YES_NO_OPTION, QUESTION_MESSAGE, null,options, options[0]);
            if(yesNo == 0){
                ArrayList<Log> logged = UserLogged.lista();
                new GestorController().delete(logged.get(0).getId());
                dispose();
                UserLogged.delete();
                JOptionPane.showMessageDialog(this, "Sua conta foi removida com sucesso.");
                new ViewLogin().setVisible(true);
            }else JOptionPane.showMessageDialog(this, "Cancelado.");
        }else if(ae.getSource() == btnSair){
            config.setVisible(false);
            leftBar.setVisible(true);
        }else if(ae.getSource() == btnLogOut){
            dispose();
            UserLogged.delete();
            new ViewLogin().setVisible(true);
        }
    }
}