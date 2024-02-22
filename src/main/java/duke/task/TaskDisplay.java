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
            message.append("Looks like there's nothing here yet! 😅 Let's add some tasks!");
        } else {
            message.append("Here's what we've got on the agenda! 📋\n");
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
            return "Hmm, I couldn't find that task number. 🤔";
        }
        Task task = taskList.get(index);
        message.append("\n   ").append(task.getTaskIcon())
                .append(task.getStatusIcon()).append(" ").append(task.getTaskDescription()).append(" ✔️\n");
        return message.toString();
    }

    private String printUnmarkTask(List<Task> taskList, int index) {
        StringBuilder message = new StringBuilder();
        if (index < 0 || index >= taskList.size()) {
            return "Oops! That task number seems off. 🧐";
        }
        Task task = taskList.get(index);
        message.append("\n   ").append(task.getTaskIcon())
                .append(task.getStatusIcon()).append(" ").append(task.getTaskDescription()).append(" 🕒\n");
        return message.toString();
    }

    private String printDeletedTask(List<Task> taskList, int index) {
        StringBuilder message = new StringBuilder();
        if (index < 0 || index >= taskList.size()) {
            return "Uh-oh, I couldn't find that task. Are you sure it exists? 🤨";
        }
        Task deletedTask = taskList.get(index);
        message.append("\n     ").append(deletedTask.getTaskIcon())
                .append(deletedTask.getStatusIcon()).append(" ").append(deletedTask.getTaskDescription()).append(" 🗑️\n")
                .append("You're now down to ").append(taskList.size() - 1)
                .append(" tasks.\n");
        return message.toString();
    }

    private String printAddedTask(List<Task> taskList, int index) {
        StringBuilder message = new StringBuilder();
        if (index < 0 || index >= taskList.size()) {
            return "Hmm, something went wrong. I couldn't add the task. 🙁";
        }
        Task task = taskList.get(index);
        message.append("\n   ").append(task.getTaskIcon())
                .append(task.getStatusIcon()).append(" ").append(task.getTaskDescription()).append(" 📝\n")
                .append("Bringing your total to ").append(taskList.size())
                .append(" tasks. Let's keep this productivity train going! 🚂💨\n");
        return message.toString();
    }

    private String printErrorMessage(String command) {
        return "Sorry, the description of " + command.toUpperCase() +
                " cannot be empty. Please add details, so that I can assist you better!";
    }
}
