import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String gap = "____________________________________________________________\n";
        System.out.println(logo);
        System.out.println(gap + "Greetings! I am Aegis.\n"
                         + "How can I assist you?\n" + gap);
        Scanner input = new Scanner(System.in);
        String command;
        ArrayList<String> tasks = new ArrayList<>();
        while (true) {
            command = input.nextLine();
            if (command.toLowerCase().equals("bye")) {
                break;
            }
            if (command.toLowerCase().equals("list")) {
                String taskList = "";
                for (int i = 0; i < tasks.size(); i++) {
                    taskList += ((i+1) + ". " + tasks.get(i) + "\n");
                }
                System.out.println(gap + taskList + gap);
            } else {
                tasks.add(command);
                System.out.println(gap + "Added: " + command + "\n" + gap);
            }
        }
        System.out.println(gap + "Goodbye! Have a pleasant day!\n" + gap);
    }
}
