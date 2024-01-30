package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
public class UI {
    private Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("     Hello! I'm Cleo");
        System.out.println("     What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println("____________________________________________________________");
        System.out.println(errorMessage);
        System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("     Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            System.out.println("     No tasks added yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     " + (i + 1) + "." + tasks.get(i));
            }
        }
    }
    public void showTasksOnDate(ArrayList<Task> tasks, LocalDate date) throws DukeException {
        System.out.println("Tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        for (Task task : tasks) {
            System.out.println(task);
        }
        if (tasks.isEmpty()) {
            System.out.println("No tasks found on this date.");
        }
    }

    public void showMaximumTasksReached() {
        System.out.println("     Maximum tasks reached. Cannot add more tasks.");
    }

    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskMarkedAsDone(Task task) {
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
    }

    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
    }

}