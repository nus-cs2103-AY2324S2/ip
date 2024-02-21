package pingmebot;

import pingmebot.command.Command;

/**
 * A simple, interactive task management application.
 * It allows user to interact with it via command line interface.
 */
public class PingMe {
    protected static String filePath = "./data/dukeData.txt";
    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private Parser parser;

    /**
     * Creates a PingMe object with a specified file path.
     *
     * @param filePath The filePath to the storage of data locally.
     */
    public PingMe(String filePath) {
        ui = new UI();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.bootingUp());
        } catch (PingMeException e) {
            tasks = new TaskList();
            ui.showError(e.getMessage());
        }
    }

    public PingMe() {
        this(filePath);
    }

    /**
     * Helps to start the main logic of the application.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            String userInput = ui.readCommand();
            String response = getResponse(userInput);
        }
    }

    /**
     * Returns the response back to the user after keying in a certain command through the GUI.
     * @param input User's command.
     * @return The response to the user.
     */
    public String getResponse(String input) {
        parser = new Parser(input, tasks.getTaskSize());
        String response = "";
        try {
            Command c = parser.parseInput();
            c.execute(tasks, storage, ui);
            response += ui.givesBackResponse();
        } catch (PingMeException e) {
            ui.showError(e.getMessage());
            response += ui.givesBackResponse();
        }
        return response;
    }

    public static void main(String[] args) {
        new PingMe(filePath).run();
    }
}
