package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

import duke.exception.DukeException;

public class Ui {
    Scanner scanner;
    TaskList taskList;

    public Ui() {
        scanner = new Scanner(System.in);
        taskList = new TaskList();
    }

    public void greet() {
        //Greetings
        String name = "Tommy";
        printDivider();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printDivider();
    }

    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
        scanner.close();
    }

    public static void showLoadingError() {
        System.out.println("There is an error loading the Storage");
    }

    public String getInput() {
        String userInput = this.scanner.nextLine();
        return userInput;
    }

    public static void displayAllTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> arrayListOfTasks = taskList.getArrayList();
        int length = taskList.getSize();
        for (int i = 0; i < length; i++) {
            Task task = arrayListOfTasks.get(i);
            System.out.println(i + 1 + "." + task.toString());
        }

        printDivider();
    }

    public static void displayDeletedTask(TaskList taskList, Task task) {
        String descOfTaskToDelete = task.toString();

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + descOfTaskToDelete);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");

        printDivider();
    }

    public static void displayMarkedTask(TaskList tasklist, int position) {
        Task markedTask = tasklist.getTaskAtPosition(position);

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + markedTask.toString());

        printDivider();
    }

    public static void displayUnmarkedTask(TaskList tasklist, int position) {
        Task unmarkedTask = tasklist.getTaskAtPosition(position);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + unmarkedTask.toString());

        printDivider();
    }

    public static void displayNewTask(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");

        printDivider();

    }

    public static void printError(DukeException e) {
        System.out.println(e.errorMessage());
        printDivider();
    }

    private static void printDivider() {
        System.out.println("____________________________");
    }
}
