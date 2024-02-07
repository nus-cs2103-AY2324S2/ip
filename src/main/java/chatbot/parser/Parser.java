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
    public boolean parseUserInput(String input) throws DukeException {
        String[] inputTokens = input.split(" ");
        String[] deadlineFormatChecker = input.split("/by");
        String[] eventFormatChecker = input.split("/from");
        String command = inputTokens[0].toLowerCase();

        switch (command) {
        case "bye":
            Ui.printByeMessage();
            return true;
            //Fallthrough
        case "list":
            Ui.printListTasks(tasks);
            break;
        case "mark":
            int taskNum = Integer.parseInt(inputTokens[1]);
            tasks.markTask(taskNum);
            break;
        case "unmark":
            int taskNum2 = Integer.parseInt(inputTokens[1]);
            tasks.unmarkTask(taskNum2);
            break;
        case "delete":
            int taskNum3 = Integer.parseInt(inputTokens[1]);
            tasks.deleteTask(taskNum3);
            break;
        case "todo":
            int len = inputTokens.length;
            if (len == 1) {
                String exceptionMessage = Ui.createLine() + "\n"
                        + "you didn't specify what you want to do! use this format instead:\n"
                        + "todo [task description]\n"
                        + Ui.createLine();
                throw new DukeException(exceptionMessage);
            }
            String todoName = "";
            for (int i = 1; i < len; i++) {
                todoName += " " + inputTokens[i];
            }
            tasks.addTodoTask(todoName);
            break;
        case "deadline":
            if (deadlineFormatChecker.length != 2) {
                String exceptionMessage = Ui.createLine() + "\n"
                        + "error! please specify the deadline task in this format:\n"
                        + "deadline [task description] /by [YYYY-MM-DD HH:MM]\n"
                        + Ui.createLine();
                throw new DukeException(exceptionMessage);
            }
            int len2 = inputTokens.length;
            String deadlineName = "";
            LocalDateTime deadline = LocalDateTime.of(2023, 12, 5, 16, 0);
            for (int i = 1; i < len2; i++) {
                if (inputTokens[i].equals("/by")) {
                    String dateTimeString = inputTokens[i + 1] + " " + inputTokens[i + 2];
                    deadline = LocalDateTime.parse(dateTimeString, formatter);
                    break;
                } else {
                    deadlineName += " " + inputTokens[i];
                }
            }
            tasks.addDeadlineTask(deadlineName, deadline);
            break;
        case "event":
            String exceptionMessage = Ui.createLine() + "\n"
                    + "error! please specify the event task in this format:\n"
                    + "event [task description] /from [YYYY-MM-DD HH:MM] /to [YYYY-MM-DD HH:MM]\n"
                    + Ui.createLine();
            if (eventFormatChecker.length != 2) {
                throw new DukeException(exceptionMessage);
            } else if (eventFormatChecker[1].split("to").length != 2) {
                throw new DukeException(exceptionMessage);
            }
            int len3 = inputTokens.length;
            String eventName = "";
            LocalDateTime start = LocalDateTime.of(2023, 12, 5, 16, 0);
            LocalDateTime end = LocalDateTime.of(2023, 12, 5, 16, 0);
            for (int i = 1; i < len3; i++) {
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
            tasks.addEventTask(eventName, start, end);
            break;
        default:
            Ui.printUnknownCommand();
            //Fallthrough
        }
        return false;
    }
}
