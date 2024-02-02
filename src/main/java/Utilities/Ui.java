package Utilities;

import Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interface interactions.
 */
public class Ui {
    /** Scanner object for user input. */
    private Scanner sc;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void sayHello() {
        System.out.println("Hello, I'm Ypxmm.\nNeed me do what for you?");
    }

    /**
     * Reads user command from the console.
     *
     * @return the user input command
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Displays available commands.
     */
    public void getCommands() {
        System.out.println("Take note ah, enter all time based commands are in <dd-mm-yyyy HHmm> format\n" +
                "  todo <task> - adds todo\n  deadline <task>/<by when> - adds deadline\n" +
                "  event <task>/<from when>/<to when> - adds event\n  list - lists out all tasks\n" +
                "  mark <x> - marks task x as done\n  unmark <x> - unmarks task x as undone\n" +
                "  delete <x> - deletes task x from the list\n" +  "  find <thing to search> - searches for tasks with the input word or phrase\n" +
                "  bye - exit");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks the list of tasks to display
     */
    public void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet la bro");
        } else {
            System.out.println("Ok wait ah, here are your tasks:");
            int count = 1;
            for (Task t : tasks) {
                System.out.println(count + ". " + t.toString());
                count++;
            }
        }
    }

    /**
     * Displays a message after adding a task.
     *
     * @param task the added task
     * @param tasklist the task list
     */
    public void addTaskMessage(Task task, TaskList tasklist) {
        System.out.println("Ok I help you add this one liao:\n" + task.toString() +
                "\nNow your list got " + tasklist.tasks.size() + ((tasklist.tasks.size() == 1) ? " task." : " tasks."));
    }

    /**
     * Displays a message after deleting a task.
     *
     * @param task the deleted task
     * @param tasklist the task list
     */
    public void deleteTaskMessage(Task task, TaskList tasklist) {
        System.out.println("Ok deleted liao:\n" + task.toString() + "\nNow your list got " +
                (tasklist.tasks.isEmpty() ? "no tasks." : tasklist.tasks.size() + " tasks left."));
    }

    /**
     * Displays a message after marking a task as completed.
     *
     * @param task the marked task
     */
    public void markMessage(Task task) {
        System.out.println("Upz la, mark for you already!");
        System.out.println(task.toString());
    }

    /**
     * Displays a message after unmarking a task as incomplete.
     *
     * @param task the unmarked task
     */
    public void unmarkMessage(Task task) {
        System.out.println("Eh wake up your idea, faster finish can or not?? Unmark for you already la!");
        System.out.println(task.toString());
    }

    /**
     * Prints the tasks found with the specified keyword in its name.
     * If no tasks are found, prints a message indicating so.
     *
     * @param tasks The list of tasks the user currently has.
     */
    public void findMessage(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks with that name la");
        } else {
            System.out.println("Ok ah, this is what I found");
            int count = 1;
            for (Task t : tasks) {
                System.out.println(count + ". " + t.toString());
                count++;
            }
        }
    }

    /**
     * Displays the goodbye message.
     */
    public void sayGoodbye() {
        System.out.println("Oh you need zao alr? Okok see you next time!");
    }
}
