package duke;

import duke.command.Parser;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    private final Ui ui;

    public Duke() {
       ui = new Ui();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        ui.startConversation();
        String username = ui.getUsername();
        Parser parser = new Parser(username);

        while (true) {
            String input = scanner.nextLine();
            String[] cmd = input.split(" ");

            if (!cmd[0].equalsIgnoreCase("bye")) {
                parser.parse(input);
            } else {
                ui.defaultExit();
                break;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}