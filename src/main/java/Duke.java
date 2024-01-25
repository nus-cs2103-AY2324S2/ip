import java.util.Arrays;
import java.util.Scanner;

/**
 * A Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String[] COMMANDS = {
            "bye: Terminate the program.\n" +
            "list: Display the list of tasks.\n" +
            "mark <index>: Mark a task as done.\n" +
            "unmark <index>: Mark a task as not done.\n" +
            "todo <description>: Add a todo task.\n" +
            "deadline <description> /by <dueDate>: Add a deadline task.\n" +
            "event <description> /from <startDate> /to <endDate>: Add an event task.\n"

    };

    private Scanner scanner;

    private Task[] tasks;

    private int id;

    public Duke() {
        this.scanner = new Scanner(System.in);
        this.tasks = new Task[100];
        this.id = 0;
    }

    /**
     * Runs the program, processing user commands until the "bye" command is entered.
     * Recognized commands include list, mark, unmark, todo, deadline, event, and others.
     * The user is prompted with a greeting and can interact with the chatbot.
     */
    public void run() {
        System.out.println("Hello from\n" + logo);

        while (scanner.hasNextLine()) {
            try {
                String[] input = scanner.nextLine().split(" ", 2);
                String command = input[0];

                switch (command) {
                    case "hello":
                        System.out.println("Hey there! This is Dooloodoodooloodoo!\n" + "What can I do for you?\n");
                        break;
                    case "bye":
                        exit();
                        break;
                    case "list":
                        listTasks();
                        break;
                    case "mark":
                        markTaskAsDone(input);
                        break;
                    case "unmark":
                        markTaskAsUndone(input);
                        break;
                    case "todo":
                        addToDoTask(input);
                        break;
                    case "deadline":
                        addDeadline(input);
                        break;
                    case "event":
                        addEvent(input);
                        break;
                    case "help":
                        System.out.println(Arrays.toString(COMMANDS));
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what '" + command + "' means :-(\n" +
                                "Please enter 'help' command to find out more.");
                }
            } catch (DukeException e) {
                System.out.println("OOPS!!! An error occurred: " + e.getMessage());
            }

        }
    }

    /**
     * Adds a ToDo task to the task list.
     *
     * @param input A string array containing the task description.
     */
    public void addToDoTask(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("OOPS! The description of a todo cannot be left blank.\n" +
                    "Please enter 'help' command to find out more.");
        }
        ToDo todo = new ToDo(input[1]);
        addTasks(todo);
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @param input A string array containing the task description with the "/by" as separator.
     */
    public void addDeadline(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("OOPS! The description of a deadline cannot be left blank.\n" +
                    "Please enter 'help' command to find out more.");
        } else if (!input[1].contains("/by")) {
            throw new DukeException("OOPS! The date/time for the deadline cannot be left blank.\n" +
                    "Please enter 'help' command to find out more.");
        }
        String[] description = input[1].split("/by");
        Deadline dd = new Deadline(description[0], description[1]);
        addTasks(dd);
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param input A string array containing the task description with the "/from" and "/to" as separators.
     */
    public void addEvent(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("OOPS! The description of an event cannot be left blank.\n" +
                    "Please enter 'help' command to find out more.");
        } else if (!input[1].contains("/from") && !input[1].contains("/to")) {
            throw new DukeException("OOPS! The start time and end time cannot be left blank.\n" +
                    "Please enter 'help' command to find out more.");
        }
        String[] description = input[1].split("/from|/to");
        Event e = new Event(description[0], description[1], description[2]);
        addTasks(e);
    }

    /**
     * Marks a task as done.
     *
     * @param input A string array containing the index of the task to mark as done.
     */
    public void markTaskAsDone(String[] input) {
        int index = Integer.parseInt(input[1]);
        tasks[index - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index - 1].toString());
    }

    /**
     * Marks a task as undone.
     *
     * @param input A string array containing the index of the task to mark as undone.
     */
    public void markTaskAsUndone(String[] input) {
        int index = Integer.parseInt(input[1]);
        tasks[index - 1].markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[index - 1].toString());
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTasks(Task task) {
        tasks[id] = task;
        id++;
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.format("Now you have %d tasks in the list.\n", id);
    }

    /**
     * Prints out the list of tasks.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < id; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    /**
     * Terminates the Chitty-Chatty program.
     */
    public void exit() {
        System.out.println("Goodbye. Have a great day ahead!");
        System.exit(0);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

