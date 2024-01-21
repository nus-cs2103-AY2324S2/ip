import java.util.Scanner;

public class Naruto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        while (true) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                exit();
            }
            echo(s);
        }
    }

    static void greet() {
        spacer();
        System.out.println("    Hello! I'm Naruto, and I'm gonna become the next Hokage!" +
                "\n    Believe it!");
        spacer();

    }

    static void exit() {
        spacer();
        System.out.println("    See you next time!");
        spacer();
        System.exit(0);
    }

    static void spacer() {
        System.out.println("    --------------------------------------------------------" +
                "----------");
    }

    static void echo(String s) {
        spacer();
        System.out.println("    " + s);
        spacer();
    }

}
