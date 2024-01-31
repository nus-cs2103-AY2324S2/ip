package henry;

import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void greet() {
        String logo = "  _    _                       \n" +
                " | |  | |                      \n" +
                " | |__| | ___ _ __  _ __ _   _ \n" +
                " |  __  |/ _ \\ '_ \\| '__| | | |\n" +
                " | |  | |  __/ | | | |  | |_| |\n" +
                " |_|  |_|\\___|_| |_|_|   \\__, |\n" +
                "                          __/ |\n" +
                "                         |___/ \n";
        String greetMessage = "Hello! I'm Henry\nWhat can I do for you?";
        System.out.println(logo);
        System.out.println(greetMessage);
        System.out.println();
    }

    public void showError(String err) {
        System.err.println(err);
    }

    public void showLoadingError() {
        System.err.println("Error reading tasks from file!\nWill recreate file!");
    }
}
