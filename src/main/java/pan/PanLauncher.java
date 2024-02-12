package pan;

import javafx.application.Application;

/**
 * PanLauncher - Represents the PanLauncher Class that uses the Application instance to target the Main Class.
 * @author Jerome Goh
 */
public class PanLauncher {
    /**
     * Main Entrypoint into the Pan application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
