package belle.run;

import belle.command.AddTaskCommand;
import belle.command.ByeCommand;
import belle.command.Command;
import belle.command.DeleteCommand;
import belle.command.FindCommand;
import belle.command.ListCommand;
import belle.command.MarkCommand;
import belle.command.SnoozeCommand;
import belle.command.UnmarkCommand;
import belle.others.BelleException;

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
     * Returns correct belle command based on users
     * input.
     *
     * @param input Users input.
     * @return Correct belle command.
     * @throws BelleException If an invalid belle command is entered.
     */
    public Command parse(String input) throws BelleException {
        String[] inputList = input.split(" ");
        assert (inputList != null) : "input list is null";
        if (inputList[0].equals("delete")) {
            return new DeleteCommand(inputList[1]);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (inputList[0].equals("mark")) {
            return new MarkCommand(inputList[1]);
        } else if (inputList[0].equals("unmark")) {
            return new UnmarkCommand(inputList[1]);
        } else if (inputList[0].equals("todo")) {
            return new AddTaskCommand("T", input);
        } else if (inputList[0].equals("deadline")) {
            return new AddTaskCommand("D", input);
        } else if (inputList[0].equals("event")) {
            return new AddTaskCommand("E", input);
        } else if (inputList[0].equals("find")) {
            assert (!input.equals("find")) : "Please enter a keyword to find";
            return new FindCommand(inputList[1]);
        } else if (inputList[0].equals("snooze")) {
            return new SnoozeCommand(input);
        } else {
            throw new BelleException("Not a valid belle command");
        }
    }
}
