package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Command;
import model.Deadline;
import model.Event;
import model.Storage;
import model.Task;
import model.ToDo;
import view.MainWindow;
import view.Ui;

/**
 * Main control class for running the Zero bot.
 * 
 * <p>The bot starts a JavaFX {@link Application} UI and exits when the UI window is closed.
 */
public class Zero extends Application {
    private static final String NAME_STRING = "Zero";
    private static final String SAVE_FILE_PATH = "data/save.ser";
    private static final DateTimeFormatter DATE_TIME_FORMATTER_INPUT =
            DateTimeFormatter.ofPattern("d/M/uu HHmm");
    private static final String DATE_TIME_INPUT_FORMAT = "dd/mm/yy HHMM"; // For display to users
    private static final DateTimeFormatter DATE_TIME_FORMATTER_OUTPUT =
            DateTimeFormatter.ofPattern("EEE, d MMM uuuu, hh:mm a");

    private Storage storage;

    /**
     * Initializes the {@code Zero} application with the following:
     * <ul>
     * <li>{@link controller.Parser#dtfInput} {@code LocalDateTime} format for user inputs.</li>
     * <li>{@link model.Deadline#dtf} {@code LocalDateTime} format for output.</li>
     * <li>{@link model.Event#dtf} {@code LocalDateTime} format for output.</li>
     * <li>{@link model.Storage} Loads the save file.</li>
     * </ul>
     * 
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void init() throws IOException {
        // Set Date Time formats for relevant classes
        Parser.setDateTimeFormat(DATE_TIME_FORMATTER_INPUT);
        Deadline.setDateTimeFormat(DATE_TIME_FORMATTER_OUTPUT);
        Event.setDateTimeFormat(DATE_TIME_FORMATTER_OUTPUT);
        
        storage = new Storage(SAVE_FILE_PATH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow mwController = fxmlLoader.<MainWindow>getController();
            mwController.setZero(this);
            mwController.showGreet(Ui.showGreet(NAME_STRING));
            stage.setTitle("Zero");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a response {@code String} from the Zero bot according to the user input.
     * 
     * @param input User input.
     * @return Zero bot's response to the user input.
     * @throws IOException If an I/O error occurs.
     */
    public String getResponse(String input) throws IOException {
        if (input.isBlank()) {
            return Ui.showEmptyCommand();
        }

        Hashtable<String, String> inputDict = Parser.parseInput(input);
        String cmd = inputDict.get("cmd");
        Command c;

        try {
            c = Command.valueOf(cmd.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            // Command not recognised, wait for next user command
            return Ui.showInvalidCommand(cmd);
        }
        
        switch (c) {
        case EXIT:
            // Fallthrough
        case BYE:
            return Ui.showBye();
        case LIST:
            return Ui.showTasks(storage.getTaskList());
        case MARK:
            return executeMarkCommand(inputDict);
        case UNMARK:
            return executeUnmarkCommand(inputDict);
        case DELETE:
            return executeDeleteCommand(inputDict);
        case TODO:
            return executeToDoCommand(inputDict);
        case DEADLINE:
            return executeDeadlineCommand(inputDict);
        case EVENT:
            return executeEventCommand(inputDict);
        case FIND:
            return executeFindCommand(inputDict);
        case TAG:
            return executeTagCommand(inputDict);
        default:
            // For debugging purposes
            return "Command(Enum) not yet implemented in switch case.";
        }
    }

    private String executeTagCommand(Hashtable<String, String> inputDict) throws IOException {
        try {
            String[] split = inputDict.get("name").split(" ", 2);
            int idx = Parser.parseIndex(split[0]);
            Parser.checkNullOrEmpty(split[1]);
            Task t = storage.getTaskList().tag(idx, split[1]);
            storage.saveTaskList();
            return Ui.showTagDone(t);
        } catch (NumberFormatException e) {
            return Ui.showIndexParseError();
        } catch (IllegalArgumentException e) {
            return Ui.showMissingTagNameError();
        } catch (IndexOutOfBoundsException e) {
            return Ui.showIndexOutOfBoundsError(storage.getTaskList().size());
        }
    }

    private String executeFindCommand(Hashtable<String, String> inputDict) {
        try {
            String s = inputDict.get("name");
            Parser.checkNullOrEmpty(s);
            return Ui.showAllMatchingTasks(storage.getTaskList().match(s));
        } catch (IllegalArgumentException e) {
            return Ui.showMissingFindArgError();
        }
    }

    private String executeEventCommand(Hashtable<String, String> inputDict) throws IOException {
        String error = "/from";
        try {
            String s = inputDict.get("name");
            Parser.checkNullOrEmpty(s);
            LocalDateTime from = Parser.parseDateTime(inputDict.get("/from"));
            error = "/to"; // [from] date successfully parsed, change possible error to [to]
            LocalDateTime to = Parser.parseDateTime(inputDict.get("/to"));
            Task t = storage.getTaskList().addTask(s, from, to);
            assert t instanceof Event : " Event command did not create an Event Task";
            storage.saveTaskList();
            return Ui.showAddTaskDone(t, storage.getTaskList().size());
        } catch (IllegalArgumentException e) {
            return Ui.showMissingTaskNameError();
        } catch (NullPointerException | DateTimeParseException e) {
            assert error == "/from" || error == "/to" : " Invalid error type";
            return Ui.showDateTimeParseError(DATE_TIME_INPUT_FORMAT, "Deadline", error);
        }
    }

    private String executeDeadlineCommand(Hashtable<String, String> inputDict) throws IOException {
        try {
            String s = inputDict.get("name");
            Parser.checkNullOrEmpty(s);
            LocalDateTime by = Parser.parseDateTime(inputDict.get("/by"));
            Task t = storage.getTaskList().addTask(s, by);
            assert t instanceof Deadline : " Deadline command did not create a Deadline Task";
            storage.saveTaskList();
            return Ui.showAddTaskDone(t, storage.getTaskList().size());
        } catch (IllegalArgumentException e) {
            return Ui.showMissingTaskNameError();
        } catch (NullPointerException | DateTimeParseException e) {
            return Ui.showDateTimeParseError(DATE_TIME_INPUT_FORMAT, "Deadline", "BY DATE");
        }
    }

    private String executeToDoCommand(Hashtable<String, String> inputDict) throws IOException {
        try {
            String s = inputDict.get("name");
            Parser.checkNullOrEmpty(s);
            Task t = storage.getTaskList().addTask(s);
            assert t instanceof ToDo : " ToDo command did not create a ToDo Task";
            storage.saveTaskList();
            return Ui.showAddTaskDone(t, storage.getTaskList().size());
        } catch (IllegalArgumentException e) {
            return Ui.showMissingTaskNameError();
        }
    }

    private String executeDeleteCommand(Hashtable<String, String> inputDict) throws IOException {
        try {
            int idx = Parser.parseIndex(inputDict.get("name"));
            Task t = storage.getTaskList().deleteTask(idx);
            storage.saveTaskList();
            return Ui.showDeleteDone(t, storage.getTaskList().size());
        } catch (NumberFormatException e) {
            return Ui.showIndexParseError();
        } catch (IndexOutOfBoundsException e) {
            return Ui.showIndexOutOfBoundsError(storage.getTaskList().size());
        }
    }

    private String executeUnmarkCommand(Hashtable<String, String> inputDict) throws IOException {
        try {
            int idx = Parser.parseIndex(inputDict.get("name"));
            Task t = storage.getTaskList().unmark(idx);
            storage.saveTaskList();
            return Ui.showUnmarkDone(t);
        } catch (NumberFormatException e) {
            return Ui.showIndexParseError();
        } catch (IndexOutOfBoundsException e) {
            return Ui.showIndexOutOfBoundsError(storage.getTaskList().size());
        }
    }

    private String executeMarkCommand(Hashtable<String, String> inputDict) throws IOException {
        try {
            int idx = Parser.parseIndex(inputDict.get("name"));
            Task t = storage.getTaskList().mark(idx);
            storage.saveTaskList();
            return Ui.showMarkDone(t);
        } catch (NumberFormatException e) {
            return Ui.showIndexParseError();
        } catch (IndexOutOfBoundsException e) {
            return Ui.showIndexOutOfBoundsError(storage.getTaskList().size());
        }
    }
}
