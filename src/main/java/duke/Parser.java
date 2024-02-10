package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;





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
     * @throws DukeException If an error occurs during the parsing or execution of the command.
     */
    public static String parseAndExecute(String userInput, TaskList tasks, Ui ui)
            throws DukeException {

        String[] words = userInput.split(" ");

        System.out.println(words[0]);

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
            } catch (DukeException e) {
                return ui.showError(e.getMessage());
            }
        case "DELETE":

            if (words.length != 2) {
                throw new DukeException("The task number to delete is missing.");
            }
            int n = Integer.parseInt(words[1]);
            return tasks.removeTasks(n, ui);


        case "MARK":
            // Handle MARK command


            if (words.length != 2) {
                throw new DukeException("The task number to mark is missing.");
            }
            int num = Integer.parseInt(words[1]);
            return tasks.markTasks(num, ui);


        case "DEADLINE":
            try {
                return parseDeadline(words, tasks);
            } catch (DukeException e) {
                return ui.showError(e.getMessage());
            }

        case "EVENT":
            try {
                return parseEvent(words, tasks);
            } catch (DukeException e) {
                return ui.showError(e.getMessage());
            }


        case "FIND":
            return parseFind(words, ui);
            //break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses a deadline command and adds the corresponding task to the TaskList.
     *
     * @param tasks The TaskList object to add the task to.
     * @throws DukeException If an error occurs during the parsing or execution of the command.
     */
    public static String parseDeadline(String[] words, TaskList tasks) throws DukeException {


        if (words.length < 4) {
            throw new DukeException("Insufficient information for creating a deadline task.");
        }

        if (words[1].isEmpty() || words[3].isEmpty()) {
            try {
                throw new DukeException("OOPS!!! The description and deadline time cannot be empty.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                //return;
            }
        }
        if (!words[2].equals("/by")) {
            try {
                throw new DukeException("OOPS!!! Please provide a deadline time using '/by'.");
            } catch (DukeException e) {
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
        tasks.addTasks(new Deadline(description, time));
        return "added deadline";

    }

    /**
     * Parses an event command and adds the corresponding task to the TaskList.
     *
     * @param tasks The TaskList object to add the task to.
     * @throws DukeException If an error occurs during the parsing or execution of the command.
     */
    public static String parseEvent(String[] words, TaskList tasks) throws DukeException {


        if (words.length < 6) {
            throw new DukeException("Insufficient information for creating a event task.");
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
            throw new DukeException("Event timing information is missing. Please provide event timing using '/from' and '/to'.");
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
        tasks.addTasks(new Event(description, fromTime, toTime));
        return "added event";

    }

    /**
     * Parses a todo command and adds the corresponding task to the TaskList.
     *
     * @param tasks The TaskList object to add the task todo
     * @throws DukeException If an error occurs during the parsing or execution of the command.
     */
    public static String parseTodo(String[] words, TaskList tasks) throws DukeException {

        String res = "";
        if (words[1].isEmpty()) {
            try {
                throw new DukeException.EmptyTodoDescriptionException();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                //return;
            }
        }

        StringBuilder descriptionBuilder = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            descriptionBuilder.append(words[i]).append(" ");
        }
        String description = descriptionBuilder.toString().trim();
        tasks.addTasks(new Todo(description));

        res += "added todo";
        return res;

    }

    /**
     * Parses a line from a saved file and constructs a Task object.
     *
     * @param line The line from the file representing a task.
     * @return The Task object constructed from the line.
     * @throws IOException If an error occurs during the parsing of the line.
     */
    public static Task parseTaskFromLine(String line) throws IOException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IOException("Invalid task format. Skipping line.");
        }

        String type = parts[0];
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new IOException("Invalid deadline format. Skipping line.");
            }
            String by = parts[3];
            task = new Deadline(description, by);
            break;
        case "E":
            if (parts.length < 4) {
                throw new IOException("Invalid event format. Skipping line.");
            }
            String[] eventParts = parts[3].split(" from ");
            if (eventParts.length < 2) {
                throw new IOException("Invalid event format. Skipping line.");
            }
            String start = eventParts[0];
            String end = eventParts[1];
            task = new Event(description, start, end);
            break;
        default:
            throw new IOException("Invalid task type in file. Skipping line.");
        }

        if (isDone) {
            task.setStatus();
        }

        return task;
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
                throw new DukeException("OOPS!!! The find value cannot be empty.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        String keyword = words[1];

        return TaskList.findTasks(keyword, ui);

    }
}
