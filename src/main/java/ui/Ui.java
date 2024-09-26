package ui;
import tasklist.Task;

import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
    private static final String ALL_TASKS_HEADER = "Here are the tasks in your list! Woof!";
    public static final String FOUND_SOMETHING_RESPONSE = "Woof! I found what you were looking for. Here it is:\n";

    public static final String WELCOME_MESSAGE = "Woof! Welcome to Jux, your loyal task companion! " +
            "Ready to do some tasks?";
    public static final String RETURN_WELCOME_MESSAGE = "Woof! Welcome back owner! Let's continue our adventure!\n";
    private static final String MARKED_TASK = "Good Work! I've marked this task as done: \n";
    private static final String UNMARKED_TASK = "Sure thing! I've marked this task as not done yet. " +
            "Keep working hard! \n";
    private static final String DELETE_TASK_HEADER = "Noted. I've removed this task. Have I been a good dog?\n";
    private static final String EMPTY_TASK_LIST = "Woof! Your list is empty. " +
            "Let's add some tasks to make it a good day!";
    private static final String TASK_AFTERWORD = "Woof I added the task below! " +
            "Did i do a great job? \n";

    /**
     * Ui class that contains the strings to be printed in the Gui
     */
    public Ui() {
    }
    public void showLoadingError(String error) {
        System.out.println(error);
    }

    /**
     * Print the task as marked
     * @param task task to be marked
     * @return the marked task
     */
    public String printTaskMarked(String task) {
        String output = MARKED_TASK;
        return output + task;
    }

    /**
     * Print the task as unmarked
     * @param task task to be unmarked
     * @return the unmarked task
     */
    public String printTaskUnMarked(String task) {
        String output = UNMARKED_TASK;
        return output + task;
    }

    /**
     * Print the task list
     * @param taskList tasklist
     * @return String the task list
     */
    public String printFindList(ArrayList<Task> taskList) {
        String output = FOUND_SOMETHING_RESPONSE;
        for (int i = 0; i < taskList.size(); i++) {
            int j = i + 1;
            output += j + "." + (taskList.get(i)) + "\n";
        }
        return output;
    }

    /**
     * Print the deleted task
     * @param task the deleted task
     * @return the deleted task string
     */
    public String printDeletedTask(String task) {
        String output = DELETE_TASK_HEADER + task;
        return output;
    }

    /**
     * Returns a string that the task list is empty
     * @return the string
     */
    public String printEmptyTaskList() {
        return EMPTY_TASK_LIST;
    }

    /**
     * Returns string after a task is added
     * @param task the task in string form
     * @return the task string
     */
    public String printTaskAfterword(String task) {
        String output = TASK_AFTERWORD;
        assert task.length() != 0 : "task should not be empty";
        output += task;
        return output;
    }

    /**
     * Returns a string saying task not found
     * @return the string
     */
    public String printNotFound() {
        return "Task not found!";
    }
    /**
     * Returns the number of tasks
     * @param num
     * @return the total number of tasks remaining
     */

    public String printNumberOfTasks(int num) {
        String output = "You now have " + num + " tasks remaining";
        if (num == 1) {
            output = "You now have 1 task remaining";
        }
        return output;
    }

    /**
     * Print the task with indexing
     * @param taskList
     */
    public String printListWithIndexing(ArrayList<Task> taskList) {
        String output = ALL_TASKS_HEADER;
        for (int i = 0; i < taskList.size();i++) {
            int j = i + 1;
            output +="\n" + j + "." +  taskList.get(i);

        }
        if (taskList.isEmpty()) {
            return printEmptyTaskList();
        }
        return output;
    }

}
