package duke;

import duke.command.Command;

import java.io.FileNotFoundException;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

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

