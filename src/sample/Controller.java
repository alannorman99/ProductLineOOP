package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javax.swing.DefaultComboBoxModel;

/**
 * A class used to execute function within the GUI, such as Button press and inserting values to a
 * Database.
 *
 * @author Alan Norman
 */
public class Controller {

  @FXML
  private Label lblChooseQuantity;

  @FXML
  private Label lblChooseProduct;

  @FXML
  private Tab tabProductLine;

  @FXML
  private Tab tabProduce;

  @FXML
  private Tab tabProductionLog;

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

  /**
   * Method that runs on program startup.
   */
  public void initialize() {

    //clears previous values in comboBox
    cmBoxQuantity.getItems().clear();
    //Loops through comboBox and adds values 1 to 10
    for (int i = 1; i < 11; i++) {
      cmBoxQuantity.getItems().add(String.valueOf(i));
    }
    //Allows the user to enter a values into the comboBox
    cmBoxQuantity.setEditable(true);
    //Shows the first values by default on the screen
    cmBoxQuantity.getSelectionModel().selectFirst();
  }

  /**
   * Creates a connection to the database.
   */
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

  /**
   * Used to confirm user input within the Produce Tab.
   *
   * @param event for when the user wants to produce a record log
   */
  @FXML
  void recordButtonAction(ActionEvent event) {

    System.out.println("Record Production");
  }

  /**
   * Button used to add a product to the database.
   *
   * @param event happens when the add product button is pressed
   */
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


