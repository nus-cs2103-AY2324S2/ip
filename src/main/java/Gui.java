import Exceptions.InvalidTaskNameException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  This GUI class contains methods that print out chatbot
 *  messages in the console to the user.
 */
public class Gui extends Duke {
    private final String line = "_____________________________________________________";

    /**
     * Constructor for a GUI object.
     */
    public Gui() { }

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
     */
    public void printList() {
        System.out.println(line);
        if (taskList.isEmpty()) {
            System.out.println("Oink! There are no tasks! Yeehaww");

        } else {
            System.out.println("Oink! Here are the tasks:");
            for (int i = 1; i <= taskList.size(); i++) {
                System.out.println(i + ". " + taskList.get(i - 1));
            }
        }
        System.out.println(line);
    }

    /**
     * printMark() prints the task marked completed.
     *
     * @param userInput a string
     */
    public void printMark(String userInput) throws InvalidTaskNameException {
        System.out.println(line);
        if (userInput.length() < 6) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> mark <task no.>\n");
        }
        int idx = userInput.charAt(5) - '0' - 1;
        taskList.get(idx).markDone();
        System.out.println(line);
    }

    /**
     * printUnmark() prints the task unmarked completed.
     *
     * @param userInput a string
     */
    public void printUnmark(String userInput) throws InvalidTaskNameException {
        System.out.println(line);
        if (userInput.length() < 8) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> unmark <task no.>\n");
        }
        int idx = userInput.charAt(7) - '0' - 1;
        taskList.get(idx).markUndone();
        System.out.println(line);
    }

    /**
     * printDelete() deletes and prints the deleted task.
     *
     * @param userInput a string
     */
    public void printDelete(String userInput) throws InvalidTaskNameException {
        System.out.println(line);
        if (userInput.length() < 8) {
            throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                    + " >> delete <task no.>\n");
        }
        int idx = userInput.charAt(7) - '0'- 1;
        taskList.get(idx).printDeleteTask(taskList.size());
        taskList.remove(idx);
        System.out.println(line);
    }

    /**
     * printTodo() adds a todo task and prints the new task.
     *
     * @param userInput a string
     */
    public void printTodo(String userInput) throws InvalidTaskNameException {
        System.out.println(line);
        if (userInput.length() < 6) {
            // If user did not input task name.
            throw new InvalidTaskNameException("Ooink oink! What's the name of your task?\n"
                    + " >> todo ...\n");
        }
        Todo t = new Todo(userInput.substring(5));
        taskList.add(t);
        t.printAddTask(taskList.size());
        System.out.println(line);
    }

    /**
     * printDeadline() adds a deadline task and prints the new task.
     *
     * @param userInput a string
     */
    public void printDeadline(String userInput) throws InvalidTaskNameException {
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
            Deadline d = new Deadline(name, deadline);
            taskList.add(d);
            d.printAddTask(taskList.size());
            System.out.println(line);
        } catch (DateTimeParseException e) {
            System.out.println("Oink! Invalid date format! Please follow:\n"
                    + ">> dd/MM/yyyy\n" + line);
        }
    }

    /**
     * printEvent() adds an event and prints the new task.
     *
     * @param userInput a string
     */
    public void printEvent(String userInput) throws InvalidTaskNameException {
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
        Event e = new Event(name, from, to);
        taskList.add(e);
        e.printAddTask(taskList.size());
        System.out.println(line);
    }
}
