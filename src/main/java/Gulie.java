import java.util.ArrayList;
import java.util.Scanner;

public class Gulie {
    private final static String name = "Güliedistodiez";
    private final static String line = "____________________________________________________________";

    public static void main(String[] args) {
        Gulie gulie = new Gulie();
        Scanner scanner = new Scanner(System.in);
        while (gulie.input(scanner.nextLine())) {

        }
    }

    public Gulie() {
        System.out.println(line);
        this.greet();
        System.out.println(line);
    }

    public boolean input(String str) {
        if (str.equals("bye")) {
            this.exit();
            return false;
        }
        System.out.println(line);
        System.out.println(str);
        System.out.println(line);
        return true;
    }

    private void greet() {
        System.out.println(String.format(" Hello! I'm %s\n What can I do for you?", name));
    }

    private void exit() {
        System.out.println(line);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
