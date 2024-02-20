package theadvisor;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    public static void setStageTitle(Stage stage) {
        stage.setTitle("TheAdvisor");
    }
}
