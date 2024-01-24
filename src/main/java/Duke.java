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
                    System.out.println(n + ". " + task[i].toString());
                }
                String userCmd = cmd.nextLine();
            } else if (cmd.hasNext("mark")) {
                String userCmd = cmd.nextLine();
                String [] uCmd = userCmd.split(" ");
                int index = Integer.parseInt(uCmd[1]) - 1;
                task[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "\t" + task[index].toString());
            } else if (cmd.hasNext("unmark")) {
                String userCmd = cmd.nextLine();
                String [] uCmd = userCmd.split(" ");
                int index = Integer.parseInt(uCmd[1]) - 1;
                task[index].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        "\t" + task[index].toString());
            } else if (cmd.hasNext("todo")) {
                String userCmd = cmd.nextLine();
                String [] divided = userCmd.split(" ", 2);
                String D = divided[1];
                task[count] = new ToDos(D);
                int n = count +1;
                String length = "" + n;
                System.out.println("Got it. I've added this task:\n" +
                        "\t" + task[count].toString());
                System.out.println("\nNow you have " + length + " tasks in the list.");
                count++;
            } else if (cmd.hasNext("event")) {
                String userCmd = cmd.nextLine();
                String [] divided = userCmd.split(" ", 2);
                String D = divided[1];
                divided = D.split("/from", 2);
                D = divided[0];
                String fromTo = divided[1];
                divided = fromTo.split("/to", 2);
                System.out.println(divided[0]);
                String from = divided[0];
                String to = divided[1];
                task[count] = new Events(D, from, to);
                int n = count +1;
                String length = "" + n;
                System.out.println("Got it. I've added this task:\n" +
                        "\t" + task[count].toString());
                System.out.println("\nNow you have " + length + " tasks in the list.");
                count++;
            } else if (cmd.hasNext("deadline")) {
                String userCmd = cmd.nextLine();
                String [] divided = userCmd.split(" ", 2);
                String D = divided[1];
                divided = D.split("/by", 2);
                D = divided[0];
                String by = divided[1];
                task[count] = new Deadline(D, by);
                int n = count +1;
                String length = "" + n;
                System.out.println("Got it. I've added this task:\n" +
                        "\t" + task[count].toString());
                System.out.println("\nNow you have " + length + " tasks in the list.");
                count++;
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
