import java.util.Scanner;
public class Univus {
    private static Scanner scanner;
    public Univus() {
        this.scanner = new Scanner(System.in);
    }
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Univus");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public void bye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public void echo(String message) {
        if(message.equals("bye")){
            bye();
            scanner.close();
        }
        else {
            System.out.println("____________________________________________________________");
            System.out.println(message);
            System.out.println("____________________________________________________________");
        }
    }
    public static void main(String[] args) {
        Univus univus = new Univus();
        univus.greet();
        while (true) {
            String message = scanner.nextLine();
            univus.echo(message);
            if (message.equals("bye")) {
                break;
            }
        }
    }
}
