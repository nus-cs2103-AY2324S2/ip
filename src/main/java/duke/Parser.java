package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;



import java.util.ArrayList;

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
     * @param storage   The Storage object for saving and loading tasks.
     * @throws DukeException If an error occurs during the parsing or execution of the command.
     */
    public static String parseAndExecute(String userInput, TaskList tasks, Ui ui, Storage storage)
            throws DukeException {

        assert userInput != null : "User input cannot be null";
        assert tasks != null : "Task list cannot be null";
        assert ui != null : "UI object cannot be null";
        assert storage != null : "Storage object cannot be null";

        String[] words = userInput.split(" ");
        assert words != null && words.length > 0 : "Parsed words array cannot be null or empty";

        String commandStr = userInput.toUpperCase();
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
                return parseTodo(words, tasks, ui);
            } catch (DukeException e) {
                return ui.showError(e.getMessage());
            }
        case "DELETE":
            //if (words.length < 2) {
            //    throw new DukeException("The task number to mark is missing.");
            //}
            //nt taskNumberMark = Integer.parseInt(words[1]);
            /*if (ui.hasNextInt()) {
                int num = ui.getUserInputInt();
                return tasks.removeTasks(num, ui);
            } else {
                 throw new DukeException("The task number to mark is missing.");

            }*/
            if (words.length != 2) {
                throw new DukeException("The task number to delete is missing.");
            }
            int n = Integer.parseInt(words[1]);
            return tasks.removeTasks(n, ui);
            //break; // Add break statement

        case "MARK":
            // Handle MARK command

            /*if (ui.hasNextInt()) {
                int num = ui.getUserInputInt();
                return tasks.markTasks(num, ui);

            } else {
                throw new DukeException("The task number to mark is missing.");
            }*/
            if (words.length != 2) {
                throw new DukeException("The task number to mark is missing.");
            }
            int num = Integer.parseInt(words[1]);
            return tasks.markTasks(num, ui);

            //break;
        case "DEADLINE":
            try {
                return parseDeadline(words, tasks, ui);
            } catch (DukeException e) {
                return ui.showError(e.getMessage());
            }

        case "EVENT":
            try {
                return parseEvent(words, tasks, ui);
            } catch (DukeException e) {
                return ui.showError(e.getMessage());
            }


        case "FIND":
            return parseFind(words, tasks, ui);
            //break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses a deadline command and adds the corresponding task to the TaskList.
     *
     * @param tasks The TaskList object to add the task to.
     * @param ui    The Ui object for user interface interactions.
     * @throws DukeException If an error occurs during the parsing or execution of the command.
     */
    public static String parseDeadline(String[] words, TaskList tasks, Ui ui) throws DukeException {
        /*if (words.length < 4) {
            throw new DukeException("Insufficient information for creating a deadline task.");
        }

        String description = words[1];
        String byKeyword = words[2];
        String time = words[3];

        if (!byKeyword.equals("/by") || description.isEmpty() || time.isEmpty()) {
            try {
                throw new DukeException("OOPS!!! The description and deadline time cannot be empty.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        try {
            // Attempt to create a Deadline with the provided description and time
            tasks.addTasks(new Deadline(description, time));
        } catch (DateTimeParseException e) {
            // Handle the case where the date format is incorrect
            System.out.println("OOPS!!! Incorrect date format. Please enter the date in the format yyyy-MM-dd.");
            return;
        }*/
        //.............................................................
        /*String inputLine = ui.getUserInput3().trim();

        // Check if there's "/by" in the input
        if (!inputLine.contains("/by")) {
            try {
                throw new DukeException("OOPS!!! Please provide a deadline time using '/by'.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                //return;
            }
        }

        // Split the input into description and time
        String[] parts = inputLine.split("/by", 2);
        String description = parts[0].trim();
        String time = parts[1].trim();

        // Check if description or time is empty
        if (description.isEmpty() || time.isEmpty()) {
            try {
                throw new DukeException("OOPS!!! The description and deadline time cannot be empty.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                //return;
            }
        }

        try {
            // Attempt to create a Deadline with the provided description and time
            tasks.addTasks(new Deadline(description, time));
        } catch (DateTimeParseException e) {
            // Handle the case where the date format is incorrect
            System.out.println("OOPS!!! Incorrect date format. Please enter the date in the format yyyy-MM-dd.");
            //return;
        }
        return "added deadline"; */

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
     * @param ui    The Ui object for user interface interactions.
     * @throws DukeException If an error occurs during the parsing or execution of the command.
     */
    public static String parseEvent(String[] words, TaskList tasks, Ui ui) throws DukeException {
        /*if (words.length < 6) {
            throw new DukeException("Insufficient information for creating a event task.");
        }

        String description = words[1];
        String fromKeyword = words[2];
        String fromTime = words[3];
        String toKeyword = words[4];
        String toTime = words[5];

        if (!fromKeyword.equals("/from") || !toKeyword.equals("/to")
        || description.isEmpty() || fromTime.isEmpty() || toTime.isEmpty()) {
            try {
                throw new DukeException("OOPS!!! The description and event time cannot be empty.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        try {
            // Attempt to create a Deadline with the provided description and time
            tasks.addTasks(new Event(description, fromTime, toTime));
        } catch (DateTimeParseException e) {
            // Handle the case where the date format is incorrect
            //System.out.println("OOPS!!! Incorrect date format. Please enter the date in the format yyyy-MM-dd.");
            return;
        } */

        // .............................
        /*
        String inputLineEvent = ui.getUserInput3().trim();
        if (!inputLineEvent.contains("/from") || !inputLineEvent.contains("/to")) {
            try {
                throw new DukeException("OOPS!!! Please provide event timing using '/from' and '/to'.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                //return;
            }
        }

        String[] partsEvent = inputLineEvent.split("/from", 2);
        String descriptionEvent = partsEvent[0].trim();

        // Check if the description is empty
        if (descriptionEvent.isEmpty()) {
            try {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                //return;
            }
        }
        partsEvent = partsEvent[1].split("/to", 2);
        String from = partsEvent[0].trim();
        String to = partsEvent[1].trim();
        // Check if "from" or "to" is empty
        if (from.isEmpty() || to.isEmpty()) {
            try {
                throw new DukeException("OOPS!!! The event timing cannot be empty.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                //return;
            }
        }

        tasks.addTasks(new Event(descriptionEvent, from, to));
        return "added event";

         */

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
            throw new DukeException("Event timing information is missing."
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
        tasks.addTasks(new Event(description, fromTime, toTime));
        return "added event";

    }

    /**
     * Parses a todo command and adds the corresponding task to the TaskList.
     *
     * @param tasks The TaskList object to add the task to.
     * @param ui    The Ui object for user interface interactions.
     * @throws DukeException If an error occurs during the parsing or execution of the command.
     */
    public static String parseTodo(String[] words, TaskList tasks, Ui ui) throws DukeException {
        /* if (words.length < 2) {
            throw new DukeException("Insufficient information for creating a event task.");
        }

        String description = words[1];

        tasks.addTasks(new Todo(description)); */

        /*String res = "";
        String descriptionTodo = ui.getUserInput2().trim();
        System.out.println(descriptionTodo);

        // Check if description is empty
        if (descriptionTodo.isEmpty()) {
            try {
                throw new DukeException.EmptyTodoDescriptionException();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                //return;
            }
        }

        tasks.addTasks(new Todo(descriptionTodo));
        res += "added todo";
        return res; */
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
     * @param tasks The TaskList containing the tasks to search.
     * @param ui    The Ui object for handling user interface interactions.
     */
    public static String parseFind(String[] words, TaskList tasks, Ui ui) {
        /* String keyword = ui.getUserInput2().trim();
        //System.out.println(descriptionTodo);
        ArrayList<Task> matchingTasks = tasks.keywordSearch(keyword);
        String res = "";
        res = ui.showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            res += ui.showMessage((i + 1) + "." + matchingTasks.get(i).toString());
        }
        return res; */
        if (words.length < 2) {
            try {
                throw new DukeException("OOPS!!! The find value cannot be empty.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        String keyword = words[1];
        // for (int i = 1; i < words.length; i++) {
        //   keyword += words[i] + " ";
        //}

        ArrayList<Task> matchingTasks = tasks.keywordSearch(keyword);
        String res = "";
        res = ui.showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            res += ui.showMessage((i + 1) + "." + matchingTasks.get(i).toString());
        }
        return res;
    }
}
