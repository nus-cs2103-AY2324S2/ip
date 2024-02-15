package ui;
import tasklist.Task;

import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
    private static final String ALLTASKSHEADER = "Here are the tasks in your list!";

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
        String end = "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        return end;
    }

    /**
     * Print the task as marked
     * @param task
     * @return the marked task
     */
    public String printTaskMarked(String task) {
        String output = "Nice! I've marked this task as done:";
        return output + "\n" + task;
    }

    /**
     * Print the task as unmarked
     * @param task
     * @return the unmarked task
     */
    public String printTaskUnMarked(String task) {
        String output = "OK, I've marked this task as not done yet:";
        return output + "\n" +task;
    }

    /**
     * Print the task list
     * @param taskList
     * @String the task list
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
     * @param task
     * @return the deleted task string
     */
    public String printDeletedTask(String task) {
        String output = "Noted. I've removed this task:" + "\n" + task;
        return output;
    }

    /**
     * Returns a string that the task list is empty
     * @return the string
     */
    public String printEmptyTaskList() {
        return ("Now, your list is empty!");
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
        String output = ALLTASKSHEADER;
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
