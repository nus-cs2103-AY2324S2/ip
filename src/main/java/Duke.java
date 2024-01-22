import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________");
        System.out.println("Hello! I'm Waffles");
        System.out.println("What can I do for you?");
        System.out.println("____________________");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        while (true) {
            String command = sc.nextLine();

            if (command.equalsIgnoreCase("list")) {
                listTasks(tasks);
            } else if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________");
                break;
            } else {
                echoAndAddTask(command, tasks);
            }
        }

        sc.close();

    }

    private static void echoAndAddTask(String task, ArrayList<String> taskList) {
        taskList.add(task);
        String output = String.format("added: %s", task);
        System.out.println("____________________");
        System.out.println(output);
        System.out.println("____________________");

    }

    private static void listTasks(ArrayList<String> taskList) {
        System.out.println("____________________");
        for (int i = 0; i < taskList.size(); i++) {
            String task = String.format("%d. %s", i + 1, taskList.get(i));
            System.out.println(task);
        }
        System.out.println("____________________");
    }

}
