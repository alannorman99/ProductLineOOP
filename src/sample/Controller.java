package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class Controller {

    public Tab tab_Produce;
    @FXML
    private Button btn_RecordProduction;

    @FXML
    private TextField txtF_ProductName;

    @FXML
    private TextField txtF_Manufacturer;

    @FXML
    private ChoiceBox<?> cB_ItemType;

    @FXML
    private ComboBox<String> cmBox_Quantity;



    private Connection conn = null;
    private Statement stmt = null;

    @FXML
    private Button btn_AddProduct;

    public void initialize() {
        System.out.println("Initialize");
    }

    //Creates a connection to the database
    private void initializeDB() {

        //Driver and Location of Database
         final String JDBC_DRIVER = "org.h2.Driver";
         final String DB_URL = "jdbc:h2:./res/ProductionDB";

        //  Database credentials
         final String USER = "";
         final String PASS = "";

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

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
            String sql = "INSERT INTO Product(type, manufacturer, name) " +
                    "VALUES ( 'AUDIO', 'Apple', 'iPod' )";

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


