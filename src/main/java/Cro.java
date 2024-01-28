import java.util.*;
public class Cro {
    static String welcomeMessage = "-----------------------------------\n"
                            + "Hello! I'm Cro!\n"
                            + "What can I do for you?\n"
                            + "-----------------------------------\n";
    static List<Task> taskList = new ArrayList<>();
    public static void addToTasks(String input) {
        taskList.add(new Task(input));
        System.out.println("-----------------------------------");
        System.out.println("added: " + input);
        System.out.println("-----------------------------------");
    }
    public static void displayTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            String output = String.format("%d. %s", i+1, taskList.get(i).getDisplayString());
            System.out.println(output);
        }
    }

    public static void markTaskAsDone(int taskNo) {
        if (taskNo > taskList.size()) {
            System.out.println("-----------------------------------");
            System.out.println("Task not found!");
            System.out.println("-----------------------------------");
        } else {
            taskList.get(taskNo-1).markAsDone();
            System.out.println("-----------------------------------");
            System.out.println("Nice! I've marked this task as done!");
            System.out.println(taskList.get(taskNo-1).getDisplayString());
            System.out.println("-----------------------------------");
        }
    }

    public static void markTaskAsUndone(int taskNo) {
        if (taskNo > taskList.size()) {
            System.out.println("-----------------------------------");
            System.out.println("Task not found!");
            System.out.println("-----------------------------------");
        } else {
            taskList.get(taskNo-1).markAsUndone();
            System.out.println("-----------------------------------");
            System.out.println("OK, I've marked this task as not done yet.");
            System.out.println(taskList.get(taskNo-1).getDisplayString());
            System.out.println("-----------------------------------");
        }
    }
    public static void main(String[] args) {
        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String inText = sc.nextLine();
            String[] splitStr = inText.trim().split("\\s+");
            if (splitStr[0].equals("bye")) {
                System.out.println("-----------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------");
                break;
            } else if (splitStr[0].equals("list")) {
                displayTasks();
            } else if (splitStr[0].equals("mark")) {
                markTaskAsDone(Integer.valueOf(splitStr[1]));
            } else if (splitStr[0].equals("unmark")) {
                markTaskAsUndone(Integer.valueOf(splitStr[1]));
            } else {
                addToTasks(inText);
            }
        }

    }
}
