package morty;

import javafx.application.Application;
import morty.command.Command;
import morty.ui.Main;

/**
 * Morty is a chatbot that helps users to keep track of their tasks.
 */
public class Morty {

    private Storage storage;
    private TaskList tasks;
    private Response ui;

    /**
     * Constructs a Morty object.
     *
     * @param filePath File path to store the tasks.
     */
    public Morty(String filePath) {
        ui = new Response();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Gets the response from Morty based on the user input.
     *
     * @param input User input.
     * @return Morty's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Starts the Morty application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
