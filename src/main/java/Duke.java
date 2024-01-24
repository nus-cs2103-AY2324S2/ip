import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Intro hi = new Intro();
        hi.response();
        Scanner sc = new Scanner(System.in);

        try {
            while (sc.hasNext()) {
                String command = sc.nextLine();
                Action response = CommandParser.parseCommand(command, taskList);
                if (command.equals("bye")) {
                    break;
                }
                //System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Sorry " + e.getMessage());
        }
    }
}


