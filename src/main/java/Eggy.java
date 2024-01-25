import java.util.*;

public class Eggy {
    public static void main(String[] args) {
        String name = "Eggy";
        List<String> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm " + name + "\uD83E\uDD5A.");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String command = sc.nextLine();;
        while (!command.equals("bye")) {
            if (!command.equals("list")) {
                addTask(command, taskList);
            } else {
                showList(taskList);
            }
            command = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye\uD83D\uDC4B. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        sc.close();
    }

    public static void addTask(String task, List<String> taskList) {
        taskList.add(task);
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + task);
        System.out.println("    ____________________________________________________________");
    }

    public static void showList(List<String> taskList) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + taskList.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }
}
