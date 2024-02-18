package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 * This class makes sense of the user's commands.
 */
public class Parser {
    private Ui ui;

    /*
     * A constructor that assigns a UI to the parser.
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /*
     * A method to parse the user command 'exit'.
     */
    public void parseExit(Storage storage, TaskList taskList) {
        ui.showExitMessage();
        storage.saveFile(taskList);
    }

    /*
     * A method to parse the user command 'list'.
     */
    public void parseList(TaskList taskList) {
        int size = taskList.size();

        ui.showTasksInListMessage();

        for (int count = 0; count < size; count++) {
            System.out.println(" " + (count + 1) + "." + taskList.getTask(count));
        }
    }

    /*
     * A method to parse the user command 'clear'.
     */
    public void parseClear(TaskList taskList) {
        taskList.clearTasks();

        ui.showClearMessage();
    }

    /*
     * A method to parse the user command 'mark'.
     */
    public void parseMark(String input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task task = taskList.getTask(index);
            task.markAsDone();

            ui.showMarkTaskMessage(task);
        }

        catch (IndexOutOfBoundsException e) {
            ui.showNoSuchTaskMessage();
        }
    }

    /*
     * A method to parse the user command 'unmark'.
     */
    public void parseUnmark(String input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task task = taskList.getTask(index);
            task.markAsUndone();

            ui.showUnmarkTaskMessage(task);
        }

        catch (IndexOutOfBoundsException e) {
            ui.showNoSuchTaskMessage();
        }
    }

    /*
     * A method to parse the new deadline task.
     */
    public void parseDeadline(String input, TaskList taskList) {
        try {
            int byIndex = input.indexOf("/by ");
            String taskDescription = input.substring(9, byIndex).trim();
            String deadlineDate = input.substring(byIndex + 4).trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(deadlineDate, formatter);

            Task newTask = new Deadline(taskDescription, dateTime);
            taskList.addTask(newTask);
            ui.showDeadlineMessage(newTask, taskList.size());
        }

        catch (DateTimeParseException e) {
            ui.showDateTimeFormatErrorMessage();
        }
    }

    /*
     * A method to parse the new todo task.
     */
    public void parseTodo(String input, TaskList taskList) {
        String taskDescription = input.substring(5);
        Task newTask = new ToDo(taskDescription);
        taskList.addTask(newTask);
        ui.showTodoMessage(newTask, taskList.size());
    }

    /*
     * A method to parse the new event task.
     */
    public void parseEvent(String input, TaskList taskList) {
        int fromIndex = input.indexOf("/from ");
        int toIndex = input.indexOf("/to ");
        String taskDescription = input.substring(6, fromIndex).trim();
        String fromDate = input.substring(fromIndex + 6, toIndex).trim();
        String toDate = input.substring(toIndex + 4).trim();

        Task newTask = new Event(taskDescription, fromDate, toDate);
        taskList.addTask(newTask);
        ui.showEventMessage(newTask, taskList.size());
    }

    /*
     * A method to parse the user command 'delete'.
     */
    public void parseDelete(String input, TaskList taskList) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.getTask(index);
        taskList.removeTask(index);

        ui.showDeleteMessage(task, taskList.size());
    }

    /*
     * A method to parse the user command 'find'.
     */
    public void parseFind(String input, TaskList taskList) {
        String keyword = input.substring(5).trim();

        TaskList matchingTasks = new TaskList();

        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showNoMatchingTasksMessage();
        } else {
            int size = matchingTasks.size();

            ui.showMatchingTasksInListMessage();

            for (int count = 0; count < size; count++) {
                System.out.println(" " + (count + 1) + "." + matchingTasks.getTask(count));
            }
        }
    }
}
