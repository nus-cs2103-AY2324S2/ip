import java.util.ArrayList;

import Commands.Command;

import Parsing.Parser;

import Utilities.Storage;
import Utilities.TaskList;
import Utilities.Ui;

import Exceptions.YpxmmException;

public class Ypxmm {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ypxmm(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }

    public void run() {
        ui.sayHello();
        ui.showLine();
        boolean isRunning = true;
        while (isRunning) {
            String input = ui.readCommand();
            ui.showLine();
            try {
                ArrayList<String> parsed = Parser.parse(input);
                Command command = Command.valueOf(parsed.get(0).toUpperCase());
                command.execute(tasks, ui, storage, parsed);
                ui.showLine();
                if (command.equals(Command.BYE)) {
                    isRunning = false;
                }
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws YpxmmException {
        new Ypxmm("/data/Ypxmm.txt").run();
    }
}
