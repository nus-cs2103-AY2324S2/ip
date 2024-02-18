package util;

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
    private final String line = "_________________________________________";

    /**
     * Constructor for a UI object.
     */
    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Prints the introduction of the chatbot when the user
     * first enters the chatbot.
     *
     * @return Greetings to user.
     */
    public String printIntro() {
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
        return logo + line + "\n" + introduction + line;
    }

    /**
     * Prints the error message.
     *
     * @param msg error message
     * @return Error message to be printed.
     */
    public String printError(String msg) {
        return line + "\n" + msg + line;
    }

    /**
     * Prints the list of commands available.
     *
     * @return The whole help list as a String.
     */
    public String printHelp() {
        String help = "Oink! Here are the Command Words:\n'list' - displays the list of task\n"
                + "'todo ...' - to add new task\n'deadline ... /by dd/MM/yyyy' - to add task with deadline\n"
                + "'event ... /from ... /to ...' - to add an event\n"
                + "'mark <task no.>' - to mark a task done\n'unmark <task no.>' - to unmark a task\n"
                + "'find ...' - to find tasks with matching descriptions\n"
                + "'delete <task no.>' - to delete a task\n'bye' - to exit the chatbot\n";
        return line + "\n" + help + line;
    }

    /**
     * Iterates through the list and prints the tasks.
     *
     * @param tasks task list
     * @return The whole list as a String.
     */
    public String printList(TaskList tasks) {
        StringBuilder listString = new StringBuilder(line + "\n");

        if (tasks.isEmpty()) {
            // If the list is empty.
            listString.append("Oink! There are no tasks! Yeehaww\n");
        } else {
            // If the list is not empty.
            listString.append("Oink! Here are the tasks:\n");
            for (int i = 1; i <= tasks.getSize(); i++) {
                listString.append(i).append(". ")
                        .append(tasks.getTask(i - 1)).append("\n");
            }
        }
        listString.append(line);
        return listString.toString();
    }

    /**
     * Prints the task marked completed.
     *
     * @param userInput a string
     * @param tasks task list
     * @return String representation of the marked task.
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public String printMark(String userInput, TaskList tasks) throws InvalidTaskNameException {
        StringBuilder markedTask = new StringBuilder(line + "\n");

        if (userInput.length() < 6) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> mark <task no.>\n");
        }

        int idx = userInput.charAt(5) - '0' - 1;
        markedTask.append(tasks.markTask(idx)).append("\n").append(line);
        return markedTask.toString();
    }

    /**
     * Prints the task unmarked completed.
     *
     * @param userInput a string
     * @param tasks task list
     * @return String representation of the unmarked task.
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public String printUnmark(String userInput, TaskList tasks) throws InvalidTaskNameException {
        StringBuilder unmarkedTask = new StringBuilder(line + "\n");

        if (userInput.length() < 8) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> unmark <task no.>\n");
        }

        int idx = userInput.charAt(7) - '0' - 1;
        unmarkedTask.append(tasks.unmarkTask(idx)).append("\n").append(line);
        return unmarkedTask.toString();
    }

    /**
     * Deletes the task and prints the details of the deleted task.
     *
     * @param userInput a string
     * @param tasks task list
     * @return String representation of the deleted task.
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public String printDelete(String userInput, TaskList tasks) throws InvalidTaskNameException {
        StringBuilder deletedTask = new StringBuilder(line + "\n");

        if (userInput.length() < 8) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> delete <task no.>\n");
        }

        int idx = userInput.charAt(7) - '0' - 1;
        deletedTask.append(tasks.deleteTask(idx)).append("\n").append(line);
        return deletedTask.toString();
    }

    /**
     * Adds a todoTask and prints the details of the new task.
     *
     * @param userInput a string
     * @param tasks task list
     * @return String representation of the todoTask.
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public String printTodo(String userInput, TaskList tasks) throws InvalidTaskNameException {
        StringBuilder printTask = new StringBuilder(line + "\n");

        String description = parser.parseTodo(userInput);
        Todo task = new Todo(description);

        printTask.append(tasks.addTask(task)).append("\n").append(line);
        assert tasks.getTaskList().contains(task) : "Task should be in the list.";
        return printTask.toString();
    }

    /**
     * Adds a deadline task and prints the details of the new task.
     *
     * @param userInput a string
     * @param tasks task list
     * @return String representation of the deadlineTask.
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public String printDeadline(String userInput, TaskList tasks) throws InvalidTaskNameException {
        StringBuilder printTask = new StringBuilder(line + "\n");

        try {
            String name = parser.parseDeadlineName(userInput);
            LocalDate deadline = parser.parseDeadlineDate(userInput);
            Deadline task = new Deadline(name, deadline);
            printTask.append(tasks.addTask(task)).append("\n").append(line);
            assert tasks.getTaskList().contains(task) : "Task should be in the list.";
        } catch (DateTimeParseException e) {
            printTask.append("Oink! Invalid date format! Please follow:\n"
                    + ">> dd/MM/yyyy\n" + line);
        }
        return printTask.toString();
    }

    /**
     * Adds an event and prints the details of the new task.
     *
     * @param userInput a string.
     * @param tasks task list.
     * @return String representation of the eventTask.
     * @throws InvalidTaskNameException Check for invalid input format.
     */
    public String printEvent(String userInput, TaskList tasks) throws InvalidTaskNameException {
        StringBuilder printTask = new StringBuilder(line + "\n");

        String[] description = parser.parseEvent(userInput);
        Event task = new Event(description);

        printTask.append(tasks.addTask(task)).append("\n").append(line);
        assert tasks.getTaskList().contains(task) : "Task should be in the list.";
        return printTask.toString();
    }

    /**
     * Finds tasks that matches the keyword by user and prints the list of matching tasks.
     *
     * @param userInput a string.
     * @param tasks task list.
     * @return String representation of FindTask.
     * @throws ChrisPBaconException Check for invalid input format.
     */
    public String printFind(String userInput, TaskList tasks) throws ChrisPBaconException {
        StringBuilder printTasks = new StringBuilder(line + "\n");

        TaskList matchingTasks = parser.parseFind(userInput, tasks);
        if (matchingTasks.isEmpty()) {
            // If the list is empty.
            printTasks.append("Oink! There are no matching tasks!\n");
        } else {
            // If the list is not empty.
            printTasks.append("Oink! Here are the matching tasks in the list:\n");
            for (int i = 1; i <= matchingTasks.getSize(); i++) {
                printTasks.append(i).append(". ")
                        .append(matchingTasks.getTask(i - 1)).append("\n");
            }
        }
        printTasks.append(line);
        return printTasks.toString();
    }
}
