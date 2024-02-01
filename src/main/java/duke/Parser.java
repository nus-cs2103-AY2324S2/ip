package duke;

import command.Command;
import command.ListCommand;
import command.MarkCommand;
import command.unMarkCommand;
import command.AddToDoCommand;
import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.DeleteCommand;
import command.SearchCommand;
import command.ExitCommand;
import command.OtherCommand;

public class Parser {

    public static Command parse(String userInput) {
        String[] splitInput = userInput.split(" ");

        if (splitInput[0].equalsIgnoreCase(Action.LIST.toString())) {
            return new ListCommand();

        } else if (splitInput[0].equalsIgnoreCase(Action.MARK.toString())) {
            return new MarkCommand(userInput);

        } else if (splitInput[0].equalsIgnoreCase(Action.UNMARK.toString())) {
            return new unMarkCommand(userInput);

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

        } else if (splitInput[0].equalsIgnoreCase(Action.BYE.toString())) {
            return new ExitCommand();

        } else {
            return new OtherCommand();
        }
    }

}
