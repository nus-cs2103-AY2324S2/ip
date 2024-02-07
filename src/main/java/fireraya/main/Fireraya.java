package fireraya.main;

import fireraya.command.Command;
import fireraya.exception.FirerayaException;
import fireraya.exception.InvalidNumOfArgsException;
import fireraya.task.Deadline;
import fireraya.task.Event;
import fireraya.task.Task;
import fireraya.task.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;


public class Fireraya {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Fireraya(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FirerayaException e) {
            ui.displayLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {

        ui.startMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FirerayaException e) {
                ui.print(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Fireraya("current_list.txt").run();
    }
}
