package models.dao;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controllers.DoadorController;
import controllers.OrfanatoController;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author CarlosMacaneta
 */
public class RelatorioPdf implements Serializable {
    
    private static Calendar c = Calendar.getInstance();
    private static String data = String.format("%d-%02d-%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DATE));
    private static int qtd;
    private static int nDoacoes;
    private static int qtdTotal;
    private static String dinheiro;
    private static Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD,new BaseColor(0,0,0));
    private static Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, new BaseColor(0,0,0));
    private static Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0,0,0));
    
    private static void row(PdfPTable tbl, String text){
        
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font2));
        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        
        tbl.addCell(cell);
    }
    
    private static void totalcell(PdfPTable tbl, String text, String str){
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font3));
        PdfPCell cell2 = new PdfPCell(new Phrase(str.trim(), font3));
        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        
        cell.setColspan(3);
        
        tbl.addCell(cell);
        tbl.addCell(cell2);
    }
    
    private static String uri(String path){
        if(path.contains(".")){
            String[] nomeFich = path.replace(".", "/").split("/");
            path = nomeFich[0];
        }
        return path;
    }
    
    private static void pdfInfo(Document doc, String str){
        doc.addAuthor("Sys Donation");
        doc.addCreationDate();
        doc.addProducer();
        doc.addCreator("Sys Donation");
        doc.addTitle(str);
    }
    
    public static void pfdReport(Document doc, String relatorio, String path){
        
        if(null != relatorio)switch(relatorio){
            case "Benefiários":
                try{
                    System.out.println(path);
                    PdfWriter.getInstance(doc, new FileOutputStream(uri(path)+".pdf"));

                    pdfInfo(doc, relatorio);
                    doc.setPageSize(PageSize.A4.rotate());
                    doc.open();
                    
                    Image img = Image.getInstance("src/images/donation-icon.png");
                    img.setAlignment(Image.ALIGN_CENTER);
                    doc.add(img);
                    doc.add(new Paragraph("Relatório dos beneficiários(orfanatos)", font1));
                    doc.add(new Paragraph("Data de emissão do relatório: "+data, font2));
                    
                    float[] columnWidth = {30f, 100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f};
                    
                    PdfPTable tbl = new PdfPTable(9);
                    
                    tbl.setSpacingBefore(20f);
                    tbl.setSpacingAfter(20f);
                    tbl.setTotalWidth(columnWidth);
                    tbl.setLockedWidth(true);
                    
                    ArrayList<Orfanato> orf = new OrfanatoController().lista();
                    
                    Propriedades.addCell(tbl, "Id", "Nome do orfanato", "Nº de crianças", "Nº de jovens", "Nº de adultos",
                        "Total de acolhidos", "Data de registo", "Qtd. Produtos recebidos","Total de valor recebido");
                    qtdTotal = 0;
                    orf.forEach((o)->{
                        qtd = new DoacaoOrfanato().qtdTotalProd_(o.getId());
                        dinheiro = new DoacaoOrfanato().valorTotal(o.getId());
                        qtdTotal += qtd; 
                        row(tbl,(String.valueOf(o.getId())));
                        row(tbl,(o.getNome()));
                        row(tbl,(String.valueOf(o.getnCriancas())));
                        row(tbl,(String.valueOf(o.getnJovens())));
                        row(tbl,(String.valueOf(o.getnAdultos())));
                        row(tbl,(String.valueOf(o.getnPessoas())));
                        row(tbl,(o.getDataReg()));
                        row(tbl, (String.valueOf(qtd)));
                        row(tbl,(dinheiro));
                    });        
                    
                    doc.add(tbl);
                    doc.add(new Paragraph("Número total de beneficiários: "+orf.size(), font2));
                    doc.add(new Paragraph("Quantidade total de produtos.: "+qtdTotal, font2));
                    doc.add(new Paragraph("Valor total..............................: "+new DoacaoOrfanato().valorTotal(), font2));
                   
                    if(Files.exists(Paths.get("src/images/graficos/relatorio-beneficiarios.png"))){
                        doc.add(new Paragraph("\n\nRepresentação gráfica", font1));
                        doc.add(new Paragraph("\n\nRepresentação gráfica das quantidades doadas ao benefiários(orfanatos).", font2));
                        Image imgGraph = Image.getInstance("src/images/graficos/relatorio-beneficiarios.png");
                        imgGraph.setAlignment(Image.ALIGN_CENTER);
                        doc.add(imgGraph);
                    }
                }catch(FileNotFoundException | DocumentException  ex){
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar pdf.\nERRO: "+ex, "ERRO", ERROR_MESSAGE);
                } catch (IOException ex) {
                }finally{
                    doc.close();
                }
                break;
            case "Doadores":
                try{
                    PdfWriter.getInstance(doc, new FileOutputStream(uri(path)+".pdf"));

                    pdfInfo(doc, relatorio);
                    doc.setPageSize(PageSize.A4.rotate());
                    doc.open();
                    
                    Image img = Image.getInstance("src/images/donation-icon.png");
                    img.setAlignment(Image.ALIGN_CENTER);
                    doc.add(img);
                    doc.add(new Paragraph("Relatório dos doadores", font1));
                    doc.add(new Paragraph("Data de emissão do relatório: "+data, font2));
                    
                    float[] columnWidth = {100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f};
                    
                    PdfPTable tbl = new PdfPTable(8);
                    
                    tbl.setSpacingBefore(20f);
                    tbl.setSpacingAfter(20f);
                    tbl.setTotalWidth(columnWidth);
                    tbl.setLockedWidth(true);
                    
                    ArrayList<DoadorDAO> donater = new DoadorController().lista();
                    ArrayList<Donativo> don = new Donativo().findDonater();
                    
                    Propriedades.addCell(tbl, "Nome Completo", "Nº de documento","Género","Telefone",
                        "Qtd. Prod. doado", "Valor total doado", "Nº. de doações","Data de registo");
                    nDoacoes = 0;
                    qtdTotal = 0;
                    donater.forEach((dd)->{
                        row(tbl, dd.getNome()+" "+dd.getApelido());
                        row(tbl, dd.getnDocumento());
                        row(tbl, dd.getGenero());
                        row(tbl, dd.getTelefone());
                        qtd = new Donativo().qtdTotalProd(dd.getnDocumento());
                        qtdTotal += qtd;
                        row(tbl, String.valueOf(qtd));
                        dinheiro = new Donativo().valorTotal(dd.getnDocumento());
                        row(tbl, dinheiro);
                        nDoacoes = new Donativo().numDoacoes(dd.getnDocumento());
                        row(tbl, String.valueOf(nDoacoes));
                        row(tbl, dd.getData());
                    });        
                    
                    doc.add(tbl);
                    doc.add(new Paragraph("Número total de doadores.................: "+donater.size(), font2));
                    doc.add(new Paragraph("Número total de doações...................: "+don.size(), font2));
                    doc.add(new Paragraph("Quantidade total de produtos doados: "+qtdTotal, font2));
                    doc.add(new Paragraph("Valor total..........................................: "+new Donativo().valorTotal(), font2));
                    
                    if(Files.exists(Paths.get("src/images/graficos/relatorio-doador1.png"))&&
                        Files.exists(Paths.get("src/images/graficos/relatorio-doador2.png"))){
                        doc.add(new Paragraph("\n\nRepresentação gráfica", font1));
                        doc.add(new Paragraph("\n\nRepresentação gráfica do número de doaçõe feitas pelo doador.", font2));
                        Image imgGraph = Image.getInstance("src/images/graficos/relatorio-doador1.png");
                        imgGraph.setAlignment(Image.ALIGN_CENTER);
                        doc.add(imgGraph);
                        doc.add(new Paragraph("Representação gráfica da quantidade dos produtos/donativos feitos pelo doador.", font2));
                        Image imgGraph_ = Image.getInstance("src/images/graficos/relatorio-doador2.png");
                        imgGraph_.setAlignment(Image.ALIGN_CENTER);
                        doc.add(imgGraph_);
                    }
                }catch(FileNotFoundException | DocumentException ex){
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar pdf.\nERRO: "+ex, "ERRO", ERROR_MESSAGE);
                } catch (IOException ex) {
                }finally{
                    doc.close();
                }
                break;
            case "Donativos":
                try{
                    PdfWriter.getInstance(doc, new FileOutputStream(uri(path)+".pdf"));

                    pdfInfo(doc, relatorio);
                    doc.setPageSize(PageSize.A4.rotate());
                    doc.open();
                    
                    Image img = Image.getInstance("src/images/donation-icon.png");
                    img.setAlignment(Image.ALIGN_CENTER);
                    doc.add(img);
                    doc.add(new Paragraph("Relatório dos donativos", font1));
                    doc.add(new Paragraph("Data de emissão do relatório: "+data, font2));
                    
                    float[] columnWidth = {30f, 100f, 100f, 100f, 100f, 100f, 100f};
                    
                    PdfPTable tbl = new PdfPTable(7);
                    
                    tbl.setSpacingBefore(20f);
                    tbl.setSpacingAfter(20f);
                    tbl.setTotalWidth(columnWidth);
                    tbl.setLockedWidth(true);
                    
                    ArrayList<Donativo> don = new Donativo().findDonater();
                    ArrayList<DoadorDAO> donater = new DoadorController().lista();
                    
                    Propriedades.addCell(tbl, "Id", "Nome do produto", "Categoria", "Quantidade","Valor doado",
                        "Data de registo", "Nome do doador");
                    qtdTotal = 0;
                    don.forEach((d)->{
                        qtdTotal += d.getQtd();
                        row(tbl, String.valueOf(d.getId()));
                        row(tbl, d.getNomeProd());
                        row(tbl, d.getCategoria());
                        row(tbl,(String.valueOf(d.getQtd())));
                        row(tbl, d.getValorDoado());
                        row(tbl,d.getData());
                        row(tbl,d.getNome()+" "+d.getApelido());
                    });       
                    
                    doc.add(tbl);
                    
                    doc.add(new Paragraph("Número total de doadores..................: "+donater.size(), font2));
                    doc.add(new Paragraph("Número total de donativos.................: "+don.size(), font2));
                    doc.add(new Paragraph("Quantidade total de produtos doados: "+qtdTotal, font2));
                    doc.add(new Paragraph("Valor total.........................................: "+new Donativo().valorTotal(), font2));
                    
                    if(Files.exists(Paths.get("src/images/graficos/relatorio-donativo1.png"))&&
                        Files.exists(Paths.get("src/images/graficos/relatorio-donativo2.png"))){
                        doc.add(new Paragraph("\n\nRepresentação gráfica", font1));
                        doc.add(new Paragraph("\n\nRepresentação gráfica das categorias dos donativos/produtos, indincando sua"
                            + " quantidade baseando-se na data de registo dos donativos/produtos.", font2));
                        Image imgGraph = Image.getInstance("src/images/graficos/relatorio-donativo1.png");
                        imgGraph.setAlignment(Image.ALIGN_CENTER);
                        doc.add(imgGraph);
                        doc.add(new Paragraph("Representação gráfica dos produtos/donativos agrupado em categoria, indicando sua quantida"
                            + "de  e data de registo dos mesmos.", font2));
                        Image imgGraph_ = Image.getInstance("src/images/graficos/relatorio-donativo2.png");
                        imgGraph_.setAlignment(Image.ALIGN_CENTER);
                        doc.add(imgGraph_);
                    }
                }catch(FileNotFoundException | DocumentException ex){
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar pdf.\nERRO: "+ex, "ERRO", ERROR_MESSAGE);
                } catch (IOException ex) {
                }finally{
                    doc.close();
                }
                break;
            default: 
                break;
        }
    }
}