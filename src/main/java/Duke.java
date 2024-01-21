import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Duke {
    // constants
    static final String LINE = "\t――――――――――――――――――――――――――――――――――――――――\n";
    static final String CHAT_BOT_NAME = "Bob";
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        greet();
        boolean isExiting = false;
        while (!isExiting) {
            String[] inputs = br.readLine().split(" ");
            System.out.print(LINE);
            switch (inputs[0]) {
                case "bye":
                    isExiting = true;
                    break;
                case "list":
                    if (tasks.isEmpty()) {
                        System.out.println("\t Congrats, you have cleared all your tasks!");
                    }
                    for (int i = 0; i < tasks.size(); i++){
                        System.out.println("\t " + (i+1) + ". " + tasks.get(i));
                    }
                    break;
                case "mark":
                    int index = Integer.parseInt(inputs[1]);
                    if (index <= tasks.size() && index > 0) {
                        tasks.get(index - 1).mark();
                        System.out.println("\t Nice! I've marked this task as done:\n\t\t"
                                + tasks.get(index - 1));
                    } else {
                        System.out.println("\t Error: index out of bounds");
                    }
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(inputs[1]);
                    if (unmarkIndex <= tasks.size() && unmarkIndex > 0) {
                        tasks.get(unmarkIndex - 1).unmark();
                        System.out.println("\t OK, I've marked this task as not done yet:\n\t\t"
                                + tasks.get(unmarkIndex - 1));
                    } else {
                        System.out.println("\t Error: index out of bounds");
                    }
                    break;
                default:
                    echo(inputs[0]);
                    Task task = new Task(inputs[0]);
                    tasks.add(task);
            }
            System.out.print(LINE);
        }
        exit();
    }

    // greet user
    public static void greet() {
        System.out.println(LINE + "\t Hello! I'm " + CHAT_BOT_NAME + "\n\t"
                + "What can I do for you?\n" + LINE);
    }

    // exit statement
    public static void exit() {
        System.out.println(LINE + "\t Bye! Hope to see you again soon!\n" + LINE);
    }

    // echo user inputs
    public static void echo(String input) {
        System.out.println("\t added: " + input);
    }
}