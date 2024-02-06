package toothless;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application{
    public Main() {
        // Empty constructor
    }
    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello World");
        Scene scene = new Scene(label);
        stage.setScene(scene);
        stage.show();
    }
}
