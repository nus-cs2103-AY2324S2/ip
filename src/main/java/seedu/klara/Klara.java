package seedu.klara;

/**
 * Represents a task manager called <code>Klara</code>.
 */
public class Klara {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Constructor for Klara.
     */
    public Klara() {
        String filePath = "data/klara.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (KlaraException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Represents the opening message when Klara starts.
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
        String klaraResponse = "";

        assert ui != null : "Ui should not be null";
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        assert userInput != null : "User input should not be null";

        // command dependent logic
        if (userInput.equals("bye")) {
            storage.saveTasks(tasks.getList());
            klaraResponse = ui.closingMessage();
        } else if (userInput.equals("list") || userInput.startsWith("l")) {
            klaraResponse = ui.printList(tasks.getList());
        } else if (userInput.startsWith("mark") || userInput.startsWith("m")) {
            klaraResponse = Parser.parseMark(userInput, tasks, ui);
        } else if (userInput.startsWith("unmark") || userInput.startsWith("u")) {
            klaraResponse = Parser.parseUnmark(userInput, tasks, ui);
        } else if (userInput.startsWith("deadline") || userInput.startsWith("dead")) {
            klaraResponse = Parser.parseDeadline(userInput, tasks, ui);
        } else if (userInput.startsWith("todo") || userInput.startsWith("t")) {
            klaraResponse = Parser.parseTodo(userInput, tasks, ui);
        } else if (userInput.startsWith("event") || userInput.startsWith("e")) {
            klaraResponse = Parser.parseEvent(userInput, tasks, ui);
        } else if (userInput.startsWith("delete") || userInput.startsWith("del")) {
            klaraResponse = Parser.parseDelete(userInput, tasks, ui);
        } else if (userInput.startsWith("find") || userInput.startsWith("f")) {
            klaraResponse = Parser.parseFind(userInput, tasks, ui);
        } else {
            try {
                throw new KlaraException("Sorry, I didn't understand that.");
            } catch (KlaraException d) {
                klaraResponse = ui.printError(d);
            }
        }
        storage.saveTasks(tasks.getList());
        assert !klaraResponse.isEmpty(): "Response should not be empty";
        return klaraResponse;
    }
}
