package Luna;

public class Luna {
    private final Storage storage;
    private final TaskList tasks;

    private final Ui ui;

    public Luna() {
        storage = new Storage("taskList");
        tasks = new TaskList();
        ui = new Ui("Luna");
    }

    public void run() {
        String userInput = ui.readInput();
        Command c = Parser.parse(userInput);
        c.execute(tasks,ui,storage);
        run();
    }

    public void begin() {

        ui.greet();
        run();
    }

    public void readIn(String input) {
        String userInput = ui.readInput(input);
        Command c = Parser.parse(userInput);
        c.execute(tasks,ui,storage);
    }

    public static void main(String[] args)  {
        new Luna().begin();
    }
}

