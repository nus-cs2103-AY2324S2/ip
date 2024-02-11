// package lumiere;

// import lumiere.tasks.*;
// import lumiere.*;

import java.io.IOException;

public class Lumiere {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static String TASK_LIST_PATH = "./lumiere.txt";

    public Lumiere() throws IOException {
        ui = new Ui();
        storage = new Storage(TASK_LIST_PATH);
        tasks = new TaskList();
        storage.loadTasksFromFile(tasks);
    }

    public void run() throws IOException {
        Lumiere lumiere = new Lumiere();
        lumiere.ui.run(lumiere.tasks, lumiere.storage);
        // lumiere.storage.save(lumiere.tasks); // for what
    }

    public static void main(String[] args) throws IOException {
        new Lumiere().run();
    }
}
