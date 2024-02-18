package seedu.duke;

import java.util.Objects;

/**
 * Represents a task manager called <code>Duke</code>.
 */
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
        assert ui != null : "Ui should not be null";
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

        assert ui != null : "Ui should not be null";
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        assert userInput != null : "User input should not be null";

        // command dependent logic
        if (userInput.equals("bye")) {
            storage.saveTasks(tasks.getList());
            dukeResponse = ui.closingMessage();
        } else if (userInput.equals("list") || userInput.startsWith("l")) {
            dukeResponse = ui.printList(tasks.getList());
        } else if (userInput.startsWith("mark") || userInput.startsWith("m")) {
            dukeResponse = Parser.parseMark(userInput, tasks, ui);
        } else if (userInput.startsWith("unmark") || userInput.startsWith("u")) {
            dukeResponse = Parser.parseUnmark(userInput, tasks, ui);
        } else if (userInput.startsWith("deadline") || userInput.startsWith("dead")) {
            dukeResponse = Parser.parseDeadline(userInput, tasks, ui);
        } else if (userInput.startsWith("todo") || userInput.startsWith("t")) {
            dukeResponse = Parser.parseTodo(userInput, tasks, ui);
        } else if (userInput.startsWith("event") || userInput.startsWith("e")) {
            dukeResponse = Parser.parseEvent(userInput, tasks, ui);
        } else if (userInput.startsWith("delete") || userInput.startsWith("del")) {
            dukeResponse = Parser.parseDelete(userInput, tasks, ui);
        } else if (userInput.startsWith("find") || userInput.startsWith("f")) {
            dukeResponse = Parser.parseFind(userInput, tasks, ui);
        } else {
            try {
                throw new DukeException("Sorry, I didn't understand that.");
            } catch (DukeException d) {
                dukeResponse = ui.printError(d);
            }
        }
        storage.saveTasks(tasks.getList());
        assert !dukeResponse.isEmpty(): "Response should not be empty";
        return dukeResponse;
    }
}
