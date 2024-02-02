package com.example.artemis;

import java.time.format.DateTimeParseException;

/**
 * Parses user input and performs corresponding actions in the Artemis application.
 */
public class Parser {

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param input   The user input.
     * @param tasks   The TaskList containing the tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for saving and loading tasks.
     * @throws ArtemisException If there is an issue with parsing the input or executing the command.
     */
    public static String parseInput(String input, TaskList tasks, Ui ui, Storage storage) throws ArtemisException {
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
            return ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param tokens The array of tokens from the user input.
     * @param tasks  The TaskList containing the tasks.
     * @param ui     The Ui for user interface interactions.
     */
    private static String handleMarkAsDone(String[] tokens, TaskList tasks, Ui ui) {
        try {
            int taskIndex = Integer.parseInt(tokens[1]) - 1;
            Task task = tasks.getTasks().get(taskIndex);
            task.markAsDone();
            return ui.showTaskMarkedAsDone(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showError("OOPS!!! Please provide a valid task number.");
        }
    }

    /**
     * Handles the "unmark" command to mark a task as not done.
     *
     * @param tokens The array of tokens from the user input.
     * @param tasks  The TaskList containing the tasks.
     * @param ui     The Ui for user interface interactions.
     */
    private static String handleMarkAsNotDone(String[] tokens, TaskList tasks, Ui ui) {
        try {
            int taskIndex = Integer.parseInt(tokens[1]) - 1;
            Task task = tasks.getTasks().get(taskIndex);
            task.markAsNotDone();
            return ui.showTaskMarkedAsNotDone(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showError("OOPS!!! Please provide a valid task number.");
        }
    }

    /**
     * Handles the "todo" command to add a todo task.
     *
     * @param tokens The array of tokens from the user input.
     * @param tasks  The TaskList containing the tasks.
     * @param ui     The Ui for user interface interactions.
     */
    private static String handleTodoTask(String[] tokens, TaskList tasks, Ui ui) {
        try {
            String description = tokens[1].trim();
            if (description.isEmpty()) {
                throw new ArtemisException("OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.addTask(new Todo(description));
            return ui.showTaskAdded(tasks.getTasks().size(), tasks.getTasks().get(tasks.getTasks().size() - 1));
        } catch (ArtemisException | IndexOutOfBoundsException e) {
            return ui.showError("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Handles the "deadline" command to add a deadline task.
     *
     * @param input The user input containing the deadline details.
     * @param tasks The TaskList containing the tasks.
     * @param ui    The Ui for user interface interactions.
     */
    private static String handleDeadlineTask(String input, TaskList tasks, Ui ui) {
        try {
            String[] tokens = input.split("/by");
            if (tokens.length < 2) {
                throw new ArtemisException("Invalid deadline format. "
                        + "Please use: deadline [description] /by [dd-mm-yyyy hhmm]");
            }

            String description = tokens[0].replace("deadline ", "").trim();
            String by = tokens[1].trim();
            tasks.addTask(new Deadline(description, by));
            return ui.showTaskAdded(tasks.getTasks().size(), tasks.getTasks().get(tasks.getTasks().size() - 1));
        } catch (ArtemisException | DateTimeParseException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the "event" command to add an event task.
     *
     * @param input The user input containing the event details.
     * @param tasks The TaskList containing the tasks.
     * @param ui    The Ui for user interface interactions.
     */
    private static String handleEventTask(String input, TaskList tasks, Ui ui) {
        try {
            String[] tokens = input.split("/from");
            if (tokens.length < 2) {
                throw new ArtemisException("Invalid event format. "
                        + "Please use: event [description] /from [dd-mm-yyyy hhmm] /to [dd-mm-yyyy hhmm]");
            }

            String description = tokens[0].replace("event ", "").trim();
            String[] fromTo = tokens[1].split("/to");
            if (fromTo.length < 2) {
                throw new ArtemisException("Invalid event format. "
                        + "Please use: event [description] /from [dd-mm-yyyy hhmm] /to [dd-mm-yyyy hhmm]");
            }

            String from = fromTo[0].trim();
            String to = fromTo[1].trim();
            tasks.addTask(new Event(description, from, to));
            return ui.showTaskAdded(tasks.getTasks().size(), tasks.getTasks().get(tasks.getTasks().size() - 1));
        } catch (ArtemisException | DateTimeParseException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the "delete" command to delete a task.
     *
     * @param tokens The array of tokens from the user input.
     * @param tasks  The TaskList containing the tasks.
     * @param ui     The Ui for user interface interactions.
     */
    private static String handleDeleteTask(String[] tokens, TaskList tasks, Ui ui) {
        try {
            int taskIndex = Integer.parseInt(tokens[1]) - 1;
            Task task = tasks.getTasks().get(taskIndex);
            tasks.deleteTask(taskIndex);
            return ui.showTaskDelete(task, tasks.getTasks().size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.showError("OOPS!!! Please provide a valid task number.");
        }
    }
}
