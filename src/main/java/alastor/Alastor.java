package alastor;

import alastor.command.Command;

/**
 * Represents Alastor, a personal assistant chatBot that helps a person to keep track of various things.
 */
public class Alastor {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs an Alastor object.
     */
    public Alastor() {
        String filePath = "./data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AlastorException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(tasks, ui, storage);
        } catch (AlastorException e) {
            return e.getMessage();
        }
    }

    protected String greetings() {
        return ui.showGreet();
    }
}
