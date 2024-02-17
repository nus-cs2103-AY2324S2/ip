package chrisPBacon;

import exceptions.InvalidTaskNameException;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *  This UI class contains methods that print out chatbot messages in the console to the user.
 */
public class Ui {
    private final Parser parser;
    private final String LINE = "_____________________________________________________";

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
        System.out.println(logo + LINE + "\n" + introduction + LINE);
    }

    /**
     * Prints bye when the user exits the chatbot.
     */
    public void printBye() {
        System.out.println(LINE + "\n" + "Oink! Okie byee... See you soon! :)\n" + LINE);
    }

    /**
     * Prints the error message.
     *
     * @param msg error message
     */
    public void printError(String msg) {
        System.out.println(LINE + "\n" + msg + LINE);
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
        System.out.println(LINE + "\n" + help + LINE);
    }

    /**
     * Iterates through the list and prints the tasks.
     *
     * @param tasks task list
     */
    public void printList(TaskList tasks) {
        System.out.println(LINE);

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

        System.out.println(LINE);
    }

    /**
     * Prints the task marked completed.
     *
     * @param userInput a string
     * @param tasks task list
     */
    public void printMark(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(LINE);

        if (userInput.length() < 6) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> mark <task no.>\n");
        }

        int idx = userInput.charAt(5) - '0' - 1;
        System.out.println(tasks.markTask(idx) + LINE);
    }

    /**
     * Prints the task unmarked completed.
     *
     * @param userInput a string
     * @param tasks task list
     */
    public void printUnmark(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(LINE);

        if (userInput.length() < 8) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> unmark <task no.>\n");
        }

        int idx = userInput.charAt(7) - '0' - 1;
        System.out.println(tasks.unmarkTask(idx) + LINE);
    }

    /**
     * Deletes the task and prints the details of the deleted task.
     *
     * @param userInput a string
     * @param tasks task list
     */
    public void printDelete(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(LINE);

        if (userInput.length() < 8) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> delete <task no.>\n");
        }

        int idx = userInput.charAt(7) - '0'- 1;
        System.out.println(tasks.deleteTask(idx) + LINE);
    }

    /**
     * Adds a todo task and prints the details of the new task.
     *
     * @param userInput a string
     * @param tasks task list
     */
    public void printTodo(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(LINE);

        String description = parser.parseTodo(userInput);
        Todo task = new Todo(description);

        System.out.println(tasks.addTask(task) + LINE);
    }

    /**
     * Adds a deadLINE task and prints the details of the new task.
     *
     * @param userInput a string
     * @param tasks task list
     */
    public void printDeadline(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(LINE);

        try {
            String name  = parser.parseDeadlineName(userInput);
            LocalDate deadline = parser.parseDeadlineDate(userInput);
            Deadline task = new Deadline(name, deadline);
            System.out.println(tasks.addTask(task) + LINE);
        } catch (DateTimeParseException e) {
            System.out.println("Oink! Invalid date format! Please follow:\n"
                    + ">> dd/MM/yyyy\n" + LINE);
        }
    }

    /**
     * Adds an event and prints the details of the new task.
     *
     * @param userInput a string
     * @param tasks task list
     */
    public void printEvent(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(LINE);

        String[] description = parser.parseEvent(userInput);
        Event task = new Event(description);

        System.out.println(tasks.addTask(task) + LINE);
    }

    public void printFind(String userInput, TaskList tasks) throws InvalidTaskNameException{

    }
}