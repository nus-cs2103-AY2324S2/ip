package Lamball;

import java.util.Scanner;

public class Lamball {
    public static String indent = "    ____________________________________________________________\n";

    private TaskList tasks;
    private Ui ui;

    public Lamball() {
        tasks = new TaskList();
        ui = new Ui();
    }

    public void initialize() {
        ui.greetingMessage();
        Storage.obtainSavedFile(this);
    }

    public boolean parse(String msg, boolean isInit) throws LamballParseException{
        boolean active = Parser.parse(msg, isInit, tasks);
        return active;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Lamball lamball = new Lamball();
        boolean active = true;

        lamball.initialize();

        while (active) {
            System.out.print("    You:");
            String userInput = scanner.nextLine();

            // Echo the user's command
            try {
                active = lamball.parse(userInput, false);
                if (!active) {
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


