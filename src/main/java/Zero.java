import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

public class Zero {
    private static final String NAME_STRING = "Zero";
    private static final Reader USER_INPUT = new InputStreamReader(System.in);
    private static final Writer USER_OUTPUT = new OutputStreamWriter(System.out);
    private static final String DIVIDER = "_".repeat(100) + "\n";
    private static final String SAVE_FILE_PATH = "data/save.ser";
    private static final DateTimeFormatter DATE_TIME_FORMATTER_INPUT = 
            DateTimeFormatter.ofPattern("d/M/uu HHmm");
    private static final String DATE_TIME_INPUT_FORMAT = "dd/mm/yy HHMM"; // For display to users
    private static final DateTimeFormatter DATE_TIME_FORMATTER_OUTPUT = 
            DateTimeFormatter.ofPattern("EEE, d MMM uuuu, hh:mm a");
    
    private Ui ui;
    private Storage storage;

    Zero() throws IOException {
        // Set Date Time formats for relevant classes
        Parser.dtfInput = DATE_TIME_FORMATTER_INPUT;
        Deadline.dtf = DATE_TIME_FORMATTER_OUTPUT;
        Event.dtf = DATE_TIME_FORMATTER_OUTPUT;

        ui = new Ui(USER_INPUT, USER_OUTPUT, DIVIDER);
        storage = new Storage(SAVE_FILE_PATH);
    }

    void close() throws IOException {
        // Close Input reader and Output writer
        ui.close();
    }

    Zero run() throws IOException {
        // Startup message
        ui.showGreet(NAME_STRING);

        // Start command loop, exit on bye
        boolean isExit = false;
        while (!isExit) {
            Hashtable<String, String> input = Parser.parseInput(ui.getUserInput());
            String cmd = input.get("cmd");
            Command c;
            try {
                c = Command.valueOf(cmd.toUpperCase());
            } catch (IllegalArgumentException | NullPointerException e) {
                // Command not recognised, wait for next user command
                ui.showInvalidCommand(cmd);
                continue;
            }
            switch (c) {
            case EXIT:
                // Fallthrough
            case BYE:
                ui.showBye();
                isExit = true;
                break;
            case LIST:
                ui.showTasks(storage.getTaskList());
                break;
            case MARK:
                try {
                    int idx = Parser.parseIndex(input.get("name"));
                    Task t = storage.getTaskList().mark(idx);
                    storage.saveTaskList();
                    ui.showMarkDone(t);
                } catch (NumberFormatException e) {
                    ui.showIndexParseError();
                } catch (IndexOutOfBoundsException e) {
                    ui.showIndexOutOfBoundsError(storage.getTaskList());
                }
                break;
            case UNMARK:
                try {
                    int idx = Parser.parseIndex(input.get("name"));
                    Task t = storage.getTaskList().unmark(idx);
                    storage.saveTaskList();
                    ui.showUnmarkDone(t);
                } catch (NumberFormatException e) {
                    ui.showIndexParseError();
                } catch (IndexOutOfBoundsException e) {
                    ui.showIndexOutOfBoundsError(storage.getTaskList());
                }
                break;
            case DELETE:
                try {
                    int idx = Parser.parseIndex(input.get("name"));
                    Task t = storage.getTaskList().deleteTask(idx);
                    storage.saveTaskList();
                    ui.showDeleteDone(t, storage.getTaskList());
                } catch (NumberFormatException e) {
                    ui.showIndexParseError();
                } catch (IndexOutOfBoundsException e) {
                    ui.showIndexOutOfBoundsError(storage.getTaskList());
                }
                break;
            case TODO:
                try {
                    String s = input.get("name");
                    Parser.checkNullOrEmpty(s);
                    Task t = storage.getTaskList().addTask(s);
                    storage.saveTaskList();
                    ui.showAddTaskDone(t, storage.getTaskList());
                } catch (IllegalArgumentException e) {
                    ui.showMissingTaskNameError();
                }
                break;
            case DEADLINE:
                try {
                    String s = input.get("name");
                    Parser.checkNullOrEmpty(s);
                    LocalDateTime by = Parser.parseDateTime(input.get("/by"));
                    Task t = storage.getTaskList().addTask(s, by);
                    storage.saveTaskList();
                    ui.showAddTaskDone(t, storage.getTaskList());
                } catch (IllegalArgumentException e) {
                    ui.showMissingTaskNameError();
                } catch (NullPointerException | DateTimeParseException e) {
                    ui.showDateTimeParseError(DATE_TIME_INPUT_FORMAT, "Deadline", "BY DATE");
                }
                break;
            case EVENT:
                String error = "/from";
                try {
                    String s = input.get("name");
                    Parser.checkNullOrEmpty(s);
                    LocalDateTime from = Parser.parseDateTime(input.get("/from"));
                    error = "/to"; // [from] date successfully parsed, change possible error to [to]
                    LocalDateTime to = Parser.parseDateTime(input.get("/to"));
                    Task t = storage.getTaskList().addTask(s, from, to);
                    storage.saveTaskList();
                    ui.showAddTaskDone(t, storage.getTaskList());
                } catch (IllegalArgumentException e) {
                    ui.showMissingTaskNameError();
                } catch (NullPointerException | DateTimeParseException e) {
                    ui.showDateTimeParseError(DATE_TIME_INPUT_FORMAT, "Deadline", error);
                }
                break;
            default:
                // For debugging purposes
                System.err.println("Command(Enum) not yet implemented in switch case.");
                break;
            }
        };

        return this;
    }
}
