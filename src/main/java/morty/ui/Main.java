package morty.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import morty.Morty;

/**
 * A GUI for Morty using FXML.
 */
public class Main extends Application {

  private Morty morty = new Morty("data/tasks.txt");

  /**
   * Starts the Morty application.
   *
   * @param args Command line arguments.
   */
  @Override
  public void start(Stage stage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
      AnchorPane ap = fxmlLoader.load();
      Scene scene = new Scene(ap);
      stage.setScene(scene);
      fxmlLoader.<MainWindow>getController().setMorty(morty);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
