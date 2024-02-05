package chatbot;
public class AlfredException extends Exception{
    protected static final String line = "________________________________________________________________________________________";
    public AlfredException(String message) {
        super(message);
    }

    /**
     * Prints the error message in a formatted manner.
     */
    public void printError() {
        System.out.println(line);
        System.out.println(this.getMessage());
        System.out.println(line);
    }
    @Override
    public String toString() {
        return this.getMessage();
    }
}
