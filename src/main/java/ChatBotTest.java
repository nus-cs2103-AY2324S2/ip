import java.util.Scanner;

public class ChatBotTest {
    public static void main(String[] args) {
        Ping p = new Ping();
        String name = p.name;

        // The Welcome Part
        System.out.println("Hello! I'm "+name+
                "\nWhat can I do for you?");

        // The Scanner Part
        Scanner sc = new Scanner(System.in);
        while (true) {
            String commands = sc.nextLine();
            if (commands.equals("bye")) {
                p.goodBye();
                break;
            } else if (commands.equals("list")) {
                p.listTasks();
            } else if (commands.indexOf("mark") == 0) {
                String[] markCommand = commands.split(" ");
                try {
                    int i = Integer.parseInt(markCommand[1]) - 1;
                    p.markJobs(p.tasks.get(i));
                } catch (Exception e) {
                    System.out.println("Incorrect number or command");
                }
            } else if (commands.indexOf("unmark") == 0) {
                String[] unmarkCommand = commands.split(" ");
                try {
                    int i = Integer.parseInt(unmarkCommand[1]) - 1;
                    p.unMarkJobs(p.tasks.get(i));
                } catch (Exception e) {
                    System.out.println("Incorrect number or command");
                }
            }
            else {
                Task t = new Task(commands);
                p.addTask(t);
            }
        }


    }
}
