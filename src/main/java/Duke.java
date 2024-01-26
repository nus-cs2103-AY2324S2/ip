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


        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] arr = str.split(" ", 2);
            String action = arr[0];
            if (action.equalsIgnoreCase("bye")) {
                System.out.println(bye);

            } else if (action.equalsIgnoreCase("list")){
                for (int i = 0; i < list.size(); i++) {
                    int n = i + 1;
                    System.out.println(indent + n + ". " + list.get(i));
                }
            } else if (action.equalsIgnoreCase("mark")) {
                int index = Integer.valueOf(arr[1]);
                Task target = list.get(index - 1);
                target.mark();
                System.out.println(indent + "Task have been marked as done.");
                System.out.println(indent + target);

            } else if (action.equalsIgnoreCase("unmark")) {
                int index = Integer.valueOf(arr[1]);
                Task target = list.get(index - 1);
                target.unmark();
                System.out.println(indent + "Task have been unmarked.");
                System.out.println(indent + target);

            } else {
                Task task;
                if (action.equalsIgnoreCase("todo")) {
                  task = new Todo(arr[1]);
                } else if (action.equalsIgnoreCase("deadline")) {
                    String[] dlarr = arr[1].split("/", 2);
                    String name = dlarr[0];
                    String by = dlarr[1];
                    task = new Deadline(name, by);


                } else if (action.equalsIgnoreCase("event")) {
                    String event = arr[1];
                    String[] evarr = arr[1].split("/", 3);
                    String name = evarr[0];
                    String from = evarr[1];
                    String to = evarr[2];
                    task = new Event(name, from, to);
                } else {continue;}

                list.add(task);
                int n = list.size();
                System.out.println(indent + "added: " + task);
                System.out.println("You now have " + n + " tasks in the list.");
            }
        }
        scanner.close();
    }
}
