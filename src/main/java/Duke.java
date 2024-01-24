import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(Greet.logo);
        System.out.println(Greet.greet);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(Echo.echo(command));
            command = sc.nextLine();
        }
        System.out.println(Greet.bye);
    }
}
