package kitchensink;

import java.io.IOException;

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

import kitchensink.exception.InvalidDateTimeException;
import kitchensink.exception.InvalidSyntaxException;
import kitchensink.exception.TaskNotFoundException;
import kitchensink.exception.UnknownCommandException;

/**
 * The entry point of the app.
 */
public class KitchenSink /* extends Application */ {
    private Ui ui = new Ui();
    private Parser parser = new Parser();
    private String fileName = "./data/duke.txt";
    private Storage storage = new Storage(fileName);
    private List taskList = new List(storage.loadTasks());

    public KitchenSink() throws IOException {

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws TaskNotFoundException, UnknownCommandException, InvalidSyntaxException,
            IOException, InvalidDateTimeException {
        return parser.parse(input, taskList, ui, storage);
    }
}
