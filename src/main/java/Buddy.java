import java.util.ArrayList;
import java.util.Scanner;

public class Buddy {
    private String lineBreak = "____________________________________________________________\n";

    private void greet() {
        String logo =
            "               ____            _     _       \n" +
            "              |  _ \\          | |   | |      \n" +
            "              | |_) |_   _  __| | __| |_   _ \n" +
            "              |  _ <| | | |/ _` |/ _` | | | |\n" +
            "              | |_) | |_| | (_| | (_| | |_| |\n" +
            "              |____/ \\__,_|\\__,_|\\__,_|\\__, |\n" +
            "                                        __/ |\n" +
            "                                       |___/ \n";
        System.out.println(lineBreak + logo + lineBreak + " Hello friend!\n" + " How can I help you?\n" + lineBreak);
    }

    private void exit() {
        System.out.println(lineBreak + " Bye. Hope to see you again soon!\n" + lineBreak);
    }

    private void run() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (running) {
            String input = sc.nextLine().trim();

            if (!input.equals("")) {
                int idx = input.indexOf(" ");
                String command;

                if (idx >= 0) {
                    command = input.substring(0, idx);
                } else {
                    command = input;
                }

                running = command(command, input, taskList);
            }
        }
        sc.close();
    }

    private boolean command(String cmd, String input, ArrayList<Task> taskList) {
        switch (cmd) {
            case "bye":
                return false;
            case "list":
                System.out.print(lineBreak);
                System.out.println("Here you go bud!:");

                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". "+ taskList.get(i));
                }
                System.out.println(lineBreak);
                return true;
            case "mark":
            case "unmark":
                int idx = Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
                Task task = taskList.get(idx);
                task.changeStatus();
                System.out.println(lineBreak + task + "\n" + lineBreak);
                return true;
            default:
                taskList.add(new Task(input));
                String output = "added: " + input + "\n";
                System.out.println(lineBreak + output + lineBreak);
                return true;
        }
    }

    public static void main(String[] args) {
        Buddy buddy = new Buddy();
        buddy.greet();
        buddy.run();
        buddy.exit();
    }
}
