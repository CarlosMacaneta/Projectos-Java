package views;

import controllers.FuncController;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import models.dao.CampoData;
import models.dao.CampoNuit;
import models.dao.ControleUsuario;
import models.dao.Criptografia;
import models.dao.Event;
import models.dao.Funcionario;
import models.dao.GerarCodigoId;
import models.dao.Propriedades;
import models.dao.ValidarDadosUser;
import models.dao.ValidarDocumentos;
/**
 *
 * @author Carlos Macaneta
 */
public class ViewRegistarFuncionario extends JFrame implements ActionListener {
    
    private final int ID = new GerarCodigoId().codigoFinal(false);
    private final String[] PROVINCIA = {"Maputo", "Gaza", "Inhambane", "Manica", "Sofala", "Tete", "Zambézia", "Nampula", "Niassa", "Cabo Delgado"};
    private final String[] TIPO_DOCUMENTO = {"Bilhete de identidade", "Passaporte", "DIRE", "Cartão de eleitor", "Carta de condução"};
    private final String[] GENERO = {"Masculino", "Femenino"};
    private final String[] ESTADO_CIVIL = {"Solteiro", "Casado", "Divorciado", "Separado", "Viúvo"};
    
    private JLabel lblFundo = new JLabel(new ImageIcon("src/images/wallpaper1.jpg"));
    private JLabel lblLogo = new JLabel(new ImageIcon("src/images/donate2.png"));
    private JLabel lblClose = new JLabel(new ImageIcon("src/images/closeIcon2.png"));
    private JLabel lblClose2 = new JLabel(new ImageIcon("src/images/close.png"));
    private JLabel lblIcon = new JLabel(new ImageIcon("src/images/userLogin.png"));
    private JLabel lblDonate = new JLabel(new ImageIcon("src/images/donate.png"));
    private JLabel lblNomeSys = new JLabel("Sys donation");
    private JLabel lblId = new JLabel("Código do func.: "+ ID);
    private JLabel lblNome = new JLabel("Nome completo");
    private JLabel lblTipoDocumento = new JLabel("Tipo de documento");
    private JLabel lblDocumento = new JLabel("Nº. do documento");
    private JLabel lblNuit = new JLabel("Nuit nº.");
    private JLabel lblDataNascimento = new JLabel("Data de nascimento");
    private JLabel lblEstadoCivil = new JLabel("Estado civil");
    private JLabel lblGenero = new JLabel("Género");
    private JLabel lblProvincia = new JLabel("Província");
    private JLabel lblBairro = new JLabel("Bairro");
    private JLabel lblCelular = new JLabel("Telefone");
    private JLabel lblEmail = new JLabel("Email");
    private JLabel lblUsuario = new JLabel("Nome de usuário: ");
    private JLabel lblSenha = new JLabel("Palavra-passe: ");
    private JLabel lblNacionalidade = new JLabel("Nacionalidade");
    private JLabel lblPossuiConta = new JLabel("Já tenho uma conta.");
    private JLabel lblUsername = new JLabel();
    private JLabel lblPassword = new JLabel(String.valueOf(ID));
    
    private JComboBox<String> jcbProvincia = new JComboBox<>(PROVINCIA);
    private JComboBox<String> jcbTipoDocumento = new JComboBox<>(TIPO_DOCUMENTO);
    private JComboBox<String> jcbGenero = new JComboBox<>(GENERO);
    private JComboBox<String> jcbEstadoCivil = new JComboBox<>(ESTADO_CIVIL);
    
    private CampoData jftfData = new CampoData();
    
    private CampoNuit jftfNuit = new CampoNuit();
    
    private JTextField jtfNome = new JTextField();
    private JTextField jtfUsuario = new JTextField();
    private JTextField jtfNDocumento = new JTextField();
    private JTextField jtfNacionalidade = new JTextField();
    private JTextField jtfBairro = new JTextField();
    private JTextField jtfTelefone = new JTextField();
    private JTextField jtfEmail = new JTextField();
    
    private JPasswordField jpfSenha = new JPasswordField();
    
    private JPanel jpRegistar = new JPanel();
    private JPanel jpDadosPessoais = new JPanel();
    private JPanel jpDadoPessoal = new JPanel();
    private JPanel jpDadosSistema = new JPanel();
    private JPanel jpEndereco = new JPanel();
    
