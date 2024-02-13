package seedu.chrisPBacon;

import seedu.exceptions.InvalidTaskNameException;
import seedu.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  This UI class contains methods that print out chatbot
 *  messages in the console to the user.
 */
public class Ui {
    private final String line = "_____________________________________________________";

    /**
     * Constructor for a UI object.
     */
    public Ui() { }

    /**
     * printIntro() prints the introduction of the chatbot when the user
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
     * printBye() prints bye when the user exits the chatbot.
     */
    public void printBye() {
        System.out.println(line + "\n" + "Oink! Okie byee... See you soon! :)\n" + line);
    }

    /**
     * printError() prints error message.
     *
     * @param msg error message
     */
    public void printError(String msg) {
        System.out.println(line + "\n" + msg + line);
    }

    /**
     * printHelp() prints the list of commands available.
     */
    public void printHelp() {
        String help = "Oink! Here are the Command Words:\n'list' - displays the list of task\n"
                + "'todo ...' - to add new task\n'deadline ... /by dd/MM/yyyy' - to add task with deadline\n"
                + "'event ... /from ... /to ...' - to add an event\n"
                + "'mark <task no.>' - to mark a task done\n'unmark <task no.>' - to unmark a task\n"
                + "'delete <task no.>' - to delete a task\n'bye' - to exit the chatbot\n";
        System.out.println(line + "\n" + help + line);
    }

    /**
     * printList() prints the list of tasks.
     *
     * @param tasks task list
     */
    public void printList(TaskList tasks) {
        System.out.println(line);
        if (tasks.isEmpty()) {
            System.out.println("Oink! There are no tasks! Yeehaww");

        } else {
            System.out.println("Oink! Here are the tasks:");
            for (int i = 1; i <= tasks.getSize(); i++) {
                System.out.println(i + ". " + tasks.getTask(i - 1));
            }
        }
        System.out.println(line);
    }

    /**
     * printMark() prints the task marked completed.
     *
     * @param userInput a string
     * @param tasks task list
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
     * printUnmark() prints the task unmarked completed.
     *
     * @param userInput a string
     * @param tasks task list
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
     * printDelete() deletes and prints the deleted task.
     *
     * @param userInput a string
     * @param tasks task list
     */
    public void printDelete(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(line);
        if (userInput.length() < 8) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> delete <task no.>\n");
        }
        int idx = userInput.charAt(7) - '0'- 1;
        System.out.println(tasks.deleteTask(idx) + line);
    }

    /**
     * printTodo() adds a todo task and prints the new task.
     *
     * @param userInput a string
     * @param tasks task list
     */
    public void printTodo(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(line);
        if (userInput.length() < 6) {
            // If user did not input task name.
            throw new InvalidTaskNameException("Ooink oink! What's the name of your task?\n"
                    + " >> todo ...\n");
        }
        Todo task = new Todo(userInput.substring(5));
        System.out.println(tasks.addTask(task) + line);
    }

    /**
     * printDeadline() adds a deadline task and prints the new task.
     *
     * @param userInput a string
     * @param tasks task list
     */
    public void printDeadline(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(line);
        int len = userInput.length();
        int idx = userInput.indexOf("/by");
        boolean isWrongInput = len < 10 || idx < 0 || len < idx + 4;
        if (isWrongInput) {
            // If user did not input task description.
            throw new InvalidTaskNameException("Ooink oink! Please describe your deadline >.<\n"
                    + " >> deadline ... /by dd/MM/yyyy\n");
        }
        String name = userInput.substring(9, idx - 1);
        String date = userInput.substring(idx + 4);
        try {
            LocalDate deadline = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy"));
            Deadline task = new Deadline(name, deadline);
            System.out.println(tasks.addTask(task) + line);
        } catch (DateTimeParseException e) {
            System.out.println("Oink! Invalid date format! Please follow:\n"
                    + ">> dd/MM/yyyy\n" + line);
        }
    }

    /**
     * printEvent() adds an event and prints the new task.
     *
     * @param userInput a string
     * @param tasks task list
     */
    public void printEvent(String userInput, TaskList tasks) throws InvalidTaskNameException {
        System.out.println(line);
        int len = userInput.length();
        int fromIdx = userInput.indexOf("/from");
        int toIdx = userInput.indexOf("/to");
        boolean isWrongInput = len < 7 || fromIdx < 0 || toIdx < 0
                || len < fromIdx + 6 || len < toIdx + 4;
        if (isWrongInput) {
            // If user did not input task description.
            throw new InvalidTaskNameException("Ooink oink! Please describe your event >.<\n"
                    + " >> event ... /from ... /to ...\n");
        }
        String name = userInput.substring(6, fromIdx - 1);
        String from = userInput.substring(fromIdx + 6, toIdx - 1);
        String to = userInput.substring(toIdx + 4);
        Event task = new Event(name, from, to);
        System.out.println(tasks.addTask(task) + line);
    }
}