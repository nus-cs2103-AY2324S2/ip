package com.example.artemis;

import java.time.format.DateTimeParseException;

/**
 * The `Parser` class is responsible for parsing user input and executing corresponding actions
 * within the Artemis application.
 */
public class Parser {
    public static final String INVALID_EVENT_INPUT = "Invalid event format. \n"
            + "     Please use: event [description] /from [dd-mm-yyyy hhmm] /to [dd-mm-yyyy hhmm]";
    public static final String INVALID_TASK_NUMBER = "OOPS!!! Please provide a valid task number.";
    public static final String INVALID_DEADLINE_INPUT = "Invalid deadline format. \n"
            + "     Please use: deadline [description] /by [dd-mm-yyyy hhmm]";
    public static final String INVALID_TODO_INPUT = "OOPS!!! The description of a todo cannot be empty.";
    public static final String INVALID_INPUT = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String TASK_ALREADY_EXISTS = "Task already exists: \n";

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param input   The user input.
     * @param tasks   The `TaskList` containing the tasks.
     * @param ui      The `Ui` for user interface interactions.
     * @param storage The `Storage` for saving and loading tasks.
     * @return A message or response generated based on the user input.
     * @throws ArtemisException If there is an issue with parsing the input or executing the command.
     */
    public static String parseInput(String input, TaskList tasks, Ui ui, Storage storage) throws ArtemisException {
        assert input != null : "Input cannot be null";
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        String[] tokens = input.split(" ", 2);
        String command = tokens[0].toLowerCase();

        switch (command) {
        case "bye":
            storage.save(tasks.getTasks());
            return ui.showGoodbyeMessage();
        case "list":
            return ui.showTaskList(tasks.getTasks());
        case "find":
            return ui.handleFindTask(tasks.getTasks(), tokens[1]);
        case "mark":
            return handleMarkAsDone(tokens, tasks, ui);
        case "unmark":
            return handleMarkAsNotDone(tokens, tasks, ui);
        case "todo":
            return handleTodoTask(tokens, tasks, ui);
        case "deadline":
            return handleDeadlineTask(input, tasks, ui);
        case "event":
            return handleEventTask(input, tasks, ui);
        case "delete":
            return handleDeleteTask(tokens, tasks, ui);
        default:
            return ui.showError(INVALID_INPUT);
        }
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param tokens The array of tokens from the user input.
     * @param tasks  The `TaskList` containing the tasks.
     * @param ui     The `Ui` for user interface interactions.
     * @return A message indicating the result of marking a task as done.
     */
    private static String handleMarkAsDone(String[] tokens, TaskList tasks, Ui ui) {
        assert tokens != null : "Tokens cannot be null";

        try {
            int taskIndex = Integer.parseInt(tokens[1]) - 1;
            Task task = tasks.getTasks().get(taskIndex);
            task.markAsDone();

            return ui.showTaskMarkedAsDone(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showError(INVALID_TASK_NUMBER);
        }
    }

    /**
     * Handles the "unmark" command to mark a task as not done.
     *
     * @param tokens The array of tokens from the user input.
     * @param tasks  The `TaskList` containing the tasks.
     * @param ui     The `Ui` for user interface interactions.
     * @return A message indicating the result of marking a task as not done.
     */
    private static String handleMarkAsNotDone(String[] tokens, TaskList tasks, Ui ui) {
        assert tokens != null : "Tokens cannot be null";

        try {
            int taskIndex = Integer.parseInt(tokens[1]) - 1;
            Task task = tasks.getTasks().get(taskIndex);
            task.markAsNotDone();

            return ui.showTaskMarkedAsNotDone(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showError(INVALID_TASK_NUMBER);
        }
    }

    /**
     * Handles the "todo" command to add a todo task.
     *
     * @param tokens The array of tokens from the user input.
     * @param tasks  The `TaskList` containing the tasks.
     * @param ui     The `Ui` for user interface interactions.
     * @return A message indicating the result of adding a todo task.
     */
    private static String handleTodoTask(String[] tokens, TaskList tasks, Ui ui) {
        assert tokens != null : "Tokens cannot be null";

        try {
            String description = tokens[1].trim();

            if (description.isEmpty()) {
                throw new ArtemisException(INVALID_TODO_INPUT);
            }

            if (tasks.containsTask(description)) {
                return ui.showError(TASK_ALREADY_EXISTS + tasks.findTask(description));
            }

            tasks.addTask(new Todo(description));
            return ui.showTaskAdded(tasks.getTasks().size(), tasks.getTasks().get(tasks.getTasks().size() - 1));
        } catch (ArtemisException | IndexOutOfBoundsException e) {
            return ui.showError(INVALID_TODO_INPUT);
        }
    }

    /**
     * Handles the "deadline" command to add a deadline task.
     *
     * @param input The user input containing the deadline details.
     * @param tasks The `TaskList` containing the tasks.
     * @param ui    The `Ui` for user interface interactions.
     * @return A message indicating the result of adding a deadline task.
     */
    private static String handleDeadlineTask(String input, TaskList tasks, Ui ui) {
        assert input != null : "Input cannot be null";

        try {
            String[] tokens = input.split("/by");

            if (tokens.length < 2) {
                throw new ArtemisException(INVALID_DEADLINE_INPUT);
            }

            String description = tokens[0].replace("deadline ", "").trim();
            String by = tokens[1].trim();

            if (tasks.containsTask(description)) {
                return ui.showError(TASK_ALREADY_EXISTS + tasks.findTask(description));
            }

            tasks.addTask(new Deadline(description, by));

            return ui.showTaskAdded(tasks.getTasks().size(), tasks.getTasks().get(tasks.getTasks().size() - 1));
        } catch (ArtemisException | DateTimeParseException e) {
            return ui.showError(INVALID_DEADLINE_INPUT);
        }
    }

    /**
     * Handles the "event" command to add an event task.
     *
     * @param input The user input containing the event details.
     * @param tasks The `TaskList` containing the tasks.
     * @param ui    The `Ui` for user interface interactions.
     * @return A message indicating the result of adding an event task.
     */
    private static String handleEventTask(String input, TaskList tasks, Ui ui) {
        assert input != null : "Input cannot be null";

        try {
            String[] tokens = input.split("/from");

            if (tokens.length < 2) {
                throw new ArtemisException(INVALID_EVENT_INPUT);
            }

            String description = tokens[0].replace("event ", "").trim();
            String[] fromTo = tokens[1].split("/to");

            if (fromTo.length < 2) {
                throw new ArtemisException(INVALID_EVENT_INPUT);
            }

            String from = fromTo[0].trim();
            String to = fromTo[1].trim();

            if (tasks.containsTask(description)) {
                return ui.showError(TASK_ALREADY_EXISTS + tasks.findTask(description));
            }

            tasks.addTask(new Event(description, from, to));

            return ui.showTaskAdded(tasks.getTasks().size(), tasks.getTasks().get(tasks.getTasks().size() - 1));
        } catch (ArtemisException | DateTimeParseException e) {
            return ui.showError(INVALID_EVENT_INPUT);
        }
    }

    /**
     * Handles the "delete" command to delete a task.
     *
     * @param tokens The array of tokens from the user input.
     * @param tasks  The TaskList containing the tasks.
     * @param ui     The Ui for user interface interactions.
     * @return A message indicating the result of deleting a task.
     */
    private static String handleDeleteTask(String[] tokens, TaskList tasks, Ui ui) {
        assert tokens != null : "Tokens cannot be null";

        try {
            int taskIndex = Integer.parseInt(tokens[1]) - 1;
            Task task = tasks.getTasks().get(taskIndex);
            tasks.deleteTask(taskIndex);

            return ui.showTaskDelete(task, tasks.getTasks().size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showError(INVALID_TASK_NUMBER);
        }
    }
}
