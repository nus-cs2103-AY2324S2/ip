package reacher;
import reacher.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * In charge of user interactions.
 */
public class Ui {
    Scanner scanner;

    /**
     * Creates a Ui.
     */
    public Ui(){
        this.scanner = new Scanner(System.in);
    }
    /**
     * Prints welcome message.
     */
    public void printWelcome() {
        System.out.println("Hello!\n" +
                "I'm Reacher.\n" +
                "Give me tasks.\n" +
                "Functions are edit, list, add and bye");
    }

    /**
     * Prints all tasks in tasks.
     * @param tasks List of tasks.
     */
    public void printList(ArrayList<Task> tasks) {
        System.out.println("Tasks:");
        int c = 1;
        for (Task task : tasks) {
            System.out.println(c + task.toString());
            c++;
        }
    }

    /**
     * Takes in user input as a string.
     * @return user input.
     * @throws ReacherException If user input is empty.
     */
    public String readString() throws ReacherException {
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            throw new ReacherException("pls type a task name.");
        }
        return input;
    }

    /**
     * Takes in user input as an int.
     * @return user input.
     */
    public int readInt() {
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }

    /**
     * Prints message.
     * @param message String to be printed.
     */
    public void print(String message){
        System.out.println(message);
    }
}
