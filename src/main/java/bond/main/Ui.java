package bond.main;

import java.util.ListIterator;
import java.util.Scanner;

import bond.task.Task;
import bond.task.TaskList;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private static final String LINE = "____________________________________________________________";

    public void showLine() {
        System.out.println(Ui.LINE);
    }

    public void newLine() {
        System.out.println();
    }

    public void showWelcome() {
        System.out.println(String.format("Hello! I'm %s. \nWhat can I do for you?\n", "Bond"));
        this.showLine();
        this.newLine();
    }

    public String readCommand() {
        String userInput = "";

        if (sc.hasNextLine()) {
            userInput = sc.nextLine();
        }

        return userInput;
    }

    public void taskAdded(Task newTask, TaskList taskList) {
        this.showLine();
        System.out.println(String.format(
                "\n    Got it. I've added this task:\n      %s \n    Now you have %d tasks in the list.",
                newTask.toString(), taskList.numberOfTasks()));
        this.showLine();
        this.newLine();
    }

    public void taskDeleted(Task deletedTask, TaskList taskList) {
        this.showLine();
        System.out.println(String.format(
                "\n    Got it. I've removed this task:\n      %s \n    Now you have %d tasks in the list.",
                deletedTask.toString(), taskList.numberOfTasks()));
        this.showLine();
        this.newLine();
    }

    public void taskMarked(Task markedTask, TaskList taskList) {
        this.showLine();
        System.out
                .println(String.format(
                        "\n    Nice! I've marked this task as done:\n      %s",
                        markedTask.toString()));
        this.showLine();
        this.newLine();
    }

    public void taskUnmarked(Task unmarkedTask, TaskList taskList) {
        this.showLine();
        System.out
                .println(String.format(
                        "\n    OK, I've marked this task as not done yet:\n      %s",
                        unmarkedTask.toString()));
        this.showLine();
        this.newLine();
    }

    public void showList(TaskList taskList) {
        ListIterator<Task> tasks = taskList.getTasks();

        this.showLine();
        System.out.println(String.format("\n    Here are the tasks in your list:"));

        while (tasks.hasNext()) {
            System.out.println(String.format("    %d. %s",
                    tasks.nextIndex() + 1, tasks.next().toString()));
        }

        this.showLine();
        this.newLine();
    }

    public void showError(Exception e) {
        this.showLine();
        System.out.println(String.format("\n    %s", e.getMessage()));
        this.showLine();
        this.newLine();
    }

    public void showGoodbye() {
        this.showLine();
        System.out.println("\n    Bye. Hope to see you again soon!");
        this.showLine();
        this.newLine();
    }

    public void closeScanner() {
        this.sc.close();
    }
}
