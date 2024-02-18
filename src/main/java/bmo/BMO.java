package bmo;

import java.io.IOException;
import java.lang.String;
import java.util.Scanner;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.util.Parser;
import bmo.ui.Ui;
import bmo.command.Command;

public class BMO {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean isExit = false;

    public BMO() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadData(), ui, storage);
        } catch (IOException e) {
            System.out.println("Error: Unable to load data. " + e.getMessage());
            tasks = new TaskList(ui);
        }
    }

    public void run() {
        ui.printTutorial();

        Scanner sc = new Scanner(System.in);
        do {
            String input = sc.nextLine().toLowerCase().trim();
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.printErrInvalidCommand();
            }
        } while (!isExit);
    }

    public Storage getStorage() {
        return this.storage;
    }

    public Ui getUi() {
        return this.ui;
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public static void main(String[] args) {
        new BMO().run();
    }
}
