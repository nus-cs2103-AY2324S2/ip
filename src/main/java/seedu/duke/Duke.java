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

    public static String gitResponse(String userInput) {
        String response = "";
        // command dependent logic
        if (userInput.equals("bye")) {
            storage.saveList(tasks.getList());
            response = ui.closingMessage();
        } else if (userInput.equals("list")) {
            Parser.parseTodo("todo hahhaha", tasks, ui);
            Parser.parseMark("mark 1", tasks, ui);
            Parser.parseUnmark("unmark 1", tasks, ui);
            Parser.parseDeadline("deadline go to sleep /by 2024-03-03", tasks, ui);
            response = ui.printList(tasks.getList());
        } else if (userInput.startsWith("mark")) {
            response = Parser.parseMark(userInput, tasks, ui);
        } else if (userInput.startsWith("unmark")) {
            response = Parser.parseUnmark(userInput, tasks, ui);
        } else if (userInput.startsWith("deadline")) {
            response = Parser.parseDeadline(userInput, tasks, ui);
        } else if (userInput.startsWith("todo")) {
            response = Parser.parseTodo(userInput, tasks, ui);
//        } else if (userInput.startsWith("event")) {
//            Parser.parseEvent(userInput, tasks, ui);
//            userInput = sc.nextLine();
//        } else if (userInput.startsWith("delete")) {
//            Parser.parseDelete(userInput, tasks, ui);
//            userInput = sc.nextLine();
        } else {
            try {
                throw new DukeException("Sorry, I didn't understand that.");
            } catch (DukeException d) {
                response = ui.printError(d);
            }
        }
        return response;
    }

    public String getResponse(String input) {
        return "hii";
    }

    /**
     * Main method in Duke.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Duke();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        System.out.println(gitResponse(userInput));
    }
}
