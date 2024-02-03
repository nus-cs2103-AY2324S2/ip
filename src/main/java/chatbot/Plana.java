package chatbot;

import chatbot.exceptions.DukeException;

import java.io.IOException;

/**
 * Represents the main class responsible for running the chatbot 'Plana'. Critical components like UI, file IO are
 * initialised here.
 */
public class Plana {
    private final TaskList taskList;
    private final Storage store;
    private final Ui view;
    private boolean shouldExit;

    /**
     * Constructor that initalises UI, Storage and TaskList components.
     *
     * @throws IOException If reading from hard disk fails.
     * @throws ClassNotFoundException If data from hard disk is of the wrong format/outdated.
     */
    public Plana() throws IOException, ClassNotFoundException {
        this.view = new Ui();
        this.shouldExit = false;
        this.store = new Storage();
        this.taskList = new TaskList(this.store);
    }

    /**
     * Initiates the user input loop.
     *
     * @throws IOException If writing to hard disk on exit fails.
     */
    public void init() throws IOException {
        this.view.greet();

        while (!shouldExit) {
            String userInput = this.view.getInput();
            try {
                Command cmd = Parser.toCommand(userInput);
                cmd.execute(view, taskList);
                this.shouldExit = cmd.shouldExit();
            } catch (DukeException e) {
                this.view.displayError(e);
            }
        }

        this.taskList.saveToStore(this.store);
        this.view.bye();
    }
}
