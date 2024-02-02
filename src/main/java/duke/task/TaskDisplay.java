package duke.task;

import java.util.List;

/**
 * The Task class represents a general task with a description and completion status.
 * It is an abstract class that serves as the base class for specific task types such as Todo, Deadline, and Event.
 */
public class TaskDisplay {
    private static final String INDENTATION = "    ";
    private static final String LINE = "    -----------------------------------------------------------------------------------------";


    /**
     * Displays tasks based on the user command and task list.
     *
     * @param taskList The list of tasks to display.
     * @param input    The user input command.
     */
    public void displayTasks(List<Task> taskList, String input) {
        String[] tokens = input.split(" ");
        String command = tokens[0].toLowerCase();

        System.out.println(LINE);

        if (!command.equalsIgnoreCase("list") && tokens.length == 1) {
            printErrorMessage(input);
            return;
        }

        int index;
        switch (command) {
            case "list":
                printTaskList(taskList);
                break;
            case "mark":
                index = Integer.parseInt(tokens[1]) - 1;
                printMarkTask(taskList, index);
                break;
            case "unmark":
                index = Integer.parseInt(tokens[1]) - 1;
                printUnmarkTask(taskList, index);
                break;
            case "delete":
                index = Integer.parseInt(tokens[1]) - 1;
                printDeletedTask(taskList, index);
                break;
            default:
                index = taskList.size() - 1;
                printAddedTask(taskList, index);
                break;
        }

        System.out.println(LINE);
    }

    /**
     * Prints the list of tasks with their icons, status, and descriptions.
     *
     * @param taskList The list of tasks to print.
     */
    private void printTaskList(List<Task> taskList) {
        System.out.println(INDENTATION + " Here are the tasks in your list:");
        int count = 1;
        for (Task task : taskList) {
            System.out.println(INDENTATION + "    " + count + ". " + task.getTaskIcon()  + task.getStatusIcon() + " " + task.getTaskDescription());
            count++;
        }
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the task in the list.
     */
    private void printMarkTask(List<Task> taskList, int index) {
        if (index < 0 || index >= taskList.size()) {
            return;
        }
        Task task = taskList.get(index);
        System.out.println(INDENTATION + "  Nice! I've marked this task as done: ");
        System.out.println(INDENTATION  + "    " + task.getTaskIcon() + task.getStatusIcon() + task.getTaskDescription());
    }

    /**
     * Prints a message indicating that a task has been marked as not done yet.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the task in the list.
     */
    private void printUnmarkTask(List<Task> taskList, int index) {
        if (index < 0 || index >= taskList.size()) {
            return;
        }
        Task task = taskList.get(index);
        System.out.println(INDENTATION + "  OK, I've marked this task as not done yet: ");
        System.out.println(INDENTATION  + "    " + task.getTaskIcon() + task.getStatusIcon() + task.getTaskDescription());
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the task in the list.
     */
    private void printDeletedTask(List<Task> taskList, int index) {
        if (index < 0 || index >= taskList.size()) {
            System.out.println(INDENTATION + "  Sorry, I believe the TASK NUMBER you specified doesn't exist.");
            return;
        }
        Task deletedTask = taskList.get(index);
        System.out.println(INDENTATION + "  Noted. I've removed this task:");
        System.out.println(INDENTATION + "     " + deletedTask.getTaskIcon() + deletedTask.getStatusIcon() + deletedTask.getTaskDescription());
        System.out.println(INDENTATION + "  Now you have " + (taskList.size() - 1) + " tasks left in this list.");
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the task in the list.
     */
    private void printAddedTask(List<Task> taskList, int index) {
        if (index < 0 || index >= taskList.size()) {
            return;
        }
        Task task = taskList.get(index);
        System.out.println(INDENTATION + "  Got it. I've added this task: " + task.getTaskDescription());
        System.out.println(INDENTATION + "    " + task.getTaskIcon() + task.getStatusIcon() + task.getTaskDescription());
        System.out.println(INDENTATION + "  Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints an error message for an invalid user command.
     *
     * @param input The user input command.
     */
    public void printErrorMessage(String input) {
        System.out.println(INDENTATION + "  Sorry, the description of " + input.toUpperCase() + " cannot be empty. Please add details, so that I can");
        System.out.println(INDENTATION + "  assist you better!");
        System.out.println(LINE);
    }
}

