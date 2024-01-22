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
        ArrayList<Boolean> completed;
        int numTasks;
        while (!task.equals("bye")) {
            if (task.equals("list")) {
                tasks = memory.getTasks();
                completed = memory.getCompleted();
                numTasks = tasks.size();
                String[] args = new String[numTasks + 1];
                getTasksList(args, tasks, completed, numTasks);
                output(args);
            } else if (task.startsWith("mark")) {
                // get last char of task
                int index = Integer.parseInt(task.substring(task.length() - 1));
                tasks = memory.getTasks();
                completed = memory.getCompleted();
                numTasks = tasks.size();
                success = memory.mark(index - 1);
                if (success) {
                    output("Nice! I've marked this task as done:", "  [X] " + tasks.get(index - 1)); 
                }
            } else if (task.startsWith("unmark")) {
                // get last char of task
                int index = Integer.parseInt(task.substring(task.length() - 1));
                tasks = memory.getTasks();
                completed = memory.getCompleted();
                numTasks = tasks.size();
                success = memory.unmark(index - 1);
                if (success) {
                    output("OK, I've marked this task as not done yet:", "  [ ] " + tasks.get(index - 1));
                }
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

    public static void getTasksList(String[] args, ArrayList<String> tasks, ArrayList<Boolean> completed, int n) {
        args[0] = "Here are the tasks in your list:";
        for (int i = 0; i < n; i++) {
            if (completed.get(i)) {
                args[i + 1] = "[X] " + tasks.get(i);
            } else {
                args[i + 1] = "[ ] " + tasks.get(i);
            }
        }
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
