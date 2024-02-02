import java.util.Scanner;

public class UI {
    private Scanner scanner;
    public UI(Scanner sc) {
        this.scanner = sc;
    }

    public void showWelcome() {
        String logo = "        _  _        \n  __ _ | || | _   _ \n / _` || || || | | |\n| (_| || || || |_| |\n \\__,_||_||_| \\__, |\n              |___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Ally\n" + "What can I do for you?");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public boolean hasNextCommand() {
        return scanner.hasNextLine();
    }

}
