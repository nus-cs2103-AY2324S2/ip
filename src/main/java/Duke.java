import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(Greet.logo);
        System.out.println(Greet.greet);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        if (command.equals("bye")) {
            System.out.println(Greet.bye);
        } else {
            System.out.println(Echo.echo(command));
        }
    }
}
