package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.RyanGoslingException;
import utilities.ResponseHandler;

/**
 * Stub class for parsing user commands related to tasks.
 */
public class CommandsParserStub {

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
        } else if (task.equals(String.valueOf(CommandsEnum.list))) {
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.mark))
                || taskSplit[0].equals(String.valueOf(CommandsEnum.unmark))) {
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.todo))) {
            // Idea from chatGPT
            Pattern pattern = Pattern.compile("todo (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                System.out.println("task");
            } else {
                throw new RyanGoslingException("Incomplete todo command, todo <event>");
            }

        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.deadline))) {
            Pattern pattern = Pattern.compile("deadline (.*?) /by (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                System.out.println("task");
            } else {
                throw new RyanGoslingException("Incomplete deadline command, "
                                                       + "deadline <event> /by <time>");
            }
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.event))) {
            Pattern pattern = Pattern.compile("event (.*?) /from (.*?) /to (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                System.out.println("task");
            } else {
                throw new RyanGoslingException("Incomplete event command, "
                                                       + "event <event> /from <time> /to <time>");
            }
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.delete))) {
        } else {
            throw new RyanGoslingException("I was created in a few hours so "
                                                   + "I don't know what that means :(");
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
        assert task != null : "Task should never be null!";
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
            Pattern pattern = Pattern.compile("todo (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                return task;
            } else {
                throw new RyanGoslingException("Incomplete todo command, todo <event>");
            }

        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.deadline))) {
            Pattern pattern = Pattern.compile("deadline (.*?) /by (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                return task;
            } else {
                throw new RyanGoslingException("Incomplete deadline command, "
                                                       + "deadline <event> /by <time>");
            }
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.event))) {
            Pattern pattern = Pattern.compile("event (.*?) /from (.*?) /to (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                return task;
            } else {
                throw new RyanGoslingException("Incomplete event command, "
                                                       + "event <event> /from <time> /to <time>");
            }
        } else {
            throw new RyanGoslingException("I was created in a few hours so "
                                                   + "I don't know what that means :(");
        }
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
