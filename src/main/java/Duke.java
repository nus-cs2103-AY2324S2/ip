import java.util.*;

public class Duke {
    public static String line = "——————————————————————————————————————————";
    public static boolean exitProg = false;
    public static List<String> userList = new ArrayList<>();


    public static void main(String[] args) {

        String logo = "  ____   ___    ____     ______  \n"
                    + "  |  |  / _ \\  |  ___ \\ / |____/ \n"
                    + "  |  | | | | | | |  | | | |____  \n"
                    + "  |  | | |_| | | |  | | | |____| \n"
                    + "|\\|  | | ___ | | |__| | | |____  \n"
                    + " \\___| |_| |_| |_____/  \\_|____\\ \n";

        System.out.println(String.format("%s%s\nHello! I'm Jade\nWhat can I do for you?\n%s", logo, line, line ,line));
        Scanner scanner = new Scanner(System.in);
        while(!exitProg) {
            String input = scanner.nextLine();
            echo(input);
        }
    }

    public static void echo(String command) {
        switch(command) {
            case "bye":
                System.out.println(String.format("%s\nBye. Hope to see you again soon!\n%s",line,line));
                exitProg = true;
                break;
            case "list":
                System.out.println(line);
                for (int i = 1; i <= userList.size(); i++) {
                    System.out.println(String.format("%d. %s", i, userList.get(i-1)));
                }
                System.out.println(line);
                break;
            default:
                userList.add(command);
                System.out.println(String.format("%s\nadded: %s\n%s", line, command, line));
                break;
        }

    }
}
