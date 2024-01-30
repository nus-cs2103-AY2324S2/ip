package duke;

import duke.command.Command;

import java.io.FileNotFoundException;

/**
 * The Duke program is features task management and displays task list
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    /**
     * Constructs a Duke class
     * @param filePath of the file path in string representation
     */

    public Duke(String filePath) {
        this.ui = new TextUi();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadFromFile());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application
     */
    public void run() {
        ui.showGreetMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }


    /**
     * Creates a Duke object and runs it
     * @param args
     */

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}



//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

