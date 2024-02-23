package eve;

public class Eve{
    /**
     * Main method of the program.
     * Runs a Hello command and then the listener command that starts the whole thing.
     * @param args
     */

 


    public static void main(String[] args) {
        Commands.commandHello();
        Commands.commandListener();

    }


    public String getResponse(String input) {
        return "Eve heard: " + input;
    }

}
