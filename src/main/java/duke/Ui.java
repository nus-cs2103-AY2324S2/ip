package duke;

import duke.task.Task;
import java.util.ArrayList;

public class Ui {
    public void showLoadingError() {

    }

    public void printError(DukeException dukeException) {
        printBreak();
        System.out.println(dukeException.getMessage());
        printBreak();
    }

    public void openingMessage() {
        printBreak();
        System.out.println("Hello! I'm Klara");
        System.out.println("What can I do for you?");
        printBreak();
    }

    public void closingMessage() {
        // Logging off upon "bye" command
        printBreak();
        System.out.println("Bye. Hope to see you again soon!");
        printBreak();
    }

    public void showTaskDone(Task task) {
        printBreak();
        System.out.println("Nice! I've marked this Duke.task as done:");
        System.out.println(task);
        printBreak();
    }

    public void showTaskUndone(Task task) {
        printBreak();
        System.out.println("OK, I've marked this Duke.task as not done yet:");
        System.out.println(task);
        printBreak();
    }

    public void showTaskAdded(Task task, int size) {
        printBreak();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        printBreak();
    }

    public void showTaskDeleted(Task task, int size) {
        printBreak();
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        printBreak();
    }

    public void printBreak() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public void printList(ArrayList<Task> list) {
        printBreak();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isDone) {
                System.out.println(Integer.toString(i + 1) + "." + list.get(i));
            }
            else {
                System.out.println(Integer.toString(i + 1) + "." + list.get(i));
            }
        }
        printBreak();
    }
}
