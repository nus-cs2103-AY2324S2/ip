import java.util.*;
public class Cro {
    static String welcomeMessage = "-----------------------------------\n"
                            + "Hello! I'm Cro!\n"
                            + "What can I do for you?\n"
                            + "-----------------------------------\n";
    static List<String> taskList = new ArrayList<>();
    public static void addToTasks(String input) {
        taskList.add(input);
        System.out.println("-----------------------------------");
        System.out.println("added: " + input);
        System.out.println("-----------------------------------");
    }
    public static void displayTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            String output = String.format("%d. %s", i+1, taskList.get(i));
            System.out.println(output);
        }
    }
    public static void main(String[] args) {
        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String inText = sc.nextLine();
            if (inText.equals("bye")) {
                System.out.println("-----------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------");
                break;
            } else if (inText.equals("list")) {
                displayTasks();
            } else {
                addToTasks(inText);
            }
        }

    }
}
