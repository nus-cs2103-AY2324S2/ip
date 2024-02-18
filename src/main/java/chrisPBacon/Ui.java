package chrisPBacon;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exceptions.ChrisPBaconException;
import exceptions.InvalidTaskNameException;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

/**
 *  This UI class contains methods that print out chatbot messages in the console to the user.
 */
public class Ui {
    private final Parser parser;
    private final String line = "_____________________________________________________";

    /**
     * Constructor for a UI object.
     */
    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Prints the introduction of the chatbot when the user
     * first enters the chatbot.
     */
    public void printIntro() {
        String introduction = "Oink Oink!\nI'm Chris P Bacon but you can call me ChrisP! Oink!\n"
                + "What can I do for you today? :D\n"
                + "~Type 'help' for more command info~\n";
        String logo = "                             -------,\n"
                + "                            |   --  |\n"
                + "                            |  |  | |\n"
                + " ---- -   -  ---  ---  ---  |   --  |\n"
                + "|     |   | |   |  |  |   | |  ,----'\n"
                + "|     |---| |---   |   `-_  |  |     \n"
                + "|     |   | | \\    |  |   | |  |     \n"
                + " ---- -   - -  -  ---  ---   --      \n";
        System.out.println(logo + line + "\n" + introduction + line);
    }

    /**
     * Prints bye when the user exits the chatbot.
     */
    public void printBye() {
        System.out.println(line + "\n" + "Oink! Okie byee... See you soon! :)\n" + line);
    }

    /**
     * Prints the error message.
     *
     * @param msg error message
     */
    public void printError(String msg) {
        System.out.println(line + "\n" + msg + line);
    }

    /**
     * Prints the list of commands available.
     */
    public void printHelp() {
        String help = "Oink! Here are the Command Words:\n'list' - displays the list of task\n"
                + "'todo ...' - to add new task\n'deadline ... /by dd/MM/yyyy' - to add task with deadline\n"
                + "'event ... /from ... /to ...' - to add an event\n"
                + "'mark <task no.>' - to mark a task done\n'unmark <task no.>' - to unmark a task\n"
                + "'find ...' - to find tasks with matching descriptions\n"
                + "'delete <task no.>' - to delete a task\n'bye' - to exit the chatbot\n";
        System.out.println(line + "\n" + help + line);
    }

    /**
     * Iterates through the list and prints the tasks.
     *
     * @param tasks task list
     */
    public void printList(TaskList tasks) {
        System.out.println(line);

        if (tasks.isEmpty()) {
            // If the list is empty.
            System.out.println("Oink! There are no tasks! Yeehaww");
        } else {
            // If the list is not empty.
            System.out.println("Oink! Here are the tasks:");
            for (int i = 1; i <= tasks.getSize(); i++) {
                System.out.println(i + ". " + tasks.getTask(i - 1));
            }
        }

        System.out.println(line);
    }

    /**
     * Prints the task marked completed.
     *
     * @param userInput a string
     * @param tasks task list
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public void printMark(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(line);

        if (userInput.length() < 6) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> mark <task no.>\n");
        }

        int idx = userInput.charAt(5) - '0' - 1;
        System.out.println(tasks.markTask(idx) + line);
    }

    /**
     * Prints the task unmarked completed.
     *
     * @param userInput a string
     * @param tasks task list
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public void printUnmark(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(line);

        if (userInput.length() < 8) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> unmark <task no.>\n");
        }

        int idx = userInput.charAt(7) - '0' - 1;
        System.out.println(tasks.unmarkTask(idx) + line);
    }

    /**
     * Deletes the task and prints the details of the deleted task.
     *
     * @param userInput a string
     * @param tasks task list
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public void printDelete(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(line);

        if (userInput.length() < 8) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> delete <task no.>\n");
        }

        int idx = userInput.charAt(7) - '0' - 1;
        System.out.println(tasks.deleteTask(idx) + line);
    }

    /**
     * Adds a todo task and prints the details of the new task.
     *
     * @param userInput a string
     * @param tasks task list
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public void printTodo(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(line);

        String description = parser.parseTodo(userInput);
        Todo task = new Todo(description);

        System.out.println(tasks.addTask(task) + line);
    }

    /**
     * Adds a deadline task and prints the details of the new task.
     *
     * @param userInput a string
     * @param tasks task list
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public void printDeadline(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(line);

        try {
            String name = parser.parseDeadlineName(userInput);
            LocalDate deadline = parser.parseDeadlineDate(userInput);
            Deadline task = new Deadline(name, deadline);
            System.out.println(tasks.addTask(task) + line);
        } catch (DateTimeParseException e) {
            System.out.println("Oink! Invalid date format! Please follow:\n"
                    + ">> dd/MM/yyyy\n" + line);
        }
    }

    /**
     * Adds an event and prints the details of the new task.
     *
     * @param userInput a string.
     * @param tasks task list.
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public void printEvent(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(line);

        String[] description = parser.parseEvent(userInput);
        Event task = new Event(description);

        System.out.println(tasks.addTask(task) + line);
    }

    /**
     * Finds tasks that matches the keyword by user and prints the list of matching tasks.
     *
     * @param userInput a string.
     * @param tasks task list.
     * @throws ChrisPBaconException Check for invalid input format.
     */
    public void printFind(String userInput, TaskList tasks) throws ChrisPBaconException {
        System.out.println(line);

        TaskList matchingTasks = parser.parseFind(userInput, tasks);
        if (matchingTasks.isEmpty()) {
            // If the list is empty.
            System.out.println("Oink! There are no matching tasks!");
        } else {
            // If the list is not empty.
            System.out.println("Oink! Here are the matching tasks in the list:");
            for (int i = 1; i <= matchingTasks.getSize(); i++) {
                System.out.println(i + ". " + matchingTasks.getTask(i - 1));
            }
        }

        System.out.println(line);
    }
}
