import java.util.Scanner;

public class UserInterface {
    /*
    This class is for the the user interface.
    */

    String greeting = "Hi! My name is HAL9000";
    String exit = "Bye! See ya soon";

    public void greet() {
        System.out.println(greeting);
    }
    public void exit() {
        System.out.println(exit);
    }

    public void echo(String string) {
        System.out.println(string);
    }

    public void poll(Scanner scan) {
        String input = scan.nextLine();
        if (!input.equals("bye")) {
            echo(input);
            poll(scan);
        }
        return;

    }
    public void start() {
        greet();
        Scanner scan = new Scanner(System.in);
        poll(scan);
        exit();
    }
}
