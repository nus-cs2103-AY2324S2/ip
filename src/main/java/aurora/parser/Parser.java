package aurora.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import aurora.command.ByeCommand;
import aurora.command.Command;
import aurora.command.DeadlineCommand;
import aurora.command.DeleteCommand;
import aurora.command.EventCommand;
import aurora.command.FindCommand;
import aurora.command.InvalidCommand;
import aurora.command.ListCommand;
import aurora.command.MarkCommand;
import aurora.command.TodoCommand;
import aurora.command.UnmarkCommand;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/**
 * Parser that helps to split the command string into chunks that can then be processed by the application.
 */
public class Parser {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Ui to interact with. */
    private Ui ui;

    /** Storage to interact with. */
    private Storage storage;


    /**
     * Constructor for a Parser.
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
     * Parses a command and returns an appropriate Command object
     *
     * @params command Full command in String format
     * @return An appropriate command object given the command string.
     */
    public Command parseCommand(String command) {
        String[] splitCommands = Parser.splitAtAllBlanks(command);
        String mainC = splitCommands[0];
        if (mainC.equalsIgnoreCase("bye")) {
            ByeCommand byeCommand = new ByeCommand(this.ui);
            return byeCommand;
        } else if (mainC.equalsIgnoreCase("list")) {
            ListCommand listCommand = new ListCommand(this.taskList, this.ui);
            return listCommand;
        } else if (mainC.equalsIgnoreCase("mark")) {
            MarkCommand markCommand = new MarkCommand(this.taskList, this.storage, splitCommands);
            return markCommand;
        } else if (mainC.equalsIgnoreCase("unmark")) {
            UnmarkCommand unmarkCommand = new UnmarkCommand(this.taskList, this.storage,
                    splitCommands);
            return unmarkCommand;
        } else if (mainC.equalsIgnoreCase("todo")) {
            TodoCommand todoCommand = new TodoCommand(this.taskList, this.ui, this.storage, command);
            return todoCommand;
        } else if (mainC.equalsIgnoreCase("deadline")) {
            DeadlineCommand deadlineCommand = new DeadlineCommand(this.taskList, this.ui, this.storage,
                    command);
            return deadlineCommand;
        } else if (mainC.equalsIgnoreCase("event")) {
            EventCommand eventCommand = new EventCommand(this.taskList, this.ui, this.storage, command);
            return eventCommand;
        } else if (mainC.equalsIgnoreCase("delete")) {
            DeleteCommand deleteCommand = new DeleteCommand(this.taskList, this.storage,
                    splitCommands);
            return deleteCommand;
        } else if (mainC.equalsIgnoreCase("find")) {
            FindCommand findCommand = new FindCommand(this.taskList, this.ui, splitCommands);
            return findCommand;
        } else {
            InvalidCommand invalidCommand = new InvalidCommand();
            return invalidCommand;
        }
    }



    /**
     * Splits a command string at all spaces
     *
     * @param command Command string.
     * @return The string array containing the split command.
     */
    public static String[] splitAtAllBlanks(String command) {
        return command.split(" ");
    }

    /**
     * Splits a command string into 2 at the first space
     *
     * @param command Command string.
     * @return The string array containing the split command.
     */
    public static String[] splitAtFirstBlank(String command) {
        return command.split(" ", 2);
    }

    /**
     * Splits a command string into 2 at the "/by"
     *
     * @param command Command string.
     * @return The string array containing the split command.
     */
    public static String[] splitAtFirstBy(String command) {
        return command.split(" /by ", 2);
    }

    /**
     * Splits a command string into 2 at the "/from"
     *
     * @param command Command string.
     * @return The string array containing the split command.
     */
    public static String[] splitAtFirstFrom(String command) {
        return command.split(" /from ", 2);
    }

    /**
     * Splits a command string into 2 at the "/to"
     *
     * @param command Command string.
     * @return The string array containing the split command.
     */
    public static String[] splitAtFirstTo(String command) {
        return command.split(" /to ", 2);
    }

    /**
     * Parses a date and time String and returns a LocalDateTime object.
     *
     * @param date String representation of a date.
     * @return LocalDateTime according to the String representation of the date.
     */
    public static LocalDateTime parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateLdt = LocalDateTime.parse(date, formatter);
        return dateLdt;
    }

    /**
     * Parses a date and time String from storage and returns a LocalDateTime object.
     *
     * @param date String representation of a date.
     * @return LocalDateTime according to the String representation of the date.
     */
    public static LocalDateTime parseDateFromStorage(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        LocalDateTime dateLdt = LocalDateTime.parse(date, formatter);
        return dateLdt;
    }

}
