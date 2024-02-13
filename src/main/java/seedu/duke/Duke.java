package seedu.duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a task manager called <code>Duke</code>.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Contains logic from greeting to farewell to user.
     * It will scan for input and parse only when given valid commands.
     * These commands will then fed back to the user within the UI.
     * eg. <code>list, mark, unmark, deadline, todo, event</code>
     */
    public void run() {
        ui.openingMessage();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            // command dependent logic
            if (userInput.equals("list")) {
                ui.printList(tasks.getList());
                userInput = sc.nextLine();
            } else if (userInput.startsWith("mark")) {
                Parser.parseMark(userInput, tasks, ui);
                userInput = sc.nextLine();
            } else if (userInput.startsWith("unmark")) {
                Parser.parseUnmark(userInput, tasks, ui);
                userInput = sc.nextLine();
            } else if (userInput.startsWith("deadline")) {
                Parser.parseDeadline(userInput, tasks, ui);
                userInput = sc.nextLine();
            } else if (userInput.startsWith("todo")) {
                Parser.parseTodo(userInput, tasks, ui);
                userInput = sc.nextLine();
            } else if (userInput.startsWith("event")) {
                Parser.parseEvent(userInput, tasks, ui);
                userInput = sc.nextLine();
            } else if (userInput.startsWith("delete")) {
                Parser.parseDelete(userInput, tasks, ui);
                userInput = sc.nextLine();
            } else {
                try {
                    throw new DukeException("Sorry, I didn't understand that.");
                } catch (DukeException d) {
                    ui.printError(d);
                    userInput = sc.nextLine();
                }
            }
        }
        storage.saveList(tasks.getList());
        ui.closingMessage();
    }

    /**
     * Main method in Duke.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // relative storage path for save and load feature
        new Duke("data/tasks.txt").run();
    }

}
