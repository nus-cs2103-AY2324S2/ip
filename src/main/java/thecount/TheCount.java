package thecount;

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

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import thecount.storage.Storage;
import thecount.task.TaskList;
import thecount.ui.Ui;

/**
 * Represents TheCount application.
 * Initialises and runs the application.
 */
public class TheCount {

    private Storage loader;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new instance of TheCount.
     * Initialises the task list, storage, and user interface.
     */
    public TheCount() {
        this.tasks = new TaskList();
        this.loader = new Storage(this.tasks);
        this.ui = new Ui(this.tasks, this.loader);
    }

    /**
     * Runs the application.
     * Starts the user interface.
     */
    public void run() {
        ui.run();
    }

    /**
     * Starts the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new TheCount().run();
    }
}
