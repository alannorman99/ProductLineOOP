package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button btn_AddProduct;

    @FXML
    void onAddProductPress(ActionEvent event) {
        System.out.println("Product added");
    }

}
