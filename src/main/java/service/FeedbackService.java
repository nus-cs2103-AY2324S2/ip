package service;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.util.Objects;
import java.util.Scanner;

public class FeedbackService {

    public void run() {
        this.PrintWelcome();
        this.listen();
        this.exit();
    }

    private void listen() {
        Scanner scanner = new Scanner(System.in);
        String exitKeyword = "bye";

        String curInput = scanner.nextLine();

        while (!curInput.equals(exitKeyword)) {
            this.Echo(curInput);
            curInput = scanner.nextLine();
        }
    }

    private void exit() {
        System.out.println("----------------------------------------------");
        System.out.println("Goodbye! Hope to see you again!");
        System.out.println("----------------------------------------------");
    }

    private void PrintWelcome() {
        // Logo generated from : https://patorjk.com/software/taag/#p=display&f=Sub-Zero&t=OAK
        String logo =
                "______     ______     __  __    \n" +
                        "/\\  __ \\   /\\  __ \\   /\\ \\/ /    \n" +
                        "\\ \\ \\/\\ \\  \\ \\  __ \\  \\ \\  _-.    \n" +
                        " \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \n" +
                        "  \\/_____/   \\/_/\\/_/   \\/_/\\/_/ \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("----------------------------------------------");

        System.out.println("Welcome! I'm Professor Oak");
        System.out.println("What can I do for you?");

        System.out.println("----------------------------------------------");

        System.out.println("Bye! Hope to see you again soon!");
    }

    private void Echo(String input) {
        System.out.println("----------------------------------------------");
        System.out.println(input);
        System.out.println("----------------------------------------------");
    }
}
