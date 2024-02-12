package duke.main;
import java.io.IOException;

import duke.exception.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *  Main executable for programme
 */
public class TalkingBox extends Application {
    static final String PATH = "save.txt";
    private Storage storage;
    private TaskList taskList;
    Ui ui;
    Parser parser;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/bot.jpg"));


    public TalkingBox() {
        taskList = new TaskList();
        ui = new Ui(taskList);
        parser = new Parser(this.taskList, this.ui);
        try {
            storage = new Storage(taskList, PATH);
        } catch (FileNotFoundException f) {
            this.ui.printException(f);
        }
    }




    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TalkingBox.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setProgramme(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that executes the main programme
     *
     * @throws IOException
     */
    public void run() throws IOException {
        this.ui.printWelcomeMessage();
        boolean isExit = false;
        boolean isError = false;
        while (!isExit && !isError) {
            String command = this.ui.readLine();
            isExit = this.parser.isExit(command);
            try {
                this.parser.parse(command);
            } catch (UnknownInputException e) {
                this.ui.printException(e);
                isError = true;
            }
        }
        this.storage.store();
        this.ui.printExitMessage();
    }

    public static void main(String[] args) throws IOException {
        new TalkingBox().run();
    }
}
