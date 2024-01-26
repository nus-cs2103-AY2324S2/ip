import java.util.Scanner;

public class UserInterface {
    /*
    This class is for the the user interface.
    */

    Scanner scan;
    CommandProcessor cmd;
    Storage storage;

    String fileLocation = "savefile.txt";
    String greeting = "Hi! My name is HAL9000";
    String exit = "Bye! See ya soon";
    boolean startUpSuccess = false;

    public UserInterface() {
        scan = new Scanner(System.in);
        try {
            storage = new Storage(fileLocation);
            cmd = new CommandProcessor(storage);
            startUpSuccess = true;
        } catch (StartUpException e) {
            System.out.println(e.getMessage());
        }

    }

    public void greet() {
        System.out.println(greeting);
    }
    public void exit() {
        System.out.println(exit);
    }

    public void startUpFailure() {
        System.out.println("Hi, you failed to start up properly! Sorry, bye!");
    }

    public void poll() {
        boolean polling = true;
        while (polling) {
            String input = scan.nextLine();
            try {
                Command command = processCommand(input);
                if (command.isExit()) {
                    polling = false;
                } else {
                    cmd.processData(command, input);
                }
            } catch (InputException | ProcessingException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Command processCommand(String input) throws InputException {
        try {
            String commandString = input.split(" ")[0];
            Command command = Command.valueOf(commandString.toUpperCase());
            return command;
        } catch (IndexOutOfBoundsException e) {
            String message = "Something went wrong when processing your command: \n"
            + "Check your input again: " + input;
            throw  new InputException(message, e);
        } catch (IllegalArgumentException e) { 
            String message = "You inputted an unrecognizable command";
            throw new InputException(message);
        }
    }

    public void start() {
        if (!startUpSuccess) {
            startUpFailure();
            return;
        }

        greet();
        poll();
        exit();
    }
}
