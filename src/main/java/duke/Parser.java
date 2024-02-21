package duke;

import java.time.format.DateTimeParseException;
/**
 * Handles parsing of user inputs and encoded task data.
 * This class provides static methods to decode task information from a string format and to interpret user commands
 * to interact with the task list and storage.
 */
public class Parser {
    /**
     * Decodes a single line of encoded task data into a Task object.
     * The encoded format is expected to be a delimited string containing task type, status, description,
     * and optionally, date/time information.
     *
     * @param line The encoded string representing a task.
     * @return A Task object corresponding to the encoded task data.
     * @throws IllegalArgumentException If the task type is unknown.
     */
    public static Task decodeTask(String line){

        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "T":
                return new Todo(parts[2], Boolean.parseBoolean(parts[1]));
            case "D":
                return new Deadline(parts[2], Boolean.parseBoolean(parts[1]), parts[3]);
            case "E":
                String[] timeParts = parts[3].split("-");
                return new Event(parts[2], Boolean.parseBoolean(parts[1]), timeParts[0], timeParts[1]);
            default:
                throw new IllegalArgumentException("Unknown task type: " + parts[0]);
        }
    }
    /**
     * Parses user input to perform operations on the task list, such as adding, deleting, and marking tasks.
     * This method interprets commands to manipulate the task list and interacts with storage as needed.
     *
     * @param userInput The raw input string from the user.
     * @param taskList The TaskList object to be manipulated based on the user command.
     * @param storage The Storage object used for saving tasks after modifications.
     * @throws ChatbotException If the user input represents an unknown command or fails to provide necessary details.
     * @throws DateTimeParseException If date/time information for deadline or event tasks is improperly formatted.
     */
    public static String parse(String userInput, TaskList taskList, Storage storage) throws ChatbotException {
        if (userInput.trim().toLowerCase().startsWith("todo")) {
            return handleTodoCommand(userInput, taskList, storage);
        } else if (userInput.trim().toLowerCase().startsWith("deadline")) {
            return handleDeadlineCommand(userInput, taskList, storage);
        } else if (userInput.trim().toLowerCase().startsWith("event")) {
            return handleEventCommand(userInput, taskList, storage);
        } else if ("list".equalsIgnoreCase(userInput)) {
            return taskList.printTasks();
        } else if (userInput.trim().toLowerCase().startsWith("mark")) {
            return handleMarkCommand(userInput, taskList, storage);
        } else if (userInput.trim().toLowerCase().startsWith("unmark")) {
            return handleUnmarkCommand(userInput, taskList, storage);
        } else if (userInput.trim().toLowerCase().startsWith("delete")) {
            return handleDeleteCommand(userInput, taskList, storage);
        } else if (userInput.trim().toLowerCase().startsWith("find")) {
            String keyword = userInput.substring("find".length()).trim();
            return taskList.findTask(keyword);
        } else {
            throw new ChatbotException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static String handleTodoCommand(String userInput, TaskList taskList, Storage storage) throws ChatbotException {
        String taskDetails = userInput.substring("todo".length()).trim();
        if (taskDetails.isEmpty()) {
            throw new ChatbotException("The description of a todo cannot be empty.");
        }
        Task todo = new Todo(taskDetails, false);
        return taskList.addTask(todo, storage);
    }

    private static String handleDeadlineCommand(String userInput, TaskList taskList, Storage storage) throws ChatbotException {
        String taskDetails = userInput.substring("deadline".length()).trim();
        String[] details = taskDetails.split(" /by ", 2);
        if (details.length < 2 || details[0].isEmpty() || details[1].isEmpty()) {
            throw new ChatbotException("The description of a deadline or its due time cannot be empty.");
        }
        try {
            Task deadline = new Deadline(details[0], false, details[1]);
            return taskList.addTask(deadline, storage);
        } catch (DateTimeParseException e) {
            throw new ChatbotException("Please use the format dd/MM/yyyy HHmm for dates.");
        }
    }

    private static String handleEventCommand(String userInput, TaskList taskList, Storage storage) throws ChatbotException {
        String taskDetails = userInput.substring("event".length()).trim();
        String[] details = taskDetails.split(" /from ", 2);
        if (details.length < 2 || details[0].isEmpty()) {
            throw new ChatbotException("The description of an event cannot be empty.");
        }
        String[] times = details[1].split(" /to ", 2);
        if (times.length < 2 || times[0].isEmpty() || times[1].isEmpty()) {
            throw new ChatbotException("The start or end time of an event cannot be empty.");
        }
        try {
            Task event = new Event(details[0], false, times[0], times[1]);
            return taskList.addTask(event, storage);
        } catch (DateTimeParseException e) {
            throw new ChatbotException("Please use the format dd/MM/yyyy HHmm for dates.");
        }
    }

    private static String handleMarkCommand(String userInput, TaskList taskList, Storage storage) throws ChatbotException {
        int taskNumber = extractTaskNumber(userInput, "mark");
        return taskList.markTask(taskNumber, true, storage);
    }

    private static String handleUnmarkCommand(String userInput, TaskList taskList, Storage storage) throws ChatbotException {
        int taskNumber = extractTaskNumber(userInput, "unmark");
        return taskList.markTask(taskNumber, false, storage);
    }

    private static String handleDeleteCommand(String userInput, TaskList taskList, Storage storage) throws ChatbotException {
        int taskNumber = extractTaskNumber(userInput, "delete");
        return taskList.deleteTask(taskNumber, storage);
    }

    private static int extractTaskNumber(String userInput, String command) throws ChatbotException {
        try {
            return Integer.parseInt(userInput.substring(command.length()).trim());
        } catch (NumberFormatException e) {
            throw new ChatbotException("Invalid task number.");
        }
    }

}
