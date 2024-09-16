package talkingjohn;

import java.util.List;
import java.util.Objects;

/**
 * Provides methods to perform various actions on the tasks.
 * It interacts with the user interface to display messages and handles user inputs.
 */
public class TaskList {
    private final List<Task> taskArr;
    private final Ui ui;

    /**
     * Constructs a TaskList object with the specified list of tasks and user interface.
     *
     * @param taskArr The list of tasks.
     * @param ui      The user interface.
     */
    public TaskList(List<Task> taskArr, Ui ui) {
        this.taskArr = taskArr;
        this.ui = ui;
    }

    /**
     * Converts a string input to an integer.
     *
     * @param input The input string.
     * @return The integer value parsed from the input.
     */
    public int convertInt(String input) {
        assert input != null : "user input cannot be null";
        return Integer.parseInt(input.split(" ", 2)[1]);
    }

    /**
     * Handles the user input by performing corresponding actions on the task list.
     *
     * @param input The user input.
     */
    public String action(String input) {
        assert input != null : "user input cannot be null";
        if (Objects.equals(input, "list")) {
            return handleListCommand();
        } else if (input.startsWith("delete") && input.length() > 6) {
            return handleDeleteCommand(input);
        } else if (input.startsWith("mark") && input.length() > 4) {
            return handleMarkCommand(input);
        } else if (input.startsWith("unmark") && input.length() > 6) {
            return handleUnmarkCommand(input);
        } else if (input.startsWith("todo") && input.length() > 4 && input.charAt(4) == ' ') {
            return handleTodoCommand(input);
        } else if (input.startsWith("deadline") && input.length() > 8) {
            return handleDeadlineCommand(input);
        } else if (input.startsWith("event") && input.length() > 5) {
            return handleEventCommand(input);
        } else if (input.startsWith("find") && input.length() > 4) {
            return handleFindCommand(input);
        } else {
            return ui.invalidInput();
        }
    }

    /**
     * Handles the 'list' command to display the list of tasks.
     *
     * @return The response message containing the list of tasks.
     */
    private String handleListCommand() {
        if (!taskArr.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Here is the list of tasks!\n");
            for (int i = 0; i < taskArr.size(); i++) {
                sb.append((i + 1) + ". " + taskArr.get(i) + "\n");
            }
            return sb.toString();
        } else {
            return ui.emptyInput("list");
        }
    }

    /**
     * Handles the 'delete' command to delete a task from the list.
     *
     * @param input The user input containing the task index to delete.
     * @return The response message indicating the deletion status.
     */
    private String handleDeleteCommand(String input) {
        assert input != null : "user input cannot be null";
        try {
            int i = convertInt(input);
            Task deleted = taskArr.remove(i - 1);
            int size = taskArr.size();
            return ui.deleteSuccess(deleted, size);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.invalidInput("delete");
        }
    }

    /**
     * Handles the 'mark' command to mark a task as done.
     *
     * @param input The user input containing the task index to mark.
     * @return The response message indicating the marking status.
     */
    private String handleMarkCommand(String input) {
        assert input != null : "user input cannot be null";
        try {
            int i = convertInt(input);
            Task taskToMark = taskArr.get(i - 1);
            taskToMark.mark();
            return ui.markTask(taskToMark);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.invalidInput("mark");
        }
    }

    /**
     * Handles the 'unmark' command to unmark a task as not done.
     *
     * @param input The user input containing the task index to unmark.
     * @return The response message indicating the unmarking status.
     */
    private String handleUnmarkCommand(String input) {
        assert input != null : "user input cannot be null";
        try {
            int i = convertInt(input);
            Task taskToUnmark = taskArr.get(i - 1);
            taskToUnmark.unMark();
            return ui.unmarkTask(taskToUnmark);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return ui.invalidInput("unmark");
        }
    }

    /**
     * Handles the 'todo' command to add a Todo task to the list.
     *
     * @param input The user input containing the Todo task description and optional notes.
     * @return The response message indicating the addition status.
     */
    private String handleTodoCommand(String input) {
        assert input != null : "user input cannot be null";
        if (input.contains(":")) {
            String check = input.split(" ", 2)[1];
            String[] buffer = check.split(":", 2);
            String whatToDo = buffer[0];
            String note = buffer[1];
            Todo toDo = new Todo(whatToDo, note);
            taskArr.add(toDo);
            return ui.addTask(toDo, taskArr.size());
        } else {
            String whatToDo = input.split(" ", 2)[1];
            Todo toDo = new Todo(whatToDo);
            taskArr.add(toDo);
            return ui.addTask(toDo, taskArr.size());
        }

    }

    /**
     * Handles the 'deadline' command to add a Deadline task to the list.
     *
     * @param input The user input containing the Deadline task description, notes, and timing.
     * @return The response message indicating the addition status.
     */
    private String handleDeadlineCommand(String input) {
        assert input != null : "user input cannot be null";
        try {
            String[] parts = input.split(" ", 2);
            if (input.contains(":")) {
                String buffer = parts[1];
                String[] secPart = buffer.split("/", 2);
                String descriptionAndNotes = secPart[0];
                String[] dAndN = descriptionAndNotes.split(":", 2);
                Deadline deadline = new Deadline(dAndN[0], dAndN[1], secPart[1]);
                taskArr.add(deadline);
                return ui.addTask(deadline, taskArr.size());
            } else {
                String buffer = parts[1];
                String[] secPart = buffer.split("/", 2);
                Deadline deadline = new Deadline(secPart[0], secPart[1]);
                taskArr.add(deadline);
                return ui.addTask(deadline, taskArr.size());
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.invalidFormat();
        }
    }

    /**
     * Handles the 'event' command to add an Event task to the list.
     *
     * @param input The user input containing the Event task description, notes, and timing.
     * @return The response message indicating the addition status.
     */
    private String handleEventCommand(String input) {
        assert input != null : "user input cannot be null";
        try {
            String[] parts = input.split(" ", 2);
            if (input.contains(":")) {
                String buffer = parts[1];
                String[] secPart = buffer.split("/", 3);
                String descriptionAndNotes = secPart[0];
                String[] dAndN = descriptionAndNotes.split(":", 2);
                Event event = new Event(dAndN[0], dAndN[1], secPart[1], secPart[2]);
                taskArr.add(event);
                return ui.addTask(event, taskArr.size());
            } else {
                String buffer = parts[1];
                String[] secPart = buffer.split("/", 3);
                Event event = new Event(secPart[0], secPart[1], secPart[2]);
                taskArr.add(event);
                return ui.addTask(event, taskArr.size());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.invalidFormat();
        }
    }

    /**
     * Handles the 'find' command to search for tasks containing a specific keyword.
     *
     * @param input The user input containing the keyword to search for.
     * @return The response message containing the tasks found with the keyword.
     */
    private String handleFindCommand(String input) {
        assert input != null : "user input cannot be null";
        try {
            String[] parts = input.split(" ", 2);
            String toFind = parts[1];
            int i = 1;
            StringBuilder sb = new StringBuilder();

            for (Task task : taskArr) {
                if (task.toString().contains(toFind)) {
                    sb.append((i) + ". " + task + "\n");
                    i++;
                }
            }
            String findTask = sb.toString();
            if (findTask.isEmpty()) {
                return "Oops! No such keyword found!";
            } else {
                return findTask;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.invalidFormat();
        }
    }
}