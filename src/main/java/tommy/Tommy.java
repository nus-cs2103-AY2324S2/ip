package tommy;

import tommy.command.Command;
import tommy.exception.TommyException;
import tommy.task.TaskList;

/**
 * Represents the Chatbot.
 */
public class Tommy {

    private Storage storage;
    private TaskList tasks;

    private Ui ui;
    private boolean isActive = true;

    /**
     * Constructor for the Chatbot and retrieve the past log from the storage.
     */
    public Tommy() {
        ui = new Ui();
        storage = new Storage("./data/tasks.txt");

        try {
            tasks = new TaskList(storage.load());
        } catch (TommyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    /**
     * Dissects the input, computes and executes the command.
     *
     * @param input User input for the chatbot.
     * @return The response text (error/confirmation message) displayed to the user.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            String response = command.execute(this.storage, this.tasks, this.ui);
            isActive = command.isActive();
            return response;
        } catch (TommyException e) {
            return "Error: " + e.getMessage();

        }
    }

    /**
     * Retrieves the availability of the chatbot, which can be used to terminate the chatbot when BYE command is
     * executed.
     *
     * @return The availability of the chatbot as a boolean.
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Retrieves the greetings to be displayed to the user when the chatbot first boots up.
     *
     * @return Greetings to be displayed to the user.
     */
    public String getGreetings() {
        return this.ui.greet();
    }
}
