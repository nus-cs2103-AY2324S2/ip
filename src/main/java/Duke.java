import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    private static String lineBreak = "______________________________________________";
    private static ArrayList<Task> lst = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        greet();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        while (!(command = br.readLine()).equals("bye")) {
            if (command.equals("list")) {
                printLst();
            } else if (command.substring(0, 4).equals("mark")) {
                int num = Character.getNumericValue(command.charAt(command.length() - 1));
                markTask(num);
            } else if (command.substring(0, 6).equals("unmark")) {
                int num = Character.getNumericValue(command.charAt(command.length() - 1));
                unmarkTask(num);
            } else {
                Task newTask = new Task(command);
                addLst(newTask);
            }
        }
        exit();
    }

    public static void greet() {
        System.out.println(lineBreak);
        System.out.println(" Hello! I'm SnowBoy\n" + " What can I do for you?");
        System.out.println(lineBreak);
    }

    public static void addLst(Task newTask) {
        lst.add(newTask);
        System.out.println(lineBreak);
        System.out.println(" added: " + newTask.getDescription());
        System.out.println(lineBreak);
    }
    public static void printLst() {
        System.out.println(lineBreak);
        if (lst.size() == 0) {
            System.out.println(" Whoops! Your list is empty :(");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < lst.size(); i++) {
                System.out.println(" " + (i + 1) + "." + lst.get(i).getStatus() + " " + lst.get(i).getDescription());
            }
        }
        System.out.println(lineBreak);
    }

    public static void markTask(int num) {
        lst.get(num - 1).markAsDone();
        System.out.println(lineBreak);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + lst.get(num - 1).getStatus() + " " + lst.get(num - 1).getDescription());
        System.out.println(lineBreak);
    }

    public static void unmarkTask(int num) {
        lst.get(num - 1).markAsUndone();
        System.out.println(lineBreak);
        System.out.println(" OK! I've marked this task as not done yet:");
        System.out.println("  " + lst.get(num - 1).getStatus() + " " + lst.get(num - 1).getDescription());
        System.out.println(lineBreak);
    }

    public static void exit() {
        System.out.println(lineBreak);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}
