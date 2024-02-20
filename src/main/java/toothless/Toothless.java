package toothless;

import toothless.commands.Command;

/**
 * A chatbot that helps store and organise your tasks
 */
public class Toothless {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

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

    public boolean isExit() {
        return isExit;
    }

    public String exit() {
        return ui.showFarewell();
    }

    /**
     * Gets the response from the chatbot based on the user input.
     * @param input The user input.
     * @return The response from the chatbot.
     * @throws ToothlessException If the input is invalid.
     */
    public String getResponse(String input) throws ToothlessException{
        Command command = Parser.parseCommand(input);
        isExit = command.isExit();
        return command.handle(ui, tasks, storage);
    }
}
