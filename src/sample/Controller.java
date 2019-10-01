package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * Database. 9/28/2019
 *
 * @author Alan Norman
 */
public class Controller {

  /**
   * labels quantity comboBox.
   */
  @FXML
  private Label lblChooseQuantity;

  /**
   * Labels the ListView that displays all the products selected.
   */
  @FXML
  private Label lblChooseProduct;

  /**
   * The individual Product Line Tab in the GUI.
   */
  @FXML
  private Tab tabProductLine;

  /**
   * The individual Produce Tab in the GUI.
   */
  @FXML
  private Tab tabProduce;

  /**
   * The individual Production Log Tab in the GUI.
   */
  @FXML
  private Tab tabProductionLog;

  /**
   * Button that initiates producing a list of records based on the quantity selected.
   */
  @FXML
  private Button btnRecordProduction;

  /**
   * Where the user enters the name of the product being entered.
   */
  @FXML
  private TextField txtfProductName;

  /**
   * Where the user enters the manufacturer of the product being added.
   */
  @FXML
  private TextField txtfManufacturer;

  /**
   * ChoiceBox for the specific item type being added.
   */
  @FXML
  private ChoiceBox<String> cbItemType;

  /**
   * The comboBox for the quantity of products, contains values 1-10.
   */
  @FXML
  private ComboBox<String> cmBoxQuantity;

  /**
   * Global variables for the database connection and statement.
   */
  private Connection conn = null;
  private Statement stmt = null;

  /**
   * initiates adding a product to the database.
   */
  @FXML
  private Button btnAddProduct;

  /**
   * Method that runs on program startup.
   */
  public void initialize() {

    //calls initializeDB method
    initializeDB();

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

    /*
     Title: DataBase Week 5
     Author: Scott Vanselow
     Date: 2019
     Code version: 1.0
     Availability: https://sites.google.com/site/profvanselow/course/cop-3003/4-Databases?authuser=0
 */

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

    try {
      //uses the connection from initializeDB to create a statement

      stmt = conn.createStatement();
      /*
     Title: OOP Project
     Author: Scott Vanselow
     Date: 2019
     Code version: 1.0
     Availability: https://sites.google.com/site/profvanselow/course/cop-3003/oop-project?authuser=0
 */

      //SQL statement used to insert textfield values into database
      String sql = "INSERT INTO Product(type, manufacturer, name) "
          + "VALUES ( '" + txtfProductName.getText() + "', '" + txtfManufacturer.getText() + "', '"
          + cbItemType.getValue() + "')";

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


