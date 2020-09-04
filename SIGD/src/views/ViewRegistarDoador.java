package views;

import controllers.ActividadeController;
import controllers.DoadorController;
import controllers.DonativoController;
import controllers.FinancaController;
import controllers.StockController;
import controllers.UserLogged;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.dao.Actividades;
import models.dao.CampoMoney;
import models.dao.DoadorDAO;
import models.dao.Donativo;
import models.dao.Event;
import models.dao.Financa;
import models.dao.Log;
import models.dao.Propriedades;
import models.dao.Stock;
import models.dao.ValidarDadosUser;
import models.dao.ValidarDocumentos;

/**
 *
 * @author Carlos Macaneta
 */
public class ViewRegistarDoador extends JFrame implements ActionListener{
    
    private Calendar c = Calendar.getInstance();
    private String data = String.format("%d-%02d-%02d", c.get(Calendar.YEAR), (c.get(Calendar.MONTH)+1), c.get(Calendar.DATE));
    private ArrayList<String> donativo = new ArrayList<>();
    private ArrayList<Donativo> don_ = new ArrayList<>(); 
    
    private String categ;
    private int id;
    private final boolean[] EDIT = {false, false, false, false, false};
    
    private String dinheiro;
    private final String[] MOEDAS = {"MZN","USD", "EUR", "ZAR"};
    private final String[] TIPO_DOCUMENTO = {"Bilhete de identidade", "Passaporte", "Cartão de eleitor", "Carta de condução"};
    
    private DefaultTableModel modelo = new DefaultTableModel();
    
    private JComboBox<String> jcbMoedas = new JComboBox<>(MOEDAS);
    private JComboBox<String> jcbTipoDocumento = new JComboBox<>(TIPO_DOCUMENTO);
    private JComboBox<String> jcbGenero = new JComboBox<>(new String[]{"Masculino", "Femenino"});
    private JComboBox<String> jcbCategoria = new JComboBox<>(new String[]{
        "Alimento", "Bebida", "Brinquedos", "Material Escolar", "Vestuario", "Outros"
    });
    
    private JButton btnAdd = new JButton("Adicionar", new ImageIcon("src/images/add_don.png"));
    private JButton btnEditar = new JButton("Editar", new ImageIcon("src/images/edit_row.png"));
    private JButton btnRemover = new JButton("Remover", new ImageIcon("src/images/delete_don.png"));
    private JButton btnEnviar = new JButton("Confirmar", new ImageIcon("src/images/don.png")); 
    
    private JTextField jtfMoeda = new JTextField();
    
    private JLabel lblfundo = new JLabel(new ImageIcon("src/images/bgDoador.jpg"));
    private JLabel lblDonateImg = new JLabel(new ImageIcon("src/images/donate.png"));
    private JLabel lblNomeSys = new JLabel("Sys Donation");
    private JLabel lblNome = new JLabel("Nome do doador");
    private JLabel lblTipoDoc = new JLabel("Tipo de documento");
    private JLabel lblNDocumento = new JLabel("Nº. do documento");
    private JLabel lblId = new JLabel("Código do doador: 0");
    private JLabel lblTelefone = new JLabel("Telefone");
    private JLabel lblID = new JLabel();
    private JLabel lblClose = new JLabel(new ImageIcon("src/images/closeIcon2.png"));
    private JLabel lblClose2 = new JLabel(new ImageIcon("src/images/close.png"));
    private JLabel lblNomeProd = new JLabel("Nome do produto");
    private JLabel lblCategoria = new JLabel("Categoria");
    private JLabel lblQtd = new JLabel("Quantidade");
    private JLabel lblMoney = new JLabel("Valor a doar");
    private JLabel lblGenero = new JLabel("Género");
    private JLabel bgd = new JLabel();
    private JLabel lblMZN = new JLabel("MZN");
    
