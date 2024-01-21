import java.util.Scanner;
public class Saopig {
    enum Command {
        COMMAND_BYE,
        COMMAND_UNKNOWN,
        // Add more commands here in the future
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        mainLoop:
        while (true) {
            String input = scanner.nextLine();
            Command command = getCommandFromString(input);
            switch (command) {
                case COMMAND_BYE:
                    speak("Bye. Hope to see you again soon!");
                    break mainLoop;
                case COMMAND_UNKNOWN:
                    speak(input);
                    break;
            }
        }
    }

    public static Command getCommandFromString(String input) {
        switch (input.trim().toUpperCase()) {
            case "BYE":
                return Command.COMMAND_BYE;
            default:
                return Command.COMMAND_UNKNOWN;
        }
    }

    // speak to user
    public static void speak(String str) {
        System.out.println(str);
        System.out.println("____________________________________________________________");
    }

    // greet user
    public static void greet() {
        String logo = " ____    _    ___  ____ ___ ____   ____   ___ _____ \n" +
                "/ ___|  / \\  / _ \\|  _ \\_ _/ ___| | __ ) / _ \\_   _|\n" +
                "\\___ \\ / _ \\| | | | |_) | | |  _  |  _ \\| | | || |  \n" +
                " ___) / ___ \\ |_| |  __/| | |_| | | |_) | |_| || |  \n" +
                "|____/_/   \\_\\___/|_|  |___\\____| |____/ \\___/ |_|\n";
        speak(logo);
        speak("Hello! I'm SAOPIG BOT\nWhat can I do for you?");
    }
}
