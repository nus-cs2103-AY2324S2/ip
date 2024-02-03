package toothless;

import toothless.commands.Command;

/**
 * A chatbot that helps store and organise your tasks
 */
public class Toothless {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Construct a chatbot with a file storage at the filepath
     * @param filepath
     */
    public Toothless(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            ui.showLoadingTasks();
            tasks = new TaskList(storage.load());
            ui.showIncompleteTask(tasks);
        } catch (ToothlessException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Start the application and query the user to input commands.
     */
    public void start() {
        boolean isExit = false;
        ui.showWelcome();
        while(!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Command command = Parser.parseCommand(input);
                isExit = command.handle(ui, tasks, storage);
            } catch (ToothlessException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Toothless("./data/toothless.txt").start();
    }
}
