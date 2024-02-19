import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * The main class of Mitsuki the Chat bot.
 *
 * @author Tee Chu jie
 */
public class Duke {
    /**
     * A list of commands that can be given to Mitsuki.
     */
     static ArrayList<String> commands = new ArrayList<>();

    /**
     * Initiating the scanner for user input.
     */
    static Scanner scan = new Scanner(System.in);

    /**
     * Initiating the user's todo list.
     */
    static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        // Greeting the user.
        System.out.println("Hello! My name is Mitsuki, nice to meet you!\n"
                + "What can I do for you today?\n");

        try {
            list = get("list.txt");
            System.out.println("I have loaded your previously saved list for you. :)");
        } catch(FileNotFoundException ex) {
            System.out.println("No saved list found. A new list will be used!");
            File saveFile = new File("list.txt");
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Adding the commands to the list of commands Mitsuki can execute.
        commands.add("help");
        commands.add("list");
        commands.add("todo");
        commands.add("deadline");
        commands.add("event");
        commands.add("mark");
        commands.add("unmark");
        commands.add("delete");
        commands.add("bye");



        // Initiating the 'command' variable.
        String command = "nil";

        // The loop that takes in user input and determine what to do.
        while (!command.equals(null)) {
            // Taking in next user input.
            command = scan.next();

            // Making sure user is giving a valid command.
            try {
                DukeException.validate(command);
            } catch(DukeException ex) {
                System.out.println("Sorry, I am unable to do that at the current moment.");
                System.out.println("Please type 'help' for a list of commands you can give me! :)");
            }

            // Calling the method based on what the user input is.
            switch (command) {
                case "help":
                    help();
                    break;

                case "deadline":
                    deadline();
                    break;

                case "todo":
                    todo();
                    break;

                case "event":
                    event();
                    break;

                case "list":
                    list();
                    break;

                case "mark":
                    mark();
                    break;

                case "unmark":
                    unmark();
                    break;

                case "delete":
                    delete();
                    break;

                case "bye":
                    try {
                        save("list.txt");
                    } catch(IOException ex) {
                        System.out.println("Something went wrong: " + ex.getMessage());
                    }
                    bye();

                default:
                    break;
            }
        }
    }

    /**
     * Lists all the commands the user can give Mitsuki.
     */
    public static void help() {
        System.out.println("Here is a list of commands you can give me:");
        for (int i = 0; i < commands.size(); i++) {
            int j = i + 1;
            System.out.println(j + ". " + commands.get(i));
        }
    }

    /**
     * Adds a deadline item to the user's list.
     * Uses '/' to separate the deadline from the task.
     * E.g. 'deadline Return book /By Sunday 2pm' adds the task
     *      [D][ ] Return book (By Sunday 2pm)
     *      to the user's list
     * Throws an exception if empty task description or no deadline is given.
     */
    public static void deadline() {
        String fullString = scan.nextLine();

        int endOfDesc = fullString.indexOf("/by");
        String description = fullString.substring(1, endOfDesc - 1);
        String deadline = fullString.substring(endOfDesc + 4);

        String[] tokens = {description, deadline};
        try {
            EmptyTaskException.timedValidate(tokens);
            MissingDeadlineException.validate(tokens);
        } catch(EmptyTaskException ex) {
            System.out.println("Please give a description for your deadline item. Try again!");
            return;
        } catch(MissingDeadlineException ex) {
            System.out.println("Please give a deadline for your deadline item. Try again! XD");
            System.out.println("E.g. Type 'deadline return book /by 12/20/2024' to add the task 'return book' " +
                    "\nwith a deadline of '20 Dec 2024' to your list.");
            return;
        }

        Task task = new Deadline(description, deadline);
        list.add(task);
        System.out.println("OK, I have added the task '" + description + "' to your list! :)");
        System.out.println("Now you have " + list.size() + " task(s) in the list.");
    }

    /**
     * Adds a todo item to the user's list.
     * E.g. 'todo Go running' adds the task
     *      [T][ ] Go running
     *      to the user's list.
     * Throws an exception if empty task description is given.
     */
    public static void todo() {
        String description1 = scan.nextLine();
        try {
            EmptyTaskException.validate(description1);
        } catch(EmptyTaskException ex) {
            System.out.println("Please give a description for your todo item. Try again!");
            return;
        }

        String trimmed1 = description1.trim();
        Task task1 = new Todo(trimmed1);
        list.add(task1);
        System.out.println("OK, I have added the task '" + trimmed1 + "' to your list! :)");
        System.out.println("Now you have " + list.size() + " task(s) in the list.");
    }

    /**
     * Adds an event item to the user's list.
     * Uses '/' to take in the timing of the event.
     * E.g. 'event Party /From Friday 8pm /to 11pm' adds the task
     *      [E][ ] Party (From Friday 8pm to 11pm)
     *      to the user's list.
     * Throws an exception if no task description, or no event start or end timing is given.
     */
    public static void event() {
        String fullString1 = scan.nextLine();
        String[] tokens1 = fullString1.split("/");

        try {
            EmptyTaskException.timedValidate(tokens1);
            MissingEventTimingException.validate(tokens1);
        } catch(EmptyTaskException ex) {
            System.out.println("Please give a description for your event item. Try again!");
            return;
        } catch(MissingEventTimingException ex) {
            System.out.println("Please give a time period for your event item. Try again! XD");
            System.out.println("E.g. Type 'event meeting /From Monday 10am /to 12pm' to add the task 'meeting' \n" +
                    "with a time period 'From Monday 10am to 12pm' to your list.");
            return;
        }

        String description2 = tokens1[0];
        String trimmed2 = description2.trim();
        String from = tokens1[1];
        String to = tokens1[2];
        String toTrimmed = to.trim();
        Task task2 = new Event(trimmed2, from, toTrimmed);
        list.add(task2);
        System.out.println("OK, I have added the task '" + trimmed2 + "' to your list! :)");
        System.out.println("Now you have " + list.size() + " task(s) in the list.");
    }

