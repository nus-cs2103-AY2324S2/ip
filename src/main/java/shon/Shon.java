package shon;

import java.time.format.DateTimeParseException;

import shon.command.Command;
import shon.exception.CommandException;
import shon.exception.ParameterException;
import shon.note.NoteList;
import shon.task.TaskList;

/**
 * Represents a chatbot that maintains a Todo List.
 */
public class Shon {
    private TaskList tasks;
    private Storage storage;
    private NoteList notes;

    /**
     * Creates a <code>Shon</code> chatbot.
     */
    public Shon() {
        this.storage = new Storage();
        this.tasks = storage.loadTasks();
        this.notes = storage.loadNotes();


        assert this.storage != null : "No storage initialised in Shon";
        assert this.tasks != null : "No tasklist initialised in Shon";
    }

    /**
     * Parses the input into a command, executes command and return the result.
     * @param input Input given by the user.
     * @return String representing the result of the command.
     */
    public String getResponse(String input)
            throws ParameterException, CommandException, DateTimeParseException {
        Command command = Parser.parse(input, this.tasks, this.notes);
        return executeCommand(command);
    }

    /**
     * Executes and saves the changes to the tasklist into the storage.
     * @param command The command to be executed.
     * @return String representing the result of the command.
     * @throws ParameterException If any parameters to the command is invalid.
     * @throws DateTimeParseException If date/time passed into command is in invalid format.
     */
    private String executeCommand(Command command) throws ParameterException, DateTimeParseException {
        try {
            String output = command.execute();
            this.storage.updateData(this.tasks, this.notes);
            return output;
        } catch (DateTimeParseException e) {
            String parsedString = e.getParsedString();
            String errorMsg = parsedString + " is not a valid date/time. "
                    + "Please enter the date/time in \"dd/mm/yyyy hhmm\" format with valid values.";
            throw new DateTimeParseException(errorMsg, parsedString, e.getErrorIndex());
        }
    }

    /**
     * Returns greeting message.
     * @return Greeting message as a String.
     */
    public String greet() {
        return "Hello! I'm Shon, your friendly bot that keeps track of your tasks and notes. What can I do for you?";
    }
}
