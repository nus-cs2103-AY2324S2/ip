package aurora.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import aurora.command.*;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/** The Parser class represents a Parser that helps to interpret user input for the application. */
public class Parser {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Ui to interact with. */
    private Ui ui;

    /** Storage to interact with. */
    private Storage storage;


    /**
     * Constructs a Parser Object.
     *
     * @param taskList TaskList to interact with
     * @param storage Storage to interact with
     * @param ui Ui to interact with
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Returns a Command object based upon the command input.
     *
     * @param command Command input.
     * @return Command object based upon the command input.
     */
    public Command parseCommand(String command) {
        String[] splitCommands = Parser.splitAtAllBlanks(command);
        String mainC = splitCommands[0];
        if (mainC.equalsIgnoreCase("bye")) {
            return new ByeCommand(this.ui);
        } else if (mainC.equalsIgnoreCase("list")) {
            return new ListCommand(this.taskList, this.ui);
        } else if (mainC.equalsIgnoreCase("mark")) {
            return new MarkCommand(this.taskList, this.storage, splitCommands);
        } else if (mainC.equalsIgnoreCase("unmark")) {
            return new UnmarkCommand(this.taskList, this.storage, splitCommands);
        } else if (mainC.equalsIgnoreCase("todo")) {
            return new TodoCommand(this.taskList, this.ui, this.storage, command);
        } else if (mainC.equalsIgnoreCase("deadline")) {
            return new DeadlineCommand(this.taskList, this.ui, this.storage, command);
        } else if (mainC.equalsIgnoreCase("event")) {
            return new EventCommand(this.taskList, this.ui, this.storage, command);
        } else if (mainC.equalsIgnoreCase("doafter")) {
            return new DoAfterCommand(this.taskList, this.ui, this.storage, command);
        } else if (mainC.equalsIgnoreCase("delete")) {
            return new DeleteCommand(this.taskList, this.storage, splitCommands);
        } else if (mainC.equalsIgnoreCase("find")) {
            return new FindCommand(this.taskList, this.ui, splitCommands);
        } else {
            return new InvalidCommand();
        }
    }



    /**
     * Returns a String array obtained by splitting a command string at all spaces.
     *
     * @param command Command string.
     * @return String array obtained by splitting a command string at all spaces
     */
    public static String[] splitAtAllBlanks(String command) {
        return command.split(" ");
    }

    /**
     * Returns a String array obtained by splitting a command string into 2 at the first space.
     *
     * @param command Command string.
     * @return String array obtained by splitting a command string into 2 at the first space.
     */
    public static String[] splitAtFirstBlank(String command) {
        return command.split(" ", 2);
    }

    /**
     * Returns a String array obtained by splitting a command string into 2 at the first "/by".
     *
     * @param command Command string.
     * @return String array obtained by splitting a command string into 2 at the first "/by".
     */
    public static String[] splitAtFirstBy(String command) {
        return command.split(" /by ", 2);
    }

    /**
     * Returns a String array obtained by splitting a command string into 2 at the first "/from".
     *
     * @param command Command string.
     * @return String array obtained by splitting a command string into 2 at the first "/from".
     */
    public static String[] splitAtFirstFrom(String command) {
        return command.split(" /from ", 2);
    }

    /**
     * Returns a String array obtained by splitting a command string into 2 at the first "/to".
     *
     * @param command Command string.
     * @return String array obtained by splitting a command string into 2 at the first "/to".
     */
    public static String[] splitAtFirstTo(String command) {
        return command.split(" /to ", 2);
    }

    /**
     * Returns a String array obtained by splitting a command string into 2 at the first "/after".
     *
     * @param command Command string.
     * @return String array obtained by splitting a command string into 2 at the first "/after".
     */
    public static String[] splitAtFirstAfter(String command) {
        return command.split(" /after ", 2);
    }

    /**
     * Returns a LocalDateTime Object corresponding to the datetime String.
     *
     * @param date String representation of a datetime.
     * @return LocalDateTime Object corresponding to the datetime String.
     */
    public static LocalDateTime parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateLdt = LocalDateTime.parse(date, formatter);
        return dateLdt;
    }

    /**
     * Returns a LocalDateTime Object corresponding to the datetime String from the storage file.
     *
     * @param date String representation of a datetime from the storage file.
     * @return LocalDateTime Object corresponding to the datetime String from the storage file.
     */
    public static LocalDateTime parseDateFromStorage(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        LocalDateTime dateLdt = LocalDateTime.parse(date, formatter);
        return dateLdt;
    }

}
