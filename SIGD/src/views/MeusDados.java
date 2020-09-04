package views;

import controllers.FuncController;
import controllers.GestorController;
import controllers.UserLogged;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import static javax.swing.border.BevelBorder.RAISED;
import models.dao.CampoData;
import models.dao.CampoNuit;
import models.dao.Event;
import models.dao.Funcionario;
import models.dao.Gestor;
import models.dao.Log;
import models.dao.Propriedades;
import models.dao.ValidarDadosUser;
import models.dao.ValidarDocumentos;

/**
 *
 * @author CarlosMacaneta
 */
public class MeusDados extends JFrame implements ActionListener {

    private ArrayList<Log> logged = UserLogged.lista();

    private final int ID = logged.get(0).getId();
    private final String[] PROVINCIA = {"Maputo", "Gaza", "Inhambane", "Manica", "Sofala", "Tete", "Zambézia", "Nampula", "Niassa", "Cabo Delgado"};
    private final String[] TIPO_DOCUMENTO = {"Bilhete de identidade", "Passaporte", "DIRE", "Cartão de eleitor", "Carta de condução"};
    private final String[] GENERO = {"Masculino", "Femenino"};
    private final String[] ESTADO_CIVIL = {"Solteiro", "Casado", "Divorciado", "Separado", "Viúvo"};

    private JCheckBox checkEdit = new JCheckBox("Alterar dados do usuário.");

    private JLabel lblFundo = new JLabel(new ImageIcon("src/images/wallpaper1.jpg"));
    private JLabel lblLogo = new JLabel(new ImageIcon("src/images/donate2.png"));
    private JLabel lblClose = new JLabel(new ImageIcon("src/images/closeIcon2.png"));
    private JLabel lblClose2 = new JLabel(new ImageIcon("src/images/close.png"));
    private JLabel lblIcon = new JLabel(new ImageIcon("src/images/userLogin.png"));
    private JLabel lblDonate = new JLabel(new ImageIcon("src/images/donate.png"));
    private JLabel lblNomeSys = new JLabel("Sys donation");
    private JLabel lblId = new JLabel("Código(Id): "+ ID);
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
    private JLabel lblUsuario = new JLabel("Nome de usuário");
    private JLabel lblNacionalidade = new JLabel("Nacionalidade");
    private JLabel lblPossuiConta = new JLabel("Já tenho uma conta.");

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

    private JButton btnRegistar = new JButton("Alterar dados");

