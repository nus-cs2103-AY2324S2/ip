package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class makes sense of the user's commands.
 */
public class Parser {
    private Ui ui;

    /**
     * A constructor that assigns a UI to the parser.
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * A method to parse the user command 'exit'.
     *
     * @param storage The file we want to save our task list to.
     * @param taskList The list of tasks we want to save.
     * @return A message indicating that the user has exited.
     */
    public String parseExit(Storage storage, TaskList taskList) {
        storage.saveFile(taskList);

        return ui.showExitMessage();
    }

    /**
     * A method to parse the user command 'list'.
     *
     * @param taskList The list of tasks that we want to output.
     * @return A string representing the output.
     */
    public String parseList(TaskList taskList) {
        int size = taskList.size();

        String text = ui.showTasksInListMessage();

        for (int count = 0; count < size; count++) {
            text = text + (count + 1) + "." + taskList.getTask(count) + "\n";
        }

        return text;
    }

    /**
     * A method to parse the user command 'clear'.
     *
     * @param taskList The list of tasks that we want to clear.
     * @return A string representing the output.
     */
    public String parseClear(TaskList taskList) {
        taskList.clearTasks();

        return ui.showClearMessage();
    }

    /**
     * A method to parse the user command 'mark'.
     *
     * @param input A string representing the input.
     * @param taskList The list of tasks that we want to mark a task.
     * @return A string representing the output.
     */
    public String parseMark(String input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task task = taskList.getTask(index);
            task.markAsDone();

            return ui.showMarkTaskMessage(task);
        }

        catch (IndexOutOfBoundsException e) {
            return ui.showNoSuchTaskMessage();
        }
    }

    /**
     * A method to parse the user command 'unmark'.
     *
     * @param input A string representing the input.
     * @param taskList The list of tasks that we want to unmark a task.
     * @return A string representing the output.
     */
    public String parseUnmark(String input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task task = taskList.getTask(index);
            task.markAsUndone();

            return ui.showUnmarkTaskMessage(task);
        }

        catch (IndexOutOfBoundsException e) {
            return ui.showNoSuchTaskMessage();
        }
    }

    /**
     * A method to parse the new deadline task.
     *
     * @param input A string representing the input.
     * @param taskList The list of tasks that we want to add a task to.
     * @return A string representing the output.
     */
    public String parseDeadline(String input, TaskList taskList) {
        try {
            int byIndex = input.indexOf("/by ");
            String taskDescription = input.substring(9, byIndex).trim();
            String deadlineDate = input.substring(byIndex + 4).trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(deadlineDate, formatter);

            Task newTask = new Deadline(taskDescription, dateTime);
            taskList.addTask(newTask);
            return ui.showDeadlineMessage(newTask, taskList.size());
        }

        catch (DateTimeParseException e) {
            return ui.showDateTimeFormatErrorMessage();
        }
    }

    /**
     * A method to parse the new todo task.
     *
     * @param input A string representing the input.
     * @param taskList The list of tasks that we want to add a task to.
     * @return A string representing the output.
     */
    public String parseTodo(String input, TaskList taskList) {
        String taskDescription = input.substring(5);
        Task newTask = new ToDo(taskDescription);
        taskList.addTask(newTask);

        return ui.showTodoMessage(newTask, taskList.size());
    }

    /**
     * A method to parse the new event task.
     *
     * @return A string representing the output.
     */
    public String parseEvent(String input, TaskList taskList) {
        int fromIndex = input.indexOf("/from ");
        int toIndex = input.indexOf("/to ");
        String taskDescription = input.substring(6, fromIndex).trim();
        String fromDate = input.substring(fromIndex + 6, toIndex).trim();
        String toDate = input.substring(toIndex + 4).trim();

        Task newTask = new Event(taskDescription, fromDate, toDate);
        taskList.addTask(newTask);

        return ui.showEventMessage(newTask, taskList.size());
    }

    /**
     * A method to parse the user command 'delete'.
     *
     * @param input A string representing the input.
     * @param taskList The list of tasks that we want to delete from.
     * @return A string representing the output.
     */
    public String parseDelete(String input, TaskList taskList) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.getTask(index);
        taskList.removeTask(index);

        return ui.showDeleteMessage(task, taskList.size());
    }

    /**
     * A method to parse the user command 'find'.
     *
     * @param input A string represnting the input.
     * @param taskList The list of tasks that we want to find from.
     * @return A string representing the output.
     */
    public String parseFind(String input, TaskList taskList) {
        String keyword = input.substring(5).trim();

        TaskList matchingTasks = new TaskList();

        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return ui.showNoMatchingTasksMessage();
        } else {
            int size = matchingTasks.size();

            String text = ui.showMatchingTasksInListMessage();

            for (int count = 0; count < size; count++) {
                text = text + (count + 1) + "." + matchingTasks.getTask(count) + "\n";
            }

            return text;
        }
    }
}
