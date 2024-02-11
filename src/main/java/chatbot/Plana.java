package chatbot;

import java.io.IOException;

import chatbot.exceptions.DukeException;

/**
 * Represents the main class responsible for running the chatbot 'Plana'. Critical components like UI, file IO are
 * initialised here.
 */
public class Plana {
    private final TaskList taskList;
    private final Storage store;
    private boolean shouldExit;

    /**
     * Constructor that initalises UI, Storage and TaskList components.
     *
     * @throws IOException If reading from hard disk fails.
     * @throws ClassNotFoundException If data from hard disk is of the wrong format/outdated.
     */
    public Plana() throws IOException, ClassNotFoundException {
        this.shouldExit = false;
        this.store = new Storage();
        this.taskList = new TaskList(this.store);
    }

    /**
     * Initiates the user input loop.
     */
    public Response getResponse(String input) {
        try {
            Command cmd = Parser.toCommand(input);
            return cmd.execute(taskList);
        } catch (DukeException e) {
            return Response.displayError(e);
        }
    }

    public void persistData() throws IOException {
        this.taskList.saveToStore(this.store);
    }
}
