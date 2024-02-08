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
                String[] time = parts[3].split("-");
                String from = time[0];
                String to  = time[1];
                return new Event(parts[2], Boolean.parseBoolean(parts[1]), from, to);
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
    public static void parse(String userInput, TaskList taskList, Storage storage) throws ChatbotException {
        if (userInput.toLowerCase().startsWith("todo")) {
            String taskDetails = userInput.substring(5).trim();
            Task todo = new Todo(taskDetails, false);
            taskList.addTask(todo, storage);
        } else if (userInput.toLowerCase().startsWith("deadline")) {
            String taskDetails = userInput.substring(9).trim();
            String[] details = taskDetails.split(" /by ", 2);
            try {
                Task deadline = new Deadline(details[0], false, details[1]);
                taskList.addTask(deadline, storage);
            } catch (DateTimeParseException e) {
                throw e;
            }
        } else if (userInput.toLowerCase().startsWith("event")) {
            String taskDetails = userInput.substring(6).trim();
            String[] details = taskDetails.split(" /from ", 2);
            String[] times = details[1].split(" /to ", 2);
            try {
                Task event = new Event(details[0], false, times[0], times[1]);
                taskList.addTask(event, storage);
            } catch (DateTimeParseException e) {
                throw e;
            }
        } else if ("list".equalsIgnoreCase(userInput)) {
            taskList.printTasks();
        } else if (userInput.toLowerCase().startsWith("mark")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            taskList.markTask(taskNumber, true, storage);
        } else if (userInput.toLowerCase().startsWith("unmark")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            taskList.markTask(taskNumber, false, storage);
        } else if (userInput.toLowerCase().startsWith("delete")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            taskList.deleteTask(taskNumber, storage);
        } else {
            throw new ChatbotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
