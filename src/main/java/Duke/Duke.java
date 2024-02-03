package Duke;

import Duke.Commands.Command;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.time.DateTimeException;
import java.util.Scanner;

public class Duke {
    TaskList taskList;
    private String filePath = "Duke/taskList.txt";
    private Storage storage;
    private Ui ui;
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = this.storage.load();
        this.ui = new Ui();
    }

    public void run() {
        this.ui.sayHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parse(fullCommand, this.ui,this.taskList, this.storage);
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.printErrorMessage(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Duke("./taskList.txt").run();
    }
}
