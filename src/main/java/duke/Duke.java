package duke;

import java.nio.file.Paths;

public class Duke {
    // protected static final String DATA_DIRECTORY = Paths.get( "src", "main", "java", "data").toString();
    protected static final String DATA_DIRECTORY = Paths.get( "src", "main", "java", "data").toString();
    protected static final String DATA_FILE = Paths.get(DATA_DIRECTORY, "tasks.txt").toString();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage(tasks);
        this.ui = new Ui("URSA", this.storage, this.tasks);
    }

    public void run() {
        this.ui.start();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}