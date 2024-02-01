package duke;

import command.*;

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

        } else if (splitInput[0].equalsIgnoreCase(Action.FIND.toString())) {
            return new FindCommand(userInput);

        }else if (splitInput[0].equalsIgnoreCase(Action.BYE.toString())) {
            return new ExitCommand();

        } else {
            return new OtherCommand();
        }
    }

}
