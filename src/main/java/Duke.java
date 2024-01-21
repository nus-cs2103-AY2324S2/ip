import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greet = "    Hi! I am your favourite friend, Lelu :) \n    What can I do for you?\n";
        String exit = "    Ok bye, you shall be missed :(";
        System.out.println(greet);

        String[] tasks = new String[100];
        int index = 0;
        while (true) {
            String task = sc.nextLine();
            if (task.equals("bye")) {
                System.out.println(exit);
                break;
            } else if (task.equals("list")) {
                for (int i = 0; i < 100; i++) {
                    if (tasks[i] == null) {
                        System.out.println("\n");
                        break;
                    }
                    System.out.println("    " + (i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[index++] = task;
                System.out.println("    added: " + task + "\n");
            }
        }

    }
}
