package chatbot.parser;

import chatbot.exception.DukeException;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the data and behaviour of the parser which parses user input.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a new Parser.
     *
     * @param tasks The TaskList object containing the user's task list.
     * @param ui The Ui object that handles user interface interactions.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses user input and executes corresponding commands.
     *
     * @param input The user input to be parsed.
     * @return True if the application should exit, false otherwise.
     * @throws DukeException The error that occurs during parsing or execution of the command.
     */
    public String parseUserInput(String input) throws DukeException {
        String[] inputTokens = input.split(" ");
        String[] deadlineFormatChecker = input.split("/by");
        String[] eventFormatChecker = input.split("/from");
        String command = inputTokens[0].toLowerCase();
        int len = inputTokens.length;

        switch (command) {
            case "list":
                if (len > 1) {
                    String exceptionMessage = "did you mean \"list\"?\n"
                            + "please use that command instead!";
                    throw new DukeException(exceptionMessage);
                }
                return Ui.printListTasks(tasks);
            case "mark":
                if (len == 1) {
                    String exceptionMessage = "you didn't specify which task you want to mark!\n"
                            + "use this format instead:\n"
                            + "mark [task number]";
                    throw new DukeException(exceptionMessage);
                } else if (len > 2) {
                    String exceptionMessage = "please use this format instead:\n"
                            + "mark [task number]";
                    throw new DukeException(exceptionMessage);
                }
                try {
                    int taskNum = Integer.parseInt(inputTokens[1]);
                    return tasks.markTask(taskNum);
                } catch (NumberFormatException e) {
                    String exceptionMessage = "please provide a valid task number!";
                    throw new DukeException(exceptionMessage);
                }
            case "unmark":
                if (len == 1) {
                    String exceptionMessage = "you didn't specify which task you want to unmark!\n"
                            + "use this format instead:\n"
                            + "unmark [task number]";
                    throw new DukeException(exceptionMessage);
                } else if (len > 2) {
                    String exceptionMessage = "please use this format instead:\n"
                            + "unmark [task number]";
                    throw new DukeException(exceptionMessage);
                }
                try {
                    int taskNum2 = Integer.parseInt(inputTokens[1]);
                    return tasks.unmarkTask(taskNum2);
                } catch (NumberFormatException e) {
                    String exceptionMessage = "please provide a valid task number!";
                    throw new DukeException(exceptionMessage);
                }
            case "delete":
                if (len == 1) {
                    String exceptionMessage = "you didn't specify which task you want to delete!\n"
                            + "use this format instead:\n"
                            + "delete [task number]";
                    throw new DukeException(exceptionMessage);
                } else if (len > 2) {
                    String exceptionMessage = "please use this format instead:\n"
                            + "delete [task number]";
                    throw new DukeException(exceptionMessage);
                }
                try {
                    int taskNum3 = Integer.parseInt(inputTokens[1]);
                    return tasks.deleteTask(taskNum3);
                } catch (NumberFormatException e) {
                    String exceptionMessage = "please provide a valid task number!";
                    throw new DukeException(exceptionMessage);
                }
            case "todo":
                if (len == 1) {
                    String exceptionMessage = "you didn't specify what you want to do! use this format instead:\n"
                            + "todo [task description]";
                    throw new DukeException(exceptionMessage);
                }
                String todoName = "";
                for (int i = 1; i < len; i++) {
                    todoName += " " + inputTokens[i];
                }
                return tasks.addTodoTask(todoName);
            case "deadline":
                if (deadlineFormatChecker.length != 2) {
                    String exceptionMessage = "error! please specify the deadline task in this format:\n"
                            + "deadline [task description] /by [YYYY-MM-DD HH:MM]";
                    throw new DukeException(exceptionMessage);
                }
                String deadlineName = "";
                LocalDateTime deadline = LocalDateTime.of(2023, 12, 5, 16, 0);
                for (int i = 1; i < len; i++) {
                    if (inputTokens[i].equals("/by")) {
                        String dateTimeString = inputTokens[i + 1] + " " + inputTokens[i + 2];
                        deadline = LocalDateTime.parse(dateTimeString, formatter);
                        break;
                    } else {
                        deadlineName += " " + inputTokens[i];
                    }
                }
                return tasks.addDeadlineTask(deadlineName, deadline);
            case "event":
                String exceptionMessage = "error! please specify the event task in this format:\n"
                        + "event [task description] /from [YYYY-MM-DD HH:MM] /to [YYYY-MM-DD HH:MM]";
                if (eventFormatChecker.length != 2) {
                    throw new DukeException(exceptionMessage);
                } else if (eventFormatChecker[1].split("to").length != 2) {
                    throw new DukeException(exceptionMessage);
                }
                String eventName = "";
                LocalDateTime start = LocalDateTime.of(2023, 12, 5, 16, 0);
                LocalDateTime end = LocalDateTime.of(2023, 12, 5, 16, 0);
                for (int i = 1; i < len; i++) {
                    if (inputTokens[i].equals("/from")) {
                        String startString = inputTokens[i + 1] + " " + inputTokens[i + 2];
                        String endString = inputTokens[i + 4] + " " + inputTokens[i + 5];
                        start = LocalDateTime.parse(startString, formatter);
                        end = LocalDateTime.parse(endString, formatter);
                        break;
                    } else {
                        eventName += " " + inputTokens[i];
                    }
                }
                return tasks.addEventTask(eventName, start, end);
            case "find":
                if (len == 1) {
                    String exceptionMessage2 = "you didn't specify what you want to find! use this format instead:\n"
                            + "find [keyword]";
                    throw new DukeException(exceptionMessage2);
                }
                String keyword = "";
                for (int i = 1; i < len; i++) {
                    keyword += " " + inputTokens[i];
                }
                return tasks.findTask(keyword.toLowerCase());
            default:
                return Ui.printUnknownCommand();
        }
    }
}
