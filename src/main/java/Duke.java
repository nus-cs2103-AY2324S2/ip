import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Memory memory = new Memory();
        greet();
        echo(memory);
        bye();
    }

    public static void indent() {
        System.out.print("    ");
    }

    public static void drawLine() {
        indent();
        System.out.println("____________________________________________________________");
    }

    public static void output(String ... sentences) {
        drawLine();
        for (String sentence : sentences) {
            indent();
            System.out.println(sentence);
        }
        drawLine();
    }

    public static void echo(Memory memory) {
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        boolean success;
        String sentence;
        ArrayList<String> tasks;
        int numTasks;
        while (!task.equals("bye")) {
            if (task.equals("list")) {
                tasks = memory.getList();
                numTasks = tasks.size();
                String[] args = new String[numTasks];
                for (int i = 0; i < tasks.size(); i++) {
                    args[i] = (i + 1) + ". " + tasks.get(i);
                }
                output(args);
            } else {
                success = memory.add(task);
                if (success) {
                    sentence = "added: " + task;
                    output(sentence);
                }
            }
            task = sc.nextLine();
        }
        sc.close();
    }

    public static void greet() {
        String name = "Cortana";
        String sentence1 = "Hello! I'm " + name;
        String sentence2 = "What can I do for you?";
        output(sentence1, sentence2);
    }

    public static void bye() {
        String sentence = "Bye. Hope to see you again soon!";
        output(sentence);
    }

}
