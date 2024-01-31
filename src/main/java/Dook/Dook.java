import Task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dook {

    private static final ArrayList<String> FILE_PATH = new ArrayList<>(List.of("data", "dook.txt"));
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    Dook() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH);
    }

    public void run() {
        try {
            this.storage.checkFile();
        } catch (Exception e) {
            System.out.println("meow :( an error happened when opening your files, please restart to prevent data loss");
            return;
        }
        this.ui.introduce();
        try {
            this.tasks = this.storage.loadTaskListFromFile();
        } catch (IOException e) {
            this.ui.println("error while loading file, specific error: " + e);
        } catch (DookException e) {
            this.ui.println(e.getMessage());
        }
        boolean willExitLoop = false;
        while (!willExitLoop) {
            String input = ui.getInput();
            this.ui.printSeparator();
            try {
                Command c = this.parser.parse(input);
                c.execute(this.tasks, this.ui, this.storage);
                willExitLoop = c.isExit();
            } catch (Exception e) {
                this.ui.printException(e);
            } finally {
                this.ui.printSeparator();
            }
        }
    }

    public static void main(String[] args) {
        Dook dook = new Dook();
        dook.run();
    }
}