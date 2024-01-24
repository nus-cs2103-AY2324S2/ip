import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "Hello! I'm DOUMMI\n" +
                "What can I do for you?";

        System.out.println(greet);


        Task[] task = new Task[100];
        int count = 0;

        Scanner cmd = new Scanner(System.in);

        while (!cmd.hasNext("bye")) {
            if (cmd.hasNext("list")) {
                for (int i = 0; i < task.length; i++) {
                    if (task[i] == null) {
                        break;
                    }
                    int n = i + 1;
                    System.out.println(n + ". [" + task[i].getStatusIcon() + "] " + task[i].description);
                }
                String userCmd = cmd.nextLine();
            } else if (cmd.hasNext("mark")) {
                String userCmd = cmd.nextLine();
                String [] uCmd = userCmd.split(" ");
                int index = Integer.parseInt(uCmd[1]) - 1;
                task[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "\t[X] " + task[index].description);
            } else if (cmd.hasNext("unmark")) {
                String userCmd = cmd.nextLine();
                String [] uCmd = userCmd.split(" ");
                int index = Integer.parseInt(uCmd[1]) - 1;
                task[index].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        "\t[ ] " + task[index].description);
            } else {
                if (count > 100) {
                    break;
                }
                String userCmd = cmd.nextLine();

                task[count] = new Task(userCmd);
                count++;
                System.out.println("added: " + userCmd);
            }
        }


        String bye = "Hope to see you again soon!";

        System.out.println(bye);
    }
}
