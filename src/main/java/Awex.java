import java.util.*;

public class Awex {
    public static void message() {
        System.out.println("Input type must be one of:");
        System.out.println("  1. list");
        System.out.println("  2. mark <task number>");
        System.out.println("  3. unmark <task number>");
        System.out.println("  4. todo <task>");
        System.out.println("  5. deadline <task> /by <deadline>");
        System.out.println("  6. event <task> /from <start> /to <end>");
        System.out.println("Type 'bye' to exit.");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm AWEX!\nWhat can I do for you?");
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        String[] arr = next.split(" ");
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                if (arr.length > 1) {
                    message();
                } else if (list.isEmpty()){
                    System.out.println("List is empty.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    int len = list.size();
                    for (int i = 1; i <= len; i++) {
                        System.out.println(i + "." + list.get(i - 1).showAll());
                    }
                }
            } else if (arr[0].equals("mark")) {
                String[] array = next.split(" ");
                if (array.length != 2) {
                    System.out.println("Format should be 'mark <task number>'");
                } else {
                    int i = Integer.parseInt(array[1]);
                    int len = list.size();
                    if (i > len) {
                        System.out.println("List has only " + len + " tasks.");
                    } else {
                        Task t = list.get(i - 1);
                        t.mark();
                        System.out.println("  " + t.showAll());
                    }
                }
            } else if (arr[0].equals("unmark")) {
                String[] array = next.split(" ");
                if (array.length != 2) {
                    System.out.println("Format should be 'unmark <task number>'");
                } else {
                    int i = Integer.parseInt(array[1]);
                    int len = list.size();
                    if (i > len) {
                        System.out.println("List has only " + len + " tasks.");
                    } else {
                        Task t = list.get(i - 1);
                        t.unmark();
                        System.out.println("  " + t.showAll());
                    }
                }
            } else {
                Task t =  null;
                if (arr[0].equals("todo")) {
                    if (arr.length > 1) {
                        t = new TodoTask(arr[1]);
                    } else {
                        System.out.println("Format should be 'todo <task>'");
                        next = sc.nextLine();
                        arr = next.split(" ", 2);
                        continue;
                    }
                } else if (arr[0].equals("deadline")) {
                    String[] array = next.split("/");
                    if (array.length != 2) {
                        System.out.println("Format should be 'deadline <task> /by <deadline>'");
                        next = sc.nextLine();
                        arr = next.split(" ", 2);
                        continue;
                    }
                    String[] hasWhat = arr[1].split("/", 2);
                    String[] hasTime = hasWhat[1].split(" ", 2);
                    t = new DeadlineTask(hasWhat[0], hasTime[1]);
                } else if (arr[0].equals("event")){
                    String[] array = next.split("/");
                    if (array.length != 3) {
                        System.out.println("Format should be 'event <task> /from <start> /to <end>'");
                        next = sc.nextLine();
                        arr = next.split(" ", 2);
                        continue;
                    }
                    String[] hasWhat = arr[1].split("/", 2);
                    String[] hasTimes = hasWhat[1].split("/", 2);
                    String[] hasStart = hasTimes[0].split(" ", 2);
                    String[] hasEnd = hasTimes[1].split(" ", 2);
                    t = new EventTask(hasWhat[0], hasStart[1], hasEnd[1]);
                } else {
                    message();
                    next = sc.nextLine();
                    arr = next.split(" ", 2);
                    continue;
                }
                list.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + t.showAll());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
            next = sc.nextLine();
            arr = next.split(" ", 2);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}