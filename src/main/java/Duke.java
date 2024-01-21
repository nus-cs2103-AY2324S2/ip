import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    static List<Task> todo_list = new ArrayList<>();
    private static void list() {
        System.out.println("_______________________________________________________\n");

        for (int i = 0; i < todo_list.size(); i++) {
            String done = todo_list.get(i).getDone()? "X": " ";
            System.out.println(i + 1 + ".[" + done + "] " + todo_list.get(i));
        }

        System.out.println("_______________________________________________________\n");
    }
    private static void addTask(String name) {
        Task task = new Task(name);
        todo_list.add(task);
        System.out.println("_______________________________________________________\n added: " +
                name + "\n" +
                "_______________________________________________________\n");
    }

    public static void main(String[] args) {
        String greeting = "_______________________________________________________\n" +
                "Hello! I'm Thames and I'll be your assistant chatbot.\n" +
                "What can I do for you today?\n" +
                "_______________________________________________________\n";
        System.out.println(greeting);

        while(true) {
            Scanner sc = new Scanner(System.in);

            String input = sc.nextLine();
            if (input.equals("bye")) break;
            else if (input.equals("list")) {
                list();
            }
            else if (input.length() >= 6 && input.substring(0, 4).equals("mark")) {
                int index = Integer.parseInt(input.substring(input.length() - 1));
                Task task = todo_list.get(index - 1);
                task.mark();
                System.out.println("_______________________________________________________\n " +
                        "Nice! I've marked this task as done:\n  [X] " + task +
                        "\n_______________________________________________________\n");
            }
            else if (input.length() >= 8 && input.substring(0, 6).equals("unmark")) {
                int index = Integer.parseInt(input.substring(input.length() - 1));
                Task task = todo_list.get(index - 1);
                task.unmark();
                System.out.println("_______________________________________________________\n " +
                        "Ok, I've marked this task as not done yet:\n  [ ] " + task +
                        "\n_______________________________________________________\n");
            }
            else {
                addTask(input);
            }
        }

        String exit = "_______________________________________________________\n" +
                "Bye. Hope to see you soon!\n" +
                "_______________________________________________________\n";
        System.out.println(exit);
    }
}
