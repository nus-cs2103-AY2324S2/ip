import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Intro hi = new Intro();
        hi.response();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String command = sc.nextLine();
            Action response = CommandParser.parseCommand(command, taskList);
            if (command.equals("bye")) {
                break;
            }
            //response.response();
        }
    }
}


