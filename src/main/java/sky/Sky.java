package sky;

import command.Command;
import exception.IncompleteCommandException;
import exception.UnknownCommandException;
import task.TaskList;
/**
 * Represents a chat bot that helps the user to manage tasks.
 */
public class Sky {
    public static final Ui UI = new Ui();
    private TaskList taskList = new TaskList();
    private Storage storage;

    /**
     * Constructor for Sky.
     */
    public Sky() {
        this.taskList = new TaskList();
        this.storage = new Storage();
        try {
            this.storage.loadData(this.taskList);
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
        } catch (UnknownCommandException e) {
            return e.toString();
        } catch (IncompleteCommandException e) {
            return e.toString();
        } catch (Exception e) {
            return e.getMessage();
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
