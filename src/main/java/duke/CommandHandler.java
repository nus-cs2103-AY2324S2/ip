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
     * @param taskList The task list to be modified.
     * @throws GeorgieException If there is an issue handling the command.
     */
    public static String handleCommand(String userInput, TaskList taskList) throws GeorgieException {
        String[] parsedCommand = Parser.parse(userInput);
        String commandType = parsedCommand[0].toLowerCase();

        switch (commandType) {
            case "bye":
                return Ui.showGoodbye();
            case "list":
                return listTasks(taskList);
            case "todo":
                return handleTodoCommand(parsedCommand, taskList);
            case "deadline":
                return handleDeadlineCommand(userInput, taskList);
            case "event":
                return handleEventCommand(userInput, taskList);
            case "delete":
                return handleDeleteCommand(parsedCommand, taskList);
            case "mark":
                return handleMarkCommand(parsedCommand, taskList);
            case "unmark":
                return handleUnmarkCommand(parsedCommand, taskList);
            case "find":
                return handleFindCommand(parsedCommand, taskList);
            default:
                assert false : "Unhandled command type";
                return Ui.showInvalidCommand();
        }
    }

    private static String listTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            return "There are no tasks in your list.";
        } else {
            assert tasks != null : "Tasks list is null";
            StringBuilder result = new StringBuilder("Here " + (tasks.size() == 1 ? "is the task" : "are the tasks") + " in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                assert tasks.get(i) != null : "Task at index " + i + " is null";
                result.append((i + 1)).append(".").append(tasks.get(i).getStatusIcon()).append("\n");
            }
            return result.toString();
        }
    }

    private static String handleTodoCommand(String[] parsedCommand, TaskList taskList) throws GeorgieException {
        if (parsedCommand.length < 2) {
            assert false : "Todo command is incomplete";
            throw new GeorgieException("Umm... The todo command is incomplete!");
        }
        String description = parsedCommand[1];
        assert taskList != null : "Task list is null";
        return ToDo.addToDoTask(taskList, description);
    }

    private static String handleDeadlineCommand(String userInput, TaskList taskList) throws GeorgieException {
        if (userInput.length() <= 9) {
            assert false : "Deadline command is incomplete";
            throw new GeorgieException("Oops! The deadline command is incomplete.");
        }

        String[] descriptionAndDueBy = Parser.parseDeadline(userInput);
        assert descriptionAndDueBy.length >= 2 : "Invalid deadline command format";

        if (descriptionAndDueBy.length < 2) {
            throw new GeorgieException("Oops! Both description and deadline are required for a deadline task.");
        }

        String dueBy = descriptionAndDueBy[1];
        assert taskList != null : "Task list is null";
        return Deadline.addDeadlineTask(taskList, descriptionAndDueBy[0], dueBy);
    }

    private static String handleEventCommand(String userInput, TaskList taskList) throws GeorgieException {
        if (userInput.length() <= 6) {
            assert false : "Event command is incomplete";
            throw new GeorgieException("Uh oh! The event command is incomplete.");
        }

        String[] description = Parser.parseEvent(userInput);

        if (description.length < 3) {
            throw new GeorgieException("Uh oh! The event command requires both start and end details.");
        }

        assert description.length >= 3 : "Invalid event command format";
        String start = description[1];
        String end = description[2];
        assert taskList != null : "Task list is null";
        return Event.addEventTask(taskList, description[0], start, end);
    }

    private static String handleDeleteCommand(String[] parsedCommand, TaskList taskList) throws GeorgieException {
        int index = parseIndex(parsedCommand);
        if (index < 0 || index >= taskList.size()) {
            assert false : "Invalid delete command. Index out of bounds";
            throw new GeorgieException("Oops! Invalid delete command. Please provide a valid task number.");
        }
        assert taskList != null : "Task list is null";
        return taskList.deleteTask(index);
    }

    private static String handleMarkCommand(String[] parsedCommand, TaskList taskList) throws GeorgieException {
        if (parsedCommand.length < 2) {
            assert false : "Mark command is incomplete";
            throw new GeorgieException("Erm... Invalid mark command. Please provide a task number.");
        }
        int index = parseIndex(parsedCommand);
        if (index < 0 || index >= taskList.size()) {
            assert false : "Invalid mark command. Index out of bounds";
            throw new GeorgieException("Erm... Invalid mark command. Please provide a valid task number.");
        }
        assert taskList != null : "Task list is null";
        return taskList.markTaskAsDone(index);
    }

    private static String handleUnmarkCommand(String[] parsedCommand, TaskList taskList) throws GeorgieException {
        if (parsedCommand.length < 2) {
            assert false : "Unmark command is incomplete";
            throw new GeorgieException("Erm... Invalid unmark command. Please provide a task number.");
        }
        int index = parseIndex(parsedCommand);
        if (index < 0 || index >= taskList.size()) {
            assert false : "Invalid unmark command. Index out of bounds";
            throw new GeorgieException("Erm... Invalid unmark command. Please provide a valid task number.");
        }
        assert taskList != null : "Task list is null";
        return taskList.markTaskAsNotDone(index);
    }

    private static String handleFindCommand(String[] parsedCommand, TaskList taskList) throws GeorgieException {
        if (parsedCommand.length < 2) {
            assert false : "Find command is incomplete";
            throw new GeorgieException("Umm... The find command is incomplete!");
        }
        String keyword = parsedCommand[1];
        ArrayList<Task> matchingTasks = taskList.findTasks(keyword);
        assert matchingTasks != null : "Matching tasks list is null";

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                assert matchingTasks.get(i) != null : "Matching task at index " + i + " is null";
                result.append((i + 1)).append(".").append(matchingTasks.get(i).getStatusIcon()).append("\n");
            }
            return result.toString();
        }
    }

    private static int parseIndex(String[] parsedCommand) throws GeorgieException {
        try {
            return Integer.parseInt(parsedCommand[1].trim()) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new GeorgieException("Invalid delete command. Please provide a valid task number.");
        }
    }
}
