import java.util.Scanner;

public class Duke {
    public static boolean exitProg = false;
    public static String line = "\n——————————————————————————————————————————\n";

    public static void main(String[] args) {

        String logo = "  ____   ___    ____     ______  \n"
                    + "  |  |  / _ \\ |  ___ \\  / |____/ \n"
                    + "  |  | | | | | | |  | | | |____  \n"
                    + "  |  | | |_| | | |  | | | |____| \n"
                    + "|\\|  | | ___ | | |__| | | |____  \n"
                    + " \\___| |_| |_| |_____/  \\_|____\\ \n";

        System.out.println(String.format("%s%sHello! I'm Jade\nWhat can I do for you?%s", logo, line, line ,line));
        Scanner scanner = new Scanner(System.in);
        while(!exitProg) {
            String input = scanner.nextLine();
            echo(input);
        }
    }

    public static void echo(String command) {
        switch(command) {
            case "bye":
                System.out.println(line + "Bye. Hope to see you again soon!" + line);
                exitProg = true;
                break;
            default:
                System.out.println(line + command + line);
                break;
        }

    }
}
