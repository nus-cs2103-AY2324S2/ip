import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        greet();
        echo();
    }

    public static void greet() {
        fillerLine();
        System.out.println("    Hello! I am Greg.\n    What can I do for you?");
        fillerLine();
    }

    public static void bye() {
        fillerLine();
        System.out.println("    Goodbye! Hope to see you again soon!");
        fillerLine();
    }

    public static void fillerLine() {
        System.out.println("    _______________________________________");
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        while (!(s.equals("bye"))) {
            fillerLine();
            System.out.println("    " + s);
            fillerLine();
            s = sc.next();
        }
        bye();
    }
}
