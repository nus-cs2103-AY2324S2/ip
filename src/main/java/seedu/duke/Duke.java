package seedu.duke;

import java.io.IOException;
import java.util.Scanner;

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
        String response = "";
        // command dependent logic
        if (userInput.equals("bye")) {
            storage.saveList(tasks.getList());
            response = ui.closingMessage();
        } else if (userInput.equals("list")) {
            response = ui.printList(tasks.getList());
        } else if (userInput.startsWith("mark")) {
            response = Parser.parseMark(userInput, tasks, ui);
        } else if (userInput.startsWith("unmark")) {
            response = Parser.parseUnmark(userInput, tasks, ui);
        } else if (userInput.startsWith("deadline")) {
            response = Parser.parseDeadline(userInput, tasks, ui);
        } else if (userInput.startsWith("todo")) {
            response = Parser.parseTodo(userInput, tasks, ui);
        } else if (userInput.startsWith("event")) {
            response = Parser.parseEvent(userInput, tasks, ui);
        } else if (userInput.startsWith("delete")) {
            response = Parser.parseDelete(userInput, tasks, ui);
        } else {
            try {
                throw new DukeException("Sorry, I didn't understand that.");
            } catch (DukeException d) {
                response = ui.printError(d);
            }
        }
        storage.saveList(tasks.getList());
        return response;
    }
}
