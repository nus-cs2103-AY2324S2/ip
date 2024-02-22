package shon;

import java.time.format.DateTimeParseException;

import shon.command.Command;
import shon.exception.CommandException;
import shon.exception.ParameterException;

/**
 * Represents a chatbot that maintains a Todo List.
 */
public class Shon {
    private TaskList list;
    private Storage storage;

    /**
     * Creates a <code>Shon</code> chatbot.
     */
    public Shon() {
        this.storage = new Storage("./data/Shon.txt");
        this.list = storage.loadList();

        assert this.storage != null : "No storage initialised in Shon";
        assert this.list != null : "No tasklist initialised in Shon";
    }

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

    private String executeCommand(Command command) throws ParameterException, DateTimeParseException {
        String output = command.execute(this.list);
        this.storage.updateData(this.list.formatData());
        return output;
    }
}
