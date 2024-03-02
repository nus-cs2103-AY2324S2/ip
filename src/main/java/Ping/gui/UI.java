package ping.gui;

import java.util.ArrayList;
import java.util.Scanner;

import ping.job.Task;

/**
 * This class is used to create a user interface
 */
public class UI {
    private Scanner sc;

    public UI() {
        this.sc = new Scanner(System.in);
    }

    public String readLines() {
        return sc.nextLine();
    }

    /**
     * This method is used to print the welcome message
     */
    public void welcome() {
        System.out.println("----------------------------------------------------------");
        System.out.println("Hello! I'm Ping" + "\nWhat can I do for you?");
        System.out.println("----------------------------------------------------------");
    }

    public String goodBye() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String respondBlah() {
        return "haha, that's humorous\n";
    }

    public String hiMessage() {
        return "Hi, there\n";
    }

    /**
     * This function below used for event and deadline command
     */

    public String addMessage(Task task, int numOfWork) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + numOfWork + " tasks in the list.";
    }

    /**
     * This function below used for todo command
     */
    public String deleteTaskMessage(int i, Task task, int numOfWork) {
        return "Noted. I've removed this task: \n"
                + task.toString() + "\n"
                + "Now you have " + numOfWork + " tasks in the list";
    }

    /**
     * This method is used to show the list of tasks
     *
     * @param tasks the list of tasks
     */
    public String showList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        if (tasks.isEmpty()) {
            return "The list is empty\n";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                int idx = i + 1;
                sb.append(idx).append(". ").append(tasks.get(i).toString()).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * This method is used to mark the task as done
     *
     * @param t the task
     */
    public String markTaskMessage(Task t) {
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    /**
     * This method is used to unmark the task as not done
     *
     * @param t the task
     */
    public String unmarkTaskMessage(Task t) {
        return "Nice! I've unmarked this task as not done:\n" + t.toString();
    }

    /**
     * This method is used to find the task
     *
     * @param tasks the list of tasks
     */
    public String findTaskMessage(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        if (tasks.isEmpty()) {
            return "No matching tasks found\n";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                int idx = i + 1;
                sb.append(idx).append(".").append(tasks.get(i).toString()).append("\n");
            }
            return sb.toString();
        }
    }

    public String showError(String message) {
        return message;
    }
}
