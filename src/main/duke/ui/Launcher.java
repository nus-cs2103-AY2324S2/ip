package duke.ui;
import javafx.application.Application;
public class Launcher {
    public static void main(String[] args) {
        assert Main.class != null : "Check if Main is missing.";
        Application.launch(Main.class, args);
    }
}
