import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import Commands.Command;
import Exceptions.DukeException;
import Parser.Parser;
import Storage.Storage;
import Storage.TaskList;
import UI.UserInterface;
import tasks.*;

public class Stille {
    private UserInterface ui;
    private Storage storage;
    private TaskList list;

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
