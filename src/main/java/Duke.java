public class Duke {
    private Ui ui;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        ui.printWelcomeMessage();
        Parser parser = new Parser();
        boolean isBye = false;
        while (!isBye) {
            String command = ui.getCommand();
            isBye = parser.parseCommand(command, ui, storage);
        }
        ui.printByeMessage();
    }
}
