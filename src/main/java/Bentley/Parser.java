package bentley;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Parses the user command and performs the corresponding action.
     *
     * @param input    The user input command.
     * @param taskList The TaskList object to perform actions on.
     * @param ui       The Ui object for user interface interaction.
     * @param storage  The Storage object for reading and writing tasks.
     * @return A string representing the result or feedback of the command.
     * @throws DukeException If the command is not recognized.
     */
    public static String parseCommand(String input, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "bye":
                ui.getByeMessage();
                storage.writeTasks(taskList.getTasks());
                System.exit(0);
            case "list":
                String listResult = taskList.listTasks();
                return listResult;
            case "todo":
                return addTodoTask(input, taskList, storage);
            case "deadline":
                return addDeadlineTask(input, taskList, storage);
            case "event":
                return addEventTask(input, taskList, storage);
            case "mark":
                String markResult = taskList.markAsDone(input);
                storage.writeTasks(taskList.getTasks());
                return markResult;
            case "unmark":
                String unmarkResult = taskList.markAsUndone(input);
                storage.writeTasks(taskList.getTasks());
                return unmarkResult;
            case "delete":
                String deleteResult = taskList.deleteTask(input);
                storage.writeTasks(taskList.getTasks());
                return deleteResult;
            case "find":
                String findResult = taskList.findTasks(input);
                return findResult;
            default:
                throw new DukeException("I'm sorry, but I don't understand that command.");
        }
    }

    /**
     * Adds a todo task based on user input.
     *
     * @param input    The user input command for adding a todo task.
     * @param taskList The TaskList object to add the new task to.
     * @param storage  The Storage object for writing tasks.
     * @return A string representing the result or feedback of adding the todo task.
     * @throws DukeException If the input is incomplete or duplicates an existing task.
     */
    public static String addTodoTask(String input, TaskList taskList, Storage storage) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("Looks like something is missing (description)");
        }

        String description = input.substring(5).trim();

        if (description.isEmpty()) {
            throw new DukeException("Looks like the description is missing");
        }

        Task newTask = new Todo(description);

        // Check for duplicate description
        if (isDuplicateDescription(String.valueOf(newTask), taskList)) {
            throw new DukeException("This task already exists in the list");
        }

        taskList.getTasks().add(newTask);


        String result = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.getTasks().size()
                + " tasks in the list.";
        storage.writeTasks(taskList.getTasks());
        return result;
    }

    /**
     * Adds a deadline task based on user input.
     *
     * @param input    The user input command for adding a deadline task.
     * @param taskList The TaskList object to add the new task to.
     * @param storage  The Storage object for writing tasks.
     * @return A string representing the result or feedback of adding the deadline task.
     * @throws DukeException If the input is incomplete or duplicates an existing task.
     */
    public static String addDeadlineTask(String input, TaskList taskList, Storage storage) throws DukeException {
        if (input.length() <= 9) {
            throw new DukeException("Looks like something is missing (description/ Deadline)");
        }

        String[] parts = input.split("/by");
        String descriptionWithDeadline = parts[0].trim();
        String date = parts[1].trim();

        if (descriptionWithDeadline.isEmpty() || date.isEmpty()) {
            throw new DukeException("Looks like something is missing (description/ Deadline)");
        }

        // Extracting "deadline" from the description
        String[] descriptionParts = descriptionWithDeadline.split(" ", 2);
        String keyword = descriptionParts[0].trim(); // Assuming the keyword is the first word

        // Remove "deadline" from the description
        String description = descriptionWithDeadline.replaceFirst(keyword, "").trim();

        LocalDate deadlineDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Task newTask = new Deadline(description, deadlineDate);
        taskList.getTasks().add(newTask);

        String result = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.getTasks().size()
                + " tasks in the list.";
        storage.writeTasks(taskList.getTasks());
        return result;
    }

    /**
     * Adds an event task based on user input.
     *
     * @param input    The user input command for adding an event task.
     * @param taskList The TaskList object to add the new task to.
     * @param storage  The Storage object for writing tasks.
     * @return A string representing the result or feedback of adding the event task.
     * @throws DukeException If the input is incomplete or duplicates an existing task.
     */
    private static String addEventTask(String input, TaskList taskList, Storage storage) throws DukeException {
        if (input.length() <= 6) {
            throw new DukeException("Looks like something is missing (description/ Deadline)");
        }

        String[] parts = input.substring(0).split("/from");
        String description = parts[0].trim().replace("event", "").trim();
        String[] eventParts = parts[1].trim().split("/to");
        String from = eventParts[0].trim();
        String to = eventParts[1].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException(
                    "Looks like something is missing (description/ start date/ end date)");
        }

        LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        Task newTask = new Event(description, fromDateTime, toDateTime);
        taskList.getTasks().add(newTask);

        String result = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.getTasks().size()
                + " tasks in the list.";
        storage.writeTasks(taskList.getTasks());
        return result;
    }

    /**
     * Checks if a task with the same description already exists in the TaskList.
     *
     * @param description The description of the task to check for duplicates.
     * @param taskList    The TaskList object to search for duplicates.
     * @return True if a task with the same description exists, false otherwise.
     */
    private static boolean isDuplicateDescription(String description, TaskList taskList) {
        for (Task task : taskList.getTasks()) {
            if (task.getDescription().trim().equalsIgnoreCase(description.trim())) {
                return true;
            }
        }
        return false;
    }
}
