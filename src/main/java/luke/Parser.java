package luke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import luke.exception.InvalidDateTimeException;
import luke.exception.InvalidSyntaxException;
import luke.exception.TaskNotFoundException;
import luke.exception.UnknownCommandException;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.ToDo;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Converts a String to LocalDateTime, if it is in "yyyy-MM-dd HH:mm" format.
     * @param date The Date (in String type) that the user inputted.
     * @return Date in LocalDateTime type.
     */
    public LocalDateTime toLocalDateTime(String date) {
        assert date != null;
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Parses the user input - if it is valid, executes it; otherwise, throws an exception.
     *
     * @param input    User input.
     * @param taskList The list that contains the user's tasks. User can manipulate the tasks it contains.
     * @param ui       The ui that confirms the user's action.
     * @param storage  The storage that saves any change to the save file.
     * @return String to output in UI.
     * @throws IOException As storage reads/writes save file.
     */
    public String parse(String input, List taskList, Ui ui, Storage storage) throws IOException {
        String command = input.split(" ")[0].toLowerCase();
        switch (command) {
        case "bye":
            return parseGlobalCommands(input, "bye", taskList, ui);
        case "list":
            return parseGlobalCommands(input, "list", taskList, ui);
        case "mark":
            return parseTaskCommands(input, "mark", taskList, storage, ui);
        case "unmark":
            return parseTaskCommands(input, "unmark", taskList, storage, ui);
        case "todo":
            return parseUnaryCommands(input, "todo", taskList, storage, ui);
        case "deadline":
            return parseDeadlineCommand(input, taskList, storage, ui);
        case "event":
            return parseEventCommand(input, taskList, storage, ui);
        case "delete":
            return parseTaskCommands(input, "delete", taskList, storage, ui);
        case "find":
            return parseUnaryCommands(input, "find", taskList, storage, ui);
        case "help":
            return parseGlobalCommands(input, "help", taskList, ui);
        default:
            assert !input.equals("bye") && !input.equals("list") && !input.equals("mark") && !input.equals("unmark")
                    && !input.equals("todo") && !input.equals("deadline") && !input.equals("event")
                    && !input.equals("delete") && !input.equals("find");
            return new UnknownCommandException().toString();
        }
    }

    /**
     * Parses commands that only has the command itself (i.e. global as it has no additional parameters).
     * At present, it does not require the storage.
     * @param input The user input.
     * @param command The input command by the user.
     * @param ui The ui that displays the success/failure of the command.
     * @return The success/failure message of the command.
     */
    private String parseGlobalCommands(String input, String command, List taskList, Ui ui) {
        if (!input.equalsIgnoreCase(command)) {
            return new InvalidSyntaxException(command).toString();
        }
        switch (command) {
        case "bye":
            // does not use taskList, but still abstracted in this method due to how similar the syntax of these
            // commands are
            assert input.equals("bye") || input.equals("Bye") || input.equals("bYe") || input.equals("byE")
                    || input.equals("BYe") || input.equals("ByE") || input.equals("bYE") || input.equals("BYE");
            return ui.displayGoodBye();
        case "list":
            assert input.equals("list") || input.equals("List") || input.equals("lIst") || input.equals("liSt")
                    || input.equals("lisT") || input.equals("LIst") || input.equals("LiSt") || input.equals("LisT")
                    || input.equals("lISt") || input.equals("lIsT") || input.equals("liST") || input.equals("LISt")
                    || input.equals("LIsT") || input.equals("LiST") || input.equals("lIST") || input.equals("LIST");
            return ui.displayTasks(taskList);
            case "help":
                // does not use taskList, but still abstracted in this method due to how similar the syntax of these
                // commands are
                assert input.equals("help") || input.equals("Help") || input.equals("hElp") || input.equals("heLp")
                        || input.equals("helP") || input.equals("HElp") || input.equals("HeLp") || input.equals("HelP")
                        || input.equals("hELp") || input.equals("hElP") || input.equals("heLP") || input.equals("HELp")
                        || input.equals("HElP") || input.equals("HeLP") || input.equals("hELP") || input.equals("HELP");
                return ui.displayHelp();
        default:
            return new UnknownCommandException().toString();
        }
    }

    /**
     * Parses commands that manipulate a single task.
     * @param input The user input.
     * @param command The input command by the user.
     * @param taskList The task list that contains the task.
     * @param storage The storage that stores the task list.
     * @param ui The ui that displays the success/failure of the command.
     * @return The success/failure message of the command.
     * @throws IOException As it writes to the save file under storage.
     */
    private String parseTaskCommands(String input, String command, List taskList, Storage storage, Ui ui)
            throws IOException {
        if (input.split(" ").length != 2) {
            return new InvalidSyntaxException(command).toString();
        }
        try {
            Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException e) {
            return new InvalidSyntaxException(command).toString();
        }
        int taskNum = Integer.parseInt(input.split(" ")[1]);
        if (!taskList.isValidTaskNum(taskNum)) {
            return new TaskNotFoundException(taskList).toString();
        }
        switch (command) {
        case "mark":
            return taskList.markTask(taskNum - 1, storage, ui);
        case "unmark":
            return taskList.unmarkTask(taskNum - 1, storage, ui);
        case "delete":
            return taskList.deleteTask(taskNum - 1, storage, ui);
        default:
            return new UnknownCommandException().toString();
        }
    }

    /**
     * Parses commands in the form [command] [parameter], i.e. with only 1 parameter
     * @param input The user input.
     * @param command The input command by the user.
     * @param taskList The task list that contains the task.
     * @param storage The storage that stores the task list.
     * @param ui The ui that displays the success/failure of the command.
     * @return The success/failure message of the command.
     * @throws IOException As it writes to the save file under storage.
     */
    private String parseUnaryCommands(String input, String command, List taskList, Storage storage, Ui ui)
            throws IOException {
        if (input.split(" ").length <= 1) {
            return new InvalidSyntaxException("todo").toString();
        }
        switch (command) {
        case "todo":
            return taskList.addTask(new ToDo(input.substring(5)), storage, ui);
        case "find":
            // does not use storage, but still abstracted in this method due to how similar the syntax of these
            // 2 commands are
            return taskList.findTasks(input.substring(5), ui);
        default:
            return new UnknownCommandException().toString();
        }
    }

    private String parseDeadlineCommand(String input, List taskList, Storage storage, Ui ui) throws IOException {
        if (!Pattern.matches("deadline .+ /by .+", input)
                || input.split("/by").length != 2) {
            return new InvalidSyntaxException("deadline").toString();
        }
        try {
            toLocalDateTime(input.split("/by ")[1]);
        } catch (Exception e) {
            return new InvalidDateTimeException().toString();
        }
        String description = input.substring(9).split(" /by")[0];
        String dueDate = input.split("/by ")[1];
        return taskList.addTask(new Deadline(description, toLocalDateTime(dueDate)), storage, ui);
    }

    private String parseEventCommand(String input, List taskList, Storage storage, Ui ui) throws IOException {
        if (!Pattern.matches("event .+ /from .+ /to .+", input)
                || input.split("/from").length != 2
                || input.split("/to").length != 2) {
            return new InvalidSyntaxException("event").toString();
        }
        try {
            toLocalDateTime(input.split("/from ")[1].split(" /to")[0]);
            toLocalDateTime(input.split("/to ")[1]);
        } catch (Exception e) {
            return new InvalidDateTimeException().toString();
        }
        String description = input.substring(6).split(" /from")[0];
        String startDate = input.split("/from ")[1].split(" /to")[0];
        String endDate = input.split("/to ")[1];
        return taskList.addTask(new Event(description, toLocalDateTime(startDate), toLocalDateTime(endDate)),
                storage, ui);
    }
}
