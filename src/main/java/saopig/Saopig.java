package saopig;

import saopig.command.Command;
import saopig.task.TaskList;

import java.util.Scanner;

public class Saopig {
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();
    private Storage storage = new Storage(this.ui, this.taskList);

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
                ui.printLine();
            } catch (SaopigInvaildSizeException e) {
                ui.showError("Unknown command error.");
                ui.printLine();
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Saopig().run();
    }
}
