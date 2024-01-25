import java.util.*;

public class Eggy {
    public static void main(String[] args) {
        String name = "Eggy";
        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm " + name + "\uD83E\uDD5A.");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            String[] commandArr = command.split(" ");
            System.out.println("    ____________________________________________________________");
            if (command.equals("list")) {
                showList(taskList);
            } else if (commandArr[0].equals("mark")) {
                taskList.get(Integer.parseInt(commandArr[1]) - 1).markDone();
            } else if (commandArr[0].equals("unmark")) {
                taskList.get(Integer.parseInt(commandArr[1]) - 1).unmarkDone();
            } else {
                addTask(command, taskList);
            }
            System.out.println("    ____________________________________________________________");
            command = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye\uD83D\uDC4B. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        sc.close();
    }

    public static void addTask(String taskName, List<Task> taskList) {
        taskList.add(new Task(taskName));
        System.out.println("     added: " + taskName);
    }

    public static void showList(List<Task> taskList) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i).toString());
        }
    }
}
