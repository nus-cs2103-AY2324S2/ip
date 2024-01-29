import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static void processUserInput(String userInput) throws SkylerException {
        if (userInput.equals("list")) {
            TaskList.listTasks();
        } else if (userInput.startsWith("todo")) {
            TaskList.addTask(new ToDo(getTaskDescription(userInput, 4), false));
        } else if (userInput.startsWith("deadline")) {
            String[] parts = userInput.split("/by", 2);

            if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new SkylerException(
                        "Invalid 'deadline' command. Please provide a valid description and deadline.");
            }

            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            LocalDate byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            TaskList.addTask(new Deadline(description, byDate, false));
        } else if (userInput.startsWith("event")) {
            String[] parts = userInput.split("/from", 2);

            if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new SkylerException("Invalid 'event' command. Please provide a valid description and timeframe.");
            }

            String description = parts[0].substring(6).trim();
            String from = parts[1].split("/to")[0].trim();
            String to = parts[1].split("/to")[1].trim();
            LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            TaskList.addTask(new Event(description, fromDate, toDate, false));
        } else if (userInput.startsWith("delete")) {
            TaskList.deleteTask(userInput);
        } else if (userInput.startsWith("mark")) {
            TaskList.markTask(userInput);
        } else if (userInput.startsWith("unmark")) {
            TaskList.unmarkTask(userInput);
        } else if (userInput.startsWith("view")) {
            TaskList.viewTasksOnDate(userInput);
        } else {
            throw new SkylerException("I'm sorry, I don't understand that command.");
        }
    }

    public static String getTaskDescription(String userInput, int startIndex, String... keywords)
            throws SkylerException {
        String description = userInput.substring(startIndex).trim();
        for (String keyword : keywords) {
            if (description.startsWith(keyword)) {
                description = description.substring(keyword.length()).trim();
                break;
            }
        }
        if (description.isEmpty()) {
            throw new SkylerException("The description of a task cannot be empty.");
        }
        return description;
    }
}