import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> todo = new ArrayList<>();
        List<String> marked = new ArrayList<>();
        int count = 1;
        System.out.println("Hello! I'm Lumiere\n" + "What can I do for you?\n");
        while (true) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count - 1; i++) {
                    System.out.println(marked.get(i) + " " + todo.get(i));
                }
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.contains("unmark")) {
                int space = command.indexOf(" ");
                int task = Integer.valueOf(command.substring(space + 1));
                String curr = marked.get(task - 1);
                String newMark = curr.replace("X", " ");
                marked.set(task - 1, newMark);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + todo.get(task - 1));
            } else if (command.contains("mark")) {
                int space = command.indexOf(" ");
                int task = Integer.valueOf(command.substring(space + 1));
                String curr = marked.get(task - 1);
                String newMark = curr.replace(" ", "X");
                marked.set(task - 1, newMark);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + todo.get(task - 1));
            } else { // to add new item
                System.out.println("added: " + command);
                todo.add(command);
                marked.add(Integer.toString(count) + ".[ ]");
                count++;
            }
        }
        sc.close();
    }
}
