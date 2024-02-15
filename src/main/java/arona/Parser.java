package arona;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Takes an input and process the string.
 */
public class Parser {

    /**
     *
     * @param fullCommand Input from user that specify kind of command.
     * @return A command that can be executed to perform it's actions.
     * @throws AronaException if the user does not provide a task number.
     */
    public static Command parseCommand(String fullCommand) throws AronaException {
        String command = fullCommand.split(" ", 0)[0];
        switch(command) {
        case "bye":
            return new QuitApplication(fullCommand);
        case "list":
            return new ListTask(fullCommand);
        case "mark":
            if (fullCommand.split(" ", 0).length == 1)
                throw new AronaException("Sensei! Please provide a task number!");
            return new MarkTask(fullCommand);
        case "unmark":
            if (fullCommand.split(" ", 0).length == 1)
                throw new AronaException("Sensei! Please provide a task number!");
            return new UnmarkTask(fullCommand);
        case "delete":
            if (fullCommand.split(" ", 0).length == 1)
                throw new AronaException("Sensei! Please provide a task number!");
            return new DeleteTask(fullCommand);
        case "find":
            if (fullCommand.split(" ", 0).length == 1)
                throw new AronaException("Sensei! Please provide a description of what you want to find!");
            return new FindTask(fullCommand);
        case "todo":
            return new AddTask(fullCommand);
        case "deadline":
            return new AddTask(fullCommand);
        case "event":
            return new AddTask(fullCommand);
        case "yes":
            return new AddTask(fullCommand);
        case "no":
            return new AddTask(fullCommand);
        default:
            throw new AronaException("Sensei, Arona does not know what that means!");
        }
    }

    /**
     * Takes user input and parse the date into another format.
     *
     * @param date Date given by user.
     * @return Formatted date.
     * @throws DateTimeParseException if user input date is of invalid format.
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    /**
     * Takes a task and format it into a string that is to be saved in the file.
     *
     * @param task Task to be saved.
     * @return Task in string format.
     */
    public static String taskToFileOutput(Task task) {
        String output = task.userInputToString()
                .replaceAll("\\[T]", "T")
                .replaceAll("\\[D]", "D")
                .replaceAll("\\[E]", "E")
                .replaceAll("\\[ ]", " \\| 0 \\|")
                .replaceAll("\\[X]", " \\| 1 \\|")
                .replaceAll("\\(by:", "\\|")
                .replaceAll("\\)", "")
                .replaceAll("\\(from:", "\\|")
                .replaceAll("to:", "\\|")
                .replaceAll("\\)", "");
        return output;
    }

    /**
     * Takes a string from file and format it to be added into tasklist.
     *
     * @param input Task in string format to be added to tasklist.
     * @return String format of task.
     */
    public static String FileInputToTask(String input) {

        String output = input.replaceAll("\\| 0 \\|", "")
                .replaceAll("\\| 1 \\|", "")
                .replaceAll("\\|", "/")
                .replaceAll("T", "todo")
                .replaceAll("D", "deadline")
                .replaceAll("E", "event")
                .replaceAll("  ", " ");
        return output;
    }

    /**
     * Takes a task in string format and find the status of the task.
     *
     * @param input Task in string format.
     * @return Boolean indicating if the task is completed.
     */
    public static boolean FileInputToTaskStatus(String input) {
        String taskStatus = input.split("\\|", 0)[1];
        boolean isTaskDone = taskStatus.trim().equals("1") ? true : false;
        return isTaskDone;
    }

    public static String ExtractDescription(String input) {
        String description = input.split(" ")[1];
        return description;
    }
}
