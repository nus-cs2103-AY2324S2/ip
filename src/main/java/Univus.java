import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Univus {
    private static Scanner scanner;
    private List<String> store;
    public Univus() {
        this.scanner = new Scanner(System.in);
        this.store = new ArrayList<>();
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
        if (message.equals("bye")){
            bye();
            scanner.close();
        } else if (message.equals("list")) {
            System.out.println("____________________________________________________________");
            int index = 1;
            for (String msg : store) {
                System.out.println(index + ". " + msg);
                index++;
            }
            System.out.println("____________________________________________________________");
        } else {
            store.add(message);
            System.out.println("____________________________________________________________");
            System.out.println("added: " + message);
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
