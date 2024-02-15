package tommy;

import tommy.command.Command;
import tommy.task.TaskList;
import tommy.exception.TommyException;

import javafx.scene.image.Image;

/**
 * Represents the Chatbot.
 */
public class Tommy {

    private Storage storage;
    private TaskList tasks;

    // deals with interactions with the user
    private Ui ui;
    private boolean isActive = true;


    private Image user = new Image(this.getClass().getResourceAsStream("/images/bobIcon.png"));
    private Image tommy = new Image(this.getClass().getResourceAsStream("/images/bearIcon.png"));

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

    public boolean isActive() {
        return this.isActive;
    }

    public String getGreetings() {
        return this.ui.greet();
    }
}