    public MeusDados() {
        setSize(850, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }

    public void view() {
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
        lblUsuario.setBounds(450, 330, 120, 30);
        jtfUsuario.setBounds(450, 352, 320, 30);
        jsUsuario.setBounds(450, 378, 320, 5);
        jpfSenha.setBounds(450, 404, 320, 30);
        jsSenha.setBounds(450, 430, 320, 5);
        checkEdit.setBounds(450, 450, 200, 30);
        btnRegistar.setBounds(625, 492, 129+2, 29+2);

        new Propriedades().setBackground(Color.BLACK, jsNome, jsData, jsNuit, jsNacionalidade, jsBairro, jsTelefone, jsEmail, jsSenha, jsUsuario, jsNDocumento, jsPossuiConta);
        new Propriedades().setBackground(new Color(242, 243, 245), jcbEstadoCivil, jcbGenero, jcbProvincia, jcbTipoDocumento, jcbGenero, checkEdit);
        btnRegistar.setBackground(new Color(45, 82, 124));
        checkEdit.setFocusable(false);

        new Propriedades().setForeground(Color.BLACK, lblId, lblNome, jtfNome, jsNome, jftfNuit, jsData, lblEstadoCivil, jsNuit, lblNacionalidade,
            jtfNacionalidade, jsNacionalidade, lblDataNascimento, jftfData, jcbEstadoCivil, lblGenero, lblBairro, lblUsuario, jsUsuario, lblCelular,
            lblTipoDocumento, lblDocumento, lblProvincia, lblNuit, lblEmail, jsEmail, jsSenha, jtfBairro, jsBairro, jtfNDocumento,
            jsNDocumento, jtfTelefone, jsTelefone, jtfEmail, jtfUsuario, jpfSenha, jcbTipoDocumento, jcbGenero, jcbProvincia, checkEdit);
        btnRegistar.setForeground(Color.WHITE);

        new  Propriedades().setFont("Calibri", Font.PLAIN, 14, lblId, lblNome, lblDataNascimento, lblGenero, lblBairro, lblUsuario, lblCelular, lblEstadoCivil, lblTipoDocumento,
            lblDocumento, lblNacionalidade, lblProvincia, lblNuit, lblEmail, lblPossuiConta, jcbEstadoCivil, jcbTipoDocumento, jcbGenero, jcbProvincia, btnRegistar, checkEdit);

        new Propriedades().setFont("Calibri", Font.PLAIN, 18, jftfData, jtfNome, jftfNuit, jtfBairro, jtfNDocumento, jtfNacionalidade, jtfTelefone, jtfEmail, jtfUsuario, jpfSenha);

        jpRegistar.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Meus dados", 1, 0, new Font("Calibri", Font.BOLD, 20), Color.BLACK));
        jpDadosPessoais.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(28, 91, 139), 2), "Dados pessoais", 1, 0, new Font("Calibri", Font.PLAIN, 14), Color.BLACK));
        jpDadoPessoal.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(28, 91, 139), 2), "Dados pessoais", 1, 0, new Font("Calibri", Font.PLAIN, 14), Color.BLACK));
        jpDadosSistema.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(28, 91, 139), 2), "Dados de acesso ao sistema", 1, 0, new Font("Calibri", Font.PLAIN, 14), Color.BLACK));
        new Propriedades().setBorder(null, checkEdit, jtfNome, jcbTipoDocumento, jftfNuit, jtfNDocumento, jcbTipoDocumento, jtfBairro, jtfTelefone, jtfEmail, jtfNacionalidade, jtfUsuario, jpfSenha, jftfData);
        btnRegistar.setBorder(BorderFactory.createSoftBevelBorder(RAISED));

        lblClose2.setVisible(false);

        new Propriedades().setOpaque(false, jcbTipoDocumento, jtfNome, jftfNuit, jftfData, jtfNDocumento, jtfEmail, jtfTelefone,
            jtfUsuario, jpfSenha, jtfBairro, jpDadosPessoais, jpDadoPessoal, jpRegistar, jpDadosSistema, jtfNacionalidade);

        new Propriedades().add(this, lblNomeSys, lblIcon, lblId, lblNome, lblUsuario, lblDataNascimento, lblTipoDocumento, jcbTipoDocumento, lblDocumento, lblNacionalidade, jtfNacionalidade, jsNacionalidade,
            lblNuit, jftfNuit, jsNuit, lblGenero, jcbGenero, lblBairro, lblProvincia, jcbProvincia, lblCelular, lblEmail, jtfNome, jsNome, jtfUsuario, jsUsuario, jftfData, jsData, lblEstadoCivil, jcbEstadoCivil, jtfNDocumento,
            jsNDocumento, jtfBairro, jsBairro, jtfTelefone, jsTelefone, jtfEmail, jsEmail, lblDonate, btnRegistar, jpEndereco, jpRegistar, jpDadosPessoais, checkEdit, jpDadoPessoal, jpDadosSistema, lblLogo,
            lblClose, lblClose2, lblFundo);

        new Propriedades().addActioListener(this, checkEdit, btnRegistar);

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
        checkEdit.addActionListener((ActionEvent ae)->{
            if(ae.getSource() == checkEdit){
                if(checkEdit.isSelected()){
                    enableComponents(true);
                }else {
                    enableComponents(false);
                }
            }
        });

        fill();
        enableComponents(false);

        new Propriedades().moveWindow(this);
        new Propriedades().disposeWindow(this, lblClose, lblClose2);
        setVisible(true);
    }

    private void fill(){
        String[] data = logged.get(0).getDataNascimento().split("-");
        String strDate = data[data.length - 1] + "/" + data[1] + "/" + data[0];
        
        jtfNome.setText(logged.get(0).getNome()+" "+ logged.get(0).getApelido());
        jftfData.setText(strDate);
        jtfNDocumento.setText(logged.get(0).getnDocumento());
        jftfNuit.setText(String.valueOf(logged.get(0).getNuit()));
        jtfBairro.setText(logged.get(0).getBairro());
        jtfNacionalidade.setText(logged.get(0).getNacionalidade());
        jtfTelefone.setText(logged.get(0).getTelefone());
        jtfEmail.setText(logged.get(0).getEmail());
        jcbEstadoCivil.setSelectedItem(logged.get(0).getEstadoCivil());
        jcbGenero.setSelectedItem(logged.get(0).getGenero());
        jcbProvincia.setSelectedItem(logged.get(0).getProvincia());
        jcbTipoDocumento.setSelectedItem(logged.get(0).getTipoDocumento());
        jtfUsuario.setText(logged.get(0).getNomeUsuario());
    }

    private void enableComponents(boolean bln){
        jtfBairro.setEditable(bln);
        jtfUsuario.setEditable(bln);
        jtfEmail.setEditable(bln);
        jtfTelefone.setEditable(bln);
        jtfNacionalidade.setEditable(bln);
        jtfNDocumento.setEditable(bln);
        jtfNome.setEditable(bln);
        jftfNuit.setEditable(bln);
        jftfData.setEditable(bln);
        jpfSenha.setEditable(bln);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnRegistar) {
            String[] strData = jftfData.getText().trim().split("/");
            if (!jtfNome.getText().isEmpty() && !jftfData.getText().isEmpty() && !jtfNDocumento.getText().isEmpty() && !jtfNacionalidade.getText().isEmpty() && !jftfNuit.getText().isEmpty()
                && !jtfBairro.getText().isEmpty() && !jtfTelefone.getText().isEmpty() && !jtfEmail.getText().isEmpty() && !jtfUsuario.getText().isEmpty() && checkEdit.isSelected()) {

                if (ValidarDocumentos.validarDocumento(jcbTipoDocumento, jtfNDocumento) && CampoData.validarData(strData) && (ValidarDadosUser.validarTelefone(jtfTelefone) && ValidarDadosUser.validarEmail(jtfEmail)
                    && ValidarDadosUser.validarUsername(jtfUsuario))) {

                    String[] nome = jtfNome.getText().split(" ");
                    String apelido = nome[nome.length - 1];
                    String[] data = jftfData.getText().split("/");
                    String strDate = data[data.length - 1] + "-" + data[1] + "-" + data[0];

                    if (logged.get(0).getNivelAcesso() == 1) {
                        Gestor g = new Gestor(ID, jtfUsuario.getText(), logged.get(0).getSenha(), 1,ValidarDadosUser.primeirosNomes(nome), apelido, strDate, (String) jcbTipoDocumento.getSelectedItem(),
                            jtfNDocumento.getText(), Integer.parseInt(jftfNuit.getText()), (String) jcbGenero.getSelectedItem(), (String) jcbEstadoCivil.getSelectedItem(), jtfNacionalidade.getText(),
                            (String) jcbProvincia.getSelectedItem(), jtfBairro.getText(), jtfTelefone.getText(), jtfEmail.getText());
                        new GestorController().update(ID, g);
                        Log.gestorLogged(g);
                    } else {
                        Funcionario f = new Funcionario(ID, jtfUsuario.getText(), logged.get(0).getSenha(), 2,ValidarDadosUser.primeirosNomes(nome), apelido, strDate, (String) jcbTipoDocumento.getSelectedItem(),
                            jtfNDocumento.getText(), Integer.parseInt(jftfNuit.getText()), (String) jcbGenero.getSelectedItem(), (String) jcbEstadoCivil.getSelectedItem(), jtfNacionalidade.getText(),
                            (String) jcbProvincia.getSelectedItem(), jtfBairro.getText(), jtfTelefone.getText(), jtfEmail.getText());
                        new FuncController().update(ID, f);
                        Log.funcLogged(f);
                    }
                    JOptionPane.showMessageDialog(this, "Dados alterados com sucesso.", "Notificação", INFORMATION_MESSAGE);
                    dispose();
                } else if (!ValidarDocumentos.validarDocumento(jcbTipoDocumento, jtfNDocumento)) {
                    JOptionPane.showMessageDialog(this, "Número de documento inserido é inválido.\nTente novamante.", "Notificação", INFORMATION_MESSAGE);
                } else if (!CampoData.validarData(strData)) {
                    JOptionPane.showMessageDialog(this, "Data inserida é inválida.\nSiga o formato (dd/mm/yyyy)", "Notificação", INFORMATION_MESSAGE);
                } else if (!ValidarDadosUser.validarTelefone(jtfTelefone)) {
                    JOptionPane.showMessageDialog(this, "Número de telefone inserido é inválido.\nTente novamante.", "Notificação", INFORMATION_MESSAGE);
                } else if (!ValidarDadosUser.validarEmail(jtfEmail)) {
                    JOptionPane.showMessageDialog(this, "Email inserido é inválido.\nTente novamante.", "Notificação", INFORMATION_MESSAGE);
                } else if (!ValidarDadosUser.validarUsername(jtfUsuario)) {
                    JOptionPane.showMessageDialog(this, "Nome de usuário inserido é inválido.\nTente novamante.", "Notificação", INFORMATION_MESSAGE);
                } else if (!ValidarDadosUser.validarSenha(jpfSenha)) {
                    JOptionPane.showMessageDialog(this, "Senha inserida é inválida.\nTente novamante.", "Notificação", INFORMATION_MESSAGE);
                }
            }else if(!checkEdit.isSelected()){
                JOptionPane.showMessageDialog(this, "Selecione a caixa de confirmação\npara alterar seus dados.");
            }else{
                JOptionPane.showMessageDialog(this, "Preencha os campos", "Notificação", INFORMATION_MESSAGE);
            }
        }
    }
}