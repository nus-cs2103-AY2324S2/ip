package yapchit;
import javafx.application.Application;
import yapchit.yapchitui.Yapchit;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Yapchit.class, args);
    }
}

