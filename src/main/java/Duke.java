import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Tasks.Task;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.ToDo;

// main class for the project
public class Duke {

    /* class-wide variables */ 
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) { 

        // Initialize
        Scanner scanner = new Scanner(System.in);
        String input;
        String command;
        String description;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "================================================================";

        String greet = "Wassup! I'm someBOTy.\n"
                     + "What are you here for?\n";

        System.out.println(logo + line);    // PRINT LOGO
        System.out.println(greet + line);   // PRINT GREET MESSAGE

        while (true) {
            System.out.println("");       // new line for formatting
            input = scanner.nextLine();
            System.out.println(line);       // line for formatting

            command = input.split(" ")[0];

            try {

                if (command.equals("bye")) {
                    break;
                }
        
                switch (command) {
            
                case "list":
                    list();
                    break;

                case "help":
                    help();
                    break;

                case "mark":
                    description = getDescription(input);
                    mark(description);
                    break;

                case "unmark":
                    description = getDescription(input);
                    unmark(description);
                    break;

                case "delete":
                    description = getDescription(input);
                    delete(description);
                    break; 

                case "todo":
                    description = getDescription(input);
                    addTodo(description);
                    break;

                case "deadline":
                    description = getDescription(input);
                    addDeadline(description);
                    break;

                case "event":
                    description = getDescription(input);
                    addEvent(description);
                    break;

                default:
                    System.out.println(
                        "Command not recognized.\n"
                        + "Type 'help' to get the list of valid commands."
                    );
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(line);       // line for formatting
        }

        scanner.close();

        String exit_message = "Aight. Imma head out.\n" + line; // Print exit message
        System.out.println(exit_message);
    }


    private static String getDescription(String string) {
        String[] listOfStrings = string.split(" ");
        String description = "";

        if (listOfStrings.length <= 1) {
            throw new IllegalArgumentException("ERROR! Description is not recognized.");

        } else {
            for (int i = 1; i < listOfStrings.length; i++) {
                description += listOfStrings[i] + " ";
            }

            return description.trim();
        }

    }

    /* =============== FUNCTIONS TO HANDLE ACTIONS ================== */


    // list out the recorded tasks
    private static void list() {

        if (tasks.size() == 0) {    // special message for empty list
            System.out.println("Wow! You have no recorded task! Peek laziness here.");
            return;
        }

        System.out.println("Here are the tasks:");

        int index = 0;
        for (Task task : tasks) {
            System.out.println(String.valueOf(index + 1) + ". " + task);
            index++;
        }
    }

    // list out the available commands
    private static void help() {
        System.out.println("Here are the tasks:");

        String[] commands = {"deadline", "event", "help", "list", "mark", "todo", "unmark"};
        for (int i = 0; i < commands.length; i++) {
            System.out.println(String.format("%d. %s", i + 1, commands[i]));
        }

    }

    // mark a given task at the specified index as completed.
    private static void mark(String description) {
        int index;
        try {
            index = Integer.parseInt(description) - 1;
        } catch(NumberFormatException e) {
            System.out.println(
                "Unable to determine which task to mark. Please use the format:\n"
                + "mark LIST_NUMBER"
                );
            return;
        }
        
        if (index < 0 || index >= tasks.size()) {   // given index out of range
            System.out.println(">>> Bruh, there ain't no task " + String.valueOf(index + 1));

        } else {
            tasks.get(index).mark();
            System.out.println(">>> Wow. Can't believe you've done it.");
            System.out.println(tasks.get(index));
        }
    }


    // unmark a task at the specified index as incomplete.
    private static void unmark(String description) {
        int index;
        try {
            index = Integer.parseInt(description) - 1;
        } catch(NumberFormatException e) {
            System.out.println(
                "Unable to determine which task to unmark. Please use the format:\n"
                + "unmark LIST_NUMBER"
                );
            return;
        }
        
        if (index < 0 || index >= tasks.size()) { // given index out of range
            System.out.println(">>> Bruh, there ain't no task " + String.valueOf(index + 1));

        } else {
            tasks.get(index).unmark();
            System.out.println(">>> O...k... you'd better finish it later.");
            System.out.println(tasks.get(index));
        }
    }

    // delete a task at the specified index.
    private static void delete(String description) {
        int index;
        try {
            index = Integer.parseInt(description) - 1;
        } catch(NumberFormatException e) {
            System.out.println(
                "Unable to determine which task to delete. Please use the format:\n"
                + "delete LIST_NUMBER"
                );
            return;
        }

        if (index < 0 || index >= tasks.size()) { // given index out of range
            System.out.println(">>> Bruh, there ain't no task " + String.valueOf(index + 1));

        } else {
            Task removedTask = tasks.remove(index);

            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        }
    }

    // add a new task of type "ToDo"
    private static void addTodo(String description) {
        ToDo newTodo = new ToDo(description);
        tasks.add(newTodo);

        printTaskMessage(newTodo);
    }

    // add a new task of type "Deadline"
    private static void addDeadline(String description) {
        
        try {
            Deadline newDeadline = new Deadline(description);
            tasks.add(newDeadline);

            printTaskMessage(newDeadline);

        } catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // add a new task of type "Event"
    private static void addEvent(String description) {
        
        try {
            Event newEvent = new Event(description);
            tasks.add(newEvent);

            printTaskMessage(newEvent);

        } catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // output a message after adding a task to the list.
    private static void printTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        System.out.println("(Type 'list' to see the full list of tasks)");
    }

}
