package kitchensink;
import kitchensink.task.Task;

import java.util.Scanner;

public class Ui {
    public static final String LINE = "__________________________________________________________\n";

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public void welcome() {
        System.out.println(LINE + "Hello! I'm KitchenSink!\n" + "What can I do for you?\n" + LINE);
    }

    public void sayGoodBye() {
        System.out.println(LINE + "Bye. Hope to see you again soon!\n" + LINE);
    }

    public void sayTaskAdded(Task task, int taskListSize) {
        String taskOrTasks = taskListSize == 1 ? " task " : " tasks ";
        System.out.println(Ui.LINE
                + "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + taskListSize + taskOrTasks + "in the list.\n"
                + Ui.LINE);
    }

    public void displayTasks(List taskList) {
        System.out.println(Ui.LINE + "Here are the tasks in your list:\n" + taskList.toString() + "\n" + Ui.LINE);
    }

    public void sayTaskDeleted(Task task, int taskListSize) {
        String taskOrTasks = taskListSize == 1 ? " task " : " tasks ";
        System.out.println(Ui.LINE + "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + taskListSize + taskOrTasks + "in the list.\n" + Ui.LINE);
    }
}
