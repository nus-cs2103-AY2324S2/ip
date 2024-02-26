package gandalf;

import gandalf.commands.Command;
import gandalf.commands.AddCommand;
import gandalf.commands.ByeCommand;
import gandalf.commands.DeleteCommand;
import gandalf.commands.FindCommand;
import gandalf.commands.ListCommand;
import gandalf.commands.MarkCommand;
import gandalf.commands.UnmarkCommand;
import gandalf.commands.SumCommand;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for the Gandalf chatbot
 */
public class Gandalf extends Application {
    private Storage storage = new Storage("./data/gandalfMeta.txt", "./data/gandalfRead.txt");
    private TaskList tasks = new TaskList(storage.load());
    private Ui ui = new Ui();

    public Gandalf() {

    }

    //@@author ZHANGTIANYAO1-reused
    //Reused from PR
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setTitle("Gandalf");
        stage.getIcons().add(new javafx.scene.image.Image(
                this.getClass().getResourceAsStream("/images/daGandalf.jpg")));
        fxmlLoader.<MainWindow>getController().setGandalf(this);
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
    //@@author

    public String getResponse(String input) {
        if (input.length() == 0) { //ignore accidental new lines from user
            return input;
        }
        Command c;
        Parser parser = new Parser(input);
        parser.interpret();
        String command = parser.getTaskType();
        try {
            switch (command) {
            case "bye":
                c = new ByeCommand(command, tasks, storage, ui);
                break;
            case "list":
                c = new ListCommand(command, tasks, storage, ui);
                break;
            case "delete":
                c = new DeleteCommand(command, tasks, storage, ui, parser.getTaskName());
                break;
            case "mark":
                int taskNumberMark = Integer.parseInt(parser.getTaskName());
                c = new MarkCommand(command, tasks, storage, ui, taskNumberMark);
                break;
            case "unmark":
                int taskNumberUnMark = Integer.parseInt(parser.getTaskName());
                c = new UnmarkCommand(command, tasks, storage, ui, taskNumberUnMark);
                break;
            case "find":
                String keyword = parser.getTaskName();
                c = new FindCommand(command, tasks, storage, ui, keyword);
                break;
            case "sum":
                String expensesName = parser.getTaskName();
                c = new SumCommand(command, tasks, storage, ui, expensesName);
                break;
            case "expenses":
            case "todo":
            case "deadline":
            case "event":
                c = new AddCommand(command, tasks, storage, ui, parser.getTaskName(), parser.getFirstInfo(),
                        parser.getSecondInfo());
                break;
            default:
                return "I do not recognize this command, I must check with the head of my order."
                        + "If you believe you are right, then check formatting in the user guide.";
            }
            return c.execute();
        } catch (GandalfException e) {
            return e.getMessage();
        }
    }
}
