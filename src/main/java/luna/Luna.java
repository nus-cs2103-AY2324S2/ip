package luna;

import luna.command.Command;

/**
 * A Luna object represent the application character and attributes.
 */
public class Luna {
    private final Storage storage;
    private final TaskList tasks;

    private final Ui ui;

    /**
     * Constructs a luna object and initialise its storage, task list and ui.
     */
    public Luna() {
        storage = new Storage("taskList");
        tasks = new TaskList();
        ui = new Ui("luna");
    }

    /**
     * Allows the program to run
     */
    public void run() {

        assert ui != null : "ui should not be equals to null";
        String userInput = ui.readInput();
        Command c = Parser.parse(userInput);

        assert storage != null : "storage should not be equals to null";
        assert tasks != null : "tasks should not be equals to null";
        c.execute(tasks, ui, storage);
        run();
    }

    /**
     * To define the application behaviour when the program first loads up
     */
    public void begin() {

        ui.greet();
        run();
    }

    /**
     * Gets the input and use a parser to obtain a command and executes it.
     */
    public void readIn(String input) {

        String userInput = ui.readInput(input);
        assert userInput != null : "user input should not be null";

        Command c = Parser.parse(userInput);
        assert c != null : "command should not be null";

        c.execute(tasks, ui, storage);
    }

    public static void main(String[] args) {
        new Luna().begin();
    }
}