    /**
     * Displays the user's current todo list.
     */
    public static void list() {
        System.out.println("Here are the items in your list:");
        int i = 0;
        int j = 1;
        while (i < list.size()) {
            System.out.println(j + ". " + list.get(i).toString());
            i++;
            j++;
        }
    }

    /**
     * Marks an item on the user's todo list as done.
     * E.g. 'mark 3' marks the 3rd item on the list as done.
     * Informs user that the task is marked as done, and also displays the task.
     * If task was already marked as done, exception is thrown and user is informed that they
     * had already completed the task.
     */
    public static void mark() {
        int index = scan.nextInt();
        Task theTask = list.get(index - 1);

        try {
            AlreadyDoneException.validate(theTask);
        } catch(AlreadyDoneException ex) {
            System.out.println("You have already completed this task! :D");
            System.out.println(theTask);
            return;
        }

        theTask.markAsDone();
        System.out.println("Ok, I have marked this task as done. :D\n" + theTask);
    }

    /**
     * Marks an item on the user's todo list as not done.
     * E.g. 'unmark 3' marks the 3rd item on the list as not done.
     * Informs user that the task is marked as not done, and also displays the task.
     * If task was already marked as not done, exception is thrown and user is informed that they
     * had not yet completed the task.
     */
    public static void unmark() {
        int index1 = scan.nextInt();
        Task aTask = list.get(index1 - 1);

        try {
            WasNotDoneException.validate(aTask);
        } catch(WasNotDoneException ex) {
            System.out.println("You had not completed this task! :O");
            System.out.println(aTask);
            return;
        }

        aTask.markAsNotDone();
        System.out.println("Ok, I have marked this task as not done yet. :O\n" + aTask);
    }

    /**
     * Deletes an item from the user's todo list.
     * E.g. 'delete 3' deletes the 3rd item on the list.
     * Informs user that the task is deleted, and also displays the deleted task.
     */
    public static void delete() {
        int index2 = scan.nextInt();
        Task goneTask = list.get(index2 - 1);
        list.remove(goneTask);

        System.out.println("Alright, I have removed the following task from your list:\n" + goneTask);
        System.out.println("Now you have " + list.size() + " task(s) in the list.");
    }

    /**
     * Saves the user's list as a text file in the hard disk.
     * @param fileName the file name of the file to be saved.
     * @throws IOException if file cannot be created.
     */
    public static void save(String fileName) throws IOException {
        File list = new File(fileName);

        FileWriter saver = new FileWriter(list);

        for (int i = 0; i < Duke.list.size(); i++) {
            saver.write(Duke.list.get(i).toString() + System.lineSeparator());
        }

        System.out.println("I have saved your list for your future reference. :D");
        saver.close();
    }

    /**
     * Retrieves the saved list file from the hard disk.
     * @param fileName the file name of the file to be loaded.
     * @return an array list of tasks that the user previously added.
     * @throws FileNotFoundException if file does not exist yet.
     */
    public static ArrayList<Task> get(String fileName) throws FileNotFoundException {
        File list = new File(fileName);
        Scanner scanner = new Scanner(list);

        while (scanner.hasNext()) {
            String fullString = scanner.nextLine();
            if (fullString.contains("[T]")) {
                String description = fullString.substring(7);

                Task nextTask = new Todo(description);
                Duke.list.add(nextTask);

            } else if (fullString.contains("[D]")) {
                int descriptionEnd = fullString.indexOf('(');
                int deadlineEnd = fullString.indexOf(')');

                String description = fullString.substring(7, descriptionEnd - 1);
                String deadline = fullString.substring(descriptionEnd + 4, deadlineEnd);
                LocalDate processedDeadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("MMM d yyyy"));
                Task nextTask = new Deadline(description, processedDeadline);
                Duke.list.add(nextTask);

            } else if (fullString.contains("[E]")) {
                int descriptionEnd = fullString.indexOf('(');
                int startTimeEnd = fullString.indexOf("to");
                int endTimeEnd = fullString.indexOf(')');

                String description = fullString.substring(7, descriptionEnd - 2);
                String startTime = fullString.substring(descriptionEnd + 1, startTimeEnd - 1);
                String endTime = fullString.substring(startTimeEnd - 1, endTimeEnd);

                Task nextTask = new Event(description, startTime, endTime);
                Duke.list.add(nextTask);
            }
        }

        return Duke.list;
    }

    /**
     * Says goodbye to Mitsuki and exits the Chat bot.
     */
    public static void bye() {
        System.out.println("Bye! Hope to see you again soon!\n");
        scan.close();
        System.exit(0);
    }
}
