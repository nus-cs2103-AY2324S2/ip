package saopig;

import java.util.Scanner;

public class Ui {
    private final String logo = " ____    _    ___  ____ ___ ____   ____   ___ _____ \n" +
            "/ ___|  / \\  / _ \\|  _ \\_ _/ ___| | __ ) / _ \\_   _|\n" +
            "\\___ \\ / _ \\| | | | |_) | | |  _  |  _ \\| | | || |  \n" +
            " ___) / ___ \\ |_| |  __/| | |_| | | |_) | |_| || |  \n" +
            "|____/_/   \\_\\___/|_|  |___\\____| |____/ \\___/ |_|\n";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public short printLine() {
        System.out.println("____________________________________________________________");
        return 0;
    }

    public short showWelcome() {
        System.out.println(this.logo);
        System.out.println("Oh, hello there!\n " +
                "I am saopig.Saopig, your personal assistant.\n " +
                "It's such a pleasure to meet you.\n " +
                "I'm just over the moon to have someone new to chat with!\n " +
                "I hope your day is as bright and cheerful as a sunny garden.\n");
        System.out.println("____________________________________________________________");
        return 0;
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    private void printMessageWithLines(String message) {
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("\n" +
                "As our time together comes to a close, " +
                "I just want to say it's been an absolute delight!\n " +
                "Remember, every day is a new adventure waiting to happen.\n " +
                "Bye for now, and take care! ");
        System.out.println(" ______   _______   _ \n" +
                "| __ ) \\ / / ____| | |\n" +
                "|  _ \\\\ V /|  _|   | |\n" +
                "| |_) || | | |___  |_|\n" +
                "|____/ |_| |_____| (_)");
    }
}
