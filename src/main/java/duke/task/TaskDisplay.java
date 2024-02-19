package duke.task;

import java.util.List;

/**
 * The Task class represents a general task with a description and completion status.
 * It is an abstract class that serves as the base class for specific task types such as Todo, Deadline, and Event.
 */
public class TaskDisplay {

    /**
     * Displays tasks based on the user command and task list.
     *
     * @param taskList The list of tasks to display.
     * @param input    The user input command.
     * @return A string containing the messages.
     */
    public String displayTasks(List<Task> taskList, String input) {
        StringBuilder message = new StringBuilder();
        String[] tokens = input.split(" ");
        String command = tokens[0].toLowerCase();

        if (!command.equalsIgnoreCase("lst") && tokens.length == 1) {
            // Ensure that the command is valid and has additional arguments
            //assert false : "Invalid command or missing arguments";
            message.append(printErrorMessage(input));
            return message.toString();
        }

        int index;
        switch (command) {
            case "lst":
                message.append(printTaskList(taskList));
                break;
            case "m":
                assert tokens.length >= 2 : "Invalid mark command syntax";
                index = Integer.parseInt(tokens[1]) - 1;
                message.append(printMarkTask(taskList, index));
                break;
            case "um":
                assert tokens.length >= 2 : "Invalid unmark command syntax";
                index = Integer.parseInt(tokens[1]) - 1;
                message.append(printUnmarkTask(taskList, index));
                break;
            case "d":
                assert tokens.length >= 2 : "Invalid delete command syntax";
                index = Integer.parseInt(tokens[1]) - 1;
                message.append(printDeletedTask(taskList, index));
                break;
            default:
                index = taskList.size() - 1;
                message.append(printAddedTask(taskList, index));
                break;
        }

        return message.toString();
    }

    /**
     * Prints the list of tasks with their icons, status, and descriptions.
     *
     * @param taskList The list of tasks to print.
     * @return A string containing the messages.
     */
    private String printTaskList(List<Task> taskList) {
        StringBuilder message = new StringBuilder();
        if (taskList.isEmpty()) {
            message.append("Your task list is empty :(\n Please add some tasks!");
        } else {
            message.append("Here are the tasks in your list:\n");
            int count = 1;
            for (Task task : taskList) {
                message.append("   ").append(count).append(". ")
                        .append(task.getTaskIcon()).append(task.getStatusIcon()).append(" ")
                        .append(task.getTaskDescription()).append("\n");
                count++;
            }
        }
        return message.toString();
    }

    public String printFindTaskList(List<Task> taskList) {
        StringBuilder message = new StringBuilder();
        message.append("Here are the matching tasks in your list:\n");
        int count = 1;
        for (Task task : taskList) {
            message.append("   ").append(count).append(". ")
                    .append(task.getTaskIcon()).append(task.getStatusIcon()).append(" ")
                    .append(task.getTaskDescription()).append("\n");
            count++;
        }
        return message.toString();
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the task in the list.
     * @return A string containing the message.
     */
    private String printMarkTask(List<Task> taskList, int index) {
        StringBuilder message = new StringBuilder();
        if (index < 0 || index >= taskList.size()) {
            return message.toString();
        }
        Task task = taskList.get(index);
        message.append("Nice! I've marked this task as done:\n")
                .append("   ").append(task.getTaskIcon())
                .append(task.getStatusIcon()).append(task.getTaskDescription()).append("\n");
        return message.toString();
    }

    /**
     * Prints a message indicating that a task has been marked as not done yet.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the task in the list.
     * @return A string containing the message.
     */
    private String printUnmarkTask(List<Task> taskList, int index) {
        StringBuilder message = new StringBuilder();
        if (index < 0 || index >= taskList.size()) {
            return message.toString();
        }
        Task task = taskList.get(index);
        message.append("OK, I've marked this task as not done yet:\n")
                .append("   ").append(task.getTaskIcon())
                .append(task.getStatusIcon()).append(task.getTaskDescription()).append("\n");
        return message.toString();
    }


    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the task in the list.
     * @return A string containing the message.
     */
    private String printDeletedTask(List<Task> taskList, int index) {
        StringBuilder message = new StringBuilder();
        if (index < 0 || index >= taskList.size()) {
            message.append("Sorry, I believe the TASK NUMBER you specified doesn't exist.");
            return message.toString();
        }
        Task deletedTask = taskList.get(index);
        message.append("Noted. I've removed this task:\n")
                .append("     ").append(deletedTask.getTaskIcon())
                .append(deletedTask.getStatusIcon()).append(deletedTask.getTaskDescription()).append("\n")
                .append("  Now you have ").append(taskList.size() - 1)
                .append(" tasks left in this list.\n");
        return message.toString();
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the task in the list.
     * @return A string containing the message.
     */
    private String printAddedTask(List<Task> taskList, int index) {
        StringBuilder message = new StringBuilder();
        if (index < 0 || index >= taskList.size()) {
            return message.toString();
        }
        Task task = taskList.get(index);
        message.append("Got it. I've added this task: ")
                .append(task.getTaskDescription()).append("\n")
                .append("   ").append(task.getTaskIcon())
                .append(task.getStatusIcon()).append(task.getTaskDescription()).append("\n")
                .append("Now you have ").append(taskList.size())
                .append(" tasks in the list.\n");
        return message.toString();
    }

    /**
     * Prints an error message for an invalid user command.
     *
     * @param input The user input command.
     * @return A string containing the message.
     */
    public String printErrorMessage(String input) {
        StringBuilder message = new StringBuilder();
        message.append("Sorry, the description of ")
                .append(input.toUpperCase())
                .append(" cannot be empty. Please add details, so that I can assist you better!");
        return message.toString();
    }
}
