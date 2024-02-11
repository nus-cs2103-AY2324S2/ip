import java.util.Scanner;

public class Dackel {
    /** Command aliases */
    private static final String QUIT = "bye";

    /** Other String constants */
    private static final String NAME = "DACKEL";
    private static final String LOGO = " ____    __    ___  _  _  ____  __   \n"
        + "(  _ \\  /__\\  / __)( )/ )( ___)(  )  \n"
        + " )(_) )/(__)\\( (__  )  (  )__)  )(__ \n"
        + "(____/(__)(__)\\___)(_)\\_)(____)(____) ";
    private static final String LINE = "____________________________________________________________";
    private static final String VERSION = "Version 0.1";

    /** Scanner for receiving user input*/
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Simulates Dackel speaking to the user ala a chatroom interface
     * 
     * @param string Message for Dackel to send
     */
    private static void speak(String message) {
        // TODO: make this take in String[] args
        System.out.println(NAME + ": " + message);
    }

    /**
     * Prompts user for input and returns it
     * 
     * @return User-inputted string
     */
    private static String receiveInput() {
        System.out.print("> ");
        String input = SCANNER.nextLine();
        return input;
    }

    public static void main(String[] args) {
        // title cards, etc.
        System.out.println(LINE);
        System.out.println(LOGO);
        System.out.println(VERSION);
        System.out.println(LINE);
        System.out.println();

        // greeting message
        speak("hihi!");
        speak("what can i do for you today?");
        
        // main command entry loop
        // TODO: make dackel read its lines from a file
        while (true) {
            String input = receiveInput();
            if (input.equals(QUIT)) {
                break;
            }
            else {
                speak(input);
                // TODO: make a separate command for echo?
            }
        }

        // goodbye message
        speak("hope to see you again soon!");
    }
}
