package badgpt.util;

import badgpt.BadGpt;

import badgpt.exceptions.BadException;
import badgpt.exceptions.NoSuchCommandException;
import badgpt.exceptions.WrongFormatException;

import badgpt.tasks.Deadline;
import badgpt.tasks.Event;
import badgpt.tasks.ToDo;

import java.time.format.DateTimeParseException;

public class Parser {
    public static void parse(String line, BadGpt bot, TaskList taskList, Ui ui, FileManager fileManager) {
        String cmd = line.split(" ")[0];
        String args = line.substring(cmd.length()).trim();

        // Cases
        // "bye": Exit the loop and the program.
        // "list": List out all currently stored tasks.
        // "mark": Mark the task corresponding to the number entered after as complete.
        // "unmark": Unmark the task corresponding to the number entered after.
        // Else, store the string entered as a new Task object.
        try {
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
            default:
                throw new NoSuchCommandException("No such command found.");
                // Fallthrough
            }
        } catch (BadException e) {
            ui.printException(e);
        }
    }
}
