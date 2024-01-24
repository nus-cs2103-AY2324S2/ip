import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "Hello! I'm DOUMMI\n" +
                "What can I do for you?";

        System.out.println(greet);

        String[] task = new String[100];
        int count = 0;

        Scanner cmd = new Scanner(System.in);

        while (!cmd.hasNext("bye")) {
            if (cmd.hasNext("list")) {
                for (int i = 0; i < task.length; i++) {
                    if (task[i] == null) {
                        break;
                    }
                    int n = i + 1;
                    System.out.println(n + ". " + task[i]);
                }
                String userCmd = cmd.nextLine();
            } else {
                if (count > 100) {
                    break;
                }
                String userCmd = cmd.nextLine();
                task[count] = userCmd;
                count++;
                System.out.println("added: " + userCmd);
            }
        }


        String bye = "Hope to see you again soon!";

        System.out.println(bye);
    }
}
