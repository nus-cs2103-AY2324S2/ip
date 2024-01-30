package lamball;

import java.util.Scanner;

public class Lamball {
    public static String indent = "    ____________________________________________________________\n";

    private TaskList tasks;
    private Ui ui;

    public Lamball() {
        tasks = new TaskList();
        ui = new Ui();
    }

    private void initialize() {
        ui.greetingMessage();
        Storage.obtainSavedFile(this);
    }

    public void initParse(String msg, boolean isInit) throws LamballParseException {
        String[] comd = Parser.parse(msg);
        tasks.runComd(comd, true);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isActive = true;

        this.initialize();

        while (isActive) {
            System.out.print("    You:");
            String userInput = scanner.nextLine();

            // Echo the user's command
            try {
                String[] comd = Parser.parse(userInput);
                isActive = tasks.runComd(comd, false);
                if (!isActive) {
                    ui.goodbyeMessage();
                }
            } catch (LamballParseException e) {
                ui.displayError(e);
            }

        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Lamball().run();
    }
}


