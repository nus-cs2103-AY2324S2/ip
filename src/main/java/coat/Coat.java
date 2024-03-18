package coat;

import java.io.FileNotFoundException;
import java.io.IOException;

import coat.command.ByeCommand;
import coat.command.Command;
import coat.parser.Parser;
import coat.storage.Storage;
import coat.task.TaskList;
import coat.ui.Main;
import coat.ui.Ui;

/**
 * The main class representing the Coat chatbot application.
 *
 * <p>The {@code Coat} class serves as the entry point for the Coat chatbot application.
 * It handles user input, parsing, command execution, and storage operations.</p>
 */
public class Coat {
    private final Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    /**
     * Constructs a Coat object
     */
    public Coat() {
        this.tasks = new TaskList();
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser(ui);

        try {
            this.tasks = this.storage.load();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the response from Coat
     *
     * @param input User input
     * @return Response from Coat
     */
    public String getResponse(String input) {
        try {
            ui.clear();

            // Parse the input
            Command command = Parser.parseInput(input);

            // Execute the command
            command.execute(this.tasks, this.ui);
            storage.save(tasks);

            if (command instanceof ByeCommand) {
                Main.getStage().close();
            }

            // For debugging purposes.
            // System.out.printf("\n------------\nInput: %s\nOutput: %s\n-----------", input, this.ui.getResponse());
            return this.ui.getResponse();

        } catch (IOException e) {
            this.ui.appendResponse(e.getMessage());
            return this.ui.getResponse();
        }
    }
}





