import java.util.ArrayList;
import java.util.Scanner;

public class Dude {
    static final String spacer = "____________________________________________________________\n";
    static final String name = "Dude";
    private ArrayList<String> list = new ArrayList<>();

    private static String getGreeting() {
        return spacer + "Hello! I'm Dude\nWhat can I do for you?\n" + spacer;
    }
    private static String getGoodbye() {
        return spacer + "Bye. Hope to see you again soon!\n" + spacer;
    }

    private void echo(String input) {
        System.out.println(spacer + input + "\n" + spacer);
    }

    private void add(String input) {
        this.list.add(input);
        System.out.println(spacer + "added: " + input + "\n" + spacer);
    }

    private void list() {
        System.out.println(spacer);
        for (int i = 1; i < this.list.size() + 1; i++) {
            System.out.println(i + ". " + this.list.get(i - 1));
        }
        System.out.println(spacer);
    }


    public static void main(String[] args) {
        System.out.println(getGreeting());

        Dude dude = new Dude();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                dude.list();
            } else {
                dude.add(input);
            }
        }
        System.out.println(getGoodbye());
    }
}
