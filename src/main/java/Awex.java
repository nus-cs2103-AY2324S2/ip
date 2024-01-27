import java.util.*;

public class Awex {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"; */
        System.out.println("Hello! I'm AWEX!\nWhat can I do for you?");
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        String[] arr = next.split(" ");
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int len = list.size();
                for (int i = 1; i <= len; i++) {
                    System.out.println(i + "." + list.get(i - 1).showAll());
                }
            } else if (arr[0].equals("mark")) {
                Task t = list.get(Integer.parseInt(arr[1]) - 1);
                t.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + t.showAll());
            } else if (arr[0].equals("unmark")) {
                Task t = list.get(Integer.parseInt(arr[1]) - 1);
                t.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + t.showAll());
            } else {
                list.add(new Task(next));
                System.out.println("added: " + next);
            }
            next = sc.nextLine();
            arr = next.split(" ");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
