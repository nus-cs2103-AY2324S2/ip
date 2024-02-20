package unim.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import unim.io.Storage;
import unim.io.Ui;
import unim.task.Deadline;
import unim.task.Event;
import unim.task.Task;
import unim.task.Todo;

/**
 * The Parser class is responsible for parsing user input and executing corresponding commands.
 * It handles various commands such as listing tasks, marking/unmarking tasks, adding todos,
 * deadlines, and events, removing tasks, and exiting the application.
 */
public class Parser {

    /**
     * Parses the user input into an array of strings, separating the command and the remaining input.
     *
     * @param input The user input to be parsed.
     * @return An array containing the command and the remaining input.
     */
    public static String[] parseInput(String input) {
        assert input != null : "Input cannot be null";
        return input.split(" ", 2);
    }

    /**
     * Handles the execution of commands based on the parsed user input.
     *
     * @param input    The user input to be processed.
     * @param taskList The task list to be manipulated.
     * @return A string result of the command execution.
     */
    public String handleCommand(String input, TaskList taskList) {
        String[] parsedInput = Parser.parseInput(input);
        assert parsedInput.length >= 1 : "Parsed input should have at least one element";
        if ("list".equalsIgnoreCase(input)) {
            return Ui.showTaskList(taskList.getTaskList());
        } else if ("bye".equalsIgnoreCase(input)) {
            Storage.saveTasks(taskList.getTaskList());
            return Ui.showByeMessage();
        } else if (parsedInput[0].equalsIgnoreCase("mark")
                || parsedInput[0].equalsIgnoreCase("unmark")) {
            return markingHandler(input, taskList);
        } else if (parsedInput[0].equalsIgnoreCase("deadline")) {
            if (isDeadlineInput(input)) {
                return handleDeadlines(input, taskList);
            } else {
                return Ui.showErrorMessage("Please complete your request by specifying the details of the task!");
            }
        } else if (parsedInput[0].equalsIgnoreCase("todo")) {
            if (isTodoInput(input)) {
                return handleTodos(input, taskList);
            } else {
                return Ui.showErrorMessage("Please complete your request by specifying the details of the task!");
            }
        } else if (parsedInput[0].equalsIgnoreCase("event")) {
            if (isEventInput(input)) {
                return handleEvents(input, taskList);
            } else {
                return Ui.showErrorMessage("Please complete your request by specifying the details of the task!");
            }
        } else if (parsedInput[0].equalsIgnoreCase("delete")) {
            return handleRemove(input, taskList);
        } else if (parsedInput[0].equalsIgnoreCase("find")) {
            return findItems(input, taskList);
        } else {
            return Ui.showErrorForInput();
        }
    }

    /**
     * Handles the marking or unmarking of tasks based on user input.
     *
     * @param input    The user input specifying the task number and the action.
     * @param taskList The task list to be manipulated.
     */
    public static String markingHandler(String input, TaskList taskList) {
        String[] split = input.split(" ");

        if (split.length < 2) {
            return Ui.showErrorMessage("Please specify the task number! (e.g. mark 2)");
        }

        try {
            int index = Integer.parseInt(split[1]) - 1;
            Task task = taskList.getTaskList().get(index);

            if ("mark".equalsIgnoreCase(split[0])) {
                task.markAsDone();
                Storage.saveTasks(taskList.getTaskList());
                return Ui.showMarkedAsDone(task);
            } else if ("unmark".equalsIgnoreCase(split[0])) {
                task.unmarkTask();
                Storage.saveTasks(taskList.getTaskList());
                return Ui.showUnmarkedTask(task);
            }

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return Ui.showErrorMessage("Invalid task number. Please check your to-do list again (use 'list').");
        }
        return Ui.showErrorMessage("Invalid input for mark. Try putting e.g. mark 2");
    }

    /**
     * Handles the creation and addition of Todo tasks to the task list.
     *
     * @param input    User input specifying the Todo description.
     * @param taskList The task list to which the Todo task will be added.
     */
    private static String handleTodos(String input, TaskList taskList) {
        String description = input.substring(5).trim();
        System.out.println("Debug: Description before checking for duplicates: " + description);

        if (isDuplicateTask(description, taskList)) {
            return Ui.showErrorMessage("Duplicate task found. Task was not added, it's already there!");
        }

        Todo todo = new Todo(description);
        taskList.addTask(todo);
        Storage.saveTasks(taskList.getTaskList());
        return Ui.showTodoAdded(todo, taskList.getTotalTasks());
    }

    /**
     * Handles the creation and addition of Deadline tasks to the task list.
     *
     * @param input    User input specifying the Deadline description and due date.
     * @param taskList The task list to which the Deadline task will be added.
     */
    private static String handleDeadlines(String input, TaskList taskList) {
        String[] splitParts = input.substring(9).split("/by", 2);

        if (splitParts.length > 1) {
            String description = splitParts[0].trim();
            String date = splitParts[1].trim();

            if (isDuplicateTask(description, taskList)) {
                return Ui.showErrorMessage("Duplicate task found. Task was not added, it's already there!");
            }

            if (isValidDate(date)) {
                LocalDate d1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
                Deadline deadline = new Deadline(description, d1);
                taskList.addTask(deadline);
                Storage.saveTasks(taskList.getTaskList());
                return Ui.showDeadlineAdded(deadline, taskList.getTotalTasks());
            } else {
                Deadline deadline = new Deadline(description, date);
                taskList.addTask(deadline);
                Storage.saveTasks(taskList.getTaskList());
                return Ui.showDeadlineAdded(deadline, taskList.getTotalTasks());
            }
        } else {
            return Ui.showErrorMessage("Invalid input format for deadline. Please follow this format: .");
        }
    }

