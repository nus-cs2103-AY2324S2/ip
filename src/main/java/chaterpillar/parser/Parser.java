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
     * Parses the string read from the input of the user.
     * Outputs the respective <code>Command</code> object.
     *
     * @param input command to be parsed
     * @return <code>Command</code> objects corresponding to
     *     the instructions of the parsed line.
     * @throws ChaterpillarException if there are any commands typed
     *     wrongly or if there are missing components in the command.
     */
    public static Command parse(String input) throws ChaterpillarException {
        String[] inputSplit = input.split(" ");

        int taskIndex;
        String taskName;
        String[] tempInputArgs;
        Task currTask;

        switch(inputSplit[0]) {
        case "list":
            return new ListAllCommand();
        case "mark":
            taskIndex = Integer.parseInt(inputSplit[1]);
            return new MarkCommand(taskIndex);
        case "unmark":
            taskIndex = Integer.parseInt(inputSplit[1]);
            return new UnmarkCommand(taskIndex);
        case "todo":
            try {
                taskName = input.substring(5);
                currTask = new TodoTask(taskName);
                return new TaskCommand(currTask);
            } catch (StringIndexOutOfBoundsException e) {
                throw new ChaterpillarException(
                        "Sorry, the taskName of the task todo cannot be empty.\n"
                        + "The way to use the command is as such: todo taskname");
            }
        case "deadline":
            try {
                tempInputArgs = input.split("/");
                taskName = tempInputArgs[0].substring(9);
                String date = tempInputArgs[1].substring(3);
                currTask = new DeadlineTask(taskName, date);
                return new TaskCommand(currTask);
            } catch (IndexOutOfBoundsException e) {
                throw new ChaterpillarException(
                        "Sorry, this command is in the wrong format.\n"
                        + "The way to use the command is: deadline taskname /by date_and_time");
            } catch (DateTimeParseException e) {
                throw new ChaterpillarException(
                        "Unable to add task, wrong date/time format!\n"
                        + "Suggested format: DD/MM/YYY HH:MM");
            }
        case "event":
            try {
                tempInputArgs = input.split("/");
                taskName = tempInputArgs[0].substring(6);
                String date1 = tempInputArgs[1].substring(5);
                String date2 = tempInputArgs[2].substring(3);
                currTask = new EventTask(taskName, date1, date2);
                return new TaskCommand(currTask);
            } catch (IndexOutOfBoundsException e) {
                throw new ChaterpillarException(
                        "Sorry, this command is in the wrong format.\n"
                        + "The way to use the command is: event taskname "
                        + "/from date_and_time /to date_and_time");
            } catch (DateTimeParseException e) {
                throw new ChaterpillarException(
                        "Unable to add task, wrong date/time format!\n"
                        + "Suggested format: DD/MM/YYY HH:MM");
            }
        case "delete":
            try {
                tempInputArgs = input.split(" ");
                int index = Integer.parseInt(tempInputArgs[1]) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new ChaterpillarException(
                        "Sorry, there is no number detected.\n"
                        + "The correct way to use the command is: delete number");
            } catch (IndexOutOfBoundsException e) {
                throw new ChaterpillarException(
                        "Sorry, the format for this command is wrong.\n"
                        + "The correct way to use the command is: delete number");
            }
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
}
