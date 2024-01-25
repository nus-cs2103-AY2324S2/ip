import java.lang.reflect.Array;
import java.util.*;
public class Duke {

    protected static ArrayList<Task> lst = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        intro();
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("list")) {
                displayList();
                continue;
            } else if (s.equals("bye")) {
                exit();
                return;
            } else {
                try {
                    String[] words = s.split(" ", 2);
                    String firstWord = words[0];
                    String taskName = words[1];
                    if (firstWord.equals("mark")) {
                        markComplete(Integer.parseInt(s.split(" ")[1]));
                    } else if (firstWord.equals("unmark")) {
                        unmarkComplete(Integer.parseInt(s.split(" ")[1]));
                    } else if (firstWord.equals("todo")) {
                        addToList(new Todo(taskName));
                    } else if (firstWord.equals("deadline")) {
                        addToList(new Deadline(taskName));
                    } else if (firstWord.equals("event")) {
                        addToList(new Event(taskName));
                    } else {
                        throw new AllyException();
                    }
                } catch (Exception e) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
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

    private static void addToList(String s) {
        lst.add(new Task(s));
        System.out.println("added: " + s);
    }

    private static void addToList(Task t) {
        lst.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    private static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(i+1 + ":" + lst.get(i));
        }
    }

    private static void markComplete(int i) {
        System.out.println("Nice! I've marked this task as done:");
        lst.get(i-1).markComplete();
        System.out.println(lst.get(i-1));
    }

    private static void unmarkComplete(int i) {
        System.out.println("OK, I've marked this task as not done yet:");
        lst.get(i-1).unmarkComplete();
        System.out.println(lst.get(i-1));
    }

}
