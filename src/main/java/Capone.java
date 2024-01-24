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

//    public static void addTask(ArrayList<String> inputList) {
//        // Combine the words back into a single string.
//        StringBuilder combinedString = new StringBuilder();
//        for (String s : inputList) {
//            combinedString.append(s).append(" ");
//        }
//
//        Task newTask = new Task(combinedString.toString());
//
//        // Store task in array and increment counter.
//        tasks.add(newTask);
//
//        // Inform user that task has been added.
//        System.out.printf("added: %s\n", newTask.toString());
//    }

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
        System.out.println("Nice! I've marked this task as done:\n" + markedTask);
    }

    public static void unmarkTask(int ndx) {
        // TODO: Add error checking for index out of bounds.

        // Mark task as done.
        Task unmarkedTask = tasks.get(ndx-1);
        unmarkedTask.unmarkTask();

        // Inform user that task has been marked.
        System.out.println("OK, I've marked this task as not done yet:\n" + unmarkedTask);
    }

    public static void processTodo(ArrayList<String> inputList) {
        // Combine the remaining words into a single string
        StringBuilder combinedString = new StringBuilder();
        for (int i = 1; i < inputList.size(); i++) {
            combinedString.append(inputList.get(i)).append(" ");
        }

        ToDo newTodo = new ToDo(combinedString.toString());

        tasks.add(newTodo);

        System.out.printf("Got it. I've added this task:\n%s\n" +
                "Now you have %d task(s) in the list.\n", newTodo.toString(), tasks.size());
    }

    public static void processDeadline(ArrayList<String> inputList) {
        String deadline = "";
        // Combine the remaining words into a single string
        StringBuilder combinedString = new StringBuilder();
        for (int i = 1; i < inputList.size(); i++) {
            if (inputList.get(i).equals("/by")) {
                deadline = inputList.get(i + 1);
                break;
            }

            combinedString.append(inputList.get(i)).append(" ");
        }

        Deadline newDeadline = new Deadline(combinedString.toString(), deadline);

        tasks.add(newDeadline);

        System.out.printf("Got it. I've added this task:\n%s\n" +
                "Now you have %d task(s) in the list.\n", newDeadline.toString(), tasks.size());
    }

    public static void processEvent(ArrayList<String> inputList) {

    }

    public static void invalidCommand() {
        System.out.println("I'm sorry, I don't understand what you just said.\n" +
                "Use -h to display the list of valid commands");

    }

    public static void displayHelp() {
        // TODO: Display help commands.
        System.out.println("placeholder");
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
            } else if (firstWord.equalsIgnoreCase("todo")) {
                processTodo(inputList);
            } else if (firstWord.equalsIgnoreCase("deadline")) {
                processDeadline(inputList);
            } else if (firstWord.equalsIgnoreCase("event")) {
                processEvent(inputList);
            } else {
                invalidCommand();
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