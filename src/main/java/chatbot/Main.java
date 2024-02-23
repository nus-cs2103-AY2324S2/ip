package chatbot;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

  private Duke duke = new Duke();

  @Override
  public void start(Stage stage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("/view/MainWindow.fxml")
      );
      assert Main.class.getResource("/view/MainWindow.fxml") !=
      null : "FXML file not found";
      AnchorPane ap = fxmlLoader.load();
      stage.setTitle("Tfamily bot");
      assert ap != null : "AnchorPane could not be loaded from FXML file";
      Scene scene = new Scene(ap);
      stage.setScene(scene);
      fxmlLoader.<MainWindow>getController().setDuke(duke);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    Application.launch(Main.class, args);
  }
}
