import java.util.*;

public class Awex {
    /**
     * Prints explainer message after user gives erroneous inputs.
     */
    public static void message() {
        System.out.println("Input type must be one of:");
        System.out.println("  1. list");
        System.out.println("  2. mark <task number>");
        System.out.println("  3. unmark <task number>");
        System.out.println("  4. todo <task>");
        System.out.println("  5. deadline <task> /by <deadline>");
        System.out.println("  6. event <task> /from <start> /to <end>");
        System.out.println("  7. delete <task number>");
        System.out.println("Type 'bye' to exit.");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm AWEX!\nWhat can I do for you?");
        LinkedList<Task> list = new LinkedList<>();
        // fill list with saved tasks FOLLOW FORMAT
        Scanner sc = new Scanner(System.in);
        String next;
        String[] arr;
        while (true) {
            next = sc.nextLine();
            arr = next.split(" ");
            if (next.equals("bye")) {
                // write to file BUT HOW
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals("list")) {
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
            } else if (arr[0].equals("mark") || arr[0].equals("unmark") || arr[0].equals("delete")) {
                String[] array = next.split(" ");
                if (array.length != 2) {
                    System.out.println("Format should be '" + arr[0] + " <task number>'");
                } else {
                    int i = Integer.parseInt(array[1]);
                    int len = list.size();
                    if (i == 0) {
                        System.out.println("Pick a value between 1 and " + len + ".");
                    } else if (i > len) {
                        System.out.println("List has only " + len + " tasks.");
                    } else {
                        if (arr[0].equals("delete")) {
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + list.remove(i - 1).showAll());
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                        } else {
                            Task t = list.get(i - 1);
                            t.changeStatus(arr[0]);
                            System.out.println("  " + t.showAll());
                        }
                    }
                }
            } else {
                Task t;
                if (arr[0].equals("todo")) {
                    if (arr.length == 1) {
                        System.out.println("Format should be 'todo <task>'");
                        continue;
                    }
                    t = new TodoTask(arr[1]);
                } else if (arr[0].equals("deadline")) {
                    String[] array = next.split("/");
                    if (array.length != 2) {
                        System.out.println("Format should be 'deadline <task> /by <deadline>'");
                        continue;
                    }
                    t = DeadlineTask.of(arr);
                } else if (arr[0].equals("event")){
                    String[] array = next.split("/");
                    if (array.length != 3) {
                        System.out.println("Format should be 'event <task> /from <start> /to <end>'");
                        continue;
                    }
                    t = EventTask.of(arr);
                } else {
                    message();
                    continue;
                }
                list.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + t.showAll());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
        }
    }
}