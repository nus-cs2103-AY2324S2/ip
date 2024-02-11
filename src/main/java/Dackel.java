public class Dackel {
    /** String constants */
    private static final String NAME = "DACKEL";
    private static final String LOGO = " ____    __    ___  _  _  ____  __   \n"
        + "(  _ \\  /__\\  / __)( )/ )( ___)(  )  \n"
        + " )(_) )/(__)\\( (__  )  (  )__)  )(__ \n"
        + "(____/(__)(__)\\___)(_)\\_)(____)(____) ";
    private static final String LINE = "____________________________________________________________";

    /**
     * Simulates Dackel speaking to the user ala a chatroom interface
     * 
     * @param string Message for Dackel to send
     */
    private static void speak(String message) {
        // TODO: make this take in String[] args
        System.out.println(NAME + ": " + message);
    }

    public static void main(String[] args) {
        // Title cards, etc.
        System.out.println(LINE);
        System.out.println(LOGO);
        System.out.println(LINE);
        System.out.println();

        // Greeting
        // TODO: make dackel read its lines from a file
        speak("hihi!");
        speak("what can i do for you today?");
        speak("hope to see you again soon!");
    }
}
