package toothless;

import toothless.commands.Command;

/**
 * A chatbot that helps store and organise your tasks
 */
public class Toothless {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Command command;

    /**
     * Construct a chatbot with a file storage at a predetermined file path.
     */
    public Toothless() {
        ui = new Ui();
        storage = new Storage("./data/toothless.txt");
    }

    public String greet() {
        return ui.showWelcome();
    }

    public String loadingTasks() {
        return ui.showLoadingTasks();
    }

    public String load() {
        try {
            tasks = new TaskList(storage.load());
            return ui.showIncompleteTask(tasks);
        } catch (ToothlessException e) {
            tasks = new TaskList();
            return e.getMessage();
        }
    }

    public String exit() {
        return ui.showFarewell();
    }

    /**
     * Start the application and query the user to input commands.
     */
    public String getResponse(String input) throws ToothlessException{
        command = Parser.parseCommand(input);
        return command.handle(ui, tasks, storage);
    }
}
