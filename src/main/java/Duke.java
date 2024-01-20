import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = "Chucklbot";
        System.out.println("Hello I'm " + logo + "\nWhat can I do for you? \n \nBye! Hope to see you again soon.");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> store = new ArrayList<>();

        String byeMessage = "Bye! Hope to see you again soon.";
        while (true) {
            String curr = sc.next();
            if (curr.equals("Bye") || curr.equals("bye")) {
                System.out.println(byeMessage);
                break;
            } else if (curr.equals("list")) {
                displayTasks(store);
            } else {
                addList(store, curr);
            }

        }


    }

    // if list entered
    public static void displayTasks(ArrayList<String> store) {
        for (int i = 0; i < store.size(); i++) {
            System.out.println((i+1) + ". " + store.get(i));
        }
    }

    public static void addList(ArrayList<String> store, String added) {
        store.add(added);
        System.out.println("added: " + added);
    }
}
