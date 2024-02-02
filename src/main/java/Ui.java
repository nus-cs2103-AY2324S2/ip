import java.util.Scanner;

public class Ui {
    private Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void printWelcome() {
        System.out.println("_________________________\n"
                + " __  __       __\n"
                + " \\ \\/ /__    / /\n"
                + "  \\  / _ \\  /_/ \n"
                + "  /_/\\___/ (_)\n\n"
                + "I'm ChatBro!\n"
                + "What can I do for you bro?\n"
                + "Use the available commands: list, bye, mark, unmark, delete, OR\n"
                + "create a new task (todo, deadline, event) to store in your list bro.\n"
                + "_________________________\n");
    }
    public void printLine() {
        System.out.println("_________________________\n");
    }
    public void printBye() {
        printLine();
        System.out.println("Hasta la vista bro!\n");
        printLine();
        sc.close();
    }
    public String readInput() {
        return sc.nextLine();
    }

}