    private JPanel jpTopBar = new JPanel();
    private JPanel jpDadosDoador = new JPanel();
    private JPanel jpDonativo = new JPanel();
    private JPanel jpText = new JPanel();
    
    private JScrollPane scroll = new JScrollPane();
    
    private JTable tabela;
    
    private JTextField jtfNome = new JTextField();
    private JTextField jtfNDocumento = new JTextField();
    private JTextField jtfTelefone = new JTextField();
    private JTextField jtfNomeProd = new JTextField();
    private JTextField jtfQtd = new JTextField();
    
    public ViewRegistarDoador() {
        setSize(950,700);        
        setResizable(false); 
        setLocation(330, 10);
        setUndecorated(!true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
    }
    
    public void View(){ 
        
        modelo.setColumnCount(0);
        new Propriedades().addColumn(modelo, "Id","Nome do produto", "Categoria", "Quantidade", "Valor doado");
 
        tabela = new JTable(modelo){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return EDIT[columnIndex];
            }
        };
        scroll.setViewportView(tabela);
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(120);
            coluna.setMaxWidth(150);
        }
       
        scroll.setBounds(50, 300, 600, 300);
        lblfundo.setBounds(0, 0, 1920, 1200);
        bgd.setBounds(0, 0, 1920, 1200); 
        jpDadosDoador.setBounds(20, 30, 910, 150);
        jpDonativo.setBounds(20, 195, 910, 450);
        jpText.setBounds(440, 220, 450, 300);
        lblId.setBounds(100-50, 50, 250, 30);
        lblNome.setBounds(100-50, 80, 100, 30);
        lblTipoDoc.setBounds(320-50, 80, 120, 30);
        lblNDocumento.setBounds(490-50, 80, 120, 30);
        lblNomeProd.setBounds(50, 225, 120, 20);
        lblCategoria.setBounds(270, 225, 120, 20);
        lblQtd.setBounds(410, 225, 225, 20);
        lblMoney.setBounds(630, 225, 225, 20);
        lblTelefone.setBounds(760, 80, 100, 30);
        lblGenero.setBounds(710-50, 80, 100, 30);
        jtfNome.setBounds(100-50, 110, 200, 30);
        jcbTipoDocumento.setBounds(320-50, 110, 150, 30);
        jtfNDocumento.setBounds(490-50, 110, 200, 30);
        jtfTelefone.setBounds(760, 110, 120, 30);
        jcbGenero.setBounds(710-50, 110, 90, 30);
        jtfNomeProd.setBounds(50, 250, 200, 30);
        jcbCategoria.setBounds(270, 250, 120, 30);
        jtfQtd.setBounds(410, 250, 200, 30);
        jtfMoeda.setBounds(630, 250, 120, 30);
        lblMZN.setBounds(750, 250, 83, 30);
        btnAdd.setBounds(660, 300, 175, 30);
        btnEditar.setBounds(660, 340, 175, 30);
        btnRemover.setBounds(660,380, 175, 30);
        btnEnviar.setBounds(660, 420, 175, 30);
        
        jpTopBar.setBackground(new Color(116, 120, 149));
        new Propriedades().setBackground(new Color(45, 82, 124), btnAdd, btnEditar, btnEnviar, btnRemover);
        
