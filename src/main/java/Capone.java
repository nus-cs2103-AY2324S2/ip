import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

public class Capone {
    // We assume there is no more than 100 tasks added.
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void printWelcomeMsg() {
//        String logo = "░█▀▀░█▀█░█▀█░█▀█░█▀█░█▀▀░\n"
//                + "░█░░░█▀█░█▀▀░█░█░█░█░█▀▀░\n"
//                + "░▀▀▀░▀░▀░▀░░░▀▀▀░▀░▀░▀▀▀░";
//        System.out.printf("Hello! I'm \n%s\nWhat can I do for you?\n%n", logo);
        System.out.println("Hello! I'm Capone. What can I do for you?");
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
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < inputList.size(); i++) {
            if (i == inputList.size() - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        ToDo newTodo = new ToDo(description.toString());

        tasks.add(newTodo);

        System.out.printf("Got it. I've added this task:\n%s\n" +
                "Now you have %d task(s) in the list.\n", newTodo.toString(), tasks.size());
    }

    public static void processDeadline(ArrayList<String> inputList) {
        // TODO: Error checking if date is empty?
        int byNdx = inputList.indexOf("/by");

        // Combine the remaining words into a single string
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < byNdx; i++) {
            if (i == byNdx - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        StringBuilder byDate = new StringBuilder();
        for (int i = byNdx + 1; i < inputList.size(); i++) {
            if (i == inputList.size() - 1) {
                byDate.append(inputList.get(i));
                break;
            }
            byDate.append(inputList.get(i)).append(" ");
        }

        Deadline newDeadline = new Deadline(description.toString(), byDate.toString());

        tasks.add(newDeadline);

        System.out.printf("Got it. I've added this task:\n%s\n" +
                "Now you have %d task(s) in the list.\n", newDeadline.toString(), tasks.size());
    }

    public static void processEvent(ArrayList<String> inputList) {
        int fromNdx = inputList.indexOf("/from");
        int toNdx = inputList.indexOf("/to");

        // TODO: Error checking for invalid from and to inputs

        // Combine the task description into a single string.
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < fromNdx; i++) {
            if (i == fromNdx - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        StringBuilder fromDate = new StringBuilder();
        for (int i = fromNdx + 1; i < toNdx; i ++) {
            if (i == toNdx - 1) {
                fromDate.append(inputList.get(i));
                break;
            }
            fromDate.append(inputList.get(i)).append(" ");
        }

        StringBuilder toDate = new StringBuilder();
        for (int i = toNdx + 1; i < inputList.size(); i++) {
            if (i == inputList.size() - 1) {
                toDate.append(inputList.get(i));
                break;
            }
            toDate.append(inputList.get(i)).append(" ");
        }

        Event newEvent = new Event(description.toString(), fromDate.toString(), toDate.toString());

        tasks.add(newEvent);

        System.out.printf("Got it. I've added this task:\n%s\n" +
                "Now you have %d task(s) in the list.\n", newEvent.toString(), tasks.size());
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

        while (true) {
            // Read the user input
            String input = scanner.nextLine();
            input = input.strip();

            // Split inputs by space and store them in an arraylist for processing.
            ArrayList<String> inputList = new ArrayList<>(Arrays.asList(input.split("\\s+")));

            // TODO: Error checking for empty input?
            String firstWord = inputList.get(0);

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
            } else if (firstWord.equalsIgnoreCase("bye")) {
                break;
            } else {
                invalidCommand();
            }
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