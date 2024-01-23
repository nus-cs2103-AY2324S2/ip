import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String line = "________________________________________________________________________________________";
    public static ArrayList<String> list = new ArrayList<String>();
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
        System.out.println("added: " + input);
        System.out.println(line);
    }
    public void addList(String input) {
        list.add(input);
        this.echo(input);
    }

    public void printList() {
        System.out.println(line);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i));
        }
        System.out.println(line);
    }
    public static void main(String[] args) {
        Duke alfred = new Duke();
        alfred.greet();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                alfred.bye();
                break;
            }
            if (input.equals("list")) {
                alfred.printList();
                continue;
            }
            alfred.addList(input);
        }
    }
}
