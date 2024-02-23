package skyler.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import skyler.task.Deadline;
import skyler.task.Event;
import skyler.task.ToDo;
import skyler.exception.SkylerException;

public class Parser {

    /**
     * Processes user input and performs corresponding actions based on the command
     * provided.
     *
     * @param userInput The user input to be processed.
     * @throws SkylerException If there is an error processing the user input.
     */
    public static String processUserInput(String userInput) throws SkylerException {
        String result = "";

        if (userInput.equals("list")) {
            result = TaskList.listTasks();
        } else if (userInput.startsWith("todo")) {
            result = TaskList.addTask(new ToDo(getTaskDescription(userInput, 4), false));
        } else if (userInput.startsWith("deadline")) {
            String[] parts = userInput.split("/by", 2);

            if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new SkylerException(
                        "Invalid 'deadline' command. Please provide a valid description and deadline.");
            }

            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            LocalDate byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            result = TaskList.addTask(new Deadline(description, byDate, false));
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
            result = TaskList.addTask(new Event(description, fromDate, toDate, false));
        } else if (userInput.startsWith("delete")) {
            result = TaskList.deleteTask(userInput);
        } else if (userInput.startsWith("undo")) {
            result = TaskList.undoTask();
        } else if (userInput.startsWith("mark")) {
            result = TaskList.markTask(userInput);
        } else if (userInput.startsWith("unmark")) {
            result = TaskList.unmarkTask(userInput);
        } else if (userInput.startsWith("find")) {
            result = TaskList.findTasks(userInput.substring(5).trim());
        } else if (userInput.startsWith("view")) {
            result = TaskList.viewTasksOnDate(userInput);
        } else if (userInput.startsWith("help")) {
            result = TaskList.help();
    }else {
            throw new SkylerException("I'm sorry, I don't understand that command.");
        }

        return result;
    }

    /**
     * Extracts the task description from the user input, starting from the
     * specified index.
     *
     * @param userInput  The user input containing the task description.
     * @param startIndex The index from which to start extracting the description.
     * @param keywords   Optional keywords to be removed from the beginning of the
     *                   description.
     * @return The extracted task description.
     * @throws SkylerException If the description is empty or if there is an error
     *                         extracting it.
     */
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