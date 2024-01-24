package jade;
import java.util.*;

public class Duke {
    public static String line = "\t——————————————————————————————————————————\n";
    public static boolean exitProg = false;
    public static List<Task> userList = new ArrayList<>();


    public static void main(String[] args) {

        String logo = "\t  ____   ___    ____     ______  \n"
                    + "\t  |  |  / _ \\  |  ___ \\ / |____/ \n"
                    + "\t  |  | | | | | | |  | | | |____  \n"
                    + "\t  |  | | |_| | | |  | | | |____| \n"
                    + "\t|\\|  | | ___ | | |__| | | |____  \n"
                    + "\t \\___| |_| |_| |_____/  \\_|____\\ \n";

        System.out.printf("%s%s\tHello! I'm Jade\n\that can I do for you?\n%s", logo, line, line);
        Scanner scanner = new Scanner(System.in);
        while(!exitProg) {
            String command = scanner.nextLine();
            echo(command);
        }
    }

    public static void echo(String command) {
        String[] splitCommand = command.split(" ");
        switch(splitCommand[0]) {
            case "list":
                System.out.println(line + "\tHere are the tasks in your list:");
                for (int i = 1; i <= userList.size(); i++) {
                    System.out.printf("\t%d. %s\n", i, userList.get(i-1));
                }
                System.out.print(line);
                break;
            case "mark":
                int indexMark = Integer.parseInt(splitCommand[1]);
                userList.get(indexMark-1).mark();
                System.out.printf("%s\tNice! I've marked this task as done:\n\t  %s\n%s", line, userList.get(indexMark-1), line);
                break;
            case "unmark":
                int indexUnmark = Integer.parseInt(splitCommand[1]);
                userList.get(indexUnmark-1).unMark();
                System.out.printf("%s\tOK, I've marked this task as not done yet:\n\t  %s\n%s", line, userList.get(indexUnmark-1), line);
                break;
            case "bye":
                System.out.printf("%s\tBye. Hope to see you again soon!\n%s",line,line);
                exitProg = true;
                break;
            default:
                userList.add(new Task(command));
                System.out.printf("%s\tadded: %s\n%s", line, command, line);
                break;
        }

    }
}
