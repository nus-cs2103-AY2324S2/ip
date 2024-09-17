import inputcommands.Command;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import snomexceptions.InvalidCommandException;
import snomparser.Parser;
import snomstorage.TaskStorage;
import snomtasklist.TaskList;
import snomui.Ui;


/**
 * Snom class implements the working body of SnomBot.
 */
public class Snom extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/trainer.png"));
    private Image snom = new Image(this.getClass().getResourceAsStream("/images/snom.png"));
    private Ui ui;
    private TaskStorage data;
    private TaskList taskList;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creates an instance of Snom.
     */
    public Snom() {
        this.ui = new Ui();
        this.data = new TaskStorage();
        this.taskList = TaskList.makeTaskList();
        this.parser = new Parser();

    }


    /**
     * Accepts the command entered by the user and converts it
     *         into a instance of a Command.
     * @param s is the input of the user represented as a string,
     * @return a String representing whether the command is valid or not.
     */
    public String runCommand(String s) {
        try {
            Command c = Command.makeCommand(s);
            String reply = this.parser.processCommand(c, this.taskList, this.data);
            if (reply.equals("bye")) {
                return this.getExitMessage();
            } else {
                return reply;
            }
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }

    public String getStartMessage() {
        return this.ui.greet();
    }

    public String getExitMessage() {
        return this.ui.bye();
    }



    @Override
    public void start(Stage stage) {
        Label startMessage = new Label(this.getStartMessage());
        Scene scene = new Scene(startMessage);
        stage.setScene(scene);
        stage.show();
    }


}
