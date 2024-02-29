package charlie.parser;

import charlie.commands.AddCommand;
import charlie.commands.Command;
import charlie.commands.DeleteCommand;
import charlie.commands.ExitCommand;
import charlie.commands.FindCommand;
import charlie.commands.ListCommand;
import charlie.commands.MarkCommand;
import charlie.commands.UnmarkCommand;
import charlie.exceptions.CharlieException;

public class Parser {

    public static Command parse(String fullCommand) throws CharlieException {
        boolean addCommand = fullCommand.startsWith("todo") || fullCommand.startsWith("event")
                || fullCommand.startsWith("deadline");
        if (fullCommand.startsWith("delete")) {
            return initiateDelete(fullCommand);
        } else if (addCommand) {
            return initiateAddCommand(fullCommand);
        } else if (fullCommand.startsWith("list")) {
            return initiateList();
        } else if (fullCommand.startsWith("bye")) {
            return initiateExit();
        } else if (fullCommand.startsWith("mark")) {
            return initiateMark(fullCommand);
        } else if (fullCommand.startsWith("unmark")) {
            return initiateUnmark(fullCommand);
        } else if (fullCommand.startsWith("find")) {
            return initiateFind(fullCommand);
        } else {
            throw new CharlieException("Sorry, unknown command, try again!");
        }
    }

    private static Command initiateDelete(String fullCommand) throws CharlieException {
        String[] words = fullCommand.split(" ");
        assert words.length > 1 : "Delete command format is incorrect";
        return new DeleteCommand(Integer.valueOf(words[1]));
    }

    private static Command initiateAddCommand(String fullCommand) {
        String[] words = fullCommand.split(" ");
        String priorityString = words[words.length-1];
        Integer priorityNumber = Integer.parseInt(priorityString);
        String actualCommand = fullCommand.substring(0, fullCommand.length()-1);
        return new AddCommand(actualCommand, priorityNumber);
    }
    private static Command initiateList() {
        return new ListCommand();
    }

    private static Command initiateExit() {
        return new ExitCommand();
    }

    private static Command initiateMark(String fullCommand) throws CharlieException {
        String[] words = fullCommand.split(" ");
        assert words.length > 1 : "Mark command format is incorrect";
        return new MarkCommand(Integer.valueOf(words[1]));
    }

    private static Command initiateUnmark(String fullCommand) throws CharlieException {
        String[] words = fullCommand.split(" ");
        assert words.length > 1 : "Unmark command format is incorrect";
        return new UnmarkCommand(Integer.valueOf(words[1]));
    }

    private static Command initiateFind(String fullCommand) throws CharlieException {
        String[] words = fullCommand.split(" ");
        assert words.length > 1 : "Find command format is incorrect";
        return new FindCommand(words[1]);
    }
}
