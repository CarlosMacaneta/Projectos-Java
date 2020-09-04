package views;

import controllers.DoadorController;
import controllers.OrfanatoController;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.dao.DoacaoOrfanato;
import models.dao.DoadorDAO;
import models.dao.Donativo;
import models.dao.Orfanato;
import models.dao.Propriedades;

/**
 *
 * @author CarlosMacaneta
 */
public class Relatorios {
    
    private static DefaultTableModel modelo = new DefaultTableModel();
    
    private static int qtd;
    private static String dinheiro;
    
    private static JTable tabela = new JTable(){
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex){
            return false;
        }
    };
    
    private static JTable jTable = new JTable(){
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex){
            return false;
        }
    };
    
    public static JScrollPane relatorioBen(){
        JScrollPane scroll = new JScrollPane();
        
        modelo.setColumnCount(0);
        new Propriedades().addColumn(modelo,"Id", "Nome do orfanato", "Nº de crianças",
            "Nº de jovens", "Nº de adultos", "Total de acolhidos", "Data de registo", "Qtd. Produtos recebidos",
            "Total de valor recebido");
        
        tabela.setModel(modelo);
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(175);
            coluna.setMaxWidth(500);
        }
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        
        scroll.setViewportView(tabela);
        
        scroll.setPreferredSize(new Dimension(700, 370));
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList<Orfanato> orf = OrfanatoController.lista();
        
        if(!orf.isEmpty()){
            orf.forEach((o)->{
                qtd = new DoacaoOrfanato().qtdTotalProd_(o.getId());
                dinheiro = new DoacaoOrfanato().valorTotal(o.getId());
                model.addRow(new String[]{
                    String.valueOf(o.getId()),
                    o.getNome(),
                    String.valueOf(o.getnCriancas()),
                    String.valueOf(o.getnJovens()),
                    String.valueOf(o.getnAdultos()),
                    String.valueOf(o.getnPessoas()),
                    o.getDataReg(),
                    String.valueOf(qtd),
                    dinheiro
                });
            });
        }else JOptionPane.showMessageDialog(null, "Relatório não disponível.");
        return scroll;
    }
    
    public static JScrollPane filtroBen(String str1, String str2){
        JScrollPane scroll = new JScrollPane();
        
        modelo.setColumnCount(0);
        new Propriedades().addColumn(modelo,"Id", "Nome do orfanato", "Nº de crianças",
            "Nº de jovens", "Nº de adultos", "Total de acolhidos", "Data de registo", "Qtd. Produtos recebidos",
            "Total de valor recebido");
        
        tabela.setModel(modelo);
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(175);
            coluna.setMaxWidth(500);
        }
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        
        scroll.setViewportView(tabela);
        
        scroll.setPreferredSize(new Dimension(700, 370));
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList<Orfanato> orf = new Orfanato().pesqData(str1, str2);
        
        if(!orf.isEmpty()){
            orf.forEach((o)->{
                qtd = new DoacaoOrfanato().qtdTotalProd_(o.getId());
                dinheiro = new DoacaoOrfanato().valorTotal(o.getId());
                model.addRow(new String[]{
                    String.valueOf(o.getId()),
                    o.getNome(),
                    String.valueOf(o.getnCriancas()),
                    String.valueOf(o.getnJovens()),
                    String.valueOf(o.getnAdultos()),
                    String.valueOf(o.getnPessoas()),
                    o.getDataReg(),
                    String.valueOf(qtd),
                    dinheiro
                });
            });
        }else JOptionPane.showMessageDialog(null, "Relatório não disponível.");
        return scroll;
    }
    
    public static JTextArea infoBen(){
        JTextArea info = new JTextArea();
        info.setOpaque(false);
        info.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        info.setPreferredSize(new Dimension(350, 370));
        info.setEditable(false);
        
        ArrayList<Orfanato> orf = OrfanatoController.lista();
        
        info.setText("Número total de beneficiários: "+orf.size()+"\n" 
            +"Quantidade total de produtos.: "+new DoacaoOrfanato().qtdTotalProd()+"\n"
            +"Valor total............................: "+new DoacaoOrfanato().valorTotal());
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, info);
        
        return info;
    }
    
    public static JTextArea infoFiltroBen(String str1, String str2){
        JTextArea info = new JTextArea();
        info.setOpaque(false);
        info.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        info.setPreferredSize(new Dimension(350, 370));
        info.setEditable(false);
        
        ArrayList<Orfanato> orf = new Orfanato().pesqData(str1, str2);
        
        info.setText("Número total de beneficiários: "+orf.size()+"\n" 
            +"Quantidade total de produtos.: "+new DoacaoOrfanato().qtdTotalProd(orf)+"\n"
            +"Valor total............................: "+new DoacaoOrfanato().valorTotal(orf));
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, info);
        
        return info;
    }
    
    public static JScrollPane relatorioDoador(){
        JScrollPane scroll = new JScrollPane();
        
        modelo.setColumnCount(0);
        new Propriedades().addColumn(modelo, "Nome Completo", "Tipo de documento", "Nº de documento",
            "Género","Telefone", "Qtd. Prod. doado", "Valor total doado", "Data de registo");
        
        tabela.setModel(modelo);
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(175);
            coluna.setMaxWidth(500);
        }
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        
        scroll.setViewportView(tabela);
        
        scroll.setPreferredSize(new Dimension(700, 370));
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList<DoadorDAO> donater = new DoadorController().lista();
        
        if(!donater.isEmpty()){
            donater.forEach((dd)->{
                qtd = new Donativo().qtdTotalProd(dd.getnDocumento());
                dinheiro = new Donativo().valorTotal(dd.getnDocumento());
                model.addRow(new String[]{
                    dd.getNome()+" "+dd.getApelido(),
                    dd.getTipoDocumento(),
                    dd.getnDocumento(),
                    dd.getGenero(),
                    dd.getTelefone(),
                    String.valueOf(qtd),
                    dinheiro,
                    dd.getData()
                });
            });
        }else JOptionPane.showMessageDialog(null, "Relatório não disponível.");
        return scroll;
    }
    
    public static JScrollPane filtroDoador(String str1, String str2){
        JScrollPane scroll = new JScrollPane();
        
        modelo.setColumnCount(0);
        new Propriedades().addColumn(modelo, "Nome Completo", "Tipo de documento", "Nº de documento",
            "Género","Telefone", "Qtd. Prod. doado", "Valor total doado", "Data de registo");
        
        tabela.setModel(modelo);
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(175);
            coluna.setMaxWidth(500);
        }
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        
        scroll.setViewportView(tabela);
        
        scroll.setPreferredSize(new Dimension(700, 370));
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList<DoadorDAO> donater = new DoadorDAO().pesqData(str1, str2);
        
        if(!donater.isEmpty()){
            donater.forEach((dd)->{
                qtd = new Donativo().qtdTotalProd(dd.getnDocumento());
                dinheiro = new Donativo().valorTotal(dd.getnDocumento());
                model.addRow(new String[]{
                    dd.getNome()+" "+dd.getApelido(),
                    dd.getTipoDocumento(),
                    dd.getnDocumento(),
                    dd.getGenero(),
                    dd.getTelefone(),
                    String.valueOf(qtd),
                    dinheiro,
                    dd.getData()
                });
            });
        }else JOptionPane.showMessageDialog(null, "Relatório não disponível.");
        return scroll;
    }
    
    public static JTextArea infoDoador(){
        JTextArea info = new JTextArea();
        info.setOpaque(false);
        info.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        info.setPreferredSize(new Dimension(350, 370));
        info.setEditable(false);
        
        ArrayList<DoadorDAO> donater = new DoadorController().lista();
        ArrayList<Donativo> don = new Donativo().findDonater();
        
        info.setText("Número total de doadores..................: "+donater.size()+"\n" 
            +"Número total de doações....................: "+don.size()+"\n"
            +"Quantidade total de produtos doados...: "+new Donativo().qtdTotalProd()+"\n"
            +"Valor total.........................................: "+new Donativo().valorTotal());
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, info);
        
        return info;
    }
    
    public static JTextArea infoFiltroDoador(String str1, String str2){
        JTextArea info = new JTextArea();
        info.setOpaque(false);
        info.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        info.setPreferredSize(new Dimension(350, 370));
        info.setEditable(false);
        
        ArrayList<DoadorDAO> donater = new DoadorDAO().pesqData(str1, str2);
        ArrayList<Donativo> don = new Donativo().pesqData(donater);
        
        info.setText("Número total de doadores..................: "+donater.size()+"\n" 
            +"Número total de doações....................: "+don.size()+"\n"
            +"Quantidade total de produtos doados...: "+new Donativo().qtdTotalProd(donater)+"\n"
            +"Valor total.........................................: "+new Donativo().valorTotal(donater));
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, info);
        
        return info;
    }
    
    public static JScrollPane relatorioDonativo(){
        JScrollPane scroll = new JScrollPane();
        
        modelo.setColumnCount(0);
        new Propriedades().addColumn(modelo, "Id","Nome do produto", "Categoria", "Quantidade",
            "Valor Doado", "Data de registo", "Doador");
        
        tabela.setModel(modelo);
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(175);
            coluna.setMaxWidth(500);
        }
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        
        scroll.setViewportView(tabela);
        
        scroll.setPreferredSize(new Dimension(700, 370));
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList<Donativo> don = new Donativo().findDonater();
        
        if(!don.isEmpty()){
            don.forEach((d)-> model.addRow(new String[]{
                String.valueOf(d.getId()),
                d.getNomeProd(),
                d.getCategoria(),
                String.valueOf(d.getQtd()),
                d.getValorDoado(),
                d.getData(),
                d.getNome()+" "+d.getApelido()
            }));
        }else JOptionPane.showMessageDialog(null, "Relatório não disponível.");
        return scroll;
    }
    
    public static JScrollPane filtroDonativo(String str1, String str2){
        JScrollPane scroll = new JScrollPane();
        
        modelo.setColumnCount(0);
        new Propriedades().addColumn(modelo, "Id","Nome do produto", "Categoria", "Quantidade",
            "Valor Doado", "Data de registo", "Doador");
        
        tabela.setModel(modelo);
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(175);
            coluna.setMaxWidth(500);
        }
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        
        scroll.setViewportView(tabela);
        
        scroll.setPreferredSize(new Dimension(700, 370));
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList<Donativo> don = new Donativo().pesqData(str1, str2);
        
        if(!don.isEmpty()){
            don.forEach((d)-> model.addRow(new String[]{
                String.valueOf(d.getId()),
                d.getNomeProd(),
                d.getCategoria(),
                String.valueOf(d.getQtd()),
                d.getValorDoado(),
                d.getData(),
                d.getNome()+" "+d.getApelido()
            }));
        }else JOptionPane.showMessageDialog(null, "Relatório não disponível.");
        return scroll;
    }
    
    public static JTextArea infoDonativo(){
        JTextArea info = new JTextArea();
        info.setOpaque(false);
        info.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        info.setPreferredSize(new Dimension(350, 370));
        info.setEditable(false);
        
        ArrayList<Donativo> don = new Donativo().findDonater();
        ArrayList<DoadorDAO> donater = new DoadorController().lista();
        
        info.setText("Número total de doadores..................: "+donater.size()+"\n"
            + "Número total de donativos..................: "+don.size()+"\n"
            + "Quantidade total de produtos doados...: "+new Donativo().qtdTotalProd()+"\n"
            + "Valor total.........................................: "+new Donativo().valorTotal());
        
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, info);
        
        return info;
    }
    
    public static JTextArea infoFiltroDonativo(String str1, String str2){
        JTextArea info = new JTextArea();
        info.setOpaque(false);
        info.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        info.setPreferredSize(new Dimension(350, 370));
        info.setEditable(false);
        
        ArrayList<Donativo> don = new Donativo().pesqData(str1, str2);
        ArrayList<DoadorDAO> donater = new DoadorDAO().pesqData(don);
        
        info.setText("Número total de doadores..................: "+donater.size()+"\n"
            + "Número total de donativos..................: "+don.size()+"\n"
            + "Quantidade total de produtos doados...: "+new Donativo().qtdTotalProd(str1, str2)+"\n"
            + "Valor total.........................................: "+new Donativo().valorTotalD(don));
        new Propriedades().setFont("Calibri", Font.PLAIN, 14, info);
        
        return info;
    }
}