package duke;

import java.util.ArrayList;

/**
 * Handles user commands and performs corresponding actions on the task list.
 */
public class CommandHandler {

    /**
     * Handles the user input command.
     *
     * @param userInput The user input command.
     * @param tasks     The task list to be modified.
     * @throws DukeException If there is an issue handling the command.
     */
    public static void handleCommand(String userInput, TaskList tasks) throws DukeException {
        String[] parsedCommand = Parser.parse(userInput);
        String commandType = parsedCommand[0].toLowerCase();

        switch (commandType) {
            case "bye":
                Ui.showGoodbye();
                System.exit(0);
                break;
            case "list":
                listTasks(tasks);
                break;
            case "todo":
                handleTodoCommand(parsedCommand, tasks);
                break;
            case "deadline":
                handleDeadlineCommand(userInput, tasks);
                break;
            case "event":
                handleEventCommand(userInput, tasks);
                break;
            case "delete":
                handleDeleteCommand(parsedCommand, tasks);
                break;
            case "mark":
                handleMarkCommand(parsedCommand, tasks);
                break;
            case "unmark":
                handleUnmarkCommand(parsedCommand, tasks);
                break;
            default:
                Ui.showInvalidCommand();
                break;
        }
    }

    private static void listTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here " + (tasks.size() == 1 ? "is the task" : "are the tasks") + " in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).getStatusIcon());
            }
        }
    }

    private static void handleTodoCommand(String[] parsedCommand, TaskList tasks) throws DukeException {
        if (parsedCommand.length < 2) {
            throw new DukeException("Umm... The todo command is incomplete!");
        }
        String description = parsedCommand[1];
        ToDo.addToDoTask(tasks, description);
    }

    private static void handleDeadlineCommand(String userInput, TaskList taskList) throws DukeException {
        if (userInput.length() <= 9) {
            throw new DukeException("Oops! The deadline command is incomplete.");
        }

        String[] descriptionAndDueBy = Parser.parseDeadline(userInput);

        if (descriptionAndDueBy.length < 2) {
            throw new DukeException("Oops! Both description and deadline are required for a deadline task.");
        }

        String dueBy = descriptionAndDueBy[1];
        Deadline.addDeadlineTask(taskList, descriptionAndDueBy[0], dueBy);
    }

    private static void handleEventCommand(String userInput, TaskList taskList) throws DukeException {
        if (userInput.length() <= 6) {
            throw new DukeException("Uh oh! The event command is incomplete.");
        }

        String[] description = Parser.parseEvent(userInput);

        if (description.length < 3) {
            throw new DukeException("Uh oh! The event command requires both start and end details.");
        }

        String start = description[1];
        String end = description[2];
        Event.addEventTask(taskList, description[0], start, end);
    }

    private static void handleDeleteCommand(String[] parsedCommand, TaskList tasks) throws DukeException {
        int index = parseIndex(parsedCommand);

        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Oops! Invalid delete command. Please provide a valid task number.");
        }

        Task removedTask = tasks.deleteTask(index);
    }

    private static void handleMarkCommand(String[] parsedCommand, TaskList taskList) throws DukeException {
        int index = parseIndex(parsedCommand);
        taskList.markTaskAsDone(index);
    }

    private static void handleUnmarkCommand(String[] parsedCommand, TaskList taskList) throws DukeException {
        int index = parseIndex(parsedCommand);
        taskList.markTaskAsNotDone(index);
    }
    private static int parseIndex(String[] parsedCommand) throws DukeException {
        try {
            return Integer.parseInt(parsedCommand[1].trim()) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid delete command. Please provide a valid task number.");
        }
    }
}
