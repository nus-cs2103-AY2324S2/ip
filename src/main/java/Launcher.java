import javafx.application.Application;
import ui.Ui;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
  public static void main(String[] args) {
    Application.launch(MainWindow.class, args);
  }
}
