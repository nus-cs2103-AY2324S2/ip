package chipchat;

import chipchat.action.Action;
import chipchat.exception.ChipchatException;
import chipchat.parser.Parser;
import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

public class App {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public App() {
        this.ui = new Ui();
        this.storage = new Storage();

        TaskList tmp;
        try {
            tmp = new TaskList(storage.load());
        } catch (ChipchatException x) {
            ui.showLoadingError();
            tmp = new TaskList();
        }
        this.tasks = tmp;
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readUserInput();
                ui.showLine();
                Action action = Parser.parseUserInput(userInput);
                action.run(tasks, ui, storage);
                isExit = action.isExit();
            } catch (ChipchatException x) {
                System.err.format("Exception at App::run(), %s\n", x);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new App().run();
    }
}
