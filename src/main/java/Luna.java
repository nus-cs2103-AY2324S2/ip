
public class Luna {
    private final Storage storage;
    private final TaskList tasks;

    private final Ui ui;

//    enum commandHints {
//        LIST,
//        TODO,
//        DEADLINE,
//        EVENT,
//        MARK,
//        EXIT
//    }

    public Luna() {
        storage = new Storage("taskList");
        tasks = new TaskList();
        ui = new Ui("Luna");
//        commandHints currentHint = commandHints.EXIT;
    }

    public void start() {
        ui.greet();
        run();
    }

    public void run() {
        String userInput = ui.readInput();
        Command c = Parser.parse(userInput);
        c.execute(tasks,ui,storage);
        run();
    }


    public static void main(String[] args)  {
        new Luna().start();
    }


}

