import java.util.Scanner;
public class Duke {
    private void greet() {
        System.out.println("Hello! I'm Friendy.");
        System.out.println("What can I do for you?");
    }

    private void farewell() {
        System.out.println("Bye. I will miss you!");
    }

    private void echo(String s) {
        System.out.println("Yes Boss, I will " + s);
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        Scanner sc = new Scanner(System.in);
        d.greet();
        String s = sc.nextLine();
        while (!s.equals("bye")) {
            d.echo(s);
            s = sc.nextLine();
        }
        d.farewell();
    }
}

