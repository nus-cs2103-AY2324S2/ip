package seedu.duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import seedu.duke.common.TaskList;
import seedu.duke.task.Task;


/**
 * The Ui class implements methods that display result to users with given format and read input from the user
 */
public class Ui {
    private String response;


    /**
     * Get the response of most recently run command
     *
     * @return a string representing the result of the command
     */
    public String getResponse() {
        return this.response;
    }


    /**
     * Get the welcome message
     *
     * @return a string representing the welcome message
     */
    public String getWelcomeMessage() {
        return "Hello Bro, I'm wind" + "\n"
                + "What can I do for you?";
    }

    /**
     * Get loading exception message to the user
     */
    public void getLoadingExceptionMessage() {
        this.response = "Bro, something bad happens during loading, I can't load your tasks";
    }

    /**
     * Get error message to the user
     *
     * @param message The error message
     */
    public void generateErrorMessage(String message) {
        this.response = message;
    }

    /**
     * Get the tasks in the taskList in the users
     *
     * @param taskList the TaskList containing all tasks that users have
     */
    public void generateTaskList(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getListSize(); i++) {
            int number = i + 1;
            stringBuilder.append(number).append(".").append(taskList.getTask(i)).append("\n");
        }
        this.response = stringBuilder.toString();
    }

    /**
     * Get the result of new task being added to the user
     *
     * @param task     The new task
     * @param taskList The TaskList containing all the tasks that users have
     */
    public void generateNewTaskResponse(Task task, TaskList taskList) {
        this.response = String.format("Got it. I've added this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list%n", task, taskList.getListSize());
    }

    /**
     * Get result when a task is being mark as done
     *
     * @param task The task that is being mark as done
     */
    public void generateMarkAsDoneResponse(Task task) {
        this.response = String.format("Nice! I've marked this task as done:\n"
                + " " + task);
    }

    /**
     * Get result when a task is being mark as not done
     *
     * @param task The task being mark as not done
     */
    public void generateMarkAsNotDoneResponse(Task task) {
        this.response = "OK, I've marked this task as not done yet:\n"
                + " " + task;
    }

    /**
     * Get result when a task is deleted
     *
     * @param task     The task that is deleted
     * @param taskList The TaskList object containing all the tasks
     */
    public void generateDeleteTaskResponse(Task task, TaskList taskList) {
        this.response = "Noted. I've removed this task:\n"
                + " " + task + "\n"
                + "Now you have " + taskList.getListSize() + " tasks in the list";
    }

    /**
     * Get the tasks that is due on a specific date
     *
     * @param tasks     The tasks that are due on that date
     * @param localDate The due date
     */
    public void generateDueTaskListResponse(List<Task> tasks, LocalDate localDate) {
        if (tasks.isEmpty()) {
            this.response = String.format("You have no task due on %s\n",
                    localDate.format(DateTimeFormatter.ofPattern("MM dd yy")));
        } else {
            this.response = String.format("You have the following tasks due on %s\n",
                    localDate.format(DateTimeFormatter.ofPattern("MM dd yy")));
            this.response += "\n" + getListView(tasks);
        }
    }


    private String getListView(List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i + 1).append(".").append(" ").append(tasks.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Get tasks that match the keyword
     *
     * @param tasks Tasks that match the keyword
     */
    public void generateFindResultResponse(List<Task> tasks) {
        this.response = "Here are the matching tasks in your list:\n";
        this.response += getListView(tasks);
    }

    /**
     * Get goodbye message
     */
    public void generateGoodByeMessage() {
        this.response = "Bye. Hope to see you again soon!";
    }
}