    private JSeparator jsNome = new JSeparator();
    private JSeparator jsUsuario = new JSeparator();
    private JSeparator jsData = new JSeparator();
    private JSeparator jsNDocumento = new JSeparator();
    private JSeparator jsNuit = new JSeparator();
    private JSeparator jsBairro = new JSeparator();
    private JSeparator jsTelefone = new JSeparator();
    private JSeparator jsEmail = new JSeparator();
    private JSeparator jsSenha = new JSeparator();
    private JSeparator jsNacionalidade = new JSeparator();
    private JSeparator jsPossuiConta = new JSeparator();
    
    private JButton btnRegistar = new JButton();
    
    public ViewRegistarFuncionario() {        
        setSize(850, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        init();
    }
    
    private void init() {
        setLayout(null);
       
        lblFundo.setBounds(0, 0, 850, 550);
        lblClose.setBounds(820, 10, 18, 18);
        lblClose2.setBounds(820, 10, 18, 18);
        lblLogo.setBounds(320, 180, 200, 200);
        lblId.setBounds(75, 70, 180, 20);
        lblIcon.setBounds(175, 95, 100, 100);
        jpRegistar.setBounds(20, 20, 800, 515);
        jpDadosPessoais.setBounds(40, 50, 370, 460);
        jpDadoPessoal.setBounds(420, 50, 370, 250);
        jpDadosSistema.setBounds(420, 310, 370, 200);
        lblNome.setBounds(75, 205, 100, 30);
        jtfNome.setBounds(75, 227, 300, 30);
        jsNome.setBounds(75, 255, 300, 5);
        lblDataNascimento.setBounds(75, 265, 120, 30);
        jftfData.setBounds(75, 287, 120, 30);
        lblEstadoCivil.setBounds(250, 265, 120, 30);
        jcbEstadoCivil.setBounds(250, 287, 125, 30);
        jsData.setBounds(75, 313, 120, 30);
        lblTipoDocumento.setBounds(75, 325, 120, 30);
        jcbTipoDocumento.setBounds(75, 347, 150, 30);
        lblDocumento.setBounds(75, 382, 120, 30);
        jtfNDocumento.setBounds(75, 407, 300, 30 );
        jsNDocumento.setBounds(75, 435, 300, 5);
        lblGenero.setBounds(250, 325, 100, 30);
        jcbGenero.setBounds(250, 347, 125, 30);
        lblNuit.setBounds(75, 440, 100, 20);
        jftfNuit.setBounds(75, 460, 120, 30);
        jsNuit.setBounds(75, 488, 120, 5);
        lblProvincia.setBounds(450, 70, 100, 20);
        jcbProvincia.setBounds(450, 92, 110, 30);
        lblBairro.setBounds(570, 70, 100, 20);
        jtfBairro.setBounds(570, 92, 200, 30);
        jsBairro.setBounds(570, 118, 200, 5);
        lblNacionalidade.setBounds(450, 125, 120, 30);
        jtfNacionalidade.setBounds(450, 147, 320, 30);
        jsNacionalidade.setBounds(450, 173, 320, 5);
        lblEmail.setBounds(450, 240, 100, 20);
        jtfEmail.setBounds(450, 260, 320, 30);
        jsEmail.setBounds(450, 286, 320, 5);
        lblCelular.setBounds(450, 180, 100, 30);
        jtfTelefone.setBounds(450, 200, 320, 30);
        jsTelefone.setBounds(450, 226, 320, 5);
        lblUsuario.setBounds(450, 352, 120, 30);
        lblUsername.setBounds(565, 352, 320, 30);
        lblSenha.setBounds(450, 404, 100, 30);
        lblPassword.setBounds(545, 404, 320, 30);
        lblPossuiConta.setBounds(450, 450, 130, 30);
        jsPossuiConta.setBounds(450, 470, 110, 1);
        btnRegistar.setBounds(625, 492, 129+2, 29+2);
        
        new Propriedades().setBackground(Color.BLACK, jsNome, jsData, jsNuit, jsNacionalidade, jsBairro, jsTelefone, jsEmail, jsSenha, jsUsuario, jsNDocumento, jsPossuiConta);            
        new Propriedades().setBackground(new Color(242, 243, 245), jcbEstadoCivil, jcbGenero, jcbProvincia, jcbTipoDocumento, jcbGenero);           
        btnRegistar.setBackground(new Color(255));
        
        new Propriedades().setForeground(Color.BLACK, lblId, lblNome, jtfNome, jsNome, jftfNuit, jsData, lblEstadoCivil, jsNuit, lblNacionalidade,
            jtfNacionalidade, jsNacionalidade, lblDataNascimento, jftfData, jcbEstadoCivil, lblGenero, lblBairro, lblUsuario, jsUsuario, lblCelular,
            lblTipoDocumento, lblDocumento, lblProvincia, lblNuit, lblEmail, jsEmail, lblSenha, jsSenha, jtfBairro, jsBairro, jtfNDocumento, lblPassword,
            jsNDocumento, jtfTelefone, jsTelefone, jtfEmail, jtfUsuario, jpfSenha, jcbTipoDocumento, jcbGenero, jcbProvincia, lblPossuiConta, lblUsername, jsPossuiConta);           
        btnRegistar.setForeground(new Color(255));
        
        new  Propriedades().setFont("Calibri", Font.PLAIN, 14, lblId, lblNome, lblDataNascimento, lblGenero, lblBairro, lblUsuario, lblCelular, lblEstadoCivil, lblTipoDocumento,
            lblDocumento, lblNacionalidade, lblProvincia, lblNuit, lblEmail, lblSenha, lblPossuiConta, jcbEstadoCivil, jcbTipoDocumento, jcbGenero, jcbProvincia, lblUsername, lblPassword);           
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 18, jftfData, jtfNome, jftfNuit, jtfBairro, jtfNDocumento, jtfNacionalidade, jtfTelefone, jtfEmail, jtfUsuario, jpfSenha);            
        lblPossuiConta.setFont(new Font("Calibri", 0, 13));
        
        jpRegistar.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Registar funcionário", 1, 0, new Font("Calibri", Font.BOLD, 20), Color.BLACK));
        jpDadosPessoais.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(28, 91, 139), 2), "Dados pessoais", 1, 0, new Font("Calibri", 0, 14), Color.BLACK));
        jpDadoPessoal.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(28, 91, 139), 2), "Dados pessoais", 1, 0, new Font("Calibri", 0, 14), Color.BLACK));
        jpDadosSistema.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(28, 91, 139), 2), "Dados de acesso ao sistema", 1, 0, new Font("Calibri", 0, 14), Color.BLACK));
        new Propriedades().setBorder(null, btnRegistar, jtfNome, jcbTipoDocumento, jftfNuit, jtfNDocumento, jcbTipoDocumento, jtfBairro, jtfTelefone, jtfEmail, jtfNacionalidade, jtfUsuario, jpfSenha, jftfData);           
        
        btnRegistar.setIcon(new ImageIcon("src/images/btnRegistar.png"));
        
        lblClose2.setVisible(false);
  
        new Propriedades().setOpaque(false, jcbTipoDocumento, jtfNome, jftfNuit, jftfData, jtfNDocumento, jtfEmail, jtfTelefone,
            jtfUsuario, jpfSenha, jtfBairro, jpDadosPessoais, jpDadoPessoal, jpRegistar, jpDadosSistema, jtfNacionalidade, btnRegistar);
        
        new Propriedades().add(this, lblNomeSys, lblIcon, lblId, lblNome, lblUsuario, lblUsername, lblSenha, lblPassword, lblDataNascimento, lblTipoDocumento, jcbTipoDocumento, lblDocumento, lblNacionalidade, jtfNacionalidade, jsNacionalidade,
            lblNuit, jftfNuit, jsNuit, lblGenero, jcbGenero, lblBairro, lblProvincia, jcbProvincia, lblCelular, lblEmail, jtfNome, jsNome, jtfUsuario, jsUsuario, jftfData, jsData, lblEstadoCivil, jcbEstadoCivil, jtfNDocumento,
            jsNDocumento, jtfBairro, jsBairro, jtfTelefone, jsTelefone, jtfEmail, jsEmail, jpfSenha, jsSenha, lblDonate, btnRegistar, jpEndereco, jpRegistar, jpDadosPessoais, jpDadoPessoal, jpDadosSistema, lblPossuiConta, jsPossuiConta, lblLogo,
        lblClose, lblClose2, lblFundo);
        
        btnRegistar.addActionListener(this);
        
        jtfNome.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke){
                if(!jtfNome.getText().isEmpty()){
                    lblUsername.setText(jtfNome.getText().trim());
                }else lblUsername.setText(null);
            }
        });
        
        jtfNDocumento.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent fe){
                Event.upperCase(jtfNDocumento);
            }
        });
        
        jpfSenha.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent ke){
                if(ke.getKeyChar() == KeyEvent.VK_ENTER){
                    btnRegistar.doClick();
                }
            }
        });
        
        lblPossuiConta.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                dispose();
                new ViewLogin().setVisible(true);
            }
        });
        
        new Propriedades().moveWindow(this);
        new Propriedades().disposeWindow(this, lblClose, lblClose2);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnRegistar){
            String[] strData = jftfData.getText().trim().split("/");
            if(!jtfNome.getText().isEmpty()&&!jftfData.getText().isEmpty()&&!jtfNDocumento.getText().isEmpty()&&!jtfNacionalidade.getText().isEmpty()&&!jftfNuit.getText().isEmpty()
                &&!jtfBairro.getText().isEmpty()&&!jtfTelefone.getText().isEmpty()&&!jtfEmail.getText().isEmpty()){
                        
                if(ValidarDocumentos.validarDocumento(jcbTipoDocumento, jtfNDocumento)&&CampoData.validarData(strData)&&(ValidarDadosUser.validarTelefone(jtfTelefone)&&ValidarDadosUser.validarEmail(jtfEmail))){
                    
                    String[] nome = jtfNome.getText().split(" ");
                    String apelido = nome[nome.length - 1];
                    String[] data = jftfData.getText().split("/");
                    String strDate = data[data.length - 1] + "-" + data[1] + "-" + data[0];
                    ControleUsuario userControl = new ControleUsuario();
                    
                    if(userControl.nivelAcesso() == 2){
                        if(new FuncController().create(new Funcionario(ID, lblUsername.getText(), Criptografia.encriptografar(lblPassword.getText()), userControl.nivelAcesso(),
                            ValidarDadosUser.primeirosNomes(nome), apelido, strDate, (String) jcbTipoDocumento.getSelectedItem(),jtfNDocumento.getText(), Integer.parseInt(jftfNuit.getText()), (String) jcbGenero.
                            getSelectedItem(), (String) jcbEstadoCivil.getSelectedItem(), jtfNacionalidade.getText(), (String) jcbProvincia.getSelectedItem(), jtfBairro.getText(), jtfTelefone.getText(), jtfEmail.getText()))){
                            JOptionPane.showMessageDialog(this, "Funcionário registado com sucesso.", "Notificação", INFORMATION_MESSAGE);
                            new GerarCodigoId().codigoFinal(true);
                        }else JOptionPane.showMessageDialog(this, "Ocorreu um erro ao registar funcionário", "ERRO", ERROR_MESSAGE);
                    }
                    dispose();
                }else if(!ValidarDocumentos.validarDocumento(jcbTipoDocumento, jtfNDocumento)){
                    JOptionPane.showMessageDialog(this, "Número de documento inserido é inválido.\nTente novamante.","Notificação", INFORMATION_MESSAGE);
                }else if(!CampoData.validarData(strData)){
                    JOptionPane.showMessageDialog(this, "Data inserida é inválida.\nSiga o formato (dd/mm/yyyy)", "Notificação", INFORMATION_MESSAGE);
                }else if(!ValidarDadosUser.validarTelefone(jtfTelefone)){
                    JOptionPane.showMessageDialog(this, "Número de telefone inserido é inválido.\nTente novamante.","Notificação", INFORMATION_MESSAGE);
                }else if(!ValidarDadosUser.validarEmail(jtfEmail)){
                    JOptionPane.showMessageDialog(this, "Email inserido é inválido.\nTente novamante.","Notificação", INFORMATION_MESSAGE);
                }else if(!ValidarDadosUser.validarUsername(jtfUsuario)){
                    JOptionPane.showMessageDialog(this, "Nome de usuário inserido é inválido.\nTente novamante.","Notificação", INFORMATION_MESSAGE);
                }else if(!ValidarDadosUser.validarSenha(jpfSenha)){
                    JOptionPane.showMessageDialog(this, "Senha inserida é inválida.\nTente novamante.","Notificação", INFORMATION_MESSAGE);
                } 
            }else{
                JOptionPane.showMessageDialog(this, "Preencha os campos", "Notificação", INFORMATION_MESSAGE);
            }
        }
    }
}