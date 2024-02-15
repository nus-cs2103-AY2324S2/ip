package duke;

import command.Command;
import exception.UnknownCommandException;
import task.TaskList;

/**
 * Represents a chat bot that helps the user to manage tasks.
 */
public class Duke {
    public static final Ui UI = new Ui();
    private TaskList taskList = new TaskList();
    private Storage storage;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage();
        try {
            this.storage.loadData(this.taskList);
        } catch (Exception e) {
            UI.showErrorMessage(e);
        }
    }

    /**
     * Main method to run the chat bot.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        UI.sayGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = UI.readCommand();
                Command command = Parser.parseCommand(userInput);
                command.execute(duke.taskList, UI);
                isExit = command.isExit();
            } catch (IllegalArgumentException e) {
                if (e.getMessage().contains("No enum constant Duke.Command.")) {
                    UI.showErrorMessage(new UnknownCommandException());
                } else {
                    UI.showErrorMessage(e);
                }
            } catch (Exception e) {
                UI.showErrorMessage(e);
            }
        }
        try {
            duke.storage.saveData(duke.taskList);
        } catch (Exception e) {
            UI.showErrorMessage(e);
        }
    }

    /**
     * Gets the response from the chat bot.
     * @param input User input.
     * @return Response from the chat bot.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            assert command != null : "Command should not be null";
            return command.execute(this.taskList, UI);
        } catch (Exception e) {
            String output = e.getMessage();
            return output == null
                    ? "OOPS!!! I'm sorry, but I don't know what that means :-("
                    : output;
        }
    }

    /**
     * Exits the chat bot.
     */
    public void saveData() {
        try {
            this.storage.saveData(this.taskList);
        } catch (Exception e) {
            UI.showErrorMessage(e);
        }
    }
}
