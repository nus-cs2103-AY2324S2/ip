package badgpt.util;

import badgpt.BadGpt;

import badgpt.exceptions.BadException;
import badgpt.exceptions.NoSuchCommandException;
import badgpt.exceptions.WrongFormatException;

import badgpt.tasks.Deadline;
import badgpt.tasks.Event;
import badgpt.tasks.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
            try {
                taskList.mark(Integer.parseInt(args.split(" ")[0]) - 1);
            } catch (NumberFormatException e) {
                throw new WrongFormatException(e.getMessage(), cmd);
            }
            break;
        case "unmark":
            try {
                taskList.unmark(Integer.parseInt(args.split(" ")[0]) - 1);
            } catch (NumberFormatException e) {
                throw new WrongFormatException(e.getMessage(), cmd);
            }
            break;
        case "todo":
            if (args.isEmpty()) {
                throw new WrongFormatException("Description is empty.", cmd);
            } else {
                taskList.store(new ToDo(args));
            }
            break;
        case "deadline":
            int by = args.indexOf("/by");
            if (by == -1) {
                throw new WrongFormatException("No deadline is specified.", cmd);
            }

            try {
                taskList.store(new Deadline(args.substring(0, by).trim(), args.substring(by + 3).trim()));
            } catch (DateTimeParseException e) {
                throw new WrongFormatException(e.getMessage(), cmd);
            }
            break;
        case "event":
            int fromIdx = args.indexOf("/from");
            int toIdx = args.indexOf("/to");
            if (fromIdx == -1 || toIdx == -1) {
                throw new WrongFormatException("No duration is specified.", cmd);
            }

            try {
                taskList.store(new Event(args.substring(0, fromIdx).trim(),
                        args.substring(fromIdx + 5, toIdx).trim(), args.substring(toIdx + 3).trim()));
            } catch (DateTimeParseException e) {
                throw new WrongFormatException(e.getMessage(), cmd);
            }
            break;
        case "delete":
            try {
                taskList.delete(Integer.parseInt(args.split(" ")[0]) - 1);
            } catch (NumberFormatException e) {
                throw new WrongFormatException(e.getMessage(), cmd);
            }
            break;
        case "find":
            if (args.isEmpty()) {
                throw new WrongFormatException("No keyword is specified.", cmd);
            } else {
                taskList.find(args);
            }
            break;
        case "date":
            if (args.isEmpty()) {
                throw new WrongFormatException("No date is specified", cmd);
            }

            try {
                taskList.findByDate(LocalDate.parse(args));
            } catch (DateTimeParseException e) {
                throw new WrongFormatException(e.getMessage(), cmd);
            }
            break;
        default:
            throw new NoSuchCommandException("No such command found.");
            // Fallthrough
        }
    }
}
