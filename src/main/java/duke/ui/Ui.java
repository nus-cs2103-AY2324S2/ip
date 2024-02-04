package duke.ui;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
       return sc.nextLine();
    }

    public void welcomeMsg() {
        System.out.println("Hello from \n" + LOGO);
        System.out.println("This is Dooloodoodooloodoo!\n" +
                "What can I do for you?\n");
    }

    public void goodbyeMsg() {
        System.out.println("Goodbye. Have a great day ahead!");
    }

    public void showAddMsg(Task task, int index) {
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.format("Now you have %d tasks in the list.\n", index);
    }

    public void showDeleteMsg(Task deletedTask, int index) {
        System.out.println("Noted. I've removed this task:\n" +
                deletedTask);
        System.out.format("Now you have %d tasks in the list.\n", (index - 1));
    }

    public void showMarkMsg(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void showUnmarkMsg(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public void showHelpMsg() {
        System.out.println("bye: Terminate the program.\n" +
                "list: Display the list of tasks.\n" +
                "mark <index>: Mark a task as done.\n" +
                "unmark <index>: Mark a task as not done.\n" +
                "todo <description>: Add a todo task.\n" +
                "deadline <description> /by <yyyy-MM-dd hh:mma>: Add a deadline task.\n" +
                "event <description> /from <startDate> /to <endDate>: Add an event task.");
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public void showList() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showLine() {
        System.out.println("-----------------------------------------------------------------");
    }



}
