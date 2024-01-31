package ui;

import exceptions.DukeException;
import task.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.List;

public class Ui {

    private final BufferedReader br;

    /**
     * Prints a vertical line.
     */
    public static void printVLine() {
        System.out.println("------------------------------------");
    }

    /**
     * Constructor for Ui.
     */
    public Ui() {
        intro();
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads user-typed input.
     * @return An array of strings.
     * @throws IOException Throws IOException when I/O error occurs.
     */
    public String[] getUserCommand() throws IOException {
        return br.readLine().split(" ");
    }
    
    private void intro() {
        printVLine();
        System.out.println("Hello! I'm Howie! My hobby is to keep track of TASKS. Let me help you!");
        System.out.println("So, what can I do for you?");
        printVLine();
    }

    /**
     * Prints the list of tasks that is currently stored by Howie.
     * @param tasks The collection of tasks.
     */
    public static void printAllTask(List<Task> tasks) {
        printVLine();
        if (tasks.isEmpty()) {
            System.out.println("Your list is now EMPTY! Time for you to have a break!");
            printVLine();
            return;
        }
        System.out.println("You have " + tasks.size() + " left. There are:");
        for (int i=1; i<=tasks.size(); i++) {
            Task t = tasks.get(i-1);
            System.out.println(i +"." + t);
        }
        printVLine();
    }

    /**
     * Exits the program when exit command is entered.
     */
    public static void exit() {
        printVLine();
        System.out.println("Bye! I'll see you when I see you :)");
        printVLine();
        System.exit(0);
    }

    /**
     * Prints the list of commands available to Howie.
     */
    public static void helpMessage() {
        printVLine();
        System.out.println("Below is a list of available commands for me:\n" +
                "   todo [task_name]\n" +
                "   event [task_name] /from [date] /to [date]\n" +
                "   deadline [task_name] /by [date]\n" +
                "   list - to see all tasks\n" +
                "   mark/unmark [index]\n" +
                "   bye - to exit the programme");
        printVLine();
    }

    /**
     * Prints a message when a blank task has been entered.
     */
    public static void emptyTaskMessage() throws DukeException {
        Ui.printVLine();
        throw new DukeException("Hey! You've just entered an unnamed task... Try to give a description/name of your task :)");
    }

    /**
     * Prints a message when an invalid format has been entered.
     */
    public static void invalidFormat() throws DukeException {
        Ui.printVLine();
        throw new DukeException("I see you've entered an invalid format. Type 'help' if you're unsure :)");
    }
}
