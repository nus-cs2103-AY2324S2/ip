package Thames;

import java.io.FileNotFoundException;




import Thames.command.Command;

/**
 * Driver class for chat bot.
 */
public class Thames {
    /** Storage for saved task list */
    protected Storage storage;
    /** List of tasks */
    protected TaskList tasks;
    /** User interface that handles input from and output to user */
    protected Ui ui;

    /**
     * Creates Thames chat bot given the file path of task list.
     *
     * @param filePath File path of task list.
     */
    public Thames(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch(FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chat bot.
     */
    public void run() {
        boolean isExit = false;
        ui.greet();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ThamesException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Runs the chat bot.
     */
//    public static void main(String[] args) {
//        new Thames("data/tasks.txt").run();
//    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (ThamesException e) {
            return e.getMessage();
        }
    }



}
