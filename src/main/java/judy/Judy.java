package judy;

import judy.commands.Command;
import judy.commands.GreetCommand;
import judy.exceptions.DukeException;
import judy.parser.Parser;
import judy.storage.Storage;
import judy.task.TaskList;
import judy.ui.Ui;

public class Judy {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private static final String FILE_PATH = "judy.txt";

    public Judy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        if (storage.isFileExists()) {
            this.taskList = new TaskList(storage.load());
        } else {
            storage.createNewFile();
            this.taskList = new TaskList();
            storage.save(taskList.getTaskList());
        }
    }

    public void run() {
        ui.showLine();
        new GreetCommand().execute(storage, ui);
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Parser parser = new Parser(input, taskList);
                Command c = parser.parse();
                c.execute(storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Judy(FILE_PATH).run();
    }
}
