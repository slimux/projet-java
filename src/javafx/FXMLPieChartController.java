/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import Class.Game;
import Connection.DataSource;
import IService.IGameService;
import Service.GameService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author slimu
 */
public class FXMLPieChartController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private PieChart myPieChart;
          //Pie chart Data
    private ObservableList<PieChart.Data> data;
    IGameService service=new GameService();
         List<Game> g=service.listerGame();
    
  
    
    

    private void buildPieChartData() {
        try {
             Connection c ;
          DataSource ds = DataSource.getInstance();
          data = FXCollections.observableArrayList();
           c = ds.getConnection();
           String SQL = "SELECT COUNT(*),genre from jeu group by genre ";
              ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
               data.add(new PieChart.Data(rs.getString(2),rs.getDouble(1)));
                
            }
            myPieChart.setTitle("Jeu par genre");
            myPieChart.setData(data);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void viewChart(ActionEvent event) {
        buildPieChartData();
        createPdf();
    }


 
    public void createPdf()
        {
            try {
                
                List<Game> list=service.listerGame();

                try (OutputStream file = new FileOutputStream(new File("Test.pdf"))) {
                    Document document = new Document();
                    
                    PdfWriter.getInstance(document, file);
                    
                    
                    document.open();
                   
                    PdfPTable my_report_table = new PdfPTable(2);
                    
                    PdfPCell table_cell;
                    
                    int j=1;
                   for (int i = 0; i < list.size(); i++)
                   {                   
                     
                    if(j==5){j=1;}
                    if(j==1||j==2)
                    {
                 
                      Image img = Image.getInstance("file:///C:/Users/slimu/Desktop/images/"+list.get(i).getImage());
                //   img.setWidthPercentage(10);
                     table_cell=new PdfPCell(img);
                    table_cell.setBorder(Rectangle.NO_BORDER);
                    my_report_table.addCell(table_cell);
                    j++;}
                    if(j==3||j==4)
                    {
                        int dec=i;
                        if(j==3)
                        {
                            dec=i-1;
                        
                        }
                        Paragraph p= new Paragraph(list.get(dec).getTitre()+"  "+list.get(dec).getPrix()+" DT");
                        table_cell=new PdfPCell(p);
                        table_cell.setBorder(Rectangle.NO_BORDER);
                        my_report_table.addCell(table_cell);
                         
                        j++;
                        i--;
                    }           
                                      
                   }
                    document.add(my_report_table);
                    document.close();
                }


        } catch (Exception e) {


            e.printStackTrace();

        }
        }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildPieChartData();
    }    
    
}
