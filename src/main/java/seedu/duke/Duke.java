package seedu.duke;

import java.util.Scanner;

/**
 * Represents a task manager called <code>Duke</code>.
 */

@SuppressWarnings("checkstyle:Regexp")
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public String getResponse(String input) {
        return "hii";
    }

//    public static String gitResponse(String input) {
//        String response = "";
//        if (input.equals("bye")) {
//            response = "Bye. Hope to see you again soon!";
//        } else if (input.equals("list")) {
//            response = "Here are the tasks in your list:\n" + TaskList.getList();
//        } else if (input.startsWith("done")) {
//            response = Parser.parseMark(input, TaskList.getList());
//        } else if (input.startsWith("deadline")) {
//            response = Parser.parseDeadline(input, TaskList.getList());
//        } else if (input.startsWith("todo")) {
//            response = Parser.parseTodo(input, TaskList.getList());
//        } else if (input.startsWith("event")) {
//            response = Parser.parseEvent(input, TaskList.getList());
//        } else {
//            response = "Sorry, I didn't understand that.";
//        }
//        return response;
//    }

    /*
    /**
     * Main method in Duke.
     *
     * @param args
     * @throws IOException

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
    */
}
