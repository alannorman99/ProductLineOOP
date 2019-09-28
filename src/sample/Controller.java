package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;


public class Controller {

  //public Tab tabProduce;
  @FXML
  private Button btnRecordProduction;

  @FXML
  private TextField txtfProductName;

  @FXML
  private TextField txtfManufacturer;

  @FXML
  private ChoiceBox<?> cbItemType;

  @FXML
  private ComboBox<String> cmBoxQuantity;


  private Connection conn = null;
  private Statement stmt = null;

  @FXML
  private Button btnAddProduct;

  public void initialize() {
    System.out.println("Initialize");
  }

  //Creates a connection to the database
  private void initializeDB() {

    //Driver and Location of Database
    final String Jdbc_Driver = "org.h2.Driver";
    final String Db_Url = "jdbc:h2:./res/ProductionDB";

    //  Database credentials
    final String User = "";
    final String Pass = "";

    try {
      // STEP 1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();

    }

  }

  //Used to confirm user input within the Produce Tab
  @FXML
  void recordButtonAction(ActionEvent event) {

    System.out.println("Record Production");
  }

  //Button used to add a product to the database
  @FXML
  void onAddProductPress(ActionEvent event) {

    //calls initializeDB method
    initializeDB();

    try {
      //uses the connection from initializeDB to create a statement
      stmt = conn.createStatement();

      //SQL statement used to access database
      String sql = "INSERT INTO Product(type, manufacturer, name) "
          + "VALUES ( 'AUDIO', 'Apple', 'iPod' )";

      //executes the previous steps
      stmt.executeUpdate(sql);

    } catch (SQLException e) {
      e.printStackTrace();
    }

    // STEP 4: Clean-up environment
    try {
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}


