package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * A class used to execute function within the GUI, such as Button press and inserting values to a
 * Database. 9/28/2019
 *
 * @author Alan Norman
 */
public class Controller {

  @FXML
  private TableView<Widget> tableViewProducts;

  @FXML
  private TableColumn<?, ?> productIdCol;

  @FXML
  private TableColumn<?, ?> productNameCol;

  @FXML
  private TableColumn<?, ?> productManuCol;

  @FXML
  private TableColumn<?, ?> productTypeCol;

  @FXML
  private ListView<Widget> produceListView;

  @FXML
  private TextArea taProductionLog;

  @FXML
  private TextArea txtAProductsDisplay;

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
  private ChoiceBox<ItemType> cbItemType;

  /**
   * The comboBox for the quantity of products, contains values 1-10.
   */
  @FXML
  private ComboBox<Integer> cmBoxQuantity;

  /**
   * Global variables for the database connection and statement.
   */
  private Connection conn = null;

  private Statement stmt = null;

  private static List<Widget> products = new ArrayList<>();

  /**
   * initiates adding a product to the database.
   */
  @FXML
  private Button btnAddProduct;

  private static ObservableList<Widget> productLine = FXCollections.observableList(products);


  /**
   * Method that runs on program startup.
   */

  public void initialize() {

    // calls fillItemType method
    fillItemType();

    // calls initializeDB method
    initializeDB();

    setupProductLineTable();

    //storeObservableList();

    storeProductTextArea();

    tableViewProducts.setVisible(true);

    for (Product widget : products) {
      String temp = widget.toString();
      taProductionLog.appendText(temp);
    }

    // clears previous values in comboBox
    cmBoxQuantity.getItems().clear();
    // Loops through comboBox and adds values 1 to 10
    for (int i = 1; i < 11; i++) {
      cmBoxQuantity.getItems().add(i);
    }
    // Allows the user to enter a values into the comboBox
    cmBoxQuantity.setEditable(true);
    // Shows the first values by default on the screen
    cmBoxQuantity.getSelectionModel().selectFirst();
  }


  /**
   * Fills the item type choice box with the ItemType enum values.
   */
  private void fillItemType() {
    cbItemType
        .getItems()
        .addAll(ItemType.Audio, ItemType.Visual, ItemType.AudioMobile, ItemType.VideoMobile);
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

    // Driver and Location of Database
    final String Jdbc_Driver = "org.h2.Driver";
    final String Db_Url = "jdbc:h2:./res/ProductionDB";

    //  Database credentials
    final String User = "";
    final String Pass = "";

    try {
      // STEP 1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      // STEP 2: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  private void storeProductTextArea() {
    try {
      //Read first names and passwords into result set
      String sql = ("SELECT * FROM PRODUCTIONRECORD");
      stmt = conn.createStatement();
      ResultSet resultSet = stmt.executeQuery(sql);

      //Loop through database and read all the values into accounts
      while (resultSet.next()) {
        int productNum = resultSet.getInt("PRODUCTION_NUM");
        int productID = resultSet.getInt("PRODUCT_ID");
        String productSerialNumber = resultSet.getString("SERIAL_NUM");
        Date date = resultSet.getDate("DATE_PRODUCED");
        ProductionRecord record = new ProductionRecord(productNum, productID, productSerialNumber,
            date);
        taProductionLog.appendText(record.toString());
      }

      System.out.println("result: " + products.toString());
      //close the statement and the result set created
      stmt.close();
      resultSet.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      if (conn == null) {
        stmt.close();
        conn.close();
      }
    } catch (SQLException e) {
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

    for (int i = 0; i <= cmBoxQuantity.getSelectionModel().getSelectedIndex(); i++) {
      Widget item = produceListView.getSelectionModel().getSelectedItem();

      taProductionLog.appendText(item.toString());
    }

  }

  /**
   * Button used to add a product to the database.
   *
   * @param event happens when the add product button is pressed
   */
  @FXML
  public void onAddProductPress(ActionEvent event) {

    try {
      // uses the connection from initializeDB to create a statement
      stmt = conn.createStatement();
      /*
          Title: OOP Project
          Author: Scott Vanselow
          Date: 2019
          Code version: 1.0
          Availability: https://sites.google.com/site/profvanselow/course/cop-3003/oop-project?authuser=0
      */

      ItemType productType = cbItemType.getValue();
      String productManufacturer = txtfManufacturer.getText();
      String productName = txtfProductName.getText();

      Widget product = new Widget(productName, productManufacturer, productType);

      //products.add(product);
      productLine.add(product);
      setupProductLineTable();
      System.out.println("Products: " + products.toString());

      // SQL statement used to insert textfield values into database
      String sql =
          "INSERT INTO Product(type, manufacturer, name) "
              + "VALUES ( '"
              + productType
              + "', '"
              + product.getManufacturer()
              + "', '"
              + product.getName()
              + "')";

      // executes the previous steps
      stmt.executeUpdate(sql);


    } catch (SQLException e) {
      e.printStackTrace();
    }

    // STEP 4: Clean-up environment
    try {
      if (conn == null) {
        stmt.close();
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  /*
        Title: DataBase OOP Project Week 11
        Author: Scott Vanselow
        Date: 2019
        Code version: 1.0
        Availability: https://github.com/profvanselow/TableViewData/blob/master/src/sample/Controller.java
    */

  private void setupProductLineTable() {
    productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    productManuCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    productTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    produceListView.setItems(productLine);
    tableViewProducts.setItems(productLine);
  }

}
