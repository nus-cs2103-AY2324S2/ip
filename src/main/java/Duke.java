import java.util.Scanner;
public class Duke {

    public static void commandHandler(String divider) {
        Scanner scanner = new Scanner(System.in);
        String outro = "Bye. Hope to see you soon!";
        Boolean isExit = false;
        String command = "";
        while (!isExit){
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(divider);
                System.out.println("    "+outro);
                System.out.println(divider);
                isExit = true;
                break;
            }
            System.out.println(divider);
            System.out.println("    "+command);
            System.out.println(divider);
        }
    }

    public static void introPrinter(String botName, String divider) {
        System.out.println(divider);
        System.out.println("   Hello there! I'm " + botName);
        System.out.println("   What can I do for you today ?");
        System.out.println(divider);
    }

    public static void main(String[] args) {
        String botName = "TOBIAS";
        String divider = "  ---------------------------------------------------------------------------------------";
        introPrinter(botName, divider);
        commandHandler(divider);
    }
}
