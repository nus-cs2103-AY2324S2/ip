package seedu.duke;

/**
 * Represents a task manager called <code>Duke</code>.
 */

@SuppressWarnings("checkstyle:Regexp")
public class Duke {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        String filePath = "data/duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Represents the opening message when Duke starts.
     * @return opening message
     */
    public String welcomeMessage() {
        return ui.openingMessage();
    }

    /**
     * Contains logic from greeting to farewell to user.
     * It will scan for input and parse only when given valid commands.
     * These commands will then fed back to the user within the UI.
     * eg. <code>list, mark, unmark, deadline, todo, event</code>
     */
    public static String getResponse(String userInput) {
        String dukeResponse = "";
        // command dependent logic
        if (userInput.equals("bye")) {
            storage.saveTasks(tasks.getList());
            dukeResponse = ui.closingMessage();
        } else if (userInput.equals("list")) {
            dukeResponse = ui.printList(tasks.getList());
        } else if (userInput.startsWith("mark")) {
            dukeResponse = Parser.parseMark(userInput, tasks, ui);
        } else if (userInput.startsWith("unmark")) {
            dukeResponse = Parser.parseUnmark(userInput, tasks, ui);
        } else if (userInput.startsWith("deadline")) {
            dukeResponse = Parser.parseDeadline(userInput, tasks, ui);
        } else if (userInput.startsWith("todo")) {
            dukeResponse = Parser.parseTodo(userInput, tasks, ui);
        } else if (userInput.startsWith("event")) {
            dukeResponse = Parser.parseEvent(userInput, tasks, ui);
        } else if (userInput.startsWith("delete")) {
            dukeResponse = Parser.parseDelete(userInput, tasks, ui);
        } else if (userInput.startsWith("find")) {
            dukeResponse = Parser.parseFind(userInput, tasks, ui);
        } else {
            try {
                throw new DukeException("Sorry, I didn't understand that.");
            } catch (DukeException d) {
                dukeResponse = ui.printError(d);
            }
        }
        storage.saveTasks(tasks.getList());
        return dukeResponse;
    }
}
