import Commands.Command;
import Exceptions.DukeException;
import Parser.Parser;
import Storage.Storage;
import Storage.TaskList;
import UI.UserInterface;

public class Stille {
    private final UserInterface ui;
    private final Storage storage;
    private final TaskList list;

    public Stille() {
        this.ui = new UserInterface();
        this.storage = new Storage();
        this.list = new TaskList();

        try {
            list.loadFromSaveFormat(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    public void run() {
        ui.showOpeningMessage();

        boolean isExit = false;
        while(!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parseInput(input);
                isExit = c.execute(this.list, this.ui);
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        this.exit();
    }

    public void exit() {
        try {
            this.storage.save(this.list.toSaveFormat());
        } catch (DukeException e) {
            ui.showError(e);
        } finally {
            ui.showClosingMessage();
        }
    }

    public static void main(String[] args) {
        new Stille().run();
    }
}
