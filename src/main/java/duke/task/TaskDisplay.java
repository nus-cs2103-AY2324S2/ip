package duke.task;

import java.util.List;

public class TaskDisplay {

    /**
     * Displays the entire list of tasks.
     *
     * @param taskList The list of tasks to display.
     * @return A string containing the formatted task list.
     */
    public String displayTaskList(List<Task> taskList) {
        return printTaskList(taskList);
    }

    /**
     * Displays a list of tasks that match a search query.
     *
     * @param taskList The list of matching tasks.
     * @return A string containing the formatted list of matching tasks.
     */
    public String displayFindTaskList(List<Task> taskList) {
        return printFindTaskList(taskList);
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the marked task.
     * @return A string containing the mark task message.
     */
    public String displayMarkTask(List<Task> taskList, int index) {
        return printMarkTask(taskList, index);
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the unmarked task.
     * @return A string containing the unmark task message.
     */
    public String displayUnmarkTask(List<Task> taskList, int index) {
        return printUnmarkTask(taskList, index);
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the deleted task.
     * @return A string containing the delete task message.
     */
    public String displayDeleteTask(List<Task> taskList, int index) {
        return printDeletedTask(taskList, index);
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param taskList The list of tasks.
     * @param index    The index of the added task.
     * @return A string containing the add task message.
     */
    public String displayAddTask(List<Task> taskList, int index) {
        return printAddedTask(taskList, index);
    }

    /**
     * Displays an error message for invalid commands.
     *
     * @param command The user input command that led to the error.
     * @return A string containing the error message.
     */
    public String displayErrorMessage(String command) {
        return printErrorMessage(command);
    }

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

    private String printFindTaskList(List<Task> taskList) {
        StringBuilder message = new StringBuilder();
        if (taskList.isEmpty()) {
            message.append("No matching tasks found.");
        } else {
            message.append("Here are the matching tasks in your list:\n");
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

    private String printMarkTask(List<Task> taskList, int index) {
        StringBuilder message = new StringBuilder();
        if (index < 0 || index >= taskList.size()) {
            return "The task number provided is invalid.";
        }
        Task task = taskList.get(index);
        message.append("Nice! I've marked this task as done:\n")
                .append("   ").append(task.getTaskIcon())
                .append(task.getStatusIcon()).append(" ").append(task.getTaskDescription()).append("\n");
        return message.toString();
    }

    private String printUnmarkTask(List<Task> taskList, int index) {
        StringBuilder message = new StringBuilder();
        if (index < 0 || index >= taskList.size()) {
            return "The task number provided is invalid.";
        }
        Task task = taskList.get(index);
        message.append("OK, I've marked this task as not done yet:\n")
                .append("   ").append(task.getTaskIcon())
                .append(task.getStatusIcon()).append(" ").append(task.getTaskDescription()).append("\n");
        return message.toString();
    }

    private String printDeletedTask(List<Task> taskList, int index) {
        StringBuilder message = new StringBuilder();
        if (index < 0 || index >= taskList.size()) {
            return "Sorry, I believe the TASK NUMBER you specified doesn't exist.";
        }
        Task deletedTask = taskList.get(index);
        message.append("Noted. I've removed this task:\n")
                .append("     ").append(deletedTask.getTaskIcon())
                .append(deletedTask.getStatusIcon()).append(" ").append(deletedTask.getTaskDescription()).append("\n")
                .append("  Now you have ").append(taskList.size() - 1)
                .append(" tasks left in this list.\n");
        return message.toString();
    }

    private String printAddedTask(List<Task> taskList, int index) {
        StringBuilder message = new StringBuilder();
        if (index < 0 || index >= taskList.size()) {
            return "The task couldn't be added properly.";
        }
        Task task = taskList.get(index);
        message.append("Got it. I've added this task: ")
                .append("   ").append(task.getTaskIcon())
                .append(task.getStatusIcon()).append(" ").append(task.getTaskDescription()).append("\n")
                .append("Now you have ").append(taskList.size())
                .append(" tasks in the list.\n");
        return message.toString();
    }

    private String printErrorMessage(String command) {
        return "Sorry, the description of " + command.toUpperCase() +
                " cannot be empty. Please add details, so that I can assist you better!";
    }
}
