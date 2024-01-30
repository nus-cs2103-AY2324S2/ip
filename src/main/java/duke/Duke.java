package duke;

import command.Command;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

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
            ui.showLoadingError();
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
                c.excuteCommand(tasks, ui, storage);
                isExit = c.getIsExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printClosingDottedLine();
            }
        }


    }




    public static void main(String[] args) {
        new Duke("duke").run();
    }




}









