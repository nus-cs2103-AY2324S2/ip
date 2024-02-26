package fireraya.main;

import fireraya.task.Task;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * A class to handle all the displays shown to the user.
 *
 * Being a chatbot program, this class is required to handle
 * messages shown to the user.
 */
public class Ui {

    private Scanner sc;

    /**
     * Constructor for the Ui.
     */
    Ui() {
        sc = new Scanner(System.in);
    }


    /**
     * Method to display a start message to a user.
     *
     * @return A string representing the start message to the user.
     */
    public static String startMessage() {
        String a = print("--------------------------------\n" );
        String b = print("Hello, my name is RONNIE!\n");
        String c = print("What can I do for you today mate?\n");
        String d = print("--------------------------------\n");
        String combined = a + b + c + d;
        return combined;
    }

    /**
     * Method to print a message to a user.
     *
     * @param message String to be printed to the user.
     * @return A string representing the message to the user.
     */
    public static String print(String message) {
        return message;
    }

    /**
     * Method to print a loading error to a user.
     *
     * @param message error String to be printed to the user.
     * @return A string representing the error message to the user.
     */
    public String displayLoadingError(String message) {
        return print("An error occurred at file load" + message);
    }

    /**
     * Method to print a task added notification to a user.
     *
     * @param t Task that was added to the TaskList.
     * @param size current size of the TaskList.
     * @return A string representing the notification message to the user.
     */
    public String taskAddedMsg(Task t, int size) {
        String a = print("I've added this task:\n");
        String b = t.toString() + "\n";
        String c = print("You have " + size + " tasks currently.\n");
        String combined = a + b + c;
        return combined;

    }

    /**
     * Method to list the tasks to the user.
     *
     * @param tasks ArrayList of the tasks to be printed.
     * @return A string representing the entire TaskList to the user.
     */
    public String listTasks(ArrayList<Task> tasks) {
        String a = print("Here is a list of your tasks!\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            int num = i + 1;
            a += print(num + "." + current.toString() + "\n");
        }
        return a;
    }

    /**
     * Method to list a specific task to a user.
     *
     * @param tasks ArrayList of the tasks to be printed.
     * @param i Index of the task to be printed.
     * @return A string representing the Task.
     */
    public String listTaskAt(ArrayList<Task> tasks, int i) {
        Task current = tasks.get(i);
        int num = i + 1;
        return print( num + "." + current.toString());
    }

    /**
     * Prints the end message to the user.
     *
     * @return A string representing the end message to the user.
     */
    public String end() {
        return print("Bye, hope to see you soon!");
    }


}
