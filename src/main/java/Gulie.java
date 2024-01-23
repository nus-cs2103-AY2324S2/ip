import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Gulie {
    private final static String name = "Güliedistodiez";
    private final static String line = "____________________________________________________________";
    private ArrayList<String> list;

    public Gulie() {
        System.out.println(line);
        this.greet();
        System.out.println(line);
        this.list = new ArrayList<>();

    }

    public boolean input(String str) {
        System.out.println(line);
        if (str.equals("bye")) {
            this.exit();
            System.out.println(line);
            return false;
        } else if (str.equals("list")) {
            this.list();
        } else {
            this.store(str);
        }
        System.out.println(line);
        return true;
    }

    private void greet() {
        System.out.println(String.format(" Hello! I'm %s\n What can I do for you?", name));
    }

    private void exit() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    private void list() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format(" %d. %s", i + 1, list.get(i)));
        }
    }

    private void store(String str) {
        System.out.println(" added: " + str);
        this.list.add(str);
    }

    public static void main(String[] args) {
        Gulie gulie = new Gulie();
        Scanner scanner = new Scanner(System.in);
        while (gulie.input(scanner.nextLine())) {

        }
    }
}
