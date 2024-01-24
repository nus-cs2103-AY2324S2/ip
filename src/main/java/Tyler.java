import java.util.*;

public class Tyler {
    private static Task[] taskList = new Task[100];
    private static int curr = 0;

    public static void main(String[] args) {
        System.out.println("    Hello from Tyler");
        System.out.println("    What can I do for you?");
        System.out.println("    --------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            if (input.equals("list")) {
                Tyler.list();
            } else {
                System.out.println("    --------------------------------------------------");
                Task newTask = new Task(input);
                taskList[curr] = newTask;
                System.out.println("    added: " + input);
                System.out.println("    --------------------------------------------------");
                curr++;
            }
            input = sc.nextLine();
        }
        System.out.println("    Bye. Hope to see you again");
    }

    public static void list() {
        System.out.println("    --------------------------------------------------");
        for(int i = 1; i < 100; i++) {
            if (taskList[i - 1] == null) {
                break;
            }
            String task = taskList[i - 1].toString();
            System.out.println("    " + i + ". " + task);
        }
        System.out.println("    --------------------------------------------------");
    }
}
