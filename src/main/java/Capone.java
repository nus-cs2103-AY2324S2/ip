import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

public class Capone {
    // We assume there is no more than 100 tasks added.
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void printWelcomeMsg() {
        String logo = "░█▀▀░█▀█░█▀█░█▀█░█▀█░█▀▀░\n"
                + "░█░░░█▀█░█▀▀░█░█░█░█░█▀▀░\n"
                + "░▀▀▀░▀░▀░▀░░░▀▀▀░▀░▀░▀▀▀░";
        System.out.printf("Hello! I'm \n%s\nWhat can I do for you?\n%n", logo);
    }

    public static void addTask(String task) {
        // Store task in array and increment counter.
        tasks.add(new Task(task));

        // Inform user that task has been added.
        System.out.printf("added: %s\n", task);
    }

    public static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i+1, tasks.get(i).toString());
        }
    }

    public static void markTask(int ndx) {
        // TODO: Add error checking for index out of bounds.

        // Mark task as done.
        Task markedTask = tasks.get(ndx-1);
        markedTask.markTask();

        // Inform user that task has been marked.
        System.out.printf("Nice! I've marked this task as done:\n" + markedTask);
    }

    public static void unmarkTask(int ndx) {
        // TODO: Add error checking for index out of bounds.

        // Mark task as done.
        Task unmarkedTask = tasks.get(ndx-1);
        unmarkedTask.unmarkTask();

        // Inform user that task has been marked.
        System.out.printf("OK, I've marked this task as not done yet:\n" + unmarkedTask);
    }

    public static void processInputs() {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Read the user input
        String input = scanner.nextLine();

        String pattern_mark = "^mark.*";

        // Split inputs by space and store them in an arraylist for processing.
        ArrayList<String> inputList = new ArrayList<>(Arrays.asList(input.split("\\s+")));

        // TODO: Error checking for empty input?
        String firstWord = inputList.get(0);

        while (!firstWord.equalsIgnoreCase("bye")) {

            if (firstWord.equalsIgnoreCase("list")) {
                listTasks();
            } else if (firstWord.equalsIgnoreCase("mark")) {
                // TODO: Error checking for insufficient args/out of bounds.
                markTask(Integer.parseInt(inputList.get(1)));
            } else if (firstWord.equalsIgnoreCase("unmark")) {
                // TODO: Error checking for insufficient args/out of bounds.
                unmarkTask(Integer.parseInt(inputList.get(1)));
            } else {
                addTask(firstWord);
            }

            // Read and process the user's next input
            input = scanner.nextLine();
            inputList = new ArrayList<>(Arrays.asList(input.split("\\s+")));
            // TODO: Error checking for empty input?
            firstWord = inputList.get(0);
        }

        // If user entered "bye", exit program. Clean up.
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
    public static void main(String[] args) {

        Capone.printWelcomeMsg();

        Capone.processInputs();
    }
}