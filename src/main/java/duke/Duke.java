package duke;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.ListOutOfBoundsException;
import duke.exceptions.MissingArgumentsException;
import duke.exceptions.WrongTimeFormatException;
import duke.parser.Command;
import duke.parser.Parser;
import duke.parser.Token;
import duke.responses.Responses;


/**
 * The Duke program is a simple task manager that allows users to manage their tasks through a command-line interface.
 */
public class Duke {

    public Duke() {
    }

    public String getResponse(String userInput) {

        Responses dukeResponse = new Responses();
        Parser parser = new Parser();
        parser.feed(userInput);
        Token output;

        try {
            output = parser.parse();
            assert output instanceof Token : "parser output should be a Token";
        } catch (InvalidCommandException e) {
            return Responses.error(e.getMessage());
        } catch (MissingArgumentsException e) {
            return Responses.error(e.getMessage());
        } catch (WrongTimeFormatException e) {
            return Responses.error(e.getMessage());
        }

        assert output.getCmd() instanceof Command : "getCmd() should return a Command";
        switch (output.getCmd()) {
        case BYE:
            return Responses.goodbye();
        case LIST:
            return dukeResponse.listItems();
        case UNMARK:
            try {
                return dukeResponse.unMarkTask(output.getSelectedItem() - 1);
            } catch (ListOutOfBoundsException e) {
                return Responses.error(e.getMessage());
            }
        case MARK:
            try {
                return dukeResponse.markTaskUI(output.getSelectedItem() - 1);
            } catch (ListOutOfBoundsException e) {
                return Responses.error(e.getMessage());
            }
        case FIND:
            return dukeResponse.findTaskUI(output.getSearchKey());
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            return dukeResponse.addItem(output.getTask());
        case DELETE:
            try {
                return dukeResponse.removeTask(output.getSelectedItem() - 1);
            } catch (ListOutOfBoundsException e) {
                return Responses.error(e.getMessage());
            }
        case PRIORITY:
            try {
                return dukeResponse.updatePriority(output.getSelectedItem() - 1, output.getPriority());
            } catch (ListOutOfBoundsException e) {
                return Responses.error(e.getMessage());
            }
        default:
            return "";
        }
    }


}
