package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

import java.util.Timer;
import java.util.TimerTask;


public class Duke {
    //Initialise variables for the programs
    private TaskManager manager;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {

        ui = new Ui();
        Timer saveTimer = new Timer();
        storage = new Storage(filePath);
        try {
            manager = storage.loadFile();
        } catch (DukeException e) {
            //show loading error but i don't plan to show a loading error
            manager = new TaskManager();
        }
        TimerTask savingTask = new TimerTask() {
            @Override
            public void run() {
                storage.saveFile(manager);
            }
        };
        saveTimer.schedule(savingTask, 0, 2000); //update at 2 seconds


    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String next = ui.readInstructions();
                ui.showLine();
                ui.printOutput(Parser.parse(next, manager));
                ui.showLine();
                isExit = Parser.isExit();

            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError(" OPPPS!!!!That is not a number!!!!!!!!!!");
            } finally {
                ui.spacer();
            }
        }

    }
    public static void main(String[] args) throws Exception {
        new Duke("data/tasks.txt").run();

    }
}
