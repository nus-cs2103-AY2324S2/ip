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

        System.out.printf("%s%s\tHello! I'm Jade\n\twhat can I do for you?\n%s", logo, line, line);
        Scanner scanner = new Scanner(System.in);
        while(!exitProg) {
            String command = scanner.nextLine();
            echo(command);
        }
    }

    public static void echo(String command) {
        String[] splitCommand = command.split(" ");
        switch(splitCommand[0]) {
            case "todo":
                String todoDescription = String.join(" ", Arrays.copyOfRange(splitCommand, 1, splitCommand.length));
                Task todoT = new Todo(todoDescription);
                userList.add(todoT);
                System.out.printf("%s\tGot it. I've added this task:\n\t %s\n\tNow you have %d task(s) in the list.\n%s", line, todoT , userList.size(), line);
                break;
            case "deadline":
                String deadlineDescription =  String.join(" ", Arrays.copyOfRange(splitCommand, 1, Arrays.asList(splitCommand).indexOf("/by")));
                String deadlineDate = String.join(" ", Arrays.copyOfRange(splitCommand, Arrays.asList(splitCommand).indexOf("/by") + 1, splitCommand.length));
                Task deadlineT = new Deadline(deadlineDescription, deadlineDate);
                userList.add(deadlineT);
                System.out.printf("%s\tGot it. I've added this task:\n\t %s\n\tNow you have %d task(s) in the list.\n%s", line, deadlineT , userList.size(), line);
                break;
            case "event":
                String eventDescription =  String.join(" ", Arrays.copyOfRange(splitCommand, 1, Arrays.asList(splitCommand).indexOf("/from")));
                String startDate = String.join(" ", Arrays.copyOfRange(splitCommand, Arrays.asList(splitCommand).indexOf("/from") + 1, Arrays.asList(splitCommand).indexOf("/to")));
                String endDate = String.join(" ", Arrays.copyOfRange(splitCommand, Arrays.asList(splitCommand).indexOf("/to") + 1, splitCommand.length));
                Task eventT = new Event(eventDescription, startDate, endDate);
                userList.add(eventT);
                System.out.printf("%s\tGot it. I've added this task:\n\t %s\n\tNow you have %d task(s) in the list.\n%s", line, eventT , userList.size(), line);
                break;
            case "list":
                System.out.println(line + "\tHere are the task(s) in your list:");
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
                break;
        }

    }
}
