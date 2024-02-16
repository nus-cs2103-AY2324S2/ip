package saopig;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import saopig.command.Command;
import saopig.task.TaskList;

/**
 * Represents a Saopig chatbot.
 * A <code>Saopig</code> object corresponds to a chatbot that can
 * help users to manage their tasks.
 */
public class Saopig extends Application {
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();
    private Storage storage = new Storage(this.ui, this.taskList);
    /**
     * Constructs a Saopig object.
     */
    public Saopig() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.taskList = this.storage.loadTaskList(this.taskList);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setTitle("Saopig");
        stage.getIcons().add(new javafx.scene.image.Image(
                this.getClass().getResourceAsStream("/images/Saopig.jpg")));
        fxmlLoader.<MainWindow>getController().setSaopig(this);
        fxmlLoader.<MainWindow>getController().initialize();
        Button sendButton = fxmlLoader.<MainWindow>getController().getSendButton();
        sendButton.setOnAction((event) -> {
            fxmlLoader.<MainWindow>getController().handleUserInput();
        });
        TextField userInput = fxmlLoader.<MainWindow>getController().getUserInput();
        userInput.setOnAction((event) -> {
            fxmlLoader.<MainWindow>getController().handleUserInput();
        });
        stage.setResizable(true);
        stage.show();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.taskList, this.ui, this.storage);
        } catch (SaopigInvaildSizeException e) {
            return "Unknown command error.";
        }
    }
}
