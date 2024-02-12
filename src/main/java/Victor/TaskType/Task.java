package victor.tasktype;

/**
 * The Task class is a parent class for all the different tasks.
 * It is used to lay out the basic information that is needed in
 * a task that all task types have to have, such as
 * description and whether it is done.
 * It also contains methods that all task types have to have.
 * It shouldn't be used to add to the ArrayList of Task, instead only
 * serving as the class that connects all the more specific
 * task types.
 *
 * @author Dominic Fu Ming Jun
 */
public class Task {

    /**
     * The description variable is a String that contains the description of the task.
     */
    protected String description;
    /**
     * The isDone variable is a boolean that determines whether the task is done or not.
     */
    protected boolean isDone;


    /**
     * The Task constructor takes in a String description and a boolean isDone
     * which is added to their respective variables of the same name.
     *
     * @param description The description of the task.
     * @param isDone      States whether the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * The getStatusIcon method is used to display an "X" if the Task is done.
     * If the Task is not done yet, it will display a space " " instead.
     *
     * @return A string of either "X" or " ", depending on the isDone variable.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * The markAsDone method is used to turn the isDone variable to true to
     * indicate that the Task is completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * The unmarkAsDone method is used to turn the isDone variable to false to
     * indicate that the Task is not yet completed.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * The toString method is used to display the String of the status Icon of the
     * task and the description of the task.
     *
     * @return A string of the status icon and the description of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * The saveInput is currently empty as it is to be overridden
     * the other task types. It is normally used to indicate the format
     * of the data that is to be saved into the data file.
     *
     * @return An empty string for the Task class.
     */
    public String saveInput() {
        return "";
    }

    /**
     * The descriptionContains method is used to determine whether
     * a task description contains the wordSearch. It is used for
     * the findTask method in the TaskList.
     *
     * @param wordSearch A string which would be used to check if
     *                   the description contains it.
     * @return A boolean would be return to indicate whether
     *         the wordSearch is found inside the description.
     */
    public boolean descriptionContains(String wordSearch) {
        return this.description.contains(wordSearch);
    }
}

