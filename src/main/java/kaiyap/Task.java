package kaiyap;

import java.time.format.DateTimeFormatter;

/**
 * Represents a general task in the KaiYap application.
 * This class serves as a base for different types of tasks.
 * It includes common properties such as the task description and completion status.
 */
public class Task {
    protected String listItem;
    protected String inputItem;
    protected String completed;
    protected boolean isCompleted;
    protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructs a new Task with the specified description and input.
     *
     * @param listItem  The description of the task to be displayed in the task list.
     * @param inputItem The original input string used to create the task.
     */
    public Task(String listItem, String inputItem) {
        this.listItem = listItem;
        this.inputItem = inputItem;
        this.setTaskDone(false);
    }

    public boolean isTaskDone() {
        return isCompleted;
    }

    public void setTaskDone(boolean taskDone) {
        this.isCompleted = taskDone;
        this.completed = !this.isCompleted ? " incomplete" : " complete";
    }

    public String getListItem() {
        return listItem;
    }

    public String getInputItem() {
        return inputItem;
    }

}
