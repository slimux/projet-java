
package javafx;


import Connection.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class stat extends Application {

 

    //PIE CHART DATA

    private ObservableList data;

 

    //MAIN EXECUTOR

    public static void main(String[] args) {

        Application.launch(stat.class, args);

    }

 

   //CONNECTION DATABASE SAVING DATA

    public void buildData(){

          Connection c ;
          DataSource ds = DataSource.getInstance();
          data = FXCollections.observableArrayList();

          try{

            c = ds.getConnection();

            String SQL = "SELECT COUNT(*),genre from jeu group by genre ";

            ResultSet rs = c.createStatement().executeQuery(SQL);

            while(rs.next()){

                data.add(new javafx.scene.chart.PieChart.Data(rs.getString(2),rs.getDouble(1)));

            }

          }catch(Exception e){

              System.out.println("Error on DB connection");
              return;

          }

      }

      @Override

      public void start(Stage stage) throws Exception {

        //PIE CHART

        javafx.scene.chart.PieChart pieChart = new javafx.scene.chart.PieChart();

        buildData();

        pieChart.getData().addAll(data);

 

        //Main Scene

       Scene scene = new Scene(pieChart);       

 

     stage.setScene(scene);

       stage.show();

      }

}
