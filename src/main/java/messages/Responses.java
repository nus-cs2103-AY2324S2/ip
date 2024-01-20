package messages;

import java.util.ArrayList;
import java.util.Scanner;

public class Responses {
    private Requests requests = new Requests();

    // String constants
    private static final String BANTER_LOGO = ".______        ___      .__   __. .___________. _______ .______      \n" +
            "|   _  \\      /   \\     |  \\ |  | |           ||   ____||   _  \\     \n" +
            "|  |_)  |    /  ^  \\    |   \\|  | `---|  |----`|  |__   |  |_)  |    \n" +
            "|   _  <    /  /_\\  \\   |  . `  |     |  |     |   __|  |      /     \n" +
            "|  |_)  |  /  _____  \\  |  |\\   |     |  |     |  |____ |  |\\  \\----.\n" +
            "|______/  /__/     \\__\\ |__| \\__|     |__|     |_______|| _| `._____|\n" +
            "                                                                     \n";

    private static final ArrayList<String> GREET = new ArrayList<String>() {{
        add("Hello! I'm Banter");
        add("What can I do for you?");
    }};

    private static final ArrayList<String> EXIT = new ArrayList<String>() {{
        add("Bye. Hope to see you again soon!");
    }};


    // Messages
    private static final MessageBox GREET_MESSAGE = new MessageBox(GREET);

    private static final MessageBox EXIT_MESSAGE = new MessageBox(EXIT);


    // Methods
    private static void echo(String input) {
        MessageBox echoMessage = new MessageBox(new ArrayList<String>() {{
            add(input);
        }});
        echoMessage.print();
    }

    public void printGreetMessage() {
        System.out.println(BANTER_LOGO);
        GREET_MESSAGE.print();
    }

    public void printExitMessage() {
        EXIT_MESSAGE.print();
    }

    public void echoUntilExit() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (requests.isExit(input)) {
                printExitMessage();
                return;
            }
            Responses.echo(input);
        }
    }
}
