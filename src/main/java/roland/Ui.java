package roland;

import java.util.Scanner;

public class Ui {

    private final String bot = "[ROLAND ⌐■-■] ";
    private final Scanner sc = new Scanner(System.in);

    public void boot() {
        this.spacer();
        String logo
                = "██████╗  ██████╗ ██╗      █████╗ ███╗   ██╗██████╗\n"
                + "██╔══██╗██╔═══██╗██║     ██╔══██╗████╗  ██║██╔══██╗\n"
                + "██████╔╝██║   ██║██║     ███████║██╔██╗ ██║██║  ██║\n"
                + "██╔══██╗██║   ██║██║     ██╔══██║██║╚██╗██║██║  ██║\n"
                + "██║  ██║╚██████╔╝███████╗██║  ██║██║ ╚████║██████╔╝\n"
                + "╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝\n";
        System.out.println(logo);

        System.out.println(bot + "Hello! I am ROLAND");
        System.out.println(bot + "What can I do for you?");
        this.spacer();
    }

    public void spacer() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public String readCommand() {

        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println(bot + message);
    }

    public String getBot() {
        return this.bot;
    }

    public void showLoadingError() {
        System.out.println("Let's get started with some tasks shall we?");
    }
}
