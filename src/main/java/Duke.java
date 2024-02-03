public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private Duke() {
        storage = new Storage("./data/duke.txt");
        tasks = new TaskList(this.storage.loadTasks());
        ui = new Ui();
        parser = new Parser();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        ui.printGreeting();

        while (true) {
            boolean isExit = false;
            try {
                String input = this.ui.getInput();
                Command command = this.parser.parseCommand(input);
                command.setTasks(this.tasks);
                command.execute(this.ui);
                if (command instanceof ByeCommand) {
                    isExit = true;
                }
            } catch (Exception e) {
                this.ui.printError(e);
            }
            if (isExit) {
                break;
            }
        }        
    }
}
