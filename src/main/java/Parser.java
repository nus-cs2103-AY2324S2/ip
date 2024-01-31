import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    public static String taskToFileOutput(Task task) {
        String output = task.toString()
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
        System.out.println("HI");
        System.out.println(output);
        return output;
    }

    public static boolean FileInputToTaskStatus(String input) {
        String taskStatus = input.split("\\|", 0)[1];
        boolean isTaskDone = taskStatus.trim().equals("1") ? true : false;
        return isTaskDone;
    }
}
