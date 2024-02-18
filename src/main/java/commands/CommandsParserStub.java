package commands;

import java.util.regex.Pattern;

import exceptions.RyanGoslingException;
import utilities.ResponseHandler;

/**
 * Stub class for parsing user commands related to tasks.
 */
public class CommandsParserStub {

    private Pattern pattern;

    /**
     * Parses original commands and performs corresponding actions.
     *
     * @param task User-inputted task.
     * @return Integer indicating the status of the command execution.
     * @throws RyanGoslingException If there is an issue with command parsing.
     */
    public int parseCommandsOriginal(String task) throws RyanGoslingException {
        String[] taskSplit = task.split(" ");
        if (task.equals(String.valueOf(CommandsEnum.bye))) {
            ResponseHandler.bye();
            return 1;
        }
        return 0;
    }

    /**
     * Parses commands and returns the task as a string.
     *
     * @param task User-inputted task.
     * @return The task as a string.
     * @throws RyanGoslingException If there is an issue with command parsing.
     */
    public String parseCommandsReturnString(String task) throws RyanGoslingException {
        // We only have tests for tasks that return a string to be printed.
        assert !(task.equals("list")
                         || task.equals("mark")
                         || task.equals("unmark")
                         || task.equals("delete"))
                : "Task input for stub should not be, there is no test for this!" + task;
        String[] taskSplit = task.split(" ");
        if (task.equals(String.valueOf(CommandsEnum.bye))) {
            return "1";
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.todo))) {
            return PatternParserStub.todoParser(task);
        }
        return task;
    }

    /**
     * Tests the CommandsParserStub class.
     *
     * @param args Command-line arguments.
     * @throws RyanGoslingException If there is an issue with command parsing.
     */
    public static void main(String[] args) throws RyanGoslingException {
        System.out.println(new CommandsParserStub().parseCommandsReturnString("todo"));
    }
}
