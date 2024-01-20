public class Duke {
    private ConsoleUserInterface ui;

    public Duke(ConsoleUserInterface ui) {
        this.ui = ui;
    }

    public void run() {
        ui.greetUser();
        ui.exit();
    }

    public static void main(String[] args) {
        ConsoleUserInterface consoleUI = new ConsoleUserInterface();
        Duke duke = new Duke(consoleUI);

        duke.run();
    }
}
