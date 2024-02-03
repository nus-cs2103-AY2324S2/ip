import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

/**
 * Main class
 */
public class Main {
    private Storage storage;
    private ArrayList<Task> taskList = new ArrayList<>();
    private TaskList tasks;
    private Ui ui;
    

    public Main() {
        this.ui = new Ui();
        this.storage = new Storage(taskList);
        this.tasks = new TaskList(storage);
    }

    public static void main(String[] args) {
        ListAdder newList = new ListAdder();
        newList.start();
    }    
}