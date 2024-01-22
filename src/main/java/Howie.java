import java.util.*;
import java.io.*;

public class Howie {
    public static void printVLine() {
        System.out.println("------------------------------------");
    }

    public static void intro() {
        printVLine();
        System.out.println("Hello! I'm Howie");
        System.out.println("What can I do for you?");
        printVLine();
    }

    public static void exit() {
        printVLine();
        System.out.println("Bye! I'll see you when I see you :)");
        printVLine();
    }

    public static void added(String item) {
        printVLine();
        System.out.println("Ok! I've added: " + item);
        printVLine();
    }

    public static void printAllTask(List<Task> tasks) {
        printVLine();
        System.out.println("Here are the list of your tasks:");
        for (int i=1; i<=tasks.size(); i++) {
            Task t = tasks.get(i-1);
            System.out.println(i +"." + t);
        }
        printVLine();
    }
    public static void main(String[] args) throws Exception {
        intro();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String[] input = br.readLine().split(" ");
            String s1 = input[0];
            switch (s1) {
                case "list":
                    printAllTask(tasks);
                    break;
                case "bye":
                    exit();
                    break;
                case "mark":
                    try {
                        Task toMark = tasks.get(Integer.parseInt(input[1])-1);
                        printVLine();
                        System.out.println("Acknowledged!\n" + toMark.setDone());
                        printVLine();
                    } catch (IndexOutOfBoundsException e) {
                        printVLine();
                        System.out.println("Opps! I can't seem to find the task.");
                        printVLine();
                    }
                    break;
                case "unmark":
                    try {
                        Task toMark = tasks.get(Integer.parseInt(input[1])-1);
                        printVLine();
                        System.out.println("Acknowledged!\n" + toMark.setUndone());
                        printVLine();
                    } catch (IndexOutOfBoundsException e) {
                        printVLine();
                        System.out.println("Cannot find task");
                        printVLine();
                    }
                    break;
                default:
                    StringBuilder item = new StringBuilder();
                    for (String s : input) {
                        item.append(s).append(" ");
                    }
                    tasks.add(new Task(item.toString()));
                    added(item.toString());
                    break;
            }
            if (s1.equals("bye")) {
                System.exit(0);
            }
        }
    }
}
