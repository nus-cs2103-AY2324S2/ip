package chaterpillar.parser;

import java.time.format.DateTimeParseException;

import chaterpillar.commands.Command;
import chaterpillar.commands.DeleteCommand;
import chaterpillar.commands.ExitCommand;
import chaterpillar.commands.FindCommand;
import chaterpillar.commands.HelpCommand;
import chaterpillar.commands.ListAllCommand;
import chaterpillar.commands.MarkCommand;
import chaterpillar.commands.TaskCommand;
import chaterpillar.commands.TasksTodayCommand;
import chaterpillar.commands.UnmarkCommand;
import chaterpillar.commands.UnrecognisedCommand;
import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.tasks.DeadlineTask;
import chaterpillar.tasks.EventTask;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TodoTask;

/**
 * Utility class for parsing inputs by the user.
 */
public class Parser {
    /**
     * Generates <code>Task</code> object from one line of <code>String</code> from a file.
     *
     * @param str line describing task, loaded from file
     * @return <code>Task</code> object.
     * @throws ChaterpillarException if there are any errors in any of the line.
     */
    public static Task parseFromFile(String str) throws ChaterpillarException {
        try {
            String[] eachLine = str.split("\\|");
            String taskType = eachLine[0];
            boolean isMarked = Boolean.parseBoolean(eachLine[1]);
            String taskName = eachLine[2];

            switch (taskType) {
            case "T": {
                return new TodoTask(taskName, isMarked);
            }
            case "D": {
                String dueDate = eachLine[3];
                return new DeadlineTask(taskName, isMarked, dueDate);
            }
            case "E": {
                String startDate = eachLine[3];
                String endDate = eachLine[4];
                return new EventTask(taskName, isMarked, startDate, endDate);
            }
            default:
                throw new ChaterpillarException(
                        "Error in type of task of this line: \n" + str);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChaterpillarException("Error in formatting of this line: \n" + str);
        }
    }

    /**
     * Parses the string read from the input of the user.
     * Outputs the respective <code>Command</code> object.
     *
     * @param input command to be parsed
     * @return <code>Command</code> objects corresponding to
     * the instructions of the parsed line.
     * @throws ChaterpillarException if there are any commands typed
     *     wrongly or if there are missing components in the command.
     */
    public static Command parse(String input) throws ChaterpillarException {
        String[] inputSplit = input.split(" ");

        int taskIndex;
        String[] tempInputArgs;
        Task currTask;

        switch (inputSplit[0]) {
        case "list":
            return new ListAllCommand();
        case "mark":
            taskIndex = Integer.parseInt(inputSplit[1]);
            return new MarkCommand(taskIndex);
        case "unmark":
            taskIndex = Integer.parseInt(inputSplit[1]);
            return new UnmarkCommand(taskIndex);
        case "todo":
            currTask = generateTodoTaskFromString(inputSplit);
            return new TaskCommand(currTask);
        case "deadline":
            tempInputArgs = input.split("/");
            currTask = generateDeadlineTaskFromString(tempInputArgs);
            return new TaskCommand(currTask);
        case "event":
            tempInputArgs = input.split("/");
            currTask = generateEventTaskFromString(tempInputArgs);
            return new TaskCommand(currTask);
        case "delete":
            tempInputArgs = input.split(" ");
            return handleDeleteFromInput(tempInputArgs);
        case "find":
            String keyword = input.substring(5);
            return new FindCommand(keyword);
        case "today":
            return new TasksTodayCommand();
        case "help":
            return new HelpCommand();
        case "bye":
            return new ExitCommand();
        default:
            return new UnrecognisedCommand();
        }
    }

    /**
     * Generates a <code>TodoTask</code> from a String input.
     * Private helper method for <code>parse()</code> method.
     *
     * @param tempInputArgs input arguments
     * @return <code>TodoTask</code> according to input specification.
     * @throws ChaterpillarException if name of task is empty.
     */
    private static Task generateTodoTaskFromString(String[] tempInputArgs) throws ChaterpillarException {
        try {
            String taskName = tempInputArgs[1];
            return new TodoTask(taskName);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ChaterpillarException("""
                    Sorry, the taskName of the task todo cannot be empty.
                    The way to use the command is as such: todo taskname""");
        }
    }

    /**
     * Generates a <code>DeadlineTask</code> from a String input.
     * Private helper method for <code>parse()</code> method.
     *
     * @param tempInputArgs input arguments
     * @return <code>DeadlineTask</code> according to input specification.
     * @throws ChaterpillarException if command or date is in the wrong format.
     */
    private static Task generateDeadlineTaskFromString(String[] tempInputArgs) throws ChaterpillarException {
        try {
            String taskName = tempInputArgs[0].substring(9);
            String date = tempInputArgs[1].substring(3);
            return new DeadlineTask(taskName, date);
        } catch (IndexOutOfBoundsException e) {
            throw new ChaterpillarException("""
                    Sorry, this command is in the wrong format.
                    The way to use the command is: deadline taskname /by date_and_time""");
        } catch (DateTimeParseException e) {
            throw new ChaterpillarException("""
                    Unable to add task, wrong date/time format!
                    Suggested format: DD/MM/YYY HH:MM""");
        }
    }

    private static Task generateEventTaskFromString(String[] tempInputArgs) throws ChaterpillarException {
        try {
            String taskName = tempInputArgs[0].substring(6);
            String date1 = tempInputArgs[1].substring(5);
            String date2 = tempInputArgs[2].substring(3);
            return new EventTask(taskName, date1, date2);
        } catch (IndexOutOfBoundsException e) {
            throw new ChaterpillarException("""
                    Sorry, this command is in the wrong format.
                    The way to use the command is: event taskname
                    /from date_and_time /to date_and_time""");
        } catch (DateTimeParseException e) {
            throw new ChaterpillarException("""
                    Unable to add task, wrong date/time format!
                    Suggested format: DD/MM/YYY HH:MM""");
        }
    }

    private static DeleteCommand handleDeleteFromInput(String[] tempInputArgs) throws ChaterpillarException {
        try {
            int index = Integer.parseInt(tempInputArgs[1]) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new ChaterpillarException("""
                    Sorry, there is no number detected.
                    The correct way to use the command is: delete number""");
        } catch (IndexOutOfBoundsException e) {
            throw new ChaterpillarException("""
                    Sorry, the format for this command is wrong.
                    The correct way to use the command is: delete number""");
        }
    }
}