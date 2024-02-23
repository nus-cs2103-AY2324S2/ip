package ui;
import tasklist.Task;

import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
    private static final String ALL_TASKS_HEADER = "Here are the tasks in your list!";
    public static final String WELCOME_MESSAGE = "Welcome to Jux!";
    public static final String RETURN_WELCOME_MESSAGE = "Previously on Jux....\n";
    private static final String GOODBYE_MESSAGE = "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________________";
    private static final String MARKED_TASK = "Nice! I've marked this task as done: \n";
    private static final String UNMARKED_TASK = "OK, I've marked this task as not done yet: \n";
    private static final String DELETE_TASK_HEADER = "Noted. I've removed this task: \n";
    private static final String EMPTY_TASK_LIST = "your list is empty!";

    /**
     * Ui class that contains the strings to be printed in the Gui
     */
    public Ui() {
    }
    public void showLoadingError(String error) {
        System.out.println(error);
    }

    /**
     * Print goodbye message upon exiting the program
     */
    public String showGoodbye() {
        return GOODBYE_MESSAGE;
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
    public String printList(ArrayList<Task> taskList) {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += (taskList.get(i)) + "\n";
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
        String output = "You entered:" + "\n" ;
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
