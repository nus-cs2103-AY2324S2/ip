import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello it's a-me! Mario!\nWhat-a can I do fo' ya!");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while(true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Ciao! Mamma-Mia!");
                break;
            }
            switch(command) {
                case "list":
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i <= tasks.size();i++) {
                        Task task = tasks.get(i - 1);
                        sb.append(String.format("%d: %s\n", i, task));
                    }
                    if (tasks.isEmpty()) {
                        sb.append("No Tasks! Oopsie!");
                    }
                    System.out.println(sb);
                    break;
                default:
                    Task newTask = new Task(command, false);
                    tasks.add(newTask);
                    System.out.println("I added!–\n" + newTask.getDescription() +  "\n–Mamma-mia!");
                    break;
            }
        }
    }
}
