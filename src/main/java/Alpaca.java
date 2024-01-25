import java.util.Scanner;
import java.util.ArrayList;

public class Alpaca {
    static String name = "Alpaca";
    static Scanner scanner;
    static ArrayList<String> list = new ArrayList<String>();

    private static void divider() {
        System.out.println("____________________________________________________________");
    }

    private static void greeting() {
        divider();
        System.out.println("Hihi! I'm " + Alpaca.name + "\nWhat can I do for you?");
        divider();
    }

    private static void bye() {
        divider();
        System.out.println("cucu");
        divider();
    }

    private static void list() {
        divider();
        int counter = 1;
        for (String i : list) {
            System.out.println(counter + ". " + i);
            counter++;
        }
        divider();
    }

    private static void add(String item) {
        divider();
        System.out.println("added: " + item);
        list.add(item);
        divider();
    }

    private static void processInput() {
        String line = scanner.nextLine();
        if (line.equals("bye")) {
            bye();
            return;
        } else if (line.equals("list")) {
            list();
        } else {
            add(line);
        }
        processInput();
    }
    
    public static void main(String[] args) {
        greeting();
        scanner = new Scanner(System.in);
        processInput();
        scanner.close();
    }
}