package duke;

import duke.exception.AllyException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.UI;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.Scanner;


/**
 * Chatbot to help users manage their tasks.
 * Supports Todos, Deadline and Event tasks.
 */
public class Duke {

    protected static TaskList lst = new TaskList();
    protected static Storage storage = new Storage();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    private MainWindow mainWindow;

    public Duke() {
        this.lst = storage.loadTasks();
    }

    protected void linkMainWindow(MainWindow mw) {
        this.mainWindow = mw;
        mw.addDukeMessage("How can I help you today?");
    }
    /**
     * Main Function that controls the ChatBot
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UI ui = new UI(sc);
        ui.showWelcome();
        lst = storage.loadTasks();
    }

    protected String getResponse(String s) {
        try {
            Parser.Command command = Parser.parseCommand(s);
            String taskDetail = Parser.parseTaskDetail(s);
            switch (command) {
            case LIST:
                return lst.getTasks();
            case BYE:
                Storage.saveTasks();
                mainWindow.addDukeMessage("Bye. Hope to see you again soon!");
                System.exit(0);
                // will not faull through
            case MARK:
                return lst.markComplete(Integer.parseInt(taskDetail.trim()));
            case UNMARK:
                return lst.unmarkComplete(Integer.parseInt(taskDetail.trim()));
            case TODO:
                return lst.addToList(new Todo(taskDetail));
            case DEADLINE:
                return lst.addToList(new Deadline(taskDetail));

            case EVENT:
                return lst.addToList(new Event(taskDetail));

            case DELETE:
                return lst.deleteTask(Integer.parseInt(taskDetail.trim()));

            case FIND:
                Finder finder = new Finder(lst);
                return finder.find(taskDetail);

            case UNKNOWN:
                throw new AllyException();
            }
        } catch (AllyException e) {
            return e.getMessage();
        }
        return "";
    }
}
