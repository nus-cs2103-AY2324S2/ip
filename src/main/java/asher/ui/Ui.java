package asher.ui;

import java.util.ArrayList;

import asher.tasks.Deadline;
import asher.tasks.TaskList;
import asher.tasks.Task;

/**
 * Contains functions to handle the user interface of the bot.
 */
public class Ui {
    /**
     * Constructs a Ui object.
     */
    public Ui() {}

    public String showWelcomeMessage() {
        return "Hello! I'm Asher. What can I do for you?";
    }

    public String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String showAddTaskMessage(Task task) {
        return "Got it. I've added this task:\n " + task;
    }

    public String showRemoveTaskMessage(Task removedTask) {
        return "Noted. I've removed this task:\n " + removedTask;
    }

    public String showNumberOfTaskInListMessage(int totalTasks) {
        return "Now you have " + totalTasks + " tasks in the list.";
    }

    public String showMarkTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n " + task;
    }

    public String showUnmarkTaskMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n " + task;
    }

    public String showTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        StringBuilder messageBuilder = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            messageBuilder.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        return messageBuilder.toString();
    }

    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder messageBuilder = new StringBuilder();

        if (matchingTasks.isEmpty()) {
            messageBuilder.append("No matching tasks at all!\n");
        } else {
            messageBuilder.append("Here are the matching tasks in your list:\n");

            for (int i = 0; i < matchingTasks.size(); i++) {
                messageBuilder.append((i + 1)).append(".").append(matchingTasks.get(i)).append("\n");
            }
        }
        return messageBuilder.toString();
    }

    public String showSortedDeadlineTasks(ArrayList<Deadline> sortedDeadlines) {
        StringBuilder messageBuilder = new StringBuilder("Here are your sorted deadlines:\n");

        for (int i = 0; i < sortedDeadlines.size(); i++) {
            messageBuilder.append((i + 1)).append(".").append(sortedDeadlines.get(i)).append("\n");
        }
        return messageBuilder.toString();

    }

    public String showErrorMessage(String message) {
        return message;
    }
}
