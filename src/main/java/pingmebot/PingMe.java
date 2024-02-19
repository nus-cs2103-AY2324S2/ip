package pingmebot;

import pingmebot.command.Command;
import pingmebot.command.ExitCommand;
import pingmebot.command.ListCommand;

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
     *
     * @param input User's commmand.
     * @return The response to the user.
     */
    public String getResponse(String input) {
        String[] splitInput = input.split(" ");
        parser = new Parser(input);
        String response = "";

        if (input.equals("bye")) {
            try {
                Command c = new ExitCommand();
                c.execute(tasks, storage, ui);
                response += ui.givesBackResponse();
            } catch (PingMeException e) {
                ui.showError(e.getMessage());
                response += ui.givesBackResponse();
            }

        } else if (input.equals("list")) {
            try {
                Command c = new ListCommand();
                c.execute(tasks, storage, ui);
                response += ui.givesBackResponse();
            } catch (PingMeException e) {
                ui.showError(e.getMessage());
                response += ui.givesBackResponse();
            }

        } else if (splitInput[0].equals("mark")) {
            try {
                Command c = parser.parseMarkCommand(tasks.getTaskSize());
                c.execute(tasks, storage, ui);
                response += ui.givesBackResponse();
            } catch (PingMeException e) {
                ui.showError(e.getMessage());
                response += ui.givesBackResponse();
            }

        } else if (splitInput[0].equals("unmark")) {
            try {
                Command c = parser.parseUnmarkCommand(tasks.getTaskSize());
                c.execute(tasks, storage, ui);
                response += ui.givesBackResponse();
            } catch (PingMeException e) {
                ui.showError(e.getMessage());
                response += ui.givesBackResponse();
            }

        } else if (splitInput[0].equals("todo")) {
            try {
                Command c = parser.parseToDoCommand();
                c.execute(tasks, storage, ui);
                response += ui.givesBackResponse();
            } catch (PingMeException e) {
                ui.showError(e.getMessage());
                response += ui.givesBackResponse();
            }

        } else if (splitInput[0].equals("deadline")) {
            try {
                Command c = parser.parseDeadlineCommand();
                c.execute(tasks, storage, ui);
                response += ui.givesBackResponse();
            } catch (PingMeException e) {
                ui.showError(e.getMessage());
                response += ui.givesBackResponse();
            }

        } else if (splitInput[0].equals("event")) {
            try {
                Command c = parser.parseEventsCommand();
                c.execute(tasks, storage, ui);
                response += ui.givesBackResponse();
            } catch (PingMeException e) {
                ui.showError(e.getMessage());
                response += ui.givesBackResponse();
            }

        } else if (splitInput[0].equals("delete")) {
            try {
                Command c = parser.parseDeleteCommand(tasks.getTaskSize());
                c.execute(tasks, storage, ui);
                response += ui.givesBackResponse();
            } catch (PingMeException e) {
                ui.showError(e.getMessage());
                response += ui.givesBackResponse();
            }

        } else if (splitInput[0].equals("find")) {
            try {
                Command c = parser.parseFindCommand();
                c.execute(tasks, storage, ui);
                response += ui.givesBackResponse();
            } catch (PingMeException e) {
                ui.showError(e.getMessage());
                response += ui.givesBackResponse();
            }

        } else if (splitInput[0].equals("postpone")) {
            try {
                Command c = parser.parsePostponeCommand();
                c.execute(tasks, storage, ui);
                response += ui.givesBackResponse();
            } catch (PingMeException e) {
                ui.showError(e.getMessage());
                response += ui.givesBackResponse();
            }

        } else {
            ui.showError("OOPS! I'm sorry, but I don't know what that means :'(");
            response += ui.givesBackResponse();
        }

        return response;
    }

    public static void main(String[] args) {
        new PingMe(filePath).run();
    }
}
