import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parseCommand(String fullCommand) throws AronaException {
        String command = fullCommand.split(" ", 0)[0];
        switch(command) {
            case "bye":
                return new QuitApplication(fullCommand);
            case "list":
                return new ListTask(fullCommand);
            case "mark":
                if (fullCommand.split(" ", 0).length == 1) throw new AronaException("Sensei! Please provide a task number!");
                return new MarkTask(fullCommand);
            case "unmark":
                if (fullCommand.split(" ", 0).length == 1) throw new AronaException("Sensei! Please provide a task number!");
                return new UnmarkTask(fullCommand);
            case "delete":
                if (fullCommand.split(" ", 0).length == 1) throw new AronaException("Sensei! Please provide a task number!");
                return new DeleteTask(fullCommand);
            default:
                return new AddTask(fullCommand);
        }
    }
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

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

    public static boolean FileInputToTaskStatus(String input) {
        String taskStatus = input.split("\\|", 0)[1];
        boolean isTaskDone = taskStatus.trim().equals("1") ? true : false;
        return isTaskDone;
    }
}
