import java.lang.reflect.Array;
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lst = new ArrayList<>();
        intro();
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("list")) {
                displayList(lst);
            } else if (s.equals("bye")) {
                exit();
                return;
            } else {
                addToList(lst, s);
            }
        }
//        echo(sc);
    }
    private static void intro() {
        String logo = "        _  _        \n  __ _ | || | _   _ \n / _` || || || | | |\n| (_| || || || |_| |\n \\__,_||_||_| \\__, |\n              |___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Ally \n" + "What can I do for you?");
    }
    private static void echo(Scanner sc) {
        String task = sc.nextLine();
        while (!task.equals("bye")) {
            System.out.println(task);
            task = sc.nextLine();
        }
    }
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addToList(ArrayList<String> lst, String s) {
        lst.add(s);
        System.out.println("added: " + s);
    }

    private static void displayList(ArrayList<String> lst) {
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(i+1 + ": " + lst.get(i));
        }
    }
}
