package ui;

import storage.TaskList;
import storage.Task;
import storage.Storage;

import dukeException.*;

public class UI {
    private static final String lines = "    ____________________________________________________________";
    private static final String name = "Wang";

    private final TaskList taskList;

    public UI() {
        this.taskList = new TaskList();
        Storage.start(taskList);
    }

    public static void greeting() {
        System.out.printf("    Hello! I'm %s\n", name);
        System.out.println("    What can I do for you?");
        System.out.println(lines);
    }

    public static void goodbye() {
        System.out.println(lines);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(lines);
    }

    public void addItem(Task task) {
        this.taskList.add(task);
        System.out.println(lines);
        System.out.println("    " + "Got it. I've added this task:\n" + "      " + task + "\n" + "" +
                String.format("    Now you have %d tasks in the list.", this.taskList.taskLength()));
        System.out.println(lines);
    }

    public void markTaskUI(int input) throws ListOutofBoundsException{
        if (input <0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutofBoundsException(String.format("%d", this.taskList.taskLength()));
        }
        System.out.println(lines);
        System.out.println("    Nice! I've marked this task as done:");
        this.taskList.markTask(input);
        System.out.println(lines);
    }
    public void unMarkTask(int input) throws ListOutofBoundsException{
        if (input < 0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutofBoundsException(String.format("%d", this.taskList.taskLength()));
        }
        System.out.println(lines);
        System.out.println("    OK, I've marked this task as not done yet:");
        this.taskList.unMarkTask(input);
        System.out.println(lines);
    }

    public void removeTask(int input) throws ListOutofBoundsException {

        if (input < 0 || input > this.taskList.taskLength() - 1) {
            throw new ListOutofBoundsException(String.format("%d", this.taskList.taskLength()));
        }
        System.out.println(lines);
        System.out.println("    Noted. I've removed this task:");
        this.taskList.remove(input);
        System.out.println(String.format("    Now you have %d tasks in the list.",this.taskList.taskLength()));
        System.out.println(lines);
    }

    public void listItems() {
        System.out.println(taskList);
    }

    public static void error(String error) {
        System.out.println(lines);
        System.out.println(error);
        System.out.println(lines);
    }


}
