import java.util.ArrayList;
import java.util.Scanner;

public class Martin {
    static String NAME = "Martin";
    static ArrayList<Task> todoList = new ArrayList<>();

    public static void main(String[] args) {
        // stop gap solution to magic numbers for task numbering
        todoList.add(new Task("dummy offset"));

        sayGreeting();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().strip();
            String[] words = line.split(" ");
            String command = words[0];
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                printList();
            } else if (command.equals("mark")) {

            } else if (command.equals("unmark")) {

            } else {
                addToList(command);
            }
        }
        sayBye();
        sc.close();
    }

    public static void addToList(String item) {
        todoList.add(new Task(item));
        System.out.println("added: " + item);
    }

    public static void printList() {
        for (int i = 1; i < todoList.size(); i++) {
            System.out.println(i + ". " + todoList.get(i));
        }
    }

    public static void sayGreeting() {
        System.out.println("Hello from " + NAME);
        System.out.println("What can I do for you?");
    }

    public static void sayBye() {
        System.out.println("Bye from " + NAME);
    }
}
