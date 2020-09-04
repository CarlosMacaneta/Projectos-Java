package views;

import controllers.ActividadeController;
import controllers.FuncController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.dao.Actividades;
import models.dao.Funcionario;
import models.dao.Propriedades;

/**
 *
 * @author CarlosMacaneta
 */
public class ViewFuncionario extends JPanel implements ActionListener {
    
    private final boolean[] EDIT = { false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false};
    
    private ArrayList<String> dadosFunc = new ArrayList<>();
    
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel model_ = new DefaultTableModel();
    
    private JButton btnAdd = new JButton("Adicionar", new ImageIcon("src/images/add.png"));
    private JButton btnPesquisar = new JButton(new ImageIcon("src/images/search_client.png"));
    private JButton btnEdit = new JButton("Editar", new ImageIcon("src/images/edit.png"));
    private JButton btnRemover = new JButton("Remover", new ImageIcon("src/images/delete.png"));
    private JButton btnRefresh = new JButton("Actualizar", new ImageIcon("src/images/refresh.png"));
    
    private JList lista = new JList();
    
    private JPanel jpGroup = new JPanel();
    private JPanel jpSearch = new JPanel();
    private JPanel jpPainel = new JPanel();
    private JPanel jpNorth = new JPanel();
    private JPanel jpCenter = new JPanel();
    private JPanel jpSouth = new JPanel();
    
    private JScrollPane scrollTable = new JScrollPane();
    private JScrollPane scroll = new JScrollPane();
    
    private JTable tabela;
    private JTable jTable;
    
    private JTextField jtfPesquisar = new JTextField(1);
    
    private ViewRegistarFuncionario regFunc = new ViewRegistarFuncionario();
    private EditarFuncionario editfunc = new EditarFuncionario();
    
    public ViewFuncionario(){
        this.setBackground(new Color(238, 232, 170));
        this.setLayout(new BorderLayout(400, 30));
    }

