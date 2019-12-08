package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Properties;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

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

  @FXML
  private TextField employeeNameField;

  @FXML
  private PasswordField employeePasswordField;

  @FXML
  private Button createEmployeeButton;

  @FXML
  private ListView<Employee> employeeListView;

  @FXML
  private Label addProductLabel;

  @FXML
  private Label recordProductionLabel;


  /**
   * Global variables for the database connection and statement.
   */
  private Connection conn = null;

  private Statement stmt = null;

  private static List<Widget> products = new ArrayList<>();
  private static List<Employee> employeeList = new ArrayList<>();
  private static ArrayList<ProductionRecord> productionRecords = new ArrayList<>();

  /**
   * initiates adding a product to the database.
   */
  @FXML
  private Button btnAddProduct;

  private static ObservableList<Widget> productLine = FXCollections.observableList(products);
  private static ObservableList<Employee> employeeObservableList = FXCollections
      .observableList(employeeList);

  private FadeTransition productFadeOut = new FadeTransition(Duration.millis(2000));

  private FadeTransition recordFadeOut = new FadeTransition(Duration.millis(2000));



  /**
   * Method that runs on program startup.
   */

  public void initialize() {

    addProductLabel.setVisible(false);
    productFadeOut.setNode(addProductLabel);
    productFadeOut.setFromValue(1.0);
    productFadeOut.setToValue(0.0);
    productFadeOut.setCycleCount(1);
    productFadeOut.setAutoReverse(false);

    recordProductionLabel.setVisible(false);
    recordFadeOut.setNode(recordProductionLabel);
    recordFadeOut.setFromValue(1.0);
    recordFadeOut.setToValue(0.0);
    recordFadeOut.setCycleCount(1);
    recordFadeOut.setAutoReverse(false);

    //loadProductionLog();

    // calls fillItemType method
    fillItemType();

    // calls initializeDB method
    initializeDB();

    loadProductionLog();

    setupProductLineTable();

    //storeObservableList();

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

    String pass = "";
    try {
      Properties prop = new Properties();
      prop.load(new FileInputStream("res/properties"));
      pass = prop.getProperty("password");
    } catch (IOException e) {
      e.printStackTrace();
    }

    //final String pass = "dbpw";

    try {
      // STEP 1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      // STEP 2: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, pass);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void createEmployee(ActionEvent event) {
    String name = employeeNameField.getText();
    String password = employeeNameField.getText();

    Employee employee = new Employee(name, password);
    employeeList.add(employee);

    employeeListView.setItems(employeeObservableList);
  }

  private void addToProductionDB(ArrayList<ProductionRecord> records) {

    for (ProductionRecord record : records) {
      try {



       stmt = conn.createStatement();

        String sql =
            "INSERT INTO ProductionRecord(production_num, product_id, serial_num, date_produced) "
                + "VALUES ( '" + record.getProductionNumber() + "', '" + record.getProductID()
                + "', '" + record.getSerialNumber() + "', '"
                + new Timestamp(record.getDateProduced().getTime()) + "')";

        stmt.executeUpdate(sql);

        System.out.println("result: " + products.toString());
        //close the statement and the result set created
        stmt.close();


      } catch (SQLException e) {
        e.printStackTrace();
      }

    }


  }

  //should load elements of database into arraylist but is giving a null pointer.
  private void loadProductionLog() {
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
        productionRecords.add(record);
      }

      //loads the 3 most recent records
      //Should only load in the previous 3 from DB but can't figure that out
      for (int i = 0; i < 3; i++) {
        taProductionLog.appendText(productionRecords.get(i).toString());
      }

      System.out.println("result: " + products.toString());
      //close the statement and the result set created
      stmt.close();
      resultSet.close();

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
    Widget item = produceListView.getSelectionModel().getSelectedItem();
    ArrayList<ProductionRecord> productionRun = new ArrayList<>();

    boolean productSelected = produceListView.getSelectionModel().isEmpty();
    boolean employeeSelected = employeeListView.getSelectionModel().isEmpty();

    if (!productSelected && !employeeSelected) {
      createRecordSuccessValidator(true);
      for (int i = 0; i <= cmBoxQuantity.getSelectionModel().getSelectedIndex(); i++) {

        ProductionRecord record = new ProductionRecord(item, i);
        productionRun.add(record);

      }
      addToProductionDB(productionRun);
      showProduction(productionRun);
    } else {
      createRecordSuccessValidator(false);
    }
  }

  private void showProduction(ArrayList<ProductionRecord> records) {
    Widget itemSelected = produceListView.getSelectionModel().getSelectedItem();
    Employee employee = employeeListView.getSelectionModel().getSelectedItem();
    String name = employee.getName();

    for (ProductionRecord record : records) {
      ProductionRecord tempRecord = new ProductionRecord(record.getProductionNumber(),
          itemSelected.getName(), record.getSerialNumber(), record.getDateProduced());
      taProductionLog.appendText("Employee: " + name + " " + tempRecord.toString());


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

      if (productName.isEmpty() && productManufacturer.isEmpty()) {
        createLoginValidator(false);
      } else if (productManufacturer.length() >= 3 && productName.length() >= 3) {
        createLoginValidator(true);
        Widget product = new Widget(productName, productManufacturer, productType);

        //products.add(product);
        productLine.add(product);
        setupProductLineTable();
        System.out.println("Products: " + products.toString());

        // SQL statement used to insert textfield values into database
        String sql =
            "INSERT INTO Product(type, manufacturer, name) "
                + "VALUES ( '" + productType + "', '" + product.getManufacturer() + "', '" + product
                .getName() + "')";

        // executes the previous steps
        stmt.executeUpdate(sql);
      } else {
        createLoginValidator(false);
      }


    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * Show some validation on the screen so the user knows if add product worked.
   *
   * @param success represents if the add product function worked or not.
   */
  private void createLoginValidator(boolean success) {
    if (success) {
      addProductLabel.setText("Success!");
    } else {
      addProductLabel.setText("Please fill empty fields" + '\n'
          + " or enter more than 3 letters!");
    }
    addProductLabel.setVisible(true);
    productFadeOut.playFromStart();

  }

  /**
   * Show some validation on the screen so the user knows if record production worked.
   *
   * @param success represents if the record production function worked or not.
   */
  private void createRecordSuccessValidator(boolean success) {
    if (success) {
      recordProductionLabel.setText("Success!");
    } else {
      recordProductionLabel.setText("Please select product and " + '\n' + "employee to record!");
    }
    recordProductionLabel.setVisible(true);
    recordFadeOut.playFromStart();

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
