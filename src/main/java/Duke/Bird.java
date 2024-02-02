package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class represents the main driver of the application.
 * It initializes the task list, storage, and user interface, and starts the user interface.
 */
public class Bird extends Application {
    private Gui gui;

    /**
     * Constructs a new Bird ChatBot.
     * Initializes the task list, storage, and user interface, and starts the user interface.
     */
    public Bird() {
        TaskList.create();
        Storage.init();
    }

    @Override
    public void start(Stage stage) {
        // Create Main Layout
        gui = new Gui(stage);
        AnchorPane mainLayout = gui.create();

        // Rendering
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Welcome Message
        gui.greet();
    }

}

