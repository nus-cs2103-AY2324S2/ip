public class Duke {
    private UI ui;

    public Duke(UI ui) {
        this.ui = ui;
    }

    public void run() {
        ui.greetUser();
        ui.exit();
    }

    public static void main(String[] args) {
        UI consoleUI = new UI();
        Duke duke = new Duke(consoleUI);

        duke.run();
    }
}
