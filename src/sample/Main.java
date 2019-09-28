package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class used to set up the stage and load the FXML.
 *
 * @author Alan Norman
 */
public class Main extends Application {

  /**
   * test commit.
   *
   * @param primaryStage accepts a stage and assigns it to primaryStage
   * @throws Exception checks for any exceptions thrown by the body of code
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Hello World");
    Scene scene = new Scene(root, 300, 275);
    scene.getStylesheets().add(Main.class.getResource("productCSS.css").toExternalForm());
    primaryStage.setScene(scene);

    primaryStage.show();
  }

  /**
   * Main method, for running Jar files not created in JavaFX.
   *
   * @param args an array of string values
   */
  public static void main(String[] args) {
    launch(args);
  }
}
