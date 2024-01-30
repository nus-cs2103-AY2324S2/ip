package drake;
import java.util.ArrayList;

import drake.task.Task;
import drake.task.TaskList;

public class Ui {
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up everyone. I'm Drake.");
        System.out.println(" How can I help?");
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" See you later, alligator! ");
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public void showTask(String message, Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("You asked for the tasks in your list? Here:");
        ArrayList<Task> taskList = tasks.getTasks();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void showAddTask(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showMarkTask(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Cool. I now declare this task marked as, done:");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    public void showUnmarkTask(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    public void showDeleteTask(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

}