        new Propriedades().setForeground(Color.BLACK,lblId,lblNome,jtfNome, lblTipoDoc, jcbTipoDocumento, lblNDocumento, jcbGenero,
            jtfNDocumento, lblTelefone, jtfTelefone, lblNomeProd, lblCategoria, lblQtd, lblMoney, jtfNomeProd, jtfQtd, jcbCategoria, jcbMoedas, lblMZN, jtfMoeda);
        new Propriedades().setForeground(Color.white, btnAdd, btnEditar, btnEnviar, btnRemover);
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, lblId,lblNome,jtfNome, lblTipoDoc, jcbTipoDocumento, lblNDocumento, jcbGenero,
            jtfNDocumento, lblTelefone, jtfTelefone, lblNomeProd, lblCategoria, lblQtd, lblMoney, jtfNomeProd, jtfQtd, jcbCategoria, jcbMoedas, lblMZN,jtfMoeda, btnAdd, btnEditar, btnEnviar, btnRemover);
        
        new Propriedades().setLineBorder(new Color(45, 82, 124), 1, jtfNome, jtfNDocumento, jtfTelefone, jtfMoeda, jtfNomeProd, jtfQtd, jcbTipoDocumento, jcbGenero, scroll, jtfNomeProd, jtfQtd, jcbCategoria, jcbMoedas, btnAdd, btnEditar, btnRemover, btnEnviar);
        new Propriedades().setBorder(BorderFactory.createRaisedBevelBorder(), btnAdd, btnEditar, btnEnviar, btnRemover);
        jpDadosDoador.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Dados do doador", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        jpDonativo.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Donativos", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        
        new Propriedades().setFocusable(false, btnAdd, btnEditar, btnEnviar, btnRemover);
        
        new Propriedades().setVisible(false, lblClose2);
        
        new Propriedades().add(this, lblId, lblNome, lblTipoDoc, lblNDocumento, lblGenero, jcbGenero, lblTelefone, jtfNome, jcbTipoDocumento, jtfNDocumento, jtfTelefone, jpDadosDoador,
            lblNomeProd, lblCategoria, lblQtd, lblMoney, jtfNomeProd, jcbCategoria, jtfQtd, jtfMoeda, lblMZN, scroll, btnAdd, btnEditar, btnRemover, btnEnviar, jpDonativo, lblDonateImg, lblClose, lblClose2, jpTopBar, bgd);
        
        new Propriedades().addActioListener(this, btnAdd, btnEditar, btnRemover, btnEnviar);
        
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                if(tabela.getSelectedRow() >= 0){
                    ArrayList<String> don_ = new ArrayList<>();
                    for (int i = 0; i < tabela.getColumnCount(); i++) {
                        don_.add((String) modelo.getValueAt(tabela.getSelectedRow(), i));
                    }  
                    id = Integer.parseInt(don_.get(0));
                    donativo = don_;
                    selectedItem();
                }
            }
        });
        
        jtfNDocumento.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                Event.upperCase(jtfNDocumento);
                Event.id(jtfNDocumento, lblId);
            }
            
            @Override
            public void focusLost(FocusEvent fe) {
                if(!ValidarDocumentos.validarDocumento(jcbTipoDocumento, jtfNDocumento)&&!jtfNDocumento.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "O número de documento introduzido é inválido.", "Notificação", WARNING_MESSAGE);
                    jtfNDocumento.setText(null);
                    lblId.setText("Código do doador: 0");
                }
            }
        });
        
        Propriedades.isTypingNumber(jtfQtd);
        
        resetMoney();
        actionMoney();
        
        new Propriedades().moveWindow(this);
        setVisible(true);
    }
    
    private void actionMoney(){
        String text = jtfMoeda.getText();
        
        jtfMoeda.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent fe) {
            jtfMoeda.addKeyListener(new KeyAdapter(){
                @Override
                public void keyReleased(KeyEvent ke){
                    int count = 0;
                    if(jtfMoeda.isEnabled()){
                        count++;
                        if(count > 0) ke.setKeyCode(KeyEvent.VK_ENTER);
                        while(ke.getKeyCode() == KeyEvent.VK_ENTER){
                            while(!jtfMoeda.getText().equals(text)){
                                if(CampoMoney.validarMoeda(MOEDAS, jtfMoeda, jcbMoedas)){
                                    dinheiro = CampoMoney.dinheiro(MOEDAS, jtfMoeda, jcbMoedas);
                                    System.out.println(dinheiro);
                                    break;
                                }else{
                                    JOptionPane.showMessageDialog(null, "Valor introduzido é inválido.\nTente novamente.", "AVISO", WARNING_MESSAGE);
                                    break;
                                }
                            }
                            if(count >= 1) break;
                        }
                    }
                }
            });
            }
        });
    }
    
    private void resetMoney(){
        if(jtfMoeda.getText().isEmpty())
            dinheiro = "MZN 0";
    }
    
    private void clean(){
        jtfNomeProd.setText(null);
        jtfQtd.setText(null);
        jtfMoeda.setText(null);
        donativo.clear();
    }
    
    private void cleanAll(){
        lblId.setText("Código do doador: 0");
        jtfNome.setText(null);
        jtfNDocumento.setText(null);
        jtfTelefone.setText(null);
        clean();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();  
        model.setNumRows(0);
        model.setColumnCount(0);
    }
    
    private void addRow(){
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();    
        model.setNumRows(0);
        ArrayList<Donativo> don = new Donativo().findDonativo(jtfNDocumento.getText());
        don_ = don;
        if(!don.isEmpty()){
            don.forEach((d)-> model.addRow(new String[]{
                String.valueOf(d.getId()),
                d.getNomeProd(),
                d.getCategoria(),
                String.valueOf(d.getQtd()),
                d.getValorDoado()
            }));
        }
        clean();
    }

    private void deleteRow(){
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        if (tabela.getSelectedRow() != -1){
            ArrayList<Stock> stk = StockController.find(donativo.get(1));
            
            if(Stock.prodExists(donativo.get(1))){
                if(donativo.get(1).equals(stk.get(0).getNome_prod())){
                    new Stock().decrease(stk.get(0).getNome_prod(), Integer.parseInt(jtfQtd.getText()));
                }
            }else System.out.println("Produto nao exite");
            
            if(Financa.currencyExists("MZN")){
                new Financa().reduce(Double.parseDouble(jtfMoeda.getText().replace(",", ".")), "MZN");
            }        
            DonativoController.delete(Integer.parseInt(donativo.get(0)));
            model.removeRow(tabela.getSelectedRow());
            addRow();
        }
    }
    
    private void editRow(){
        if (tabela.getSelectedRow() != -1){
            ArrayList<Stock> stk = StockController.find(donativo.get(1));
            String categ_;
            if(jtfNomeProd.getText().isEmpty()||jtfQtd.getText().isEmpty()){
                categ_ = "";
                jtfQtd.setText("0");
            }else categ_ = (String) jcbCategoria.getSelectedItem();

            if(Stock.prodExists(donativo.get(1))){
                if(donativo.get(1).equals(stk.get(0).getNome_prod())){
                    stk.get(0).setNome_prod(jtfNomeProd.getText());
                    stk.get(0).setCategoria(categ_);
                    stk.get(0).setQtdActual(stk.get(0).getQtdActual() + Integer.parseInt(jtfQtd.getText()));
                    StockController.update(stk.get(0).getId(), stk.get(0));
                }
            }

            if(Financa.currencyExists("MZN")){
                double valor;
                if(Double.parseDouble(jtfMoeda.getText().replace(",", ".")) > Double.parseDouble(donativo.get(4).
                    substring(4).replace(",", "."))){
                    valor = Double.parseDouble(jtfMoeda.getText().replace(",", ".")) - Double.parseDouble(donativo.get(4).
                    substring(4).replace(",", "."));
                }else{
                    valor = Double.parseDouble(donativo.get(4).substring(4).replace(",", ".")) - Double.parseDouble(jtfMoeda.
                        getText().replace(",", "."));
                }
                
                new Financa().increaseValorActual(valor, "MZN");
            }         
            DonativoController.update(id, new Donativo(id, jtfNomeProd.getText(), categ_, 
                Integer.parseInt(jtfQtd.getText()), dinheiro, data, jtfNDocumento.getText()));
            addRow();
        }
    }
    
    private void selectedItem(){
        jtfNomeProd.setText(donativo.get(1));
        jcbCategoria.setSelectedItem(donativo.get(2));
        jtfQtd.setText(donativo.get(3));
        jtfMoeda.setText(new Propriedades().valorDoado(donativo.get(4).replace(",", ".")));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnAdd){
            if(!jtfNome.getText().isEmpty()&&!jtfNDocumento.getText().isEmpty()&&!jtfTelefone.getText().isEmpty()) {
                if(jtfNomeProd.getText().isEmpty()||jtfQtd.getText().isEmpty()){
                    categ = "";
                    jtfQtd.setText("0");
                }else categ = (String) jcbCategoria.getSelectedItem();
                 
                DonativoController.create(new Donativo(DonativoController.id(), jtfNomeProd.getText(), categ, Integer.parseInt(
                    jtfQtd.getText()), dinheiro, data, jtfNDocumento.getText()));
                if(!jtfNomeProd.getText().isEmpty()&&!jtfQtd.getText().isEmpty()){
                    if (StockController.lista().isEmpty() || !Stock.prodExists(jtfNomeProd.getText())) {
                        StockController.create(new Stock(StockController.id(), jtfNomeProd.getText(), categ, Integer.parseInt(
                            jtfQtd.getText()), 0));
                    } else {
                        new Stock().increaseQtd(new Stock(StockController.id(), jtfNomeProd.getText(), categ, Integer.parseInt(
                            jtfQtd.getText()), 0));
                    }
                }
                if (FinancaController.lista().isEmpty() || !Financa.currencyExists(dinheiro.substring(0, 3))) {
                    FinancaController.create(new Financa(FinancaController.id(), dinheiro, "MZN 0"));
                } else {
                    String[] cash = dinheiro.split(" ");
                    double valor = Double.parseDouble(cash[1].replace(".", "").replace(",", "."));
                    new Financa().increaseValorActual(valor, cash[0]);
                }
                addRow();
            }else JOptionPane.showMessageDialog(this, "O doador deve efectuar alguma doação para ser registado.", "Notificação", INFORMATION_MESSAGE);
        }else if(ae.getSource() == btnEditar){
            editRow();
        }else if(ae.getSource() == btnRemover){
            deleteRow();
        }else if(ae.getSource() == btnEnviar){
            if(!jtfNome.getText().isEmpty()&&!jtfNDocumento.getText().isEmpty()&&!jtfTelefone.getText().isEmpty()){
                if(tabela.getRowCount() > 0){
                    String[] nome = jtfNome.getText().split(" ");
                    new DoadorController().create(new DoadorDAO(ValidarDadosUser.primeirosNomes(nome), nome[nome.length - 1], (String)
                        jcbTipoDocumento.getSelectedItem(), jtfNDocumento.getText(), (String) jcbGenero.getSelectedItem(), jtfTelefone.getText(), data));
                    JOptionPane.showMessageDialog(this, "Doador registado com sucesso.");
                    
                    ArrayList<Integer> prodQtd = new ArrayList<>();
                    ArrayList<String> money = new ArrayList<>();
                    int id_ = ActividadeController.id();
                    ArrayList<Log> logged = UserLogged.lista();
                    
                    don_.forEach((d_) -> {
                        prodQtd.add(d_.getQtd());
                        money.add(d_.getValorDoado());
                    });
                    
                    Actividades act = new Actividades(id_, 1, Actividades.countProd(prodQtd), 0, 0, Actividades.countMoney(money), "MZN 0",data, logged.get(0).getId());
                    Actividades.dailyActivity(logged.get(0).getId(), act);
                    
                    cleanAll();
                    dispose();
                }else JOptionPane.showMessageDialog(this, "O doador deve efectuar alguma doação para ser registado.", "Notificação", INFORMATION_MESSAGE);
            }else JOptionPane.showMessageDialog(this, "Insira todos os dados do doador para efectuar a doação.","Notificação", INFORMATION_MESSAGE);
        }
    }
}