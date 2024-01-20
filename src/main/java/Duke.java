import java.util.Scanner;

public class Duke {
    private UserInterface ui;

    public Duke(UserInterface ui) {
        this.ui = ui;
    }

    public void run() {
        ui.greetUser();
        ui.exit();
    }

    public static void main(String[] args) {
        UserInterface consoleUI = new ConsoleUserInterface();
        Duke duke = new Duke(consoleUI);
        duke.run();
    }
}
