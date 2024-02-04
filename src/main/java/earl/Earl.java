package earl;

import earl.exceptions.EarlException;
import earl.logic.Handler;
import earl.util.Parser;
import earl.util.Storage;
import earl.util.TaskList;
import earl.util.Ui;

public class Earl {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Earl(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList temp;
        try {
            temp = new TaskList(storage.load());
        } catch (EarlException e) {
            ui.makeResponse(e.getMessage());
            temp = new TaskList(); // empty list when fail to read
        }
        tasks = temp;
    }

    public void run() {
        ui.showGreeting();
        // main loop
        String input = ui.getUserInput();
        String[] command;
        while (!input.equals("bye")) {
            try {
                command = Parser.parseUserInput(input);
                Handler.dispatch(command).handle(tasks, ui);
            } catch (EarlException e) {
                ui.makeResponse(e.getMessage());
            } finally {
                input = ui.getUserInput();
            }
        }
        // save to file
        try {
            storage.save(tasks.getList());
        } catch (EarlException e) {
            ui.makeResponse(e.getMessage());
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Earl("data/earl.txt").run();
    }
}