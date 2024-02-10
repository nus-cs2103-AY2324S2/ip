package duke.run;

import duke.command.*;
import duke.others.BelleException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Constructs Parser.
     */
    public Parser() {
    }

    /**
     * Returns correct belle.belle.command based on users
     * input.
     *
     * @param input Users input.
     * @return Correct belle.belle.command.
     * @throws BelleException  If an invalid belle.belle.command is entered.
     */
    public Command parse(String input) throws BelleException {
        String[] inputlist = input.split(" ");
        assert (inputlist != null) : "input list is null";
        if (inputlist[0].equals("delete")) {
            return new DeleteCommand(inputlist[1]);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (inputlist[0].equals("mark")) {
            return new MarkCommand(inputlist[1]);
        } else if (inputlist[0].equals("unmark")) {
            return new UnmarkCommand(inputlist[1]);
        } else if (inputlist[0].equals("todo") || inputlist[0].equals("deadline") || inputlist[0].equals("event")) {
            if (inputlist[0].equals("todo")) {
                return new AddTaskCommand("todo", input);
            } else if (inputlist[0].equals("deadline")) {
                return new AddTaskCommand("deadline", input);
            } else {
                return new AddTaskCommand("event", input);
            }
        } else if (inputlist[0].equals("find")) {
            assert (!input.equals("find")) : "Please enter a keyword to find";
            return new FindCommand(inputlist[1]);
        } else if (inputlist[0].equals("snooze")) {
            String[] deadlinelist = input.split("to");
            if (deadlinelist.length != 2) {
                throw new BelleException("Please enter command in the format ( snooze [index] to [new deadline] )");
            }
            return new SnoozeCommand(inputlist[1], deadlinelist[1]);
        } else {
            throw new BelleException("Not a valid belle command");
        }
    }
}
