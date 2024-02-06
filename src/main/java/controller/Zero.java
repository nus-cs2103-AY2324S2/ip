package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

import model.Command;
import model.Deadline;
import model.Event;
import model.Storage;
import model.Task;
import view.Ui;

/**
 * Main control class for running the Zero bot.
 * 
 * <p>The bot can be run by creating a new instance and calling the {@link controller.Zero#run()} method.
 * 
 * <p>After the bot is done running, call {@link controller.Zero#close()} to properly terminate.
 */
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

    /**
     * Creates a new {@code Zero} instance which initializes the following:
     * <ul>
     * <li>{@link controller.Parser#dtfInput} {@code LocalDateTime} format for user inputs.</li>
     * <li>{@link model.Deadline#dtf} {@code LocalDateTime} format for output.</li>
     * <li>{@link model.Event#dtf} {@code LocalDateTime} format for output.</li>
     * <li>{@link view.Ui} Sets the input reader, output writer and divider displayed.</li>
     * <li>{@link model.Storage} Loads the save file.</li>
     * </ul>
     * 
     * @throws IOException If an I/O error occurs.
     */
    public Zero() throws IOException {
        // Set Date Time formats for relevant classes
        Parser.setDateTimeFormat(DATE_TIME_FORMATTER_INPUT);
        Deadline.setDateTimeFormat(DATE_TIME_FORMATTER_OUTPUT);
        Event.setDateTimeFormat(DATE_TIME_FORMATTER_OUTPUT);

        ui = new Ui(USER_INPUT, USER_OUTPUT, DIVIDER);
        storage = new Storage(SAVE_FILE_PATH);
    }

    /**
     * Close Input reader and Output writer.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void close() throws IOException {
        ui.close();
    }

    /**
     * Run Zero bot.
     * 
     * <p> Contains the main Zero bot logic and decision making.
     *
     * @return This {@code Zero} instance.
     * @throws IOException If an I/O error occurs.
     */
    public Zero run() throws IOException {
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
