package duke;

import command.Command;


public class Duke {



    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }


    public void run() {
        ui.printWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String command = ui.readCommand();
                ui.printOpeningDottedLine();

                Command c = Parser.parse(command);
                c.executeCommand(tasks, ui, storage);
                isExit = c.getIsExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printClosingDottedLine();
            }
        }


    }




    public static void main(String[] args) {
        new Duke("duke").run();
    }




}









