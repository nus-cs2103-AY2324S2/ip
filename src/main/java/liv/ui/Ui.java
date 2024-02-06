package liv.ui;

import java.util.Scanner;
import liv.container.TaskList;
import liv.task.Task;
import liv.task.TodoTask;
import liv.task.Deadline;
import liv.task.Event;

public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);
    public void displayBar() {
        System.out.println("____________________________________________________________");
    }
    public void displayGreetCommand() {
        System.out.println("liv.Liv, under your instructions!");
        System.out.println("What is your command?\n");
    }

    public String readCommand() {
        return SCANNER.nextLine();
    }
    public static void displayByeCommand() {
        System.out.println("Farewell, see you next time!");
    }

    public static void displayListCommand() {
        System.out.println("Here are the missions you added:");
        for (int i = 0; i < TaskList.getListSize(); i++) {
            Task task = TaskList.getTask(i);
            int displayedIndex = i + 1;
            System.out.println(displayedIndex + ". " + task.getDisplayedString());
            //System.out.printf("%d. %s %s\n", displayedIndex, task.getStatusIcon(), task.getDescription());
        }
        System.out.println("Total: " + TaskList.getListSize() + " mission(s)");
    }
    public static void displayMarkCommand(Task task) {
        System.out.println("Mission completed:\n");
        System.out.println(" " + task.getStatusIcon() + " " + task.getDescription());
    }

    public static void displayUnmarkCommand(Task task) {
        System.out.println("Mission pending:\n");
        System.out.println(" " + task.getStatusIcon() + " " + task.getDescription());
    }

    public static void displayDeleteCommand(Task removed) {
        System.out.println("Mission deleted from list:");
        System.out.println(removed.getDisplayedString());
        System.out.println("You have " + TaskList.getListSize() + " mission(s) on the list");
    }
    public static void displayTodoCommand(TodoTask todo) {
        System.out.println("liv.task.Task added:");
        System.out.println(todo.getDisplayedString());
        System.out.println("You have " + TaskList.getListSize() + " mission(s) on the list");
    }

    public static void displayDeadlineCommand(Deadline deadline) {
        System.out.println("liv.task.Deadline added:");
        System.out.println(deadline.getDisplayedString());
        System.out.println("You have " + TaskList.getListSize() + " mission(s) on the list");
    }

    public static void displayEventCommand(Event event) {
        System.out.println("liv.task.Task added:");
        System.out.println(event.getDisplayedString());
        System.out.println("You have " + TaskList.getListSize() + " mission(s) on the list");
    }

    public void displayErrorMessage(String message) {
        System.out.println(message);
    }
}
