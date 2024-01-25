import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String hello = "Hello!\n" + "What can I do for you?\n";
        System.out.println(hello);

        String bye = "See you soon!";

        String indent = "    ";

        while (true) {
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            String[] arr = str.split(" ");
            String action = arr[0];
            if (action.equals("bye") || action.equals("Bye")) {
                System.out.println(bye);
                break;
            } else if (action.equals("list")){
                for (int i = 0; i < list.size(); i++) {
                    int n = i + 1;
                    System.out.println(indent + n + ". " + list.get(i));
                }
            } else if (action.equals("mark")) {
                int index = Integer.valueOf(arr[1]);
                Task target = list.get(index - 1);
                target.mark();
                System.out.println(indent + "Task have been marked as done.");
                System.out.println(indent + target);
            } else if (action.equals("unmark")) {
                int index = Integer.valueOf(arr[1]);
                Task target = list.get(index - 1);
                target.unmark();
                System.out.println(indent + "Task have been unmarked.");
                System.out.println(indent + target);

            } else {
                Task cur = new Task(str);
                list.add(cur);
                System.out.println(indent + "added: " + cur);
            }
        }
    }
}
