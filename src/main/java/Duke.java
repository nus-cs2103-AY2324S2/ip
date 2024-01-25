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
        while (true) {
            command = input.nextLine();
            if (command.toLowerCase().equals("bye")) {
                break;
            }
            System.out.println(gap + command + "\n" + gap);
        }
        System.out.println(gap + "Goodbye! Have a pleasant day!\n" + gap);
    }
}
