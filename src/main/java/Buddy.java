import java.sql.Array;
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

    private void echo() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList();

        while (running) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                running = false;
            } else if (input.equals("list")) {
                System.out.print(lineBreak);
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". "+ taskList.get(i));
                }
                System.out.println(lineBreak);
            } else {
                taskList.add(input);
                String output = "added: " + input + "\n";
                System.out.println(lineBreak + output + lineBreak);
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Buddy buddy = new Buddy();
        buddy.greet();
        buddy.echo();
        buddy.exit();
    }
}
