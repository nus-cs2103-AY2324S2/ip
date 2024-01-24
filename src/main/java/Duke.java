import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {
    List<String> todolist = new ArrayList<String>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sep = "\t__________________________________________";

        // hi
        System.out.println(sep);
        System.out.println("\tHello! I'm JOSEPH JOSHTUR!!!");
        System.out.println("\tWhat can I do for you?");
        System.out.println(sep);

        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            System.out.println(sep);
            System.out.println("\t" + command);
            System.out.println(sep);
            command = scanner.nextLine();
        }

        // bye
        System.out.println(sep);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(sep);
    }
    
    public void addItem(String item) {
        todolist.add(item);
    }
}
