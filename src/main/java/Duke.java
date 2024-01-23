import java.util.Scanner;

public class Duke {
    static final String line = "________________________________________________________________________________________";

    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Alfred");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }
    public void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
    public void echo(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                duke.bye();
                break;
            }
            duke.echo(input);
        }
    }
}
