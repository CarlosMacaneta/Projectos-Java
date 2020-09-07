package views;

import controllers.ProfController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.Professor;

/**
 *
 * @author CarlosMacaneta
 */
public class ListaProf extends JFrame implements ActionListener {
    
    private ArrayList<String> prof = new ArrayList<>();
    
    private boolean[] cellEditable = {false, false, false, false}; 
    
    private DefaultTableModel model = new DefaultTableModel();
    
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnRemover = new JButton("Remover");
    private JButton btnEditar = new JButton("Editar");
    private JButton pesquisar = new JButton("Pesquisar");
    
    
    private JTable tabela;
    
    private JTextField pes = new JTextField("Nome ou id");
    
    private JScrollPane scroll;

    private JPanel centro = new JPanel();
    private JPanel pesquisa = new JPanel();
    private JPanel buttons = new JPanel();

    public ListaProf() {
        super("Lista dos professores");
        setSize(700, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(238, 232, 170)));
    }
    
    public void init(){
        Container main = this.getContentPane();
        main.setLayout(new BorderLayout(10, 50));
        
        main.setBackground(new Color(238, 232, 170));
        centro.setBackground(new Color(238, 232, 170));
        pesquisa.setBackground(new Color(238, 232, 170));
        buttons.setBackground(new Color(238, 232, 170));
        
        model.addColumn("Código do professor");
        model.addColumn("Nome do professor");
        model.addColumn("Género");
        model.addColumn("Grau académico");
        
        tabela = new JTable(model) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return cellEditable[columnIndex];
            }
        };
        
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                if(tabela.getSelectedRow() >= 0){
                    ArrayList<String> dados_ = new ArrayList<>();
                    for(int i = 0; i < tabela.getColumnCount(); i++){
                        dados_.add((String) model.getValueAt(tabela.getSelectedRow(), i));
                    }
                    prof = dados_;
                }
            }
        });
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(150);
            coluna.setMaxWidth(200);
        }
        
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        
        scroll = new JScrollPane(tabela);
        
        scroll.setPreferredSize(new Dimension(450, 300));
        pes.setPreferredSize(new Dimension(200, 30));
        pesquisar.setPreferredSize(new Dimension(120, 30));
        btnRefresh.setPreferredSize(new Dimension(120, 30));
        btnRemover.setPreferredSize(new Dimension(120, 30));
        addRow();
        
        pesquisa.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pesquisa.add(pes);
        pesquisa.add(pesquisar);
        
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        buttons.add(btnRefresh);
        buttons.add(btnRemover);
        
        pesquisa.setBorder(new TitledBorder(new LineBorder(new Color(18, 30, 49), 2), "Pesquisar", 1, 0, new Font("Calibri",Font.BOLD, 14), Color.BLACK));
       
        centro.add(scroll);
        centro.add(buttons);
        
        main.add(pesquisa, BorderLayout.NORTH);
        main.add(centro, BorderLayout.CENTER);
        
        pesquisar.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnRemover.addActionListener(this);
        
        setVisible(true);
    }
    
    private void addRow(){
        DefaultTableModel model_ = (DefaultTableModel) tabela.getModel();
        model_.setNumRows(0);
        ArrayList<Professor> prof_ = ProfController.lista();
        
        if(!prof_.isEmpty()){
            prof_.forEach((p)-> model_.addRow(new String[]{
                String.valueOf(p.getId()),
                p.getNome(),
                p.getGenero(),
                p.getGrauAcademico()
            }));
        }else JOptionPane.showMessageDialog(this, "A lista de professores encontra-se vazia.");
    }
    
    private void find(String str){
        DefaultTableModel model_ = (DefaultTableModel) tabela.getModel();
        model_.setNumRows(0);
        ArrayList<Professor> prof_ = ProfController.pesquisaNome(str);
        
        if(!prof_.isEmpty()){
            prof_.forEach((p)-> model_.addRow(new String[]{
                String.valueOf(p.getId()),
                p.getNome(),
                p.getGenero(),
                p.getGrauAcademico()
            }));
        }else JOptionPane.showMessageDialog(this, "A lista de professores encontra-se vazia.");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnRefresh){
            addRow();
            prof.clear();
        }else if(ae.getSource() == btnRemover){
            ProfController.delete(Integer.parseInt(prof.get(0)));
            prof.clear();
            addRow();
        }else if(ae.getSource() == pesquisar){
            if(!pes.getText().isEmpty()&&!pes.getText().equals("Nome ou id")){
                find(pes.getText());
            }else if(pes.getText().equals("Nome ou id") || pes.getText().isEmpty()) addRow();
            prof.clear();
        }
    }
}
