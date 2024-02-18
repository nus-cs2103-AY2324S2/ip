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
     * Prints bye when the user exits the chatbot.
     *
     * @return Exit message to user.
     */
    public String printBye() {
        return line + "\n" + "Oink! Okie byee... See you soon! :)\n" + line;
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
        StringBuilder listString = new StringBuilder(line);

        if (tasks.isEmpty()) {
            // If the list is empty.
            listString.append("Oink! There are no tasks! Yeehaww");
        } else {
            // If the list is not empty.
            listString.append("Oink! Here are the tasks:");
            for (int i = 1; i <= tasks.getSize(); i++) {
                listString.append(i).append(". ").append(tasks.getTask(i - 1));
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
        StringBuilder markedTask = new StringBuilder(line);

        if (userInput.length() < 6) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> mark <task no.>\n");
        }

        int idx = userInput.charAt(5) - '0' - 1;
        markedTask.append(tasks.markTask(idx)).append(line);
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
        StringBuilder unmarkedTask = new StringBuilder(line);

        if (userInput.length() < 8) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> unmark <task no.>\n");
        }

        int idx = userInput.charAt(7) - '0' - 1;
        unmarkedTask.append(tasks.unmarkTask(idx)).append(line);
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
        StringBuilder deletedTask = new StringBuilder(line);

        if (userInput.length() < 8) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> delete <task no.>\n");
        }

        int idx = userInput.charAt(7) - '0' - 1;
        deletedTask.append(tasks.deleteTask(idx)).append(line);
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
        StringBuilder todoTask = new StringBuilder(line);

        String description = parser.parseTodo(userInput);
        Todo task = new Todo(description);

        todoTask.append(tasks.addTask(task)).append(line);
        return todoTask.toString();
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
        StringBuilder deadlineTask = new StringBuilder(line);

        try {
            String name = parser.parseDeadlineName(userInput);
            LocalDate deadline = parser.parseDeadlineDate(userInput);
            Deadline task = new Deadline(name, deadline);
            deadlineTask.append(tasks.addTask(task)).append(line);
        } catch (DateTimeParseException e) {
            deadlineTask.append("Oink! Invalid date format! Please follow:\n"
                    + ">> dd/MM/yyyy\n" + line);
        }
        return deadlineTask.toString();
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
        StringBuilder eventTask = new StringBuilder(line);

        String[] description = parser.parseEvent(userInput);
        Event task = new Event(description);

        eventTask.append(tasks.addTask(task)).append(line);
        return eventTask.toString();
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
        StringBuilder findTask = new StringBuilder(line);

        TaskList matchingTasks = parser.parseFind(userInput, tasks);
        if (matchingTasks.isEmpty()) {
            // If the list is empty.
            findTask.append("Oink! There are no matching tasks!");
        } else {
            // If the list is not empty.
            findTask.append("Oink! Here are the matching tasks in the list:");
            for (int i = 1; i <= matchingTasks.getSize(); i++) {
                findTask.append(i).append(". ")
                        .append(matchingTasks.getTask(i - 1));
            }
        }
        findTask.append(line);
        return findTask.toString();
    }
}
