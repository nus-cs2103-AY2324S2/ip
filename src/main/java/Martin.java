import java.util.ArrayList;
import java.util.Scanner;

public class Martin {
    static String NAME = "Martin";
    static ArrayList<String> todoList = new ArrayList<>();
    public static void main(String[] args) {
        sayGreeting();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                sayBye();
                sc.close();
                break;
            }
            if (line.equals("list")) {
                printList();
            } else {
                addToList(line);
            }
        }
    }

    public static void addToList(String item) {
        todoList.add(item);
        System.out.println("added: " + item);
    }

    public static void printList() {
        for (int i = 0; i < todoList.size(); i++) {
            int index = i + 1;
            System.out.println(index  + ". " + todoList.get(i));
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
