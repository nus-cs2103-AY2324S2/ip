package shon;

import java.time.format.DateTimeParseException;

import shon.command.Command;
import shon.exception.CommandException;
import shon.exception.ParameterException;

/**
 * Represents a chatbot that maintains a Todo List.
 */
public class Shon {
    private TaskList tasks;
    private Storage storage;

    /**
     * Creates a <code>Shon</code> chatbot.
     */
    public Shon() {
        this.storage = new Storage("./data/Shon.txt");
        this.tasks = storage.loadList();
    }

    /**
     * Parses the input into a command, executes command and return the result.
     * @param input Input given by the user.
     * @return String representing the result of the command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return executeCommand(command);
        } catch (ParameterException | CommandException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return e.getParsedString() + " is not a valid date/time. "
                    + "Please enter the date/time in \"dd/mm/yyyy hhmm\" format with valid values.";
        }
    }

    /**
     * Executes and saves the changes to the tasklist into the storage.
     * @param command The command to be executed.
     * @return String representing the result of the command.
     * @throws ParameterException If any parameters to the command is invalid.
     * @throws DateTimeParseException If date/time passed into command is in invalid format.
     */
    private String executeCommand(Command command) throws ParameterException, DateTimeParseException {
        String output = command.execute(this.tasks);
        this.storage.updateData(this.tasks);
        return output;
    }
}
