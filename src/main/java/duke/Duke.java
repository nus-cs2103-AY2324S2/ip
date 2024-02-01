package duke;

public class Duke {

    private final String LOGO = "" 
            + "    __    _                 \n"
            + "   / /   (_)___  __  _______\n"
            + "  / /   / / __ \\/ / / / ___/\n"
            + " / /___/ / / / / /_/ (__  ) \n"
            + "/_____/_/_/ /_/\\__,_/____/  \n";

    private final String NAME = "Linus";

    private boolean running;

    private Ui ui;

    private TaskList taskList;

    private Parser parser;

    private Storage storage;

    private final String FILE_PATH = "./data/linus.txt";


    public Duke() {
        this.running = true;
        this.taskList = new TaskList();
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        this.parser = new Parser(taskList, ui);
    }

    public void start() {
        ui.print("Hello from\n" + LOGO);
        ui.print(NAME);
        this.readInput();
        //this.end();
    }

    public void loadData() {
        storage.loadData(taskList, ui);
    }

    public void readInput() {
        while (running) {
            parser.readUserInput();
        }
    }




    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.start();

    }
}

