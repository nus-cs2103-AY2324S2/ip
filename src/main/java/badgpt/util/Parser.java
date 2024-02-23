package badgpt.util;

import badgpt.BadGpt;

import badgpt.commands.CreateDeadline;
import badgpt.commands.CreateEvent;
import badgpt.commands.CreateToDo;
import badgpt.commands.Date;
import badgpt.commands.Delete;
import badgpt.commands.Find;
import badgpt.commands.Mark;
import badgpt.commands.Unmark;
import badgpt.exceptions.BadException;
import badgpt.exceptions.NoSuchCommandException;

/**
 * The class responsible for reading and understanding commands.
 */
public class Parser {
    /**
     * Reads the input and tries to make sense of the user command.
     *
     * @param line The line entered by the user.
     * @param bot The instance of the chatbot being run.
     * @param taskList The instance of the task list being used.
     * @throws BadException If there is an issue with the command entered (e.g. wrong format).
     */
    public static void parse(String line, BadGpt bot, TaskList taskList) throws BadException {
        String cmd = line.split(" ")[0];
        String args = line.substring(cmd.length()).trim();

        switch (cmd) {
        case "bye":
            bot.bye();
            break;
        case "list":
            taskList.listTasks();
            break;
        case "mark":
            new Mark().execute(bot, taskList, args);
            break;
        case "unmark":
            new Unmark().execute(bot, taskList, args);
            break;
        case "todo":
            new CreateToDo().execute(bot, taskList, args);
            break;
        case "deadline":
            new CreateDeadline().execute(bot, taskList, args);
            break;
        case "event":
            new CreateEvent().execute(bot, taskList, args);
            break;
        case "delete":
            new Delete().execute(bot, taskList, args);
            break;
        case "find":
            new Find().execute(bot, taskList, args);
            break;
        case "date":
            new Date().execute(bot, taskList, args);
            break;
        default:
            throw new NoSuchCommandException("No such command found.");
            // Fallthrough
        }
    }
}
