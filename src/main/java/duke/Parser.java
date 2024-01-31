package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.io.IOError;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
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
    public static void parseAndExecute(String userInput, TaskList tasks, Ui ui, Storage storage) throws DukeException {

        //String[] words = userInput.split(" ");
        String commandStr = userInput.toUpperCase();


        switch (commandStr) {
            case "BYE":
                // Handle BYE command
                ui.showGoodbyeMessage();
                System.exit(0);
                break;
            case "LIST":
                // Handle LIST command
                tasks.listTasks(ui);
                break;
            case "DELETE":
                //if (words.length < 2) {
                //    throw new DukeException("The task number to mark is missing.");
                //}
                //nt taskNumberMark = Integer.parseInt(words[1]);
                if (ui.hasNextInt()) {
                    int num = ui.getUserInputInt();
                    tasks.removeTasks(num, ui);
                } else {
                    throw new DukeException("The task number to mark is missing.");

                }
                break; // Add break statement

            case "MARK":
                // Handle MARK command

                if (ui.hasNextInt()) {
                    int num = ui.getUserInputInt();
                    tasks.markTasks(num, ui);

                } else {
                    throw new DukeException("The task number to mark is missing.");
                }

                break;
            case "DEADLINE":
                parseDeadline( tasks, ui);
                break;

            case "EVENT":
                parseEvent( tasks, ui);
                break;
            case "TODO":

                parseTodo( tasks, ui);

                break;

            case "FIND":
                parseFind(tasks, ui);
                break;
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
    public static void parseDeadline( TaskList tasks, Ui ui) throws DukeException{
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

        String inputLine = ui.getUserInput3().trim();

        // Check if there's "/by" in the input
        if (!inputLine.contains("/by")) {
            try {
                throw new DukeException("OOPS!!! Please provide a deadline time using '/by'.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return;
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
        }

    }

    /**
     * Parses an event command and adds the corresponding task to the TaskList.
     *
     * @param tasks The TaskList object to add the task to.
     * @param ui    The Ui object for user interface interactions.
     * @throws DukeException If an error occurs during the parsing or execution of the command.
     */
    public static void parseEvent( TaskList tasks, Ui ui) throws DukeException{
        /*if (words.length < 6) {
            throw new DukeException("Insufficient information for creating a event task.");
        }

        String description = words[1];
        String fromKeyword = words[2];
        String fromTime = words[3];
        String toKeyword = words[4];
        String toTime = words[5];

        if (!fromKeyword.equals("/from") || !toKeyword.equals("/to") || description.isEmpty() || fromTime.isEmpty() || toTime.isEmpty()) {
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

        String inputLineEvent = ui.getUserInput3().trim();
        if (!inputLineEvent.contains("/from") || !inputLineEvent.contains("/to")) {
            try {
                throw new DukeException("OOPS!!! Please provide event timing using '/from' and '/to'.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return;
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
                return;
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
                return;
            }
        }

        tasks.addTasks(new Event(descriptionEvent, from, to));

    }

    /**
     * Parses a todo command and adds the corresponding task to the TaskList.
     *
     * @param tasks The TaskList object to add the task to.
     * @param ui    The Ui object for user interface interactions.
     * @throws DukeException If an error occurs during the parsing or execution of the command.
     */
    public static void parseTodo( TaskList tasks, Ui ui) throws DukeException {
        /* if (words.length < 2) {
            throw new DukeException("Insufficient information for creating a event task.");
        }

        String description = words[1];

        tasks.addTasks(new Todo(description)); */

        String descriptionTodo = ui.getUserInput2().trim();
        System.out.println(descriptionTodo);

        // Check if description is empty
        if (descriptionTodo.isEmpty()) {
            try {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        tasks.addTasks(new Todo(descriptionTodo));

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
    public static void parseFind( TaskList tasks, Ui ui) {
        String keyword = ui.getUserInput2().trim();
        //System.out.println(descriptionTodo);
        ArrayList<Task> matchingTasks = tasks.keywordSearch(keyword);

        ui.showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            ui.showMessage((i + 1) + "." + matchingTasks.get(i).toString());
        }
    }
}
