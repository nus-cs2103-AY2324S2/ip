package duke;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

  private Duke duke = new Duke();
  private Image DaBanner = new Image(this.getClass().getResourceAsStream("/images/DaBanner.png"));

  /**
   * Initializes and starts the Duke application GUI.
   * Loads tasks from file into the Duke instance.
   * Creates and configures the main window using FXML.
   * Sets the Duke instance for the controller of the main window.
   *
   * @param stage The primary stage for the application.
   */
  @Override
  public void start(Stage stage) {
    // Load tasks from file
    Storage.loadTasksFromFile(duke.getTasks());

    try {
      FXMLLoader fxmlLoader = new FXMLLoader(duke.Main.class.getResource("/view/MainWindow.fxml"));
      stage.setTitle("Peanut");
      stage.getIcons().add(DaBanner);
      BorderPane ap = fxmlLoader.load();
      Scene scene = new Scene(ap);
      stage.setScene(scene);
      fxmlLoader.<MainWindow>getController().setDuke(duke);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
