package chatbot;
public class AlfredException extends Exception{
    protected static final String line = "________________________________________________________________________________________";
    public AlfredException(String message) {
        super(message);
    }
    public void printError() {
        System.out.println(line);
        System.out.println(this.getMessage());
        System.out.println(line);
    }
}
