package andelu;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddToDoCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.OtherCommand;
import command.SearchCommand;
import command.UnmarkCommand;

/**
 * A Parser class to decide the instruction made by the user.
 */
public class Parser {

    /**
     * Parses the instruction to the correct command.
     *
     * @param userInput The information made by the user.
     * @return The actual command class to execute the user instruction.
     */
    public static Command parse(String userInput) {
        String[] splitInput = userInput.split(" ");

        if (splitInput[0].equalsIgnoreCase(Action.LIST.toString())) {
            return new ListCommand();

        } else if (splitInput[0].equalsIgnoreCase(Action.MARK.toString())) {
            return new MarkCommand(userInput);

        } else if (splitInput[0].equalsIgnoreCase(Action.UNMARK.toString())) {
            return new UnmarkCommand(userInput);

        } else if (splitInput[0].equalsIgnoreCase(Action.TODO.toString())) {
            return new AddToDoCommand(userInput);

        } else if (splitInput[0].equalsIgnoreCase(Action.DEADLINE.toString())) {
            return new AddDeadlineCommand(userInput);

        } else if (splitInput[0].equalsIgnoreCase(Action.EVENT.toString())) {
            return new AddEventCommand(userInput);

        } else if (splitInput[0].equalsIgnoreCase(Action.DELETE.toString())) {
            return new DeleteCommand(userInput);

        } else if (splitInput[0].equalsIgnoreCase(Action.DATE.toString())) {
            return new SearchCommand(userInput);

        } else if (splitInput[0].equalsIgnoreCase(Action.FIND.toString())) {
            return new FindCommand(userInput);

        } else if (splitInput[0].equalsIgnoreCase(Action.BYE.toString())) {
            return new ExitCommand();

        } else {
            return new OtherCommand();
        }
    }

}