    /**
     * Handles the creation and addition of Event tasks to the task list.
     *
     * @param input    User input specifying the Event description and date range.
     * @param taskList The task list to which the Event task will be added.
     */
    private static String handleEvents(String input, TaskList taskList) {
        String[] splitParts = input.substring(6).split("/from", 2);

        if (splitParts.length > 1) {
            String description = splitParts[0].trim();
            String dateRange = splitParts[1].trim();

            String[] dateParts = dateRange.split("/to", 2);

            if (dateParts.length > 1) {
                String fromDate = dateParts[0].trim();
                String toDate = dateParts[1].trim();

                if (isDuplicateTask(description, taskList)) {
                    return Ui.showErrorMessage("Duplicate task found. Task not added, it's already there!");
                }

                if (isValidDate(fromDate) && isValidDate(toDate)) {
                    LocalDate d1 = LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
                    LocalDate d2 = LocalDate.parse(toDate, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
                    Event event = new Event(description, d1, d2);
                    taskList.addTask(event);
                    Storage.saveTasks(taskList.getTaskList());
                    return Ui.showEventAdded(event, taskList.getTotalTasks());
                } else {
                    return Ui.showErrorMessage("Invalid input format for event. Please provide valid dates.");
                }
            }
        }

        return Ui.showErrorMessage("Invalid input format for event.");
    }

    /**
     * Handles the removal of tasks from the task list based on user input.
     *
     * @param input    User input specifying the task number to be removed.
     * @param taskList The task list from which the task will be removed.
     */
    private static String handleRemove(String input, TaskList taskList) {
        String[] splitParts = input.split(" ");
        if (splitParts.length < 2) {
            System.out.println("Please specify which task number you want to delete!");
        }
        try {
            int index = Integer.parseInt(splitParts[1]) - 1;
            Task removedTask = taskList.removeTask(index);
            Storage.saveTasks(taskList.getTaskList());
            return Ui.showTaskRemoved(removedTask, taskList.getTotalTasks());
        } catch (IndexOutOfBoundsException e) {
            return Ui.showErrorMessage("Invalid task number. Please refer to your to-do list again.");
        } catch (NumberFormatException e) {
            return Ui.showErrorMessage("Invalid input format for delete. Try something like 'delete 1'");
        }
    }

    /**
     * Checks if a task with the given description already exists in the task list.
     *
     * @param description The description of the task to check.
     * @param taskList    The task list to check for duplicates.
     * @return True if a duplicate task is found, false otherwise.
     */
    private static boolean isDuplicateTask(String description, TaskList taskList) {
        for (Task task : taskList.getTaskList()) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles finding items based on a keyword.
     *
     * @param input    User input specifying the keyword for finding tasks.
     * @param tasks The task list to search for tasks.
     * @return A string result or message.
     */
    public String findItems(String input, TaskList tasks) {
        Ui.showFindItemList(input);
        boolean isFound = false;
        String[] splitParts = input.split("find", 2);

        if (splitParts.length > 1) {
            String keyword = splitParts[1].trim();
            StringBuilder foundTasks = new StringBuilder(Ui.showFindItemList(keyword));

            int count = 0;
            for (int i = 0; i < tasks.getTotalTasks(); i++) {
                Task item = tasks.getTask(i);
                if (item.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    count++;
                    foundTasks.append(count).append(". ").append(Ui.showFoundTask(item));
                    isFound = true;
                }
            }
            if (isFound) {
                return foundTasks.toString();
            } else {
                return Ui.showErrorMessage("No tasks found containing: " + keyword);
            }
        } else {
            return Ui.showErrorMessage("Invalid input format for find. Please specify a search keyword.");
        }
    }

    /**
     * Validates the input format for creating Deadlines.
     * @param input User input specifying the Deadline description and due date.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean isDeadlineInput(String input) {
        String[] splitParts = input.substring(9).split("/by", 2);
        return splitParts.length > 1;
    }

    /**
     * Validates the input format for creating Todo tasks.
     * @param input User input specifying the Todo description.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean isTodoInput(String input) {
        return input.length() > 5;
    }

    /**
     * Validates the input format for creating Events.
     * @param input User input specifying the Event description and date range.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean isEventInput(String input) {
        String[] splitParts = input.substring(6).split("/from", 2);
        return splitParts.length > 1;
    }

    /**
     * Validates the input format for a date string.
     *
     * @param input The date string to be validated.
     * @return True if the input is a valid date string, false otherwise.
     */
    private static boolean isValidDate(String input) {
        try {
            LocalDate.parse(input, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
            return true;
        } catch (DateTimeParseException e1) {
            try {
                LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return true;
            } catch (DateTimeParseException e2) {
                return false;
            }

        }
    }

}

