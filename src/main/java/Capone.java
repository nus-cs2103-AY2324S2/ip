import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Capone {
    // We assume there is no more than 100 tasks added.
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void printWelcomeMsg() {
        String logo = "░█▀▀░█▀█░█▀█░█▀█░█▀█░█▀▀░\n"
                + "░█░░░█▀█░█▀▀░█░█░█░█░█▀▀░\n"
                + "░▀▀▀░▀░▀░▀░░░▀▀▀░▀░▀░▀▀▀░";
        System.out.printf("Hello! I'm\n%s\nWhat can I do for you?\n%n", logo);
    }

    public static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i+1, tasks.get(i).toString());
        }
    }

    public static void markTask(ArrayList<String> inputList) throws CaponeException {
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter an index of a task you'd like to mark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: mark [index]");
        } else if (inputList.size() > 2) {
            throw new CaponeException("Please enter only one index you would like to mark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: mark [index]");
        }

        // Mark task as done.
        try {
            Task markedTask = tasks.get(Integer.parseInt(inputList.get(1))-1);
            markedTask.markTask();

            // Inform user that task has been marked.
            System.out.println("Nice! I've marked this task as done:\n" + markedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CaponeException("Sorry, you have entered an invalid index.\n" +
                    "You can check the list of valid indices using the 'list' command.");
        }
    }

    public static void unmarkTask(ArrayList<String> inputList) throws CaponeException{
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter an index of a task you'd like to unmark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: unmark [index]");
        } else if (inputList.size() > 2) {
            throw new CaponeException("Please enter only one index you would like to unmark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: unmark [index]");
        }

        try {
            Task unmarkedTask = tasks.get(Integer.parseInt(inputList.get(1))-1);
            unmarkedTask.unmarkTask();

            // Inform user that task has been marked.
            System.out.println("OK, I've marked this task as not done yet:\n" + unmarkedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CaponeException("Sorry, you have entered an invalid index.\n" +
                    "You can check the list of valid indices using the 'list' command.");
        }
    }

    public static void processTodo(ArrayList<String> inputList) throws CaponeException {
        // If the inputList has only one string, throw error (insufficient args).
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter a description for this ToDo task!\n" +
                    "Usage: todo [description]");
        }

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

    public static void processDeadline(ArrayList<String> inputList) throws CaponeException {
        // If the inputList has only one string, throw error (insufficient args).
        if (inputList.size() == 1) {
            throw new CaponeException("Insufficient arguments!\n" +
                    "Usage: deadline [description] /by [date]");
        }

        int byNdx = inputList.indexOf("/by");

        // Catch potential erros from date entry.
        if (byNdx == inputList.size() - 1 || byNdx == -1) {
            throw new CaponeException("Please enter a date for this deadline task!\n" +
                    "Usage: deadline [description] /by [date]");
        }

        // Combine the remaining words into a single string
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < byNdx; i++) {
            if (i == byNdx - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        if (description.toString().equalsIgnoreCase("")) {
            throw new CaponeException("Insufficient arguments!\n" +
                    "Usage: deadline [description] /by [date]");
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

    public static void processEvent(ArrayList<String> inputList) throws CaponeException {
        // If the inputList has only one string, throw error (insufficient args).
        if (inputList.size() == 1) {
            throw new CaponeException("Insufficient arguments!\n" +
                    "Usage: event [description] /from [date] /to [date]");
        }

        int fromNdx = inputList.indexOf("/from");
        int toNdx = inputList.indexOf("/to");

        if (toNdx < fromNdx) {
            throw new CaponeException("Please input from date followed by to date!\n" +
                    "Usage: event [description] /from [date] /to [date]");
        }

        // Catch potential errors from date entry.
        if (fromNdx == -1 || toNdx == -1 || toNdx - fromNdx == 1 || fromNdx - toNdx == 1 ||
                fromNdx == inputList.size() - 1 || toNdx == inputList.size() - 1) {
            throw new CaponeException("Please enter a start and end date!\n" +
                    "Usage: event [description] /from [date] /to [date]");
        }

        // Combine the task description into a single string.
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < fromNdx; i++) {
            if (i == fromNdx - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        if (description.toString().equalsIgnoreCase("")) {
            throw new CaponeException("Insufficient arguments!\n" +
                    "Usage: event [description] /from [date] /to [date]");
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

    public static void deleteTask(ArrayList<String> inputList) throws CaponeException{
        // If the inputList has more than two arguments, throw exception.
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter an index of a task you'd like to delete.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: delete [index]");
        } else if (inputList.size() > 2) {
            throw new CaponeException("Please enter only one index you would like to delete.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: delete [index]");
        }

        try {
            // Remove the task from the tasks Arraylist.
            Task removedTask = tasks.remove(Integer.parseInt(inputList.get(1))-1);

            System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n",
                    removedTask.toString(), tasks.size());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CaponeException("Sorry, you have entered an invalid index.\n" +
                    "You can check the list of valid indices using the 'list' command.");
        }
    }

    public static void invalidCommand() throws CaponeException{
        throw new CaponeException("I'm sorry, I don't understand what you just said.\n" +
                "Use -h to display the list of valid commands");

    }

    public static void displayHelp() {
        System.out.println("Commands I understand:\n" +
                "1. list - Lists the tasks entered.\n" +
                "2. todo [description] - Creates a new ToDo task. Remember to enter the description!\n" +
                "3. deadline [description] /by [date] - Creates a new Deadline task.\n" +
                "   Remember to enter the description and date!\n" +
                "4. event [description] /from [date] /to [date] - Creates a new Event task.\n" +
                "   Remember to enter the description, as well as the start and end date!\n" +
                "5. mark [index] - Marks a task as completed. Use this in conjunction with the 'list' command!\n" +
                "6. unmark [index] - Unmarks a task. Use this in conjunction with the 'list' command!\n" +
                "7. delete [index] - Deletes a task. Use this in conjunction with the 'list' command!");
    }

    public static void processInputs() {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Read the user input
            String input = scanner.nextLine();

            // Split inputs by space and store them in an arraylist for processing.
            ArrayList<String> inputList = new ArrayList<>(Arrays.asList(input.split("\\s+")));

            String firstWord = inputList.get(0);
            try {
                if (firstWord.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (firstWord.equalsIgnoreCase("mark")) {
                    markTask(inputList);
                } else if (firstWord.equalsIgnoreCase("unmark")) {
                    unmarkTask(inputList);
                } else if (firstWord.equalsIgnoreCase("todo")) {
                    processTodo(inputList);
                } else if (firstWord.equalsIgnoreCase("deadline")) {
                    processDeadline(inputList);
                } else if (firstWord.equalsIgnoreCase("event")) {
                    processEvent(inputList);
                } else if (firstWord.equalsIgnoreCase("delete")) {
                    deleteTask(inputList);
                } else if (firstWord.equalsIgnoreCase("bye")) {
                    break;
                } else if (firstWord.equalsIgnoreCase("-h")) {
                    displayHelp();
                } else {
                    invalidCommand();
                }
            } catch (CaponeException e) {
                System.out.println(e.getMessage());
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