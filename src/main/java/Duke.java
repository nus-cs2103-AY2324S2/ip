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
        String[] commandArr;
        while (!(command = br.readLine()).equals("bye")) {
            commandArr = command.split(" ");
            switch (commandArr[0]) {
                case "list":
                    printLst();
                    break;
                case "mark":
                    int toMark = Integer.parseInt(commandArr[1]);
                    markTask(toMark);
                    break;
                case "unmark":
                    int toUnmark = Integer.parseInt(commandArr[1]);
                    unmarkTask(toUnmark);
                    break;
                case "todo":
                    String todo = command.substring(5);
                    Task newTodo = new Todo(todo);
                    addLst(newTodo);
                    break;
                case "deadline":
                    String[] deadlineArr = command.substring(9).split(" /by ");
                    Task newDeadline = new Deadline(deadlineArr[0], deadlineArr[1]);
                    addLst(newDeadline);
                    break;
                case "event":
                    String[] eventArr = command.substring(6).split(" /");
                    Task newEvent = new Event(eventArr[0], eventArr[1].substring(5), eventArr[2].substring(3));
                    addLst(newEvent);
                    break;
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
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask.toString());
        System.out.println(" Now you have " + lst.size() + " tasks in the list.");
        System.out.println(lineBreak);
    }
    public static void printLst() {
        System.out.println(lineBreak);
        if (lst.size() == 0) {
            System.out.println(" Whoops! Your list is empty :(");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < lst.size(); i++) {
                System.out.println(" " + (i + 1) + "." + lst.get(i).toString());
            }
        }
        System.out.println(lineBreak);
    }

    public static void markTask(int num) {
        lst.get(num - 1).markAsDone();
        System.out.println(lineBreak);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + lst.get(num - 1).toString());
        System.out.println(lineBreak);
    }

    public static void unmarkTask(int num) {
        lst.get(num - 1).markAsUndone();
        System.out.println(lineBreak);
        System.out.println(" OK! I've marked this task as not done yet:");
        System.out.println("   " + lst.get(num - 1).toString());
        System.out.println(lineBreak);
    }

    public static void exit() {
        System.out.println(lineBreak);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}