    public JPanel init(JTabbedPane tab,String title, JButton btnCloseTab){
        jpGroup.setBackground(new Color(238, 232, 170));
        new Propriedades().setBackground(new Color(45, 82, 124), btnAdd, btnEdit, btnPesquisar, btnRemover, btnRefresh, btnCloseTab);
        
        new Propriedades().searchLayout(jpGroup, jtfPesquisar,btnPesquisar, lista);
        
        jpSearch.setLayout(new GridLayout(2, 1, 20, 20));
        jpPainel.setLayout(new FlowLayout(2, 2, 2));
        jpNorth.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 20));
        jpSouth.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        
        this.setBorder(createLineBorder(new Color(238, 232, 170), 10));
        jpNorth.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 2), "Pesquisar funcionário", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        scrollTable.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 1), "Lista de funcionários", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        scroll.setBorder(BorderFactory.createTitledBorder(createLineBorder(new Color(45, 82, 124), 1), "Actividades do funcionários", 1, 0, new Font("Calibri", Font.BOLD, 16), Color.BLACK));
        new Propriedades().setLineBorder(new Color(45, 82, 124),1, jtfPesquisar, jpCenter);
        new Propriedades().setSoftBevelBorder(BevelBorder.RAISED, btnAdd, btnEdit, btnPesquisar, btnRemover, btnRefresh, btnCloseTab);
        
        new Propriedades().setOpaque(false, jpNorth, jpCenter, jpSouth, scrollTable, scroll);
        
        new Propriedades().addColumn(model, "Código", "Nome", "Apelido", "Data de Nascimento", "Tipo de documento", "Nº. do "
            + "documento", "Nuit","Género", "Estado Civil", "Nacionalidade", "Província", "Bairro", "Telefone", "Email");
        new Propriedades().addColumn(model_, "Id", "Nº total de doadores reg.", "Qtd. prod. registado", "Valor total registado", "Nº.de orfanatos reg.",
            "Qtd. prod. doados para orf.", "Valor total doado para orf.", "Data");
        
        tabela = new JTable(model){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return EDIT[columnIndex];
            }
        };
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(150);
            coluna.setMaxWidth(500);
        }
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollTable.setViewportView(tabela);
        
        jTable = new JTable(model_){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false;
            }
        };

        for(int i = 0; i < jTable.getColumnCount(); i++){
            TableColumn coluna = jTable.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(148);
            coluna.setMaxWidth(500);
        }
        jTable.setAutoResizeMode(AUTO_RESIZE_OFF);
        scroll.setViewportView(jTable);
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, jtfPesquisar, tabela, btnAdd, btnEdit, btnPesquisar, btnRemover, btnCloseTab);
        
        new Propriedades().setForeground(Color.BLACK, jtfPesquisar, tabela);
        new Propriedades().setForeground(Color.WHITE, btnAdd, btnEdit, btnPesquisar, btnRemover,btnRefresh, btnCloseTab);
        
        lista.setVisible(false);

        new Propriedades().setPreferredSize(new Dimension(120, 30),btnRefresh, btnAdd, btnEdit, btnRemover, btnCloseTab);
        scrollTable.setPreferredSize(new Dimension(600, 370)); 
        scroll.setPreferredSize(new Dimension(450, 370)); 
        
        addRow();
        
        new Propriedades().addContainer(jpNorth, jpGroup);
        new Propriedades().addContainer(jpCenter, scrollTable, scroll, jpSouth);
        new Propriedades().addContainer(jpSouth, btnRefresh, btnAdd, btnEdit, btnRemover, btnCloseTab);
        
        btnCloseTab.addActionListener((ActionEvent ae) ->{
            if(ae.getSource() == btnCloseTab){
                tab.remove(tab.indexOfTab(title));
            }
        });
        
        new Propriedades().addActioListener(this, btnPesquisar, btnRefresh, btnRemover, btnAdd, btnEdit);
        
        this.add(jpNorth, BorderLayout.NORTH);
        this.add(jpCenter, BorderLayout.CENTER);
        
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                if(tabela.getSelectedRow() >= 0){
                    ArrayList<String> dadosFunc_ = new ArrayList<>();
                    for(int i = 0; i < tabela.getColumnCount(); i++){
                        dadosFunc_.add((String) model.getValueAt(tabela.getSelectedRow(), i));
                    }
                    addRow(Integer.parseInt(dadosFunc_.get(0)));
                    dadosFunc = dadosFunc_;
                }
            }
        });
        
        jtfPesquisar.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke){
                if(ke.getKeyChar() != KeyEvent.VK_BACK_SPACE){
                    addSearch(jtfPesquisar.getText());
                }
            }
        });
        
        return this;
    }
    
    private void addRow(){
        DefaultTableModel modelo_ = (DefaultTableModel) tabela.getModel();
        modelo_.setNumRows(0);
        
        ArrayList<Funcionario> dados_ = new FuncController().lista();
        if(!dados_.isEmpty()){     
            dados_.forEach((dado) -> modelo_.addRow(new String[]{
                String.valueOf(dado.getId()),
                dado.getNome(),
                dado.getApelido(),
                dado.getDataNascimento(),
                dado.getTipoDocumento(),
                dado.getnDocumento(),
                String.valueOf(dado.getNuit()),
                dado.getGenero(),
                dado.getEstadoCivil(),
                dado.getNacionalidade(),
                dado.getProvincia(),
                dado.getBairro(),
                dado.getTelefone(),
                dado.getEmail()
            }));
        }else{
            JOptionPane.showMessageDialog(this, "Nenhum funcionário foi registado.");
        }
    }
    
    private void addRow(int fk){
        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
        dtm.setNumRows(0);
        ArrayList<Actividades> act = ActividadeController.lista(fk);
        
        if(!act.isEmpty()){
            act.forEach((a)-> dtm.addRow(new String[]{
                String.valueOf(a.getId()),
                String.valueOf(a.getDoadoreReg()),
                String.valueOf(a.getProd()),
                String.valueOf(a.getBen()),
                String.valueOf(a.getQtfBen()),
                String.valueOf(a.getValor()),
                String.valueOf(a.getValorBen()),
                String.valueOf(a.getData())
            }));
        }else JOptionPane.showMessageDialog(this, "Este funcionário ainda não exerceu suas actividades diárias.");
    }
    
    private void addSearch(String str){
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        
        ArrayList<Funcionario> dados_ = new FuncController().search(str);
        if(!dados_.isEmpty()){     
            dados_.forEach((dado) -> modelo.addRow(new String[]{
                String.valueOf(dado.getId()),
                dado.getNome(),
                dado.getApelido(),
                dado.getDataNascimento(),
                dado.getTipoDocumento(),
                dado.getnDocumento(),
                String.valueOf(dado.getNuit()),
                dado.getGenero(),
                dado.getEstadoCivil(),
                dado.getNacionalidade(),
                dado.getProvincia(),
                dado.getBairro(),
                dado.getTelefone(),
                dado.getEmail()
            }));
        }else JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnRefresh){
            addRow();
            DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
            dtm.setNumRows(0);
        }else if(ae.getSource() == btnPesquisar){
            addSearch(jtfPesquisar.getText());
        }else if(ae.getSource() == btnAdd){ 
            regFunc.setVisible(true);
        }else if(ae.getSource() == btnEdit){
            System.out.println("Yes");
            editfunc.setVisible(true);
        }else if(ae.getSource() == btnRemover){
            if(tabela.getSelectedRow() != -1){
                if(!dadosFunc.isEmpty()){
                    Object[] options = {"Sim","Não"};
                    int yesNo = JOptionPane.showOptionDialog(null, "Tem certeza de que deseja remover "+dadosFunc.get(1)+" "+
                        dadosFunc.get(2)+" ?",null, YES_NO_OPTION, QUESTION_MESSAGE, null,options, options[0]);
                    if(yesNo == 0){
                        if(new FuncController().delete(Integer.parseInt(dadosFunc.get(0)))){
                            dadosFunc.clear(); addRow();
                            JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso.");
                        }else JOptionPane.showMessageDialog(null, "Ocorreu um erro ao remover funcionário.");
                    }else JOptionPane.showMessageDialog(null, "Acção cancelada.");
                }else JOptionPane.showMessageDialog(null, "Selecione um funcionário para remover.");
            }else JOptionPane.showMessageDialog(null, "Selecione um funcionário para remover.");
        }
    } 
}