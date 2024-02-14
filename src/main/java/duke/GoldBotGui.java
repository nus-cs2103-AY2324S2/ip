package duke;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GoldBotGui extends Application {
    public void start(Stage stage) {
        Label label = new Label("Hello, JavaFX!");
        Scene scene = new Scene(label, 400, 200);
        stage.setScene(scene);
        stage.setTitle("GoldBot");
        stage.show();
    }
}
