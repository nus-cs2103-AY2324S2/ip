package roland;

import roland.command.Command;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RolandException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.boot();
//        System.out.println(System.getProperty("user.dir"));
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.spacer();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (RolandException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.spacer();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("./src/main/java/roland/data/roland.txt").run();
    }



}