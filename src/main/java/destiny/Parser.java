package destiny;

import commands.Command;
import commands.ListCmd;
import commands.FindCmd;
import commands.MarkDoneCmd;
import commands.MarkNotDoneCmd;
import commands.DeleteCmd;
import commands.ToDoCmd;
import commands.DeadlineCmd;
import commands.EventCmd;

import java.util.Arrays;

/**
 * Used to understand the user input.
 */
public class Parser {
    /**
     * Determines command that the user has inputted.
     *
     * @param userMessage The user input.
     * @return Command object that will subsequently execute desired task by user.
     * @throws DestinyException If description of command is required but not provided by user.
     */
    public Command parse(String userMessage) throws DestinyException {
        String[] input = new String[2];
        Boolean foundSplit = false;
        for (int i = 0; i < userMessage.length(); i++) {
            if (userMessage.charAt(i) == ' ') {
                input[0] = userMessage.substring(0, i);
                input[1] = userMessage.substring(i + 1, userMessage.length());
                foundSplit = true;
                break;
            }
        }
        if (!foundSplit) {
            input[0] = userMessage;
        }

        String cmd = input[0].toLowerCase().trim();
        // check if command is in list of valid commands
        try {
            AcceptedCmds testCommand = AcceptedCmds.valueOf(cmd.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new DestinyException("Please enter a valid command\nThe list of valid commands are as follows:\n"
                    + Arrays.asList(AcceptedCmds.values()));
        }

        // list is the only command that does not require details
        if (cmd.equals("list")) {
            return new ListCmd();
        }

        // ensure that details are provided
        if (input[1] == null || input[1].trim().length() == 0) {
            throw new DestinyException("Please enter a description after the command");
        }

        // start processing commands that require details.
        String details = input[1].trim();

        //  todo, deadline, event, delete
        switch (cmd) {
        case "find":
            return new FindCmd(details);
        case "mark":
            return new MarkDoneCmd(details);
        case "unmark":
            return new MarkNotDoneCmd(details);
        case "delete":
            return new DeleteCmd(details);
        case "todo":
            return new ToDoCmd(details);
        case "deadline":
            return new DeadlineCmd(details);
        case "event":
            return new EventCmd(details);
        default:
            // invalid commands have already been accounted for,
            // code should not execute beyond this point
            assert false;
            throw new DestinyException("Critical error");
        }
    }
}
