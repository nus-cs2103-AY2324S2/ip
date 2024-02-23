package joy;
import joy.task.Deadline;
import joy.task.Event;

import joy.task.Todo;








/**
 * Parser class parses user input and executes corresponding commands.
 * Provides methods to handle various commands such as adding tasks, marking tasks as done, etc.
 */
public class Parser {

    /**
     * Constructs a Parser object.
     */
    public Parser() {

    }

    /**
     * Parses user input and executes the corresponding command.
     *
     * @param userInput The user input to be parsed and executed.
     * @param tasks     The TaskList object to perform operations on.
     * @param ui        The Ui object for user interface interactions.
     * @throws JoyException If an error occurs during the parsing or execution of the command.
     */
    public static String parseAndExecute(String userInput, TaskList tasks, Ui ui)
            throws JoyException {

        assert userInput != null : "User input cannot be null";
        assert tasks != null : "Task list cannot be null";
        assert ui != null : "UI object cannot be null";


        String[] words = userInput.split(" ");

        assert words != null && words.length > 0 : "Parsed words array cannot be null or empty";



        switch (words[0].toUpperCase()) {
        case "BYE":
            // Handle BYE command
            //System.exit(0);
            return ui.showGoodbyeMessage();

        case "LIST":
            // Handle LIST command

            return tasks.listTasks(ui);
            //break;
        case "TODO":

            try {
                return parseTodo(words, tasks);
            } catch (JoyException e) {
                return ui.showError(e.getMessage());
            }
        case "DELETE":

            if (words.length != 2) {
                throw new JoyException("The task number to delete is missing.");
            }
            int n = Integer.parseInt(words[1]);
            return tasks.removeTasks(n, ui);


        case "MARK":
            // Handle MARK command


            if (words.length != 2) {
                throw new JoyException("The task number to mark is missing.");
            }
            int num = Integer.parseInt(words[1]);
            return tasks.markTasks(num, ui);


        case "DEADLINE":
            try {
                return parseDeadline(words, tasks);
            } catch (JoyException e) {
                return ui.showError(e.getMessage());
            }

        case "EVENT":
            try {
                return parseEvent(words, tasks);
            } catch (JoyException e) {
                return ui.showError(e.getMessage());
            }


        case "FIND":
            return parseFind(words, ui);
            //break;
        default:
            throw new JoyException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses a deadline command and adds the corresponding task to the TaskList.
     *
     * @param tasks The TaskList object to add the task to.
     * @throws JoyException If an error occurs during the parsing or execution of the command.
     */
    public static String parseDeadline(String[] words, TaskList tasks) throws JoyException {


        if (words.length < 4) {
            throw new JoyException.DeadlineException("Insufficient information for creating a deadline task.");
        }

        if (words[1].isEmpty() || words[3].isEmpty()) {
            try {
                throw new JoyException.DeadlineException("OOPS!!! The description and deadline time cannot be empty.");
            } catch (JoyException e) {
                System.out.println(e.getMessage());
                //return;
            }
        }
        if (!words[2].equals("/by")) {
            try {
                throw new JoyException.DeadlineException("OOPS!!! Please provide a deadline time using '/by'.");
            } catch (JoyException e) {
                System.out.println(e.getMessage());
                //return;
            }
        }

        StringBuilder descriptionBuilder = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/by")) {
                break;
            }
            descriptionBuilder.append(words[i]).append(" ");
        }
        String description = descriptionBuilder.toString().trim();

        StringBuilder timeBuilder = new StringBuilder();
        boolean isDeadline = false;
        for (int i = 1; i < words.length; i++) {
            if (isDeadline) {
                timeBuilder.append(words[i]).append(" ");
            }
            if (words[i].equals("/by")) {
                isDeadline = true;
            }
        }
        String time = timeBuilder.toString().trim();
        return tasks.addTasks(new Deadline(description, time));


    }

    /**
     * Parses an event command and adds the corresponding task to the TaskList.
     *
     * @param tasks The TaskList object to add the task to.
     * @throws JoyException If an error occurs during the parsing or execution of the command.
     */
    public static String parseEvent(String[] words, TaskList tasks) throws JoyException {


        if (words.length < 6) {
            throw new JoyException.EventException("Insufficient information for creating a event task.");
        }

        // Check if '/from' and '/to' are present
        boolean hasFrom = false;
        boolean hasTo = false;
        for (String word : words) {
            if (word.equals("/from")) {
                hasFrom = true;
            }
            if (word.equals("/to")) {
                hasTo = true;
            }
        }

        if (!hasFrom || !hasTo) {
            throw new JoyException.EventException("Event timing information is missing."
                    +
                    " Please provide event timing using '/from' and '/to'.");
        }
        StringBuilder descriptionBuilder = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/from")) {
                break;
            }
            descriptionBuilder.append(words[i]).append(" ");
        }
        String description = descriptionBuilder.toString().trim();

        StringBuilder fromTimeBuilder = new StringBuilder();
        StringBuilder toTimeBuilder = new StringBuilder();
        boolean isFromTime = false;
        boolean isToTime = false;
        for (int i = 1; i < words.length; i++) {
            if (isFromTime && !isToTime) {
                if (words[i].equals("/to")) {
                    isToTime = true;
                    continue;
                }
                fromTimeBuilder.append(words[i]).append(" ");
            } else if (isToTime) {
                toTimeBuilder.append(words[i]).append(" ");
            }
            if (words[i].equals("/from")) {
                isFromTime = true;
            }
        }
        String fromTime = fromTimeBuilder.toString().trim();
        String toTime = toTimeBuilder.toString().trim();
        return tasks.addTasks(new Event(description, fromTime, toTime));

    }

    /**
     * Parses a todo command and adds the corresponding task to the TaskList.
     *
     * @param tasks The TaskList object to add the task todo
     * @throws JoyException If an error occurs during the parsing or execution of the command.
     */
    public static String parseTodo(String[] words, TaskList tasks) throws JoyException {


        if (words[1].isEmpty()) {
            try {
                throw new JoyException.EmptyTodoDescriptionException();
            } catch (JoyException e) {
                System.out.println(e.getMessage());
                //return;
            }
        }

        StringBuilder descriptionBuilder = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            descriptionBuilder.append(words[i]).append(" ");
        }
        String description = descriptionBuilder.toString().trim();
        return tasks.addTasks(new Todo(description));



    }


    /**
     * Parses the "find" command, searching for tasks containing a specified keyword.
     * Retrieves user input for the keyword and displays matching tasks.
     *
     * @param ui    The Ui object for handling user interface interactions.
     */
    public static String parseFind(String[] words, Ui ui) {

        if (words.length < 2) {
            try {
                throw new JoyException("OOPS!!! The find value cannot be empty.");
            } catch (JoyException e) {
                System.out.println(e.getMessage());
            }
        }

        String keyword = words[1];


        return TaskList.findTasks(keyword, ui);

    }
}
