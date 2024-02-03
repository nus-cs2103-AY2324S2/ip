package Ping;

import java.util.ArrayList;
import java.util.Scanner;

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
    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void respondBlah() {
        System.out.println("haha, that's humorous");
    }

    public void exceptionMessage() {
        System.out.println("Incorrect number or command");
    }

    public void hiMessage() {
        System.out.println("Hi, there");
    }
    /**
     * This function below used for event and deadline command
     */

    public void addMessage(Task task, int numOfWork) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list." + "%n", numOfWork);
    }

    /**
     * This function below used for todo command
     */
    public void deleteTaskMessage(int i, Task task, int numOfWork) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list." + "%n", numOfWork);
    }
    /**
     * This method is used to show the list of tasks
     * @param tasks the list of tasks
     */
    public void showList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No task added yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                int idx = i + 1;
                if (tasks.get(i).isDone) {
                    System.out.printf("%d." + tasks.get((i)).toString() + "%n", idx);
                } else {
                    System.out.printf("%d." + tasks.get(i).toString() + "%n", idx);

                }
            }
        }
    }
    /**
     * This method is used to mark the task as done
     * @param t the task
     */
    public void markTaskMessage(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
    }
    /**
     * This method is used to unmark the task as not done
     * @param t the task
     */
    public void unmarkTaskMessage(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
    }
}
