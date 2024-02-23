package reacher;

import javafx.scene.layout.AnchorPane;
import reacher.command.Command;
import reacher.ui.MainWindow;

import java.time.format.DateTimeParseException;

public class Reacher {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Reacher(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadList());
    }

    public String handleInput(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(input, tasks, ui, storage);
        } catch (ReacherException e) {
            return (e.getMessage());
        } catch (DateTimeParseException e) {
            return("Pls provide correct format: yyyy-mm-dd");
        }
    }

}
