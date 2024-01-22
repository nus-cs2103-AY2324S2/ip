import java.util.Scanner;
public class Duke {
    String name = "XVX-016 Aerial";
    String command = "";
    Scanner scanner1;

    public Duke() {
    }

    public void greeting() {
        horizontalLines();
        indent();
        System.out.println("HELLO, Nice to meet you. I am " + this.name + "! \uD83E\uDD16");
        indent();
        System.out.println("What are we doing today?");
        horizontalLines();
    }

    public void bye() {
        indent();
        System.out.println("See you next time! ♥( ˆ⌣ ˆԅ)");
        horizontalLines();
        this.scanner1.close();
    }

    public void horizontalLines() {
        System.out.println("\n    ____________________________________________________________");
    }

    public void echo() {
        horizontalLines();
        indent();
        System.out.println("╭( ๐ _๐)╮");
        indent();
        System.out.println("\uD83D\uDDE8️ You said THIS: ");
        indent();
        System.out.println(this.command);
        horizontalLines();
        input();
    }

    public void indent() {
        System.out.print("    ");
    }

    public void input() {
        String commandInput = scanner1.nextLine();
        this.command = commandInput;

        if (this.command.equals("bye")) {
            bye();
        } else {
            echo();
        }
    }

    public static void main(String[] args) {
        Duke Duke1 = new Duke();
        Duke1.greeting();
        Duke1.scanner1 = new Scanner(System.in);
        Duke1.input();
    }
}
